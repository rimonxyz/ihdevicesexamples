package com.ihealth.communication.cloud.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.ihealth.communication.utils.Log;

/**
 * SDK入口
 * @author brave
 *
 */
public class AM_InAuthor {

	private boolean isDebug = false;
	private static final String TAG = "AM_SDK";
	private boolean timerIsStart = false;
	public Context context;

	private static final AM_InAuthor INSTANCE = new AM_InAuthor();

	public static AM_InAuthor getInstance() {
		return INSTANCE;
	}
	public AM_InAuthor() {

	}
	public void initAuthor(Context context,String un){

		//取当前用户配置信息-由用户登录SDK验证获得
		//--注意:只有Host首字母大写 其他全部为小写
		SharedPreferences userInfo = context.getSharedPreferences(un+"userinfo", 0);
		String email = userInfo.getString("email", "");
		String apiName = userInfo.getString("apiName", "");
		String Host = userInfo.getString("Host", "");
		String accessToken = userInfo.getString("accessToken", "");
		String refreshToken = userInfo.getString("refreshToken", "");
		String client_id = userInfo.getString("client_id", "");
		String client_secret = userInfo.getString("client_secret", "");

		if(isDebug){
			Log.i(TAG, "进入SDK前打印用户配置!!!");
			Log.i(TAG, "email = " + email);
			Log.i(TAG, "apiName = " + apiName);
			Log.i(TAG, "Host = " + Host);
			Log.i(TAG, "accessToken = " + accessToken);
			Log.i(TAG, "refreshToken = " + refreshToken);
			Log.i(TAG, "client_id = " + client_id);
			Log.i(TAG, "client_secret = " + client_secret);
			Log.i(TAG, "取到数据放入配置文件 : jiuan.sdk.author");
		}

		//--注意:只有Host首字母大写 其他全部为小写
		SharedPreferences sp = context.getSharedPreferences("jiuan.sdk.author", 0);
		Editor edit = sp.edit();
		edit.putString("email", email);
		edit.putString("apiName", apiName);
		edit.putString("Host", Host);
		edit.putString("accessToken", accessToken);
		edit.putString("refreshToken", refreshToken);
		edit.putString("client_id", client_id);
		edit.putString("client_secret", client_secret);
		edit.commit();

		this.context = context;
	}

	//SDK 数据上传流程入口
	public void run(){

		//20140609 modify wz 将第三方注册放入到Timer中
		//解决 无网络访问情况下,开始无网络,之后开启网络的情况处理
		if(!timerIsStart){
			AM_Up timer = new AM_Up(this.context);
			timer.Start_timer();
			timerIsStart =true;
		}
	}
}
