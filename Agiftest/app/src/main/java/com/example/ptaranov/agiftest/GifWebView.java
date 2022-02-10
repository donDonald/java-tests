package com.example.ptaranov.agiftest;

import android.content.Context;
import android.graphics.Canvas;
import android.webkit.WebView;

/**
 * Created by ptaranov on 18.03.15.
 */
public class GifWebView extends WebView {
    public GifWebView(Context context, String path) {
        super(context);
        loadUrl(path);
    }
    protected void onDraw(Canvas canvas) {
        int h = getHeight();
        int w = getWidth();
        float sX = getScaleX();
        float sY = getScaleY();
        android.view.ViewGroup.LayoutParams lp = getLayoutParams();
        super.onDraw(canvas);

    }
}
