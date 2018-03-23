package org.paul.lib.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SPHelper {

    public static Number getNumber(Context context, String key, Number defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (sharedPreferences != null) {
            if (sharedPreferences.contains(key)) {
                if (defaultValue instanceof Long) {
                    return sharedPreferences.getLong(key, defaultValue.longValue());
                }
                if (defaultValue instanceof Integer) {
                    return sharedPreferences.getInt(key, defaultValue.intValue());
                }
                if (defaultValue instanceof Float) {
                    return sharedPreferences.getFloat(key, defaultValue.floatValue());
                }
                return defaultValue;
            } else {
                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }

    public static boolean setNumber(Context context, String key, Number value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (value instanceof Integer) {
            edit.putInt(key, value.intValue());
        }
        if (value instanceof Float) {
            edit.putFloat(key, value.floatValue());
        }
        if (value instanceof Long) {
            edit.putLong(key, value.longValue());
        }
        return edit.commit();
    }

    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, defaultValue);
    }

    public static boolean setString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key, value);
        return edit.commit();
    }


}
