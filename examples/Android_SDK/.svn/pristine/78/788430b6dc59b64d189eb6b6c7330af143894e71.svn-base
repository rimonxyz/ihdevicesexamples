
package com.ihealth.communication.cloud.data;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.ihealth.communication.cloud.UserCheckSDK;
import com.ihealth.communication.cloud.tools.AppsDeviceParameters;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import com.ihealth.communication.utils.Log;

/**
 * Start_timer->执行SDK上传Start_timer接口
 * 
 * @author brave
 */

public class HS_Up {

    private static final String TAG = "HS-sdk_Up";

    public final Timer timer = new Timer();
    public TimerTask task;
    private String un;
    public Context context;
    private String accessToken, refreshToken, host;

    public HS_Up(Context context) {
        if (AppsDeviceParameters.isLog)
            Log.i(TAG, "实例化sdk_Up,取本地配置-jiuan.sdk.author");

        un = context.getSharedPreferences("jiuan.sdk.author", 0).getString("email", "");
        accessToken = context.getSharedPreferences("jiuan.sdk.author", 0).getString("accessToken", "");
        refreshToken = context.getSharedPreferences("jiuan.sdk.author", 0).getString("refreshToken", "");
        host = context.getSharedPreferences("jiuan.sdk.author", 0).getString("Host", "");

        if ("".equals(host)) {
            host = AppsDeviceParameters.webSite;
        }
        this.context = context;
        if (AppsDeviceParameters.isLog) {
            Log.i(TAG, "取得 un = " + un);
            Log.i(TAG, "取得 verifyToken = " + accessToken);
            Log.i(TAG, "取得 refreshToken = " + refreshToken);
            Log.i(TAG, "取得 host = " + host);
        }
    }

    /**
     * @param un2
     * @return 开启AM云定时器，30秒更新一次
     */
    public void Start_timer() {

        task = new TimerTask() {
            public void run() {
                if (UserCheckSDK.isNetworkAvailable(context)) {

                    ArrayList<Data_HS_Result> hsList = null;
                    hsList = collecBgData();// 从数据库取数

                    if (hsList == null) {
                        if (AppsDeviceParameters.isLog)
                            Log.i(TAG, "无数据上传");
                    } else if (hsList != null && hsList.size() > 0) {
                        if (AppsDeviceParameters.isLog)
                            Log.i(TAG, "有数据上传");
                        boolean upState = false;// 上传标记 true 上传成功
                        try {
                            HS_CommCloud comm = new HS_CommCloud(context);
                            HS_ReturnData bgUploadReturn = comm.weight_upload(un, accessToken, hsList, host);
                            String resultMessage = bgUploadReturn.getResultMessage();

                            if ("100".equals(resultMessage)) {
                                upState = true;
                            } else {
                                upState = false;
                            }
                            if (AppsDeviceParameters.isLog)
                                Log.i(TAG, "上传结果 = " + upState);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // 上传成功后删除数据库中的数据
                        if (upState) {

                            DataBaseTools db = new DataBaseTools(context);
                            boolean asd = false;
                            for (Data_HS_Result hsr : hsList) {
                                asd = db.deleteData(
                                        DataBaseConstants.TABLE_TB_HSRESULT,
                                        DataBaseConstants.HSRESULT_IHEALTHID + " = '" + un
                                                + "' and " + DataBaseConstants.HSRESULT_PHONEDATAID + " = '"
                                                + hsr.getPhoneDataID() + "'");
                            }

                            if (AppsDeviceParameters.isLog)
                                Log.i(TAG, "删除结果 = " + asd);

                            // 关闭数据库连接
                        }

                        if (AppsDeviceParameters.isLog) {
                            SharedPreferences sharedPreferences = context.getSharedPreferences("jiuan.sdk.author", 0);
                            String email = sharedPreferences.getString("email", "");
                            String apiName = sharedPreferences.getString("apiName", "");
                            String Host = sharedPreferences.getString("Host", "");
                            String accessToken = sharedPreferences.getString("accessToken", "");
                            String refreshToken = sharedPreferences.getString("refreshToken", "");
                            String client_id = sharedPreferences.getString("client_id", "");
                            String client_secret = sharedPreferences.getString("client_secret", "");

                            Log.i(TAG, "上传BP数据完成后读取BP_SDK配置");
                            Log.i(TAG, "email = " + email);
                            Log.i(TAG, "apiName = " + apiName);
                            Log.i(TAG, "Host = " + Host);
                            Log.i(TAG, "accessToken = " + accessToken);
                            Log.i(TAG, "refreshToken = " + refreshToken);
                            Log.i(TAG, "client_id = " + client_id);
                            Log.i(TAG, "client_secret = " + client_secret);
                        }

                        // 再取一次验证删除
                        DataBaseTools db1 = new DataBaseTools(context);
                        Cursor cur = db1.selectData(DataBaseConstants.TABLE_TB_HSRESULT, null,
                                DataBaseConstants.HSRESULT_IHEALTHID + "='" + un + "'");
                        if (AppsDeviceParameters.isLog)
                            Log.i(TAG, "再取一次验证删除成功 ==> 取得条目数 = " + cur.getCount());
                    }
                } else {
                    if (AppsDeviceParameters.isLog)
                        Log.e(TAG, "当前无网络连接!");
                }
            }
        };
        try {
            timer.schedule(task, 1000, 30000);
        } catch (IllegalStateException e) {
            // TODO: handle exception
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return 停止云定时器
     */
    public void Stop_timer() {
        timer.cancel();
    }

    // 取存储在数据库中的数据放入List准备上传到服务器
    private ArrayList<Data_HS_Result> collecBgData() {

        DataBaseTools db = new DataBaseTools(this.context);
        Cursor cur = db.selectData(DataBaseConstants.TABLE_TB_HSRESULT, null, DataBaseConstants.HSRESULT_IHEALTHID
                + "='" + un + "'");
        cur.moveToFirst();

        if (cur.getCount() > 0) {

            ArrayList<Data_HS_Result> hsArr = new ArrayList<Data_HS_Result>();// 返回的数据集合

            for (int i = 0; i < cur.getCount(); i++) {
                Data_HS_Result bpo = new Data_HS_Result();

                // 添加来自数据库的数据到数据类-准备上传
                bpo.setChangeType(cur.getInt(cur.getColumnIndex(DataBaseConstants.HSRESULT_CHANGETYPE)));
                bpo.setLastChangeTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.HSRESULT_LASTCHANGETIME)));
                bpo.setPhoneDataID(cur.getString(cur.getColumnIndex(DataBaseConstants.HSRESULT_PHONEDATAID)));
                bpo.setPhoneCreateTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.HSRESULT_PHONECREATETIME)));
                bpo.setLat(cur.getFloat(cur.getColumnIndex(DataBaseConstants.HSRESULT_LAT)));
                bpo.setLon(cur.getFloat(cur.getColumnIndex(DataBaseConstants.HSRESULT_LON)));
                bpo.setTimeZone(cur.getFloat(cur.getColumnIndex(DataBaseConstants.HSRESULT_TIMEZONE)));
                bpo.setBMI(cur.getFloat(cur.getColumnIndex(DataBaseConstants.HSRESULT_BMI)));// BMI
                bpo.setBoneValue(cur.getFloat(cur.getColumnIndex(DataBaseConstants.HSRESULT_BONEVALUE)));// 骨密度
                bpo.setDCI(cur.getFloat(cur.getColumnIndex(DataBaseConstants.HSRESULT_DCI)));// DCI每天最低摄入量
                bpo.setFatValue(cur.getFloat(cur.getColumnIndex(DataBaseConstants.HSRESULT_FATVALUE)));
                bpo.setMuscaleValue(cur.getFloat(cur.getColumnIndex(DataBaseConstants.HSRESULT_MUSCALEVALUE)));
                bpo.setMeasureType(cur.getInt(cur.getColumnIndex(DataBaseConstants.HSRESULT_MEASURETYPE)));
                bpo.setWaterValue(cur.getInt(cur.getColumnIndex(DataBaseConstants.HSRESULT_WATERVALUE)));
                bpo.setWeightValue(cur.getFloat(cur.getColumnIndex(DataBaseConstants.HSRESULT_WEIGHTVALUE)));
                bpo.setMeasureTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.HSRESULT_MEASURETIME)));
                bpo.setNote(cur.getString(cur.getColumnIndex(DataBaseConstants.HSRESULT_NOTE)));
                bpo.setVisceraFatLevel(cur.getInt(cur.getColumnIndex(DataBaseConstants.HSRESULT_VISCERAFATLEVEL)));
                bpo.setMechineType(cur.getString(cur.getColumnIndex(DataBaseConstants.HSRESULT_MECHINETYPE)));
                bpo.setMechineDeviceID(cur.getString(cur.getColumnIndex(DataBaseConstants.HSRESULT_MECHINEDEVICEID)));
                bpo.setiHealthID(cur.getString(cur.getColumnIndex(DataBaseConstants.HSRESULT_IHEALTHID)));
                bpo.setEmotions(cur.getInt(cur.getColumnIndex(DataBaseConstants.HSRESULT_EMOTIONS)));
                bpo.setNoteTS(cur.getLong(cur.getColumnIndex(DataBaseConstants.HSRESULT_NOTETS)));
                bpo.setMood(cur.getInt(cur.getColumnIndex(DataBaseConstants.HSRESULT_MOOD)));
                bpo.setActivity(cur.getInt(cur.getColumnIndex(DataBaseConstants.HSRESULT_ACTIVITY)));
                bpo.setTemp(cur.getString(cur.getColumnIndex(DataBaseConstants.HSRESULT_TEMP)));
                bpo.setWeather(cur.getString(cur.getColumnIndex(DataBaseConstants.HSRESULT_WEATHER)));
                bpo.setHumidity(cur.getString(cur.getColumnIndex(DataBaseConstants.HSRESULT_HUMIDITY)));
                bpo.setVisibility(cur.getString(cur.getColumnIndex(DataBaseConstants.HSRESULT_VISIBILITY)));
                bpo.setUsedUserid(cur.getInt(cur.getColumnIndex(DataBaseConstants.HSRESULT_USEDUSERID)));

                hsArr.add(bpo);
                cur.moveToNext();
                if (hsArr.size() > 50) {
                    break;
                }
            }

            // 关闭游标,DataBase,数据库连接
            cur.close();
            return hsArr;
        } else {
            cur.close();
            return null;
        }
    }
}
