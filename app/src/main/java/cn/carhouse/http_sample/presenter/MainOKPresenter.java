package cn.carhouse.http_sample.presenter;

import android.app.Activity;

import cn.carhouse.http.OkHttpPresenter;
import cn.carhouse.http.callback.StringCallback;
import cn.carhouse.http_sample.http.bean.SendCoreBean;

public class MainOKPresenter  {

    public static void test(Activity activity,StringCallback<String> callback) {
        String url = "https://www.baidu.com/";
        OkHttpPresenter.with(activity).get(url,callback);
    }

}
