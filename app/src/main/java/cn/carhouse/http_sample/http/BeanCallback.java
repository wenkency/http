package cn.carhouse.http_sample.http;

import android.os.Handler;
import android.os.Looper;

import cn.carhouse.http.callback.StringCallback;
import cn.carhouse.http.core.RequestParams;

/**
 * ================================================================
 * 版权: 爱车小屋所有（C） 2019
 * <p>
 * 作者：刘付文 （61128910@qq.com）
 * <p>
 * 时间: 2019-11-15 17:38
 * <p>
 * 描述：自已根据项目需求两次封装
 * ================================================================
 */
public abstract class BeanCallback<T> extends StringCallback<T> {
    private static Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onError(final Throwable e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onFailed(e);
            }
        });
    }

    @Override
    public void onSucceed(RequestParams params, final String result, boolean isSuccessful, int code) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onSucceed(result);
            }
        });
    }

    public abstract void onSucceed(String result);

    public abstract void onFailed(Throwable e);
}
