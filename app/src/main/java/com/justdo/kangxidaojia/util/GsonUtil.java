package com.justdo.kangxidaojia.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

public class GsonUtil {
    private static final Gson gson = new Gson();

    private GsonUtil() {
    }

    public static String toJson(Object bean) {
        return gson.toJson(bean);
    }

    public static <T> T toBean(String jsonString, Class<T> clazz) {
        try {
            return gson.fromJson(jsonString, clazz);
        } catch (JsonSyntaxException e) {
            Log.e("GsonUtil", "toBean: Json解析异常,传入的class无法匹配json字符串");
            e.printStackTrace();
            return null;
        }
    }


    public static <T> List<T> toList(String jsonString, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        try {
            JsonArray arry = new JsonParser().parse(jsonString).getAsJsonArray();
            for (JsonElement jsonElement : arry) {
                list.add(gson.fromJson(jsonElement, clazz));
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            Log.e("GsonUtil", "toList: Json解析异常,传入的class无法匹配json字符串");
            return null;
        }
        return list;
    }

}
