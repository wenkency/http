package cn.carhouse.http.util;

import android.text.TextUtils;

import java.io.File;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import cn.carhouse.http.HttpLog;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 处理请求参数的工具类
 */

public class ParamsUtil {
    public static String formParams(String params) {
        if (TextUtils.isEmpty(params)) {
            return params;
        }
        return params.replace("\\\"", "\"")
                .replace("\"{", "{")
                .replace("}\"", "}")
                .replace("\"[", "[")
                .replace("]\"", "]");
    }

    /**
     * 参数拼接
     */
    public static void appendParams(FormBody.Builder builder, Map<String, Object> params) {
        if (builder == null || params == null || params.isEmpty()) {
            return;
        }
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                Object obj = params.get(key);
                if (obj != null) {
                    String value = obj.toString();
                    if (!TextUtils.isEmpty(value)) {
                        builder.add(key, value);
                    }
                }
            }
        }
    }

    /**
     * 参数拼接
     */
    public static void appendParams(HttpUrl.Builder builder, Map<String, Object> params) {
        if (builder == null || params == null || params.isEmpty()) {
            return;
        }
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                Object obj = params.get(key);
                if (obj != null) {
                    String value = obj.toString();
                    if (!TextUtils.isEmpty(value)) {
                        builder.addQueryParameter(key, value);
                    }
                }
            }
        }
    }

    /**
     * Get请求接数据
     */
    public static void appendHeadParams(Request.Builder build, Map<String, String> params) {
        if (build != null && params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                build.addHeader(key, params.get(key));
            }
            HttpLog.e("headParams-->" + ParamsUtil.formParams(GsonUtil.getGson().toJson(params)));
        }

    }


    /**
     * 组装文件上传post请求参数body
     */
    public static RequestBody appendBody(Map<String, Object> params) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        addParams(builder, params);
        return builder.build();
    }

    // 添加参数
    private static void addParams(MultipartBody.Builder builder, Map<String, Object> params) {
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                Object value = params.get(key);
                if (value instanceof File) {
                    // 处理文件 --> Object File
                    File file = (File) value;
                    builder.addFormDataPart(key, file.getName(), RequestBody
                            .create(MediaType.parse(guessMimeType(file
                                    .getAbsolutePath())), file));
                } else if (value instanceof List) {
                    // 代表提交的是 List集合
                    try {
                        List<File> listFiles = (List<File>) value;
                        for (int i = 0; i < listFiles.size(); i++) {
                            // 获取文件
                            File file = listFiles.get(i);
                            builder.addFormDataPart(key + i, file.getName(), RequestBody
                                    .create(MediaType.parse(guessMimeType(file
                                            .getAbsolutePath())), file));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    builder.addFormDataPart(key, value + "");
                }
            }
        }
    }

    /**
     * 猜测文件类型
     */
    private static String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

}
