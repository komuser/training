package com.example.training_hagita;

public class ThreadCountUp {

    public static void main(String[] args) {
        new SubThread().start();
        new Thread(new SubRunnable()).start();
    }
}

class SubThread extends Thread {

    private static final int THREAD_NUMBER = 500;
    private static final int THREAD_SLEEP_MILLIS = 100;

    @Override
    public void run() {
        for (int i = 1; i <= THREAD_NUMBER; i++) {
            try {
                // 0.1秒休止
                Thread.sleep(THREAD_SLEEP_MILLIS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 3の倍数
            if (i % 3 == 0) {
                System.out.println("SubThread:   " + i);
            }
        }
    }
}

class SubRunnable implements Runnable {

    private static final int RUNNABLE_NUMBER = 250;
    private static final int RUNNABLE_SLEEP_MILLIS = 200;

    @Override
    public void run() {
        for (int i = 1; i <= RUNNABLE_NUMBER; i++) {
            try {
                // 0.2秒休止
                Thread.sleep(RUNNABLE_SLEEP_MILLIS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 7の倍数
            if (i % 7 == 0) {
                System.out.println("SubRunnable: " + i);
            }
        }
    }
}