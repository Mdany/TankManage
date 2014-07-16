package org.monsterlab.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

@SuppressLint("CommitPrefEdits")
public class SharedPreferencesDB  {

	SharedPreferences preferences;
	SharedPreferences.Editor editor;

	/**
	 * 数据保存
	 * 
	 * @param str
	 * @param date
	 */
	public void dateSave(String str, int date, Context context) {
		// 获取只能被本应用程序读、写的SharedPreferences对象
		preferences = context.getSharedPreferences("starsbird", context.MODE_WORLD_READABLE);
		editor = preferences.edit();

		editor.putInt(str, date);
		editor.commit();
	}

	public void dateSave(String str, float date, Context context) {
		// 获取只能被本应用程序读、写的SharedPreferences对象
		preferences = context.getSharedPreferences("starsbird", context.MODE_WORLD_READABLE);
		editor = preferences.edit();

		editor.putFloat(str, date);
		editor.commit();
	}

	public void dateSave(String str, long date, Context context) {
		// 获取只能被本应用程序读、写的SharedPreferences对象
		preferences = context.getSharedPreferences("starsbird", context.MODE_WORLD_READABLE);
		editor = preferences.edit();

		editor.putLong(str, date);
		editor.commit();
	}

	public void dateSave(String str, String date, Context context) {
		// 获取只能被本应用程序读、写的SharedPreferences对象
		preferences = context.getSharedPreferences("starsbird", context.MODE_WORLD_READABLE);
		editor = preferences.edit();

		editor.putString(str, date);
		editor.commit();
	}

	public void dateSave(String str, boolean date, Context context) {
		// 获取只能被本应用程序读、写的SharedPreferences对象
		preferences = context.getSharedPreferences("starsbird", context.MODE_WORLD_READABLE);
		editor = preferences.edit();

		editor.putBoolean(str, date);
		editor.commit();
	}

	/**
	 * 获取数据
	 * @param str
	 * @param date
	 * @return
	 */
	public int getDate(String str, int date, Context context) {
		// 获取只能被本应用程序读、写的SharedPreferences对象
		preferences = context.getSharedPreferences("starsbird", context.MODE_WORLD_READABLE);
		editor = preferences.edit();

		return preferences.getInt(str, date);
	}
	public float getDate(String str, float date, Context context) {
		// 获取只能被本应用程序读、写的SharedPreferences对象
		preferences = context.getSharedPreferences("starsbird", context.MODE_WORLD_READABLE);
		editor = preferences.edit();

		return preferences.getFloat(str, date);
	}
	public long getDate(String str, long date, Context context) {
		// 获取只能被本应用程序读、写的SharedPreferences对象
		preferences = context.getSharedPreferences("starsbird", context.MODE_WORLD_READABLE);
		editor = preferences.edit();

		return preferences.getLong(str, date);
	}
	public String getDate(String str, String date, Context context) {
		// 获取只能被本应用程序读、写的SharedPreferences对象
		preferences = context.getSharedPreferences("starsbird", context.MODE_WORLD_READABLE);
		editor = preferences.edit();

		return preferences.getString(str, date);
	}
	public boolean getDate(String str, boolean date, Context context) {
		// 获取只能被本应用程序读、写的SharedPreferences对象
		preferences = context.getSharedPreferences("starsbird", context.MODE_WORLD_READABLE);
		editor = preferences.edit();

		return preferences.getBoolean(str, date);
	}
}
