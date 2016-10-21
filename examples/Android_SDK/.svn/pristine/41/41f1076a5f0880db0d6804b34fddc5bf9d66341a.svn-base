package com.ihealth.communication.cloud.data;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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

public class BP_CommCloud {

	public static final String TAG = "Net";
	
	public float resultMessage;
	public int queueNum;
	public int result = 0;
	public long TS = 0;
	public String messageReturn = "";
	public static final String SV_bp_upload = "c629584d4e8141a6b18b2fab90d28b1e";

	Context context;
	private String QueueNum = "111111";
	private String host;
	private String un;
	private String client_id;
	private String client_secret;
	public BP_CommCloud(Context context) {
		this.context = context;
		un = context.getSharedPreferences("jiuan.sdk.author", 0).getString("email", "");
		host = context.getSharedPreferences(un+"userinfo", 0).getString("Host", "");//获取最优服务器
		if("".equals(host)){
			host = AppsDeviceParameters.webSite;
		}
		client_id = context.getSharedPreferences(un+"userinfo", 0).getString("client_id", "");
		client_secret = context.getSharedPreferences(un+"userinfo", 0).getString("client_secret", "");
	}


	public BP_ReturnData bp_upload(String un, String VerifyToken, ArrayList<Data_BP_Result> bpArr, String inputHost) {

		BP_ReturnData bpUploadReturn = new BP_ReturnData();
		Map<String, String> bp_uploadParams = new HashMap<String, String>();
		
		bp_uploadParams.put("sc", AppsDeviceParameters.SC); 
		bp_uploadParams.put("sv", SV_bp_upload);
		bp_uploadParams.put("AppVersion", AppsDeviceParameters.APP_VERSION);
		bp_uploadParams.put("AppGuid", getAppID());
		bp_uploadParams.put("PhoneOS", "android"+android.os.Build.VERSION.RELEASE);
		bp_uploadParams.put("PhoneName", android.os.Build.MODEL);
		bp_uploadParams.put("PhoneID", getDeviceID());
		bp_uploadParams.put("PhoneLanguage", Locale.getDefault().getLanguage());
		bp_uploadParams.put("PhoneRegion", Locale.getDefault().getCountry());
		bp_uploadParams.put("QueueNum", QueueNum);
		bp_uploadParams.put("Token", "");

		// 编辑参数UploadData
		JSONArray jsonBPUArr = new JSONArray();
		try {
			JSONObject jsonBPUData;
			String weather;
			for (int i = 0; i < bpArr.size(); i++) {
				jsonBPUData = new JSONObject();

				jsonBPUData.put("ChangeType", bpArr.get(i).getChangeType());
				jsonBPUData.put("LastChangeTime", bpArr.get(i).getLastChangeTime());
				jsonBPUData.put("PhoneCreateTime", bpArr.get(i).getPhoneCreateTime());
				jsonBPUData.put("PhoneDataID", bpArr.get(i).getPhoneDataID());
				jsonBPUData.put("Lat", bpArr.get(i).getLat());
				jsonBPUData.put("Lon", bpArr.get(i).getLon());
				jsonBPUData.put("TimeZone", bpArr.get(i).getTimeZone());
				jsonBPUData.put("BPL", bpArr.get(i).getBPL());
				jsonBPUData.put("HP", bpArr.get(i).getHP());
				jsonBPUData.put("HR", bpArr.get(i).getHR());
				jsonBPUData.put("IsArr", bpArr.get(i).getIsArr());
				jsonBPUData.put("LP", bpArr.get(i).getLP());
				jsonBPUData.put("MeasureType", bpArr.get(i).getMeasureType());
				jsonBPUData.put("MeasureTime", bpArr.get(i).getMeasureTime());
				jsonBPUData.put("Note", bpArr.get(i).getNote());
				jsonBPUData.put("MechineType", bpArr.get(i).getMechineType());
				jsonBPUData.put("MechineDeviceID", bpArr.get(i).getMechineDeviceID());
				
				JSONArray jsonWLArr = new JSONArray();
				int wlLenght = bpArr.get(i).getWL().split("A").length;
				for (int j = 0; j < wlLenght; j++) {
					jsonWLArr.put(j, bpArr.get(i).getWL().split("A")[j]);
				}
				jsonBPUData.put("WL", jsonWLArr);

				//5.0云新增字段
				jsonBPUData.put("NoteTS", bpArr.get(i).getNoteTS());
				jsonBPUData.put("Mood", bpArr.get(i).getBpMood());
				jsonBPUData.put("Activity", bpArr.get(i).getBpActivity());
				weather = bpArr.get(i).getTemp() +","+bpArr.get(i).getHumidity() +","+bpArr.get(i).getVisibility() +"," +bpArr.get(i).getWeather();
				jsonBPUData.put("Weather", weather);
				
				jsonBPUArr.put(i, jsonBPUData);
			}
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		
		bp_uploadParams.put("Un", un);//bpArr.get(0).getiHealthCloud()+"@BpSDK"  "qqqqqq@163.com"
		bp_uploadParams.put("VerifyToken", VerifyToken);//"qvXiz-yLgiNFXfmjJREmcVKVwFyrxsozoF7f45qWmcZArh1VtSX6Q1nIIr9cbyMg1u*c*jTbV6RQ3ij*SSqa1wtAaRnEpIiFqbxJN4ij15-eLFIqDIrCnRn1L6nfG8ZH"
		bp_uploadParams.put("UploadData", jsonBPUArr.toString());
		
		String webAddress = inputHost + AppsDeviceParameters.path + "bp_upload.htm";//ashx
		HttpsPost ht = new HttpsPost(context);
		
		try {
			messageReturn = ht.requireClass(webAddress, bp_uploadParams, "UTF-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (messageReturn.length() == 0)
			return bpUploadReturn;

		// 分析收取的数据
		try {
			JSONTokener jsonTParser = new JSONTokener(messageReturn);
			JSONObject jsonORegist = (JSONObject) jsonTParser.nextValue();
			
			this.result = jsonORegist.getInt("Result");
			this.TS = Long.parseLong(jsonORegist.getString("TS"));
			this.resultMessage = Float.parseFloat(jsonORegist.getString("ResultMessage"));
			this.queueNum = jsonORegist.getInt("QueueNum");

			bpUploadReturn.setResultMessage(jsonORegist.getString("ResultMessage"));
			
			if (resultMessage == 100.0) {
				return bpUploadReturn;
			} else if(resultMessage == 208.0){//服务器地址不正确
				
				JSONTokener jsonTokener = new JSONTokener(jsonORegist.getString("ReturnValue"));
				JSONObject jsonWebListOut = (JSONObject) jsonTokener.nextValue();
				String regionHost = jsonWebListOut.getString("RegionHost");

				BP_ReturnData bpUploadReturn1 = bp_upload(un, VerifyToken, bpArr, regionHost);
				if ("100".equals(bpUploadReturn1.getResultMessage())) {// 登录成功
					// 保存最新的信息
					UserCheckSDK.saveUserInfo(context, null, null, regionHost,null,null,null,null, -1);
					host = regionHost;
					return bpUploadReturn1;
				} else { // 登录失败
					return bpUploadReturn1;
				}
			} else if(resultMessage == 212.0){//Token失效->刷新
				SharedPreferences sharedPreferences = context.getSharedPreferences("jiuan.sdk.author", 0);
				String refreshToken = sharedPreferences.getString("refreshToken", "");
				
				CommCloudSDK commCloudSDK = new CommCloudSDK(context);
				ReturnDataUser token_refresh = commCloudSDK.token_refresh(refreshToken, un, host);
				if("100".equals(token_refresh.getResultCode())){
					//保存新的accessToken, refreshToken到本地
					String accessToken = token_refresh.getAccessToken();
					//保存后重新调用上传
//					Log.i("BP_SDK", "重新调用bp_upload");
					BP_ReturnData bpUploadReturn1 = bp_upload(un, accessToken, bpArr,host);
					if("100".equals(bpUploadReturn1.getResultMessage())){
//						Log.i("BP_SDK", "刷新Token后上传血压数据成功!保存最新Token到本地");
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
					return bpUploadReturn;
				}
			} else if(resultMessage == 221.0){//Token失败->重新登录
				BP_ReturnData regReturnData = new BP_ReturnData();
				try {
//					regReturnData = reg(un);
					CommCloudSDK commCloudSDK = new CommCloudSDK(context);
					ReturnDataUser userSign = commCloudSDK.UserSign(client_id, client_secret, un, inputHost);
					if("100".equals(userSign.getResultCode())){
						regReturnData.setResultMessage(userSign.getResultCode());
						regReturnData.setRegionHost(userSign.getRegionHost());
						regReturnData.setAccessToken(userSign.getAccessToken());
						regReturnData.setRefreshToken(userSign.getRefreshToken());
						regReturnData.setExpires(userSign.getExpires());
						String accessToken = regReturnData.getAccessToken();
						BP_ReturnData bpUploadReturn1 = bp_upload(un, accessToken, bpArr, inputHost);
						if("100".equals(bpUploadReturn1.getResultMessage())){
							SharedPreferences sharedPreferences = context.getSharedPreferences("jiuan.sdk.author", 0);
							Editor edit = sharedPreferences.edit();
							String refreshToken = regReturnData.getRefreshToken();
							edit.putString("accessToken", accessToken);
							edit.putString("refreshToken", refreshToken);
							edit.commit();
							return bpUploadReturn1;
						}else{
							return bpUploadReturn1;
						}
					}else{
						return regReturnData;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				return bpUploadReturn;
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return bpUploadReturn;
		}
		return bpUploadReturn;
	}


	/**
	 * MD5 加密： 调用方法时只传明文密码，在本类中进行MD5加密
	 * 
	 * @param str
	 *            待加密的字符串
	 * @return
	 */
	private static String MD5(String str) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		char[] charArray = str.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}
		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = md5Bytes[i] & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		// Log.i("MD5",hexValue.toString());
		return hexValue.toString();
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
