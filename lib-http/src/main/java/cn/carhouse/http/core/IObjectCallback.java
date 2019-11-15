package cn.carhouse.http.core;

/**
 * ================================================================
 * 版权: 爱车小屋所有（C） 2019
 * <p>
 * 作者：刘付文 （61128910@qq.com）
 * <p>
 * 时间: 2019-11-15 13:22
 * <p>
 * 描述：统一回调
 * ================================================================
 */
public interface IObjectCallback {
    /**
     * 请求成功回调事件处理
     */
    void onSuccess(Object object);

    /**
     * 请求失败回调事件处理
     */
    void onError(Exception e);

}  
