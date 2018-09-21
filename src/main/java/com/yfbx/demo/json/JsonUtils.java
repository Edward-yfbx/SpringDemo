package com.yfbx.demo.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yfbx.demo.constant.Code;

import java.util.List;

public class JsonUtils {

    private static ObjectMapper objectMapper;


    public static String success(String msg) {
        EntityJson json = new EntityJson();
        json.setCode(Code.SUCCESS);
        json.setMsg(msg);
        return toJson(json);
    }

    public static String success(String msg, Object data) {
        EntityJson json = new EntityJson();
        json.setCode(Code.SUCCESS);
        json.setMsg(msg);
        json.setData(data);
        return toJson(json);
    }

    public static String success(String msg, List<?> data) {
        ArrayJson json = new ArrayJson();
        json.setCode(Code.SUCCESS);
        json.setMsg(msg);
        json.setData(data);
        return toJson(json);
    }

    public static String fail(String msg) {
        EntityJson json = new EntityJson();
        json.setCode(Code.FAIL);
        json.setMsg(msg);
        return toJson(json);
    }

    public static String error(String msg) {
        EntityJson json = new EntityJson();
        json.setCode(Code.ERROR);
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
