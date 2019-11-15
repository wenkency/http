package cn.carhouse.http.core;


import cn.carhouse.http.impl.RequestOkHttp;

/**
 * 网络请求工厂
 */
public class RequestFactory {
    /**
     * 创建网络请求的工厂类
     */
    public static <R extends IRequest> R createRequest(Class<R> clz) {
        R request = null;
        try {
            request = clz.newInstance();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return request;
    }

    /**
     * 默认的网络请求类
     */
    public static IRequest defaultRequest() {
        return createRequest(RequestOkHttp.class);
    }
}
