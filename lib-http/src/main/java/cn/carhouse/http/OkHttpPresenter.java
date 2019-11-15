package cn.carhouse.http;



import java.util.Map;

import cn.carhouse.http.core.RequestType;
import cn.carhouse.http.callback.FileCallback;
import cn.carhouse.http.callback.StringCallback;
import cn.carhouse.http.util.RequestUtil;


/**
 * OkHttp网络请求的业务类
 */

public class OkHttpPresenter {


    /**
     * get请求
     *
     * @param url      请求的URL
     * @param params   请求的参数
     * @param callback 回调
     * @param isCache  是否要缓存
     * @param <T>
     */
    public static <T> void get(String url, Map<String, Object> params, StringCallback<T> callback, boolean isCache, boolean isAutoCancel) {
        HttpUtils.with()
                .url(url)
                .type(RequestType.GET)
                .params(params)
                .cache(isCache)
                .autoCancel(isAutoCancel)
                .execute(callback);
    }

    public static <T> void get(String url, Map<String, Object> params, StringCallback<T> callback) {
        get(url, params, callback, false, true);
    }

    public static <T> void get(String url, Object object, StringCallback<T> callback) {
        get(url, RequestUtil.getObjParams(object), callback, false, true);
    }

    public static <T> void get(String url, String key, String value, StringCallback<T> callback) {
        get(url, RequestUtil.getObjParams(key, value), callback, false, true);
    }

    public static <T> void get(String url, StringCallback<T> callback) {
        get(url, null, callback);
    }

    /**
     * post请求
     *
     * @param url      请求的URL
     * @param data     请求的参数
     * @param callback 回调
     * @param isCache  是否要缓存
     * @param <T>
     */
    public static <T> void post(String url, Object data, StringCallback<T> callback, boolean isCache, boolean isAutoCancel, boolean isFormat) {
        post(url, RequestUtil.getObjParams(data), callback, isCache, isAutoCancel, isFormat);
    }

    public static <T> void post(String url, Map<String, Object> params, StringCallback<T> callback, boolean isCache, boolean isAutoCancel, boolean format) {
        HttpUtils.with().url(url)
                .params(params)
                .cache(isCache)
                .autoCancel(isAutoCancel)
                .format(format)
                .execute(callback);
    }

    /**
     * Post请求
     */
    public static <T> void post(String url, Object data, StringCallback<T> callback) {
        post(url, data, callback, true);
    }

    public static <T> void post(String url, Object data, StringCallback<T> callback, boolean isFormat) {
        post(url, data, callback, false, true, isFormat);
    }

    public static <T> void post(String url, String key, String value, StringCallback<T> callback) {
        post(url, RequestUtil.getObjParams(key, value), callback, false, true, true);
    }

    /**
     * Post请求
     */
    public static <T> void post(String url, StringCallback<T> callback) {
        post(url, new Object(), callback, false, true, true);
    }


    /**
     * put请求
     *
     * @param url      请求的URL
     * @param data     请求的参数
     * @param callback 回调
     * @param isCache  是否要缓存
     * @param <T>
     */
    public static <T> void put(String url, Object data, StringCallback<T> callback, boolean isCache, boolean isAutoCancel) {
        put(url, RequestUtil.getObjParams(data), callback, isCache, isAutoCancel);
    }

    public static <T> void put(String url, Map<String, Object> params, StringCallback<T> callback, boolean isCache, boolean isAutoCancel) {
        HttpUtils.with().url(url)
                .params(params)
                .type(RequestType.PUT)
                .cache(isCache)
                .autoCancel(isAutoCancel)
                .execute(callback);
    }

    /**
     * put请求
     */
    public static <T> void put(String url, Object data, StringCallback<T> callback) {
        put(url, data, callback, false, true);
    }

    public static <T> void put(String url, String key, String value, StringCallback<T> callback) {
        put(url, RequestUtil.getObjParams(key, value), callback, false, true);
    }

    /**
     * put请求
     */
    public static <T> void put(String url, StringCallback<T> callback) {
        put(url, new Object(), callback, false, true);
    }

    /**
     * delete请求
     *
     * @param url      请求的URL
     * @param data     请求的参数
     * @param callback 回调
     * @param isCache  是否要缓存
     * @param <T>
     */
    public static <T> void delete(String url, Object data, StringCallback<T> callback, boolean isCache, boolean isAutoCancel) {
        delete(url, RequestUtil.getObjParams(data), callback, isCache, isAutoCancel);
    }

    public static <T> void delete(String url, Map<String, Object> params, StringCallback<T> callback, boolean isCache, boolean isAutoCancel) {
        HttpUtils.with().url(url)
                .params(params)
                .type(RequestType.DELETE)
                .cache(isCache)
                .autoCancel(isAutoCancel)
                .execute(callback);
    }

    /**
     * delete请求
     */
    public static <T> void delete(String url, Object data, StringCallback<T> callback) {
        delete(url, data, callback, false, true);
    }

    public static <T> void delete(String url, String key, String value, StringCallback<T> callback) {
        delete(url, RequestUtil.getObjParams(key, value), callback, false, true);
    }

    /**
     * put请求
     */
    public static <T> void delete(String url, StringCallback<T> callback) {
        delete(url, new Object(), callback, false, true);
    }

    /**
     * 文件上传
     *
     * @param url      上传的URL
     * @param params   上传的参数
     * @param callback 回调
     */
    public static <T> void upload(String url, Map<String, Object> params, StringCallback<T> callback) {
        HttpUtils.with().url(url)
                .type(RequestType.UPLOAD)
                .params(params)
                .cache(false)
                .autoCancel(true)
                .execute(callback);
    }

    /**
     * 文件下载
     *
     * @param url      下载URL
     * @param fileDir  文件目录名称
     * @param fileName 文件名称
     * @param callback 回调
     */
    public static void download(String url, String fileDir, String fileName, FileCallback callback) {
        HttpUtils.with().url(url)
                .type(RequestType.DOWNLOAD)
                .cache(false)
                .autoCancel(false)
                .fileDir(fileDir)
                .fileName(fileName)
                .execute(callback);
    }

}
