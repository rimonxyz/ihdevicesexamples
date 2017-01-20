package com.ihealth.sdk;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.ihealth.communication.control.AmProfile;
import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.manager.iHealthDevicesUpgradeManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;

/**
 * Created by Jeepend on 4/11/2016.
 */

public class AMActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AMActivity";
    private String mMacAddress = null;
    private Object mControl = null;
    private int clientId = -1;

    private enum AMType {
        AM3(iHealthDevicesManager.TYPE_AM3),
        AM3S(iHealthDevicesManager.TYPE_AM3S),
        AM4(iHealthDevicesManager.TYPE_AM4);
        String typeName;

        AMType(String typeName) {
            this.typeName = typeName;
        }
    }

    private AMType mType = AMType.AM3;

    private TextView mResultTextVie = null;
    private View mDetailInfoLayout = null;
    private TextView mDetailInfoTextView = null;

    private iHealthDevicesCallback miHealthDevicesCallback = new iHealthDevicesCallback() {

        @Override
        public void onDeviceConnectionStateChange(String mac, String deviceType, int status, int errorID) {
            Log.d(TAG, "onDeviceConnectionStateChange() deviceType = " + deviceType + " status = " + status + " errorID = " + errorID);
            Toast.makeText(AMActivity.this, "Disconnect successfully, exit...", Toast.LENGTH_SHORT).show();
            finish();
        }

        @Override
        public void onDeviceNotify(String mac, String deviceType, String action, String message) {
            Log.i(TAG, "Action-----> " + action);
            if (message != null) {
                Log.i(TAG, "message-----> " + message);
            }
            mResultTextVie.setText(String.format("Action: %s", action));
            String info = String.format("Action : %s\nMessage : %s", action, message);
            mDetailInfoTextView.setText(info);
        }
    };

    iHealthDevicesManager manager = iHealthDevicesManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_am_common);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mMacAddress = getIntent().getStringExtra("mac");
        int type = getIntent().getIntExtra("am_type", 0);
        switch (type) {
            case 0:
                mType = AMType.AM3;
                mControl = manager.getAm3Control(mMacAddress);
                break;
            case 1:
                mType = AMType.AM3S;
                mControl = manager.getAm3sControl(mMacAddress);
                break;
            case 2:
                mType = AMType.AM4;
                mControl = manager.getAm4Control(mMacAddress);
                break;
        }

        clientId = manager.registerClientCallback(miHealthDevicesCallback);

        iHealthDevicesManager.getInstance().addCallbackFilterForDeviceType(clientId, mType.typeName);
        toolbar.setTitle(mType.typeName);
        setTitle(mType.typeName);

        findViewById(R.id.btn_SendRandomNumber).setOnClickListener(this);
        findViewById(R.id.btn_SetUserMessage).setOnClickListener(this);
        findViewById(R.id.btn_GetUserMessage).setOnClickListener(this);
        findViewById(R.id.btn_SetUserID).setOnClickListener(this);
        findViewById(R.id.btn_GetUserID).setOnClickListener(this);
        findViewById(R.id.btn_Reset).setOnClickListener(this);
        findViewById(R.id.btn_checkSwimPara).setOnClickListener(this);
        findViewById(R.id.btn_SetSwimPara).setOnClickListener(this);
        findViewById(R.id.btn_SetRemind).setOnClickListener(this);
        findViewById(R.id.btn_GetRemind).setOnClickListener(this);
        findViewById(R.id.btn_SetClock).setOnClickListener(this);
        findViewById(R.id.btn_GetClock).setOnClickListener(this);
        findViewById(R.id.btn_GetClockNum).setOnClickListener(this);
        findViewById(R.id.btn_GetBattery).setOnClickListener(this);
        findViewById(R.id.btn_SetRealTime).setOnClickListener(this);
        findViewById(R.id.btn_SetTimeMode).setOnClickListener(this);
        findViewById(R.id.btn_SyncStage).setOnClickListener(this);
        findViewById(R.id.btn_SyncSleep).setOnClickListener(this);
        findViewById(R.id.btn_SyncSport).setOnClickListener(this);
        findViewById(R.id.btn_SyncReal).setOnClickListener(this);
        findViewById(R.id.btn_askDeviceInfo).setOnClickListener(this);
        findViewById(R.id.btn_startUpgrade).setOnClickListener(this);
        findViewById(R.id.btn_destroyUp).setOnClickListener(this);
        findViewById(R.id.btn_isUpgrade).setOnClickListener(this);
        findViewById(R.id.btn_GetTimeMode).setOnClickListener(this);
        findViewById(R.id.btn_DeleteClock).setOnClickListener(this);
        findViewById(R.id.btn_disconnect).setOnClickListener(this);
        findViewById(R.id.btn_SetBMR).setOnClickListener(this);
        findViewById(R.id.btn_setMode).setOnClickListener(this);
        findViewById(R.id.btn_getpicture).setOnClickListener(this);
        findViewById(R.id.btn_setpicture).setOnClickListener(this);
        mResultTextVie = (TextView) findViewById(R.id.tv_return);
        mDetailInfoLayout = findViewById(R.id.detail_info_layout);
        mDetailInfoTextView = (TextView) findViewById(R.id.detail_info_text);
        Switch detailSwitch = (Switch) findViewById(R.id.detail_info_switch);
        detailSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mDetailInfoLayout.setVisibility(View.VISIBLE);
                } else {
                    mDetailInfoLayout.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iHealthDevicesManager.getInstance().unRegisterClientCallback(clientId);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        TextView textView;
        switch (id) {
            case R.id.btn_getpicture:
                executeMethod("getPicture", null);
                break;
            case R.id.btn_setpicture:
                textView = (TextView) findViewById(R.id.set_picture_index);
                String indexString = textView.getText().toString().trim();
                if (!TextUtils.isEmpty(indexString)) {
                    int index = Integer.parseInt(indexString);
                    executeMethod("setPicture", new Class[]{int.class}, index);
                }
                break;
            case R.id.btn_setMode:
                textView = (TextView) findViewById(R.id.set_mode);
                String modeString = textView.getText().toString().trim();
                if (!TextUtils.isEmpty(modeString)) {
                    int mode = Integer.parseInt(modeString);
                    executeMethod("setMode", new Class[]{int.class}, mode);
                }
                break;
            case R.id.btn_SetBMR:
                textView = (TextView) findViewById(R.id.set_bmr);
                String bmrString = textView.getText().toString().trim();
                if (!TextUtils.isEmpty(bmrString)) {
                    int bmr = Integer.parseInt(bmrString);
                    executeMethod("setUserBmr", new Class[]{int.class}, bmr);
                }
                break;
            case R.id.btn_disconnect:
                executeMethod("disconnect", null);
                break;
            case R.id.btn_GetTimeMode:
                executeMethod("getHourMode", null);
                break;
            case R.id.btn_SendRandomNumber:
                executeMethod("sendRandom", null);
                break;
            case R.id.btn_SetUserMessage:
                popSetUserInformationDialog();
                break;
            case R.id.btn_GetUserMessage:
                executeMethod("getUserInfo", null);
                break;
            case R.id.btn_SetUserID:
                textView = (TextView) findViewById(R.id.set_user_id);
                String userIdString = textView.getText().toString().trim();
                if (!TextUtils.isEmpty(userIdString)) {
                    int userId = Integer.parseInt(userIdString);
                    executeMethod("setUserId", new Class[]{int.class}, userId);
                }
                break;
            case R.id.btn_GetUserID:
                executeMethod("getUserId", null);
                break;
            case R.id.btn_Reset:
                executeMethod("reset", new Class[]{int.class}, 0xFFFFFFFF);
                break;
            case R.id.btn_checkSwimPara:
                executeMethod("checkSwimPara", null);
                break;
            case R.id.btn_SetSwimPara:
                executeMethod("setSwimPara", new Class[]{boolean.class,  int.class, int.class, int.class, int.class},
                        AmProfile.AM_SWITCH_OPEN, 25, 0, 10, AmProfile.AM_SET_UNIT_IMPERIAL_STANDARD);
                break;
            case R.id.btn_SetRemind:
                executeMethod("setActivityRemind", new Class[] {int.class, int.class, boolean.class},
                        0 , 20, true);
                break;
            case R.id.btn_GetRemind:
                executeMethod("getActivityRemind", null);
                break;
            case R.id.btn_SetClock:
                popupSetAlarmDialog();
                break;
            case R.id.btn_GetClock:
                executeMethod("getAlarmClockDetail", new Class[]{int[].class},
                        new int[] {3, 3, 1, 1, 2, 1});
                break;
            case R.id.btn_DeleteClock:
                executeMethod("deleteAlarmClock", new Class[]{int.class}, 1);
                executeMethod("deleteAlarmClock", new Class[]{int.class}, 2);
                executeMethod("deleteAlarmClock", new Class[]{int.class}, 3);
                break;
            case R.id.btn_GetClockNum:
                executeMethod("getAlarmClockNum", null);
                break;
            case R.id.btn_GetBattery:
                executeMethod("queryAMState", null);
                break;

            case R.id.btn_SetRealTime:
                executeMethod("syncRealTime", null);
                break;
            case R.id.btn_SetTimeMode:
                executeMethod("setHourMode", new Class[]{int.class}, 1);
                break;
            case R.id.btn_SyncStage:
                executeMethod("syncStageReprotData", null);
                break;
            case R.id.btn_SyncSleep:
                executeMethod("syncSleepData", null);
                break;
            case R.id.btn_SyncSport:
                executeMethod("syncActivityData", null);
                break;
            case R.id.btn_SyncReal:
                executeMethod("syncRealData", null);
                break;
            case R.id.btn_askDeviceInfo:
                if (mControl != null)
                    iHealthDevicesUpgradeManager.getInstance().queryUpgradeInfoFromDeviceAndCloud(mMacAddress, mType.typeName);
                else
                    Toast.makeText(this, "mControl == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_startUpgrade:
                if (mControl != null)
                    iHealthDevicesUpgradeManager.getInstance().startUpGrade(mMacAddress, mType.typeName);
                else
                    Toast.makeText(this, "mControl == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_destroyUp:
                if (mControl != null)
                    iHealthDevicesUpgradeManager.getInstance().stopUpgrade(mMacAddress, mType.typeName);
                else
                    Toast.makeText(this, "mControl == null", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_isUpgrade:
                if (mControl != null)
                    Log.i(TAG, iHealthDevicesUpgradeManager.getInstance().isUpgradeState(mMacAddress, mType.typeName) + "");
                else
                    Toast.makeText(this, "mControl == null", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

    private void popupSetAlarmDialog() {
        Calendar calendar = Calendar.getInstance();
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                executeMethod("setAlarmClock", new Class[]{int.class, int.class, int.class, boolean.class, int[].class, boolean.class},
                        2, hourOfDay, minute, true, new int[]{1, 1, 1, 1, 0, 1, 1}, true);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), true).show();

    }

    private void popSetUserInformationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View contentView = View.inflate(this, R.layout.dialog_am4_userinfo, null);;
        builder.setView(contentView);
        builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView age = (TextView) contentView.findViewById(R.id.age);
                TextView height = (TextView) contentView.findViewById(R.id.height);
                TextView weight = (TextView) contentView.findViewById(R.id.weight);
                ToggleButton gender = (ToggleButton) contentView.findViewById(R.id.gender);
                ToggleButton unit = (ToggleButton) contentView.findViewById(R.id.unit);
                TextView target = (TextView) contentView.findViewById(R.id.target);
                TextView level = (TextView) contentView.findViewById(R.id.activity_level);
                TextView swimTarget = (TextView) contentView.findViewById(R.id.swim_target);
                int uage = Integer.parseInt(age.getText().toString().trim());
                int uheight = Integer.parseInt(height.getText().toString().trim());
                float uweight = Float.parseFloat(weight.getText().toString().trim());
                int ugender = gender.isChecked() ? 0 : 1;
                int uunit = unit.isChecked() ? 0 : 1;
                int utarget = Integer.parseInt(target.getText().toString().trim());
                int ulevel = Integer.parseInt(level.getText().toString().trim());
                int uswimTarget = Integer.parseInt(swimTarget.getText().toString().trim());
                Log.i(TAG, "setUserInfo(), age = " + uage
                        + ", height = " + uheight
                        + ", weight = " + uweight
                        + ", gender = " + ugender
                        + ", unit = " + uunit
                        + ", target = " + utarget
                        + ", level = " + ulevel
                        + ", swimTarget = " + uswimTarget);
                switch (mType) {
                    case AM3:
                    case AM3S:
                        executeMethod("setUserInfo", new Class[]{int.class, int.class, float.class, int.class, int.class, int.class, int.class},
                                uage, uheight, uweight, ugender, uunit, utarget, ulevel);
                        break;
                    case AM4:
                        executeMethod("setUserInfo", new Class[]{int.class, int.class, float.class, int.class, int.class, int.class, int.class, int.class},
                                uage, uheight, uweight, ugender, uunit, utarget, ulevel, uswimTarget);
                        break;
                }
            }
        });
        builder.show();
    }

    /**
     * Call specified method with reflection.
     * @param name Method's name
     * @param parametersForm Form of parameters, should be null or Class array
     * @param parameters parameters, should be empty or Object array
     */
    private void executeMethod(String name, Class[] parametersForm, Object... parameters) {
        if (TextUtils.isEmpty(name)) {
            Log.e(TAG, "executeMethod() name is empty");
            return;
        }
        if (parametersForm != null && parameters != null) {
            if (parametersForm.length != parameters.length) {
                Log.e(TAG, "executeMethod() parameterForm and parameters length not equal.");
                return;
            }
        }
        if (mControl != null) {
            try {
                if (parametersForm != null) {
                    Method method = mControl.getClass().getMethod(name, parametersForm);
                    method.invoke(mControl, parameters);
                } else {
                    Method method = mControl.getClass().getMethod(name);
                    method.invoke(mControl);
                }
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
                StringBuilder builder = new StringBuilder();
                if (parametersForm != null) {
                    for (Class cls : parametersForm) {
                        builder.append(cls.getSimpleName()).append(',');
                    }
                }
                String parameterString = builder.toString();
                if (!parameterString.isEmpty()) {
                    parameterString = parameterString.substring(0, parameterString.length() - 1);
                }
                Toast.makeText(this, String.format("%s not support this operation: %s(%s)", mType.typeName, name, parameterString), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "mControl == null", Toast.LENGTH_LONG).show();
        }
    }
}
