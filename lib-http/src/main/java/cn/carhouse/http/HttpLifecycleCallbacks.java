package cn.carhouse.http;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;

import cn.carhouse.http.util.CallUtil;
import cn.carhouse.http.util.TagUtils;

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


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        // 根据TAG取消
        String tag = TagUtils.getTag(activity);
        if (!TextUtils.isEmpty(tag)) {
            CallUtil.getInstance().cancel(tag);
        }
    }


}
