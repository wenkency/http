package cn.carhouse.http.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;


/**
 * 根据URL缓存网络的数据
 */

public class CacheUtil {
    private static String CONFIG = "CacheUtil";


    /**
     * 从数据库获取Json
     */
    public static String getJson(Context context, String url) {
        if (context == null || TextUtils.isEmpty(url)) {
            return "";
        }
        // 2. 从SD卡拿
        String key = MD5.getHexMD5(url);
        SharedPreferences sp = getSharedPreferences(context);
        String cacheJson = sp.getString(key, "");
        return cacheJson;
    }

    public static void putJson(Context context, final String url, final String json) {
        if (context == null || TextUtils.isEmpty(url)) {
            return;
        }
        String key = MD5.getHexMD5(url);
        SharedPreferences sp = getSharedPreferences(context);
        sp.edit().putString(key, json).commit();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
    }

}