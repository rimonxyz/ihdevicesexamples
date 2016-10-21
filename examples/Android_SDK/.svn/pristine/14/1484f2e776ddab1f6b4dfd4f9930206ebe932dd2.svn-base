package com.ihealth.communication.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.cloud.CommCloudSyncTime;
import com.ihealth.communication.cloud.tools.AppsDeviceParameters;
import com.ihealth.communication.cloud.tools.Method;
import com.ihealth.communication.control.Bg5Profile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class BgSyncData {

	private static final String TAG = "BgSyncData";
	private static Context mContext;
	private static long getLastSyncTime(Context con, String mac) {
		mContext = con;
		long syncTime = 0;
		long templ1 = downloadSyncTime(con, mac);
		long templ2 = getHistoryTime(mac);//0
		Log.v(TAG,"templ1:" +  Method.LongToDate(templ1) + " templ2:" + Method.LongToDate(templ2));

		if(templ2 > templ1){
			syncTime = templ2;
		}else{
			if(templ1 > Method.getTS()) {
				syncTime = templ2;
			} else {
				syncTime = templ1;
			}
		}
		return syncTime;
	}

	private static SharedPreferences sharedPreferences;
	private static SharedPreferences.Editor editor;
	public static void setHistoryTime(Context context, String mac, long time) {

		sharedPreferences = context.getSharedPreferences("historyTimeBG", Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		editor.putLong(mac, time);
		editor.commit();
	}

	private static long getHistoryTime(String mac) {
		sharedPreferences = mContext.getSharedPreferences("historyTimeBG", Context.MODE_PRIVATE);
		long req = sharedPreferences.getLong(mac, 0);
		return req;
	}

	public static boolean uploadSyncTime(Context con, String mac, long TS) {
		boolean upState = false;
		CommCloudSyncTime comm = new CommCloudSyncTime(con);
		try {
			setHistoryTime(con, mac, TS);
			int result = comm.uploadSyncTime(mac, "BG5", TS);
			if (result == 100) {
				Log.v(TAG,"uploadSyncTime:" + new Date(TS*1000));
				upState = true;
			} else {
				upState = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return upState;
	}

	public static long downloadSyncTime(Context con, String mac) {
		long time = 0;
		CommCloudSyncTime comm = new CommCloudSyncTime(con);
		try {
			int result = comm.downloadSyncTime(mac, "BG5");
			if (result == 100) {
				time = comm.getSyncTime();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	/*
	* process the firmware version 3.0 BG5 offline data
	* */
	public static JSONArray processData(Context con, String mac, JSONArray array) {
		long regTime = getLastSyncTime(con, mac);
		Log.v(TAG,"regTime:" + Method.LongToDate(regTime));
		JSONArray result = new JSONArray();
		try {
			for (int i = 0; i < array.length(); i++) {
				JSONObject ob = array.optJSONObject(i);
				JSONObject temp = new JSONObject();

				String date = ob.getString("date");
				String date1 = "";


				long off = 0;
				if(Method.DateToLong(date) <= regTime) {
					date1 = date;
				} else {
					if(regTime == 0) {
						date1 = date;
					} else {
						off = ((Method.DateToLong(date) - regTime)/2) + regTime;
						date1 = Method.LongToDate(off);
					}
				}

				temp.put("date",date1);
				temp.put("value",ob.getInt("value"));
				temp.put(Bg5Profile.DATA_ID,ob.getString(Bg5Profile.DATA_ID));

				result.put(temp);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

}
