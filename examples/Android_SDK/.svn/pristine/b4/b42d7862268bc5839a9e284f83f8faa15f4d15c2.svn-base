package com.ihealth.sdk;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ihealth.communication.control.Am4Control;
import com.ihealth.communication.control.AmProfile;
import com.ihealth.communication.control.UpgradeProfile;
import com.ihealth.communication.ins.F0InsSet;
import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.manager.iHealthDevicesUpgradeManager;

import org.json.JSONException;
import org.json.JSONObject;

public class AM4 extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AM4";

    private Am4Control am4Control;
    private String deviceMac;
    private int clientId;
    private JSONObject jsonObject;
    private TextView tv_return;
    private JSONObject o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_am4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = getIntent();
        this.deviceMac = intent.getStringExtra("mac");

        clientId = iHealthDevicesManager.getInstance().registerClientCallback(miHealthDevicesCallback);

        iHealthDevicesManager.getInstance().addCallbackFilterForDeviceType(clientId,
                iHealthDevicesManager.TYPE_AM4);

        am4Control = iHealthDevicesManager.getInstance().getAm4Control(deviceMac);

        findViewById(R.id.btn_SetUserMessage).setOnClickListener(this);
        findViewById(R.id.btn_GetUserMessage).setOnClickListener(this);
        findViewById(R.id.btn_SetUserID).setOnClickListener(this);
        findViewById(R.id.btn_GetUserID).setOnClickListener(this);
        findViewById(R.id.btn_Reset).setOnClickListener(this);
        findViewById(R.id.btn_checkSwimPara).setOnClickListener(this);
        findViewById(R.id.btn_SetSwimPara).setOnClickListener(this);
        findViewById(R.id.btn_SetRemind).setOnClickListener(this);
        findViewById(R.id.btn_GetRemind).setOnClickListener(this);
        findViewById(R.id.btn_SetClock).setOnClickListener(this);
        findViewById(R.id.btn_GetClock).setOnClickListener(this);
        findViewById(R.id.btn_GetBattery).setOnClickListener(this);
        findViewById(R.id.btn_SetRealTime).setOnClickListener(this);
        findViewById(R.id.btn_SetTimeMode).setOnClickListener(this);
        findViewById(R.id.btn_SyncStage).setOnClickListener(this);
        findViewById(R.id.btn_SyncSleep).setOnClickListener(this);
        findViewById(R.id.btn_SyncSport).setOnClickListener(this);
        findViewById(R.id.btn_SyncReal).setOnClickListener(this);
        findViewById(R.id.btn_askDeviceInfo).setOnClickListener(this);
        findViewById(R.id.btn_startUpgrade).setOnClickListener(this);
        findViewById(R.id.btn_destroyUp).setOnClickListener(this);
        findViewById(R.id.btn_isUpgrade).setOnClickListener(this);
        tv_return = (TextView)findViewById(R.id.tv_return);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        iHealthDevicesManager.getInstance().unRegisterClientCallback(clientId);
    }


    private iHealthDevicesCallback miHealthDevicesCallback = new iHealthDevicesCallback() {

        @Override
        public void onDeviceConnectionStateChange(String mac, String deviceType, int status, int errorID) {}

        @Override
        public void onDeviceNotify(String mac, String deviceType, String action, String message) {
            Log.i(TAG, "Action-----> "+action);
            if (message != null) {
                Log.i(TAG, "message-----> "+message);
            }
            switch (action) {
                case AmProfile.ACTION_QUERY_STATE_AM:
                    try {
                        JSONObject info = new JSONObject(message);
                        String battery =info.getString(AmProfile.QUERY_BATTERY_AM);
                        Message msg = new Message();
                        msg.what = HANDLER_MESSAGE;
                        msg.obj = "battery: " + battery;
                        myHandler.sendMessage(msg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;

                case AmProfile.ACTION_USERID_AM:
                    try {
                        JSONObject info = new JSONObject(message);
                        String id =info.getString(AmProfile.USERID_AM);
                        Message msg = new Message();
                        msg.what = HANDLER_MESSAGE;
                        msg.obj = "User ID: " + id;
                        myHandler.sendMessage(msg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case AmProfile.ACTION_GET_ALARMNUM_AM:
                    try {
                        JSONObject info = new JSONObject(message);
                        String alarm_num =info.getString(AmProfile.GET_ALARMNUM_AM);
                        Message msg = new Message();
                        msg.what = HANDLER_MESSAGE;
                        msg.obj = "Alarm Num: " + alarm_num;
                        myHandler.sendMessage(msg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case AmProfile.ACTION_SYNC_STAGE_DATA_AM:
                    try {
                        JSONObject info = new JSONObject(message);
                        String stage_info =info.getString(AmProfile.SYNC_STAGE_DATA_AM);
                        Message msg = new Message();
                        msg.what = HANDLER_MESSAGE;
                        msg.obj = "Stage Data: " + stage_info;
                        myHandler.sendMessage(msg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case AmProfile.ACTION_SYNC_SLEEP_DATA_AM:
                    try {
                        JSONObject info = new JSONObject(message);
                        String stage_info =info.getString(AmProfile.SYNC_SLEEP_DATA_AM);
                        Message msg = new Message();
                        msg.what = HANDLER_MESSAGE;
                        msg.obj = "Sleep Data: " + stage_info;
                        myHandler.sendMessage(msg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case AmProfile.ACTION_SYNC_ACTIVITY_DATA_AM:
                    try {
                        JSONObject info = new JSONObject(message);
                        String activity_info =info.getString(AmProfile.SYNC_ACTIVITY_DATA_AM);
                        Message msg = new Message();
                        msg.what = HANDLER_MESSAGE;
                        msg.obj = "Activity Data: " + activity_info;
                        myHandler.sendMessage(msg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case AmProfile.ACTION_SYNC_REAL_DATA_AM:
                    try {
                        JSONObject info = new JSONObject(message);
                        String real_info =info.getString(AmProfile.SYNC_REAL_STEP_AM);
                        Message msg = new Message();
                        msg.what = HANDLER_MESSAGE;
                        msg.obj = "Real Step: " + real_info;
                        myHandler.sendMessage(msg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case AmProfile.ACTION_GET_USERINFO_AM:
                    try {
                        JSONObject info = new JSONObject(message);
                        String user_info =info.getString(AmProfile.GET_USER_AGE_AM);
                        Message msg = new Message();
                        msg.what = HANDLER_MESSAGE;
                        msg.obj = "User Age: " + user_info;
                        myHandler.sendMessage(msg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case AmProfile.ACTION_GET_ALARMINFO_AM:
                    try {
                        JSONObject info = new JSONObject(message);
                        String alarm_id =info.getString(AmProfile.GET_ALARM_ID_AM);
                        Message msg = new Message();
                        msg.what = HANDLER_MESSAGE;
                        msg.obj = "Alarm ID: " + alarm_id;
                        myHandler.sendMessage(msg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case AmProfile.ACTION_SET_USERID_SUCCESS_AM:
                    Message msg = new Message();
                    msg.what = HANDLER_MESSAGE;
                    msg.obj = "Set ID success";
                    myHandler.sendMessage(msg);
                    break;
                case AmProfile.ACTION_SET_ALARMINFO_SUCCESS_AM:
                    Message msg1 = new Message();
                    msg1.what = HANDLER_MESSAGE;
                    msg1.obj = "Set Alarm success";
                    myHandler.sendMessage(msg1);
                    break;
                case AmProfile.ACTION_GET_RANDOM_AM:
                    try {
                        JSONObject info = new JSONObject(message);
                        String random =info.getString(AmProfile.GET_RANDOM_AM);
                        Message msg2 = new Message();
                        msg2.what = HANDLER_MESSAGE;
                        msg2.obj = "Random: " + random;
                        myHandler.sendMessage(msg2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case AmProfile.ACTION_SET_USERINFO_SUCCESS_AM:
                    Message msg3 = new Message();
                    msg3.what = HANDLER_MESSAGE;
                    msg3.obj = "Set User Info success";
                    myHandler.sendMessage(msg3);
                    break;
                case AmProfile.ACTION_RESET_AM:
                    Message msg4 = new Message();
                    msg4.what = HANDLER_MESSAGE;
                    msg4.obj = "Reset success";
                    myHandler.sendMessage(msg4);
                    break;
                case AmProfile.ACTION_SET_SWIMINFO_AM:
                    Message msg5 = new Message();
                    msg5.what = HANDLER_MESSAGE;
                    msg5.obj = "Set Swim Para success";
                    myHandler.sendMessage(msg5);
                    break;
                case AmProfile.ACTION_GET_SWIMINFO_AM:
                    try {
                        JSONObject info = new JSONObject(message);
                        String length =info.getString(AmProfile.GET_SWIMLANE_LENGTH_AM);
                        Message msg6 = new Message();
                        msg6.what = HANDLER_MESSAGE;
                        msg6.obj = "Swim pool length: " + length;
                        myHandler.sendMessage(msg6);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case UpgradeProfile.ACTION_DEVICE_UP_INFO:
                    //{"up.device.upgrade.flag":0,"up.device.firmware.version":"1.3.7","status":0,"up.device.up.mode":"101"}
                    try {
                        o = new JSONObject(message);
                        String firmware =o.getString(UpgradeProfile.DEVICE_FIRMWARE_VERSION);
                        Message msg7 = new Message();
                        msg7.what = HANDLER_MESSAGE;
                        msg7.obj = "CurrentVersion: " + firmware;
                        myHandler.sendMessage(msg7);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, o.toString());
                    break;
                case UpgradeProfile.ACTION_DEVICE_CLOUD_FIRMWARE_VERSION:
                    //{"up.device.cloud.firmware.version":"1.3.9","up.device.mandatory.flag":1}
                    try {
                        JSONObject info = new JSONObject(message);
                        String firmware = info.getString(UpgradeProfile.DEVICE_CLOUD_FIRMWARE_VERSION);
                        Message msg8 = new Message();
                        msg8.what = HANDLER_MESSAGE;
                        msg8.obj = "Latest Version: " + firmware;
                        myHandler.sendMessage(msg8);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, message.toString());
                    break;
                case UpgradeProfile.ACTION_DEVICE_UP_START_DOWNLOAD:
                    //"action.up.start.download"

                    Message msg9 = new Message();
                    msg9.what = HANDLER_MESSAGE;
                    msg9.obj = "Start download firmware form cloud";
                    myHandler.sendMessage(msg9);

                    Log.i(TAG, msg9.toString());
                    break;
                case UpgradeProfile.ACTION_DEVICE_UP_DOWNLOAD_COMPLETED:
                    //"action.up.download.completed"

                    Message msg91 = new Message();
                    msg91.what = HANDLER_MESSAGE;
                    msg91.obj = "Download firmware completed";
                    myHandler.sendMessage(msg91);

                    Log.i(TAG, msg91.toString());
                    break;
                case UpgradeProfile.ACTION_DEVICE_START_UP:
                    //{"status":0}
                    try {
                        JSONObject info = new JSONObject(message);
                        String startUp = info.getString(UpgradeProfile.DEVICE_FINISH_UP_FLAG);
                        Message startUpMsg = new Message();
                        startUpMsg.what = HANDLER_MESSAGE;
                        startUpMsg.obj = "upgrade flag: " + startUp;
                        myHandler.sendMessage(startUpMsg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, message.toString());
                    break;
                case UpgradeProfile.ACTION_DEVICE_STOP_UP:
                    //"action.up.device.stop.up"
                    Message stopUpMsg = new Message();
                    stopUpMsg.what = HANDLER_MESSAGE;
                    stopUpMsg.obj = "stop upgrade";
                    myHandler.sendMessage(stopUpMsg);
                    break;
                case UpgradeProfile.ACTION_DEVICE_UP_PROGRESS:
                    //{"progress":0}
                    try {
                        JSONObject info = new JSONObject(message);
                        String firmwareProgress = info.getString(UpgradeProfile.DEVICE_PROGRESS_VALUE);
                        Message msg10 = new Message();
                        msg10.what = HANDLER_MESSAGE;
                        msg10.obj = "upgrade progress: " + firmwareProgress;
                        myHandler.sendMessage(msg10);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, message.toString());
                    break;
                case UpgradeProfile.ACTION_DEVICE_FINISH_UP:
                    //{"status":1}
                    try {
                        JSONObject info = new JSONObject(message);
                        String finishUp = info.getString(UpgradeProfile.DEVICE_FINISH_UP_FLAG);
                        Message finishUpMsg = new Message();
                        finishUpMsg.what = HANDLER_MESSAGE;
                        finishUpMsg.obj = "upgrade completed: " + finishUp;
                        myHandler.sendMessage(finishUpMsg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, message.toString());
                    break;
                case UpgradeProfile.ACTION_DEVICE_ERROR:
                    //{"device.access.hardware.error":104}
                    try {
                        JSONObject info = new JSONObject(message);
                        String errorCode = info.getString(UpgradeProfile.DEVICE_UP_ERROR);
                        Message msg12 = new Message();
                        msg12.what = HANDLER_MESSAGE;
                        msg12.obj = "errorCode: " + errorCode;
                        myHandler.sendMessage(msg12);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, message.toString());
                    break;
                default:
                    break;
            }

        }
    };

    @Override
    public void onClick(View arg0) {
        int id = arg0.getId();
        switch (id) {
            case R.id.btn_SetUserMessage:
                if (am4Control != null)
                    am4Control.setUserInfo(29, 177, 65, 1, 1, 10000, 1, 59);
                else
                    Toast.makeText(AM4.this, "am4Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_GetUserMessage:
                if (am4Control != null)
                    am4Control.getUserInfo();
                else
                    Toast.makeText(AM4.this, "am4Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_SetUserID:
                if (am4Control != null)
                    am4Control.setUserId(123456);
                else
                    Toast.makeText(AM4.this, "am4Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_GetUserID:
                if (am4Control != null)
                    am4Control.getUserId();
                else
                    Toast.makeText(AM4.this, "am4Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_Reset:
                if (am4Control != null)
                    am4Control.reset(123456);
                else
                    Toast.makeText(AM4.this, "am4Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_checkSwimPara:
                if (am4Control != null)
                    am4Control.checkSwimPara();
                else
                    Toast.makeText(AM4.this, "am4Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_SetSwimPara:
                if (am4Control != null)
                    am4Control.setSwimPara(AmProfile.AM_SWITCH_OPEN, 50, 0, 10, AmProfile.AM_SET_UNIT_METRIC);
                else
                    Toast.makeText(AM4.this, "am4Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_SetRemind:
                if (am4Control != null)
                    am4Control.setActivityRemind(1,20,true);
                else
                    Toast.makeText(AM4.this, "am4Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_GetRemind:
                if (am4Control != null)
                    am4Control.getActivityRemind();
                else
                    Toast.makeText(AM4.this, "am4Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_SetClock:
                if (am4Control != null)
                    am4Control.setAlarmClock(1,10,20,true,new int[] {1,1,1,1,1,1,1},true);
                else
                    Toast.makeText(AM4.this, "am4Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_GetClock:
                if (am4Control != null)
                    am4Control.getAlarmClockDetail(1);
                else
                    Toast.makeText(AM4.this, "am4Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_GetBattery:
                if (am4Control != null)
                    am4Control.queryAMState();
                else
                    Toast.makeText(AM4.this, "am4Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_SetRealTime:
                if (am4Control != null)
                    am4Control.syncRealTime();
                else
                    Toast.makeText(AM4.this, "am4Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_SetTimeMode:
                if (am4Control != null)
                    am4Control.setHourMode(1);
                else
                    Toast.makeText(AM4.this, "am4Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_SyncStage:
                if (am4Control != null)
                    am4Control.syncStageReprotData();
                else
                    Toast.makeText(AM4.this, "am4Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_SyncSleep:
                if (am4Control != null)
                    am4Control.syncSleepData();
                else
                    Toast.makeText(AM4.this, "am4Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_SyncSport:
                if (am4Control != null)
                    am4Control.syncActivityData();
                else
                    Toast.makeText(AM4.this, "am4Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_SyncReal:
                if (am4Control != null)
                    am4Control.syncRealData();
                else
                    Toast.makeText(AM4.this, "am4Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_askDeviceInfo:
                if (am4Control != null)
                    iHealthDevicesUpgradeManager.getInstance().queryUpgradeInfoFromDeviceAndCloud(deviceMac, iHealthDevicesManager.TYPE_AM4);
                else
                    Toast.makeText(AM4.this, "am4Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_startUpgrade:
                if (am4Control != null)
                    iHealthDevicesUpgradeManager.getInstance().startUpGrade(deviceMac, iHealthDevicesManager.TYPE_AM4);
                else
                    Toast.makeText(AM4.this, "am4Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_destroyUp:
                if (am4Control != null)
                    iHealthDevicesUpgradeManager.getInstance().stopUpgrade(deviceMac, iHealthDevicesManager.TYPE_AM4);
                else
                    Toast.makeText(AM4.this, "am4Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_isUpgrade:
                if (am4Control != null)
                    Log.i(TAG, iHealthDevicesUpgradeManager.getInstance().isUpgradeState(deviceMac, iHealthDevicesManager.TYPE_AM4)+"");
                else
                    Toast.makeText(AM4.this, "am4Control == null", Toast.LENGTH_LONG).show();
                break;

            default:
                break;
        }
    }

    private static final int HANDLER_MESSAGE = 101;
    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLER_MESSAGE:
                    tv_return.setText((String)msg.obj);
                    break;
            }
            super.handleMessage(msg);
        }
    };

}
