package com.ishow.androidlibs.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 缓存类，所有的数据都是采用SharedPreferences方式存储
 * @author Administrator
 *
 */
public class SharedPreferenceUtils {

	public static final String CACHE_FILE_NAME = "HUIYUAN";
	private static SharedPreferences mSharedPreferences;

	/**
	 * 获取boolean型数据
	 * @param context 上下文
	 * @param key key名称
	 * @param defValue 默认值
	 * @return
	 */
	public static boolean getBoolean(Context context,String key,boolean defValue){
		if(mSharedPreferences==null)
			mSharedPreferences = context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
		return  mSharedPreferences.getBoolean(key, defValue);
		
	}
	/**
	 * 存布尔值
	 * @param context 上下文
	 * @param key 存放的名字
	 * @param value 存放的值
	 */
	public static void putBoolean(Context context,String key,boolean value){
		if(mSharedPreferences==null){
			mSharedPreferences = context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
		}
		mSharedPreferences.edit().putBoolean(key, value).commit();
	}
	/**
	 * 存储String类型的数据
	 * @param context 上下文
	 * @param key 存放的名字
	 * @param value 存放的值
	 */
	public static void putString(Context context,String key,String value){
		if(mSharedPreferences==null){
			mSharedPreferences = context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
		}
		mSharedPreferences.edit().putString(key, value).commit();
	}
	/**
	 * 获取String型数据
	 * @param context 上下文
	 * @param key key名称
	 * @param defValue 默认值
	 * @return String
	 */
	public static String getString(Context context,String key,String defValue){
		if(mSharedPreferences==null)
			mSharedPreferences = context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
		return  mSharedPreferences.getString(key, defValue);
		
	}
	/**
	 * 存储int类型的数据
	 * @param context 上下文
	 * @param key 存放的名字
	 * @param value 存放的值
	 */
	public static void putInt(Context context,String key,int value){
		if(mSharedPreferences==null){
			mSharedPreferences = context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
		}
		mSharedPreferences.edit().putInt(key, value).commit();
	}
	/**
	 * 获取int型数据
	 * @param context 上下文
	 * @param key key名称
	 * @param defValue 默认值
	 * @return int
	 */
	public static int getInt(Context context,String key,int defValue){
		if(mSharedPreferences==null)
			mSharedPreferences = context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
		return  mSharedPreferences.getInt(key, defValue);
	}
	/**
	 * 存储long类型的数据
	 * @param context 上下文
	 * @param key 存放的名字
	 * @param value 存放的值
	 */
	public static void putLong(Context context,String key,long value){
		if(mSharedPreferences==null){
			mSharedPreferences = context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
		}
		mSharedPreferences.edit().putLong(key, value).commit();
	}
	/**
	 * 获取long型数据
	 * @param context 上下文
	 * @param key key名称
	 * @param defValue 默认值
	 * @return long
	 */
	public static long getLong(Context context,String key,long defValue){
		if(mSharedPreferences==null)
			mSharedPreferences = context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
		return  mSharedPreferences.getLong(key, defValue);
	}
	/**
	 * 清除所有的数据
	 * @param context
	 */
	public static void cleanAllKey(Context context){
		if(mSharedPreferences==null)
			mSharedPreferences = context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
		mSharedPreferences.edit().clear();
	}
}
