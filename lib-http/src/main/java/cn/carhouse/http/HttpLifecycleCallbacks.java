package cn.carhouse.http;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;

import cn.carhouse.http.util.CallUtil;

/**
 * ================================================================
 * 版权: 爱车小屋所有（C） 2019
 * <p>
 * 作者：刘付文 （61128910@qq.com）
 * <p>
 * 时间: 2019-11-15 13:30
 * <p>
 * 描述：
 * ================================================================
 */
public class HttpLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    private String currentKey;
    private String lastKey;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        currentKey = activity.hashCode() + "http_cancel";
        CallUtil.getInstance().setTag(currentKey);
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
        currentKey = activity.hashCode() + "http_cancel";
        // 打TAG
        CallUtil.getInstance().setTag(currentKey);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        lastKey = activity.hashCode() + "http_cancel";
    }

    @Override
    public void onActivityStopped(Activity activity) {
        if (!TextUtils.isEmpty(lastKey)) {
            // 这个要取消掉
            if (checkNet(activity)) {
                CallUtil.getInstance().cancel(lastKey);
            }
        }

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    public static boolean checkNet(Activity activity) {
        if (activity == null || activity.getWindow() == null || activity.getWindow().getDecorView() == null) {
            return true;
        }
        return activity.getWindow().getDecorView().getTag(R.id.net_cancel) == null;
    }
}
