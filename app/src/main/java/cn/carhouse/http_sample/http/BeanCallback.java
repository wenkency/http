package cn.carhouse.http_sample.http;

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

    @Override
    public void onError(final Throwable e) {
        HandlerUtils.post(new Runnable() {
            @Override
            public void run() {
                onFailed(e);
                HandlerUtils.cancel(this);
            }
        });
    }

    @Override
    public void onSucceed(RequestParams params, final String result, boolean isSuccessful, int code) {
        HandlerUtils.post(new Runnable() {
            @Override
            public void run() {
                onSucceed(result);
                HandlerUtils.cancel(this);
            }
        });
    }

    public abstract void onSucceed(String result);

    public abstract void onFailed(Throwable e);
}
