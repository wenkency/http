package cn.carhouse.http_sample.http;


import cn.carhouse.http.core.IObjectCallback;
import cn.carhouse.http.util.GsonUtil;

public class ObjectCallback implements IObjectCallback {
    private IObjectCallback callback;

    public ObjectCallback(IObjectCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onSuccess(final String json, final Object object, final Class clazz) {
        HandlerUtils.post(new Runnable() {
            @Override
            public void run() {
                // 如果是字符串类型
                if (clazz == String.class) {
                    callback.onSuccess(json, json, clazz);
                } else {
                    Object data = GsonUtil.getGson().fromJson(json, clazz);
                    callback.onSuccess(json, data, clazz);
                }

            }
        });

    }

    @Override
    public void onError(final Exception e) {
        HandlerUtils.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(e);
            }
        });
    }
}
