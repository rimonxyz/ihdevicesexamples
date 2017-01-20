package com.ihealth.sdk;

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

import com.ihealth.communication.control.AbiControl;
import com.ihealth.communication.control.AbiProfile;
import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;

import org.json.JSONException;
import org.json.JSONObject;

public class ABI extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "ABI";
    private String deviceMacForArm;
    private String deviceMac;
    private AbiControl abiControl;
    private int clientCallbackId;
    private TextView tv_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abi);
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

        //Get the mac address of arm blood pressure meter, if you only want to measure arm blood pressure.
        deviceMacForArm = AbiControl.getAbiConnectedForArm();
        //Get the mac address of leg blood pressure meter, if you  want to measure arm and len blood pressure at the same time (ABI).
        deviceMac = AbiControl.getAbiConnected();
        findViewById(R.id.btn_getAbiOnlyForArm).setOnClickListener(this);
        findViewById(R.id.btn_getAbi).setOnClickListener(this);
        findViewById(R.id.btn_getbattery).setOnClickListener(this);
        findViewById(R.id.btn_startMeasure).setOnClickListener(this);
        findViewById(R.id.btn_stopMeasure).setOnClickListener(this);
        findViewById(R.id.btn_disconnect).setOnClickListener(this);
        tv_return = (TextView)findViewById(R.id.tv_return);

        clientCallbackId = iHealthDevicesManager.getInstance().registerClientCallback(miHealthDevicesCallback);
		/* Limited wants to receive notification specified device */
        iHealthDevicesManager.getInstance().addCallbackFilterForDeviceType(clientCallbackId, iHealthDevicesManager.TYPE_BP5);

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
            Log.i(TAG, "mac: " + mac + " deviceType: " + deviceType + " action: " + action + " message: " + message);

            if(AbiProfile.ACTION_BATTERY_ABI.equals(action)){
                try {
                    JSONObject info = new JSONObject(message);
                    int battery =info.getInt(AbiProfile.BATTERY_ABI);
                    Message msg = new Message();
                    msg.what = HANDLER_MESSAGE;
                    msg.obj = "battery: " + battery;
                    myHandler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else if (AbiProfile.ACTION_ERROR_ABI.equals(action)) {
                try {
                    JSONObject info = new JSONObject(message);
                    int errorID =info.getInt(AbiProfile.ERROR_NUM_ABI);
                    Message msg = new Message();
                    msg.what = HANDLER_MESSAGE;
                    msg.obj = "error: " + errorID;
                    myHandler.sendMessage(msg);

                    //measure error , stop measure
                    if(abiControl != null)
                        abiControl.stopMeasure();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (AbiProfile.ACTION_ZEROING_ABI.equals(action)) {
                Message msg = new Message();
                msg.what = HANDLER_MESSAGE;
                msg.obj = "zeroing" + " " + mac;
                myHandler.sendMessage(msg);

            } else if (AbiProfile.ACTION_ZERO_OVER_ABI.equals(action)) {
                Message msg = new Message();
                msg.what = HANDLER_MESSAGE;
                msg.obj = "zero over" + " " + mac;
                myHandler.sendMessage(msg);

            } else if (AbiProfile.ACTION_ONLINE_PRESSURE_ABI.equals(action)) {
                try {
                    JSONObject info = new JSONObject(message);
                    int pressure =info.getInt(AbiProfile.BLOOD_PRESSURE_ABI);
                    Message msg = new Message();
                    msg.what = HANDLER_MESSAGE;
                    msg.obj = "pressure: " + pressure + " " + mac;
                    myHandler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (AbiProfile.ACTION_ONLINE_PULSE_WAVE_ABI.equals(action)) {
                try {
                    JSONObject info = new JSONObject(message);
                    int pressure =info.getInt(AbiProfile.BLOOD_PRESSURE_ABI);
                    boolean heartbeat = info.getBoolean(AbiProfile.FLAG_HEARTBEAT_ABI);
                    String wave = info.getString(AbiProfile.PULSE_WAVE_ABI);
                    Message msg = new Message();
                    msg.what = HANDLER_MESSAGE;
                    msg.obj = "pressure: " + pressure + " heartbeat" + heartbeat + " wave" + wave  + " "+ mac;
                    myHandler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else if (AbiProfile.ACTION_ONLINE_RESULT_ABI.equals(action)) {
                try {
                    JSONObject info = new JSONObject(message);
                    int high_pressure = info.getInt(AbiProfile.HIGH_BLOOD_PRESSURE_ABI);
                    int low_pressure = info.getInt(AbiProfile.LOW_BLOOD_PRESSURE_ABI);
                    int pulse = info.getInt(AbiProfile.PULSE_ABI);
                    int ahr = info.getInt(AbiProfile.MEASUREMENT_AHR_ABI);
                    int hsd = info.getInt(AbiProfile.MEASUREMENT_HSD_ABI);

                    Message msg = new Message();
                    msg.what = HANDLER_MESSAGE;
                    msg.obj = "high_pressure: " + high_pressure + " low_pressure" + low_pressure + " pulse" + pulse  + " "+ mac;
                    myHandler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (AbiProfile.ACTION_STOP_ABI.equals(action)) {
                Message msg = new Message();
                msg.what = HANDLER_MESSAGE;
                msg.obj = "stop measure from app:"+ " " + mac;
                myHandler.sendMessage(msg);
            } else if (AbiProfile.ACTION_INTERRUPTED_ABI.equals(action)) {
                Message msg = new Message();
                msg.what = HANDLER_MESSAGE;
                msg.obj = "stop measure from meter:"+ " " + mac;
                myHandler.sendMessage(msg);

                //measure error , stop measure
                if(abiControl != null)
                    abiControl.stopMeasure();
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_getAbiOnlyForArm:
                abiControl = iHealthDevicesManager.getInstance().getAbiControlforUp(deviceMacForArm);
                if(abiControl == null)
                    Toast.makeText(ABI.this, "abiControl == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_getAbi:
                abiControl = iHealthDevicesManager.getInstance().getAbiControl(deviceMac);
                if(abiControl == null)
                    Toast.makeText(ABI.this, "abiControl == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_getbattery:
                if(abiControl != null)
                    abiControl.getBattery();
                else
                    Toast.makeText(ABI.this, "abiControl == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_startMeasure:
                if(abiControl != null)
                    abiControl.startMeasure();
                else
                    Toast.makeText(ABI.this, "abiControl == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_stopMeasure:
                if(abiControl != null)
                    abiControl.stopMeasure();
                else
                    Toast.makeText(ABI.this, "abiControl == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_disconnect:
                if(abiControl != null)
                    abiControl.disconnect();
                else
                    Toast.makeText(ABI.this, "abiControl == null", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        iHealthDevicesManager.getInstance().unRegisterClientCallback(clientCallbackId);
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
