package com.ihealth.communication.cloud.data;

import android.content.Context;
import android.database.Cursor;

import com.ihealth.communication.cloud.UserCheckSDK;
import com.ihealth.communication.cloud.tools.AppsDeviceParameters;
import com.ihealth.communication.utils.Log;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Start_timer->执行SDK上传Start_timer接口
 *
 * @author brave
 */
public class AM_Up {

    private static final String TAG = "AM_sdk_Up";

    public final Timer timer = new Timer();
    public TimerTask task;
    private String un;
    public Context context;
    private String accessToken, refreshToken, host;

    public AM_Up(Context context) {
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
     * @return 开启AM云定时器，30秒更新一次
     */
    public void Start_timer() {

        task = new TimerTask() {
            public void run() {
                if (UserCheckSDK.isNetworkAvailable(context)) {
                    // activity
                    ArrayList<Data_AM_Activity> activityList = null;
                    activityList = collectActivity();// 从数据库取数

                    if (activityList == null) {
                        if (AppsDeviceParameters.isLog)
                            Log.i(TAG, "activity无数据上传");
                    } else if (activityList != null && activityList.size() > 0) {
                        if (AppsDeviceParameters.isLog)
                            Log.i(TAG, "activity有数据上传");
                        boolean upState = false;// 上传标记 true 上传成功
                        try {
                            AM_CommCloud comm = new AM_CommCloud(context);
                            AM_ReturnData bgUploadReturn = comm.am_activity_upload(
                                    un, accessToken, activityList, host);
                            String resultMessage = bgUploadReturn
                                    .getResultMessage();

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
                            for (Data_AM_Activity ama : activityList) {
                                asd = db.deleteData(DataBaseConstants.TABLE_TB_AM_ACTIVITY, DataBaseConstants.ACTIVITY_IHEALTHID + " = '" + un
                                        + "' and " + DataBaseConstants.ACTIVITY_PHONEDATAID + " = '" + ama.getPhoneDataID() + "'");
                            }

                            if (AppsDeviceParameters.isLog)
                                Log.i(TAG, "删除结果 = " + asd);

                            // 关闭数据库连接
                        }

                        // 再取一次验证删除
                        DataBaseTools db1 = new DataBaseTools(context);
                        Cursor cur = db1.selectData(DataBaseConstants.TABLE_TB_AM_ACTIVITY, null,
                                DataBaseConstants.ACTIVITY_IHEALTHID + "='" + un + "'");
                        if (AppsDeviceParameters.isLog)
                            Log.i(TAG, "再取一次验证删除成功 ==> 取得条目数 = " + cur.getCount());

                        cur.close();
                    }
                    //report
                    ArrayList<Data_AM_ActivityDayReport> reportList = null;
                    reportList = collectReport();// 从数据库取数

                    if (reportList == null) {
                        if (AppsDeviceParameters.isLog)
                            Log.i(TAG, "report无数据上传");
                    } else if (reportList != null && reportList.size() > 0) {
                        if (AppsDeviceParameters.isLog)
                            Log.i(TAG, "report有数据上传");
                        boolean upState = false;// 上传标记 true 上传成功
                        try {
                            AM_CommCloud comm = new AM_CommCloud(context);
                            AM_ReturnData bgUploadReturn = comm.am_report_upload(
                                    un, accessToken, reportList, host);
                            String resultMessage = bgUploadReturn
                                    .getResultMessage();

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
                            for (Data_AM_ActivityDayReport amar : reportList) {
                                asd = db.deleteData(DataBaseConstants.TABLE_TB_AM_ACTIVITYREPORT, DataBaseConstants.ACTIVITYREPORT_IHEALTHID + " = '" + un
                                        + "' and " + DataBaseConstants.ACTIVITYREPORT_PHONEDATAID + " = '" + amar.getPhoneDataID() + "'");
                            }

                            if (AppsDeviceParameters.isLog)
                                Log.i(TAG, "删除结果 = " + asd);

                        }

                        // 再取一次验证删除
                        DataBaseTools db1 = new DataBaseTools(context);
                        Cursor cur = db1.selectData(DataBaseConstants.TABLE_TB_AM_ACTIVITYREPORT, null,
                                DataBaseConstants.ACTIVITYREPORT_IHEALTHID + "='" + un + "'");
                        if (AppsDeviceParameters.isLog)
                            Log.i(TAG, "再取一次验证删除成功 ==> 取得条目数 = " + cur.getCount());
                        cur.close();
                    }
                    // Sleep
                    ArrayList<Data_AM_Sleep> sleepyList = null;
                    sleepyList = collectSleep();// 从数据库取数

                    if (sleepyList == null) {
                        if (AppsDeviceParameters.isLog)
                            Log.i(TAG, "Sleep无数据上传");
                    } else if (sleepyList != null && sleepyList.size() > 0) {
                        if (AppsDeviceParameters.isLog)
                            Log.i(TAG, "Sleep有数据上传");
                        boolean upState = false;// 上传标记 true 上传成功
                        try {
                            AM_CommCloud comm = new AM_CommCloud(context);
                            AM_ReturnData bgUploadReturn = comm.am_sleep_upload(
                                    un, accessToken, sleepyList, host);
                            String resultMessage = bgUploadReturn
                                    .getResultMessage();

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
                            for (Data_AM_Sleep ams : sleepyList) {
                                asd = db.deleteData(DataBaseConstants.TABLE_TB_AM_SLEEP, DataBaseConstants.SLEEP_IHEALTHID + " = '" + un
                                        + "' and " + DataBaseConstants.SLEEP_PHONEDATAID + " = '" + ams.getPhoneDataID() + "'");
                            }

                            if (AppsDeviceParameters.isLog)
                                Log.i(TAG, "删除结果 = " + asd);

                        }

                        // 再取一次验证删除
                        DataBaseTools db1 = new DataBaseTools(context);
                        Cursor cur = db1.selectData(DataBaseConstants.TABLE_TB_AM_SLEEP, null,
                                DataBaseConstants.SLEEP_IHEALTHID + "='" + un + "'");
                        if (AppsDeviceParameters.isLog)
                            Log.i(TAG, "再取一次验证删除成功 ==> 取得条目数 = " + cur.getCount());
                        cur.close();
                    }
                    //sleep report
                    ArrayList<Data_AM_SleepSectionReport> sectionList = null;
                    sectionList = collectSection();// 从数据库取数

                    if (sectionList == null) {
                        if (AppsDeviceParameters.isLog)
                            Log.i(TAG, "Sleepreport无数据上传");
                    } else if (sectionList != null && sectionList.size() > 0) {
                        if (AppsDeviceParameters.isLog)
                            Log.i(TAG, "Sleepreport有数据上传");
                        boolean upState = false;// 上传标记 true 上传成功
                        try {
                            AM_CommCloud comm = new AM_CommCloud(context);
                            AM_ReturnData bgUploadReturn = comm.am_section_upload(
                                    un, accessToken, sectionList, host);
                            String resultMessage = bgUploadReturn
                                    .getResultMessage();

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
                            for (Data_AM_SleepSectionReport amss : sectionList) {
                                asd = db.deleteData(DataBaseConstants.TABLE_TB_AM_SLEEPREPORT, DataBaseConstants.SLEEPREPORT_IHEALTHID + " = '" + un
                                        + "' and " + DataBaseConstants.SLEEPREPORT_PHONEDATAID + " = '" + amss.getPhoneDataID() + "'");
                            }

                            if (AppsDeviceParameters.isLog)
                                Log.i(TAG, "删除结果 = " + asd);

                            // 关闭数据库连接
                        }

                        // 再取一次验证删除
                        DataBaseTools db1 = new DataBaseTools(context);
                        Cursor cur = db1.selectData(DataBaseConstants.TABLE_TB_AM_SLEEPREPORT, null,
                                DataBaseConstants.SLEEPREPORT_IHEALTHID + "='" + un + "'");
                        if (AppsDeviceParameters.isLog)
                            Log.i(TAG, "再取一次验证删除成功 ==> 取得条目数 = " + cur.getCount());
                        cur.close();
                    }

                    //WorkOut
                    ArrayList<Data_TB_Workout> workoutList = null;
                    workoutList = collectWorkout();// 从数据库取数

                    if (workoutList == null) {
                        if (AppsDeviceParameters.isLog)
                            Log.i(TAG, "Workout无数据上传");
                    } else if (workoutList != null && workoutList.size() > 0) {
                        if (AppsDeviceParameters.isLog)
                            Log.i(TAG, "workout有数据上传");
                        boolean upState = false;// 上传标记 true 上传成功
                        try {
                            AM_CommCloud comm = new AM_CommCloud(context);
                            AM_ReturnData bgUploadReturn = comm.am_workout_upload(
                                    un, accessToken, workoutList, host);
                            String resultMessage = bgUploadReturn
                                    .getResultMessage();

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
                            for (Data_TB_Workout amwo : workoutList) {
                                asd = db.deleteData(DataBaseConstants.TABLE_TB_WORKOUT, DataBaseConstants
                                        .WORKOUT_IHEALTHCLOUD + " = '" + un
                                        + "' and " + DataBaseConstants.WORKOUT_PHONEDATAID + " = '" + amwo
                                        .getWorkout_PhoneDataID() + "'");
                            }

                            if (AppsDeviceParameters.isLog)
                                Log.i(TAG, "删除结果 = " + asd);

                            // 关闭数据库连接
                        }

                        // 再取一次验证删除
                        DataBaseTools db1 = new DataBaseTools(context);
                        Cursor cur = db1.selectData(DataBaseConstants.TABLE_TB_WORKOUT, null,
                                DataBaseConstants.WORKOUT_IHEALTHCLOUD + "='" + un + "'");
                        if (AppsDeviceParameters.isLog)
                            Log.i(TAG, "再取一次验证删除成功 ==> 取得条目数 = " + cur.getCount());
                        cur.close();
                    }

                    //swim
                    ArrayList<Data_TB_Swim> swimList = null;
                    swimList = collectSwim();// 从数据库取数

                    if (swimList == null) {
                        if (AppsDeviceParameters.isLog)
                            Log.i(TAG, "swimList无数据上传");
                    } else if (swimList != null && swimList.size() > 0) {
                        if (AppsDeviceParameters.isLog)
                            Log.i(TAG, "swimList有数据上传");
                        boolean upState = false;// 上传标记 true 上传成功
                        try {
                            AM_CommCloud comm = new AM_CommCloud(context);
                            int result = comm.swimActivity_upload(
                                    un, accessToken, swimList, host);

                            if (result == 100) {
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
                            for (Data_TB_Swim amsw : swimList) {
                                asd = db.deleteData(DataBaseConstants.TABLE_TB_SWIM, DataBaseConstants
                                        .SWIM_IHEALTHCLOUD + " = '" + un
                                        + "' and " + DataBaseConstants.SWIM_PHONEDATAID + " = '" + amsw
                                        .getSwim_PhoneDataID() + "'");
                            }

                            if (AppsDeviceParameters.isLog)
                                Log.i(TAG, "删除结果 = " + asd);

                            // 关闭数据库连接
                        }

                        // 再取一次验证删除
                        DataBaseTools db1 = new DataBaseTools(context);
                        Cursor cur = db1.selectData(DataBaseConstants.TABLE_TB_SWIM, null,
                                DataBaseConstants.SWIM_IHEALTHCLOUD + "='" + un + "'");
                        if (AppsDeviceParameters.isLog)
                            Log.i(TAG, "再取一次验证删除成功 ==> 取得条目数 = " + cur.getCount());
                        cur.close();
                    }

                    //swimReport
                    ArrayList<Data_TB_SwimSection> swimSectionsList = null;
                    swimSectionsList = collectSwimReport();// 从数据库取数

                    if (swimSectionsList == null) {
                        if (AppsDeviceParameters.isLog)
                            Log.i(TAG, "swimSectionsList无数据上传");
                    } else if (swimSectionsList != null && swimSectionsList.size() > 0) {
                        if (AppsDeviceParameters.isLog)
                            Log.i(TAG, "swimSectionsList有数据上传");
                        boolean upState = false;// 上传标记 true 上传成功
                        try {
                            AM_CommCloud comm = new AM_CommCloud(context);
                            int result = comm.swimReport_upload(
                                    un, accessToken, swimSectionsList, host);

                            if (result == 100) {
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
                            for (Data_TB_SwimSection amswims : swimSectionsList) {
                                asd = db.deleteData(DataBaseConstants.TABLE_TB_SWIMSECTION, DataBaseConstants
                                        .SWIMSECTION_IHEALTHCLOUD + " = '" + un
                                        + "' and " + DataBaseConstants.SWIMSECTION_DATAID + " = '" + amswims
                                        .getSwimSection_DataID() + "'");
                            }

                            if (AppsDeviceParameters.isLog)
                                Log.i(TAG, "删除结果 = " + asd);

                            // 关闭数据库连接
                        }

                        // 再取一次验证删除
                        DataBaseTools db1 = new DataBaseTools(context);
                        Cursor cur = db1.selectData(DataBaseConstants.TABLE_TB_SWIMSECTION, null,
                                DataBaseConstants.SWIMSECTION_IHEALTHCLOUD + "='" + un + "'");
                        if (AppsDeviceParameters.isLog)
                            Log.i(TAG, "再取一次验证删除成功 ==> 取得条目数 = " + cur.getCount());
                        cur.close();
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

    //取存储在数据库中的数据放入List准备上传到服务器
    private ArrayList<Data_AM_Activity> collectActivity() {

        DataBaseTools db = new DataBaseTools(this.context);
        Cursor cur = db.selectData(DataBaseConstants.TABLE_TB_AM_ACTIVITY, null, DataBaseConstants.ACTIVITY_IHEALTHID + "='" + un + "'");
        cur.moveToFirst();

        if (cur.getCount() > 0) {

            ArrayList<Data_AM_Activity> amArr = new ArrayList<Data_AM_Activity>();//返回的数据集合

            for (int i = 0; i < cur.getCount(); i++) {
                Data_AM_Activity bpo = new Data_AM_Activity();

                //添加来自数据库的数据到数据类-准备上传
                bpo.setChangeType(cur.getInt(cur.getColumnIndex(DataBaseConstants.ACTIVITY_CHANGETYPE)));
                bpo.setLastChangeTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.ACTIVITY_LASTCHANGETIME)));
                bpo.setPhoneDataID(cur.getString(cur.getColumnIndex(DataBaseConstants.ACTIVITY_PHONEDATAID)));
                bpo.setPhoneCreateTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.ACTIVITY_PHONECREATETIME)));
                bpo.setLat(cur.getFloat(cur.getColumnIndex(DataBaseConstants.ACTIVITY_LAT)));
                bpo.setLon(cur.getFloat(cur.getColumnIndex(DataBaseConstants.ACTIVITY_LON)));
                bpo.setTimeZone(cur.getFloat(cur.getColumnIndex(DataBaseConstants.ACTIVITY_TIMEZONE)));

                bpo.setCalorie(cur.getFloat(cur.getColumnIndex(DataBaseConstants.ACTIVITY_CALORIE)));
                bpo.setStepLength(cur.getInt(cur.getColumnIndex(DataBaseConstants.ACTIVITY_STEPLENGTH)));
                bpo.setSteps(cur.getInt(cur.getColumnIndex(DataBaseConstants.ACTIVITY_STEPS)));
                bpo.setSumCalorie(cur.getInt(cur.getColumnIndex(DataBaseConstants.ACTIVITY_SUMCALORIE)));
                bpo.setSumSteps(cur.getInt(cur.getColumnIndex(DataBaseConstants.ACTIVITY_SUMSTEPS)));

                bpo.setMeasureTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.ACTIVITY_MEASURETIME)));
                bpo.setMechineType(cur.getString(cur.getColumnIndex(DataBaseConstants.ACTIVITY_MECHINETYPE)));
                bpo.setMechineDeviceID(cur.getString(cur.getColumnIndex(DataBaseConstants.ACTIVITY_MECHINEDEVICEID)));

                bpo.setiHealthID(cur.getString(cur.getColumnIndex(DataBaseConstants.ACTIVITY_IHEALTHID)));

                amArr.add(bpo);
                cur.moveToNext();
                if (amArr.size() > 50) {
                    break;
                }
            }

            //关闭游标,DataBase,数据库连接
            cur.close();
            return amArr;
        } else {
            cur.close();
            return null;
        }
    }

    private ArrayList<Data_AM_ActivityDayReport> collectReport() {

        DataBaseTools db = new DataBaseTools(this.context);
        Cursor cur = db.selectData(DataBaseConstants.TABLE_TB_AM_ACTIVITYREPORT, null, DataBaseConstants.ACTIVITYREPORT_IHEALTHID + "='" + un + "'");
        cur.moveToFirst();

        if (cur.getCount() > 0) {

            ArrayList<Data_AM_ActivityDayReport> amArr = new ArrayList<Data_AM_ActivityDayReport>();//返回的数据集合

            for (int i = 0; i < cur.getCount(); i++) {
                Data_AM_ActivityDayReport bpo = new Data_AM_ActivityDayReport();

                //添加来自数据库的数据到数据类-准备上传
                bpo.setChangeType(cur.getInt(cur.getColumnIndex(DataBaseConstants.ACTIVITYREPORT_CHANGETYPE)));
                bpo.setLastChangeTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.ACTIVITYREPORT_LASTCHANGETIME)));
                bpo.setPhoneDataID(cur.getString(cur.getColumnIndex(DataBaseConstants.ACTIVITYREPORT_PHONEDATAID)));
                bpo.setPhoneCreateTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.ACTIVITYREPORT_PHONECREATETIME)));
                bpo.setLat(cur.getFloat(cur.getColumnIndex(DataBaseConstants.ACTIVITYREPORT_LAT)));
                bpo.setLon(cur.getFloat(cur.getColumnIndex(DataBaseConstants.ACTIVITYREPORT_LON)));
                bpo.setTimeZone(cur.getFloat(cur.getColumnIndex(DataBaseConstants.ACTIVITYREPORT_TIMEZONE)));

                bpo.setCalorie(cur.getFloat(cur.getColumnIndex(DataBaseConstants.ACTIVITYREPORT_CALORIE)));
                bpo.setStepLength(cur.getInt(cur.getColumnIndex(DataBaseConstants.ACTIVITYREPORT_STEPLENGTH)));
                bpo.setSteps(cur.getInt(cur.getColumnIndex(DataBaseConstants.ACTIVITYREPORT_STEPS)));
                bpo.setPlanSteps(cur.getInt(cur.getColumnIndex(DataBaseConstants.ACTIVITYREPORT_PLANSTEPS)));
                bpo.setPlanCalorie(cur.getFloat(cur.getColumnIndex(DataBaseConstants.ACTIVITYREPORT_PLANCALORIE)));

                bpo.setCity(cur.getString(cur.getColumnIndex(DataBaseConstants.ACTIVITYREPORT_CITY)));
                bpo.setWeather(cur.getString(cur.getColumnIndex(DataBaseConstants.ACTIVITYREPORT_WEATHER)));
                bpo.setComment(cur.getString(cur.getColumnIndex(DataBaseConstants.ACTIVITYREPORT_COMMENT)));

                bpo.setMeasureTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.ACTIVITYREPORT_MEASURETIME)));
                bpo.setMechineType(cur.getString(cur.getColumnIndex(DataBaseConstants.ACTIVITYREPORT_MECHINETYPE)));
                bpo.setMechineDeviceID(cur.getString(cur.getColumnIndex(DataBaseConstants.ACTIVITYREPORT_MECHINEDEVICEID)));

                bpo.setiHealthID(cur.getString(cur.getColumnIndex(DataBaseConstants.ACTIVITYREPORT_IHEALTHID)));

                amArr.add(bpo);
                cur.moveToNext();
                if (amArr.size() > 50) {
                    break;
                }
            }

            //关闭游标,DataBase,数据库连接
            cur.close();
            return amArr;
        } else {
            cur.close();
            return null;
        }
    }

    private ArrayList<Data_AM_Sleep> collectSleep() {

        DataBaseTools db = new DataBaseTools(this.context);
        Cursor cur = db.selectData(DataBaseConstants.TABLE_TB_AM_SLEEP, null, DataBaseConstants.SLEEP_IHEALTHID + "='" + un + "'");
        cur.moveToFirst();

        if (cur.getCount() > 0) {

            ArrayList<Data_AM_Sleep> amArr = new ArrayList<Data_AM_Sleep>();//返回的数据集合

            for (int i = 0; i < cur.getCount(); i++) {
                Data_AM_Sleep bpo = new Data_AM_Sleep();

                //添加来自数据库的数据到数据类-准备上传
                bpo.setChangeType(cur.getInt(cur.getColumnIndex(DataBaseConstants.SLEEP_CHANGETYPE)));
                bpo.setLastChangeTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.SLEEP_LASTCHANGETIME)));
                bpo.setPhoneDataID(cur.getString(cur.getColumnIndex(DataBaseConstants.SLEEP_PHONEDATAID)));
                bpo.setPhoneCreateTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.SLEEP_PHONECREATETIME)));
                bpo.setLat(cur.getFloat(cur.getColumnIndex(DataBaseConstants.SLEEP_LAT)));
                bpo.setLon(cur.getFloat(cur.getColumnIndex(DataBaseConstants.SLEEP_LON)));
                bpo.setTimeZone(cur.getFloat(cur.getColumnIndex(DataBaseConstants.SLEEP_TIMEZONE)));

                bpo.setSleepLevel(cur.getInt(cur.getColumnIndex(DataBaseConstants.SLEEP_SLEEPLEVEL)));
                bpo.setTimeSectionId(cur.getString(cur.getColumnIndex(DataBaseConstants.SLEEP_TIMESECTIONID)));

                bpo.setMeasureTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.SLEEP_MEASURETIME)));
                bpo.setMechineType(cur.getString(cur.getColumnIndex(DataBaseConstants.SLEEP_MECHINETYPE)));
                bpo.setMechineDeviceID(cur.getString(cur.getColumnIndex(DataBaseConstants.SLEEP_MECHINEDEVICEID)));

                bpo.setiHealthID(cur.getString(cur.getColumnIndex(DataBaseConstants.SLEEP_IHEALTHID)));

                amArr.add(bpo);
                cur.moveToNext();
                if (amArr.size() > 50) {
                    break;
                }
            }

            //关闭游标,DataBase,数据库连接
            cur.close();
            return amArr;
        } else {
            cur.close();
            return null;
        }
    }

    private ArrayList<Data_AM_SleepSectionReport> collectSection() {

        DataBaseTools db = new DataBaseTools(this.context);
        Cursor cur = db.selectData(DataBaseConstants.TABLE_TB_AM_SLEEPREPORT, null, DataBaseConstants.SLEEPREPORT_IHEALTHID + "='" + un + "'");
        cur.moveToFirst();

        if (cur.getCount() > 0) {

            ArrayList<Data_AM_SleepSectionReport> amArr = new ArrayList<Data_AM_SleepSectionReport>();//返回的数据集合

            for (int i = 0; i < cur.getCount(); i++) {
                Data_AM_SleepSectionReport bpo = new Data_AM_SleepSectionReport();

                //添加来自数据库的数据到数据类-准备上传
                bpo.setChangeType(cur.getInt(cur.getColumnIndex(DataBaseConstants.SLEEPREPORT_CHANGETYPE)));
                bpo.setLastChangeTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.SLEEPREPORT_LASTCHANGETIME)));
                bpo.setPhoneDataID(cur.getString(cur.getColumnIndex(DataBaseConstants.SLEEPREPORT_PHONEDATAID)));
                bpo.setPhoneCreateTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.SLEEPREPORT_PHONECREATETIME)));
                bpo.setLat(cur.getFloat(cur.getColumnIndex(DataBaseConstants.SLEEPREPORT_LAT)));
                bpo.setLon(cur.getFloat(cur.getColumnIndex(DataBaseConstants.SLEEPREPORT_LON)));
                bpo.setTimeZone(cur.getFloat(cur.getColumnIndex(DataBaseConstants.SLEEPREPORT_TIMEZONE)));

                bpo.setAwake(cur.getInt(cur.getColumnIndex(DataBaseConstants.SLEEPREPORT_AWAKE)));
                bpo.setDeepSleep(cur.getInt(cur.getColumnIndex(DataBaseConstants.SLEEPREPORT_DEEPSLEEP)));
                bpo.setFallSleep(cur.getInt(cur.getColumnIndex(DataBaseConstants.SLEEPREPORT_FALLSLEEP)));
                bpo.setSleep(cur.getInt(cur.getColumnIndex(DataBaseConstants.SLEEPREPORT_SLEEP)));
                bpo.setAwakenTimes(cur.getInt(cur.getColumnIndex(DataBaseConstants.SLEEPREPORT_AWAKENTIMES)));

                bpo.setSleepStartTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.SLEEPREPORT_SLEEPSTARTTIME)));
                bpo.setSleepEndTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.SLEEPREPORT_SLEEPENDTIME)));
                bpo.setTimeSectionId(cur.getString(cur.getColumnIndex(DataBaseConstants.SLEEPREPORT_TIMESECTIONID)));

                bpo.setCity(cur.getString(cur.getColumnIndex(DataBaseConstants.SLEEPREPORT_CITY)));
                bpo.setWeather(cur.getString(cur.getColumnIndex(DataBaseConstants.SLEEPREPORT_WEATHER)));
                bpo.setComment(cur.getString(cur.getColumnIndex(DataBaseConstants.SLEEPREPORT_COMMENT)));

                bpo.setNap(cur.getInt(cur.getColumnIndex(DataBaseConstants.SLEEPREPORT_NAP)));
                bpo.setMood(cur.getInt(cur.getColumnIndex(DataBaseConstants.SLEEPREPORT_MOOD)));
                bpo.setActivity(cur.getInt(cur.getColumnIndex(DataBaseConstants.SLEEPREPORT_ACTIVITY)));

                bpo.setMeasureTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.SLEEPREPORT_MEASURETIME)));
                bpo.setMechineType(cur.getString(cur.getColumnIndex(DataBaseConstants.SLEEPREPORT_MECHINETYPE)));
                bpo.setMechineDeviceID(cur.getString(cur.getColumnIndex(DataBaseConstants.SLEEPREPORT_MECHINEDEVICEID)));

                bpo.setiHealthID(cur.getString(cur.getColumnIndex(DataBaseConstants.SLEEPREPORT_IHEALTHID)));

                amArr.add(bpo);
                cur.moveToNext();
                if (amArr.size() > 50) {
                    break;
                }
            }

            //关闭游标,DataBase,数据库连接
            cur.close();
            return amArr;
        } else {
            cur.close();
            return null;
        }
    }

    private ArrayList<Data_TB_Workout> collectWorkout() {

        DataBaseTools db = new DataBaseTools(this.context);
        Cursor cur = db.selectData(DataBaseConstants.TABLE_TB_WORKOUT, null, DataBaseConstants.WORKOUT_IHEALTHCLOUD + "='"
                + un + "'");
        cur.moveToFirst();

        if (cur.getCount() > 0) {

            ArrayList<Data_TB_Workout> amArr = new ArrayList<Data_TB_Workout>();//返回的数据集合

            for (int i = 0; i < cur.getCount(); i++) {
                Data_TB_Workout workout = new Data_TB_Workout();

                workout.setWorkout_iHealthCloud(un);
                workout.setWorkout_ChangeType(cur.getInt(cur.getColumnIndex(DataBaseConstants.WORKOUT_CHANGETYPE)));
                workout.setWorkout_LastChangeTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.WORKOUT_LASTCHANGETIME)));
                workout.setWorkout_PhoneCreateTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.WORKOUT_PHONECREATETIME)));
                workout.setWorkout_PhoneDataID(cur.getString(cur.getColumnIndex(DataBaseConstants.WORKOUT_PHONEDATAID)));
                workout.setWorkout_Lat(cur.getDouble(cur.getColumnIndex(DataBaseConstants.WORKOUT_LAT)));
                workout.setWorkout_Lon(cur.getDouble(cur.getColumnIndex(DataBaseConstants.WORKOUT_LON)));
                workout.setWorkout_TimeZone(cur.getFloat(cur.getColumnIndex(DataBaseConstants.WORKOUT_TIMEZONE)));

                workout.setWorkout_SpendMinutes(cur.getInt(cur.getColumnIndex(DataBaseConstants.WORKOUT_SPENDMINUTES)));
                workout.setWorkout_Steps(cur.getInt(cur.getColumnIndex(DataBaseConstants.WORKOUT_STEPS)));
                workout.setWorkout_Distance(cur.getInt(cur.getColumnIndex(DataBaseConstants.WORKOUT_DISTANCE)));
                workout.setWorkout_Calories(cur.getFloat(cur.getColumnIndex(DataBaseConstants.WORKOUT_CALORIES)));
                workout.setWorkout_City(cur.getString(cur.getColumnIndex(DataBaseConstants.WORKOUT_CITY)));

                workout.setWorkout_Temperature(cur.getString(cur.getColumnIndex(DataBaseConstants.WORKOUT_TEMPERATURE)));
                workout.setWorkout_Humidity(cur.getString(cur.getColumnIndex(DataBaseConstants.WORKOUT_HUMIDITY)));
                workout.setWorkout_Atmosphere(cur.getString(cur.getColumnIndex(DataBaseConstants.WORKOUT_ATMOSPHERE)));
                workout.setWorkout_WeatherCode(cur.getString(cur.getColumnIndex(DataBaseConstants.WORKOUT_WEATHERCODE)));

                workout.setWorkout_CommentNote(cur.getString(cur.getColumnIndex(DataBaseConstants.WORKOUT_COMMENTNOTE)));
                workout.setWorkout_CommentTS(cur.getLong(cur.getColumnIndex(DataBaseConstants.WORKOUT_COMMENTTS)));
                workout.setWorkout_CommentPic(cur.getString(cur.getColumnIndex(DataBaseConstants.WORKOUT_COMMENTPIC)));

                workout.setWorkout_MechineType(cur.getString(cur.getColumnIndex(DataBaseConstants.WORKOUT_MECHINETYPE)));
                workout.setWorkout_MechineDeviceID(cur.getString(cur.getColumnIndex(DataBaseConstants.WORKOUT_MECHINEDEVICEID)));


                //添加来自数据库的数据到数据类-准备上传
                amArr.add(workout);
                cur.moveToNext();
                if (amArr.size() > 50) {
                    break;
                }
            }

            //关闭游标,DataBase,数据库连接
            cur.close();
            return amArr;
        } else {
            cur.close();
            return null;
        }
    }

    private ArrayList<Data_TB_Swim> collectSwim() {

        DataBaseTools db = new DataBaseTools(this.context);
        Cursor cur = db.selectData(DataBaseConstants.TABLE_TB_SWIM, null, DataBaseConstants.SWIM_IHEALTHCLOUD + "='"
                + un + "'");
        cur.moveToFirst();

        if (cur.getCount() > 0) {

            ArrayList<Data_TB_Swim> amArr = new ArrayList<Data_TB_Swim>();//返回的数据集合

            for (int i = 0; i < cur.getCount(); i++) {

                Data_TB_Swim SwimOb = new Data_TB_Swim();

                SwimOb.setSwim_iHealthCloud(un);
                SwimOb.setSwim_ChangeType(cur.getInt(cur.getColumnIndex(DataBaseConstants.SWIM_CHANGETYPE)));
                SwimOb.setSwim_LastChangeTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.SWIM_LASTCHANGETIME)));
                SwimOb.setSwim_PhoneCreateTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.SWIM_PHONECREATETIME)));
                SwimOb.setSwim_PhoneDataID(cur.getString(cur.getColumnIndex(DataBaseConstants.SWIM_PHONEDATAID)));
                SwimOb.setSwim_Lat(cur.getDouble(cur.getColumnIndex(DataBaseConstants.SWIM_LAT)));
                SwimOb.setSwim_Lon(cur.getDouble(cur.getColumnIndex(DataBaseConstants.SWIM_LON)));
                SwimOb.setSwim_TimeZone(cur.getFloat(cur.getColumnIndex(DataBaseConstants.SWIM_TIMEZONE)));
                SwimOb.setSwim_SpendMinutes(cur.getInt(cur.getColumnIndex(DataBaseConstants.SWIM_SPENDMINUTES)));
                SwimOb.setSwim_PullTimes(cur.getInt(cur.getColumnIndex(DataBaseConstants.SWIM_PULLTIMES)));
                SwimOb.setSwim_Storke(cur.getInt(cur.getColumnIndex(DataBaseConstants.SWIM_STROKE)));
                SwimOb.setSwim_Cycles(cur.getInt(cur.getColumnIndex(DataBaseConstants.SWIM_CYCLES)));
                SwimOb.setSwim_Distance(cur.getInt(cur.getColumnIndex(DataBaseConstants.SWIM_DISTANCE)));
                SwimOb.setSwim_Calories(cur.getFloat(cur.getColumnIndex(DataBaseConstants.SWIM_CALORIES)));
                SwimOb.setSwim_City(cur.getString(cur.getColumnIndex(DataBaseConstants.SWIM_CITY)));

                SwimOb.setSwim_Temperature(cur.getString(cur.getColumnIndex(DataBaseConstants.SWIM_TEMPERATURE)));
                SwimOb.setSwim_WeatherCode(cur.getString(cur.getColumnIndex(DataBaseConstants.SWIM_WEATHERCODE)));
                SwimOb.setSwim_Humidity(cur.getString(cur.getColumnIndex(DataBaseConstants.SWIM_HUMIDITY)));
                SwimOb.setSwim_Atmosphere(cur.getString(cur.getColumnIndex(DataBaseConstants.SWIM_ATMOSPHERE)));
                SwimOb.setSwim_CommentPic(cur.getString(cur.getColumnIndex(DataBaseConstants.SWIM_COMMENTPIC)));

                SwimOb.setSwim_CommentTS(cur.getLong(cur.getColumnIndex(DataBaseConstants.SWIM_COMMENTTS)));
                SwimOb.setSwim_endtime(cur.getLong(cur.getColumnIndex(DataBaseConstants.SWIM_ENDTIME)));
                SwimOb.setSwim_MechineType(cur.getString(cur.getColumnIndex(DataBaseConstants.SWIM_MECHINETYPE)));
                SwimOb.setSwim_MechineDeviceID(cur.getString(cur.getColumnIndex(DataBaseConstants.SWIM_MECHINEDEVICEID)));
                SwimOb.setSwim_CommentNote(cur.getString(cur.getColumnIndex(DataBaseConstants.SWIM_COMMENTNOTE)));
                SwimOb.setSwim_CutInTimeDif(cur.getInt(cur.getColumnIndex(DataBaseConstants.SWIM_CUTINDIF)));
                SwimOb.setSwim_CutOutTimeDif(cur.getInt(cur.getColumnIndex(DataBaseConstants.SWIM_CUTOUTDIF)));
                SwimOb.setSwim_ProcessFlag(cur.getInt(cur.getColumnIndex(DataBaseConstants.SWIM_PROCESSFLAG)));

                //添加来自数据库的数据到数据类-准备上传
                amArr.add(SwimOb);
                cur.moveToNext();
                if (amArr.size() > 50) {
                    break;
                }
            }

            //关闭游标,DataBase,数据库连接
            cur.close();
            return amArr;
        } else {
            cur.close();
            return null;
        }
    }

    private ArrayList<Data_TB_SwimSection> collectSwimReport() {

        DataBaseTools db = new DataBaseTools(this.context);
        Cursor cur = db.selectData(DataBaseConstants.TABLE_TB_SWIMSECTION, null, DataBaseConstants.SWIMSECTION_IHEALTHCLOUD + "='"
                + un + "'");
        cur.moveToFirst();

        if (cur.getCount() > 0) {

            ArrayList<Data_TB_SwimSection> amArr = new ArrayList<Data_TB_SwimSection>();//返回的数据集合

            for (int i = 0; i < cur.getCount(); i++) {

                Data_TB_SwimSection swimSectionOb = new Data_TB_SwimSection();

                swimSectionOb.setSwimSection_iHealthCloud(un);
//                swimSectionOb.setAmalChangeType(cur.getInt(cur.getColumnIndex(DataBaseConstants.AMALRESULT_CHANGETYPE)));
                swimSectionOb.setSwimSection_City(cur.getString(cur.getColumnIndex(DataBaseConstants.SWIMSECTION_CITY)));
                swimSectionOb.setSwimSection_DeviceID(cur.getString(cur.getColumnIndex(DataBaseConstants.SWIMSECTION_DEVICEID)));
                swimSectionOb.setSwimSection_DeviceSource(cur.getString(cur.getColumnIndex(DataBaseConstants.SWIMSECTION_DEVICESOURCE)));
                swimSectionOb.setSwimSection_Lat(cur.getDouble(cur.getColumnIndex(DataBaseConstants.SWIMSECTION_LAT)));
                swimSectionOb.setSwimSection_Lon(cur.getDouble(cur.getColumnIndex(DataBaseConstants.SWIMSECTION_LON)));
                swimSectionOb.setSwimSection_LastChangeTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.SWIMSECTION_LASTCHANGETIME)));
                swimSectionOb.setSwimSection_Note(cur.getString(cur.getColumnIndex(DataBaseConstants.SWIMSECTION_NOTE)));
                swimSectionOb.setSwimSection_NoteTS(cur.getLong(cur.getColumnIndex(DataBaseConstants.SWIMSECTION_NOTETS)));
                swimSectionOb.setSwimSection_SwimCoastTime(cur.getInt(cur.getColumnIndex(DataBaseConstants.SWIMSECTION_SWIMCOASTTIME)));
                swimSectionOb.setSwimSection_Endtime(cur.getLong(cur.getColumnIndex(DataBaseConstants.SWIMSECTION_ENDTIME)));
                swimSectionOb.setSwimSection_StartTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.SWIMSECTION_STARTTIME)));
                swimSectionOb.setSwimSection_PoolLength(cur.getInt(cur.getColumnIndex(DataBaseConstants.SWIMSECTION_POOLLENGTH)));
                swimSectionOb.setSwimSection_DataID(cur.getString(cur.getColumnIndex(DataBaseConstants.SWIMSECTION_DATAID)));
                swimSectionOb.setSwimSection_SpendTimeBackStroke(cur.getInt(cur.getColumnIndex(DataBaseConstants.SWIMSECTION_SPENDTIMEBACKSTROKE)));
                swimSectionOb.setSwimSection_SpendTimeBreastStroke(cur.getInt(cur.getColumnIndex(DataBaseConstants.SWIMSECTION_SPENDTIMEBREASTSTROKE)));
                swimSectionOb.setSwimSection_SpendTimeFreeStroke(cur.getInt(cur.getColumnIndex(DataBaseConstants.SWIMSECTION_SPENDTIMEFREESTROKE)));
                swimSectionOb.setSwimSection_SpendTimeUnrecognized(cur.getInt(cur.getColumnIndex(DataBaseConstants.SWIMSECTION_SPENDTIMEUNRECOGNIZED)));
                swimSectionOb.setSwimSection_SumCalories(cur.getFloat(cur.getColumnIndex(DataBaseConstants.SWIMSECTION_SUMCALORIES)));
                swimSectionOb.setSwimSection_SumThrashTimes(cur.getInt(cur.getColumnIndex(DataBaseConstants.SWIMSECTION_SUMTHRASHTIMES)));
                swimSectionOb.setSwimSection_SumTripCount(cur.getInt(cur.getColumnIndex(DataBaseConstants.SWIMSECTION_SUMTRIPCOUNT)));
                swimSectionOb.setSwimSection_TimeZone(cur.getFloat(cur.getColumnIndex(DataBaseConstants.SWIMSECTION_TIMEZONE)));
                swimSectionOb.setSwimSection_Weather(cur.getString(cur.getColumnIndex(DataBaseConstants.SWIMSECTION_WEATHER)));

                //添加来自数据库的数据到数据类-准备上传
                amArr.add(swimSectionOb);
                cur.moveToNext();
                if (amArr.size() > 50) {
                    break;
                }
            }

            //关闭游标,DataBase,数据库连接
            cur.close();
            return amArr;
        } else {
            cur.close();
            return null;
        }
    }

}
