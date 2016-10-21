package com.ihealth.communication.cloudmanager;

import java.util.ArrayList;

import com.ihealth.communication.cloud.ApiData;
import com.ihealth.communication.cloud.CommCloudCenter;
import com.ihealth.communication.cloud.ReturnDataCenter;
import com.ihealth.communication.cloud.UserCheckSDK;
import com.ihealth.communication.cloud.tools.GetNewApiName;
import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Looper;
import com.ihealth.communication.utils.Log;

/**
 * @hide
 */
public class iHealthDeviceCloudManager {

	private static final String TAG = "iHealthDeviceCloudManager"; 
	private Context mContext;
	private iHealthDevicesCallback miHealthDevicesCallback;
	public iHealthDeviceCloudManager(Context context, iHealthDevicesCallback ihealthDevicesCallback){
		this.mContext = context;
		this.miHealthDevicesCallback = ihealthDevicesCallback;
	}
	
    public void SDKUserInAuthor(final String userName, final String clientID, final String clientSecret) {
        new Thread(new Runnable() {
            @Override
			public void run() {
				userInauthor(userName, clientID, clientSecret);
			}
        }).start();
    }
    
    public boolean getDevicePermisson(String userName, String type){
    	if(isGuest){
    		return true;
    	}
    	SharedPreferences sharedPreferences= mContext.getSharedPreferences(userName +"userinfo", Context.MODE_PRIVATE); 
    	String userPermission = sharedPreferences.getString("apiName", null); 
    	if(userPermission == null)
    		return false;
    	
    	String am1 = "OpenApiActivity";
    	String am2 = "OpenApiSleep";
    	String po = "OpenApiSpO2";
    	String hs = "OpenApiWeight";
    	String bp = "OpenApiBP";
    	String bg = "OpenApiBG";
    	String[] test = null;
    	if(iHealthDevicesManager.TYPE_AM3.equals(type)
    			|| iHealthDevicesManager.TYPE_AM3S.equals(type)
    			|| iHealthDevicesManager.TYPE_AM4.equals(type)){
    		test = new String[]{am1, am2};
    		
    	}else if(iHealthDevicesManager.TYPE_BG1.equals(type)
    			|| iHealthDevicesManager.TYPE_BG5.equals(type)
				|| iHealthDevicesManager.TYPE_BG5l.equals(type)){
    		test = new String[]{bg};
    		
    	}else if(iHealthDevicesManager.TYPE_BP3L.equals(type)
    			|| iHealthDevicesManager.TYPE_BP3M.equals(type)
    			|| iHealthDevicesManager.TYPE_BP5.equals(type)
    			|| iHealthDevicesManager.TYPE_BP7.equals(type)
    			|| iHealthDevicesManager.TYPE_BP7S.equals(type)
    			|| iHealthDevicesManager.TYPE_550BT.equals(type)
    			|| iHealthDevicesManager.TYPE_KD926.equals(type)
				|| iHealthDevicesManager.TYPE_KD723.equals(type)
				|| iHealthDevicesManager.TYPE_ABPM.equals(type)
				|| iHealthDevicesManager.TYPE_CBP.equals(type)){
    		test = new String[]{bp};
    		
    	}else if(iHealthDevicesManager.TYPE_HS3.equals(type)
    			|| iHealthDevicesManager.TYPE_HS4.equals(type)
    			|| iHealthDevicesManager.TYPE_HS4S.equals(type)
    			|| iHealthDevicesManager.TYPE_HS5.equals(type)
    			|| iHealthDevicesManager.TYPE_HS6.equals(type)
    			|| iHealthDevicesManager.TYPE_HS5_BT.equals(type)){
    		test = new String[]{hs};
    		
    	}else if(iHealthDevicesManager.TYPE_PO3.equals(type)){
    		test = new String[]{po};
    	}else{
    		return false;
    	}
    	for (String string : test) {
    		if(userPermission.contains(string)){
    			return true;
    		}
		}
    	return false;
    }
    
    private boolean isGuest = false;
	private void userInauthor(String userName, String clientID, String clientSecret) {
        String[] apiName = new String[] {
        		"OpenApiActivity", "OpenApiSleep", "OpenApiSpO2", "OpenApiWeight", "OpenApiBP", "OpenApiBG"
        };
        String email = userName;
        UserCheckSDK userCheckSDK = new UserCheckSDK();
        int num = userCheckSDK.CheckSDK(this.mContext, apiName, clientID, clientSecret, email);
//        Log.i(TAG, "userCheckSDK:" + num);
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(userName + "userinfo", 0);
        int userId = mSharedPreferences.getInt("user_ID", 0);

        switch (num) {
            case 1:
                // 认证通过，存状态
                SharedPreferences userInfo = this.mContext.getSharedPreferences("noNetWorkTime", 0);
                Editor editor = userInfo.edit();
                editor.putBoolean("IsIdentifed", true);
                editor.commit();
                miHealthDevicesCallback.onUserStatus(userName, 1);
                break;
            case 2:
                GetNewApiName gnan = new GetNewApiName(mContext, email);
                gnan.getNewAPIName();
                miHealthDevicesCallback.onUserStatus(userName, 2);
                break;
            case 3:
                // 认证通过，存状态
                SharedPreferences userInfo1 = this.mContext.getSharedPreferences("noNetWorkTime", 0);
                Editor editor1 = userInfo1.edit();
                editor1.putBoolean("IsIdentifed", true);
                editor1.commit();
                miHealthDevicesCallback.onUserStatus(userName, 3);
                break;
            case 4:
            	isGuest = true;
            	miHealthDevicesCallback.onUserStatus(userName, 4);
                break;
            case 5:
            	miHealthDevicesCallback.onUserStatus(userName, 5);
                break;
            case 6:
            	miHealthDevicesCallback.onUserStatus(userName, 6);
                break;
            case 7:
            	miHealthDevicesCallback.onUserStatus(userName, 7);
                break;
            case 8:
            	miHealthDevicesCallback.onUserStatus(userName, 8);
                break;
            case 33:
                getNoIhealth(this.mContext, userName, clientID, clientSecret);
                break;
            default:
                break;
        }
    }
	
	 private void getNoIhealth(final Context con, final String userName, final String clientID, final String clientSecret) {
	        new Thread() {
	            public void run() {
	                // 模拟数据
	                final String apiName = "OpenApiWeight";
	                final String email = userName;

	                // 下载免责声明及权限说明
	                CommCloudCenter commCloudCenter = new CommCloudCenter(con);
	                ReturnDataCenter returnDataCenter = null;
	                String message = "";
	                try {
	                    returnDataCenter = commCloudCenter.PrivacyandAuthorizationDownload(clientID, clientSecret);
	                    ArrayList<ApiData> apiInfo = returnDataCenter.getApiInfo();
	                    ApiData apiData;
	                    if (apiInfo.size() > 0 && apiInfo != null) {
	                        for (int i = 0; i < apiInfo.size(); i++) {
	                            apiData = apiInfo.get(i);
	                            message += apiData.getAPIShowName() + ":\n" + apiData.getAPIDescription() + "\n";
	                        }
	                    }
	                    message = returnDataCenter.getContent() + "Authorization : " + "\n" + message;

	                } catch (Exception e1) {
	                	miHealthDevicesCallback.onUserStatus(userName, 8);// 网络出问题
	                }

	                Looper.prepare();
	                new AlertDialog.Builder(con).setTitle(returnDataCenter.getAuthorizationTitle())
	                        .setMessage(message)
	                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	                            @Override
	                            public void onClick(DialogInterface dialog, int which) {
	                                new Thread() {
	                                    public void run() {
	                                        // 取最优服务器
	                                        CommCloudCenter commCloudCenter = new CommCloudCenter(con);
	                                        String host;
	                                        try {
	                                            host = commCloudCenter.ServiceHostListByCountry_get();
	                                            final int msgcode = UserCheckSDK.entry(con, clientID, clientSecret, email,
	                                                    apiName, host);
	                                            new Thread() {
	                                                public void run() {
	                                                	miHealthDevicesCallback.onUserStatus(userName, msgcode);
	                                                }
	                                            }.start();
	                                            if (msgcode == 1 || msgcode == 2 || msgcode == 3) {
	                                            }
	                                        } catch (Exception e) {
	                                        	miHealthDevicesCallback.onUserStatus(userName, 8);// 网络出问题
	                                        }
	                                    }
	                                }.start();
	                            }
	                        }).setOnCancelListener(new DialogInterface.OnCancelListener() {
	                            @Override
	                            public void onCancel(DialogInterface dialog) {
	                            	miHealthDevicesCallback.onUserStatus(userName, 7);
	                            }
	                        }).show();
	                Looper.loop();
	            }
	        }.start();
	    }
}
