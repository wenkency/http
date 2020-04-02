package cn.carhouse.http_sample;

import android.app.Application;

import cn.carhouse.http.HttpLog;
import cn.carhouse.http.HttpUtils;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Activity销毁自动取消，要初始化一下，不用就不用管
        HttpUtils.init(this);
        HttpLog.setIsDebug(true);
    }
}
