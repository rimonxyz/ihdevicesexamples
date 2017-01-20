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

import com.ihealth.communication.control.Bg5Control;
import com.ihealth.communication.control.Bg5Profile;
import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;

import java.util.Timer;
import java.util.TimerTask;

public class BG5 extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Bg5";
    private Bg5Control bg5Control;
    private String deviceMac;
    private int clientCallbackId;
    private TextView tv_return;
    public String QRCode = "02323C641E3114322D0800A064646464646464646464FA012261000E1CCC";

    private Timer mTimer;
    private TimerTask mTimerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bg5);
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
        iHealthDevicesManager.getInstance().addCallbackFilterForDeviceType(clientCallbackId, iHealthDevicesManager.TYPE_BG5);
        /* Get bg5 controller */
        bg5Control = iHealthDevicesManager.getInstance().getBg5Control(deviceMac);
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
                if (bg5Control != null)
                    bg5Control.getBattery();
                else
                    Toast.makeText(BG5.this, "bg5Control == null", Toast.LENGTH_LONG).show();

                break;
            case R.id.btn_syncTime:
                if (bg5Control != null)
                    bg5Control.setTime();

                else
                    Toast.makeText(BG5.this, "bg5Control == null", Toast.LENGTH_LONG).show();

                break;
            case R.id.btn_syncUnit:
                if (bg5Control != null)
                    bg5Control.setUnit(1);
                else
                    Toast.makeText(BG5.this, "bg5Control == null", Toast.LENGTH_LONG).show();

                break;
            case R.id.btn_getBottleId:
                if (bg5Control != null)
                    bg5Control.getBottleId();
                else
                    Toast.makeText(BG5.this, "bg5Control == null", Toast.LENGTH_LONG).show();

                break;
            case R.id.btn_getCode:
                if (bg5Control != null)
                    bg5Control.getBottleMessage();
                else
                    Toast.makeText(BG5.this, "bg5Control == null", Toast.LENGTH_LONG).show();

                break;
            case R.id.btn_getOfflineData:
                if (bg5Control != null)
                    bg5Control.getOfflineData();
                else
                    Toast.makeText(BG5.this, "bg5Control == null", Toast.LENGTH_LONG).show();

                break;
            case R.id.btn_deleteOfflineData:
                if (bg5Control != null)
                    bg5Control.deleteOfflineData();
                else
                    Toast.makeText(BG5.this, "bg5Control == null", Toast.LENGTH_LONG).show();

                break;
            case R.id.btn_sendQRCode:
                if (bg5Control != null) {
                    String QRInfo = bg5Control.getBottleInfoFromQR(QRCode);
                    Log.i(TAG, "QRinfo =" + QRInfo);
                    bg5Control.setBottleMessageWithInfo(1, 1, QRCode, 20, "2017-02-14");
                } else
                    Toast.makeText(BG5.this, "bg5Control == null", Toast.LENGTH_LONG).show();

                break;
            case R.id.btn_setBottleMessage:
                if (bg5Control != null) {
                    bg5Control.setBottleMessageWithInfo(2, 1, "", 20, "2017-02-14");
                } else
                    Toast.makeText(BG5.this, "bg5Control == null", Toast.LENGTH_LONG).show();

                break;
            case R.id.btn_startMeasure:
                if (bg5Control != null)
                    bg5Control.startMeasure(1);
                else
                    Toast.makeText(BG5.this, "bg5Control == null", Toast.LENGTH_LONG).show();

                break;
            case R.id.btn_holdLink:
                if (bg5Control != null)
                    bg5Control.holdLink();
                else
                    Toast.makeText(BG5.this, "bg5Control == null", Toast.LENGTH_LONG).show();

                break;
            case R.id.btn_disconnect:

                if (bg5Control != null) {
                    closeTimer();
                    bg5Control.disconnect();
                } else
                    Toast.makeText(BG5.this, "bg5Control == null", Toast.LENGTH_LONG).show();

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

            Message msg = new Message();
            msg.what = HANDLER_MESSAGE;

            switch (action) {
                case Bg5Profile.ACTION_BATTERY_BG:
                case Bg5Profile.ACTION_SET_TIME:
                case Bg5Profile.ACTION_SET_UNIT:
                case Bg5Profile.ACTION_ERROR_BG:
                case Bg5Profile.ACTION_GET_BOTTLEID:
                case Bg5Profile.ACTION_GET_CODEINFO:
                case Bg5Profile.ACTION_HISTORICAL_DATA_BG:
                case Bg5Profile.ACTION_DELETE_HISTORICAL_DATA:
                case Bg5Profile.ACTION_SET_BOTTLE_MESSAGE_SUCCESS:
                case Bg5Profile.ACTION_START_MEASURE:
                case Bg5Profile.ACTION_ONLINE_RESULT_BG:
                case Bg5Profile.ACTION_KEEP_LINK:
                    msg.obj = message;
                    break;
                case Bg5Profile.ACTION_STRIP_IN:
                    msg.obj = "strip in";
                    break;
                case Bg5Profile.ACTION_GET_BLOOD:
                    msg.obj = "get blood";
                    break;
                case Bg5Profile.ACTION_STRIP_OUT:
                    msg.obj = "strip out";
                    break;
            }
            myHandler.sendMessage(msg);

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
