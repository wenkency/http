package cn.carhouse.http.util;

import android.app.Activity;

public class TagUtils {
    public static String getTag(Activity activity) {
        String tag = null;
        if (activity != null) {
            tag = activity.getClass().getSimpleName() + activity.hashCode();
        }
        return tag;
    }
}
