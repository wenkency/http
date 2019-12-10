package cn.carhouse.http_sample.http;

import android.os.Handler;
import android.os.Looper;

/**
 * Handler工具类
 */
public class HandlerUtils {
    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public static void post(Runnable runnable) {
        if (runnable != null) {
            mHandler.post(runnable);
        }
    }

    public static void cancel(Runnable runnable) {
        if (runnable != null) {
            mHandler.removeCallbacks(runnable);
        }
    }

    public static void cancelAll() {
        mHandler.removeCallbacksAndMessages(null);
    }
}
