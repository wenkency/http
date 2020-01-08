package cn.carhouse.http_sample.presenter;

import android.app.Activity;

import cn.carhouse.http.OkHttpPresenter;
import cn.carhouse.http.callback.StringCallback;

public class MainOKPresenter extends OkHttpPresenter {
    public MainOKPresenter(Activity activity) {
        super(activity);
    }

    public void test(StringCallback<String> callback) {
        String url = "https://www.baidu.com/";
        get(url, callback);
    }
}
