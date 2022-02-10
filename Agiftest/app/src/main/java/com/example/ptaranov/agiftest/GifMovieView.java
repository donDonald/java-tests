package com.example.ptaranov.agiftest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.os.SystemClock;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ptaranov on 17.03.15.
 */
public class GifMovieView extends View {
    private Movie       m_movie;
    private InputStream m_is;
    private long        m_movieStart;

    public GifMovieView(Context context) {
        super(context);
        try {
            //m_is=context.getAssets().open("watch_loop1b.gif");
            //m_is=context.getAssets().open("478px-Seven_segment_display-animated.gif");
            //m_is=context.getAssets().open("cool-animated-gif-4.gif");
            //m_is=context.getAssets().open("SnakeSkull2.1.gif");
            m_is=context.getAssets().open("piggy.gif");
            //m_is=context.getAssets().open("");
            //m_is=context.getAssets().open("");
            //m_is=context.getResources().openRawResource(R.drawable.watch_loop1b);
            assert(m_is!=null);
            m_movie=Movie.decodeStream(m_is);
            assert(m_movie!=null);
            int duration = m_movie.duration();
            if (0==duration) {
                throw new Exception("Zero movie duration!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onDraw(Canvas canvas) {
        //canvas.drawColor(Color.TRANSPARENT);
        canvas.drawColor(Color.RED);
        super.onDraw(canvas);
        final long now = SystemClock.uptimeMillis();
        if (m_movieStart == 0) {
            m_movieStart = now;
        }
        int duration = m_movie.duration();
        final int relTime = (int)((now - m_movieStart) % m_movie.duration());
        m_movie.setTime(relTime);
        m_movie.draw(canvas, 10, 10);
        this.invalidate();
//        canvas.drawColor(Color.WHITE);
//        super.onDraw(canvas);
//        long now=android.os.SystemClock.uptimeMillis();
////        System.out.println("now="+now);
//        if (movieStart == 0) {
//            movieStart = now;
//        }
////        System.out.println("\tmoviestart="+movieStart);
//        int relTime = (int)((now - movieStart) % movie.duration()) ;
////        System.out.println("time="+relTime+"\treltime="+movie.duration());
//        movie.setTime(relTime);
//        movie.draw(canvas,this.getWidth()/2-20,this.getHeight()/2-40);
//        this.invalidate();
    }
}
