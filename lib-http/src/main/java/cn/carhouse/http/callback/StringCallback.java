package cn.carhouse.http.callback;


import cn.carhouse.http.core.RequestParams;

public abstract class StringCallback<T> extends DefaultCallback {

    @Override
    public abstract void onError(Throwable e);

    // 子线程方法 更新UI请自主转主线程
    @Override
    public abstract void onSucceed(RequestParams params, String result, boolean isSuccessful, int code);

}
