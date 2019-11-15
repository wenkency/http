package cn.carhouse.http.util;

import androidx.collection.ArrayMap;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public class RequestUtil {

    public static final Gson mGson = GsonUtil.getGson();


    public static Map<String, Object> getObjParams(Object obj) {
        Map<String, Object> list = new ArrayMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                String name = field.getName();
                if (name == null || name.contains("$") || name.equals("serialVersionUID")) {
                    continue;
                }
                field.setAccessible(true);
                Object value = field.get(obj);
                if (value != null) {
                    // 根据标识转成Json
                    FieldToJson toJson = field.getAnnotation(FieldToJson.class);
                    if (toJson != null) {
                        list.put(name, mGson.toJson(value));
                    } else {
                        list.put(name, value.toString());
                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 请求共用方法
     *
     * @param params String[] 数组参数. 格式: {"key", value, "key", value...}
     * @return
     */
    public static Map<String, Object> getObjParams(String... params) {
        Map<String, Object> map = new HashMap<>();
        // 业务的参数
        String key = "";
        String value = "";
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                if (i % 2 == 0) {
                    key = params[i];// 取得Key
                } else {
                    value = params[i];// 取得值
                    map.put(key, value);//添加到集合
                }
            }
        }
        return map;
    }
}