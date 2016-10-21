package com.ihealth.sdk;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ihealth.communication.control.Bg1Control;
import com.ihealth.communication.control.Bg1Profile;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BG1 extends AppCompatActivity {

    private static final String TAG = "BG1";
    private TextView mTextView;
    public Bg1Control mBg1Control;

    private boolean isGetStripInBg1 = false;
    private boolean isGetResultBg1 = false;
    private boolean isGetBloodBg1 = false;

    public String QRCode = "02554064554014322D1200A05542D3BACE1446CE9A961901222F00A70B46";
//    public String QRCode = "02ABCDE67C284BA29ACDFEE6E60A2FE43EDF0C";

    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS--");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bg1);
        mTextView = (TextView) findViewById(R.id.tv_startBG1);
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

        registerBroadcast();
        Intent intent = getIntent();
        String userName = intent.getExtras().getString("userName");

        mBg1Control = Bg1Control.getInstance();
        mBg1Control.init(BG1.this, userName, 0);

    }

    private void registerBroadcast() {
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

            //1305
            else if (action.equals(Bg1Profile.ACTION_BG1_DEVICE_READY)) {
                mTextView.append(SDF.format(new Date()) + "device handshake\n");
            } else if (action.equals(Bg1Profile.ACTION_BG1_IDPS)) {
                String idps = intent.getStringExtra(Bg1Profile.BG1_IDPS);
                mTextView.append(SDF.format(new Date()) + "deviceInfo = " + idps + "\n");
            } else if (action.equals(Bg1Profile.ACTION_BG1_CONNECT_RESULT)) {
                int flag = intent.getIntExtra(Bg1Profile.BG1_CONNECT_RESULT, -1);
                mTextView.append(SDF.format(new Date()) + "conect flag = " + flag + "\n");
                if (flag == 0) {
                    mTextView.append(SDF.format(new Date()) + "connect success,please send code\n");
                    mBg1Control.sendCode(QRCode);
                } else {
                    mTextView.append(SDF.format(new Date()) + "connect failed\n");
                    mBg1Control.disconnect();
                }
            } else if (action.equals(Bg1Profile.ACTION_BG1_SENDCODE_RESULT)) {
                int flag = intent.getIntExtra(Bg1Profile.BG1_SENDCODE_RESULT, -1);
                mTextView.append(SDF.format(new Date()) + "sendCode flag = " + flag + "\n");
                if (flag == 0) {
                    mTextView.append(SDF.format(new Date()) + "sendCode success,ready to  measure\n");
                } else {
                    mTextView.append(SDF.format(new Date()) + "sendCode failed\n");
                    mBg1Control.disconnect();
                }
            } else if (action.equals(Bg1Profile.ACTION_BG1_MEASURE_ERROR)) {
                int errorNum = intent.getIntExtra(Bg1Profile.BG1_MEASURE_ERROR, -1);
                Log.e(TAG, "---------------------msgError = " + errorNum + "------------------------------");

                mTextView.append(SDF.format(new Date()) + "msgError = " + errorNum + "\n");

                //resend code to fix error 4
                if (errorNum == 4) {
                    mBg1Control.sendCode(QRCode);
                }
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
