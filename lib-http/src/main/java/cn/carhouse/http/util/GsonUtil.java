package cn.carhouse.http.util;

import com.google.gson.Gson;

/**
 * Gson创建的工具类
 */

public class GsonUtil {
    private static Gson mGson;

    public static Gson getGson() {
        if (mGson == null) {
            mGson = new Gson();
        }
        return mGson;
    }
}
