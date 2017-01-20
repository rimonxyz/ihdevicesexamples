package com.ihealth.sdk;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ihealth.communication.control.BPControl;
import com.ihealth.communication.control.BpProfile;
import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jing on 16/6/24.
 */
public class ContinuaBP extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "ContinuaBP";
    private BPControl bpControl;
    private String deviceMac;

    private int clientCallbackId;
    private TextView tv_return;
    private Button btn_getBattery;
    private Button btn_setUser1;
    private Button btn_setUser2;

    private Button btn_getData;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continuabp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        deviceMac = intent.getStringExtra("mac");

        bpControl = iHealthDevicesManager.getInstance().getBPControl(deviceMac);
        clientCallbackId = iHealthDevicesManager.getInstance().registerClientCallback(callback);

        tv_return = (TextView) findViewById(R.id.tv_offlinedata);
        btn_getBattery = (Button) findViewById(R.id.btn_getbattery);
        btn_getBattery.setOnClickListener(this);
        btn_setUser1 = (Button) findViewById(R.id.btn_setUser1);
        btn_setUser1.setOnClickListener(this);
        btn_setUser2 = (Button) findViewById(R.id.btn_setUser2);
        btn_setUser2.setOnClickListener(this);
        btn_getData = (Button) findViewById(R.id.btn_getData);
        btn_getData.setOnClickListener(this);

    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.btn_getbattery:
                if(bpControl != null)
                    bpControl.getBattery();
                else
                    Toast.makeText(this, "bpControl == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_setUser1:
                if(bpControl != null)
                    bpControl.commandSetUser(1);
                else
                    Toast.makeText(this, "bpControl == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_setUser2:
                if(bpControl != null)
                    bpControl.commandSetUser(2);
                else
                    Toast.makeText(this, "bpControl == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_getData:
                if(bpControl != null)
                    bpControl.getData();
                else
                    Toast.makeText(this, "bpControl == null", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

    iHealthDevicesCallback callback = new iHealthDevicesCallback() {
        @Override
        public void onDeviceConnectionStateChange(String mac, String deviceType, int status, int errorID) {
            super.onDeviceConnectionStateChange(mac, deviceType, status, errorID);

        }

        @Override
        public void onDeviceNotify(String mac, String deviceType, String action, String message) {
            super.onDeviceNotify(mac, deviceType, action, message);
            tv_return.setText(message);
            if (action.equals(BpProfile.ACTION_HISTORY_DATA_CBP)) {
                tv_return.setText(message);
            } else if(action.equals(BpProfile.ACTION_BATTERY_CBP)) {
                try {
                    JSONObject info = new JSONObject(message);
                    int battery = info.getInt(BpProfile.BATTERY_CBP);
                    Toast.makeText(ContinuaBP.this,"Battery:" + battery,Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

}
