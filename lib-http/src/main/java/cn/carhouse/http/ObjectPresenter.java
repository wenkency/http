package cn.carhouse.http;


import android.app.Activity;

import java.util.Map;

import cn.carhouse.http.core.IObjectCallback;
import cn.carhouse.http.core.RequestType;
import cn.carhouse.http.util.CallUtil;
import cn.carhouse.http.util.RequestUtil;
import cn.carhouse.http.util.TagUtils;


/**
 * OkHttp网络请求的业务类
 */

public class ObjectPresenter {

    private String mTag;

    public ObjectPresenter(Activity activity) {
        mTag = TagUtils.getTag(activity);
    }

    public ObjectPresenter() {
    }

    public static ObjectPresenter with() {
        return with(null);
    }

    public static ObjectPresenter with(Activity activity) {
        return new ObjectPresenter(activity);
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
     * 请求
     *
     * @param url 请求的URL
     */
    public <T> void request(RequestType type, String url, Map<String, Object> params, Class clazz, IObjectCallback callback) {
        HttpUtils.with()
                .url(url)
                .type(type)
                .params(params)
                .cache(false)
                .autoCancel(true)
                .format(false)
                .tag(mTag)
                .clazz(clazz)
                .objectCallback(callback)
                .execute();
    }


    public <T> void get(String url, Map<String, Object> params, Class clazz, IObjectCallback callback) {
        request(RequestType.GET, url, params, clazz, callback);
    }

    public <T> void get(String url, Object object, Class clazz, IObjectCallback callback) {
        get(url, RequestUtil.getObjParams(object), clazz, callback);
    }

    public <T> void get(String url, String key, String value, Class clazz, IObjectCallback callback) {
        get(url, RequestUtil.getObjParams(key, value), clazz, callback);
    }

    public <T> void get(String url, Class clazz, IObjectCallback callback) {
        get(url, null, clazz, callback);
    }

    public <T> void post(String url, Object object, Class clazz, IObjectCallback callback) {
        request(RequestType.POST, url, RequestUtil.getObjParams(object), clazz, callback);
    }

    public <T> void post(String url, String key, String value, Class clazz, IObjectCallback callback) {
        post(url, RequestUtil.getObjParams(key, value), clazz, callback);
    }

    public <T> void post(String url, Class clazz, IObjectCallback callback) {
        post(url, null, clazz, callback);
    }


    public <T> void put(String url, Object object, Class clazz, IObjectCallback callback) {
        request(RequestType.PUT, url, RequestUtil.getObjParams(object), clazz, callback);
    }

    public <T> void put(String url, String key, String value, Class clazz, IObjectCallback callback) {
        put(url, RequestUtil.getObjParams(key, value), clazz, callback);
    }

    public <T> void put(String url, Class clazz, IObjectCallback callback) {
        put(url, null, clazz, callback);
    }

    public <T> void delete(String url, Object object, Class clazz, IObjectCallback callback) {
        request(RequestType.DELETE, url, RequestUtil.getObjParams(object), clazz, callback);
    }

    public <T> void delete(String url, String key, String value, Class clazz, IObjectCallback callback) {
        delete(url, RequestUtil.getObjParams(key, value), clazz, callback);
    }

    public <T> void delete(String url, Class clazz, IObjectCallback callback) {
        delete(url, null, clazz, callback);
    }


    public <T> void form(String url, Map<String, Object> params, Class clazz, IObjectCallback callback) {
        request(RequestType.FORM, url, params, clazz, callback);
    }

    public <T> void form(String url, Object object, Class clazz, IObjectCallback callback) {
        form(url, RequestUtil.getObjParams(object), clazz, callback);
    }

    public <T> void form(String url, String key, String value, Class clazz, IObjectCallback callback) {
        form(url, RequestUtil.getObjParams(key, value), clazz, callback);
    }

    public <T> void form(String url, Class clazz, IObjectCallback callback) {
        form(url, null, clazz, callback);
    }

}
