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

import com.ihealth.communication.control.Bp723Control;
import com.ihealth.communication.control.BpProfile;
import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class KD723 extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "KD926";
    private Bp723Control bp723Control;
    private String deviceMac;
    private int clientCallbackId;
    private TextView tv_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kd723);
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
        deviceMac = intent.getStringExtra("mac");
        findViewById(R.id.btn_getbattery).setOnClickListener(this);
        findViewById(R.id.btn_getOfflineNum).setOnClickListener(this);
        findViewById(R.id.btn_getOffineData).setOnClickListener(this);
        findViewById(R.id.btn_getOffineDataOver).setOnClickListener(this);
        findViewById(R.id.btn_getFunctionInfo).setOnClickListener(this);
        findViewById(R.id.btn_disconnect).setOnClickListener(this);
        tv_return = (TextView)findViewById(R.id.tv_return);

        clientCallbackId = iHealthDevicesManager.getInstance().registerClientCallback(miHealthDevicesCallback);
		/* Limited wants to receive notification specified device */
        iHealthDevicesManager.getInstance().addCallbackFilterForDeviceType(clientCallbackId, iHealthDevicesManager.TYPE_KD723);

        bp723Control = iHealthDevicesManager.getInstance().getBp723Control(deviceMac);
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
            Log.i(TAG, "action: " + action);
            Log.i(TAG, "message: " + message);

            if(BpProfile.ACTION_BATTERY_BP.equals(action)){
                try {
                    JSONObject info = new JSONObject(message);
                    String battery =info.getString(BpProfile.BATTERY_BP);
                    Message msg = new Message();
                    msg.what = HANDLER_MESSAGE;
                    msg.obj = "battery: " + battery;
                    myHandler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(BpProfile.ACTION_ERROR_BP.equals(action)){
                try {
                    JSONObject info = new JSONObject(message);
                    String num =info.getString(BpProfile.ERROR_NUM_BP);
                    Message msg = new Message();
                    msg.what = HANDLER_MESSAGE;
                    msg.obj = "error num: " + num;
                    myHandler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(BpProfile.ACTION_HISTORICAL_DATA_BP.equals(action)){
                String str = "";
                try {
                    JSONObject info = new JSONObject(message);
                    if (info.has(BpProfile.HISTORICAL_DATA_BP)) {
                        JSONArray array = info.getJSONArray(BpProfile.HISTORICAL_DATA_BP);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject obj = array.getJSONObject(i);
                            String date          = obj.getString(BpProfile.MEASUREMENT_DATE_BP);
                            String hightPressure = obj.getString(BpProfile.HIGH_BLOOD_PRESSURE_BP);
                            String lowPressure   = obj.getString(BpProfile.LOW_BLOOD_PRESSURE_BP);
                            String pulseWave     = obj.getString(BpProfile.PULSEWAVE_BP);
                            String ahr           = obj.getString(BpProfile.MEASUREMENT_AHR_BP);
                            String hsd           = obj.getString(BpProfile.MEASUREMENT_HSD_BP);
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
                    msg.obj =  str;
                    myHandler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(BpProfile.ACTION_HISTORICAL_NUM_BP.equals(action)){
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

            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iHealthDevicesManager.getInstance().unRegisterClientCallback(clientCallbackId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_getbattery:
                if(bp723Control != null)
                    bp723Control.getBattery();
                else
                    Toast.makeText(this, "bp723Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_getOfflineNum:
                if(bp723Control != null)
                    bp723Control.getOfflineNum(2);
                else
                    Toast.makeText(this, "bp723Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_getOffineData:
                if(bp723Control != null)
                    bp723Control.getOfflineData(2);
                else
                    Toast.makeText(this, "bp723Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_getOffineDataOver:
                if(bp723Control != null)
                    bp723Control.getOfflineDataOver();
                else
                    Toast.makeText(this, "bp723Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_getFunctionInfo:
                if(bp723Control != null)
                    bp723Control.getFunctionInfo();
                else
                    Toast.makeText(this, "bp723Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_disconnect:
                if(bp723Control != null)
                    bp723Control.disconnect();
                else
                    Toast.makeText(this, "bp723Control == null", Toast.LENGTH_LONG).show();
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
