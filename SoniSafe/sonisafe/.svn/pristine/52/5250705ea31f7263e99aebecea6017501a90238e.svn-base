package com.karapu3z.tools.common;

import java.util.LinkedList;
import java.util.List;

public class ThreadsExecutor {
    ThreadPool pool;
    List<Runnable> waiting;
    ReusableThread waitingThread;

    public ThreadsExecutor(ThreadPool pool) throws ThreadExecutorException {
        this.pool = pool;
        waitingThread = pool.getThread();
        waiting = new LinkedList<Runnable>();
        while (waitingThread == null) {
            try {
                wait();
                waitingThread = pool.getThread();
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                throw new ThreadExecutorException();
            }
        }
        waitingThread.setRunnable(new ThreadsExecutorRunnable());
        waitingThread.start();
    }

    public void execute(Runnable r) {
        synchronized (waiting) {
            waiting.add(r);
            waiting.notifyAll();
        }
    }

    private class ThreadsExecutorRunnable implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                while (waiting.size() > 0) {
                    startNextThread();
                }
                try {
                    Thread.currentThread().sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void startNextThread() {
            final ThreadPool pool = ThreadsExecutor.this.pool;
            Runnable r;
            synchronized (waiting) {
                r = waiting.get(0);
                waiting.remove(0);
            }
            if (r == null)//нечего исполнять
                return;

            ReusableThread t = pool.getThread();
            while (t == null) {
                try {
                    pool.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                t = pool.getThread();
            }
            t.setRunnable(new OneThreadExecutor(r, t));
            t.start();
        }

        private class OneThreadExecutor implements Runnable {
            Runnable runnable;
            ReusableThread thread;

            public OneThreadExecutor(Runnable runnable, ReusableThread thread) {
                this.runnable = runnable;
                this.thread = thread;
            }

            @Override
            public void run() {
                runnable.run();
                pool.returnThread(thread);
            }
        }
    }

    public static class ThreadExecutorException extends Exception {
    }
}
