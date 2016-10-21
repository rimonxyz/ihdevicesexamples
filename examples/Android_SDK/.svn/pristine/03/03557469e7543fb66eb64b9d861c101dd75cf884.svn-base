package com.ihealth.communication.cloud.tools;

import com.ihealth.communication.utils.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * AM自升级云接口
 *          包括：
 *          1，AM版本信息下载     cloundAMVersionDownload
 *          2，检查APP最新版本   cloundAMVersionCheck213
 *          
 * @author zhaoyongguang
 *
 */
public class CommCloudAMInstall {
	
	private static final String TAG = "CommCloudAMInstall";

	static final String Address = "https://api.ihealthlabs.com:443/upgrade"; //正式地址
//	static final String Address = "http://test.ihealthlabs.com:8000/upgrade";
	static final String sc = "4c60fce10c154ff2a3ebd4fbe040e782";
	//static final String sv_AmVersionCheck = "9a81fee6f56549439ff68adf71ca5211";
	static final String sv_AmFiledownload = "ace761655f754e11843fcd408517db5d";
	static final String sv_Amcheckverson2013 = "87a5c5fde8a1413bb34f1059e6a9a377";
	
	public int result; // 请求结果状态，1：成功，2：失败，3网站异常
	public long TS; // 时间戳，以UNIX时间表示(格林威治时间LONG INT 十进制)
	public float resultMessage; // 结果信息，根据定义的编码表示不同的错误，编码是Float型
	public int queueNum; // 每个接口需要新增协议顺序号，接口不做处理
	public String returnValue; // 返回的结果值，通常为请求需要得到的内容，以JSON格式体现
	public String messageReturn; // 存储服务器端返回的信息
	public String msgSent; // 测试用：回显发送的信息
	
	public class AMcheckRetrun {
		public String Description;	 //更新的文字说明
		public String latestversion; //当前产品在升级服务器上的最新固件版本(没有查找到任何固件的时候返回0.0.0三位版本号返回 0.0.0.0四位版本号返回)
		public int mandatoryupgrade; //判断客户端上传固件版本是否需要强制升级 0不强制 1强制
		public String[] BeforeImage; //更新之前的图片数组,如果没有,则返回[]
		public String[] AfterImage;  //更新之后的图片数组,如果没有,则返回[]

		public AMcheckRetrun() {
			this.Description = new String();
			this.latestversion = new String();
		}
	}

	
	public AMcheckRetrun amCheckReturn; // 下载的AM数据
	
	
	public boolean cloundAMVersionDownload(String version, String productnum, String productmodel, String hardwareversion, String testcode, int filetype,
			int beginblock, int endblock) {
		this.result = 0; // 返回结果的标识
		this.resultMessage = 0; // 返回结果编码
		this.returnValue = new String(); // 返回的有效信息
		this.messageReturn = new String(); // 返回的字符串
		this.msgSent = new String(); // 发送的信息

		try {
			
			// https://api.ihealthlabs.com:8443/upgrade/downloadblock.htm?
			// sc=4c60fce10c154ff2a3ebd4fbe040e782
			// & sv=ace761655f754e11843fcd408517db5d
			// & version=1.0.3
			// &productnum=0xAA
			// &productmodel=AM3%2011070
			// &hardwareversion=5.0.1
			// &filetype=1
			// &testcode=com.ihealthlabs.iHealth
			// &beginblock=0
			// &endblock=10000000
				
				
			// 编辑发送参数
			Map<String, String> AMVersionDownloadPOST = new HashMap<String, String>();
			AMVersionDownloadPOST.put("sc", sc);
			AMVersionDownloadPOST.put("sv", sv_AmFiledownload);
			AMVersionDownloadPOST.put("version", version);
			AMVersionDownloadPOST.put("productnum", productnum);
			AMVersionDownloadPOST.put("productmodel", productmodel);
			AMVersionDownloadPOST.put("hardwareversion", hardwareversion);
			AMVersionDownloadPOST.put("testcode", testcode);
			AMVersionDownloadPOST.put("filetype", filetype + "");
			AMVersionDownloadPOST.put("beginblock", beginblock + "");
			AMVersionDownloadPOST.put("endblock", endblock + "");

			// 发送地址
			final String webAddr = Address + "/downloadblock.htm?";

			msgSent = AMVersionDownloadPOST.toString();
			Log.i(TAG, "AMVersionDownloadGET = " + msgSent);

			// 发送数据并收取结果
//			httpPost ht = new httpPost();
			HttpClientGet htGet = new HttpClientGet();
			messageReturn = htGet.sendPOSTRequestForInputStream(webAddr, AMVersionDownloadPOST, "UTF-8", filetype);
//			Log.i(TAG, "AMVersionDownloadRETURN =" + messageReturn);

			// 返回信息检测，防止在返回空值的时候出现JSON解析异常
			if (messageReturn.equals("")) {
				Log.e(TAG, "AMVersionDownloadRETURN 返回信息检测，信息为空");
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public boolean cloundAMVersionCheck213(String version, String productnum, String productmodel, String hardwareversion, String testcode) {
		this.result = 0; // 返回结果的标识
		this.resultMessage = 0; // 返回结果编码
		this.returnValue = new String(); // 返回的有效信息
		this.messageReturn = new String(); // 返回的字符串
		this.msgSent = new String(); // 发送的信息
		this.amCheckReturn = new AMcheckRetrun(); // 接受返回的数据

		try {
			// 编辑发送参数
			Map<String, String> AMVersionCheckPOST = new HashMap<String, String>();

			AMVersionCheckPOST.put("sc", sc);
			AMVersionCheckPOST.put("sv", sv_Amcheckverson2013);
			AMVersionCheckPOST.put("hardwareversion", hardwareversion);
			AMVersionCheckPOST.put("productmodel", productmodel);
			AMVersionCheckPOST.put("productnum", productnum);
			AMVersionCheckPOST.put("version", version);
			AMVersionCheckPOST.put("testcode", testcode);
			AMVersionCheckPOST.put("Country", Locale.getDefault().getCountry());
			AMVersionCheckPOST.put("Lan", Locale.getDefault().getLanguage());
//			AMVersionCheckPOST.put("ImageWidth", AppsDeviceParameters.screenWidth + "");
//			AMVersionCheckPOST.put("ImageHeight", AppsDeviceParameters.screenHeigh + "");
			
			// 发送地址
			final String webAddr = Address + "/checkverson2013.htm?";

			msgSent = AMVersionCheckPOST.toString();
			Log.i(TAG, "AMVersionCheckPOST213 String= " + msgSent);

			// 发送数据并收取结果
//			httpsPost ht = new httpsPost();
			HttpPost ht = new HttpPost();
			messageReturn = ht.requireClass(webAddr, AMVersionCheckPOST, "UTF-8");
			Log.i("UpDeviceManager", "AMVersionCheckReturn213  String =" + messageReturn);

			// 返回信息检测，防止在返回空值的时候出现JSON解析异常
			if (messageReturn.equals("")) {
				Log.e(TAG, "cloundAMVersionCheck213 返回信息检测，信息为空");
				return false;
			}
			
			// 解析返回的字符串
			JSONObject jso = new JSONObject(messageReturn);
			
			amCheckReturn.Description = jso.getString("Description");
			amCheckReturn.latestversion = jso.getString("latestversion");
			amCheckReturn.mandatoryupgrade = jso.getInt("mandatoryupgrade");
			if(jso.getString("BeforeImage").equals("[]")){
				amCheckReturn.BeforeImage = new String[]{};
			}else{
				JSONArray JSA = new JSONArray(jso.getString("BeforeImage"));
				int length = JSA.length();
				amCheckReturn.BeforeImage = new String[length];
				for(int i = 0; i< length ; i++){
					amCheckReturn.BeforeImage[i] = JSA.optString(i);
				}
			}
			if(jso.getString("AfterImage").equals("[]")){
				amCheckReturn.AfterImage = new String[]{};
			}else{
				JSONArray JSA = new JSONArray(jso.getString("BeforeImage"));
				int length = JSA.length();
				amCheckReturn.AfterImage = new String[length];
				for(int i = 0; i< length ; i++){
					amCheckReturn.AfterImage[i] = JSA.optString(i);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
