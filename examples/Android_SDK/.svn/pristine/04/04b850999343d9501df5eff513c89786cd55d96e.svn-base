package com.ihealth.communication.cloud.tools;

import java.math.BigInteger;
import java.security.SecureRandom;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.provider.Settings.Secure;
import com.ihealth.communication.utils.Log;

public class AppIDFactory {

	private boolean isDebug = false;
	protected static final String TAG = "getAppIDFactory";
	
	protected static final String PREFS_FILE = "device_id.txt";
	protected static final String PREFS_DEVICE_ID = "device_id";
	

	String AppID;
	Context context;

	public AppIDFactory(Context context) {
		// this.AppID = new String();
		this.context = context;
		// Log.e(TAG, "调用 getAppIDFactory 构造函数");
	}

	public String getAppID() {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE, 0);
		AppID = prefs.getString(PREFS_DEVICE_ID, null);

		if (AppID == null) {
			AppID = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
			if(isDebug)
				Log.e(TAG, "AppID SecureRandom before = " + AppID);
			final SecureRandom random = new SecureRandom();
			AppID = new BigInteger(64, random).toString(16);
			if(isDebug)
				Log.e(TAG, "AppID SecureRandom after = " + AppID);

			Editor editor = prefs.edit();
			editor.putString(PREFS_DEVICE_ID, AppID);
			editor.commit();
			// if(editor!=null)
			// editor = null;
		} else {
			if(isDebug)
				Log.e(TAG, "SharedPreferences 已经有 AppID 直接取 " + AppID);
		}

		return AppID;
	}

	public boolean dropAppID() {

		SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE, 0);
		AppID = prefs.getString(PREFS_DEVICE_ID, null);
		Editor editor = prefs.edit();
		editor.clear();
		if (editor.commit()) {
			if(isDebug)
				Log.e(TAG, "删除用户 sharePerence 成功");
			return true;
		} else {
			if(isDebug)
				Log.e(TAG, "删除用户 sharePerence 失败");
			return false;
		}

		// File file = new File("/data/data/" +
		// context.getPackageName().toString() + "/shared_prefs",
		// PREFS_FILE+".xml");
		//
		// if (file.exists()) {
		// Log.e(TAG, "有此APPID文件");
		// if(file.delete()){
		// Toast.makeText(context, "删除成功", Toast.LENGTH_LONG).show();
		// return true;
		// }else{
		// Toast.makeText(context, "删除失败", Toast.LENGTH_LONG).show();
		// return false;
		// }
		// }else{
		// Log.e(TAG, "无此APPID文件");
		// return false;
		// }

	}
}
