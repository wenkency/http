package cn.carhouse.http;


import java.util.Map;

import cn.carhouse.http.core.IObjectCallback;
import cn.carhouse.http.core.RequestType;
import cn.carhouse.http.util.RequestUtil;


/**
 * OkHttp网络请求的业务类
 */

public class ObjectPresenter {


    /**
     * get请求
     *
     * @param url 请求的URL
     */
    public static <T> void request(RequestType type, String url, Map<String, Object> params, Class clazz, IObjectCallback callback) {
        HttpUtils.with()
                .url(url)
                .type(type)
                .params(params)
                .cache(false)
                .autoCancel(true)
                .clazz(clazz)
                .objectCallback(callback)
                .execute();
    }


    public static <T> void get(String url, Object object, Class clazz, IObjectCallback callback) {
        request(RequestType.GET, url, RequestUtil.getObjParams(object), clazz, callback);
    }

    public static <T> void get(String url, String key, String value, Class clazz, IObjectCallback callback) {
        request(RequestType.GET, url, RequestUtil.getObjParams(key, value), clazz, callback);
    }

    public static <T> void get(String url, Class clazz, IObjectCallback callback) {
        request(RequestType.GET, url, null, clazz, callback);
    }

    public static <T> void post(String url, Object object, Class clazz, IObjectCallback callback) {
        request(RequestType.POST, url, RequestUtil.getObjParams(object), clazz, callback);
    }

    public static <T> void post(String url, String key, String value, Class clazz, IObjectCallback callback) {
        request(RequestType.POST, url, RequestUtil.getObjParams(key, value), clazz, callback);
    }

    public static <T> void post(String url, Class clazz, IObjectCallback callback) {
        request(RequestType.POST, url, null, clazz, callback);
    }


    public static <T> void put(String url, Object object, Class clazz, IObjectCallback callback) {
        request(RequestType.PUT, url, RequestUtil.getObjParams(object), clazz, callback);
    }

    public static <T> void put(String url, String key, String value, Class clazz, IObjectCallback callback) {
        request(RequestType.PUT, url, RequestUtil.getObjParams(key, value), clazz, callback);
    }

    public static <T> void put(String url, Class clazz, IObjectCallback callback) {
        request(RequestType.PUT, url, null, clazz, callback);
    }

    public static <T> void delete(String url, Object object, Class clazz, IObjectCallback callback) {
        request(RequestType.DELETE, url, RequestUtil.getObjParams(object), clazz, callback);
    }

    public static <T> void delete(String url, String key, String value, Class clazz, IObjectCallback callback) {
        request(RequestType.DELETE, url, RequestUtil.getObjParams(key, value), clazz, callback);
    }

    public static <T> void delete(String url, Class clazz, IObjectCallback callback) {
        request(RequestType.DELETE, url, null, clazz, callback);
    }

}
