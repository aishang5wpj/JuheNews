package com.aishang5wpj.juhenews.utils;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class JsonUtil {

    public static String getValue(JSONObject object, String key) {
        String value = "";
        if (object == null || !object.has(key)) {
            return value;
        }
        try {
            return object.get(key).toString();
        } catch (Exception e) {

        }
        return value;
    }

    public static Boolean getBoolean(JSONObject object, String key) {
        Boolean value = false;
        if (object == null || !object.has(key)) {
            return value;
        }
        try {
            return object.getBoolean(key);
        } catch (Exception e) {
        }
        return value;
    }

    public static boolean hasValue(JSONObject object, String key) {
        if (object == null || !object.has(key)) return false;
        return true;
    }

    public static int getInt(JSONObject object, String key) {
        if (object == null || !object.has(key))
            return -1;
        try {
            return object.getInt(key);
        } catch (Exception e) {

        }
        return -1;
    }

    public static Long getLong(JSONObject object, String key) {
        if (object == null || !object.has(key)) return 0l;
        try {
            return object.getLong(key);
        } catch (Exception e) {

        }
        return 0l;
    }

    public static JSONArray getArray(JSONObject object, String key) {
        if (object == null || !object.has(key)) return null;
        try {
            return object.getJSONArray(key);
        } catch (Exception e) {

        }
        return null;
    }

    public static JSONObject getObject(JSONObject object, String key) {
        if (object == null || !object.has(key)) return null;
        try {
            return object.getJSONObject(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
