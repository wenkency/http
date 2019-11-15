package cn.carhouse.http;

import android.util.Log;

/**
 * HttpLog打印Log
 */
public class HttpLog {

    public static void e(String msg) {
        if (BuildConfig.DEBUG) {
            Log.e("HttpLog", msg);
        }
    }
}
