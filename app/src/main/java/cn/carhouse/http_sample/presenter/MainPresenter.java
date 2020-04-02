package cn.carhouse.http_sample.presenter;

import android.app.Activity;

import cn.carhouse.http.ObjectPresenter;
import cn.carhouse.http.core.IObjectCallback;
import cn.carhouse.http_sample.http.ObjectCallback;

public class MainPresenter {

    public static void test(Activity activity, IObjectCallback callback) {
        String url = "https://www.baidu.com/";
        ObjectPresenter.with(activity).get(url, String.class, new ObjectCallback(callback));
    }

}
