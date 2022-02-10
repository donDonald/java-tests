package com.example.ptaranov.agiftest;

import android.app.ActionBar;
import android.graphics.Rect;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AbsoluteLayout;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_gifView = (WebView)findViewById(R.id.progress);
        //m_gifView.loadUrl("file:///android_asset/piggy.gif");
        m_gifView.loadUrl("file:///android_asset/watch_loop1b-256.gif");
        m_gifView.setClickable(false);
        m_gifView.setEnabled(false);
        m_gifView.setGr
        android.view.ViewGroup.LayoutParams lp = m_gifView.getLayoutParams();
        int xz = 0;
        xz += 100;

/*
        //View view = new GifMovieView(this);
        //View view = new GifWebView(this, "file:///android_asset/piggy.gif");


        m_gifView = new GifWebView(this, "file:///android_asset/watch_loop1b.gif");
        //View view = new GifWebView(this, "file:///android_asset/478px-Seven_segment_display-animated.gif");
        //View view = new GifWebView(this, "file:///android_asset/cool-animated-gif-4.gif");
        //View view = new GifWebView(this, "file:///android_asset/SnakeSkull2.1.gif");
        //View view = new GifWebView(this, "file:///android_asset/piggy.gif");

        m_gifView.measure(100,100);
        int h = m_gifView.getHeight();
        int w = m_gifView.getWidth();
        float sX = m_gifView.getScaleX();
        float sY = m_gifView.getScaleY();
        android.view.ViewGroup.LayoutParams lp = m_gifView.getLayoutParams();
        m_gifView.setEnabled(false);
        setContentView(m_gifView);
*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    WebView m_gifView;
}
