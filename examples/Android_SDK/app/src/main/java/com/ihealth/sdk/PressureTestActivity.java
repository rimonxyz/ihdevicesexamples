package com.ihealth.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.ihealth.communication.control.AmProfile;
import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.utils.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jeepend on 5/12/2016.
 * It's for pressure test.
 */

public class PressureTestActivity extends Activity {
    private static final String TAG = "PressureTestActivity";
    private String mMac = null;
    private ArrayList<Command> mSendCommandList = new ArrayList<>();
    private ArrayList<Command> mReceivedNotifyList = new ArrayList<>();

    private iHealthDevicesCallback mCallback = new iHealthDevicesCallback() {
        @Override
        public void onDeviceNotify(String mac, String deviceType, String action, String message) {
            super.onDeviceNotify(mac, deviceType, action, message);
            if (mac.equals(mMac)) {
                Command command = Command.parseCommand(action);
                if (command == Command.Unknown) {
                    return;
                }
                mReceivedNotifyList.add(command);
                int i = mReceivedNotifyList.size() - 1;
                if (i < mSendCommandList.size()) {
                    Command sendCommand = mSendCommandList.get(i);
                    Command receiveCommand = mReceivedNotifyList.get(i);
                    if (sendCommand == receiveCommand) {
                        Log.d(TAG, String.format("#%d send: %s, receive: %s", i + 1, sendCommand.toString(), receiveCommand.toString()));
                    } else {
                        Log.e(TAG, String.format("#%d send: %s, receive: %s", i + 1, sendCommand.toString(), receiveCommand.toString()));
                    }
                } else {
                    Command receiveCommand = mReceivedNotifyList.get(i);
                    Log.e(TAG, String.format("#%d send: null, receive: %s", i + 1, receiveCommand.toString()));
                }

//                if (mReceivedNotifyList.size() >= mSendCommandList.size()) {
//                    Log.d(TAG, "==============================================================");
//                    for (int i = 0; i < mReceivedNotifyList.size(); i++) {
//                        if (i < mSendCommandList.size()) {
//                            Command sendCommand = mSendCommandList.get(i);
//                            Command receiveCommand = mReceivedNotifyList.get(i);
//                            if (sendCommand == receiveCommand) {
//                                Log.d(TAG, String.format("#%d send: %s, receive: %s", i + 1, sendCommand.toString(), receiveCommand.toString()));
//                            } else {
//                                Log.e(TAG, String.format("#%d send: %s, receive: %s", i + 1, sendCommand.toString(), receiveCommand.toString()));
//                            }
//                        } else {
//                            Command receiveCommand = mReceivedNotifyList.get(i);
//                            Log.e(TAG, String.format("#%d send: null, receive: %s", i + 1, receiveCommand.toString()));
//                        }
//                    }
//                    Log.d(TAG, "==============================================================");
//                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMac = getIntent().getStringExtra("mac");
        if (TextUtils.isEmpty(mMac)) {
            Log.d(TAG, "mac address is empty.");
            return;
        }
        iHealthDevicesManager.getInstance().registerClientCallback(mCallback);
        new Thread(){
            @Override
            public void run() {
                super.run();
                runPressureTest();
            }
        }.start();
    }

    private void runPressureTest() {
        generateCommandList();
        mReceivedNotifyList.clear();
        Object control = iHealthDevicesManager.getInstance().getAm3sControl(mMac);
        for (Command command : mSendCommandList) {
            command.run(this, control);
        }
    }

    private void generateCommandList() {
        mSendCommandList.clear();
        Random random = new Random();
        for (int i = 0; i < 200; i++) {
            mSendCommandList.add(Command.getCommand(random.nextInt(Command.SetPicture.what()) + 1));
        }
    }

    private enum Command {
        Unknown("Unknown", new OP() {
            @Override
            public void run(Context context, Object control) {
                Log.d(TAG, "do nothing");
            }
        }),
        GetUserId(AmProfile.ACTION_USERID_AM, new OP() {
            @Override
            public void run(Context context, Object control) {
                executeMethod(context, control, "getUserId", null);
            }
        }),
        SetUserId(AmProfile.ACTION_SET_USERID_SUCCESS_AM, new OP() {
            @Override
            public void run(Context context, Object control) {
                executeMethod(context, control, "setUserId", new Class[]{int.class}, 6);
            }
        }),
        SyncTime(AmProfile.ACTION_SYNC_TIME_SUCCESS_AM, new OP() {
            @Override
            public void run(Context context, Object control) {
                executeMethod(context, control, "syncRealTime", null);
            }
        }),
        SetUserInfo(AmProfile.ACTION_SET_USERINFO_SUCCESS_AM, new OP() {
            @Override
            public void run(Context context, Object control) {
                executeMethod(context, control, "setUserInfo", new Class[]{int.class, int.class, float.class, int.class, int.class, int.class, int.class},
                        25, 180, 80, 0, 0, 1000, 1);
            }
        }),
        GetUserInfo(AmProfile.ACTION_GET_USERINFO_AM, new OP() {
            @Override
            public void run(Context context, Object control) {
                executeMethod(context, control, "getUserInfo", null);
            }
        }),
        GetAlarmNum(AmProfile.ACTION_GET_ALARMNUM_AM, new OP() {
            @Override
            public void run(Context context, Object control) {
                executeMethod(context, control, "getAlarmClockNum", null);
            }
        }),
        GetAlarmInfo(AmProfile.ACTION_GET_ALARMINFO_AM, new OP() {
            @Override
            public void run(Context context, Object control) {
                executeMethod(context, control, "getAlarmClockDetail", new Class[]{int[].class},
                        new int[] {3, 3, 1, 1, 2, 1});
            }
        }),
        SetAlarmInfo(AmProfile.ACTION_SET_ALARMINFO_SUCCESS_AM, new OP() {
            @Override
            public void run(Context context, Object control) {
                executeMethod(context, control, "setAlarmClock", new Class[]{int.class, int.class, int.class, boolean.class, int[].class, boolean.class},
                        2, 12, 30, true, new int[]{1, 1, 1, 1, 1, 1, 1}, true);
            }
        }),
        DeleteAlarm(AmProfile.ACTION_DELETE_ALARM_SUCCESS_AM, new OP() {
            @Override
            public void run(Context context, Object control) {
                executeMethod(context, control, "deleteAlarmClock", new Class[]{int.class}, 1);
            }
        }),
        GetReminder(AmProfile.ACTION_GET_ACTIVITY_REMIND_AM, new OP() {
            @Override
            public void run(Context context, Object control) {
                executeMethod(context, control, "getActivityRemind", null);
            }
        }),
        SetReminder(AmProfile.ACTION_SET_ACTIVITYREMIND_SUCCESS_AM, new OP() {
            @Override
            public void run(Context context, Object control) {
                executeMethod(context, control, "setActivityRemind", new Class[] {int.class, int.class, boolean.class},
                        0 , 20, true);
            }
        }),
        SyncActivityData(AmProfile.ACTION_SYNC_ACTIVITY_DATA_AM, new OP() {
            @Override
            public void run(Context context, Object control) {
                executeMethod(context, control, "syncActivityData", null);
            }
        }),
        SyncSleepData(AmProfile.ACTION_SYNC_SLEEP_DATA_AM, new OP() {
            @Override
            public void run(Context context, Object control) {
                executeMethod(context, control, "syncSleepData", null);
            }
        }),
        GetDeviceState(AmProfile.ACTION_QUERY_STATE_AM, new OP() {
            @Override
            public void run(Context context, Object control) {
                executeMethod(context, control, "queryAMState", null);
            }
        }),
        SyncRealTimeData(AmProfile.ACTION_SYNC_REAL_DATA_AM, new OP() {
            @Override
            public void run(Context context, Object control) {
                executeMethod(context, control, "syncRealData", null);
            }
        }),
        SetBMR(AmProfile.ACTION_SET_BMR_SUCCESS_AM, new OP() {
            @Override
            public void run(Context context, Object control) {
                executeMethod(context, control, "setUserBmr", new Class[]{int.class}, 5000);
            }
        }),
        GetHourMode(AmProfile.ACTION_GET_HOUR_MODE_AM, new OP() {
            @Override
            public void run(Context context, Object control) {
                executeMethod(context, control, "getHourMode", null);
            }
        }),
        SetHourMode(AmProfile.ACTION_SET_HOUR_MODE_SUCCESS_AM, new OP() {
            @Override
            public void run(Context context, Object control) {
                executeMethod(context, control, "setHourMode", new Class[]{int.class}, 1);
            }
        }),
        SyncStageData_Finish(AmProfile.ACTION_SYNC_STAGE_DATA_AM, new OP() {
            @Override
            public void run(Context context, Object control) {
                executeMethod(context, control, "syncStageReprotData", null);
            }
        }),
        SendRandomNumber(AmProfile.ACTION_GET_RANDOM_AM, new OP() {
            @Override
            public void run(Context context, Object control) {
                executeMethod(context, control, "sendRandom", null);
            }
        }),
        GetPicture(AmProfile.ACTION_GET_PICTURE_AM, new OP() {
            @Override
            public void run(Context context, Object control) {
                executeMethod(context, control, "getPicture", null);
            }
        }),
        SetPicture(AmProfile.ACTION_SET_PICTURE_SUCCESS_AM, new OP() {
            @Override
            public void run(Context context, Object control) {
                executeMethod(context, control, "setPicture", new Class[]{int.class}, 1);
            }
        }),
        Reset(AmProfile.ACTION_RESET_AM, new OP() {
            @Override
            public void run(Context context, Object control) {
                executeMethod(context, control, "reset", new Class[]{int.class}, 6);
            }
        }),
        SetMode(AmProfile.ACTION_SET_DEVICE_MODE_AM, new OP() {
            @Override
            public void run(Context context, Object control) {
                executeMethod(context, control, "setMode", new Class[]{int.class}, 2);
            }
        }),
        GetSwimParameter(AmProfile.ACTION_GET_SWIMINFO_AM, new OP() {
            @Override
            public void run(Context context, Object control) {
                executeMethod(context, control, "checkSwimPara", null);
            }
        }),
        SetSwimParameter(AmProfile.ACTION_SET_SWIMINFO_AM, new OP() {
            @Override
            public void run(Context context, Object control) {
                executeMethod(context, control, "setSwimPara", new Class[]{boolean.class,  int.class, int.class, int.class, int.class},
                        AmProfile.AM_SWITCH_OPEN, 25, 0, 10, AmProfile.AM_SET_UNIT_IMPERIAL_STANDARD);
            }
        });

        String action;
        OP op;
        Command(String action, OP op) {
            this.action = action;
            this.op = op;
        }
        static PressureTestActivity.Command parseCommand(String action) {
            for (PressureTestActivity.Command command : values()) {
                if (command.action.equals(action)) {
                    return command;
                }
            }
            return Unknown;
        }

        static PressureTestActivity.Command getCommand(int what) {
            if (what < 0 || what >= values().length) {
                return Unknown;
            }
            return values()[what];
        }

        @Override
        public String toString() {
            return String.format("%s(%s)", name(), action);
        }

        public void run(Context context, Object control) {
            if (op != null) {
                op.run(context, control);
            }
        }

        public int what() {
            for (int i = 0; i < values().length; i++) {
                if (this == values()[i]) {
                    return i;
                }
            }
            return 0;
        }

        interface OP {
            void run(Context context, Object control);
        }
    }

    /**
     * Call specified method with reflection.
     * @param name Method's name
     * @param parametersForm Form of parameters, should be null or Class array
     * @param parameters parameters, should be empty or Object array
     */
    private static void executeMethod(Context context, Object control, String name, Class[] parametersForm, Object... parameters) {
        if (TextUtils.isEmpty(name)) {
            android.util.Log.e(TAG, "executeMethod() name is empty");
            return;
        }
        if (parametersForm != null && parameters != null) {
            if (parametersForm.length != parameters.length) {
                android.util.Log.e(TAG, "executeMethod() parameterForm and parameters length not equal.");
                return;
            }
        }
        if (control != null) {
            try {
                if (parametersForm != null) {
                    Method method = control.getClass().getMethod(name, parametersForm);
                    method.invoke(control, parameters);
                } else {
                    Method method = control.getClass().getMethod(name);
                    method.invoke(control);
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
                Toast.makeText(context, String.format("Not support this operation: %s(%s)", name, parameterString), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "control == null", Toast.LENGTH_LONG).show();
        }
    }

}
