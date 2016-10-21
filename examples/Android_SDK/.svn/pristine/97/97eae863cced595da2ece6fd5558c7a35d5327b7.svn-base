package com.ihealth.communication.cloud.data;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
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
import com.ihealth.communication.cloud.tools.HttpPost;
import com.ihealth.communication.cloud.tools.HttpsPost;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.telephony.TelephonyManager;
import com.ihealth.communication.utils.Log;

public class AM_CommCloud {

	private static boolean isDebug = false;
	public static final String TAG = "AM_CommCloudData";

	Context context;
	private String QueueNum = "111111";

	public static final String SV_amsearch = "d33f5ba526e44b58ab84c6f29d00b716";
	public static final String SV_ambinding = "6089f6b908684656a84fd5ce449042bf";
	public static final String SV_amunbinding = "444f63ec37a843e497566855a0d45fec";

	private static final String SV_activity = "f655fca476104655b4b7a89cfde03661";
	private static final String SV_sleep = "d63856e6867d45a2b5d4a598e49cc161";
	private static final String SV_activity_report = "569f5746b8a840a490a4f7287ba822fa";
	private static final String SV_sleep_section = "3574ce171f834a109a572d0a3431025b";
	private static final String SV_workout = "9a1932f91aba409baafa9c091728ec8d";

	public static final String Sv_swimActivity_upload = "f845fc6716664a2aaf52e58b9aaf4881";
	public static final String Sv_swimReport_upload = "f845fc6716646a2aaf52e58b9aaf4881";

	public String messageReturn = "";
	public int result = 0;
	public long TS = 0;
	public float resultMessage = 0;
	public int queueNum = 0;

	private String un;
	private String host;
	private String client_id;
	private String client_secret;

	/**
	 * 构造函数
	 */
	public AM_CommCloud(Context context) {
		if (isDebug)
			Log.i(TAG, "实例化sdk_AuthorTools,获取本地配置 un host");
		this.context = context;
		un = context.getSharedPreferences("jiuan.sdk.author", 0).getString("email", "jiuan");
		host = context.getSharedPreferences(un + "userinfo", 0).getString("Host", "");//获取最优服务器
		if ("".equals(host)) {
			host = AppsDeviceParameters.webSite;
		}
		client_id = context.getSharedPreferences(un + "userinfo", 0).getString("client_id", "");
		client_secret = context.getSharedPreferences(un + "userinfo", 0).getString("client_secret", "");
		if (isDebug) {
			Log.i(TAG, "取得un = " + un);
			Log.i(TAG, "取得host = " + host);
		}

	}

	public class amSearchReturn {
		public String[] mac;
		public String iHealthID;

		amSearchReturn() {
			// mac = new String();
			iHealthID = new String();
		}
	}

	public amSearchReturn getAmsearch_return() {
		return amsearch_return;
	}

	public amSearchReturn amsearch_return;

	public int amsearch(String userName, String token, String host) throws Exception {

		this.amsearch_return = new amSearchReturn();
		// 编辑发送参数
		Map<String, String> amsearchParams = new HashMap<String, String>();
		amsearchParams.put("sc", AppsDeviceParameters.SC);
		amsearchParams.put("sv", SV_amsearch);
		amsearchParams.put("AppVersion", AppsDeviceParameters.APP_VERSION);
		amsearchParams.put("AppGuid", getAppID());
		amsearchParams.put("PhoneOS", android.os.Build.VERSION.RELEASE);
		amsearchParams.put("PhoneName", android.os.Build.MODEL);
		amsearchParams.put("PhoneID", getDeviceID());
		amsearchParams.put("PhoneLanguage", Locale.getDefault().getLanguage());
		amsearchParams.put("PhoneRegion", Locale.getDefault().getCountry());
		amsearchParams.put("QueueNum", "1");
		amsearchParams.put("Token", "");

		amsearchParams.put("Un", userName);
		amsearchParams.put("VerifyToken", token);

		String webAddress = host + AppsDeviceParameters.path + "amsearch.htm";

		try {
			if (AppsDeviceParameters.isOfficial) {
				HttpsPost ht = new HttpsPost(context);
				messageReturn = ht.requireClass(webAddress, amsearchParams, "UTF-8");
			} else {
				HttpPost ht = new HttpPost();
				messageReturn = ht.requireClass(webAddress, amsearchParams, "UTF-8");
			}

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


		// 分析收取的数据
		try {
			JSONTokener jsonTParser = new JSONTokener(messageReturn);
			JSONObject jsonAMSC = (JSONObject) jsonTParser.nextValue();
			this.result = jsonAMSC.getInt("Result");
			this.TS = Long.parseLong(jsonAMSC.getString("TS"));
			this.resultMessage = Integer.parseInt(jsonAMSC.getString("ResultMessage"));
			this.queueNum = jsonAMSC.getInt("QueueNum");

			if (resultMessage == 100) {
				JSONObject jsObAMsearchReturnValue = jsonAMSC.getJSONObject("ReturnValue");
				JSONArray jsObAMsearchData = jsObAMsearchReturnValue.getJSONArray("Data");
				amsearch_return.iHealthID = jsObAMsearchReturnValue.getString("iHealthID");
				int lenAMsearchOut = jsObAMsearchData.length();
				if (lenAMsearchOut != 0) {
					amsearch_return.mac = new String[lenAMsearchOut];
					for (int i = 0; i < lenAMsearchOut; i++) {
						JSONObject jsonAmsearchItemOb = jsObAMsearchData.optJSONObject(i);
						if (jsonAmsearchItemOb == null) {
							Log.i("返回AMsearchArr 条目 ", "jsonAmscItemOb == null");
						} else {
							String mac = jsonAmsearchItemOb.getString("mac");
							if (mac.equals("0")) {
								mac = "";
							}
							amsearch_return.mac[i] = mac;
							Log.v(TAG, "mac====" + amsearch_return.mac[i]);
						}
					}
				} else {
					return 999;
				}

				// 此处仅取第一位mac地址， 因为 AM目前是1V1对应
				if (amsearch_return.mac[0] != null) {
					Log.e(TAG, "return true");
					return 100;
				} else {
					Log.e(TAG, "return false");
					return 999;
				}

			}  else {
				Log.e(TAG, "return false");
				return (int) resultMessage;
			}
		} catch (JSONException e) {
			//登录流程监控9-调用amsearch接口获取AM-MAC-JSON异常
			return 999;
		}
	}

	public class ambindingReturn {
		int Status;
		String iHealthID;

		ambindingReturn() {
			Status = 0;
			iHealthID = new String();
		}
	}

	ambindingReturn ambinding_return;

	public int ambinding(String userName, String mac, String token, String host) throws Exception {

		this.ambinding_return = new ambindingReturn();

		// 编辑发送参数
		Map<String, String> ambindingParams = new HashMap<String, String>();
		ambindingParams.put("sc", AppsDeviceParameters.SC);
		ambindingParams.put("sv", SV_ambinding);
		ambindingParams.put("AppVersion", AppsDeviceParameters.APP_VERSION);
		ambindingParams.put("AppGuid", getAppID());
		ambindingParams.put("PhoneOS", "android" + android.os.Build.VERSION.RELEASE);
		ambindingParams.put("PhoneName", android.os.Build.MODEL);
		ambindingParams.put("PhoneID", getDeviceID());
		ambindingParams.put("PhoneLanguage", Locale.getDefault().getLanguage());
		ambindingParams.put("PhoneRegion", Locale.getDefault().getCountry());
		ambindingParams.put("QueueNum", "1");
		ambindingParams.put("Token", "");

		JSONArray jsonArray = new JSONArray();
		JSONObject jso = new JSONObject();
		jso.put("mac", mac);
		jsonArray.put(jso);

		ambindingParams.put("Un", userName);
		ambindingParams.put("VerifyToken", token);
		ambindingParams.put("UploadData", jsonArray.toString());

		String webAddress = host + AppsDeviceParameters.path + "ambinding.htm";

		try {
			if (AppsDeviceParameters.isOfficial) {
				HttpsPost ht = new HttpsPost(context);
				messageReturn = ht.requireClass(webAddress, ambindingParams, "UTF-8");
			} else {
				HttpPost ht = new HttpPost();
				messageReturn = ht.requireClass(webAddress, ambindingParams, "UTF-8");
			}

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


		// 分析收取的数据
		try {
			JSONTokener jsonTParser = new JSONTokener(messageReturn);
			JSONObject jsonAMSC = (JSONObject) jsonTParser.nextValue();
			this.result = jsonAMSC.getInt("Result");
			this.TS = Long.parseLong(jsonAMSC.getString("TS"));
			this.resultMessage = Integer.parseInt(jsonAMSC.getString("ResultMessage"));
			this.queueNum = jsonAMSC.getInt("QueueNum");

			if (resultMessage == 100) {
				JSONTokener jsonTokenerData = new JSONTokener(jsonAMSC.getString("ReturnValue"));
				JSONObject jsonAmsclOut = (JSONObject) jsonTokenerData.nextValue();

				this.ambinding_return.Status = jsonAmsclOut.getInt("Status");
				this.ambinding_return.iHealthID = jsonAmsclOut.getString("iHealthID");

				if (this.ambinding_return.Status == 1) {
					Log.e(TAG, "return true");
					return 100;
				} else {
					Log.e(TAG, "return false");
					return 999;
				}
			} else {
				Log.e(TAG, "return false");
				return (int) resultMessage;
			}
		} catch (JSONException e) {
			e.printStackTrace();
			Log.e(TAG, "return false");
			return 999;
		}
	}

	ambindingReturn amunbinding_return;

	public int amunbinding(String userName, String mac, String token, String host) throws Exception {

		this.amunbinding_return = new ambindingReturn();

		// 编辑发送参数
		Map<String, String> amunbindingParams = new HashMap<String, String>();
		amunbindingParams.put("sc", AppsDeviceParameters.SC);
		amunbindingParams.put("sv", SV_amunbinding);
		amunbindingParams.put("AppVersion", AppsDeviceParameters.APP_VERSION);
		amunbindingParams.put("AppGuid", getAppID());
		amunbindingParams.put("PhoneOS", android.os.Build.VERSION.RELEASE);
		amunbindingParams.put("PhoneName", android.os.Build.MODEL);
		amunbindingParams.put("PhoneID", getDeviceID());
		amunbindingParams.put("PhoneLanguage", Locale.getDefault().getLanguage());
		amunbindingParams.put("PhoneRegion", Locale.getDefault().getCountry());
		amunbindingParams.put("QueueNum", "1");
		amunbindingParams.put("Token", "");

		JSONArray jsonArray = new JSONArray();
		JSONObject jso = new JSONObject();
		jso.put("mac", mac);
		jsonArray.put(jso);

		amunbindingParams.put("Un", userName);
		amunbindingParams.put("VerifyToken", token);
		amunbindingParams.put("UploadData", jsonArray.toString());

		String webAddress = host + AppsDeviceParameters.path + "amunbinding.htm";
		try {
			if (AppsDeviceParameters.isOfficial) {
				HttpsPost ht = new HttpsPost(context);
				messageReturn = ht.requireClass(webAddress, amunbindingParams, "UTF-8");
			} else {
				HttpPost ht = new HttpPost();
				messageReturn = ht.requireClass(webAddress, amunbindingParams, "UTF-8");
			}

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


		// 分析收取的数据
		try {
			JSONTokener jsonTParser = new JSONTokener(messageReturn);
			JSONObject jsonAMSC = (JSONObject) jsonTParser.nextValue();
			this.result = jsonAMSC.getInt("Result");
			this.TS = Long.parseLong(jsonAMSC.getString("TS"));
			this.resultMessage = Integer.parseInt(jsonAMSC.getString("ResultMessage"));
			this.queueNum = jsonAMSC.getInt("QueueNum");

			if (resultMessage == 100) {
				JSONTokener jsonTokenerData = new JSONTokener(jsonAMSC.getString("ReturnValue"));
				JSONObject jsonAmsclOut = (JSONObject) jsonTokenerData.nextValue();

				this.amunbinding_return.Status = jsonAmsclOut.getInt("Status");
				this.amunbinding_return.iHealthID = jsonAmsclOut.getString("iHealthID");

				if (this.amunbinding_return.Status == 1) {
					Log.e(TAG, "return true");
					return 100;
				} else {
					Log.e(TAG, "return false");
					return 999;
				}
			}  else {
				Log.e(TAG, "return false");
				return (int) resultMessage;
			}
		} catch (JSONException e) {
			e.printStackTrace();
			Log.e(TAG, "return false");
			return 999;
		}
	}

	/**
	 * 运动数据上传
	 *
	 * @param userName    用户名
	 * @param VerifyToken Token
	 * @param hsArr       数据
	 * @param inputHost   传入的host  当前最适的Host
	 * @throws ConnectTimeoutException
	 * @throws SocketTimeoutException
	 * @return Data 接口结果类
	 */
	public AM_ReturnData am_activity_upload(String userName, String VerifyToken, ArrayList<Data_AM_Activity> hsArr, String inputHost
	) throws ConnectTimeoutException, SocketTimeoutException {

		AM_ReturnData hsUploadReturn = new AM_ReturnData();
		// 编辑发送参数
		Map<String, String> activity_uploadParams = new HashMap<String, String>();
		activity_uploadParams.put("sc", AppsDeviceParameters.SC);
		activity_uploadParams.put("sv", SV_activity);
		activity_uploadParams.put("AppVersion", AppsDeviceParameters.APP_VERSION);
		activity_uploadParams.put("AppGuid", getAppID());
		activity_uploadParams.put("PhoneOS", android.os.Build.VERSION.RELEASE);
		activity_uploadParams.put("PhoneName", android.os.Build.MODEL);
		activity_uploadParams.put("PhoneID", getDeviceID());
		activity_uploadParams.put("PhoneLanguage", Locale.getDefault().getLanguage());
		activity_uploadParams.put("PhoneRegion", Locale.getDefault().getCountry());
		activity_uploadParams.put("QueueNum", "1");
		activity_uploadParams.put("Token", "");

		// 编辑参数UploadData
		JSONArray jsonWTUArr = new JSONArray();
		try {
			JSONObject jsonWTUData;
			if (isDebug)
				Log.i(TAG, hsArr.size() + "");
			for (int i = 0; i < hsArr.size(); i++) {
				jsonWTUData = new JSONObject();

				jsonWTUData.put("ChangeType", hsArr.get(i).getChangeType());
				jsonWTUData.put("LastChangeTime", hsArr.get(i).getLastChangeTime());
				jsonWTUData.put("PhoneDataID", hsArr.get(i).getPhoneDataID());
				jsonWTUData.put("PhoneCreateTime", hsArr.get(i).getPhoneCreateTime());
				jsonWTUData.put("Lat", hsArr.get(i).getLat());
				jsonWTUData.put("Lon", hsArr.get(i).getLon());
				jsonWTUData.put("TimeZone", hsArr.get(i).getTimeZone());

				jsonWTUData.put("Calories", hsArr.get(i).getCalorie());
				jsonWTUData.put("StepLength", hsArr.get(i).getStepLength());
				jsonWTUData.put("Steps", hsArr.get(i).getSteps());
				jsonWTUData.put("SunCalories", hsArr.get(i).getSumCalorie());
				jsonWTUData.put("SunSteps", hsArr.get(i).getSumSteps());

				jsonWTUData.put("MeasureTime", hsArr.get(i).getMeasureTime());
				jsonWTUData.put("MechineType", hsArr.get(i).getMechineType());
				jsonWTUData.put("MechineDeviceID", hsArr.get(i).getMechineDeviceID());

				jsonWTUArr.put(i, jsonWTUData);
			}
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		activity_uploadParams.put("Un", userName);
		activity_uploadParams.put("VerifyToken", VerifyToken);
		activity_uploadParams.put("UploadData", jsonWTUArr.toString());

		String webAddress = inputHost + AppsDeviceParameters.path + "amsport_upload.htm";//流程中调用接口
		if (isDebug)
			Log.i(TAG, "数据上传 = " + activity_uploadParams.toString());
		HttpsPost ht = new HttpsPost(context);

		try {
			messageReturn = ht.requireClass(webAddress, activity_uploadParams, "UTF-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (messageReturn.length() == 0) {
			return hsUploadReturn;
		}
		try {
			JSONTokener jsonTParser = new JSONTokener(messageReturn);
			JSONObject jsonORegist = (JSONObject) jsonTParser.nextValue();

			this.result = jsonORegist.getInt("Result");
			this.TS = Long.parseLong(jsonORegist.getString("TS"));
			this.resultMessage = Float.parseFloat(jsonORegist.getString("ResultMessage"));
			this.queueNum = jsonORegist.getInt("QueueNum");

			hsUploadReturn.setResultMessage(jsonORegist.getString("ResultMessage"));

			if (isDebug) Log.i(TAG, "上传返回 = " + resultMessage);

			if (resultMessage == 100.0) {
				return hsUploadReturn;
			} else if (resultMessage == 208.0) {//服务器地址不正确
				JSONTokener jsonTokener = new JSONTokener(jsonORegist.getString("ReturnValue"));
				JSONObject jsonWebListOut = (JSONObject) jsonTokener.nextValue();
				String regionHost = jsonWebListOut.getString("RegionHost");

				if (isDebug) Log.i(TAG, "hs_upload返回208,拿到regionHost = " + regionHost);

				AM_ReturnData hsUploadReturn1 = am_activity_upload(un, VerifyToken, hsArr, regionHost);
				if ("100".equals(hsUploadReturn1.getResultMessage())) {// 登录成功

					// 保存最新的信息
					UserCheckSDK.saveUserInfo(context, null, null, regionHost, null, null, null, null, -1);

					if (isDebug) Log.i(TAG, "保存regionHost到本地");

					host = regionHost;
					return hsUploadReturn1;
				} else { // 登录失败
					return hsUploadReturn1;
				}
			} else if (resultMessage == 212.0) {//Token失效->刷新

				if (isDebug) Log.i(TAG, "212->Token过期:刷新Token");

				//取得refreshToken
				SharedPreferences sharedPreferences = context.getSharedPreferences("jiuan.sdk.author", 0);
				String refreshToken = sharedPreferences.getString("refreshToken", "");

				//执行刷新
				CommCloudSDK commCloudSDK = new CommCloudSDK(context);
				ReturnDataUser token_refresh = commCloudSDK.token_refresh(refreshToken, un, host);

				if ("100".equals(token_refresh.getResultCode())) {//成功

					//保存后重新调用上传
					if (isDebug) Log.i(TAG, "重新调用hs_upload");
					String accessToken = token_refresh.getAccessToken();
					AM_ReturnData bpUploadReturn1 = am_activity_upload(un, accessToken, hsArr, host);

					if ("100".equals(bpUploadReturn1.getResultMessage())) {

						if (isDebug) Log.i(TAG, "刷新Token后上传HS数据成功!保存最新Token到本地");
						//保存新的accessToken, refreshToken到本地
						//保存到jiuan.sdk.author
						refreshToken = token_refresh.getRefreshToken();
						Editor localEditor = sharedPreferences.edit();
						localEditor.putString("accessToken", accessToken);
						localEditor.putString("refreshToken", refreshToken);
						localEditor.commit();

						// 保存最新的信息-un+"userinfo"
						UserCheckSDK.saveUserInfo(context, null, null, null, accessToken, refreshToken, null, null, -1);

						return bpUploadReturn1;
					} else {
						return bpUploadReturn1;
					}
				} else {
					return hsUploadReturn;
				}
			} else if (resultMessage == 221.0) {//Token失败->重新登录
				if (isDebug)
					Log.i(TAG, "221->Token验证失败->其他APP已刷新,需重新登录");
				AM_ReturnData regReturnData = new AM_ReturnData();
				try {
					CommCloudSDK commCloudSDK = new CommCloudSDK(context);
					ReturnDataUser userSign = commCloudSDK.UserSign(client_id, client_secret, un, inputHost);
					if ("100".equals(userSign.getResultCode())) {
						regReturnData.setResultMessage(userSign.getResultCode());
						regReturnData.setRegionHost(userSign.getRegionHost());
						regReturnData.setAccessToken(userSign.getAccessToken());
						regReturnData.setRefreshToken(userSign.getRefreshToken());
						regReturnData.setExpires(userSign.getExpires());
						if (isDebug)
							Log.i(TAG, "重新登录成功,重新调用hs_upload上传数据");
						String accessToken = regReturnData.getAccessToken();
						AM_ReturnData hsUploadReturn1 = am_activity_upload(un, accessToken, hsArr, inputHost);
						if ("100".equals(hsUploadReturn1.getResultMessage())) {
							if (isDebug)
								Log.i(TAG, "再次上HS数据成功,保存最新Token信息到本地");

							//保存新的accessToken, refreshToken到本地
							//保存到jiuan.sdk.author
							SharedPreferences sharedPreferences = context.getSharedPreferences("jiuan.sdk.author", 0);
							Editor edit = sharedPreferences.edit();
							String refreshToken = regReturnData.getRefreshToken();
							edit.putString("accessToken", accessToken);
							edit.putString("refreshToken", refreshToken);
							edit.commit();

							// 保存最新的信息-un+"userinfo"
							UserCheckSDK.saveUserInfo(context, null, null, null, accessToken, refreshToken, null, null, -1);

							return hsUploadReturn1;
						} else {
							return hsUploadReturn1;
						}
					} else {
						return regReturnData;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				return hsUploadReturn;
			}
		} catch (JSONException e) {
			return hsUploadReturn;
		}
		return hsUploadReturn;
	}

	/**
	 * 运动report上传
	 *
	 * @param userName    用户名
	 * @param VerifyToken Token
	 * @param hsArr       数据
	 * @param inputHost   传入的host  当前最适的Host
	 * @throws ConnectTimeoutException
	 * @throws SocketTimeoutException
	 * @return Data 接口结果类
	 */
	public AM_ReturnData am_report_upload(String userName, String VerifyToken, ArrayList<Data_AM_ActivityDayReport> hsArr, String inputHost
	) throws ConnectTimeoutException, SocketTimeoutException {

		AM_ReturnData hsUploadReturn = new AM_ReturnData();
		// 编辑发送参数
		Map<String, String> report_uploadParams = new HashMap<String, String>();
		report_uploadParams.put("sc", AppsDeviceParameters.SC);
		report_uploadParams.put("sv", SV_activity_report);
		report_uploadParams.put("AppVersion", AppsDeviceParameters.APP_VERSION);
		report_uploadParams.put("AppGuid", getAppID());
		report_uploadParams.put("PhoneOS", android.os.Build.VERSION.RELEASE);
		report_uploadParams.put("PhoneName", android.os.Build.MODEL);
		report_uploadParams.put("PhoneID", getDeviceID());
		report_uploadParams.put("PhoneLanguage", Locale.getDefault().getLanguage());
		report_uploadParams.put("PhoneRegion", Locale.getDefault().getCountry());
		report_uploadParams.put("QueueNum", "1");
		report_uploadParams.put("Token", "");

		// 编辑参数UploadData
		JSONArray jsonWTUArr = new JSONArray();
		try {
			JSONObject jsonWTUData;
			if (isDebug)
				Log.i(TAG, hsArr.size() + "");
			for (int i = 0; i < hsArr.size(); i++) {
				jsonWTUData = new JSONObject();

				jsonWTUData.put("ChangeType", hsArr.get(i).getChangeType());
				jsonWTUData.put("LastChangeTime", hsArr.get(i).getLastChangeTime());
				jsonWTUData.put("PhoneDataID", hsArr.get(i).getPhoneDataID());
				jsonWTUData.put("PhoneCreateTime", hsArr.get(i).getPhoneCreateTime());
				jsonWTUData.put("Lat", hsArr.get(i).getLat());
				jsonWTUData.put("Lon", hsArr.get(i).getLon());
				jsonWTUData.put("TimeZone", hsArr.get(i).getTimeZone());

				jsonWTUData.put("Calories", hsArr.get(i).getCalorie());
				jsonWTUData.put("StepLength", hsArr.get(i).getStepLength());
				jsonWTUData.put("Steps", hsArr.get(i).getSteps());
				jsonWTUData.put("PlanCalories", hsArr.get(i).getPlanCalorie());
				jsonWTUData.put("PlanSteps", hsArr.get(i).getPlanSteps());

				jsonWTUData.put("City", hsArr.get(i).getCity());
				jsonWTUData.put("Weather", hsArr.get(i).getWeather());
				jsonWTUData.put("Comment", hsArr.get(i).getComment());

				jsonWTUData.put("MeasureTime", hsArr.get(i).getMeasureTime());
				jsonWTUData.put("MechineType", hsArr.get(i).getMechineType());
				jsonWTUData.put("MechineDeviceID", hsArr.get(i).getMechineDeviceID());

				jsonWTUData.put("Mood", 1);
				jsonWTUData.put("Activity", 1);
				jsonWTUData.put("OutDoorRatio", 50);

				jsonWTUArr.put(i, jsonWTUData);
			}
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		report_uploadParams.put("Un", userName);
		report_uploadParams.put("VerifyToken", VerifyToken);
		report_uploadParams.put("UploadData", jsonWTUArr.toString());

		String webAddress = inputHost + AppsDeviceParameters.path + "amsportdaily_upload.htm";//流程中调用接口
		if (isDebug)
			Log.i(TAG, "数据上传 = " + report_uploadParams.toString());
		HttpsPost ht = new HttpsPost(context);

		try {
			messageReturn = ht.requireClass(webAddress, report_uploadParams, "UTF-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (messageReturn.length() == 0) {
			return hsUploadReturn;
		}
		try {
			JSONTokener jsonTParser = new JSONTokener(messageReturn);
			JSONObject jsonORegist = (JSONObject) jsonTParser.nextValue();

			this.result = jsonORegist.getInt("Result");
			this.TS = Long.parseLong(jsonORegist.getString("TS"));
			this.resultMessage = Float.parseFloat(jsonORegist.getString("ResultMessage"));
			this.queueNum = jsonORegist.getInt("QueueNum");

			hsUploadReturn.setResultMessage(jsonORegist.getString("ResultMessage"));

			if (isDebug) Log.i(TAG, "上传返回 = " + resultMessage);

			if (resultMessage == 100.0) {
				return hsUploadReturn;
			} else if (resultMessage == 208.0) {//服务器地址不正确
				JSONTokener jsonTokener = new JSONTokener(jsonORegist.getString("ReturnValue"));
				JSONObject jsonWebListOut = (JSONObject) jsonTokener.nextValue();
				String regionHost = jsonWebListOut.getString("RegionHost");

				if (isDebug) Log.i(TAG, "hs_upload返回208,拿到regionHost = " + regionHost);

				AM_ReturnData hsUploadReturn1 = am_report_upload(un, VerifyToken, hsArr, regionHost);
				if ("100".equals(hsUploadReturn1.getResultMessage())) {// 登录成功

					// 保存最新的信息
					UserCheckSDK.saveUserInfo(context, null, null, regionHost, null, null, null, null, -1);

					if (isDebug) Log.i(TAG, "保存regionHost到本地");

					host = regionHost;
					return hsUploadReturn1;
				} else { // 登录失败
					return hsUploadReturn1;
				}
			} else if (resultMessage == 212.0) {//Token失效->刷新

				if (isDebug) Log.i(TAG, "212->Token过期:刷新Token");

				//取得refreshToken
				SharedPreferences sharedPreferences = context.getSharedPreferences("jiuan.sdk.author", 0);
				String refreshToken = sharedPreferences.getString("refreshToken", "");

				//执行刷新
				CommCloudSDK commCloudSDK = new CommCloudSDK(context);
				ReturnDataUser token_refresh = commCloudSDK.token_refresh(refreshToken, un, host);

				if ("100".equals(token_refresh.getResultCode())) {//成功

					//保存后重新调用上传
					if (isDebug) Log.i(TAG, "重新调用hs_upload");
					String accessToken = token_refresh.getAccessToken();
					AM_ReturnData bpUploadReturn1 = am_report_upload(un, accessToken, hsArr, host);

					if ("100".equals(bpUploadReturn1.getResultMessage())) {

						if (isDebug) Log.i(TAG, "刷新Token后上传HS数据成功!保存最新Token到本地");
						//保存新的accessToken, refreshToken到本地
						//保存到jiuan.sdk.author
						refreshToken = token_refresh.getRefreshToken();
						Editor localEditor = sharedPreferences.edit();
						localEditor.putString("accessToken", accessToken);
						localEditor.putString("refreshToken", refreshToken);
						localEditor.commit();

						// 保存最新的信息-un+"userinfo"
						UserCheckSDK.saveUserInfo(context, null, null, null, accessToken, refreshToken, null, null, -1);

						return bpUploadReturn1;
					} else {
						return bpUploadReturn1;
					}
				} else {
					return hsUploadReturn;
				}
			} else if (resultMessage == 221.0) {//Token失败->重新登录
				if (isDebug)
					Log.i(TAG, "221->Token验证失败->其他APP已刷新,需重新登录");
				AM_ReturnData regReturnData = new AM_ReturnData();
				try {
					CommCloudSDK commCloudSDK = new CommCloudSDK(context);
					ReturnDataUser userSign = commCloudSDK.UserSign(client_id, client_secret, un, inputHost);
					if ("100".equals(userSign.getResultCode())) {
						regReturnData.setResultMessage(userSign.getResultCode());
						regReturnData.setRegionHost(userSign.getRegionHost());
						regReturnData.setAccessToken(userSign.getAccessToken());
						regReturnData.setRefreshToken(userSign.getRefreshToken());
						regReturnData.setExpires(userSign.getExpires());
						if (isDebug)
							Log.i(TAG, "重新登录成功,重新调用hs_upload上传数据");
						String accessToken = regReturnData.getAccessToken();
						AM_ReturnData hsUploadReturn1 = am_report_upload(un, accessToken, hsArr, inputHost);
						if ("100".equals(hsUploadReturn1.getResultMessage())) {
							if (isDebug)
								Log.i(TAG, "再次上HS数据成功,保存最新Token信息到本地");

							//保存新的accessToken, refreshToken到本地
							//保存到jiuan.sdk.author
							SharedPreferences sharedPreferences = context.getSharedPreferences("jiuan.sdk.author", 0);
							Editor edit = sharedPreferences.edit();
							String refreshToken = regReturnData.getRefreshToken();
							edit.putString("accessToken", accessToken);
							edit.putString("refreshToken", refreshToken);
							edit.commit();

							// 保存最新的信息-un+"userinfo"
							UserCheckSDK.saveUserInfo(context, null, null, null, accessToken, refreshToken, null, null, -1);

							return hsUploadReturn1;
						} else {
							return hsUploadReturn1;
						}
					} else {
						return regReturnData;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				return hsUploadReturn;
			}
		} catch (JSONException e) {
			return hsUploadReturn;
		}
		return hsUploadReturn;
	}

	/**
	 * 睡眠数据上传
	 *
	 * @param userName    用户名
	 * @param VerifyToken Token
	 * @param hsArr       数据
	 * @param inputHost   传入的host  当前最适的Host
	 * @throws ConnectTimeoutException
	 * @throws SocketTimeoutException
	 * @return Data 接口结果类
	 */
	public AM_ReturnData am_sleep_upload(String userName, String VerifyToken, ArrayList<Data_AM_Sleep> hsArr, String inputHost
	) throws ConnectTimeoutException, SocketTimeoutException {

		AM_ReturnData hsUploadReturn = new AM_ReturnData();
		// 编辑发送参数
		Map<String, String> sleep_uploadParams = new HashMap<String, String>();
		sleep_uploadParams.put("sc", AppsDeviceParameters.SC);
		sleep_uploadParams.put("sv", SV_sleep);
		sleep_uploadParams.put("AppVersion", AppsDeviceParameters.APP_VERSION);
		sleep_uploadParams.put("AppGuid", getAppID());
		sleep_uploadParams.put("PhoneOS", android.os.Build.VERSION.RELEASE);
		sleep_uploadParams.put("PhoneName", android.os.Build.MODEL);
		sleep_uploadParams.put("PhoneID", getDeviceID());
		sleep_uploadParams.put("PhoneLanguage", Locale.getDefault().getLanguage());
		sleep_uploadParams.put("PhoneRegion", Locale.getDefault().getCountry());
		sleep_uploadParams.put("QueueNum", "1");
		sleep_uploadParams.put("Token", "");

		// 编辑参数UploadData
		JSONArray jsonWTUArr = new JSONArray();
		try {
			JSONObject jsonWTUData;
			if (isDebug)
				Log.i(TAG, hsArr.size() + "");
			for (int i = 0; i < hsArr.size(); i++) {
				jsonWTUData = new JSONObject();

				jsonWTUData.put("ChangeType", hsArr.get(i).getChangeType());
				jsonWTUData.put("LastChangeTime", hsArr.get(i).getLastChangeTime());
				jsonWTUData.put("PhoneDataID", hsArr.get(i).getPhoneDataID());
				jsonWTUData.put("PhoneCreateTime", hsArr.get(i).getPhoneCreateTime());
				jsonWTUData.put("Lat", hsArr.get(i).getLat());
				jsonWTUData.put("Lon", hsArr.get(i).getLon());
				jsonWTUData.put("TimeZone", hsArr.get(i).getTimeZone());

				jsonWTUData.put("SleepLevel", hsArr.get(i).getSleepLevel());
				jsonWTUData.put("TimeSectionID", hsArr.get(i).getTimeSectionId());

				jsonWTUData.put("MeasureTime", hsArr.get(i).getMeasureTime());
				jsonWTUData.put("MechineType", hsArr.get(i).getMechineType());
				jsonWTUData.put("MechineDeviceID", hsArr.get(i).getMechineDeviceID());

				jsonWTUArr.put(i, jsonWTUData);
			}
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		sleep_uploadParams.put("Un", userName);
		sleep_uploadParams.put("VerifyToken", VerifyToken);
		sleep_uploadParams.put("UploadData", jsonWTUArr.toString());

		String webAddress = inputHost + AppsDeviceParameters.path + "amsleep_upload.htm";//流程中调用接口
		if (isDebug)
			Log.i(TAG, "数据上传 = " + sleep_uploadParams.toString());
		HttpsPost ht = new HttpsPost(context);

		try {
			messageReturn = ht.requireClass(webAddress, sleep_uploadParams, "UTF-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (messageReturn.length() == 0) {
			return hsUploadReturn;
		}
		try {
			JSONTokener jsonTParser = new JSONTokener(messageReturn);
			JSONObject jsonORegist = (JSONObject) jsonTParser.nextValue();

			this.result = jsonORegist.getInt("Result");
			this.TS = Long.parseLong(jsonORegist.getString("TS"));
			this.resultMessage = Float.parseFloat(jsonORegist.getString("ResultMessage"));
			this.queueNum = jsonORegist.getInt("QueueNum");

			hsUploadReturn.setResultMessage(jsonORegist.getString("ResultMessage"));

			if (isDebug) Log.i(TAG, "上传返回 = " + resultMessage);

			if (resultMessage == 100.0) {
				return hsUploadReturn;
			} else if (resultMessage == 208.0) {//服务器地址不正确
				JSONTokener jsonTokener = new JSONTokener(jsonORegist.getString("ReturnValue"));
				JSONObject jsonWebListOut = (JSONObject) jsonTokener.nextValue();
				String regionHost = jsonWebListOut.getString("RegionHost");

				if (isDebug) Log.i(TAG, "hs_upload返回208,拿到regionHost = " + regionHost);

				AM_ReturnData hsUploadReturn1 = am_sleep_upload(un, VerifyToken, hsArr, regionHost);
				if ("100".equals(hsUploadReturn1.getResultMessage())) {// 登录成功

					// 保存最新的信息
					UserCheckSDK.saveUserInfo(context, null, null, regionHost, null, null, null, null, -1);

					if (isDebug) Log.i(TAG, "保存regionHost到本地");

					host = regionHost;
					return hsUploadReturn1;
				} else { // 登录失败
					return hsUploadReturn1;
				}
			} else if (resultMessage == 212.0) {//Token失效->刷新

				if (isDebug) Log.i(TAG, "212->Token过期:刷新Token");

				//取得refreshToken
				SharedPreferences sharedPreferences = context.getSharedPreferences("jiuan.sdk.author", 0);
				String refreshToken = sharedPreferences.getString("refreshToken", "");

				//执行刷新
				CommCloudSDK commCloudSDK = new CommCloudSDK(context);
				ReturnDataUser token_refresh = commCloudSDK.token_refresh(refreshToken, un, host);

				if ("100".equals(token_refresh.getResultCode())) {//成功

					//保存后重新调用上传
					if (isDebug) Log.i(TAG, "重新调用hs_upload");
					String accessToken = token_refresh.getAccessToken();
					AM_ReturnData bpUploadReturn1 = am_sleep_upload(un, accessToken, hsArr, host);

					if ("100".equals(bpUploadReturn1.getResultMessage())) {

						if (isDebug) Log.i(TAG, "刷新Token后上传HS数据成功!保存最新Token到本地");
						//保存新的accessToken, refreshToken到本地
						//保存到jiuan.sdk.author
						refreshToken = token_refresh.getRefreshToken();
						Editor localEditor = sharedPreferences.edit();
						localEditor.putString("accessToken", accessToken);
						localEditor.putString("refreshToken", refreshToken);
						localEditor.commit();

						// 保存最新的信息-un+"userinfo"
						UserCheckSDK.saveUserInfo(context, null, null, null, accessToken, refreshToken, null, null, -1);

						return bpUploadReturn1;
					} else {
						return bpUploadReturn1;
					}
				} else {
					return hsUploadReturn;
				}
			} else if (resultMessage == 221.0) {//Token失败->重新登录
				if (isDebug)
					Log.i(TAG, "221->Token验证失败->其他APP已刷新,需重新登录");
				AM_ReturnData regReturnData = new AM_ReturnData();
				try {
					CommCloudSDK commCloudSDK = new CommCloudSDK(context);
					ReturnDataUser userSign = commCloudSDK.UserSign(client_id, client_secret, un, inputHost);
					if ("100".equals(userSign.getResultCode())) {
						regReturnData.setResultMessage(userSign.getResultCode());
						regReturnData.setRegionHost(userSign.getRegionHost());
						regReturnData.setAccessToken(userSign.getAccessToken());
						regReturnData.setRefreshToken(userSign.getRefreshToken());
						regReturnData.setExpires(userSign.getExpires());
						if (isDebug)
							Log.i(TAG, "重新登录成功,重新调用hs_upload上传数据");
						String accessToken = regReturnData.getAccessToken();
						AM_ReturnData hsUploadReturn1 = am_sleep_upload(un, accessToken, hsArr, inputHost);
						if ("100".equals(hsUploadReturn1.getResultMessage())) {
							if (isDebug)
								Log.i(TAG, "再次上HS数据成功,保存最新Token信息到本地");

							//保存新的accessToken, refreshToken到本地
							//保存到jiuan.sdk.author
							SharedPreferences sharedPreferences = context.getSharedPreferences("jiuan.sdk.author", 0);
							Editor edit = sharedPreferences.edit();
							String refreshToken = regReturnData.getRefreshToken();
							edit.putString("accessToken", accessToken);
							edit.putString("refreshToken", refreshToken);
							edit.commit();

							// 保存最新的信息-un+"userinfo"
							UserCheckSDK.saveUserInfo(context, null, null, null, accessToken, refreshToken, null, null, -1);

							return hsUploadReturn1;
						} else {
							return hsUploadReturn1;
						}
					} else {
						return regReturnData;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				return hsUploadReturn;
			}
		} catch (JSONException e) {
			return hsUploadReturn;
		}
		return hsUploadReturn;
	}

	/**
	 * 运动report上传
	 *
	 * @param userName    用户名
	 * @param VerifyToken Token
	 * @param hsArr       数据
	 * @param inputHost   传入的host  当前最适的Host
	 * @throws ConnectTimeoutException
	 * @throws SocketTimeoutException
	 * @return Data 接口结果类
	 */
	public AM_ReturnData am_section_upload(String userName, String VerifyToken, ArrayList<Data_AM_SleepSectionReport> hsArr, String inputHost
	) throws ConnectTimeoutException, SocketTimeoutException {

		AM_ReturnData hsUploadReturn = new AM_ReturnData();
		// 编辑发送参数
		Map<String, String> section_uploadParams = new HashMap<String, String>();
		section_uploadParams.put("sc", AppsDeviceParameters.SC);
		section_uploadParams.put("sv", SV_sleep_section);
		section_uploadParams.put("AppVersion", AppsDeviceParameters.APP_VERSION);
		section_uploadParams.put("AppGuid", getAppID());
		section_uploadParams.put("PhoneOS", android.os.Build.VERSION.RELEASE);
		section_uploadParams.put("PhoneName", android.os.Build.MODEL);
		section_uploadParams.put("PhoneID", getDeviceID());
		section_uploadParams.put("PhoneLanguage", Locale.getDefault().getLanguage());
		section_uploadParams.put("PhoneRegion", Locale.getDefault().getCountry());
		section_uploadParams.put("QueueNum", "1");
		section_uploadParams.put("Token", "");

		// 编辑参数UploadData
		JSONArray jsonWTUArr = new JSONArray();
		try {
			JSONObject jsonWTUData;
			if (isDebug)
				Log.i(TAG, hsArr.size() + "");
			for (int i = 0; i < hsArr.size(); i++) {
				jsonWTUData = new JSONObject();

				jsonWTUData.put("ChangeType", hsArr.get(i).getChangeType());
				jsonWTUData.put("LastChangeTime", hsArr.get(i).getLastChangeTime());
				jsonWTUData.put("PhoneDataID", hsArr.get(i).getPhoneDataID());
				jsonWTUData.put("PhoneCreateTime", hsArr.get(i).getPhoneCreateTime());
				jsonWTUData.put("Lat", hsArr.get(i).getLat());
				jsonWTUData.put("Lon", hsArr.get(i).getLon());
				jsonWTUData.put("TimeZone", hsArr.get(i).getTimeZone());

				jsonWTUData.put("Awake", hsArr.get(i).getAwake());
				jsonWTUData.put("DeepSleep", hsArr.get(i).getDeepSleep());
				jsonWTUData.put("FallSleep", hsArr.get(i).getFallSleep());
				jsonWTUData.put("Sleep", hsArr.get(i).getSleep());
				jsonWTUData.put("AwakenTimes", hsArr.get(i).getAwakenTimes());

				jsonWTUData.put("SleepStartTime", hsArr.get(i).getSleepStartTime());
				jsonWTUData.put("SleepEndTime", hsArr.get(i).getSleepEndTime());
				jsonWTUData.put("TimeSectionID", hsArr.get(i).getTimeSectionId());

				jsonWTUData.put("City", hsArr.get(i).getCity());
				jsonWTUData.put("Weather", hsArr.get(i).getWeather());
				jsonWTUData.put("Comment", hsArr.get(i).getComment());

				jsonWTUData.put("Nap", hsArr.get(i).getNap());
				jsonWTUData.put("Mood", hsArr.get(i).getMood());
				jsonWTUData.put("Activity", hsArr.get(i).getActivity());

				jsonWTUData.put("MeasureTime", hsArr.get(i).getMeasureTime());
				jsonWTUData.put("MechineType", hsArr.get(i).getMechineType());
				jsonWTUData.put("MechineDeviceID", hsArr.get(i).getMechineDeviceID());

				jsonWTUData.put("Mood", 1);
				jsonWTUData.put("Activity", 1);
				jsonWTUData.put("OutDoorRatio", 50);

				jsonWTUArr.put(i, jsonWTUData);
			}
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		section_uploadParams.put("Un", userName);
		section_uploadParams.put("VerifyToken", VerifyToken);
		section_uploadParams.put("UploadData", jsonWTUArr.toString());

		String webAddress = inputHost + AppsDeviceParameters.path + "amsleepperiodreport_upload.htm";//流程中调用接口
		if (isDebug)
			Log.i(TAG, "数据上传 = " + section_uploadParams.toString());
		HttpsPost ht = new HttpsPost(context);

		try {
			messageReturn = ht.requireClass(webAddress, section_uploadParams, "UTF-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (messageReturn.length() == 0) {
			return hsUploadReturn;
		}
		try {
			JSONTokener jsonTParser = new JSONTokener(messageReturn);
			JSONObject jsonORegist = (JSONObject) jsonTParser.nextValue();

			this.result = jsonORegist.getInt("Result");
			this.TS = Long.parseLong(jsonORegist.getString("TS"));
			this.resultMessage = Float.parseFloat(jsonORegist.getString("ResultMessage"));
			this.queueNum = jsonORegist.getInt("QueueNum");

			hsUploadReturn.setResultMessage(jsonORegist.getString("ResultMessage"));

			if (isDebug) Log.i(TAG, "上传返回 = " + resultMessage);

			if (resultMessage == 100.0) {
				return hsUploadReturn;
			} else if (resultMessage == 208.0) {//服务器地址不正确
				JSONTokener jsonTokener = new JSONTokener(jsonORegist.getString("ReturnValue"));
				JSONObject jsonWebListOut = (JSONObject) jsonTokener.nextValue();
				String regionHost = jsonWebListOut.getString("RegionHost");

				if (isDebug) Log.i(TAG, "hs_upload返回208,拿到regionHost = " + regionHost);

				AM_ReturnData hsUploadReturn1 = am_section_upload(un, VerifyToken, hsArr, regionHost);
				if ("100".equals(hsUploadReturn1.getResultMessage())) {// 登录成功

					// 保存最新的信息
					UserCheckSDK.saveUserInfo(context, null, null, regionHost, null, null, null, null, -1);

					if (isDebug) Log.i(TAG, "保存regionHost到本地");

					host = regionHost;
					return hsUploadReturn1;
				} else { // 登录失败
					return hsUploadReturn1;
				}
			} else if (resultMessage == 212.0) {//Token失效->刷新

				if (isDebug) Log.i(TAG, "212->Token过期:刷新Token");

				//取得refreshToken
				SharedPreferences sharedPreferences = context.getSharedPreferences("jiuan.sdk.author", 0);
				String refreshToken = sharedPreferences.getString("refreshToken", "");

				//执行刷新
				CommCloudSDK commCloudSDK = new CommCloudSDK(context);
				ReturnDataUser token_refresh = commCloudSDK.token_refresh(refreshToken, un, host);

				if ("100".equals(token_refresh.getResultCode())) {//成功

					//保存后重新调用上传
					if (isDebug) Log.i(TAG, "重新调用hs_upload");
					String accessToken = token_refresh.getAccessToken();
					AM_ReturnData bpUploadReturn1 = am_section_upload(un, accessToken, hsArr, host);

					if ("100".equals(bpUploadReturn1.getResultMessage())) {

						if (isDebug) Log.i(TAG, "刷新Token后上传HS数据成功!保存最新Token到本地");
						//保存新的accessToken, refreshToken到本地
						//保存到jiuan.sdk.author
						refreshToken = token_refresh.getRefreshToken();
						Editor localEditor = sharedPreferences.edit();
						localEditor.putString("accessToken", accessToken);
						localEditor.putString("refreshToken", refreshToken);
						localEditor.commit();

						// 保存最新的信息-un+"userinfo"
						UserCheckSDK.saveUserInfo(context, null, null, null, accessToken, refreshToken, null, null, -1);

						return bpUploadReturn1;
					} else {
						return bpUploadReturn1;
					}
				} else {
					return hsUploadReturn;
				}
			} else if (resultMessage == 221.0) {//Token失败->重新登录
				if (isDebug)
					Log.i(TAG, "221->Token验证失败->其他APP已刷新,需重新登录");
				AM_ReturnData regReturnData = new AM_ReturnData();
				try {
					CommCloudSDK commCloudSDK = new CommCloudSDK(context);
					ReturnDataUser userSign = commCloudSDK.UserSign(client_id, client_secret, un, inputHost);
					if ("100".equals(userSign.getResultCode())) {
						regReturnData.setResultMessage(userSign.getResultCode());
						regReturnData.setRegionHost(userSign.getRegionHost());
						regReturnData.setAccessToken(userSign.getAccessToken());
						regReturnData.setRefreshToken(userSign.getRefreshToken());
						regReturnData.setExpires(userSign.getExpires());
						if (isDebug)
							Log.i(TAG, "重新登录成功,重新调用hs_upload上传数据");
						String accessToken = regReturnData.getAccessToken();
						AM_ReturnData hsUploadReturn1 = am_section_upload(un, accessToken, hsArr, inputHost);
						if ("100".equals(hsUploadReturn1.getResultMessage())) {
							if (isDebug)
								Log.i(TAG, "再次上HS数据成功,保存最新Token信息到本地");

							//保存新的accessToken, refreshToken到本地
							//保存到jiuan.sdk.author
							SharedPreferences sharedPreferences = context.getSharedPreferences("jiuan.sdk.author", 0);
							Editor edit = sharedPreferences.edit();
							String refreshToken = regReturnData.getRefreshToken();
							edit.putString("accessToken", accessToken);
							edit.putString("refreshToken", refreshToken);
							edit.commit();

							// 保存最新的信息-un+"userinfo"
							UserCheckSDK.saveUserInfo(context, null, null, null, accessToken, refreshToken, null, null, -1);

							return hsUploadReturn1;
						} else {
							return hsUploadReturn1;
						}
					} else {
						return regReturnData;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				return hsUploadReturn;
			}
		} catch (JSONException e) {
			return hsUploadReturn;
		}
		return hsUploadReturn;
	}

	/**
	 *
	 * @param userName    用户名
	 * @param VerifyToken Token
	 * @param inputHost   传入的host  当前最适的Host
	 * @throws SocketTimeoutException
	 * @return Data 接口结果类
	 */
	public AM_ReturnData am_workout_upload(String userName, String VerifyToken, ArrayList<Data_TB_Workout> workOut, String
			inputHost
	) throws SocketTimeoutException {

		AM_ReturnData hsUploadReturn = new AM_ReturnData();
		// 编辑发送参数
		Map<String, String> workout_uploadParams = new HashMap<String, String>();
		workout_uploadParams.put("sc", AppsDeviceParameters.SC);
		workout_uploadParams.put("sv", SV_workout);
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
		JSONArray jsonWorkOutArr = new JSONArray();

		try {
			JSONObject jsonWorkOutData;
			String weather = "";
			// 生成BP JSON
			Log.e(TAG, workOut.size() + "");
			for (int i = 0; i < workOut.size(); i++) {
				jsonWorkOutData = new JSONObject();

				jsonWorkOutData.put("ChangeType", workOut.get(i).getWorkout_ChangeType());
				jsonWorkOutData.put("LastChangeTime", workOut.get(i).getWorkout_LastChangeTime());
				jsonWorkOutData.put("PhoneCreateTime", workOut.get(i).getWorkout_PhoneCreateTime());
				jsonWorkOutData.put("PhoneDataID", workOut.get(i).getWorkout_PhoneDataID());
				jsonWorkOutData.put("Lat", workOut.get(i).getWorkout_Lat());
				jsonWorkOutData.put("Lon", workOut.get(i).getWorkout_Lon());
				jsonWorkOutData.put("TimeZone", workOut.get(i).getWorkout_TimeZone());

				jsonWorkOutData.put("SpendMinutes", workOut.get(i).getWorkout_SpendMinutes());
				jsonWorkOutData.put("Steps", workOut.get(i).getWorkout_Steps());
				jsonWorkOutData.put("Distance", workOut.get(i).getWorkout_Distance());
				jsonWorkOutData.put("Calories", workOut.get(i).getWorkout_Calories());
				jsonWorkOutData.put("City", workOut.get(i).getWorkout_City());

				weather = workOut.get(i).getWorkout_Temperature() + "," + workOut.get(i).getWorkout_Humidity() + ","
						+ workOut.get(i).getWorkout_Atmosphere() + "," + workOut.get(i).getWorkout_WeatherCode();
				jsonWorkOutData.put("Weather", weather);

				JSONObject jsonCommentData = new JSONObject();
				try {
					jsonCommentData.put("Note", workOut.get(i).getWorkout_CommentNote());
					jsonCommentData.put("NoteTS", workOut.get(i).getWorkout_CommentTS());
					jsonCommentData.put("Pic", workOut.get(i).getWorkout_CommentPic());
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				jsonWorkOutData.put("Comment", jsonCommentData);

				jsonWorkOutData.put("MechineType", workOut.get(i).getWorkout_MechineType());
				jsonWorkOutData.put("MechineDeviceID", workOut.get(i).getWorkout_MechineDeviceID());

				jsonWorkOutArr.put(i, jsonWorkOutData);
			}
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		workout_uploadParams.put("Un", userName);
		workout_uploadParams.put("VerifyToken", VerifyToken);
		workout_uploadParams.put("UploadData", jsonWorkOutArr.toString());

		String webAddress = inputHost + AppsDeviceParameters.path + "workout_upload.htm";//流程中调用接口
		if (isDebug)
			Log.i(TAG, "数据上传 = " + workout_uploadParams.toString());
		HttpsPost ht = new HttpsPost(context);

		try {
			messageReturn = ht.requireClass(webAddress, workout_uploadParams, "UTF-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (messageReturn.length() == 0) {
			return hsUploadReturn;
		}
		try {
			JSONTokener jsonTParser = new JSONTokener(messageReturn);
			JSONObject jsonORegist = (JSONObject) jsonTParser.nextValue();

			this.result = jsonORegist.getInt("Result");
			this.TS = Long.parseLong(jsonORegist.getString("TS"));
			this.resultMessage = Float.parseFloat(jsonORegist.getString("ResultMessage"));
			this.queueNum = jsonORegist.getInt("QueueNum");

			hsUploadReturn.setResultMessage(jsonORegist.getString("ResultMessage"));

			if (isDebug) Log.i(TAG, "上传返回 = " + resultMessage);

			if (resultMessage == 100.0) {
				return hsUploadReturn;
			} else if (resultMessage == 208.0) {//服务器地址不正确
				JSONTokener jsonTokener = new JSONTokener(jsonORegist.getString("ReturnValue"));
				JSONObject jsonWebListOut = (JSONObject) jsonTokener.nextValue();
				String regionHost = jsonWebListOut.getString("RegionHost");

				if (isDebug) Log.i(TAG, "hs_upload返回208,拿到regionHost = " + regionHost);

				AM_ReturnData hsUploadReturn1 = am_workout_upload(un, VerifyToken, workOut, regionHost);
				if ("100".equals(hsUploadReturn1.getResultMessage())) {// 登录成功

					// 保存最新的信息
					UserCheckSDK.saveUserInfo(context, null, null, regionHost, null, null, null, null, -1);

					if (isDebug) Log.i(TAG, "保存regionHost到本地");

					host = regionHost;
					return hsUploadReturn1;
				} else { // 登录失败
					return hsUploadReturn1;
				}
			} else if (resultMessage == 212.0) {//Token失效->刷新

				if (isDebug) Log.i(TAG, "212->Token过期:刷新Token");

				//取得refreshToken
				SharedPreferences sharedPreferences = context.getSharedPreferences("jiuan.sdk.author", 0);
				String refreshToken = sharedPreferences.getString("refreshToken", "");

				//执行刷新
				CommCloudSDK commCloudSDK = new CommCloudSDK(context);
				ReturnDataUser token_refresh = commCloudSDK.token_refresh(refreshToken, un, host);

				if ("100".equals(token_refresh.getResultCode())) {//成功

					//保存后重新调用上传
					if (isDebug) Log.i(TAG, "重新调用hs_upload");
					String accessToken = token_refresh.getAccessToken();
					AM_ReturnData bpUploadReturn1 = am_workout_upload(un, accessToken, workOut, host);

					if ("100".equals(bpUploadReturn1.getResultMessage())) {

						if (isDebug) Log.i(TAG, "刷新Token后上传HS数据成功!保存最新Token到本地");
						//保存新的accessToken, refreshToken到本地
						//保存到jiuan.sdk.author
						refreshToken = token_refresh.getRefreshToken();
						Editor localEditor = sharedPreferences.edit();
						localEditor.putString("accessToken", accessToken);
						localEditor.putString("refreshToken", refreshToken);
						localEditor.commit();

						// 保存最新的信息-un+"userinfo"
						UserCheckSDK.saveUserInfo(context, null, null, null, accessToken, refreshToken, null, null, -1);

						return bpUploadReturn1;
					} else {
						return bpUploadReturn1;
					}
				} else {
					return hsUploadReturn;
				}
			} else if (resultMessage == 221.0) {//Token失败->重新登录
				if (isDebug)
					Log.i(TAG, "221->Token验证失败->其他APP已刷新,需重新登录");
				AM_ReturnData regReturnData = new AM_ReturnData();
				try {
					CommCloudSDK commCloudSDK = new CommCloudSDK(context);
					ReturnDataUser userSign = commCloudSDK.UserSign(client_id, client_secret, un, inputHost);
					if ("100".equals(userSign.getResultCode())) {
						regReturnData.setResultMessage(userSign.getResultCode());
						regReturnData.setRegionHost(userSign.getRegionHost());
						regReturnData.setAccessToken(userSign.getAccessToken());
						regReturnData.setRefreshToken(userSign.getRefreshToken());
						regReturnData.setExpires(userSign.getExpires());
						if (isDebug)
							Log.i(TAG, "重新登录成功,重新调用hs_upload上传数据");
						String accessToken = regReturnData.getAccessToken();
						AM_ReturnData hsUploadReturn1 = am_workout_upload(un, accessToken, workOut, inputHost);
						if ("100".equals(hsUploadReturn1.getResultMessage())) {
							if (isDebug)
								Log.i(TAG, "再次上HS数据成功,保存最新Token信息到本地");

							//保存新的accessToken, refreshToken到本地
							//保存到jiuan.sdk.author
							SharedPreferences sharedPreferences = context.getSharedPreferences("jiuan.sdk.author", 0);
							Editor edit = sharedPreferences.edit();
							String refreshToken = regReturnData.getRefreshToken();
							edit.putString("accessToken", accessToken);
							edit.putString("refreshToken", refreshToken);
							edit.commit();

							// 保存最新的信息-un+"userinfo"
							UserCheckSDK.saveUserInfo(context, null, null, null, accessToken, refreshToken, null, null, -1);

							return hsUploadReturn1;
						} else {
							return hsUploadReturn1;
						}
					} else {
						return regReturnData;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				return hsUploadReturn;
			}
		} catch (JSONException e) {
			return hsUploadReturn;
		}
		return hsUploadReturn;
	}

	/**
	 * 96.	Swim上传
	 *
	 * @param userName
	 * @param verifyToken
	 * @param swimArr
	 * @return
	 * @throws SocketTimeoutException
	 * @throws Exception
	 */
	public int swimActivity_upload(String userName, String verifyToken, ArrayList<Data_TB_Swim> swimArr, String inputHost) throws Exception {

		// 编辑发送参数
		Map<String, String> swim_uploadParams = new HashMap<String, String>();
		swim_uploadParams.put("sc", AppsDeviceParameters.SC);
		swim_uploadParams.put("sv", Sv_swimActivity_upload);
		swim_uploadParams.put("AppVersion", AppsDeviceParameters.APP_VERSION);
		swim_uploadParams.put("AppGuid", getAppID());
		swim_uploadParams.put("PhoneOS", android.os.Build.VERSION.RELEASE);
		swim_uploadParams.put("PhoneName", android.os.Build.MODEL);
		swim_uploadParams.put("PhoneID", getDeviceID());
		swim_uploadParams.put("PhoneLanguage", Locale.getDefault().getLanguage());
		swim_uploadParams.put("PhoneRegion", Locale.getDefault().getCountry());
		swim_uploadParams.put("QueueNum", "1");
		swim_uploadParams.put("Token", "");

		//上传数据
		JSONArray dataArr = new JSONArray();
		Log.e("上传数据", "数组长度 = " + swimArr.size());
		try {
			JSONObject dataItem;
			for (int i = 0; i < swimArr.size(); i++) {
				dataItem = new JSONObject();
				dataItem.put("ChangeType", swimArr.get(i).getSwim_ChangeType());
				dataItem.put("swimEndTimeStamp", swimArr.get(i).getSwim_endtime());//每一程游泳结束时间戳
				dataItem.put("swimCostTime", swimArr.get(i).getSwim_SpendMinutes());//每一程游泳耗时,单位秒
				dataItem.put("swimRoundTimes", swimArr.get(i).getSwim_Cycles());//每一程的程数标号 例如1，2，3。。。
				dataItem.put("swimPoolLength", swimArr.get(i).getSwim_Distance());//游泳池长度
				dataItem.put("swimStyle", swimArr.get(i).getSwim_Stroke());//每一程游泳的泳姿，自由泳：0 蛙泳：1   仰泳：2 ，未识别：5
				dataItem.put("swimThrashTimes", swimArr.get(i).getSwim_PullTimes());//划水次数 这里是依次递加的
				dataItem.put("swimCalories", swimArr.get(i).getSwim_Calories());//消耗的卡路里数 这里是依次递加的
				dataItem.put("city", swimArr.get(i).getSwim_City());
				dataItem.put("PhoneDataID", swimArr.get(i).getSwim_PhoneDataID());
				dataItem.put("MechineDeviceID", swimArr.get(i).getSwim_MechineDeviceID());
				dataItem.put("MechineType", swimArr.get(i).getSwim_MechineType());
				dataItem.put("Lat", swimArr.get(i).getSwim_Lat());
				dataItem.put("Lon", swimArr.get(i).getSwim_Lon());
				dataItem.put("LastChangeTime", swimArr.get(i).getSwim_LastChangeTime());
				dataItem.put("Note", swimArr.get(i).getSwim_CommentNote());
				dataItem.put("noteTS", swimArr.get(i).getSwim_CommentTS());

				//这个字段是否加上？ swimStartTimeStamp
//				dataItem.put("swimStartTimeStamp", swimArr.get(i).getSwimStartTimeStamp());//每一程游泳开始时间戳

				dataItem.put("TimeZone", swimArr.get(i).getSwim_TimeZone());
				dataItem.put("swimCutInTimeDiff", swimArr.get(i).getSwim_CutInTimeDif());//切入时间差，单位秒
				dataItem.put("swimCutOutTimeDiff", swimArr.get(i).getSwim_CutOutTimeDif());//切出时间差，单位秒
				dataItem.put("swimProcessFlag", swimArr.get(i).getSwim_ProcessFlag());//程数进程标识，1：代表开始、0：代表正在游、2代表结束、3：代表此次进入游泳就一程
				dataItem.put("PhoneCreateTime", swimArr.get(i).getSwim_PhoneCreateTime());//App创建数据的时间

				dataArr.put(dataItem);
			}
		} catch (Exception e) {
			Log.e(TAG, "解析上传数据错误！" + e.toString());
		}

		swim_uploadParams.put("Un", userName);
		swim_uploadParams.put("VerifyToken", verifyToken);
		swim_uploadParams.put("UploadData", dataArr.toString());

		if (isDebug) {
			Log.e(TAG, "上传参数 = " + swim_uploadParams.toString());
		}
		String webAddress = inputHost + AppsDeviceParameters.path + "swimActivity_upload.htm";

		try {
			HttpsPost ht = new HttpsPost(context);
			messageReturn = ht.requireClass(webAddress, swim_uploadParams, "UTF-8");

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
		//解析返回数据
		try {
			JSONTokener jsonTParser = new JSONTokener(messageReturn);
			JSONObject jsonORegist = (JSONObject) jsonTParser.nextValue();
			this.result = jsonORegist.getInt("Result");
			this.resultMessage = Integer.parseInt(jsonORegist.getString("ResultMessage"));
			this.queueNum = jsonORegist.getInt("QueueNum");
			String returnString = jsonORegist.getString("ReturnValue");
			if (resultMessage == 100) {
				Log.i(TAG, "上传成功，返回：" + returnString);
				return 100;
			} else if (resultMessage == 208) {//服务器地址不正确
				JSONTokener jsonTokener = new JSONTokener(jsonORegist.getString("ReturnValue"));
				JSONObject jsonWebListOut = (JSONObject) jsonTokener.nextValue();
				String regionHost = jsonWebListOut.getString("RegionHost");

				UserCheckSDK.saveUserInfo(context, null, null, regionHost, null, null, null, null, -1);

				return swimActivity_upload(userName, verifyToken, swimArr, regionHost);

			} else if (resultMessage == 212) {//Token失效->刷新

				if (isDebug) Log.i(TAG, "212->Token过期:刷新Token");

				//取得refreshToken
				SharedPreferences sharedPreferences = context.getSharedPreferences("jiuan.sdk.author", 0);
				String refreshToken = sharedPreferences.getString("refreshToken", "");

				//执行刷新
				CommCloudSDK commCloudSDK = new CommCloudSDK(context);
				ReturnDataUser token_refresh = commCloudSDK.token_refresh(refreshToken, un, host);

				if ("100".equals(token_refresh.getResultCode())) {//成功

					String accessToken = token_refresh.getAccessToken();

					refreshToken = token_refresh.getRefreshToken();
					Editor localEditor = sharedPreferences.edit();
					localEditor.putString("accessToken", accessToken);
					localEditor.putString("refreshToken", refreshToken);
					localEditor.commit();

					// 保存最新的信息-un+"userinfo"
					UserCheckSDK.saveUserInfo(context, null, null, null, accessToken, refreshToken, null, null, -1);

					return swimActivity_upload(userName, accessToken, swimArr, inputHost);

				} else {
					return 999;
				}
			} else if (resultMessage == 221.0) {//Token失败->重新登录
				if (isDebug)
					Log.i(TAG, "221->Token验证失败->其他APP已刷新,需重新登录");
				AM_ReturnData regReturnData = new AM_ReturnData();
				try {
					CommCloudSDK commCloudSDK = new CommCloudSDK(context);
					ReturnDataUser userSign = commCloudSDK.UserSign(client_id, client_secret, un, inputHost);
					if ("100".equals(userSign.getResultCode())) {
						regReturnData.setResultMessage(userSign.getResultCode());
						regReturnData.setRegionHost(userSign.getRegionHost());
						regReturnData.setAccessToken(userSign.getAccessToken());
						regReturnData.setRefreshToken(userSign.getRefreshToken());
						regReturnData.setExpires(userSign.getExpires());

						String accessToken = regReturnData.getAccessToken();
						SharedPreferences sharedPreferences = context.getSharedPreferences("jiuan.sdk.author", 0);
						Editor edit = sharedPreferences.edit();
						String refreshToken = regReturnData.getRefreshToken();
						edit.putString("accessToken", accessToken);
						edit.putString("refreshToken", refreshToken);
						edit.commit();

						// 保存最新的信息-un+"userinfo"
						UserCheckSDK.saveUserInfo(context, null, null, null, accessToken, refreshToken, null, null, -1);


						return swimActivity_upload(userName, accessToken, swimArr, inputHost);

					} else {
						return 999;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				Log.i(TAG, "上传失败，返回：" + returnString);
				return 999;
			}
		} catch (Exception e) {
			Log.e(TAG, "解析返回数据失败！" + e.toString());
		}
		return 999;
	}


	/**
	 * 98.	SwinReport上传
	 *
	 * @param userName
	 * @param verifyToken
	 * @param swimArr
	 * @return
	 * @throws SocketTimeoutException
	 * @throws Exception
	 */
	public int swimReport_upload(String userName, String verifyToken, ArrayList<Data_TB_SwimSection> swimArr, String inputHost) throws Exception {

		// 编辑发送参数
		Map<String, String> swim_uploadParams = new HashMap<String, String>();
		swim_uploadParams.put("sc", AppsDeviceParameters.SC);
		swim_uploadParams.put("sv", Sv_swimReport_upload);
		swim_uploadParams.put("AppVersion", AppsDeviceParameters.APP_VERSION);
		swim_uploadParams.put("AppGuid", getAppID());
		swim_uploadParams.put("PhoneOS", android.os.Build.VERSION.RELEASE);
		swim_uploadParams.put("PhoneName", android.os.Build.MODEL);
		swim_uploadParams.put("PhoneID", getDeviceID());
		swim_uploadParams.put("PhoneLanguage", Locale.getDefault().getLanguage());
		swim_uploadParams.put("PhoneRegion", Locale.getDefault().getCountry());
		swim_uploadParams.put("QueueNum", "1");
		swim_uploadParams.put("Token", "");

		//上传数据
		JSONArray dataArr = new JSONArray();
		Log.e("上传数据", "数组长度 = " + swimArr.size());
		try {
			JSONObject dataItem;
			for (int i = 0; i < swimArr.size(); i++) {
				dataItem = new JSONObject();
				//default 1
				//这个字段是否加上？ ChangeType   PhoneCreateTime
				dataItem.put("ChangeType", 1);//swimArr.get(i).getSwimSection_ChangeType()

				dataItem.put("swimCostTime", swimArr.get(i).getSwimSection_SwimCoastTime());//阶段游泳的总耗时
				dataItem.put("swimEndTime", swimArr.get(i).getSwimSection_Endtime());//切出游泳模式的时间
				dataItem.put("PhoneDataID", swimArr.get(i).getSwimSection_DataID());//数据ID
				dataItem.put("swimPoolLength", swimArr.get(i).getSwimSection_PoolLength());//游泳池长度
				dataItem.put("swimSpendTimeBackStroke", swimArr.get(i).getSwimSection_SpendTimeBackStroke());//阶段游泳中仰泳耗时
				dataItem.put("swimSpendTimeBreastStroke", swimArr.get(i).getSwimSection_SpendTimeBreastStroke());//阶段游泳中蛙泳耗时
				dataItem.put("swimSpendTimeFreeStroke", swimArr.get(i).getSwimSection_SpendTimeFreeStroke());//阶段游泳中自由泳耗时
				dataItem.put("swimSpendTimeUnrecognized", swimArr.get(i).getSwimSection_SpendTimeUnrecognized());//阶段游泳中未识别其他泳姿耗时
				dataItem.put("city", swimArr.get(i).getSwimSection_City());
				dataItem.put("MechineDeviceID", swimArr.get(i).getSwimSection_DeviceID());
				dataItem.put("MechineType", swimArr.get(i).getSwimSection_DeviceSource());
				dataItem.put("Lat", swimArr.get(i).getSwimSection_Lat());
				dataItem.put("Lon", swimArr.get(i).getSwimSection_Lon());
				dataItem.put("LastChangeTime", swimArr.get(i).getSwimSection_LastChangeTime());
				dataItem.put("Note", swimArr.get(i).getSwimSection_Note());
				dataItem.put("noteTS", swimArr.get(i).getSwimSection_NoteTS());
				dataItem.put("swimStartTime", swimArr.get(i).getSwimSection_StartTime());//切入游泳模式的时间
				dataItem.put("TimeZone", swimArr.get(i).getSwimSection_TimeZone());
				dataItem.put("swimSumCalories", swimArr.get(i).getSwimSection_SumCalories());//阶段游泳消耗的总卡路里
				dataItem.put("swimSumThrashTimes", swimArr.get(i).getSwimSection_SumThrashTimes());//阶段游泳总划水次数
				dataItem.put("swimSumTripCount", swimArr.get(i).getSwimSection_SumTripCount());//阶段游泳中总的程数

//				dataItem.put("PhoneCreateTime", swimArr.get(i).getSwimSection_PhoneCreateTime());//App创建数据的时间

				dataArr.put(dataItem);
			}
		} catch (Exception e) {
			Log.e(TAG, "解析上传数据错误！" + e.toString());
		}

		swim_uploadParams.put("Un", userName);
		swim_uploadParams.put("VerifyToken", verifyToken);
		swim_uploadParams.put("UploadData", dataArr.toString());

		Log.e(TAG, "上传参数 = " + swim_uploadParams.toString());
		String webAddress = AppsDeviceParameters.Address + "swimReport_upload.htm";

		try {
			HttpsPost ht = new HttpsPost(context);
			messageReturn = ht.requireClass(webAddress, swim_uploadParams, "UTF-8");

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
		//解析返回数据
		try {
			JSONTokener jsonTParser = new JSONTokener(messageReturn);
			JSONObject jsonORegist = (JSONObject) jsonTParser.nextValue();
			this.result = jsonORegist.getInt("Result");
			this.resultMessage = Integer.parseInt(jsonORegist.getString("ResultMessage"));
			this.queueNum = jsonORegist.getInt("QueueNum");
			String returnString = jsonORegist.getString("ReturnValue");
			if (resultMessage == 100) {
				Log.i(TAG, "上传成功，返回：" + returnString);
				return 100;
			}  else if (resultMessage == 208) {//服务器地址不正确
				JSONTokener jsonTokener = new JSONTokener(jsonORegist.getString("ReturnValue"));
				JSONObject jsonWebListOut = (JSONObject) jsonTokener.nextValue();
				String regionHost = jsonWebListOut.getString("RegionHost");

				UserCheckSDK.saveUserInfo(context, null, null, regionHost, null, null, null, null, -1);

				return swimReport_upload(userName, verifyToken, swimArr, regionHost);

			} else if (resultMessage == 212) {//Token失效->刷新

				if (isDebug) Log.i(TAG, "212->Token过期:刷新Token");

				//取得refreshToken
				SharedPreferences sharedPreferences = context.getSharedPreferences("jiuan.sdk.author", 0);
				String refreshToken = sharedPreferences.getString("refreshToken", "");

				//执行刷新
				CommCloudSDK commCloudSDK = new CommCloudSDK(context);
				ReturnDataUser token_refresh = commCloudSDK.token_refresh(refreshToken, un, host);

				if ("100".equals(token_refresh.getResultCode())) {//成功

					String accessToken = token_refresh.getAccessToken();

					refreshToken = token_refresh.getRefreshToken();
					Editor localEditor = sharedPreferences.edit();
					localEditor.putString("accessToken", accessToken);
					localEditor.putString("refreshToken", refreshToken);
					localEditor.commit();

					// 保存最新的信息-un+"userinfo"
					UserCheckSDK.saveUserInfo(context, null, null, null, accessToken, refreshToken, null, null, -1);

					return swimReport_upload(userName, accessToken, swimArr, inputHost);

				} else {
					return 999;
				}
			} else if (resultMessage == 221.0) {//Token失败->重新登录
				if (isDebug)
					Log.i(TAG, "221->Token验证失败->其他APP已刷新,需重新登录");
				AM_ReturnData regReturnData = new AM_ReturnData();
				try {
					CommCloudSDK commCloudSDK = new CommCloudSDK(context);
					ReturnDataUser userSign = commCloudSDK.UserSign(client_id, client_secret, un, inputHost);
					if ("100".equals(userSign.getResultCode())) {
						regReturnData.setResultMessage(userSign.getResultCode());
						regReturnData.setRegionHost(userSign.getRegionHost());
						regReturnData.setAccessToken(userSign.getAccessToken());
						regReturnData.setRefreshToken(userSign.getRefreshToken());
						regReturnData.setExpires(userSign.getExpires());

						String accessToken = regReturnData.getAccessToken();
						SharedPreferences sharedPreferences = context.getSharedPreferences("jiuan.sdk.author", 0);
						Editor edit = sharedPreferences.edit();
						String refreshToken = regReturnData.getRefreshToken();
						edit.putString("accessToken", accessToken);
						edit.putString("refreshToken", refreshToken);
						edit.commit();

						// 保存最新的信息-un+"userinfo"
						UserCheckSDK.saveUserInfo(context, null, null, null, accessToken, refreshToken, null, null, -1);


						return swimReport_upload(userName, accessToken, swimArr, inputHost);

					} else {
						return 999;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				Log.i(TAG, "上传失败，返回：" + returnString);
				return 999;
			}
		} catch (Exception e) {
			Log.e(TAG, "解析返回数据失败！" + e.toString());
		}
		return 999;
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
