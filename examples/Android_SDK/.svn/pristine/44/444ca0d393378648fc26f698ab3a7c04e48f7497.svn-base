package com.ihealth.sdk;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ihealth.communication.control.Am3sControl;
import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;

public class AM3S extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "AM3S";

    private Am3sControl am3sControl;
    private String mac;
    private int clientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_am3_s);
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

        clientId = iHealthDevicesManager.getInstance().registerClientCallback(miHealthDevicesCallback);

        iHealthDevicesManager.getInstance().addCallbackFilterForDeviceType(clientId,
                iHealthDevicesManager.TYPE_AM3S);

        Intent intent = getIntent();
        this.mac = intent.getStringExtra("mac");

        am3sControl = iHealthDevicesManager.getInstance().getAm3sControl(this.mac);
        //button
        findViewById(R.id.getbattery).setOnClickListener(this);
        findViewById(R.id.btn_SetUserMessage).setOnClickListener(this);
        findViewById(R.id.getuserid).setOnClickListener(this);
        findViewById(R.id.getalarmnum).setOnClickListener(this);
        findViewById(R.id.syncstage).setOnClickListener(this);
        findViewById(R.id.syncactivity).setOnClickListener(this);
        findViewById(R.id.syncreal).setOnClickListener(this);
        findViewById(R.id.getuserinfo).setOnClickListener(this);
        findViewById(R.id.setuserid).setOnClickListener(this);
        findViewById(R.id.sendrandom).setOnClickListener(this);
        findViewById(R.id.btn_disconnect).setOnClickListener(this);
        findViewById(R.id.btn_getpicture).setOnClickListener(this);
        findViewById(R.id.btn_setpicture).setOnClickListener(this);
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
            Log.i(TAG, "Action-----> " + action);
            if (message != null) {
                Log.i(TAG, "message-----> "+message);
            }
        }
    };

    @Override
    public void onClick(View arg0) {
        int id = arg0.getId();
        switch (id) {
            case R.id.btn_getpicture:
                if (am3sControl != null)
                    am3sControl.getPicture();
                else
                    Toast.makeText(AM3S.this, "am4Control == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_setpicture:
                if (am3sControl != null) {
                    TextView textView = (TextView) findViewById(R.id.set_picture_index);
                    int index = Integer.parseInt(textView.getText().toString());
                    am3sControl.setPicture(index);
                } else
                    Toast.makeText(AM3S.this, "am4Control == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_disconnect:
                if (am3sControl != null)
                    am3sControl.disconnect();
                else
                    Toast.makeText(AM3S.this, "am4Control == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.getbattery:
                if (am3sControl != null) {
                    am3sControl.queryAMState();
                }else
                    Toast.makeText(this, "am3sControl == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_SetUserMessage:
                if (am3sControl != null) {
                    am3sControl.setUserInfo(29, 177, 65, 1, 1, 10000, 1);
                }else
                    Toast.makeText(this, "am3sControl == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.getuserid:
                if (am3sControl != null) {
                    am3sControl.getUserId();
                }else
                    Toast.makeText(this, "am3sControl == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.getalarmnum:
                if (am3sControl != null) {
                    am3sControl.getAlarmClockNum();
                }else
                    Toast.makeText(this, "am3sControl == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.syncstage:
                if (am3sControl != null) {
                    am3sControl.syncStageReprotData();
                }else
                    Toast.makeText(this, "am3sControl == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.syncactivity:
                if (am3sControl != null) {
                    am3sControl.syncActivityData();
                }else
                    Toast.makeText(this, "am3sControl == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.syncreal:
                if (am3sControl != null) {
                    am3sControl.syncRealData();
                }else
                    Toast.makeText(this, "am3sControl == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.getuserinfo:
                if (am3sControl != null) {
                    am3sControl.getUserInfo();
                }else
                    Toast.makeText(this, "am3sControl == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.setuserid:
                if (am3sControl != null) {
                    am3sControl.setUserId(1);
                }else
                    Toast.makeText(this, "am3sControl == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.sendrandom:
                if (am3sControl != null) {
                    am3sControl.sendRandom();
                }else
                    Toast.makeText(this, "am3sControl == null", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

}
