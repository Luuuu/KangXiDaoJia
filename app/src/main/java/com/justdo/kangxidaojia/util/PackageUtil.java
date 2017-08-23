package com.justdo.kangxidaojia.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class PackageUtil {
	//获取版本名
	public static String getVersionName(Context context, String packageName){
		PackageManager manager = context.getPackageManager();
		String versionName = "";
		try {
			PackageInfo packageInfo = manager.getPackageInfo(packageName, 0);
			versionName = packageInfo.versionName;
			return versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "未知";
	}
	//获取版本号
	public static int getVersionCode(Context context, String packageName){
		PackageManager manager = context.getPackageManager();
		int versionCode = 1;
		try {
			PackageInfo packageInfo = manager.getPackageInfo(packageName, 0);
			versionCode = packageInfo.versionCode;
			return versionCode;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return versionCode;
	}
}
