package com.daemon.framework.dutils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 普通的json解析
 * Created by Daemon on 2015/10/28.
 */
public class JsonObjectUtils {

    /**
     *
     * @param json
     * @param code
     * @return
     */
    public static String getString(String json, String code) {
        String result = "";
        try {
            JSONObject jsonObject = new JSONObject(json);
            result = jsonObject.getString(code);
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
        return result;
    }
}
