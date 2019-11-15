package cn.carhouse.http;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.Map;

import cn.carhouse.http.core.ICallback;
import cn.carhouse.http.core.IObjectCallback;
import cn.carhouse.http.core.IRequest;
import cn.carhouse.http.core.RequestFactory;
import cn.carhouse.http.core.RequestParams;
import cn.carhouse.http.core.RequestType;
import cn.carhouse.http.callback.DefaultCallback;


/**
 * 网络请求的工具类
 */

public class HttpUtils {
    /**
     * 默认使用OkHttp网络请求
     */
    private static IRequest mRequest = RequestFactory.defaultRequest();
    /**
     * 默认的回调
     */
    public static final ICallback mDefCallback = new DefaultCallback();

    private static Context mContext;
    /**
     * 请求参数
     */
    private RequestParams mParams;
    /**
     * 请求方式
     */
    private RequestType mRequestType = RequestType.POST;

    /**
     * 初始化
     */
    public static void init(Application application) {
        if (application != null) {
            mContext = application.getApplicationContext();
            application.registerActivityLifecycleCallbacks(new HttpLifecycleCallbacks());
        }
    }

    private HttpUtils() {
        mParams = new RequestParams();
        mParams.setContext(mContext);
    }

    public static HttpUtils with() {
        return new HttpUtils();
    }


    /**
     * 更换网络请求
     */
    public static void changeRequest(IRequest request) {
        mRequest = request;
    }

    /**
     * 设置URL
     */
    public HttpUtils url(String url) {
        this.mParams.setUrl(url);
        return this;
    }

    /**
     * 设置请求参数
     */
    public HttpUtils params(Map<String, Object> params) {
        if (params != null && !params.isEmpty()) {
            this.mParams.putAll(params);
        }
        return this;
    }

    /**
     * 设置请求参数
     */
    public HttpUtils params(String key, Object value) {
        if (key != null && value != null) {
            this.mParams.put(key, value);
        }
        return this;
    }

    /**
     * 设置请求头参数
     */
    public HttpUtils headParams(Map<String, String> params) {
        if (params != null && !params.isEmpty()) {
            this.mParams.putHeadAll(params);
        }

        return this;
    }

    /**
     * 设置请求头参数
     */
    public HttpUtils headParams(String key, String value) {
        if (key != null && value != null) {
            this.mParams.putHead(key, value);
        }
        return this;
    }

    /**
     * 是否缓存
     */
    public HttpUtils cache(boolean isCache) {
        this.mParams.setCache(isCache);
        return this;
    }

    public HttpUtils clazz(Class clazz) {
        this.mParams.setClazz(clazz);
        return this;
    }

    public HttpUtils objectCallback(IObjectCallback objectCallback) {
        this.mParams.setObjectCallback(objectCallback);
        return this;
    }

    public HttpUtils autoCancel(boolean isAutoCancle) {
        this.mParams.setAutoCancel(isAutoCancle);
        return this;
    }

    /**
     * 设置请求方式
     */
    public HttpUtils type(RequestType requestType) {
        this.mRequestType = requestType;
        return this;
    }

    /**
     * 文件下载目录名称
     */
    public HttpUtils fileDir(String fileDir) {
        this.mParams.setFileDir(fileDir);
        return this;
    }

    /**
     * 文件下载名称
     */
    public HttpUtils fileName(String fileName) {
        this.mParams.setFileName(fileName);
        return this;
    }

    public HttpUtils format(boolean format) {
        this.mParams.setFormat(format);
        return this;
    }

    /**
     * 执行请求
     */
    public void execute(ICallback callback) {
        if (mRequestType == null) {
            return;
        }
        if (callback == null) {
            callback = mDefCallback;
        }

        // 1. 初始化请求参数

        mParams.setCallback(callback);

        // 2. 请求前的一些通用参数的处理
        callback.onBefore(mParams, mRequestType);

        switch (mRequestType) {
            case POST:
                mRequest.post(mParams);
                break;
            case GET:
                mRequest.get(mParams);
                break;
            case PUT:
                mRequest.put(mParams);
                break;
            case DELETE:
                mRequest.delete(mParams);
                break;
            case UPLOAD:
                mRequest.upload(mParams);
                break;
            case DOWNLOAD:
                mRequest.download(mParams);
                break;
        }
    }


    /**
     * 执行请求
     */
    public void execute() {
        execute(mDefCallback);
    }

    /**
     * 不取消网络
     * 在Activity调用一下
     */
    public static void unCancel(Activity activity) {
        if (activity == null || activity.getWindow() == null || activity.getWindow().getDecorView() == null) {
            return;
        }
        activity.getWindow().getDecorView().setTag(R.id.net_cancel, "lib-http");
    }
}
