package cn.bproject.neteasynews.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharePreference封装
 */
public class PrefUtils {

	public static boolean getBoolean(Context ctx, String preferencesName, String key, boolean defValue) {
		SharedPreferences sp = ctx.getSharedPreferences(preferencesName,
				Context.MODE_PRIVATE);
		return sp.getBoolean(key, defValue);
	}

	public static void setBoolean(Context ctx, String preferencesName, String key, boolean value) {
		SharedPreferences sp = ctx.getSharedPreferences(preferencesName,
				Context.MODE_PRIVATE);
		sp.edit().putBoolean(key, value).commit();
	}

	public static void setString(Context ctx, String preferencesName, String key, String value) {
		SharedPreferences sp = ctx.getSharedPreferences(preferencesName,
				Context.MODE_PRIVATE);
		sp.edit().putString(key, value).commit();
	}

	public static String getString(Context ctx, String preferencesName, String key, String defValue) {
		SharedPreferences sp = ctx.getSharedPreferences(preferencesName,
				Context.MODE_PRIVATE);
		return sp.getString(key, defValue);
	}

	public static void setInt(Context ctx, String preferencesName, String key, int value) {
		SharedPreferences sp = ctx.getSharedPreferences(preferencesName,
				Context.MODE_PRIVATE);
		sp.edit().putInt(key, value).commit();
	}

	public static int getInt(Context ctx, String preferencesName, String key, int defValue) {
		SharedPreferences sp = ctx.getSharedPreferences(preferencesName,
				Context.MODE_PRIVATE);
		return sp.getInt(key, defValue);
	}

	public static void setLong(Context ctx, String preferencesName, String key, long value) {
		SharedPreferences sp = ctx.getSharedPreferences(preferencesName,
				Context.MODE_PRIVATE);
		sp.edit().putLong(key, value).commit();
	}

	public static long getLong(Context ctx, String preferencesName, String key, long defValue) {
		SharedPreferences sp = ctx.getSharedPreferences(preferencesName,
				Context.MODE_PRIVATE);
		return sp.getLong(key, defValue);
	}
}
