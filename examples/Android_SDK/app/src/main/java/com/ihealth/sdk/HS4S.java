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

import com.ihealth.communication.control.Hs4sControl;
import com.ihealth.communication.control.HsProfile;
import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class HS4S extends AppCompatActivity implements View.OnClickListener{

    private static String TAG = "HS4S";

    private TextView tv_return;
    private String deviceMac;
    private int clientId;
    private Hs4sControl mHs4sControl;
    private int usrId = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hs4_s);
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
                iHealthDevicesManager.TYPE_HS4S);
        /* Get hs4s controller */
        mHs4sControl = iHealthDevicesManager.getInstance().getHs4sControl(deviceMac);
    }

    private void initView() {
        tv_return = (TextView) findViewById(R.id.tv_return);
        findViewById(R.id.btn_getOfflineData).setOnClickListener(this);
        findViewById(R.id.btn_disconnect).setOnClickListener(this);
        findViewById(R.id.btn_startMeasure).setOnClickListener(this);
    }

    iHealthDevicesCallback mIHealthDevicesCallback = new iHealthDevicesCallback() {

        public void onDeviceConnectionStateChange(String mac, String deviceType, int status,int errorID) {
            Log.e(TAG, "mac:" + mac + "-deviceType:" + deviceType + "-status:" + status);

            switch (status) {
                case iHealthDevicesManager.DEVICE_STATE_DISCONNECTED:
                    mHs4sControl = null;
                    noticeString = "The device disconnect";
                    Toast.makeText(HS4S.this, "The device disconnect", Toast.LENGTH_LONG).show();
                    tv_return.setText(noticeString);
                    break;

                default:
                    break;
            }
        };

        public void onDeviceNotify(String mac, String deviceType, String action, String message) {
            Log.d(TAG, "mac:" + mac + "--type:" + deviceType + "--action:" + action + "--message:" + message);
            JSONTokener jsonTokener = new JSONTokener(message);
            switch (action) {
                case HsProfile.ACTION_HISTORICAL_DATA_HS:
                    try {
                        JSONObject object = (JSONObject) jsonTokener.nextValue();
                        JSONArray jsonArray = object.getJSONArray(HsProfile.HISTORDATA__HS);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                            String dateString = jsonObject.getString(HsProfile.MEASUREMENT_DATE_HS);
                            String dataId=jsonObject.getString(HsProfile.DATAID);
                            float weight = (float) jsonObject.getDouble(HsProfile.WEIGHT_HS);
                            Log.d(TAG, "dataId:"+dataId+"--date:" + dateString + "-weight:" + weight);
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
                case HsProfile.ACTION_LIVEDATA_HS:
                    try {
                        JSONObject jsonObject = (JSONObject) jsonTokener.nextValue();
                        float weight = (float) jsonObject.getDouble(HsProfile.LIVEDATA_HS);
                        Log.d(TAG, "weight:" + weight);
                        noticeString = "weight:" + weight;
                        Message message3 = new Message();
                        message3.what = 1;
                        message3.obj = noticeString;
                        mHandler.sendMessage(message3);
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case HsProfile.ACTION_ONLINE_RESULT_HS:
                    try {
                        JSONObject jsonObject = (JSONObject) jsonTokener.nextValue();
                        float weight = (float) jsonObject.getDouble(HsProfile.WEIGHT_HS);
                        String dataId=jsonObject.getString(HsProfile.DATAID);

                        Log.d(TAG,"dataId:"+dataId+ "---weight:" + weight);
                        noticeString = "dataId:"+dataId+ "---weight:" + weight;

                        Message message3 = new Message();
                        message3.what = 1;
                        message3.obj = noticeString;
                        mHandler.sendMessage(message3);
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case HsProfile.ACTION_NO_HISTORICALDATA:
                    noticeString = "no history data";
                    Message message2 = new Message();
                    message2.what = 1;
                    message2.obj = message;
                    mHandler.sendMessage(message2);
                    break;
                case HsProfile.ACTION_ERROR_HS:

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
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        iHealthDevicesManager.getInstance().unRegisterClientCallback(clientId);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btn_getOfflineData:
                if (mHs4sControl == null) {
                    Toast.makeText(HS4S.this, "mHs4Control == null", Toast.LENGTH_LONG).show();

                } else {
                    mHs4sControl.getOfflineData();
                }
                break;
            case R.id.btn_startMeasure:
                if (mHs4sControl == null) {
                    Toast.makeText(HS4S.this, "mHs4Control == null", Toast.LENGTH_LONG).show();

                } else {
                    mHs4sControl.measureOnline(1, usrId);
                }
                break;
            case R.id.btn_disconnect:
                if (mHs4sControl == null) {
                    Toast.makeText(HS4S.this, "mHs4Control == null", Toast.LENGTH_LONG).show();

                } else {
                    mHs4sControl.disconnect();
                }
                break;
            default:
                break;

        }
    }

}
