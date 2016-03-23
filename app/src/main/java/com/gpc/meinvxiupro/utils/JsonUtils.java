package com.gpc.meinvxiupro.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * json parser utils
 *
 * @author pcgu
 */
public class JsonUtils {
    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = null;

    static {
        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gson = builder.create();
    }

    /**
     * convert from json to Object,Object can include List ,Map and more
     */
    public static <T> T fromJson(String msg, Class<T> classOfT) {
        try {
            return gson.fromJson(msg, classOfT);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * convert from json to Object by user define policy,Object can include List
     * ,Map and more
     */
    public static <T> T fromJson(String msg, Class<T> classOfT,
                                 FieldNamingPolicy policy) {
        try {
            GsonBuilder builderPolicy = new GsonBuilder();
            builderPolicy.setFieldNamingPolicy(policy);
            Gson gsonPolicy = builderPolicy.create();
            return gsonPolicy.fromJson(msg, classOfT);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * convert to list
     */
    public static <T> ArrayList<T> fromJsonToList(String msg) {
        try {
            ArrayList<T> retList = gson.fromJson(msg, new TypeToken<List<T>>() {
            }.getType());
            return retList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * convert to list by user define policy
     */
    public static <T> ArrayList<T> fromJsonToList(String msg,
                                                  FieldNamingPolicy policy) {
        try {
            GsonBuilder builderPolicy = new GsonBuilder();
            builderPolicy.setFieldNamingPolicy(policy);
            Gson gsonPolicy = builderPolicy.create();
            ArrayList<T> retList = gsonPolicy.fromJson(msg, new TypeToken<List<T>>() {
            }.getType());
            return retList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * convert to json from Object,Object can include List ,Map and more
     */
    public static String toJson(Object objectOfJson) {
        try {
            return gson.toJson(objectOfJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * convert from json to Object,Object can include List ,Map and more
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromJson(String json, Type type) {
        try {
            return (T) gson.fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * convert from json to Object,Object can include List ,Map and more
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromJson(String json, Type type, FieldNamingPolicy policy) {
        try {
            return (T) gson.fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * convert to json from Object by user define policy,Object can include List
     * ,Map and more
     */
    public static String toJson(Object objectOfJson, FieldNamingPolicy policy) {
        try {
            GsonBuilder builderPolicy = new GsonBuilder();
            builderPolicy.setFieldNamingPolicy(policy);
            Gson gsonPolicy = builderPolicy.create();
            return gsonPolicy.toJson(objectOfJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
