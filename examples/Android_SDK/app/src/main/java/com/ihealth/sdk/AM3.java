package com.ihealth.sdk;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ihealth.communication.control.Am3Control;
import com.ihealth.communication.control.AmProfile;
import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.manager.iHealthDevicesUpgradeManager;

public class AM3 extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "AM3";
    private Am3Control am3Control;
    private String mac;
    private int clientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_am3);
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
                iHealthDevicesManager.TYPE_AM3);

        Intent intent = getIntent();
        this.mac = intent.getStringExtra("mac");
        am3Control = iHealthDevicesManager.getInstance().getAm3Control(this.mac);
        //button
        findViewById(R.id.getbattery).setOnClickListener(this);
        findViewById(R.id.getuserid).setOnClickListener(this);
        findViewById(R.id.getalarmnum).setOnClickListener(this);
        findViewById(R.id.setalarm).setOnClickListener(this);
        findViewById(R.id.syncactivity).setOnClickListener(this);
        findViewById(R.id.syncreal).setOnClickListener(this);
        findViewById(R.id.getuserinfo).setOnClickListener(this);
        findViewById(R.id.setuserinfo).setOnClickListener(this);
        findViewById(R.id.getalarminfo).setOnClickListener(this);
        findViewById(R.id.setuserid).setOnClickListener(this);
        findViewById(R.id.reset).setOnClickListener(this);
        findViewById(R.id.btn_askDeviceInfo).setOnClickListener(this);
        findViewById(R.id.btn_startUpgrade).setOnClickListener(this);
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
            case R.id.getbattery:
                if (am3Control != null) {
                    am3Control.queryAMState();
                }else
                    Toast.makeText(this, "am3Control == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.getuserid:
                if (am3Control != null) {
                    am3Control.getUserId();
                }else
                    Toast.makeText(this, "am3Control == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.getalarmnum:
                if (am3Control != null) {
                    am3Control.getAlarmClockNum();
                }else
                    Toast.makeText(this, "am3Control == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.setalarm:
                if (am3Control != null) {
                    am3Control.setAlarmClock(1, 10, 20, AmProfile.AM_SWITCH_REPEAT, new int[]{1,1,1,1,1,1,1}, AmProfile.AM_SWITCH_OPEN);
                }else
                    Toast.makeText(this, "am3Control == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.syncactivity:
                if (am3Control != null) {
                    am3Control.syncActivityData();
                }else
                    Toast.makeText(this, "am3Control == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.syncreal:
                if (am3Control != null) {
                    am3Control.syncRealData();
                }else
                    Toast.makeText(this, "am3Control == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.getuserinfo:
                if (am3Control != null) {
                    am3Control.getUserInfo();
                }else
                    Toast.makeText(this, "am3Control == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.setuserinfo:
                if (am3Control != null) {
                    am3Control.setUserInfo(20, 180, (float) 60.5, AmProfile.AM_SET_MALE, AmProfile.AM_SET_UNIT_METRIC, 200, 0);
                }else
                    Toast.makeText(this, "am3Control == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.getalarminfo:
                if (am3Control != null) {
                    am3Control.getActivityRemind();
                }else
                    Toast.makeText(this, "am3Control == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.setuserid:
                if (am3Control != null) {
                    am3Control.setUserId(1);
                }else
                    Toast.makeText(this, "am3Control == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.reset:
                if (am3Control != null) {
                    am3Control.reset(1);
                }else
                    Toast.makeText(this, "am3Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_askDeviceInfo:
                if (am3Control != null)
                    iHealthDevicesUpgradeManager.getInstance().queryUpgradeInfoFromDeviceAndCloud(mac, iHealthDevicesManager.TYPE_AM3);
                else
                    Toast.makeText(AM3.this, "am4Control == null", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_startUpgrade:
                if (am3Control != null)
                    iHealthDevicesUpgradeManager.getInstance().startUpGrade(mac, iHealthDevicesManager.TYPE_AM3);
                else
                    Toast.makeText(AM3.this, "am4Control == null", Toast.LENGTH_LONG).show();
                break;

            default:
                break;
        }
    }

}
