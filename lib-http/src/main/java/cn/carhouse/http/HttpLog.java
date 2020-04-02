package cn.carhouse.http;

import android.util.Log;

/**
 * HttpLog打印Log
 */
public class HttpLog {

    private static boolean isDebug = false;

    public static void e(String msg) {
        if (isDebug) {
            Log.e("HttpLog", msg);
        }
    }

    public static void setIsDebug(boolean isDebug) {
        HttpLog.isDebug = isDebug;
    }
}
