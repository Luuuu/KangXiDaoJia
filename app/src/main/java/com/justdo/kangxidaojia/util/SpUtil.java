package com.justdo.kangxidaojia.util;

import android.content.Context;
import android.content.SharedPreferences;



public class SpUtil {

    private static final String SP_NAME = "config";
    private static SharedPreferences sp;

    private static Context context;

    public SpUtil(Context context) {
        this.context = context.getApplicationContext();
    }

    public static void saveString(String key, String value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, value).apply();

    }

    public static String getString(String key, String defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return sp.getString(key,defValue);
    }

}
