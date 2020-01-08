package cn.carhouse.http_sample.presenter;

import android.app.Activity;
import cn.carhouse.http.ObjectPresenter;
import cn.carhouse.http.core.IObjectCallback;
import cn.carhouse.http_sample.http.ObjectCallback;

public class MainPresenter extends ObjectPresenter {
    public MainPresenter(Activity activity) {
        super(activity);
    }

    public void test(IObjectCallback callback) {
        String url = "https://www.baidu.com/";
        get(url, String.class, new ObjectCallback(callback));
    }

}
