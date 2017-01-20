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

import com.ihealth.communication.control.Bg5lControl;
import com.ihealth.communication.control.Bg5Profile;
import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jing on 16/6/30.
 */


public class BG5l extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = this.getClass().toString();
    private Bg5lControl bg5lControl;
    private String deviceMac;
    private int clientCallbackId;
    private TextView tv_return;
    public String QRCode = "02323C641E3114322D0800A064646464646464646464FA012261000E1CCC";

    private Timer mTimer;
    private TimerTask mTimerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bg5l);
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
        findViewById(R.id.btn_getBattery).setOnClickListener(this);
        findViewById(R.id.btn_syncTime).setOnClickListener(this);
        findViewById(R.id.btn_syncUnit).setOnClickListener(this);
        findViewById(R.id.btn_getBottleId).setOnClickListener(this);
        findViewById(R.id.btn_getCode).setOnClickListener(this);
        findViewById(R.id.btn_getOfflineData).setOnClickListener(this);
        findViewById(R.id.btn_deleteOfflineData).setOnClickListener(this);
        findViewById(R.id.btn_sendQRCode).setOnClickListener(this);
        findViewById(R.id.btn_setBottleMessage).setOnClickListener(this);
        findViewById(R.id.btn_startMeasure).setOnClickListener(this);
        findViewById(R.id.btn_holdLink).setOnClickListener(this);
        findViewById(R.id.btn_disconnect).setOnClickListener(this);

        tv_return = (TextView) findViewById(R.id.tv_msgReturn);

        clientCallbackId = iHealthDevicesManager.getInstance().registerClientCallback(miHealthDevicesCallback);
        /* Limited wants to receive notification specified device */
        iHealthDevicesManager.getInstance().addCallbackFilterForDeviceType(clientCallbackId, iHealthDevicesManager.TYPE_BG5l);
        /* Get bg5 controller */
        bg5lControl = iHealthDevicesManager.getInstance().getBG5lControl(deviceMac);
//        bg5lControl.setTime();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iHealthDevicesManager.getInstance().unRegisterClientCallback(clientCallbackId);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btn_getBattery:
                if (bg5lControl != null) {
                    bg5lControl.getBattery();
                } else {
                    Toast.makeText(BG5l.this, "bg5Control == null", Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.btn_syncTime:
                if (bg5lControl != null)
                    bg5lControl.setTime();
                else
                    Toast.makeText(BG5l.this, "bg5Control == null", Toast.LENGTH_LONG).show();

                break;
            case R.id.btn_syncUnit:
                if (bg5lControl != null)
                    bg5lControl.setUnit(1);
                else
                    Toast.makeText(BG5l.this, "bg5Control == null", Toast.LENGTH_LONG).show();

                break;
            case R.id.btn_getBottleId:
                if (bg5lControl != null)
                    bg5lControl.getBottleId();
                else
                    Toast.makeText(BG5l.this, "bg5Control == null", Toast.LENGTH_LONG).show();

                break;
            case R.id.btn_getCode:
                if (bg5lControl != null)
                    bg5lControl.getBottleMessage();
                else
                    Toast.makeText(BG5l.this, "bg5Control == null", Toast.LENGTH_LONG).show();

                break;
            case R.id.btn_getOfflineData:
                if (bg5lControl != null)
                    bg5lControl.getOfflineData();
                else
                    Toast.makeText(BG5l.this, "bg5Control == null", Toast.LENGTH_LONG).show();

                break;
            case R.id.btn_deleteOfflineData:
                if (bg5lControl != null)
                    bg5lControl.deleteOfflineData();
                else
                    Toast.makeText(BG5l.this, "bg5Control == null", Toast.LENGTH_LONG).show();

                break;
            case R.id.btn_sendQRCode:
                if (bg5lControl != null) {
                    String QRInfo = bg5lControl.getBottleInfoFromQR(QRCode);
                    Log.i(TAG, "QRinfo =" + QRInfo);
                    bg5lControl.setBottleMessageWithInfo(1, 1, QRCode, 20, "2017-2-14");
                } else
                    Toast.makeText(BG5l.this, "bg5Control == null", Toast.LENGTH_LONG).show();

                break;
            case R.id.btn_setBottleMessage:
                if (bg5lControl != null) {
                    bg5lControl.setBottleMessageWithInfo(2, 1, "", 20, "2017-2-14");
                } else
                    Toast.makeText(BG5l.this, "bg5Control == null", Toast.LENGTH_LONG).show();

                break;
            case R.id.btn_startMeasure:
                if (bg5lControl != null)
                    bg5lControl.startMeasure(1);
                else
                    Toast.makeText(BG5l.this, "bg5Control == null", Toast.LENGTH_LONG).show();

                break;
            case R.id.btn_holdLink:
                if (bg5lControl != null)
                    bg5lControl.holdLink();
                else
                    Toast.makeText(BG5l.this, "bg5Control == null", Toast.LENGTH_LONG).show();

                break;
            case R.id.btn_disconnect:
                if (bg5lControl != null) {
                    closeTimer();
                    bg5lControl.disconnect();
                } else
                    Toast.makeText(BG5l.this, "bg5Control == null", Toast.LENGTH_LONG).show();

                break;
        }

    }

    private iHealthDevicesCallback miHealthDevicesCallback = new iHealthDevicesCallback() {

        @Override
        public void onDeviceConnectionStateChange(String mac,
                                                  String deviceType, int status, int errorID) {
            Log.i(TAG, "mac: " + mac);
            Log.i(TAG, "deviceType: " + deviceType);
            Log.i(TAG, "status: " + status);
            if (status == 2) {//disconnect
                closeTimer();
                Message msg = new Message();
                msg.what = HANDLER_MESSAGE;
                msg.obj = "disconnect" + "  deviceType: " + deviceType + "  mac: " + mac;
                myHandler.sendMessage(msg);
            }
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

            if (Bg5Profile.ACTION_BATTERY_BG.equals(action)) {
                try {
                    JSONObject info = new JSONObject(message);
                    String battery = info.getString(Bg5Profile.BATTERY_BG);
                    Message msg = new Message();
                    msg.what = HANDLER_MESSAGE;
                    msg.obj = "battery: " + battery;
                    myHandler.sendMessage(msg);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } else if (Bg5Profile.ACTION_ERROR_BG.equals(action)) {
                try {
                    JSONObject info = new JSONObject(message);
                    String num = info.getString(Bg5Profile.ERROR_NUM_BG);
                    Message msg = new Message();
                    msg.what = HANDLER_MESSAGE;
                    msg.obj = "error num: " + num;
                    myHandler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else if (Bg5Profile.ACTION_GET_BOTTLEID.equals(action)) {
                try {
                    JSONObject info = new JSONObject(message);
                    String bottleId = info.getString(Bg5Profile.GET_BOTTLEID);
                    Message msg = new Message();
                    msg.what = HANDLER_MESSAGE;
                    msg.obj = "bottleId: " + bottleId;
                    myHandler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (Bg5Profile.ACTION_GET_CODEINFO.equals(action)) {
                try {
                    JSONObject info = new JSONObject(message);
                    String expireTime = info.getString(Bg5Profile.GET_EXPIRECTIME);
                    String usedNum = info.getString(Bg5Profile.GET_USENUM);
                    Message msg = new Message();
                    msg.what = HANDLER_MESSAGE;
                    msg.obj = "bottleInfo: " + info;
                    myHandler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else if (Bg5Profile.ACTION_HISTORICAL_DATA_BG.equals(action)) {
                Log.e(TAG, "History data successfully!");

                Message msg = new Message();
                msg.what = HANDLER_MESSAGE;
                msg.obj = "History data successfully!";
                myHandler.sendMessage(msg);
//                    bg5lControl.deleteOfflineData();


            } else if (Bg5Profile.ACTION_ONLINE_RESULT_BG.equals(action)) {
                try {
                    JSONObject info = new JSONObject(message);
                    String result = info.getString(Bg5Profile.ONLINE_RESULT_BG);
                    Message msg = new Message();
                    msg.what = HANDLER_MESSAGE;
                    msg.obj = "result: " + result;
                    myHandler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else if (Bg5Profile.ACTION_SET_TIME.equals(action)) {
                Log.e(TAG, "set time successfully");

                Message msg = new Message();
                msg.what = HANDLER_MESSAGE;
                msg.obj = "set time successfully!";
                myHandler.sendMessage(msg);

//                bg5lControl.setUnit(1);

            } else if (Bg5Profile.ACTION_SET_UNIT.equals(action)) {
                Log.e(TAG, "set unit successfully");

                Message msg = new Message();
                msg.what = HANDLER_MESSAGE;
                msg.obj = "set unit successfully!";
                myHandler.sendMessage(msg);

//                bg5lControl.getBattery();

            } else if (Bg5Profile.ACTION_STRIP_IN.equals(action)) {
                Message msg = new Message();
                msg.what = HANDLER_MESSAGE;
                msg.obj = "strip in";
                myHandler.sendMessage(msg);
            } else if (Bg5Profile.ACTION_GET_BLOOD.equals(action)) {
                Message msg = new Message();
                msg.what = HANDLER_MESSAGE;
                msg.obj = "get blood";
                myHandler.sendMessage(msg);
            } else if (Bg5Profile.ACTION_STRIP_OUT.equals(action)) {
                Message msg = new Message();
                msg.what = HANDLER_MESSAGE;
                msg.obj = "strip out";
                myHandler.sendMessage(msg);
            } else if (Bg5Profile.ACTION_START_MEASURE.equals(action)) {
                Message msg = new Message();
                msg.what = HANDLER_MESSAGE;
                msg.obj = "start measure";
                myHandler.sendMessage(msg);
            } else if (Bg5Profile.ACTION_DELETE_HISTORICAL_DATA.equals(action)) {
                Log.e(TAG, "delete historical data successfully!");

                Message msg = new Message();
                msg.what = HANDLER_MESSAGE;
                msg.obj = "delete historical data successfully!";
                myHandler.sendMessage(msg);
//                bg5lControl.setBottleMessage(QRCode);
            } else if (Bg5Profile.ACTION_SET_BOTTLE_ID_SUCCESS.equals(action)) {
                Log.e(TAG, "set bottle id successfully!");

                Message msg = new Message();
                msg.what = HANDLER_MESSAGE;
                msg.obj = "set bottle id successfully!";
                myHandler.sendMessage(msg);
            } else if (Bg5Profile.ACTION_SET_BOTTLE_MESSAGE_SUCCESS.equals(action)) {
                Log.e(TAG, "set bottle message successfully!");

                Message msg = new Message();
                msg.what = HANDLER_MESSAGE;
                msg.obj = "set bottle message successfully!";
                myHandler.sendMessage(msg);
            }

        }
    };

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


    private void closeTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }

}
