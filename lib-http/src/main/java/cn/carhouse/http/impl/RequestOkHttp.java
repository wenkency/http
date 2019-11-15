package cn.carhouse.http.impl;


import java.util.Map;

import cn.carhouse.http.HttpLog;
import cn.carhouse.http.callback.OkFileCallback;
import cn.carhouse.http.callback.WarpCallback;
import cn.carhouse.http.core.IRequest;
import cn.carhouse.http.core.RequestParams;
import cn.carhouse.http.core.RequestType;
import cn.carhouse.http.util.CallUtil;
import cn.carhouse.http.util.GsonUtil;
import cn.carhouse.http.util.OkHttpClientUtil;
import cn.carhouse.http.util.ParamsUtil;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Okhttp实现网络请求
 */
public class RequestOkHttp implements IRequest {
    private static OkHttpClient mOkHttpClient = OkHttpClientUtil.getOkHttpClient();
    // Json 类型
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");


    @Override
    public void get(RequestParams requestParams) {
        getOrDelete(requestParams, RequestType.GET);
    }

    @Override
    public void delete(RequestParams requestParams) {
        getOrDelete(requestParams, RequestType.DELETE);
    }

    @Override
    public void post(RequestParams requestParams) {
        postOrPut(requestParams, RequestType.POST);
    }

    @Override
    public void put(RequestParams requestParams) {
        postOrPut(requestParams, RequestType.PUT);
    }


    private void getOrDelete(RequestParams requestParams, RequestType requestType) {
        if (requestParams == null) {
            return;
        }
        Map<String, Object> params = requestParams.getParams();
        // 拼接请求链接
        String url = requestParams.getUrl();
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        if (params != null) {
            ParamsUtil.appendParams(builder, params);
        }
        HttpUrl httpUrl = builder.build();

        HttpLog.e("httpUrl-->" + httpUrl.toString());
        HttpLog.e("params-->" + ParamsUtil.formParams(GsonUtil.getGson().toJson(params)));

        //创建一个Request
        Request.Builder build = new Request.Builder().url(httpUrl);
        if (requestType == RequestType.GET) {
            build.get();
        } else if (requestType == RequestType.DELETE) {
            build.delete();
        }

        // 添加请求头
        if (requestParams.getHeaderParams() != null) {
            ParamsUtil.appendHeadParams(build, requestParams.getHeaderParams());
        }
        // 是不是要自动取消
        if (requestParams.isAutoCancel()) {
            build.tag(CallUtil.getInstance().getTag());
        }
        final Request request = build.build();
        // 创建一个回调
        Call call = mOkHttpClient.newCall(request);
        // 将请求缓存起来
        CallUtil.getInstance().add(call);
        call.enqueue(new WarpCallback(requestParams));
    }

    private void postOrPut(RequestParams requestParams, RequestType requestType) {
        if (requestParams == null) {
            return;
        }
        Map<String, Object> params = requestParams.getParams();
        // 请求体
        String content = GsonUtil.getGson().toJson(params);
        if (requestParams.isFormat()) {
            content = ParamsUtil.formParams(content);
        }
        HttpLog.e("httpUrl-->" + requestParams.getUrl());
        HttpLog.e("params-->" + ParamsUtil.formParams(content));


        RequestBody body = RequestBody.create(JSON, content);
        Request.Builder build = new Request.Builder()
                .url(requestParams.getUrl());
        if (requestType == RequestType.POST) {
            build.post(body);
        } else if (requestType == RequestType.PUT) {
            build.put(body);
        }
        // 添加请求头
        if (requestParams.getHeaderParams() != null) {
            ParamsUtil.appendHeadParams(build, requestParams.getHeaderParams());
        }
        // 是不是要自动取消
        if (requestParams.isAutoCancel()) {
            build.tag(CallUtil.getInstance().getTag());
        }
        final Request request = build.build();
        // 创建一个回调
        Call call = mOkHttpClient.newCall(request);
        // 将请求缓存起来
        CallUtil.getInstance().add(call);
        call.enqueue(new WarpCallback(requestParams));
    }

    @Override
    public void upload(RequestParams requestParams) {
        if (requestParams == null) {
            return;
        }
        Map<String, Object> params = requestParams.getParams();
        // 请求体
        RequestBody body = ParamsUtil.appendBody(params);
        // 代理类去写
        ExMultipartBody exMultipartBody = new ExMultipartBody(body);
        // 设置上传进度回调监听
        exMultipartBody.setCallback(requestParams.getCallback());
        Request.Builder build = new Request.Builder()
                .url(requestParams.getUrl())
                .post(exMultipartBody);
        // 是不是要自动取消
        if (requestParams.isAutoCancel()) {
            build.tag(CallUtil.getInstance().getTag());
        }
        final Request request = build.build();

        // 创建一个回调
        Call call = mOkHttpClient.newCall(request);
        // 将请求缓存起来
        CallUtil.getInstance().add(call);
        call.enqueue(new WarpCallback(requestParams));
    }

    @Override
    public void download(RequestParams requestParams) {
        //创建一个Request
        Request.Builder build = new Request.Builder()
                .url(requestParams.getUrl());
        // 是不是要自动取消
        if (requestParams.isAutoCancel()) {
            build.tag(CallUtil.getInstance().getTag());
        }
        final Request request = build.build();
        // 创建一个回调
        Call call = mOkHttpClient.newCall(request);
        // 将请求缓存起来
        CallUtil.getInstance().add(call);
        //请求加入调度
        call.enqueue(new OkFileCallback(requestParams));
    }

}
