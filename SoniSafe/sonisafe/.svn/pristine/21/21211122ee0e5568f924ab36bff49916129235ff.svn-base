package com.karapu3z.tools.common;

public class ReusableThread extends Thread{
    Runnable r;
    public void setRunnable(Runnable r){
        this.r = r;
    }
    @Override
    public void run() {
        r.run();
    }
}
