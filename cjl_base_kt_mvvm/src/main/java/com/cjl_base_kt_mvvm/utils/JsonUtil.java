package com.cjl_base_kt_mvvm.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

public class JsonUtil {
    private Gson mGson;

    public static JsonUtil getInstance() {
        return new JsonUtil();
    }

    public JsonUtil() {
        mGson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    public Gson getGson() {
        return mGson;
    }

    public <T> T getObjectByStr(String json, Class<T> cls) {
        try {
            return mGson.fromJson(json, cls);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T getObjectByStr(String json, Type typeOfT) {
        try {
            return mGson.fromJson(json, typeOfT);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toJsonStr(Object src) {
        return mGson.toJson(src);
    }

    public int getInt(String jsonStr, String key){
        int msg=0;
        try {
            JSONObject jsonObject=new JSONObject(jsonStr);
            msg=jsonObject.getInt(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return msg;
    }

    public String getString(String jsonStr, String key){
        String data="无数据";
        try {
            JSONObject jsonObject=new JSONObject(jsonStr);
            data=jsonObject.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 获取 success
     * @param jsonStr
     * @return
     */
    public boolean getSuccess(String jsonStr){
        boolean success = false;
        try {
            JSONObject jsonObject=new JSONObject(jsonStr);
            success=jsonObject.getBoolean("success");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return success;
    }

    /**
     * 获取code
     * @param jsonStr
     * @return
     */
    public int getInt(String jsonStr){
        int msg=0;
        try {
            JSONObject jsonObject=new JSONObject(jsonStr);
            msg=jsonObject.getInt("code");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 获取succeed
     * @param jsonStr
     * @return
     */
    public boolean getBoole(String jsonStr){
        boolean succeed = false;
        try {
            JSONObject jsonObject=new JSONObject(jsonStr);
            succeed=jsonObject.getBoolean("succeed");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return succeed;
    }



    /**
     * 获取错误error信息
     * @param jsonStr
     * @return
     */
    public String getError(String jsonStr){
        String data="无数据";
        try {
            JSONObject jsonObject=new JSONObject(jsonStr);
            data=jsonObject.getString("error");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }


    /**
     * 获取错误msg信息
     * @param jsonStr
     * @return
     */
    public String getString(String jsonStr){
        String data="无数据";
        try {
            JSONObject jsonObject=new JSONObject(jsonStr);
            data=jsonObject.getString("msg");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

}
