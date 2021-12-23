package com.cthugha.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Classname JsonResponse
 * @Description 用于生成Json格式响应的工具类
 * @Date 2021/11/5 22:29
 * @Created by gaoqi
 */
public class JsonResponse<T> {

    private T data;
    private Integer state;
    private String message;
    private Integer type;

    /**
     * 无返回数据，成功，状态码0
     */
    public JsonResponse() {
        this.state = 0;
        this.message = "操作成功";
    }

    /**
     * 无返回数据，自定义状态码和信息
     * @param state 状态码
     * @param message 信息
     */
    public JsonResponse(Integer state, String message) {
        this.state = state;
        this.message = message;
    }

    /**
     * 有返回数据，默认成功，状态码0
     * @param data 返回数据
     */
    public JsonResponse(T data) {
        this.data = data;
        this.state = 0;
        this.message = "操作成功";
    }

    /**
     * 有返回数据，默认成功，状态码0，自定义信息
     * @param data 返回数据
     * @param message 信息
     */
    public JsonResponse(T data, String message) {
        this.data = data;
        this.state = 0;
        this.message = message;
    }

    public JsonResponse(T data, Integer type) {
        this.data = data;
        this.type = type;
    }

    public JsonResponse(T data, String message, Integer type) {
        this.data = data;
        this.message = message;
        this.type = type;
    }

    public JsonResponse(T data, Integer state, String message, Integer type) {
        this.data = data;
        this.state = state;
        this.message = message;
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String toJsonString() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(this);
    }
}
