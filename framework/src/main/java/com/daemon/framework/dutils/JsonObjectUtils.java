package com.daemon.framework.dutils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 普通的json解析
 * Created by Daemon on 2015/10/28.
 */
public class JsonObjectUtils {

    /**
     * @param json
     * @param code
     * @return
     */
    public static String getString(String json, String code) throws JSONException {
        String result = "";

        JSONObject jsonObject = new JSONObject(json);
        result = jsonObject.getString(code);

        return result;
    }

    public static int getInt(String data, String type) throws JSONException {
        int result=-1;

        JSONObject jsonObject = new JSONObject(data);
        result = jsonObject.getInt(type);

        return result;
    }

    public static JSONArray getArray(String data, String type) throws JSONException {
        JSONArray result=null;

        JSONObject jsonObject = new JSONObject(data);
        result = jsonObject.getJSONArray(type);

        return result;
    }

}
