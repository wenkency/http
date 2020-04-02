package cn.carhouse.http.util;

import android.app.Activity;

/**
 * 请求打TAG，根据TAG取消网络
 */
public class TagUtils {
    public static String getTag(Activity activity) {
        String tag = null;
        if (activity != null) {
            tag = activity.getClass().getSimpleName() + activity.hashCode();
        }
        return tag;
    }
}
