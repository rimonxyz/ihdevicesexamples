package com.ihealth.sdk;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ihealth.communication.control.ABPMControl;
import com.ihealth.communication.control.BpProfile;
import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ABPM extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ABPM";
    private ABPMControl abpmControl;
    private TextView tv_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abpm);

        Intent intent = getIntent();
        String deviceMac = intent.getStringExtra("mac");
        findViewById(R.id.btn_get_battery).setOnClickListener(this);
        findViewById(R.id.btn_get_function_info).setOnClickListener(this);
        findViewById(R.id.btn_set_display_unit).setOnClickListener(this);
        findViewById(R.id.btn_set_measure_time).setOnClickListener(this);
        findViewById(R.id.btn_get_measure_time).setOnClickListener(this);
        findViewById(R.id.btn_set_alarm).setOnClickListener(this);
        findViewById(R.id.btn_get_alarm).setOnClickListener(this);
        findViewById(R.id.btn_get_alarm_type).setOnClickListener(this);
        findViewById(R.id.btn_get_offline_num).setOnClickListener(this);
        findViewById(R.id.btn_get_offline_data).setOnClickListener(this);
        findViewById(R.id.btn_delete_all).setOnClickListener(this);
        findViewById(R.id.btn_disconnect).setOnClickListener(this);
        tv_return = (TextView) findViewById(R.id.tv_return);

        int clientCallbackId = iHealthDevicesManager.getInstance().registerClientCallback(miHealthDevicesCallback);
        /* Limited wants to receive notification specified device */
        iHealthDevicesManager.getInstance().addCallbackFilterForDeviceType(clientCallbackId, iHealthDevicesManager.TYPE_KD926);
        /* Get kd926 controller */
        abpmControl = iHealthDevicesManager.getInstance().getABPMControl(deviceMac);
    }

    private iHealthDevicesCallback miHealthDevicesCallback = new iHealthDevicesCallback() {

        @Override
        public void onDeviceConnectionStateChange(String mac,
                                                  String deviceType, int status, int errorID) {
            Log.i(TAG, "mac: " + mac);
            Log.i(TAG, "deviceType: " + deviceType);
            Log.i(TAG, "status: " + status);
        }

        @Override
        public void onUserStatus(String username, int userStatus) {
            Log.i(TAG, "username: " + username);
            Log.i(TAG, "userState: " + userStatus);
        }

        @Override
        public void onDeviceNotify(String mac, String deviceType,
                                   String action, String message) {
            Log.i(TAG, "mac: " + mac);
            Log.i(TAG, "deviceType: " + deviceType);
            switch (action) {
                case BpProfile.ACTION_BATTERY_BP:
                    try {
                        JSONObject info = new JSONObject(message);
                        String battery = info.getString(BpProfile.BATTERY_BP);
                        String voltage = info.getString(BpProfile.VOLTAGE_BP);
                        String times = info.getString(BpProfile.REMAINING_TIMES_BP);
                        Message batteryMsg = new Message();
                        batteryMsg.what = HANDLER_MESSAGE;
                        batteryMsg.obj = "电池电量: " + battery + "\n当前电视电压: " + voltage + "\n剩余测量次数: " + times;
                        myHandler.sendMessage(batteryMsg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case BpProfile.ACTION_FUNCTION_INFORMATION_BP:
                    Message functionMsg = new Message();
                    functionMsg.what = HANDLER_MESSAGE;
                    functionMsg.obj = "function information: " + message;
                    myHandler.sendMessage(functionMsg);
                    break;
                case BpProfile.ACTION_GET_CYCLE_MEASURE:
                    Message cycleMsg = new Message();
                    cycleMsg.what = HANDLER_MESSAGE;
                    cycleMsg.obj = "cycle measure: " + message;
                    myHandler.sendMessage(cycleMsg);
                    break;
                case BpProfile.ACTION_ALARM_SETTING_BP:
                    Message alarmMsg = new Message();
                    alarmMsg.what = HANDLER_MESSAGE;
                    alarmMsg.obj = "alarm setting: " + message;
                    myHandler.sendMessage(alarmMsg);
                    break;
                case BpProfile.ACTION_ALARM_TYPE_BP:
                    Message typeMsg = new Message();
                    typeMsg.what = HANDLER_MESSAGE;
                    typeMsg.obj = "alarm type: " + message;
                    myHandler.sendMessage(typeMsg);
                    break;
                case BpProfile.ACTION_HISTORICAL_NUM_BP:
                    try {
                        JSONObject info = new JSONObject(message);
                        String num = info.getString(BpProfile.HISTORICAL_NUM_BP);
                        Message msg = new Message();
                        msg.what = HANDLER_MESSAGE;
                        msg.obj = "num: " + num;
                        myHandler.sendMessage(msg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case BpProfile.ACTION_HISTORICAL_DATA_BP:
                    String str = "";
                    try {
                        JSONObject info = new JSONObject(message);
                        if (info.has(BpProfile.HISTORICAL_DATA_BP)) {
                            JSONArray array = info.getJSONArray(BpProfile.HISTORICAL_DATA_BP);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);
                                String date = obj.getString(BpProfile.MEASUREMENT_DATE_BP);
                                String hightPressure = obj.getString(BpProfile.HIGH_BLOOD_PRESSURE_BP);
                                String lowPressure = obj.getString(BpProfile.LOW_BLOOD_PRESSURE_BP);
                                String pulseWave = obj.getString(BpProfile.PULSEWAVE_BP);
                                String ahr = obj.getString(BpProfile.MEASUREMENT_AHR_BP);
                                String hsd = obj.getString(BpProfile.MEASUREMENT_HSD_BP);
                                str = "date:" + date
                                        + "hightPressure:" + hightPressure + "\n"
                                        + "lowPressure:" + lowPressure + "\n"
                                        + "pulseWave" + pulseWave + "\n"
                                        + "ahr:" + ahr + "\n"
                                        + "hsd:" + hsd + "\n";
                            }
                        }
                        Message msg = new Message();
                        msg.what = HANDLER_MESSAGE;
                        msg.obj = str;
                        myHandler.sendMessage(msg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case BpProfile.ACTION_ERROR_BP:
                    try {
                        JSONObject info = new JSONObject(message);
                        String num = info.getString(BpProfile.ERROR_NUM_BP);
                        Message errorNum = new Message();
                        errorNum.what = HANDLER_MESSAGE;
                        errorNum.obj = "error num: " + num;
                        myHandler.sendMessage(errorNum);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;

            }
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_battery:
                if (abpmControl != null) {

                    abpmControl.getBattery();
                } else {
                    Toast.makeText(this, "abpmControl == null", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_get_function_info:
                if (abpmControl != null) {

                    abpmControl.getFunctionInfo();
                } else {
                    Toast.makeText(this, "abpmControl == null", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_set_display_unit:
                if (abpmControl != null) {
                    abpmControl.setDisplayUnit(ABPMControl.UNIT_KPA);
                } else {
                    Toast.makeText(this, "abpmControl == null", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_set_measure_time:
                if (abpmControl != null) {
                    abpmControl.setMeasureTime(1, true, new int[]{7, 0, 5, 3});
                } else {
                    Toast.makeText(this, "abpmControl == null", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_get_measure_time:
                if (abpmControl != null) {
                    abpmControl.getMeasureTime();
                } else {
                    Toast.makeText(this, "abpmControl == null", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_set_alarm:
                if (abpmControl != null) {
                    abpmControl.setAlarm(new int[]{1, 2, 10, 20});
                } else {
                    Toast.makeText(this, "abpmControl == null", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_get_alarm:
                if (abpmControl != null) {
                    abpmControl.getAlarmSetting();
                } else {
                    Toast.makeText(this, "abpmControl == null", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_get_alarm_type:
                if (abpmControl != null) {
                    abpmControl.getAlarmType();
                } else {
                    Toast.makeText(this, "abpmControl == null", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_get_offline_num:
                if (abpmControl != null) {
                    abpmControl.getOfflineNum();
                } else {
                    Toast.makeText(this, "abpmControl == null", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_get_offline_data:
                if (abpmControl != null) {
                    abpmControl.getOfflineData();
                } else {
                    Toast.makeText(this, "abpmControl == null", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_delete_all:
                if (abpmControl != null) {
                    abpmControl.deleteAllMemory();
                } else {
                    Toast.makeText(this, "abpmControl == null", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_disconnect:
                if (abpmControl != null) {
                    abpmControl.disconnect();
                } else {
                    Toast.makeText(this, "abpmControl == null", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private static final int HANDLER_MESSAGE = 101;
    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLER_MESSAGE:
                    tv_return.setText((String) msg.obj);
                    break;
            }
            super.handleMessage(msg);
        }
    };
}
