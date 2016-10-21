/**
 * 
 */
package com.ihealth.communication.cloud.tools;

import com.ihealth.communication.cloud.CommCloudSDK;
import com.ihealth.communication.cloud.ReturnDataUser;
import com.ihealth.communication.cloud.UserCheckSDK;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @author zhaoyongguang
 * 
 * 2014年12月23日 星期二 18时49分07秒 CST
 *
 */
public class GetNewApiName {
	
	Context context;
	String username;
	
	public GetNewApiName(Context context, String username) {
		this.context = context;
		this.username = username;
//		super();
	}

	/**
	 * wz  2014年12月21日 星期日 10时04分42秒 CST
	 * 
	 */
	private final static String WEBSITE = "https://api.ihealthlabs.com:443";
	public void getNewAPIName() {
		
		final String username1 = username;

		new Thread() {
			public void run() {
				String un = context.getSharedPreferences(username1+"userinfo", 0).getString("email", "");
				String host = context.getSharedPreferences(username1+"userinfo", 0).getString("Host", "");//获取最优服务器
				if("".equals(host)){
					host = WEBSITE;
				}
				String client_id = context.getSharedPreferences(username1+"userinfo", 0).getString("client_id", "");
				String client_secret = context.getSharedPreferences(username1+"userinfo", 0).getString("client_secret", "");
				//				System.out.println(" 取出 un = " + un);
				//				System.out.println(" 取出 host = " + host);
				//				System.out.println(" 取出 client_id = " + client_id);
				//				System.out.println(" 取出 client_secret = " + client_secret);
				CommCloudSDK commCloudSDK = new CommCloudSDK(context);
				ReturnDataUser userSign = new ReturnDataUser();
				try {
					userSign = commCloudSDK.UserSign(client_id, client_secret, un, host);
				} catch (Exception e) {
					e.printStackTrace();
					//					System.out.println("errro!!!!");
				}
				if("100".equals(userSign.getResultCode())){
					if(userSign.getApiName() != null){
						//						System.out.println("  得到新的apinName = " + userSign.getApiName());
						UserCheckSDK.saveUserInfo(context, null, userSign.getApiName(),null,userSign.getAccessToken(),userSign.getRefreshToken(),null,null,userSign.getId());
						//认证通过，存状态
						SharedPreferences userInfo = context.getSharedPreferences("noNetWorkTime", 0);
						Editor editor = userInfo.edit();
						editor.putBoolean("IsIdentifed", true);
						editor.commit();
					}
				} else {
					//					System.out.println(" ! 100 ");
					//不处理
				}
			}
		}.start();
	}

}
