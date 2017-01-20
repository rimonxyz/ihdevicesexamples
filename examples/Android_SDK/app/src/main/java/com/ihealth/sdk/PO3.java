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

import com.ihealth.communication.control.Po3Control;
import com.ihealth.communication.control.PoProfile;
import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.manager.iHealthDevicesUpgradeManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class PO3 extends AppCompatActivity implements View.OnClickListener{

    private static String TAG = "Po3";

    private TextView tv_return;
    private String deviceMac;
    private int clientId;
    private Po3Control mPo3Control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_po3);
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

        initView();
        Intent intent = getIntent();
        deviceMac = intent.getStringExtra("mac");
        clientId = iHealthDevicesManager.getInstance().registerClientCallback(mIHealthDevicesCallback);
        /* Limited wants to receive notification specified device */
        iHealthDevicesManager.getInstance().addCallbackFilterForDeviceType(clientId,
                iHealthDevicesManager.TYPE_PO3);
        /* Get po3 controller */
        mPo3Control = iHealthDevicesManager.getInstance().getPo3Control(deviceMac);
        Log.d(TAG, "deviceMac:" + deviceMac + "--mPo3Control:" + mPo3Control);
    }

    private void initView() {
        tv_return = (TextView) findViewById(R.id.tv_return);
        findViewById(R.id.btn_getOfflineData).setOnClickListener(this);
        findViewById(R.id.btn_disconnect).setOnClickListener(this);
        findViewById(R.id.btn_startMeasure).setOnClickListener(this);
        findViewById(R.id.btn_getBattery).setOnClickListener(this);
        findViewById(R.id.btn_askDeviceInfo).setOnClickListener(this);
        findViewById(R.id.btn_startUpgrade).setOnClickListener(this);
    }

    iHealthDevicesCallback mIHealthDevicesCallback = new iHealthDevicesCallback() {

        public void onDeviceConnectionStateChange(String mac, String deviceType, int status,int errorID) {
            Log.e(TAG, "mac:" + mac + "-deviceType:" + deviceType + "-status:" + status);
            noticeString = "device disconnect";
            mPo3Control = null;
            Message message2 = new Message();
            message2.what = 1;
            message2.obj = noticeString;
            mHandler.sendMessage(message2);

            switch (status) {
                case iHealthDevicesManager.DEVICE_STATE_DISCONNECTED:
                    mPo3Control=null;
                    Toast.makeText(PO3.this, "The device disconnect", Toast.LENGTH_LONG).show();

                    break;

                default:
                    break;
            }
        };

        public void onDeviceNotify(String mac, String deviceType, String action, String message) {
            Log.d(TAG, "mac:" + mac + "--type:" + deviceType + "--action:" + action + "--message:" + message);
            JSONTokener jsonTokener = new JSONTokener(message);
            switch (action) {
                case PoProfile.ACTION_OFFLINEDATA_PO:
                    try {
                        JSONObject object = (JSONObject) jsonTokener.nextValue();
                        JSONArray jsonArray = object.getJSONArray(PoProfile.OFFLINEDATA_PO);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                            String dataId=jsonObject.getString(PoProfile.DATAID);
                            String dateString = jsonObject.getString(PoProfile.MEASURE_DATE_PO);
                            int oxygen = jsonObject.getInt(PoProfile.BLOOD_OXYGEN_PO);
                            int pulseRate = jsonObject.getInt(PoProfile.PULSE_RATE_PO);
                            JSONArray jsonArray1 = jsonObject.getJSONArray(PoProfile.PULSE_WAVE_PO);
                            int[] wave = new int[jsonArray1.length()];
                            for (int j = 0; j < jsonArray1.length(); j++) {
                                wave[j] = jsonArray1.getInt(j);
                            }
                            Log.i(TAG,"dataId:"+dataId+ "--date:" + dateString + "--oxygen:" + oxygen + "--pulseRate:" + pulseRate
                                    + "-wave1:"
                                    + wave[0]
                                    + "-wave2:" + wave[1] + "--wave3:" + wave[2]);
                        }
                        Message message2 = new Message();
                        message2.what = 1;
                        message2.obj = message;
                        mHandler.sendMessage(message2);
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case PoProfile.ACTION_LIVEDA_PO:
                    try {
                        JSONObject jsonObject = (JSONObject) jsonTokener.nextValue();
                        int oxygen = jsonObject.getInt(PoProfile.BLOOD_OXYGEN_PO);
                        int pulseRate = jsonObject.getInt(PoProfile.PULSE_RATE_PO);
                        float PI = (float) jsonObject.getDouble(PoProfile.PI_PO);
                        JSONArray jsonArray = jsonObject.getJSONArray(PoProfile.PULSE_WAVE_PO);
                        int[] wave = new int[3];
                        for (int i = 0; i < jsonArray.length(); i++) {
                            wave[i] = jsonArray.getInt(i);
                        }
                        Log.i(TAG, "oxygen:" + oxygen + "--pulseRate:" + pulseRate + "--Pi:" + PI + "-wave1:" + wave[0]
                                + "-wave2:" + wave[1] + "--wave3:" + wave[2]);
                        Message message3 = new Message();
                        message3.what = 1;
                        message3.obj = message;
                        mHandler.sendMessage(message3);
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case PoProfile.ACTION_RESULTDATA_PO:
                    try {
                        JSONObject jsonObject = (JSONObject) jsonTokener.nextValue();
                        String dataId=jsonObject.getString(PoProfile.DATAID);
                        int oxygen = jsonObject.getInt(PoProfile.BLOOD_OXYGEN_PO);
                        int pulseRate = jsonObject.getInt(PoProfile.PULSE_RATE_PO);
                        float PI = (float) jsonObject.getDouble(PoProfile.PI_PO);
                        JSONArray jsonArray = jsonObject.getJSONArray(PoProfile.PULSE_WAVE_PO);
                        int[] wave = new int[3];
                        for (int i = 0; i < jsonArray.length(); i++) {
                            wave[i] = jsonArray.getInt(i);
                        }
                        Log.i(TAG, "dataId:"+dataId+"--oxygen:" + oxygen + "--pulseRate:" + pulseRate + "--Pi:" + PI + "-wave1:" + wave[0]
                                + "-wave2:" + wave[1] + "--wave3:" + wave[2]);
                        Message message3 = new Message();
                        message3.what = 1;
                        message3.obj = message;
                        mHandler.sendMessage(message3);
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case PoProfile.ACTION_NO_OFFLINEDATA_PO:
                    noticeString = "no history data";
                    Message message2 = new Message();
                    message2.what = 1;
                    message2.obj = noticeString;
                    mHandler.sendMessage(message2);
                    break;
                case PoProfile.ACTION_BATTERY_PO:
                    JSONObject jsonobject;
                    try {
                        jsonobject = (JSONObject) jsonTokener.nextValue();
                        int battery = jsonobject.getInt(PoProfile.BATTERY_PO);
                        Log.d(TAG, "battery:" + battery);

                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    Message message3 = new Message();
                    message3.what = 1;
                    message3.obj = message;
                    mHandler.sendMessage(message3);
                    break;
                default:
                    break;
            }
        };
    };
    String noticeString = "";
    Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    tv_return.setText((String) msg.obj);
                    break;

                default:
                    break;
            }
        };
    };

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btn_getOfflineData:
                if (mPo3Control == null) {
                    Log.i(TAG, "mPo3Control == null");
                    Toast.makeText(PO3.this, "mPo3Control == null", Toast.LENGTH_LONG).show();

                } else {
                    mPo3Control.getHistoryData();
                }
                break;
            case R.id.btn_startMeasure:
                if (mPo3Control == null) {
                    Log.i(TAG, "mPo3Control == null");
                    Toast.makeText(PO3.this, "mPo3Control == null", Toast.LENGTH_LONG).show();
                    ;

                } else {
                    mPo3Control.startMeasure();
                }
                break;
            case R.id.btn_getBattery:
                if (mPo3Control == null) {
                    Log.i(TAG, "mPo3Control == null");
                    Toast.makeText(PO3.this, "mPo3Control == null", Toast.LENGTH_LONG).show();

                } else {
                    mPo3Control.getBattery();
                }
                break;
            case R.id.btn_askDeviceInfo:
                if (mPo3Control != null)
                    iHealthDevicesUpgradeManager.getInstance().queryUpgradeInfoFromDeviceAndCloud(deviceMac, iHealthDevicesManager.TYPE_PO3);
                else
                    Toast.makeText(PO3.this, "mPo3Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_startUpgrade:
                if (mPo3Control != null)
                    iHealthDevicesUpgradeManager.getInstance().startUpGrade(deviceMac, iHealthDevicesManager.TYPE_PO3);
                else
                    Toast.makeText(PO3.this, "mPo3Control == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_disconnect:
                if (mPo3Control == null) {
                    Log.i(TAG, "mPo3Control == null");
                    Toast.makeText(PO3.this, "mPo3Control == null", Toast.LENGTH_LONG).show();

                } else {
                    mPo3Control.disconnect();
                }
                break;
            default:
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iHealthDevicesManager.getInstance().unRegisterClientCallback(clientId);
    }

}
