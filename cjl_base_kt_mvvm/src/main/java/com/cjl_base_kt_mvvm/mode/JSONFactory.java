package com.cjl_base_kt_mvvm.mode;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.lang.reflect.Type;

/**
 * Created by cjl on 2019/9/4
 */

public class JSONFactory {

    public static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(String.class, new StringResponseDeserializer())
            .create();
    public static final JsonParser jsonParser = new JsonParser();

    private JSONFactory() {

    }

    public static String toJson(Object o) {
        return gson.toJson(o);
    }

    public static <T> T fromJson(String string, Class<T> tClass) {
        return gson.fromJson(string, tClass);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }

    public static JsonElement parseJson(String json) {
        return jsonParser.parse(json);
    }

    public static String getValue(JsonElement json, String key) {
        if (TextUtils.isEmpty(key)) {
            return null;
        }
        JsonElement jsonElement = json.getAsJsonObject().get(key);
        if (jsonElement.isJsonNull()) {
            return "";
        }
        return jsonElement.toString();
    }

}
