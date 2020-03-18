package cn.carhouse.http.callback;

import android.text.TextUtils;

import java.io.IOException;

import cn.carhouse.http.HttpLog;
import cn.carhouse.http.cache.CacheUtil;
import cn.carhouse.http.core.ICallback;
import cn.carhouse.http.core.IObjectCallback;
import cn.carhouse.http.core.RequestParams;
import cn.carhouse.http.util.CallUtil;
import cn.carhouse.http.util.ParamsUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 包装OkHttp原来的回调
 */
public class WarpCallback implements Callback {

    private RequestParams mParams;

    public WarpCallback(RequestParams params) {
        this.mParams = params;
        // 先检查缓存
        checkCache(params);
    }

    /**
     * 检查缓存
     */
    private void checkCache(RequestParams params) {
        if (params.isCache()) {
            final String cacheJson = CacheUtil.getJson(params.getContext(), params.getUrl());
            if (!TextUtils.isEmpty(cacheJson)) {
                // 添加ObjectPresent处理
                if (handleResult(cacheJson)) {
                    return;
                }
                ICallback callback = params.getCallback();
                if (callback != null) {
                    callback.onSucceed(mParams, cacheJson, true, 200);
                    callback.onAfter();
                }
            }
        }
    }


    @Override
    public void onFailure(Call call, final IOException e) {
        // 将Call移除
        CallUtil.getInstance().remove(call);

        // 添加ObjectPresent处理
        if (handleError(e)) {
            return;
        }

        ICallback callback = mParams.getCallback();
        if (callback != null) {
            callback.onError(e);
            callback.onAfter();
        }
    }


    @Override
    public void onResponse(Call call, Response response) throws IOException {
        // 将Call移除
        CallUtil.getInstance().remove(call);

        String result = response.body().string();
        // 格式化
        result = ParamsUtil.formParams(result);

        HttpLog.e("response-->" + result);

        // 添加ObjectPresent处理
        if (handleResult(result)) {
            return;
        }

        ICallback callback = mParams.getCallback();
        if (callback != null) {
            callback.onSucceed(mParams, result, response.isSuccessful(), response.code());
            callback.onAfter();
        }
        // 缓存数据
        if (mParams.isCache()) {
            CacheUtil.putJson(mParams.getContext(), mParams.getUrl(), result);
        }

    }

    /**
     * 单独处理ObjectPresent回调
     */
    private boolean handleError(final IOException e) {
        final IObjectCallback objectCallback = mParams.getObjectCallback();
        Class clazz = mParams.getClazz();
        if (objectCallback != null && clazz != null) {
            objectCallback.onError(e);
            return true;
        }
        return false;
    }

    /**
     * 单独处理ObjectPresent回调
     */
    private boolean handleResult(String json) {
        // 是不是要统一处理
        final IObjectCallback objectCallback = mParams.getObjectCallback();
        Class clazz = mParams.getClazz();
        if (objectCallback != null && clazz != null) {
            // 直接返回结果
            objectCallback.onSuccess(json, null,clazz);
            return true;
        }
        return false;
    }

}
