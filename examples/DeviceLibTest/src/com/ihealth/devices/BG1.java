package com.ihealth.devices;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;

import com.example.devicelibtest.R;
import com.ihealth.communication.control.Bg1Control;
import com.ihealth.communication.control.Bg1Profile;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BG1 extends Activity {

    private static final String TAG = "BG1";
    private TextView mTextView;
    public Bg1Control mBg1Control;

    private boolean isGetStripInBg1 = false;
    private boolean isGetResultBg1 = false;
    private boolean isGetBloodBg1 = false;

    public String QRCode = "02323C641E3114322D0800A064646464646464646464FA012261000E1CCC";

    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss--");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bg1);
        mTextView = (TextView) findViewById(R.id.startBG1);

        registerBroadcast();
        Intent intent = getIntent();
        String userName = intent.getExtras().getString("userName");

        mBg1Control = Bg1Control.getInstance();
        mBg1Control.init(BG1.this, userName,0);
    }

    private void registerBroadcast(){
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_HEADSET_PLUG);

        filter.addAction(Bg1Profile.ACTION_BG1_DEVICE_READY);
        filter.addAction(Bg1Profile.ACTION_BG1_IDPS);
        filter.addAction(Bg1Profile.ACTION_BG1_CONNECT_RESULT);
        filter.addAction(Bg1Profile.ACTION_BG1_SENDCODE_RESULT);

        filter.addAction(Bg1Profile.ACTION_BG1_MEASURE_ERROR);
        filter.addAction(Bg1Profile.ACTION_BG1_MEASURE_STRIP_IN);
        filter.addAction(Bg1Profile.ACTION_BG1_MEASURE_STRIP_OUT);
        filter.addAction(Bg1Profile.ACTION_BG1_MEASURE_GET_BLOOD);
        filter.addAction(Bg1Profile.ACTION_BG1_MEASURE_RESULT);
        filter.addAction(Bg1Profile.ACTION_BG1_MEASURE_STANDBY);
        this.registerReceiver(mBroadcastReceiver, filter);
    }

    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_HEADSET_PLUG)) {

                if (intent.hasExtra("state")) {
                    if (intent.getIntExtra("state", 0) == 0) {
                        Log.e(TAG, "headset out");

                        mBg1Control.disconnect();
                    }
                    if (intent.getIntExtra("state", 0) == 1) {
                        Log.e(TAG, "headset in\n");

                        mTextView.setText(SDF.format(new Date()) + "headset in\n");

                        String QRInfo = mBg1Control.getBottleInfoFromQR(QRCode);
                        Log.e(TAG, "QRInfo =" + QRInfo);

                        mBg1Control.connect();
                    }
                }
            }

            else if (action.equals(Bg1Profile.ACTION_BG1_DEVICE_READY)) {
                mTextView.append(SDF.format(new Date()) + "device handshake\n");
            } else if (action.equals(Bg1Profile.ACTION_BG1_IDPS)) {
                String idps = intent.getStringExtra(Bg1Profile.BG1_IDPS);
                mTextView.append(SDF.format(new Date()) + "deviceInfo = " + idps + "\n");
            }
            else if (action.equals(Bg1Profile.ACTION_BG1_CONNECT_RESULT)) {
                int flag = intent.getIntExtra(Bg1Profile.BG1_CONNECT_RESULT, -1);
                mTextView.append(SDF.format(new Date()) + "conect flag = " + flag + "\n");
                if(flag == 0) {
                    mTextView.append(SDF.format(new Date()) + "connect success,please send code\n");
                    mBg1Control.sendCode(QRCode);
                } else {
                    mTextView.append(SDF.format(new Date()) + "connect failed\n");
                    mBg1Control.disconnect();
                }
            }
            else if (action.equals(Bg1Profile.ACTION_BG1_SENDCODE_RESULT)) {
                int flag = intent.getIntExtra(Bg1Profile.BG1_SENDCODE_RESULT, -1);
                mTextView.append(SDF.format(new Date()) + "sendCode flag = " + flag + "\n");
                if(flag == 0) {
                    mTextView.append(SDF.format(new Date()) + "sendCode success,ready to  measure\n");
                } else {
                    mTextView.append(SDF.format(new Date()) + "sendCode failed\n");
                    mBg1Control.disconnect();
                }
            }

            else if (action.equals(Bg1Profile.ACTION_BG1_MEASURE_ERROR)) {
                int errorNum = intent.getIntExtra(Bg1Profile.BG1_MEASURE_ERROR, -1);
                Log.e(TAG, "---------------------msgError = " + errorNum + "------------------------------");

                mTextView.append(SDF.format(new Date()) + "msgError = " + errorNum + "\n");

            } else if (action.equals(Bg1Profile.ACTION_BG1_MEASURE_STRIP_IN)) {
                if (!isGetStripInBg1) {
                    isGetStripInBg1 = true;
                    Log.e(TAG, "---------------------msgStripIn------------------------------");

                    mTextView.append(SDF.format(new Date()) + "Strip In\n");
                }
                new Thread() {
                    @Override
                    public void run() {
                        SystemClock.sleep(3000);
                        isGetStripInBg1 = false;
                    }
                }.start();
            } else if (action.equals(Bg1Profile.ACTION_BG1_MEASURE_GET_BLOOD)) {
                if (!isGetBloodBg1) {
                    isGetBloodBg1 = true;
                    Log.e(TAG, "---------------------msgGetBlood------------------------------");

                    mTextView.append(SDF.format(new Date()) + "Get Blood\n");
                }
                new Thread() {
                    @Override
                    public void run() {
                        SystemClock.sleep(3000);
                        isGetBloodBg1 = false;
                    }
                }.start();
            } else if (action.equals(Bg1Profile.ACTION_BG1_MEASURE_RESULT)) {
                if (!isGetResultBg1) {
                    isGetResultBg1 = true;
                    int measureResult = intent.getIntExtra(Bg1Profile.BG1_MEASURE_RESULT, -1);
                    String dataId = intent.getStringExtra(Bg1Profile.DATA_ID);
                    Log.e(TAG, "---------------------msgResult ＝ " + measureResult + "------------------------------");
                    Log.e(TAG, "---------------------dataId ＝ " + dataId + "------------------------------");

                    mTextView.append(SDF.format(new Date()) + "msgResult ＝ " + measureResult + "\n");
                }
                new Thread() {
                    @Override
                    public void run() {
                        SystemClock.sleep(3000);
                        isGetResultBg1 = false;
                    }
                }.start();

            } else if (action.equals(Bg1Profile.ACTION_BG1_MEASURE_STRIP_OUT)) {
                Log.e(TAG, "---------------------msgStripOut------------------------------");
                mTextView.append(SDF.format(new Date()) + "Strip Out\n");
            } else if (action.equals(Bg1Profile.ACTION_BG1_MEASURE_STANDBY)) {

                if (!isGetResultBg1) {
                    isGetResultBg1 = true;
                    mTextView.append(SDF.format(new Date()) + "Stand By\n");
                }
                new Thread() {
                    @Override
                    public void run() {
                        SystemClock.sleep(3000);
                        isGetResultBg1 = false;
                    }
                }.start();
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mBg1Control.disconnect();
        unregisterReceiver(mBroadcastReceiver);
        super.onDestroy();
    }

}
