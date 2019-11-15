package cn.carhouse.http.core;

import java.io.File;
import java.util.Map;

/**
 * 这个是网络引擎的回调
 */

public interface ICallback {
    /**
     * 请求前
     */
    void onBefore(RequestParams params, RequestType type);

    /**
     * 错误的回调
     */
    void onError(Throwable e);

    /**
     * 成功的回调
     */
    void onSucceed(RequestParams params, String result, boolean isSuccessful, int code);

    /**
     * 请求后统一调用
     */
    void onAfter();


    /**
     * 文件下载的进度
     */
    void onProgress(float progress, float current, float total);

    /**
     * 文件下载成功
     */
    void onSucceed(File file);
}
