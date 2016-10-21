package com.ihealth.communication.cloud;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.ihealth.communication.cloud.tools.AppIDFactory;
import com.ihealth.communication.cloud.tools.AppsDeviceParameters;
import com.ihealth.communication.cloud.tools.HttpsPost;
import com.ihealth.communication.cloud.tools.Method;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CommCloudSyncTime {

	public static final String TAG = "CommCloudSyncTime";

	Context context;
	public String messageReturn = "";
	public int result = 0;
	public long TS = 0;
	public int resultMessage = 0;
	public int queueNum = 0;

	private static final String SV_downloadSyncTime = "08f8480f0d2f4bd4bb63a23ceebeb45a";
	private static final String SV_uploadSyncTime = "8f75928e6bc9490bafea38be9d3e678c";


	/**
	 * 构造函数
	 */
	public CommCloudSyncTime(Context context) {
		this.context = context;
	}


	/**
	 * BG5 3.0同步时间上传
	 * @throws SocketTimeoutException
	 */
	public int uploadSyncTime(String mac, String deviceType, long TS) throws Exception {

		// 编辑发送参数
		Map<String, String> workout_uploadParams = new HashMap<String, String>();
		workout_uploadParams.put("sc", AppsDeviceParameters.SC);
		workout_uploadParams.put("sv", SV_uploadSyncTime);
		workout_uploadParams.put("AppVersion", AppsDeviceParameters.APP_VERSION);
		workout_uploadParams.put("AppGuid", getAppID());
		workout_uploadParams.put("PhoneOS", android.os.Build.VERSION.RELEASE);
		workout_uploadParams.put("PhoneName", android.os.Build.MODEL);
		workout_uploadParams.put("PhoneID", getDeviceID());
		workout_uploadParams.put("PhoneLanguage", Locale.getDefault().getLanguage());
		workout_uploadParams.put("PhoneRegion", Locale.getDefault().getCountry());
		workout_uploadParams.put("QueueNum", "1");
		workout_uploadParams.put("Token", "");

		// 编辑参数UploadData
		JSONArray jsonWTUArr = new JSONArray();
		try {
			JSONObject jsonWTUData;
			for (int i = 0; i < 1; i++) {
				jsonWTUData = new JSONObject();

				jsonWTUData.put("mDeviceId", mac);
				jsonWTUData.put("TimeOfAppSetLow", TS);
				jsonWTUData.put("DeviceType", deviceType);
				jsonWTUData.put("TimeZone", Method.getTimeZone());

				jsonWTUArr.put(i, jsonWTUData);
			}
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		workout_uploadParams.put("UploadData", jsonWTUArr.toString());

		String webAddress = "https://api.ihealthlabs.com:443/api5/lowmachine_upload_time.htm";//流程中调用接口


		try {
			HttpsPost ht = new HttpsPost(context);
			messageReturn = ht.requireClass(webAddress, workout_uploadParams, "UTF-8");

		} catch (UnknownHostException e) {
			return 101;
		} catch (SocketTimeoutException e) {
			return 102;
		}

		if (messageReturn.length() == 0) {
			return 999;
		} else if (messageReturn.length() == 3) {
			return Integer.valueOf(messageReturn);//403 404 500
		}

		try {
			JSONTokener jsonTParser = new JSONTokener(messageReturn);
			JSONObject jsonORegist = (JSONObject) jsonTParser.nextValue();

			this.result = jsonORegist.getInt("Result");
			this.TS = Long.parseLong(jsonORegist.getString("TS"));
			this.resultMessage = Integer.parseInt(jsonORegist.getString("ResultMessage"));
			this.queueNum = jsonORegist.getInt("QueueNum");

			if (resultMessage == 100) {
				return 100;
			}
			else {
				return resultMessage;
			}
		} catch (JSONException e) {
			return 999;
		}
	}

	private long syncTime;
	public long getSyncTime(){
		return syncTime;
	}
	/**
	 * BG5 3.0 同步时间下载
	 * @throws SocketTimeoutException
	 */
	public int downloadSyncTime(String mac, String deviceType) throws Exception {

		syncTime = 0;

		// 编辑发送参数
		Map<String, String> workout_uploadParams = new HashMap<String, String>();
		workout_uploadParams.put("sc", AppsDeviceParameters.SC);
		workout_uploadParams.put("sv", SV_downloadSyncTime);
		workout_uploadParams.put("AppVersion", AppsDeviceParameters.APP_VERSION);
		workout_uploadParams.put("AppGuid", getAppID());
		workout_uploadParams.put("PhoneOS", android.os.Build.VERSION.RELEASE);
		workout_uploadParams.put("PhoneName", android.os.Build.MODEL);
		workout_uploadParams.put("PhoneID", getDeviceID());
		workout_uploadParams.put("PhoneLanguage", Locale.getDefault().getLanguage());
		workout_uploadParams.put("PhoneRegion", Locale.getDefault().getCountry());
		workout_uploadParams.put("QueueNum", "1");
		workout_uploadParams.put("Token", "");

		workout_uploadParams.put("mDeviceId", mac);
		workout_uploadParams.put("DeviceType", deviceType);//"BG5"

		// 编辑参数UploadData

		String webAddress =  "https://api.ihealthlabs.com:443/api5/lowmachine_download_time.htm";//流程中调用接口

		try {
			HttpsPost ht = new HttpsPost(context);
			messageReturn = ht.requireClass(webAddress, workout_uploadParams, "UTF-8");

		} catch (UnknownHostException e) {
			return 101;
		} catch (SocketTimeoutException e) {
			return 102;
		}

		if (messageReturn.length() == 0) {
			return 999;
		} else if (messageReturn.length() == 3) {
			return Integer.valueOf(messageReturn);//403 404 500
		}

		try {
			JSONTokener jsonTParser = new JSONTokener(messageReturn);
			JSONObject jsonORegist = (JSONObject) jsonTParser.nextValue();

			this.result = jsonORegist.getInt("Result");
			this.TS = Long.parseLong(jsonORegist.getString("TS"));
			this.resultMessage = Integer.parseInt(jsonORegist.getString("ResultMessage"));
			this.queueNum = jsonORegist.getInt("QueueNum");

			if (resultMessage == 100) {
				JSONTokener jsonTokener = new JSONTokener(jsonORegist.getString("ReturnValue"));
				JSONObject jsonWebListOut = (JSONObject) jsonTokener.nextValue();
				syncTime = jsonWebListOut.getLong("TimeOfAppSetLow");
				return 100;
			}
			else {
				return resultMessage;
			}
		} catch (JSONException e) {
			return 999;
		}
	}

	private String getDeviceID() {

		TelephonyManager telephonyManager = (TelephonyManager) this.context
				.getSystemService(Context.TELEPHONY_SERVICE);

		return (telephonyManager.getDeviceId() != null) ? telephonyManager
				.getDeviceId() : getAppID();
	}

	private String getAppID() {
		// String appID = getDeviceID();
		String appID = "appID";
		AppIDFactory appIDFactory = new AppIDFactory(context);
		appID = appIDFactory.getAppID();
		return appID;
	}
}
