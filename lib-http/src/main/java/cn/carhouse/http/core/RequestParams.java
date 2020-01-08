package cn.carhouse.http.core;

import android.content.Context;

import androidx.collection.ArrayMap;

import java.util.Map;

/**
 * 请求参数
 */
public class RequestParams {

    private Context context;
    /**
     * 请求的URL
     */
    private String url;
    // 请求的参数
    private Map<String, Object> params;
    // 请求的回调
    private ICallback callback;
    // Object回调
    private IObjectCallback objectCallback;
    // 是否缓存
    private boolean isCache;
    // 在Activity Pause的时候是否自动取消请求
    private boolean isAutoCancel;
    // 文件下载目录
    private String fileDir;
    // 文件下载名称
    private String fileName;
    // 请求头的参数
    private Map<String, String> headerParams;

    private boolean isFormat = true;

    private Object mObjParams;

    private Object mTag;

    /**
     * 这个是Object统一处理
     */
    private Class clazz;

    public RequestParams() {
        params = new ArrayMap<>(4);
        headerParams = new ArrayMap<>(2);
    }

    /**
     * 请求头
     *
     * @param headerParams
     */
    public void setHeaderParams(Map<String, String> headerParams) {
        this.headerParams = headerParams;
    }

    public Map<String, String> getHeaderParams() {
        return headerParams;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public ICallback getCallback() {
        return callback;
    }

    public void setCallback(ICallback callback) {
        this.callback = callback;
    }

    public boolean isCache() {
        return isCache;
    }

    public void setCache(boolean cache) {
        isCache = cache;
    }

    public boolean isAutoCancel() {
        return isAutoCancel;
    }

    public void setAutoCancel(boolean autoCancel) {
        isAutoCancel = autoCancel;
    }

    public String getFileDir() {
        return fileDir;
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Object getTag() {
        return mTag;
    }

    public void setTag(Object mTag) {
        this.mTag = mTag;
    }

    /**
     * 格式化参数
     *
     * @return
     */
    public boolean isFormat() {
        return isFormat;
    }

    public void setFormat(boolean format) {
        isFormat = format;
    }

    public void setObjParams(Object mObjParams) {
        this.mObjParams = mObjParams;
    }

    public Object getObjParams() {
        return mObjParams;
    }

    public void putAll(Map<String, Object> params) {
        this.params.putAll(params);
    }

    public void put(String key, Object value) {
        this.params.put(key, value);
    }

    public void putHeadAll(Map<String, String> params) {
        this.headerParams.putAll(params);
    }

    public void putHead(String key, String value) {
        this.headerParams.put(key, value);
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public void setObjectCallback(IObjectCallback objectCallback) {
        // 这里包装一下
        this.objectCallback = objectCallback;
    }

    public IObjectCallback getObjectCallback() {
        return objectCallback;
    }

    public Class getClazz() {
        return clazz;
    }
}
