package com.karapu3z.tools.common;

import android.util.Log;
import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private List<ReusableThread> waitingThreads;
    private List<ReusableThread> workingThreads;
    int maxSize;
    public ThreadPool(int size){
        this.maxSize=size;
        waitingThreads=new LinkedList<ReusableThread>();
        workingThreads=new LinkedList<ReusableThread>();
    }

    public synchronized ReusableThread getThread(){
        ReusableThread res = null;
        if (workingThreads.size()+waitingThreads.size()<maxSize) {
            // If we still have some room - create a new thread
            res = new ReusableThread();
        } else if (waitingThreads.size()>0) {
            // Otherwise peek a thread from the pool
            res = waitingThreads.get(0);
            workingThreads.add(res);
            waitingThreads.remove(0);
        }
        return res;
    }

    public synchronized  void returnThread(ReusableThread thread) {
        if (workingThreads.contains(thread)) {
            if (!thread.isInterrupted() && !thread.isAlive()) {
                // Get the thread back to the pool
                workingThreads.remove(thread);
                waitingThreads.add(thread);
            } else {
                Log.e(ThreadPool.class.getSimpleName(), "Can't get the thread back - it's still active");
            }
        }
        notify();
    }
}
