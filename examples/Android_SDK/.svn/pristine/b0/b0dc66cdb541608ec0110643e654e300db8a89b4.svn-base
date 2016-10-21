package com.ihealth.communication.cloud.data;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.ihealth.communication.cloud.CommCloudSDK;
import com.ihealth.communication.cloud.ReturnDataUser;
import com.ihealth.communication.cloud.UserCheckSDK;
import com.ihealth.communication.cloud.tools.AppIDFactory;
import com.ihealth.communication.cloud.tools.AppsDeviceParameters;
import com.ihealth.communication.cloud.tools.HttpsPost;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.telephony.TelephonyManager;
import com.ihealth.communication.utils.Log;

public class BG_CommCloud {

	private static boolean isDebug = false;
	public static final String TAG = "BG_SDK"; 
	
	Context context;
	private String QueueNum = "111111";
	public String messageReturn = "";
	public int result = 0;
	public long TS = 0;
	public float resultMessage = 0;
	public int queueNum = 0;
	
	public static final String SV_bg_upload = "163fc4265de64d518e287d7696d3b71f";
	
	private String un;
	private String host;
	private String client_id;
	private String client_secret;
	/**
	 * 构造函数
	 */
	public BG_CommCloud(Context context) {
		if(isDebug)
			Log.i(TAG, "实例化CommCloudBG,获取本地配置 un host");
		this.context = context;
		un = context.getSharedPreferences("jiuan.sdk.author", 0).getString("email", "");
		host = context.getSharedPreferences("jiuan.sdk.author", 0).getString("Host", "");//获取最优服务器
		if("".equals(host)){
			host = AppsDeviceParameters.webSite;
		}
		client_id = context.getSharedPreferences("jiuan.sdk.author", 0).getString("client_id", "");
		client_secret = context.getSharedPreferences("jiuan.sdk.author", 0).getString("client_secret", "");
		if(isDebug){
			Log.i(TAG, "取得un = " + un);
			Log.i(TAG, "取得host = " + host);
			Log.i(TAG, "取得client_id = " + client_id);
			Log.i(TAG, "取得client_secret = " + client_secret);
		}
	}

	/**
	 * @param userName 用户名
	 * @param VerifyToken 登录时获取的Token
	 * @throws SocketTimeoutException
	 *             author_GYL 2013年11月26日 上午11:25:41
	 */
	public BG_ReturnData bg_upload(String userName, String VerifyToken, ArrayList<Data_BG_Result> bgArr, String inputHost) throws ConnectTimeoutException, SocketTimeoutException {

		BG_ReturnData bgUploadReturn = new BG_ReturnData();
		
		// 编辑发送参数
		Map<String, String> bg_uploadParams = new HashMap<String, String>();
		
		bg_uploadParams.put("sc", AppsDeviceParameters.SC);
		bg_uploadParams.put("sv", SV_bg_upload);//
		bg_uploadParams.put("AppVersion", AppsDeviceParameters.APP_VERSION);
		bg_uploadParams.put("AppGuid", getAppID());
		bg_uploadParams.put("PhoneOS", "android"+android.os.Build.VERSION.RELEASE);
		bg_uploadParams.put("PhoneName", android.os.Build.MODEL);
		bg_uploadParams.put("PhoneID", getDeviceID());
		bg_uploadParams.put("PhoneLanguage", Locale.getDefault().getLanguage());
		bg_uploadParams.put("PhoneRegion", Locale.getDefault().getCountry());
		bg_uploadParams.put("QueueNum", QueueNum);
		bg_uploadParams.put("Token", "");

		// 编辑参数UploadData
		JSONArray jsonBGUArr = new JSONArray();
		try {
			JSONObject jsonBGUData;
			// 生成BP JSON
			if(isDebug)
				Log.i(TAG, "血糖 上传 条目数 = " + bgArr.size());
			for (int i = 0; i < bgArr.size(); i++) {
				jsonBGUData = new JSONObject();

				jsonBGUData.put("ChangeType", bgArr.get(i).getChangeType());
				jsonBGUData.put("LastChangeTime", bgArr.get(i).getLastChangeTime());
				jsonBGUData.put("PhoneCreateTime", bgArr.get(i).getPhoneCreateTime());
				jsonBGUData.put("PhoneDataID", bgArr.get(i).getPhoneDataID());
				jsonBGUData.put("Lat", bgArr.get(i).getLat());
				jsonBGUData.put("Lon", bgArr.get(i).getLon());
				jsonBGUData.put("TimeZone", bgArr.get(i).getTimeZone());

				jsonBGUData.put("BGValue", bgArr.get(i).getBGValue());
				jsonBGUData.put("Medication", bgArr.get(i).getMedication());
				jsonBGUData.put("MTimeType", bgArr.get(i).getMTimeType());
				jsonBGUData.put("MeasureType", bgArr.get(i).getMeasureType());
				jsonBGUData.put("BottleId", bgArr.get(i).getBottleId());
				jsonBGUData.put("Sports", bgArr.get(i).getSports());
				jsonBGUData.put("Snacks", bgArr.get(i).getSnacks());
				jsonBGUData.put("Effective", bgArr.get(i).getEffective());
				
				jsonBGUData.put("MeasureTime", bgArr.get(i).getMeasureTime());
				jsonBGUData.put("Note", bgArr.get(i).getNote());
				jsonBGUData.put("MechineType", bgArr.get(i).getMechineType());
				jsonBGUData.put("MechineDeviceID", bgArr.get(i).getMechineDeviceID());

				jsonBGUArr.put(i, jsonBGUData);
			}
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		bg_uploadParams.put("Un", userName);
		bg_uploadParams.put("VerifyToken", VerifyToken);
		bg_uploadParams.put("UploadData", jsonBGUArr.toString());

		String webAddress = inputHost + AppsDeviceParameters.path + "bg_upload.htm";//流程中调用接口
		
		HttpsPost ht = new HttpsPost(context);
		
		if(isDebug)
			Log.i(TAG, "血糖数据上传 = " + bg_uploadParams.toString());
		
		try {
			messageReturn = ht.requireClass(webAddress, bg_uploadParams, "UTF-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (messageReturn.length() == 0)
			return bgUploadReturn;

		// 分析收取的数据
		try {
			JSONTokener jsonTParser = new JSONTokener(messageReturn);
			JSONObject jsonBO = (JSONObject) jsonTParser.nextValue();
			
			this.result = jsonBO.getInt("Result");
			this.TS = Long.parseLong(jsonBO.getString("TS"));
			this.resultMessage = Float.parseFloat(jsonBO.getString("ResultMessage"));
			this.queueNum = jsonBO.getInt("QueueNum");

			bgUploadReturn.setResultMessage(jsonBO.getString("ResultMessage"));
			
			if (resultMessage == 100.0) {
				return bgUploadReturn;
			} else if(resultMessage == 208.0){//服务器地址不正确
				JSONTokener jsonTokener = new JSONTokener(jsonBO.getString("ReturnValue"));
				JSONObject jsonWebListOut = (JSONObject) jsonTokener.nextValue();
				String regionHost = jsonWebListOut.getString("RegionHost");
				if(isDebug)
					Log.i(TAG, "bp_upload返回208,拿到regionHost = " + regionHost);

				BG_ReturnData bgUploadReturn1 = bg_upload(un, VerifyToken, bgArr, regionHost);
				if ("100".equals(bgUploadReturn1.getResultMessage())) {// 登录成功
					// 保存最新的信息
					UserCheckSDK.saveUserInfo(context, null, null, regionHost, null, null, null, null, -1);
					if(isDebug)
						Log.i(TAG, "保存regionHost到本地");
					host = regionHost;
					return bgUploadReturn1;
				} else { // 登录失败
					return bgUploadReturn1;
				}
			} else if(resultMessage == 212.0){//Token失效->刷新
				if(isDebug)
					Log.i(TAG, "212->Token过期:刷新Token");
				SharedPreferences sharedPreferences = context.getSharedPreferences("jiuan.sdk.author", 0);
				String refreshToken = sharedPreferences.getString("refreshToken", "");
				
				CommCloudSDK commCloudSDK = new CommCloudSDK(context);
				ReturnDataUser token_refresh = commCloudSDK.token_refresh(refreshToken, un, host);
				if("100".equals(token_refresh.getResultCode())){
					//保存新的accessToken, refreshToken到本地
					String accessToken = token_refresh.getAccessToken();
					//保存后重新调用上传
					if(isDebug)
						Log.i(TAG, "重新调用bg_upload");
					BG_ReturnData bpUploadReturn1 = bg_upload(un, accessToken, bgArr,host);
					if("100".equals(bpUploadReturn1.getResultMessage())){
						if(isDebug)
							Log.i(TAG, "刷新Token后上传BG数据成功!保存最新Token到本地");
						refreshToken = token_refresh.getRefreshToken();
						Editor localEditor = sharedPreferences.edit();
						localEditor.putString("accessToken", accessToken);
						localEditor.putString("refreshToken", refreshToken);
						localEditor.commit();
						return bpUploadReturn1;
					}else{
						return bpUploadReturn1;
					}
				}else{
					return bgUploadReturn;
				}
			} else if(resultMessage == 221.0){//Token失败->重新登录
				if(isDebug)
					Log.i(TAG, "221->Token验证失败->其他APP已刷新,需重新登录");
				BG_ReturnData regReturnData = new BG_ReturnData();
				try {
					CommCloudSDK commCloudSDK = new CommCloudSDK(context);
					ReturnDataUser userSign = commCloudSDK.UserSign(client_id, client_secret, un, inputHost);
					if("100".equals(userSign.getResultCode())){
						regReturnData.setResultMessage(userSign.getResultCode());
						regReturnData.setRegionHost(userSign.getRegionHost());
						regReturnData.setAccessToken(userSign.getAccessToken());
						regReturnData.setRefreshToken(userSign.getRefreshToken());
						regReturnData.setExpires(userSign.getExpires());
						if(isDebug)
							Log.i(TAG, "重新登录成功,重新调用bg_upload上传数据");
						String accessToken = regReturnData.getAccessToken();
						BG_ReturnData bgUploadReturn1 = bg_upload(un, accessToken, bgArr, inputHost);
						if("100".equals(bgUploadReturn1.getResultMessage())){
							if(isDebug)
								Log.i(TAG, "再次上传BG数据成功,保存最新Token信息到本地");
							SharedPreferences sharedPreferences = context.getSharedPreferences("jiuan.sdk.author", 0);
							Editor edit = sharedPreferences.edit();
							String refreshToken = regReturnData.getRefreshToken();
							edit.putString("accessToken", accessToken);
							edit.putString("refreshToken", refreshToken);
							edit.commit();
							return bgUploadReturn1;
						}else{
							return bgUploadReturn1;
						}
					}else{
						return regReturnData;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				return bgUploadReturn;
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return bgUploadReturn;
		}
		return bgUploadReturn;
	}

	private String getDeviceID() {
		TelephonyManager telephonyManager = (TelephonyManager) this.context.getSystemService(Context.TELEPHONY_SERVICE);
		return (telephonyManager.getDeviceId() != null) ? telephonyManager.getDeviceId() : getAppID();
	}

	private String getAppID() {
		String appID = "appID";
		AppIDFactory appIDFactory = new AppIDFactory(context);
		appID = appIDFactory.getAppID();
		return appID;
	}

}
