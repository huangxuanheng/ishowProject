package com.ishow.androidlibs.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;


public class UIUtils {
	public static Context getContext(){
		return null;
	}

	public static Resources getResources(){
		return getContext().getResources();
	}
	public static String getString(int id){
		return getResources().getString(id);
	}

	public static boolean getBoolean(int id){
		return getResources().getBoolean(id);
	}


	/**
	 * 获取资源文件夹values中的strings.xml下定义的数组
	 * @param id
	 * @return
	 */
	public static String[] getStringArray(int id){
		return getResources().getStringArray(id);
	}
	public static Drawable getDrawable(int id){
		return getResources().getDrawable(id);
	}
	
	//dip To  px
	public static int dip2px(int dp){
		//dp和px的转换关系
		float density = getResources().getDisplayMetrics().density;
		//2*1.5+0.5  2*0.75 = 1.5+0.5
		return (int)(dp*density+0.5);
	}
	
	//px  To  dip
	public static int px2dip(int px){
		float density = getResources().getDisplayMetrics().density;
		return (int)(px/density+0.5);
	}
	

	public static ColorStateList getColorStateList(int mTabTextColorResId) {
		return getResources().getColorStateList(mTabTextColorResId);
	}
	
	public static int getDimens(int id){
		return getResources().getDimensionPixelSize(id);
	}


}
