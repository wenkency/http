package cn.carhouse.http;


import android.app.Activity;

import androidx.annotation.Nullable;

import java.util.Map;

import cn.carhouse.http.callback.FileCallback;
import cn.carhouse.http.callback.StringCallback;
import cn.carhouse.http.core.RequestType;
import cn.carhouse.http.util.CallUtil;
import cn.carhouse.http.util.RequestUtil;
import cn.carhouse.http.util.TagUtils;


/**
 * OkHttp网络请求的业务类
 */

public class OkHttpPresenter {

    private String mTag;


    public OkHttpPresenter(Activity activity) {
        mTag = TagUtils.getTag(activity);
    }

    public OkHttpPresenter() {
    }

    public static OkHttpPresenter with(Activity activity) {
        return new OkHttpPresenter(activity);
    }

    public static OkHttpPresenter with() {
        return with(null);
    }

    /**
     * 自己设置TAG
     */
    public void setTag(String tag) {
        this.mTag = tag;
    }

    /**
     * 自己取消TAG
     */
    public void cancel(String tag) {
        CallUtil.getInstance().cancel(tag);
    }

    /**
     * 网络请求
     */
    public <T> void request(RequestType type, String url, Map<String, Object> params,
                            StringCallback<T> callback, boolean isCache, boolean isAutoCancel, boolean isFormat) {
        HttpUtils.with()
                .url(url)
                .type(type)
                .params(params)
                .cache(isCache)
                .autoCancel(isAutoCancel)
                .tag(mTag)
                .format(isFormat)
                .execute(callback);
    }


    // ========================= get ===============================================

    public <T> void get(String url, Map<String, Object> params, StringCallback<T> callback) {
        request(RequestType.GET, url, params, callback, false, true, false);
    }

    public <T> void get(String url, @Nullable Object object, StringCallback<T> callback) {
        get(url, RequestUtil.getObjParams(object), callback);
    }

    public <T> void get(String url, String key, String value, StringCallback<T> callback) {
        get(url, RequestUtil.getObjParams(key, value), callback);
    }

    public <T> void get(String url, StringCallback<T> callback) {
        get(url, null, callback);
    }
    // ========================= post ===============================================

    public <T> void post(String url, Map<String, Object> params, StringCallback<T> callback, boolean isFormat) {
        request(RequestType.POST, url, params, callback, false, true, isFormat);
    }

    public <T> void post(String url, Map<String, Object> params, StringCallback<T> callback) {
        post(url, params, callback, false);
    }

    public <T> void post(String url, @Nullable Object object, StringCallback<T> callback) {
        post(url, RequestUtil.getObjParams(object), callback);
    }

    public <T> void post(String url, String key, String value, StringCallback<T> callback) {
        post(url, RequestUtil.getObjParams(key, value), callback);
    }

    public <T> void post(String url, StringCallback<T> callback) {
        post(url, null, callback);
    }
    // ========================= delete ===============================================

    public <T> void delete(String url, Map<String, Object> params, StringCallback<T> callback) {
        request(RequestType.DELETE, url, params, callback, false, true, false);
    }

    public <T> void delete(String url, @Nullable Object object, StringCallback<T> callback) {
        delete(url, RequestUtil.getObjParams(object), callback);
    }

    public <T> void delete(String url, String key, String value, StringCallback<T> callback) {
        delete(url, RequestUtil.getObjParams(key, value), callback);
    }

    public <T> void delete(String url, StringCallback<T> callback) {
        delete(url, null, callback);
    }

    // ========================= put ===============================================

    public <T> void put(String url, Map<String, Object> params, StringCallback<T> callback) {
        request(RequestType.PUT, url, params, callback, false, true, false);
    }

    public <T> void put(String url, @Nullable Object object, StringCallback<T> callback) {
        put(url, RequestUtil.getObjParams(object), callback);
    }

    public <T> void put(String url, String key, String value, StringCallback<T> callback) {
        put(url, RequestUtil.getObjParams(key, value), callback);
    }

    public <T> void put(String url, StringCallback<T> callback) {
        put(url, null, callback);
    }

    // ========================= form ===============================================

    public <T> void form(String url, Map<String, Object> params, StringCallback<T> callback) {
        request(RequestType.FORM, url, params, callback, false, true, false);
    }

    public <T> void form(String url, @Nullable Object object, StringCallback<T> callback) {
        form(url, RequestUtil.getObjParams(object), callback);
    }

    public <T> void form(String url, String key, String value, StringCallback<T> callback) {
        put(url, RequestUtil.getObjParams(key, value), callback);
    }

    public <T> void form(String url, StringCallback<T> callback) {
        put(url, null, callback);
    }

    /**
     * 文件上传
     *
     * @param url      上传的URL
     * @param params   上传的参数
     * @param callback 回调
     */
    public <T> void upload(String url, Map<String, Object> params, StringCallback<T> callback) {
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
    public void download(String url, String fileDir, String fileName, FileCallback callback) {
        HttpUtils.with().url(url)
                .type(RequestType.DOWNLOAD)
                .cache(false)
                .autoCancel(false)
                .fileDir(fileDir)
                .fileName(fileName)
                .execute(callback);
    }
}
