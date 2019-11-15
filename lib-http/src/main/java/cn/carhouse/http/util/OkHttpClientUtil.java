package cn.carhouse.http.util;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

/**
 * OkHttpClient 创建的工具类
 */

public class OkHttpClientUtil {
    private static OkHttpClient mOkHttpClient;

    public static OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            mOkHttpClient = initClient();
        }
        return mOkHttpClient;
    }

    private static OkHttpClient initClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS);
        builder.followRedirects(true);
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        builder.sslSocketFactory(SSLUtils.initSSLSocketFactory(), SSLUtils.initTrustManager());
        return mOkHttpClient = builder.build();
    }
}
