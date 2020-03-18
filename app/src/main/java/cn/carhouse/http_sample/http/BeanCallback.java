package cn.carhouse.http_sample.http;

import android.text.TextUtils;

import java.lang.reflect.Type;
import java.util.Map;

import cn.carhouse.http.callback.StringCallback;
import cn.carhouse.http.core.RequestParams;
import cn.carhouse.http.core.RequestType;
import cn.carhouse.http.util.GsonUtil;
import cn.carhouse.http_sample.http.bean.BaseCore;

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
        Map<String, String> headerParams = params.getHeaderParams();
        // 获取token
        String token = null;
        // 获取到就加
        if (!TextUtils.isEmpty(token)) {
            // 1. 在这里统一加请求头就好
            headerParams.put("token", "你缓存的token");
        }
        // 2. 返回
    }

    @Override
    public void onError(final Throwable e) {
        HandlerUtils.post(new Runnable() {
            @Override
            public void run() {
                onFailed(e);
                onAfter();
                HandlerUtils.cancel(this);
            }
        });
    }

    @Override
    public void onSucceed(RequestParams params, final String result, boolean isSuccessful, int code) {

        // 统一解析数据，后台返回如下：
//        {
//            "code": 200,
//                "msg": "成功",
//                "data": {
//                    "uuid": "4a295aee95504855b2b4b4082dd76f18",
//                    "expiredAt": 600
//                }
//        }
        // 获取泛型
        Type actualType = ParameterTypeUtils.parameterType(this);
        // 创建BaseCore解析类型
        ParameterizedTypeImpl parseType = new ParameterizedTypeImpl(BaseCore.class, new Type[]{actualType});
        final BaseCore baseCore = GsonUtil.getGson().fromJson(result, parseType);
        // 1. 读取缓存
        // 2. 比较缓存，相同不处理
        // 3. 不相同，返回刷新
        HandlerUtils.post(new Runnable() {
            @Override
            public void run() {
                if (baseCore.isSucceed()) {
                    onSucceed((T) baseCore.getData());
                } else {
                    onError(new RuntimeException(baseCore.getMsg()));
                    return;
                }
                onAfter();
                HandlerUtils.cancel(this);
            }
        });
    }

    public abstract void onSucceed(T data);

    public abstract void onFailed(Throwable e);
}
