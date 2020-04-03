package cn.carhouse.http_sample.http;

import java.lang.reflect.Type;

import cn.carhouse.http.callback.StringCallback;
import cn.carhouse.http.core.RequestParams;
import cn.carhouse.http.core.RequestType;
import cn.carhouse.http.parse.ParameterTypeUtils;
import cn.carhouse.http.util.GsonUtil;

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
    public void onBefore(RequestParams params, RequestType type) {
    }

    @Override
    public void onError(final Throwable e) {
        HttpHandler.post(new Runnable() {
            @Override
            public void run() {
                onFailed(e);
                onAfter();
            }
        });
    }

    @Override
    public void onSucceed(RequestParams params, final String result, boolean isSuccessful, int code) {
        // 1. 获取泛型
        Type actualType = ParameterTypeUtils.parameterType(this);
        // 2. 如果是字符串就不处理
        final T data;
        if (!ParameterTypeUtils.isString(actualType)) {
            data = GsonUtil.getGson().fromJson(result, actualType);
        } else {
            data = null;
        }
        // 3. 不相同，返回刷新
        HttpHandler.post(new Runnable() {
            @Override
            public void run() {
                if (data != null) {
                    onSucceed(data);
                } else {
                    onSucceed((T) result);
                }
                onAfter();
            }
        });
    }

    public abstract void onSucceed(T data);

    public abstract void onFailed(Throwable e);
}
