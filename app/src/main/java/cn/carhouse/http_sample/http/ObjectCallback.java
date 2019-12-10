package cn.carhouse.http_sample.http;


import cn.carhouse.http.core.IObjectCallback;

public class ObjectCallback implements IObjectCallback {
    private IObjectCallback callback;

    public ObjectCallback(IObjectCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onSuccess(final Object object, final Class clazz) {
        HandlerUtils.post(new Runnable() {
            @Override
            public void run() {
                // TODO 真正处理逻辑的地方
                callback.onSuccess(object, clazz);
                HandlerUtils.cancel(this);
            }
        });

    }

    @Override
    public void onError(final Exception e) {
        HandlerUtils.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(e);
                HandlerUtils.cancel(this);
            }
        });
    }
}
