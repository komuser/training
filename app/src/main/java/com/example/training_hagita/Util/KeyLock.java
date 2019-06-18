package com.example.training_hagita.Util;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class KeyLock {

    private static final String TAG = KeyLock.class.getName();
    /**
     * 抑止時間（ミリ秒）。
     */
    private final static int LOCK_TIME_MS = 500;
    /**
     * 抑止フラグ。
     */
    private static boolean mKeyHit = false;
    /**
     * タイマー。
     */
    private static Timer mTimer = null;

    /**
     * キー連打判定。<br>
     * 抑止時間はデフォルト値（500ms）を使用。
     * @return true：抑止中、false：抑止解除。
     */
    public static boolean isKeyHit() {
        return isKeyHit(LOCK_TIME_MS);
    }

    /**
     * キー連打判定。
     * @param time 抑止時間（ミリ秒）。
     * @return true：抑止中、false：抑止解除。
     */
    public static synchronized boolean isKeyHit(int time) {
        boolean ret = false;
        if (mKeyHit) {
            ret = true;
            Log.d(TAG, "isKeyHit()=true");
        } else {
            mKeyHit = true;
            mTimer = new Timer();
            mTimer.schedule(new TimerTask() {
                /**
                 * 抑止解除実行。
                 */
                @Override
                public void run() {
                    mKeyHit = false;
                    mTimer = null;
                }
            }, time);
        }
        return ret;
    }

    /**
     * キーロック強制解除。
     */
    public static synchronized void unlock() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        mKeyHit = false;
    }
}
