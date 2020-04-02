package cn.carhouse.http.core;

/**
 * 请求接口
 */
public interface IRequest {
    /**
     * get请求
     */
    void get(RequestParams params);

    /**
     * post请求
     */
    void post(RequestParams params);

    /**
     * form请求
     */
    void form(RequestParams params);

    /**
     * put请求
     */
    void put(RequestParams params);

    /**
     * delete请求
     */
    void delete(RequestParams params);

    /**
     * 上传文件
     */
    void upload(RequestParams params);

    /**
     * 文件下载
     */
    void download(RequestParams params);


}
