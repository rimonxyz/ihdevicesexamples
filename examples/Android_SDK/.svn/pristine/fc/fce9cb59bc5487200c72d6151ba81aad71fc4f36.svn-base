package com.ihealth.communication.cloud;

import java.net.SocketTimeoutException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.ihealth.communication.cloud.tools.AppIDFactory;
import com.ihealth.communication.cloud.tools.AppsDeviceParameters;
import com.ihealth.communication.cloud.tools.HttpsPost;

import android.content.Context;
import android.telephony.TelephonyManager;

public class CommCloudCenter {
	
	private static final String TAG = "CommCloudCenter";
	private static final String Sv_ServiceHostListByCountry_get = "b497716bec0b4850a0cc1d2026412d9a";
	private static final String Sv_PrivacyandAuthorizationDownload = "c1da25bfc46b4a638839f454bb96b725";

	private Context context;
	public String messageReturn = "";
	public int result = 0;
	public long TS = 0;
	public float resultMessage = 0;
	public int queueNum = 0;
	String QueueNum = "111111";//服务器写死的111111
	
	public CommCloudCenter(Context context) {
		this.context = context;
	}

	/**
	 * 获取服务器列表（地址） author_GYL 2014年5月16日 下午2:50:20
	 * @throws Exception 
	 * @throws SocketTimeoutException 
	 */
	public String ServiceHostListByCountry_get() throws Exception {

		String host = "";
		
		// 编辑发送参数
		Map<String, String> registerParams = new HashMap<String, String>();
		registerParams.put("sc", AppsDeviceParameters.SC);
		registerParams.put("sv", Sv_ServiceHostListByCountry_get);
		registerParams.put("AppVersion", AppsDeviceParameters.APP_VERSION);
		registerParams.put("AppGuid", getAppID());
		registerParams.put("PhoneOS", "android"+android.os.Build.VERSION.RELEASE);
		registerParams.put("PhoneName", android.os.Build.MODEL);
		registerParams.put("PhoneID", getDeviceID());
		registerParams.put("PhoneLanguage", Locale.getDefault().getLanguage());
		registerParams.put("PhoneRegion", Locale.getDefault().getCountry());
		registerParams.put("QueueNum", QueueNum);
		registerParams.put("Token", "");

		String webAddress = AppsDeviceParameters.AddressCenter + "ServiceHostListByCountry_get.htm";
		HttpsPost ht = new HttpsPost(context);


		messageReturn = ht.requireClass(webAddress, registerParams, "UTF-8");
	

		if (messageReturn.length() == 0)
			return host;

		// 分析收取的数据
		try {
			JSONTokener jsonTParser = new JSONTokener(messageReturn);
			JSONObject jsonORegist = (JSONObject) jsonTParser.nextValue();
			this.result = jsonORegist.getInt("Result");
			this.TS = Long.parseLong(jsonORegist.getString("TS"));
			this.resultMessage = Float.parseFloat(jsonORegist.getString("ResultMessage"));
			this.queueNum = jsonORegist.getInt("QueueNum");

			if (resultMessage == 100.0) {
				// ID 字段为预留，服务器端暂不发送
				JSONTokener jsonTokener = new JSONTokener(jsonORegist.getString("ReturnValue"));
				JSONObject jsonWebListOut = (JSONObject) jsonTokener.nextValue();

				JSONArray jsonWebListArr = jsonWebListOut.getJSONArray("HostList");
				int webListLength = jsonWebListArr.length();
				if (webListLength > 0) {

					int verify = 0;
					JSONObject jsonWebItemOb;
					for (int i = 0; i < webListLength; i++) {
						jsonWebItemOb = jsonWebListArr.optJSONObject(i);
						if (jsonWebItemOb == null) {
//							Log.i("返回服务器列表 条目 ", "jsonWebItemOb == null");
						} else {
							verify = jsonWebItemOb.getInt("HostType");
							if (1 == verify) {
								host = jsonWebItemOb.getString("HostUrl");
							}
						}
					}
				}

				return host;
			} else {
				return host;
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return host;
		}

	}
	
	/**
	 * 下载隐私条款和授权内容接口 author_WZ 2014年5月22日 上午11:13:20
	 * @throws Exception 
	 * @throws SocketTimeoutException 
	 */
	public ReturnDataCenter PrivacyandAuthorizationDownload(String client_id, String client_secret) throws Exception {
		
		ReturnDataCenter returnDataCenter = new ReturnDataCenter();
		
		// 编辑发送参数
		Map<String, String> Params = new HashMap<String, String>();
		Params.put("sc", AppsDeviceParameters.SC);
		Params.put("sv", Sv_PrivacyandAuthorizationDownload);
		Params.put("AppVersion", AppsDeviceParameters.APP_VERSION);
		Params.put("AppGuid", getAppID());
		Params.put("PhoneOS", "android"+android.os.Build.VERSION.RELEASE);
		Params.put("PhoneName", android.os.Build.MODEL);
	    Params.put("PhoneID", getDeviceID());
		Params.put("PhoneLanguage", Locale.getDefault().getLanguage());
		Params.put("PhoneRegion", Locale.getDefault().getCountry());
		Params.put("QueueNum", QueueNum);
		Params.put("Token", "");
	    
		Params.put("client_id", client_id);
		Params.put("client_secret", client_secret);
		Params.put("hash", MD5(client_id + "ihealth_API-SDK" + QueueNum));//MD5（client_id+”ihealth_API-SDK”+Queue_number）
		
		String webAddress = AppsDeviceParameters.AddressCenter + "PrivacyandAuthorizationDownload.htm";
		
		HttpsPost ht = new HttpsPost(context);

		messageReturn = ht.requireClass(webAddress, Params, "UTF-8");


		if (messageReturn.length() == 0)
			return returnDataCenter;

		// 分析收取的数据
		try {
			JSONTokener jsonTParser = new JSONTokener(messageReturn);
			JSONObject jsonORegist = (JSONObject) jsonTParser.nextValue();
			this.result = jsonORegist.getInt("Result");
			this.TS = Long.parseLong(jsonORegist.getString("TS"));
			this.resultMessage = Float.parseFloat(jsonORegist.getString("ResultMessage"));
			this.queueNum = jsonORegist.getInt("QueueNum");

			String returnCode = jsonORegist.getString("ResultMessage");
			returnDataCenter.setReturncode(returnCode);
			
			if (resultMessage == 100.0) {
				
				// ID 字段为预留，服务器端暂不发送
				JSONTokener jsonTokener = new JSONTokener(jsonORegist.getString("ReturnValue"));
				JSONObject jsonWebListOut = (JSONObject) jsonTokener.nextValue();
				
				//ContentList
				JSONArray jsonContentList = jsonWebListOut.getJSONArray("ContentList");//数组Json
				int contentListLength = jsonContentList.length();
				if (contentListLength > 0) {
					
					String Content = "";
					String content = "";
					int ContentType = 0;
					JSONObject jsonWebItemOb;
					
					for (int i = 0; i < contentListLength; i++) {
						jsonWebItemOb = jsonContentList.optJSONObject(i);
						if (jsonWebItemOb == null) {
						} else {
							Content = jsonWebItemOb.getString("Content");
							ContentType = jsonWebItemOb.getInt("ContentType");
							String contentType = "";
							if(ContentType == 1) {
								contentType = "Privacy:";
							} else if (ContentType == 2){
								contentType = "Terms:";
							} else {
								contentType = "Other:";
							}
							content += contentType + "\n"  + Content + "\n";
						}
					}
					returnDataCenter.setContent(content);
				}
				
				//AuthorizationList
				JSONArray jsonAuthorizationList = jsonWebListOut.getJSONArray("AuthorizationList");
				int authorizationListLength1 = jsonAuthorizationList.length();
				if (authorizationListLength1 > 0) {
					
					String APIShowName = "";
					String APIName = "";
					String APIDescription = "";
					JSONObject jsonWebItemOb;
					ArrayList<ApiData> apiInfo = new ArrayList<ApiData>();
					
					for (int i = 0; i < authorizationListLength1; i++) {
						jsonWebItemOb = jsonAuthorizationList.optJSONObject(i);
						if (jsonWebItemOb == null) {
						} else {
							APIName = jsonWebItemOb.getString("APIName");
							APIShowName = jsonWebItemOb.getString("APIShowName");
							APIDescription = jsonWebItemOb.getString("APIDescription");
							
							ApiData apiData = new ApiData();
							apiData.setAPIName(APIName);
							apiData.setAPIShowName(APIShowName);
							apiData.setAPIDescription(APIDescription);
							apiInfo.add(apiData);
						}
					}
					returnDataCenter.setApiInfo(apiInfo);
				}
				
			    //AuthorizationTitle
				String AuthorizationTitle = jsonWebListOut.getString("AuthorizationTitle");
				returnDataCenter.setAuthorizationTitle(AuthorizationTitle);
				
				return returnDataCenter;
			} else {
				return returnDataCenter;
			}

		} catch (JSONException e) {
			e.printStackTrace();
			return returnDataCenter;
		}
	}
	
	
	private String getDeviceID() {

		TelephonyManager telephonyManager = (TelephonyManager) this.context.getSystemService(Context.TELEPHONY_SERVICE);

		return (telephonyManager.getDeviceId() != null) ? telephonyManager.getDeviceId() : getAppID();
	}

	private String getAppID() {
		// String appID = getDeviceID();
		String appID = "appID";
		AppIDFactory appIDFactory = new AppIDFactory(context);
		appID = appIDFactory.getAppID();
		return appID;
	}

	/**
	 * @param str
	 * @return MD5加密算法 author_GYL 2013年11月21日 下午1:48:44
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
		return hexValue.toString();
	}
}
