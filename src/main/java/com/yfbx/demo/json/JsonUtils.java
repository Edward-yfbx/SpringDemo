package com.yfbx.demo.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonUtils {

    private static ObjectMapper objectMapper;

    private static int ERROR = -1;//服务端出错
    private static int FAIL = 0;//由客户端参数导致的异常
    private static int SUCCESS = 1;//请求成功

    public static String success(String msg) {
        EntityJson json = new EntityJson();
        json.setCode(SUCCESS);
        json.setMsg(msg);
        return toJson(json);
    }

    public static String success(String msg, Object data) {
        EntityJson json = new EntityJson();
        json.setCode(SUCCESS);
        json.setMsg(msg);
        json.setData(data);
        return toJson(json);
    }

    public static String success(String msg, List<?> data) {
        ArrayJson json = new ArrayJson();
        json.setCode(SUCCESS);
        json.setMsg(msg);
        json.setData(data);
        return toJson(json);
    }

    public static String fail(String msg) {
        EntityJson json = new EntityJson();
        json.setCode(FAIL);
        json.setMsg(msg);
        return toJson(json);
    }

    public static String error(String msg) {
        EntityJson json = new EntityJson();
        json.setCode(ERROR);
        json.setMsg(msg);
        return toJson(json);
    }

    /**
     * 解析json
     */
    public static <T> T fromJson(String content, Class<T> valueType) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        try {
            return objectMapper.readValue(content, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成json
     */
    public static String toJson(Object object) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
