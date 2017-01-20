package com.ihealth.sdk;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ihealth.communication.base.wifi.iHealthDeviceBPM1Callback;
import com.ihealth.communication.control.BpProfile;
import com.ihealth.communication.control.Bpm1Control;
import com.ihealth.communication.manager.iHealthDevicesManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BPM1 extends AppCompatActivity implements iHealthDeviceBPM1Callback {
    private static final String TAG = "BPM1";

    private Button btnConnectDevice;
    private Bpm1Control mBpm1Control;
    private TextView tvMessage;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    tvMessage.setText((String) msg.obj);
                    break;
                case 2:
                    adapter.changeList(mArray);
                    break;
            }
        }
    };
    private Button btnIDPS;
    private Button btnList;
    private ListView lvList;
    private JSONArray mArray;
    private RouterListAdapter adapter;
    private String url = "http://www.baidu.com";
    private String pid = "0123456789876543210";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bpm1);

        initData();
        setupView();
        addListeners();
    }

    private void initData() {
        mBpm1Control = new Bpm1Control(this, iHealthDevicesManager.TYPE_BPM1, this);
    }

    private void setupView() {
        btnConnectDevice = (Button) findViewById(R.id.btn_bpm1_connect_device);
        btnIDPS = (Button) findViewById(R.id.btn_bpm1_get_idps);
        btnList = (Button) findViewById(R.id.btn_bpm1_get_wifi_list);
        tvMessage = (TextView) findViewById(R.id.tv_bpm1_message);

        lvList = (ListView) findViewById(R.id.lv_bpm1_wifi_list);
        mArray = new JSONArray();
        adapter = new RouterListAdapter(this, mArray);
        lvList.setAdapter(adapter);
    }

    private void addListeners() {
        btnConnectDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBpm1Control.connectDevice("iHealth-BPM1", 10000);
            }
        });

        btnIDPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBpm1Control.getIDPS();
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBpm1Control.getRouters();
            }
        });

        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    JSONObject object = mArray.getJSONObject(position);
                    object.put(BpProfile.ROUTER_URL_BPM1, url);
                    object.put(BpProfile.ROUTER_PID_BPM1, pid);
                    //判断WiFi加密方式
                    if ("OPEN".equals(object.getString(BpProfile.ROUTER_SECURITY_BPM1))) {
                        sendRouter(object);
                    } else {
                        //弹出对话框输入密码
                        showInputDialog(object);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void showInputDialog(final JSONObject object) {
        final EditText etPwd = new EditText(this);
        etPwd.setFocusable(true);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请输入密码").setIcon(
                R.drawable.signal_secure).setView(etPwd).setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                try {
                    String pwd = etPwd.getText().toString();
                    object.put(BpProfile.ROUTER_PWD_BPM1, pwd);
                    sendRouter(object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        builder.show();
    }

    private void sendRouter(JSONObject object) {
        mBpm1Control.sendRouter(object);
    }

    @Override
    public void onNewDataNotify(String type, String action, String message) {
        Log.i(TAG, "type:" + type + "--action:" + action + "--mesage:" + message);
        Message msg = new Message();
        msg.what = 1;
        switch (action) {
            case BpProfile.ACTION_STATE_BPM1:
                try {
                    JSONObject object = new JSONObject(message);
                    int state = object.getInt(BpProfile.STATE_NUMBER_BPM1);
                    if (state == 5) {
                        mBpm1Control.connectConfiguration("TP_LINK_3019");
                    }
                    msg.obj = message;
                    handler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case BpProfile.ACTION_IDPS_BPM1:
                msg.obj = message;
                handler.sendMessage(msg);
                break;
            case BpProfile.ACTION_ROUTERS_BPM1:
                try {
                    mArray = new JSONArray(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                msg.what = 2;
                handler.sendMessage(msg);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBpm1Control.disconnect();
    }

    public class RouterListAdapter extends BaseAdapter {

        private final Context mContext;
        private JSONArray mArray;

        RouterListAdapter(Context context, JSONArray array) {
            this.mContext = context;
            setList(array);
        }

        private void setList(JSONArray array) {
            if (array != null)
                this.mArray = array;
            else
                this.mArray = new JSONArray();
        }

        void changeList(JSONArray array) {
            setList(array);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mArray.length();
        }

        @Override
        public JSONObject getItem(int i) {
            try {
                return mArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view == null) {
                view = View.inflate(mContext, R.layout.item_router_list, null);
                holder = new ViewHolder();
                holder.tvSSID = (TextView) view.findViewById(R.id.tv_item_router_list_ssid);
                holder.tvSecurity = (TextView) view.findViewById(R.id.tv_item_router_list_security);
                holder.ivRSSI = (ImageView) view.findViewById(R.id.iv_item_router_list_rssi);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            try {
                JSONObject object = mArray.getJSONObject(i);
                holder.tvSSID.setText(object.getString(BpProfile.ROUTER_SSID_BPM1));
                holder.tvSecurity.setText(object.getString(BpProfile.ROUTER_SECURITY_BPM1));
                if ("OPEN".equals(object.getString(BpProfile.ROUTER_SECURITY_BPM1))) {
                    holder.ivRSSI.setImageResource(R.drawable.signal_normal);
                } else {
                    holder.ivRSSI.setImageResource(R.drawable.signal_secure);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return view;
        }

        private class ViewHolder {
            private TextView tvSSID;
            private TextView tvSecurity;
            private ImageView ivRSSI;
        }
    }
}
