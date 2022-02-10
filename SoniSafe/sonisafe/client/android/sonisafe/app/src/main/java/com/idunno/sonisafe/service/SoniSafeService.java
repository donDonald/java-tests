package com.idunno.sonisafe.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.karapu3z.tools.common.ThreadPool;
import com.karapu3z.tools.common.ThreadsExecutor;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class SoniSafeService extends Service {

    public class SoniSafeServiceBinder extends Binder
    {
        public SoniSafeServiceBinder(SoniSafeService service)
        {
            m_service = service;
        }

        public SoniSafeService getService ()
        {
            return m_service;
        }

        private SoniSafeService m_service;
    }
    public SoniSafeService() {
    }

    @Override
    public void onCreate() {
         super.onCreate();
         try {
             m_threadsExecutor = new ThreadsExecutor(new ThreadPool(10));
         } catch (ThreadsExecutor.ThreadExecutorException e) {
             e.printStackTrace();
             assert(false);
         }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new SoniSafeServiceBinder(this);
    }

    public interface PushSoundHandler {

        public abstract void onPushSoundComplete(ServerError error, ByteBuffer data);

    }

    public void pushSound(
            Context c,
            String url,
            int captureTimeMs,
            int sampleFrequency,
            int bitsPerSample,
            final PushSoundHandler  receiver) {
        Map<Long, Handler> indexes = getMap();
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                ByteBuffer data = null;
                ServerError error = null;
                if (msg.getData().containsKey(S_OK)) {
                    data = ByteBuffer.wrap(msg.getData().getByteArray(S_OK));
                }
                if (msg.getData().containsKey(S_FAIL)) {
                    error = new ServerError(msg.getData().getByteArray(S_FAIL));
                }
                receiver.onPushSoundComplete(error, data);
            }
        };
        long newIndex;
        synchronized (m_indexes) {
            newIndex = generateNewIndex();
            m_indexes.put(newIndex, handler);
        }
        m_threadsExecutor.execute(
                new PushSoundTask(newIndex,
                                  url,
                                  captureTimeMs,
                                  sampleFrequency,
                                  bitsPerSample) );
    }

    static void returnResult(Long index, ServerError error, ByteBuffer answer) {
        Bundle res = new Bundle();
        if (answer!=null) {
            res.putByteArray(S_OK, answer.array());
        }
        if (error!=null) {
            res.putByteArray(S_FAIL, error.array());
        }
        Message m = new Message();
        m.setData(res);

        Handler handler;
        synchronized (m_indexes) {
            handler = m_indexes.get(index);
            if(handler!=null) {
                m_indexes.remove(handler);
            }
        }
        if (handler != null) {
            handler.sendMessage(m);
        }
    }

    public static Map<Long, Handler> getMap() {
        if (m_indexes == null) {
            m_indexes = new HashMap<Long, Handler>();
        }
        return m_indexes;
    }


    private static long generateNewIndex() {
        long x = 0;
        while (m_indexes.containsKey(x)) {
            x = Math.round(Math.random() * Long.MAX_VALUE);
        }
        return x;
    }

    private ThreadsExecutor           m_threadsExecutor;
    private static Map<Long, Handler> m_indexes;

    private static final String S_FAIL = "FAIL";
    private static final String S_OK = "OK";
}
