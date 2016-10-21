package com.ihealth.communication.cloud;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ihealth.communication.cloud.tools.AppsDeviceParameters;

public class UserCheckSDK {

    public final static int UserAuthen_RegisterSuccess = 1;//新用户注册成功
    public final static int UserAuthen_LoginSuccess = 2;//用户登录成功
    public final static int UserAuthen_CombinedSuccess = 3;//用户为iHealth用户，增加了SDK测量功能，通过SDK测量产生的测量数据也属于此用户。
    public final static int UserAuthen_TrySuccess = 4;//网络异常仅测试使用

    public final static int UserAuthen_InvalidateUserInfo = 5;//userid或clientID或clientSecret验证失败
    public final static int UserAuthen_SDKInvalidateRight = 6;//应用无此权限
    public final static int UserAuthen_UserInvalidateRight = 7;//用户无此权限
    public final static int UserAuthen_InternetError = 8;//网络异常导致验证失败

    private static final String DEVICE_PERMISSION = "ihealthdevicepermission";

    public static int returncode;
    //第三方开启APP前SDK验证
    //首次启动SDK要获取服务器列表选择最优服务器
    //0:SDK验证通过,可以执行开启应用
    //1:本地存在配置,但不包含动作权限
    //2:ihealthAddSDK登录成功,不包含动作 OR 登录失败
    //3:ihealth用户，非SDK用户,合并用户失败
    //4:ihealth用户，非SDK用户,合并后不包含动作
    //5:非ihealth用户,创建用户失败
    //6:非ihealth用户,创建用户成功,不包含动作
    //7:获取用户状态失败(非1,2,3)  --> UserAuthen_InternetError，网络异常导致验证失败
    //8:ihealth用户，非SDK用户 合并用户确认-否 (由于取消了合并用户选择,废弃)
    //9:未定义的错误(弹出隐私条款和授权窗口时点击返回,放弃登录)
    //10:本地存在配置的情况下(非首次登录),登录失败,更新授权信息失败
    //11:无网络访问大于10天		--> UserAuthen_InternetError，网络异常导致验证失败

    /**
     * 第三方开启APP前SDK验证
     *
     * @param context            context
     * @param apiName2           要执行的动作
     * @param client_id          来自第三方
     * @param client_secret      来自第三方
     * @param username           第三方用户名
     * @return UserAuthen_RegisterSuccess=1,新用户注册成功
     * UserAuthen_LoginSuccess=2,用户登录成功
     * UserAuthen_CombinedSuccess=3,用户为iHealth用户，增加了SDK测量功能，通过SDK测量产生的测量数据也属于此用户。
     * UserAuthen_TrySuccess=4,网络异常仅测试使用
     * <p/>
     * UserAuthen_InvalidateUserInfo=5, userid或clientID或clientSecret验证失败
     * UserAuthen_SDKInvalidateRight=6,应用无此权限
     * UserAuthen_UserInvalidateRight=7,用户无此权限
     * UserAuthen_InternetError=8，网络异常导致验证失败
     */
    public int CheckSDK(Context context, String[] apiName2, String client_id, String client_secret, String username) {

        CommCloudSDK commCloudSDK = new CommCloudSDK(context);
        int returnCode = 0;
        //判断邮箱格式
        if (!checkEmail(username) || (username.length() > 129)) {
            return UserAuthen_InvalidateUserInfo;
        }
        if (!isNetworkAvailable(context)) {
            return noNetWork(context);
        } else {
            //获取配置文件中的最优服务器,如果没有访问网络并保存到本地
            String host = getAndSavehost(context, username);

            //判断是否是第一次的标准-是否保存了ApiName-成功后才保存的ApiName
            SharedPreferences user = context.getSharedPreferences(username + "userinfo", 0);
            String apiName = user.getString("apiName", "");

            if (apiName.equals("")) {
                //System.out.println("首次使用SDK,本地配置不存在!!!");

                //获取用户状态--接口测试通过
                int status = 0;

                ReturnDataUser userExistForThird = new ReturnDataUser();
                try {
                    userExistForThird = commCloudSDK.UserExistForThird(client_id, client_secret, username, host);
                } catch (Exception e) {
                    return UserAuthen_InternetError;//网络异常导致验证失败
                }
                //再次获取Host
                host = getAndSavehost(context, username);

                String resultCode = userExistForThird.getResultCode();
                if ("100".equals(resultCode)) {
                    status = userExistForThird.getStatus();
                    //记录client_id,client_secret,接口内部已经保存了生效的Host
                    saveUserInfo(context, username, null, null, null, null, client_id, client_secret, userExistForThird.getId());
                    //至此已经保存了 username Host client_id client_secret 还差Token ApiName
                    if (status == 3) {
                        //returnCode = 33;//noIhealth(context, apiName_in, client_id, client_secret, username, host);	//非ihealth用户
                        returnCode = userRegisterAndCheck(context, apiName2, client_id, client_secret, username);

                    } else if (status == 2) {
                        returnCode = ihealthNoSDK(context, apiName2, client_id, client_secret, username, host); //ihealth用户，非SDK用户
                    } else if (status == 1) {
                        returnCode = ihealthAddSDK(context, apiName2, client_id, client_secret, username, host);//ihealth用户，SDK用户
                    } else {
                        returnCode = UserAuthen_InternetError;//网络异常导致验证失败
                    }
                } else if ("223".equals(resultCode) || "224".equals(resultCode) || "225".equals(resultCode)) {
                    return UserAuthen_InvalidateUserInfo;
                } else {
                    returnCode = UserAuthen_InternetError;//网络异常导致验证失败
                }
                return returnCode;
            } else {//存在 判断该Email是否有指定ApiName权限

				/*与iOS验证流程保持一致：
                 * 1.本地无权限，联网更新权限；
				 * 2.本地有权限，打开app，打开成功，联网更新权限；*/
//				//1,检查本地是否有eMail信息
                boolean bl = false;
                for (String string : apiName2) {
                    if (apiName.contains(string)) {
                        bl = true;
                        SharedPreferences mySharedPreferences = context.getSharedPreferences(DEVICE_PERMISSION, Context.MODE_PRIVATE);
                        Editor editor = mySharedPreferences.edit();
                        editor.putString(client_id, apiName);
                        editor.commit();
                        break;
                    }
                }//判断是否包含动作
                if (bl) {
                    return UserAuthen_LoginSuccess;//用户登录成功
                } else {
//					System.out.println("比对结果 失败 调用登陆重新获取" );
                    ReturnDataUser userSign = new ReturnDataUser();
                    try {
                        userSign = commCloudSDK.UserSign(client_id, client_secret, username, host);
                    } catch (Exception e) {
                        return UserAuthen_InternetError;//网络异常导致验证失败
                    }
                    if ("100".equals(userSign.getResultCode())) {
//						System.out.println("重新获取的apiname ＝ "+userSign.getApiName());
                        boolean bl1 = false;
                        for (String string : apiName2) {
                            if ((userSign.getApiName()).contains(string)) {
                                bl = true;
                                break;
                            }
                        }//判断是否包含动作
                        if (bl1) {
//							System.out.println("重新比对结果ok");
                            saveUserInfo(context, null, userSign.getApiName(), null, userSign.getAccessToken(), userSign.getRefreshToken(), null, null, userSign.getId());
                            return UserAuthen_LoginSuccess;//用户登录成功
                        } else {
                            //更新权限之后也不包含 返回权限认证错误
                            return UserAuthen_UserInvalidateRight;//用户无此权限
                        }
                    } else if ("223".equals(userSign.getResultCode()) || "224".equals(userSign.getResultCode())) {
                        return UserAuthen_InvalidateUserInfo;
                    } else {
                        return UserAuthen_InternetError;//网络异常导致验证失败
                    }
                }
            }
        }
    }

    /**
     * code 33 省略下载隐私条款 直接注册新用户，并验证SDK权限
     *
     * @return
     * @author zhaoyongguang
     */
    public int userRegisterAndCheck(Context context, String[] apiName2, String client_id, String client_secret, String username) {

        int result = UserAuthen_InternetError;
        // 取最优服务器
        CommCloudCenter commCloudCenter = new CommCloudCenter(context);
        String host;
        try {
            host = commCloudCenter.ServiceHostListByCountry_get();
            result = UserCheckSDK.entry(context, client_id, client_secret, username,
                    apiName2[0], host);
        } catch (Exception e) {

        }
        return result;
    }

    /**
     * Email 验证
     *
     * @param email
     * @return
     */
    private Boolean checkEmail(String email) {
        String str = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    //无网络时处理,保存系统时间到本地,非首次时与首次时间对比,大于10天弹框强制联网
    private int noNetWork(Context context) {
        String data_old = "";
        String data_now = "";

        // TODO Auto-generated method stub
        SharedPreferences userInfo = context.getSharedPreferences("noNetWorkTime", 0);
        String data_before = userInfo.getString("Time", "");
        if ("".equals(data_before)) {
//			System.out.println("非联网,首次登陆,保存时间到本地文件");
            Editor editor = userInfo.edit();
            data_old = millisToData(System.currentTimeMillis());//毫秒转日期
            editor.putString("Time", data_old);
            editor.commit();
//			return 0 ;
            return UserAuthen_TrySuccess;//网络异常仅测试使用
        } else {//比对
//			System.out.println("非联网,非首次登陆,对比时间差是否大于十天");
            long time_now = System.currentTimeMillis();
            data_now = millisToData(time_now);//毫秒转日期
//			data_now = "2014-06-16 08:58:56";//模拟测试
            boolean bl = Compare(data_before, data_now);//是否大于十天

            if (!bl) {//小于10天
//				return 0 ;
                return UserAuthen_TrySuccess;//网络异常仅测试使用
            } else {
//				Looper.prepare();
//				new AlertDialog.Builder(context)
//				.setTitle("联网警告")
//				.setMessage("非联网状态使用大于10天,请联网使用!")
//				.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						// TODO Auto-generated method stub
//						AlertNetEntry();
//					}
//				}).show();
//				Looper.loop();
//				System.out.println("无网络访问大于10天!!!");
//				return 11;
                boolean Identify = userInfo.getBoolean("IsIdentifed", false);
                if (Identify) {//未联网超过十天，曾经认证过
                    return UserAuthen_TrySuccess;//网络异常仅测试使用
                } else {//未联网超过十天，没有认证过
                    return UserAuthen_InternetError;//网络异常导致验证失败
                }
            }
        }
    }

    //毫秒转换为日期
    private static String millisToData(long time_now) {
        // TODO Auto-generated method stub

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time_now);
        String data = formatter.format(calendar.getTime());
//		 System.out.println("毫秒转换为日期 time_now = " + data);

        return data;
    }

    //比较两个时间是否大于10天
    private boolean Compare(String data_before, String data_now) {
        // TODO Auto-generated method stub
        long dayCount = 0;//天数差
        // 日期转换为毫秒 两个日期想减得到天数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start = data_before;
        String end = data_now;

        //得到毫秒数
        try {
            long timeStart = sdf.parse(start).getTime();
            long timeEnd = sdf.parse(end).getTime();
//			System.out.println(timeStart + " ==== " + sdf.format(timeStart));
//			System.out.println(timeEnd + " ==== " + sdf.format(timeEnd));
            //两个日期想减得到天数
            dayCount = (timeEnd - timeStart) / (24 * 3600 * 1000);
//			System.out.println("天数差 = " + dayCount);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dayCount <= 10) {
            return false;
        } else {
            return true;
        }

    }

    //保存得到的用户信息
    public static void saveUserInfo(Context context, String username, String apiName, String host,
                                    String accessToken, String refreshToken, String client_id, String client_secret, int userID) {
        // TODO Auto-generated method stub
//		System.out.println("保存得到的用户信息");

        SharedPreferences userInfo = context.getSharedPreferences(username + "userinfo", 0);
        Editor editor = userInfo.edit();

        if (username != null) {
            editor.putString("email", username);
        }
        if (apiName != null) {
            editor.putString("apiName", apiName);
        }
        if (host != null) {
            editor.putString("Host", host);
        }
        if (accessToken != null) {
            editor.putString("accessToken", accessToken);
        }
        if (refreshToken != null) {
            editor.putString("refreshToken", refreshToken);
        }
        if (client_id != null) {
            editor.putString("client_id", client_id);
        }
        if (client_secret != null) {
            editor.putString("client_secret", client_secret);
        }
        if (userID != -1 && userID != 0) {
            editor.putInt("user_ID", userID);
        }

        editor.commit();
    }

    //ihealth用户，非SDK用户
    private int ihealthNoSDK(Context context, String[] apiName2, String client_id, String client_secret, String username, String host) {
        // TODO Auto-generated method stub
        return combineOk(context, client_id, client_secret, username, apiName2, host);

    }

    //ihealth用户，SDK用户
    private int ihealthAddSDK(Context context, String[] apiName2, String client_id, String client_secret, String username, String host) {
        // TODO Auto-generated method stub
//		System.out.println("ihealth用户，SDK用户");

        //告知cloud用户登录
//		System.out.println("调用登录接口");
        CommCloudSDK commCloudSDK = new CommCloudSDK(context);
        ReturnDataUser userSign = new ReturnDataUser();
        try {
            userSign = commCloudSDK.UserSign(client_id, client_secret, username, host);
        } catch (Exception e) {
            return UserAuthen_InternetError;//网络异常导致验证失败
        }

        if ("100".equals(userSign.getResultCode())) {//登录成功
            boolean bl = false;
            for (String string : apiName2) {
                if ((userSign.getApiName()).contains(string)) {
                    bl = true;
                    break;
                }
            }//判断是否包含动作
            if (bl) {//包含动作 该干嘛干嘛去
                //保存信息到本地
                //接口中保存生效的Host,外面不保存
                saveUserInfo(context, username, userSign.getApiName(), null, userSign.getAccessToken(), userSign.getRefreshToken(), null, null, userSign.getId());
                saveFirstTime(context);
                return UserAuthen_LoginSuccess;//用户登录成功
            } else {
                return UserAuthen_UserInvalidateRight;//用户无此权限
            }
        } else if ("223".equals(userSign.getResultCode()) || "224".equals(userSign.getResultCode())) {
            return UserAuthen_InvalidateUserInfo;
        } else { //登录成功
            return UserAuthen_InternetError;//网络异常导致验证失败
        }
    }

    //非ihealth用户,创建用户
    public static int entry(Context context, String client_id, String client_secret, String username, String apiName_in, String host) {

//		System.out.println("调用创建用户接口");
        //点击"是",创建用户
        CommCloudSDK commCloudSDK = new CommCloudSDK(context);
        ReturnDataUser userRegister = new ReturnDataUser();
        try {
            userRegister = commCloudSDK.UserRegister(client_id, client_secret, username, host);
        } catch (Exception e) {
            return UserAuthen_InternetError;//网络异常导致验证失败
        }
//		System.out.println("返回码 = "+userRegister.getResultCode());

        if ("100".equals(userRegister.getResultCode())) {
//			System.out.println("apiName比较 : " + userRegister.getApiName() );
//			System.out.println("apiName_IN : " + apiName_in );
            boolean bl = (userRegister.getApiName()).contains(apiName_in);//判断是否包含动作
//			System.out.println("判断存在动作权限?");
            if (bl) {
//				System.out.println("登录成功,保存用户信息到本地");
                //保存信息到本地
                saveUserInfo(context, username, userRegister.getApiName(), host, userRegister.getAccessToken(), userRegister.getRefreshToken(), null, null, userRegister.getId());
                saveFirstTime(context);
//				return 0;
                return UserAuthen_RegisterSuccess;//新用户注册成功
            } else if ("223".equals(userRegister.getResultCode()) || "224".equals(userRegister.getResultCode())) {
                return UserAuthen_InvalidateUserInfo;
            } else {
//				System.out.println("登录成功,但不包含动作");
//				return 6;//非ihealth用户,创建用户成功,不包含动作
                return UserAuthen_UserInvalidateRight;//用户无此权限
            }
        } else {
//			return 5;//非ihealth用户,创建用户失败
            return UserAuthen_InternetError;//网络异常导致验证失败
        }
    }

    //首次登录成功,记录首次登录时间用于断网10天教验
    private static void saveFirstTime(Context context) {
        // TODO Auto-generated method stub
        SharedPreferences userInfo = context.getSharedPreferences("noNetWorkTime", 0);
//		System.out.println("首次登录成功,记录首次登录时间用于断网10天教验");
        Editor editor = userInfo.edit();
        String data = millisToData(System.currentTimeMillis());//毫秒转日期
        editor.putString("Time", data);
        editor.commit();
    }

    //获取配置文件中的最优服务器放入配置文件,如果没有访问网络并保存到本地
    private static String getAndSavehost(Context context, String username) {
        // TODO Auto-generated method stub
        String host;
        SharedPreferences userInfo = context.getSharedPreferences(username + "userinfo", 0);
        host = userInfo.getString("Host", "");
        if ("".equals(host)) {
            host = AppsDeviceParameters.webSite;//使用默认Host
        }

        return host;
    }

    //合并用户确认-是
    private int combineOk(Context context, String client_id, String client_secret, String username, String[] apiName2, String host) {
//		System.out.println("调用用户数据合并接口");
        //合并数据
        CommCloudSDK commCloudSDK = new CommCloudSDK(context);
        ReturnDataUser userCombine = new ReturnDataUser();
        try {
            userCombine = commCloudSDK.UserCombine(client_id, client_secret, username, host);
        } catch (Exception e) {
            return UserAuthen_InternetError;//网络异常导致验证失败
        }

//		System.out.println("返回码:" + userCombine.getResultCode());

        if ("100".equals(userCombine.getResultCode())) {
            boolean bl = false;
            for (String string : apiName2) {
                if ((userCombine.getApiName()).contains(string)) {
                    bl = true;
                    break;
                }
            }//判断是否包含动作
            if (bl) { //合并后包含动作  该干嘛干嘛去
//				System.out.println("登录成功,保存用户信息到本地");
                //保存信息到本地
                saveUserInfo(context, username, userCombine.getApiName(), null, userCombine.getAccessToken(), userCombine.getRefreshToken(), null, null, userCombine.getId());
                saveFirstTime(context);
                return UserAuthen_CombinedSuccess;//用户为iHealth用户，增加了SDK测量功能，通过SDK测量产生的测量数据也属于此用户。
            } else {//合并后不包含动作
                return UserAuthen_UserInvalidateRight;//用户无此权限
            }
        } else if ("223".equals(userCombine.getResultCode()) || "224".equals(userCombine.getResultCode())) {
            return UserAuthen_InvalidateUserInfo;
        } else {
            return UserAuthen_InternetError;//网络异常导致验证失败
        }
    }

    /**
     * 网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = mgr.getAllNetworkInfo();
        if (info != null) {
            for (int i = 0; i < info.length; i++) {
                if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String getDate() {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd HH/mm/ss");
        Date dd = new Date();
        return ft.format(dd);
    }

    private static long getQuot(String time1, String time2) {
        long quot = 0;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date date1 = ft.parse(time1);
            Date date2 = ft.parse(time2);
            quot = date1.getTime() - date2.getTime();
            quot = quot / 1000 / 60 / 60 / 24;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return quot;
    }
}
