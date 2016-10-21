package com.ihealth.communication.ins;

import android.content.Context;
import android.content.Intent;

import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.base.protocol.BleCommProtocol;
import com.ihealth.communication.base.protocol.BtCommProtocol;
import com.ihealth.communication.cloud.tools.AppsDeviceParameters;
import com.ihealth.communication.control.AbiProfile;
import com.ihealth.communication.control.BpProfile;
import com.ihealth.communication.manager.iHealthDevicesIDPS;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.privatecontrol.AbiControlSubManager;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.MD5;
import com.ihealth.communication.utils.PublicMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Private API for Bp series devices that contain Bp3m, Bp3l, Bp5, Bp7.
 *
 * @hide
 */
public class A1InsSet extends IdentifyIns implements NewDataCallback {

    private static final String TAG = "A1InsSet";

    private static final byte deviceType = (byte) 0xa1;

    private Context mContext;
    private BaseCommProtocol btcm;
    private String mAddress;
    private String mType;
    private String hVer = "";
    private String fVer = "";
    private String accessoryName = "";
    private String manufacture = "";
    private String modeNumber = "";
    private String protocolString = "";
    private BaseComm mBaseComm;
    private String mUserName;

    /* Product protocol callback */
    private InsCallback mInsCallback;

    /* Communication callback */
    private BaseCommCallback mBaseCommCallback;
    private A1DBtools mA1DBtools;

    /**
     * a constructor for A1InsSet.
     *
     * @param com               class for communication.
     * @param context           Context.
     * @param mac               valid Bluetooth address(without colon).
     * @param userName          valid Bluetooth name.
     * @param insCallback       Bp series protocol callback.
     * @param mBaseCommCallback communication callback.
     * @hide
     */
    public A1InsSet(Context context, BaseComm com, String userName, String mac, String type, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        Log.p(TAG, Log.Level.INFO, "A1InsSet_Constructor", userName, mac, type);
        this.mContext = context;
        this.mBaseComm = com;
        this.mUserName = userName;
        this.mAddress = mac;
        this.mType = type;
        this.mInsCallback = insCallback;
        if (type.equals(iHealthDevicesManager.TYPE_BP3L)) {
            this.btcm = new BleCommProtocol(context, com, mac, deviceType, this);
        } else {
            this.btcm = new BtCommProtocol(com, this);
        }
        this.mBaseCommCallback = mBaseCommCallback;
        this.mA1DBtools = new A1DBtools();
        setInsSetCallbak(insCallback, mac, type, mBaseComm);
    }

    /**
     * Get device IDPS
     *
     * @hide
     */
    public void getIdps() {
        Log.p(TAG, Log.Level.INFO, "getIdps");
        byte[] returnCommand = new byte[2];
        byte commandID = (byte) 0xf1;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        btcm.packageData(mAddress, returnCommand);
    }

    /**
     * Authentication
     *
     * @hide
     */
    public void identify() {
        Log.p(TAG, Log.Level.INFO, "identify");
        startTimeout(0xfa, AppsDeviceParameters.Delay_Medium, 0xfb, 0xfd, 0xfe);
        btcm.packageData(mAddress, identify(deviceType));
    }

    /**
     * Get battery of the current Bp device.
     *
     * @hide
     */
    public void getBatteryLevel() {
        Log.p(TAG, Log.Level.INFO, "getBatteryLevel");
        byte[] returnCommand = new byte[5];
        byte commandID = (byte) 0x20;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        returnCommand[2] = (byte) 0x00;
        returnCommand[3] = (byte) 0x00;
        returnCommand[4] = (byte) 0x00;
        startTimeout(0x20, AppsDeviceParameters.Delay_Medium, 0x20);
        btcm.packageData(mAddress, returnCommand);
    }

    /* Get information of the current Bp device. */
    public void getFunctionInfo() {
        Log.p(TAG, Log.Level.INFO, "getFunctionInfo");
        Calendar calenda = Calendar.getInstance();
        calenda.setTimeZone(TimeZone.getDefault());
        int year = calenda.get(Calendar.YEAR) - 2000;
        int month = calenda.get(Calendar.MONTH) + 1;
        int day = calenda.get(Calendar.DAY_OF_MONTH);
        int hour = calenda.get(Calendar.HOUR_OF_DAY);
        int min = calenda.get(Calendar.MINUTE);
        int sed = calenda.get(Calendar.SECOND);

        byte[] returnCommand = new byte[8];
        byte commandID = (byte) 0x21;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        returnCommand[2] = (byte) year;
        returnCommand[3] = (byte) month;
        returnCommand[4] = (byte) day;
        returnCommand[5] = (byte) hour;
        returnCommand[6] = (byte) min;
        returnCommand[7] = (byte) sed;
        startTimeout(0x21, AppsDeviceParameters.Delay_Medium, 0x21, 0x38);
        btcm.packageData(mAddress, returnCommand);
    }

    /* enable Off-line measurement */
    public void enableOfflineMeasure() {
        Log.p(TAG, Log.Level.INFO, "enableOfflineMeasure");
        byte[] returnCommand = new byte[5];
        byte commandID = (byte) 0x24;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        returnCommand[2] = (byte) 0x55;
        returnCommand[3] = (byte) 0x00;
        returnCommand[4] = (byte) 0x00;
        btcm.packageData(mAddress, returnCommand);
        //发送完指令后回调一个假的callback
        offlineMeasureCallBack(returnCommand[2]);
    }

    /* disable Off-line measurement */
    public void disableOfflineMeasure() {
        Log.p(TAG, Log.Level.INFO, "disableOfflineMeasure");
        byte[] returnCommand = new byte[5];
        byte commandID = (byte) 0x24;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        returnCommand[2] = (byte) 0x00;
        returnCommand[3] = (byte) 0x00;
        returnCommand[4] = (byte) 0x00;
        btcm.packageData(mAddress, returnCommand);

        offlineMeasureCallBack(returnCommand[2]);
    }

    /* start On-line measurement */
    public void startMeasure() {
        Log.p(TAG, Log.Level.INFO, "startMeasure");
        hasGetResult = false;
        byte lastHBP = 0x00;
        boolean samePerson = false;
        byte tempSP = 0x00;
        byte pressureFastLimit = 0x4b;
        byte pressureSlowLimit1H = 0x00;
        byte pressureSlowLimit1L = 0x29;
        byte pressureSlowLimit2H = 0x00;
        byte pressureSlowLimit2L = 0x1b;
        byte pressureLimit = 0x1e;

        if (samePerson)
            tempSP = (byte) 0x55;
        else
            tempSP = (byte) 0x00;

        byte[] returnCommand = new byte[10];
        byte commandID = (byte) 0x31;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        returnCommand[2] = lastHBP;
        returnCommand[3] = tempSP;
        returnCommand[4] = pressureFastLimit;
        returnCommand[5] = pressureSlowLimit1H;
        returnCommand[6] = pressureSlowLimit1L;
        returnCommand[7] = pressureSlowLimit2H;
        returnCommand[8] = pressureSlowLimit2L;
        returnCommand[9] = pressureLimit;
        isStopMeasure = false;
        startTimeout(0x31, AppsDeviceParameters.Delay_Medium, 0x30, 0x32, 0x3a, 0x3b, 0x3c, 0x3d, 0x3e, 0x36, 0x37, 0x38);
        btcm.packageData(mAddress, returnCommand);
    }

    /* interrupt the measurement immediately  */
    private boolean isStopMeasure = false;

    public void interruptMeasure() {
        Log.p(TAG, Log.Level.INFO, "interruptMeasure");
        byte[] returnCommand = new byte[5];
        byte commandID = (byte) 0x37;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        returnCommand[2] = (byte) 0x00;
        returnCommand[3] = (byte) 0x00;
        returnCommand[4] = (byte) 0x00;
        btcm.packageData(mAddress, returnCommand);
        stopTimeout(0x37);
        isStopMeasure = true;

        if (mType.equals(iHealthDevicesManager.TYPE_BP5) || mType.equals(iHealthDevicesManager.TYPE_BP7)) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    mInsCallback.onNotify(mAddress, mType, BpProfile.ACTION_INTERRUPTED_BP, "");
                }
            }, 500);
        }
    }

    /* This angle is not suitable for measurement, need to test the angle again. */
    public void angleNo() {
        Log.p(TAG, Log.Level.INFO, "angleNo");
        byte[] returnCommand = new byte[5];
        byte commandID = (byte) 0x3a;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        returnCommand[2] = (byte) 0x55;
        returnCommand[3] = (byte) 0x00;
        returnCommand[4] = (byte) 0x00;
        btcm.packageData(mAddress, returnCommand);
    }

    /* This angle is suitable for measurement, and start On-line measurement. */
    public void angleYes() {
        Log.p(TAG, Log.Level.INFO, "angleYes");
        byte[] returnCommand = new byte[5];
        byte commandID = (byte) 0x3a;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        returnCommand[2] = (byte) 0x00;
        returnCommand[3] = (byte) 0x00;
        returnCommand[4] = (byte) 0x00;
        btcm.packageData(mAddress, returnCommand);
    }

    /* Get Number of historical measurement data */
    public void getOffLineDataNum() {
        Log.p(TAG, Log.Level.INFO, "getOffLineDataNum");
        byte[] returnCommand = new byte[5];
        byte commandID = (byte) 0x40;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        returnCommand[2] = (byte) 0x01;
        returnCommand[3] = (byte) 0x00;
        returnCommand[4] = (byte) 0x00;
        btcm.packageData(mAddress, returnCommand);
    }

    /* Get historical measurement data in Bp device */
    public void getOffLineData() {
        Log.p(TAG, Log.Level.INFO, "getOffLineData");
        byte[] returnCommand = new byte[5];
        byte commandID = (byte) 0x41;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        returnCommand[2] = (byte) 0x01;
        returnCommand[3] = (byte) 0x00;
        returnCommand[4] = (byte) 0x00;
        btcm.packageData(mAddress, returnCommand);
    }

    /* delete historical measurement data */
    private void deleteOffline() {
        byte[] returnCommand = new byte[5];
        byte commandID = (byte) 0x45;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        returnCommand[2] = (byte) 0x01;
        returnCommand[3] = (byte) 0x00;
        returnCommand[4] = (byte) 0x00;
        btcm.packageData(mAddress, returnCommand);
    }

    private void ask() {
        byte[] returnCommand = new byte[1];
        returnCommand[0] = deviceType;
        btcm.packageDataAsk(returnCommand);
    }

    private void allPkgOk(byte commandID) {
        byte[] returnCommand = new byte[2];
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        btcm.packageData(mAddress, returnCommand);
    }

    /*
     * angle state
     * 1: angle is too big.
     * 2: angle is too small.
     * 3: angle is ok.
     */
    private int angle_Values = 0;
    private boolean hasGetResult = false;

    @Override
    public void haveNewData(int what, int stateId, byte[] returnData) {
        Log.p(TAG, Log.Level.DEBUG, "haveNewData", String.format("0x%02X", what), stateId, ByteBufferUtil.Bytes2HexString(returnData));
        if (0 == stateId && (!mType.equals(iHealthDevicesManager.TYPE_BP3L)) && (what == 0x32
                || what == 0x35 || what == 0x3b || what == 0x44 || what == 0x30 || what == 0x33
                || what == 0x3e || what == 0x3c || what == 0x3d || what == 0x3a
                || what == 0x36 || what == 0x38)) {
            ask();
        }
        stopTimeout(what);
        JSONObject jsonObject = new JSONObject();
        switch (what) {
            case 0xfb:
                byte[] req = deciphering(returnData, mType, deviceType);
                if (protocolString.contains("com.jiuan.BPV23")) {
                    req = deciphering(returnData, "BPweixin", deviceType);
                }
                startTimeout(0xfc, AppsDeviceParameters.Delay_Medium, 0xfd, 0xfe);
                btcm.packageData(mAddress, req);
                break;
            case 0xfd:
                this.mBaseCommCallback.onConnectionStateChange(mAddress, mType, iHealthDevicesManager.DEVICE_STATE_CONNECTED, 0, null);
                break;
            case 0xfe:
                mBaseComm.disconnect();
                break;

            case 0x20:
                int batteryLevel = returnData[0] & 0xff;
                if (!((batteryLevel > 0) && (batteryLevel <= 100))) {
                /* if the battery beyond 100, set battery to 100. */
                    batteryLevel = 100;
                }
                try {
                    jsonObject.put(BpProfile.BATTERY_BP, batteryLevel);
                    mInsCallback.onNotify(mAddress, mType, BpProfile.ACTION_BATTERY_BP, jsonObject.toString());
                } catch (JSONException e) {
                    Log.p(TAG, Log.Level.WARN, "Exception", e.getMessage());
                }

                break;

            case 0x21:
            /* is it can be off-line measurement? */
                boolean isOffline;
                if ((returnData[4] & 0x08) == 0) {
                    isOffline = false;
                } else {
                    isOffline = true;
                }
                try {
                    jsonObject.put(BpProfile.IS_ENABLE_OFFLINE, isOffline);
                    mInsCallback.onNotify(mAddress, mType, BpProfile.ACTION_IS_ENABLE_OFFLINE, jsonObject.toString());
                } catch (JSONException e) {
                    Log.p(TAG, Log.Level.WARN, "Exception", e.getMessage());
                }
                break;

            case 0x24:
                break;

            case 0x30:
                if (!isStopMeasure) {
                /* Bp device initializing is doing*/
                    mInsCallback.onNotify(mAddress, mType, BpProfile.ACTION_ZOREING_BP, jsonObject.toString());
                }
                break;

            case 0x31:
                break;

            case 0x32:
                if (!isStopMeasure) {
                /* Bp device initializing is done*/
                    mInsCallback.onNotify(mAddress, mType, BpProfile.ACTION_ZOREOVER_BP, jsonObject.toString());
                }
                if (mType.equals(iHealthDevicesManager.TYPE_BP3L)) {
                    allPkgOk((byte) 0x32);
                }
                break;

            case 0x3a:
                if (!isStopMeasure) {
                /* left hand */
                    if ((returnData[0] & 0xff) < 90) {
                        this.angle_Values = (returnData[0] & 0xff);
                        if (((returnData[0] & 0xff) >= 10) && ((returnData[0] & 0xff) <= 30)) {
                        } else {
                            if ((returnData[0] & 0xff) < 10) {
                            }
                            if ((returnData[0] & 0xff) > 30) {
                            }
                            angleNo();
                        }
                /* right hand */
                    } else if ((returnData[1] & 0xff) > 90) {
                        angleNo();
                    }
                    if ((returnData[0] & 0xff) < 90) {
                        //angle_Values = returnData[0]&0xff
                    }
                    if ((returnData[2] & 0xff) == 1) {/* angle is ok */}
                    if ((returnData[2] & 0xff) == 0) {/* angle is not ok */}
                    if ((returnData[3] & 0xff) == 1) {/* left hand */}
                    if ((returnData[3] & 0xff) == 2) {/* right hand */}
                    if ((returnData[3] & 0xff) == 3) {/* measurement is doing*/}
                    if ((returnData[3] & 0xff) == 4) {/* measurement is done and it did ont distinguish between right or left */}
                    try {
                        jsonObject.put(BpProfile.ANGLE_BP, angle_Values);
                        mInsCallback.onNotify(mAddress, mType, BpProfile.ACTION_ANGLE_BP, jsonObject.toString());
                    } catch (JSONException e) {
                        Log.p(TAG, Log.Level.WARN, "Exception", e.getMessage());
                    }
                }
                break;

            case 0x3b:
                mInsCallback.onNotify(mAddress, mType, BpProfile.ACTION_STOP_BP, "");
                break;

            case 0x3c:
                if (!isStopMeasure) {
                    if (mType.equals("BP3L")) {
                        convertWaveForBp3l(returnData, false);
                    } else {
                        convertWave(returnData, false);
                    }
                }
                break;

            case 0x3d:
                if (!isStopMeasure) {
                    if (mType.equals("BP3L")) {
                        convertWaveForBp3l(returnData, true);
                    } else {
                        convertWave(returnData, true);
                    }
                }
                break;

            case 0x3e:
                if (!isStopMeasure) {
                    int pressureData = (((returnData[0] & 0xff) * 256 + (returnData[1] & 0xff)) * 300 + 150) / 4096;
                    try {
                        jsonObject.put(BpProfile.BLOOD_PRESSURE_BP, pressureData);
                        mInsCallback.onNotify(mAddress, mType, BpProfile.ACTION_ONLINE_PRESSURE_BP, jsonObject.toString());
                    } catch (JSONException e) {
                        Log.p(TAG, Log.Level.WARN, "Exception", e.getMessage());
                    }
                }
                break;

            case 0x36:
                if (!hasGetResult) {
                    hasGetResult = true;
                    convertOnline(returnData);
                }
                break;

            case 0x37:
                mInsCallback.onNotify(mAddress, mType, BpProfile.ACTION_INTERRUPTED_BP, "");
                break;

            case 0x38:
                int errorNum = (int) (returnData[0] & 0xff);
                try {
                    if (!hasGetResult) {
                        hasGetResult = true;
                        jsonObject.put(iHealthDevicesManager.IHEALTH_DEVICE_TYPE, mType);
                        jsonObject.put(BpProfile.ERROR_NUM_BP, errorNum);
                        mInsCallback.onNotify(mAddress, mType, BpProfile.ACTION_ERROR_BP, jsonObject.toString());
                    }
                } catch (JSONException e) {
                    Log.p(TAG, Log.Level.WARN, "Exception", e.getMessage());
                }
                if (mType.equals(iHealthDevicesManager.TYPE_BP3L)) {
                    allPkgOk((byte) 0x38);
                }
                break;

            case 0x40:
                int num = returnData[1] & 0xff;
                try {
                    jsonObject.put(iHealthDevicesManager.IHEALTH_DEVICE_TYPE, mType);
                    jsonObject.put(BpProfile.HISTORICAL_NUM_BP, num);
                    mInsCallback.onNotify(mAddress, mType, BpProfile.ACTION_HISTORICAL_NUM_BP, jsonObject.toString());
                } catch (JSONException e) {
                    Log.p(TAG, Log.Level.WARN, "Exception", e.getMessage());
                }
                break;

            case 0x41:
                convertOffline(returnData);
                break;

            case 0x44:
                break;
            case 0xf0:
                packageIDPS(returnData);
                break;

            case 0xff:
                identify();
                break;

            default:
                Log.p(TAG, Log.Level.WARN, "Exception", "no method");
                break;
        }
    }

    //获取字符串的结束位置  以数字0为结束符
    private int getLength(byte[] buffer, int index) {
        int length = 0;
        for (int i = index; i < buffer.length; i++) {
            if (buffer[i] == 0) {
                break;
            }
            length++;
        }
        return length;
    }

    private void packageIDPS(byte[] returnData) {
        // com.jiuan.BPV20
        int length = getLength(returnData, 0);
        byte[] protocolStringBs = new byte[length];
        // BP Monitor
        length = getLength(returnData, 16);
        byte[] accessorynameBs = new byte[length];
        // 1.0.2
        length = 3;
        byte[] fwVerBs = new byte[length];
        // 1.0.1
        length = 3;
        byte[] hwVerBs = new byte[length];
        // iHealth
        length = getLength(returnData, 38);
        byte[] manufactureBs = new byte[length];
        // BP5 110700
        length = getLength(returnData, 54);
        //jing 20160822 BP5低电后无法获取ModeNumber
        if (length == 0) {
            length = 16;
        }
        byte[] modeNumberBs = new byte[length];
        try {
            for (int i = 0; i < protocolStringBs.length; i++) {
                protocolStringBs[i] = returnData[i + 0];
            }
            for (int i = 0; i < accessorynameBs.length; i++) {
                accessorynameBs[i] = returnData[i + 16];
            }
            for (int i = 0; i < fwVerBs.length; i++) {
                fwVerBs[i] = (byte) (returnData[i + 32] + 0x30);
            }
            for (int i = 0; i < hwVerBs.length; i++) {
                hwVerBs[i] = (byte) (returnData[i + 35] + 0x30);
            }
            for (int i = 0; i < manufactureBs.length; i++) {
                manufactureBs[i] = returnData[i + 38];
            }
            for (int i = 0; i < modeNumberBs.length; i++) {
                modeNumberBs[i] = returnData[i + 54];
            }
            protocolString = new String(protocolStringBs, "UTF-8");
            accessoryName = new String(accessorynameBs, "UTF-8");
            fVer = new String(fwVerBs, "UTF-8");
            hVer = new String(hwVerBs, "UTF-8");
            if (modeNumberBs[0] == 0x42) {
                modeNumber = new String(modeNumberBs, "UTF-8");
            } else {
                if (mType.equals(iHealthDevicesManager.TYPE_BP5)) {
                    if (protocolString.contains("com.jiuan.BPV21")) {
                        modeNumber = "BPS5X 11070";
                    } else {
                        modeNumber = "BP5 11070";
                    }
                } else if (mType.equals(iHealthDevicesManager.TYPE_BP7)) {
                    modeNumber = "BP7 11070";
                } else if (mType.equals(iHealthDevicesManager.TYPE_BP3L)) {
                    modeNumber = "BP3L 11070";
                } else {
                    modeNumber = "BPx 11070";
                }
            }
            manufacture = new String(manufactureBs, "UTF-8");
            Intent intent = new Intent(iHealthDevicesIDPS.MSG_IHEALTH_DEVICE_IDPS);
            intent.putExtra(iHealthDevicesIDPS.PROTOCOLSTRING, protocolString);
            intent.putExtra(iHealthDevicesIDPS.ACCESSORYNAME, accessoryName);
            intent.putExtra(iHealthDevicesIDPS.FIRMWAREVERSION, fVer);
            intent.putExtra(iHealthDevicesIDPS.HARDWAREVERSION, hVer);
            intent.putExtra(iHealthDevicesIDPS.MODENUMBER, modeNumber);
            intent.putExtra(iHealthDevicesIDPS.MANUFACTURER, manufacture);
            intent.putExtra(iHealthDevicesIDPS.SERIALNUMBER, mAddress);
            intent.putExtra(iHealthDevicesManager.IHEALTH_DEVICE_TYPE, mType);
            //20160731 jing  加包名限制，防止多个App间影响
            intent.setPackage(mContext.getPackageName());

            mContext.sendBroadcast(intent);

            if (modeNumber.contains("BPS5A")) {
                AbiControlSubManager.getInstance().createControlUp(mContext, mBaseComm, btcm, mUserName, mAddress, mType, AbiProfile.ABI_ARM, mInsCallback, mBaseCommCallback);
            } else if (modeNumber.contains("BPS5C")) {
                AbiControlSubManager.getInstance().createControlUp(mContext, mBaseComm, btcm, mUserName, mAddress, mType, AbiProfile.ABI_ARM, mInsCallback, mBaseCommCallback);
            } else if (modeNumber.contains("BPS5B")) {
                AbiControlSubManager.getInstance().createControlDown(mContext, mBaseComm, btcm, mUserName, mAddress, mType, AbiProfile.ABI_LEG, mInsCallback, mBaseCommCallback);
            } else if (modeNumber.contains("BPS5D")) {
                AbiControlSubManager.getInstance().createControlDown(mContext, mBaseComm, btcm, mUserName, mAddress, mType, AbiProfile.ABI_LEG, mInsCallback, mBaseCommCallback);
            } else if (modeNumber.contains("BPS5X")) {
                AbiControlSubManager.getInstance().createControlUnkonwn(mContext, mBaseComm, btcm, mUserName, mAddress, mType, AbiProfile.ABI_UNKNOWN, mInsCallback, mBaseCommCallback);
            } else {
                identify();
            }
        } catch (UnsupportedEncodingException e) {
            Log.p(TAG, Log.Level.WARN, "Exception", e.getMessage());
        } catch (Exception e1) {
            Log.p(TAG, Log.Level.WARN, "Exception", e1.getMessage());
        }
    }

    private void convertWave(byte[] datas, boolean heartbeat) {
        int pressure = (((datas[0] & 0xff) * 256 + (datas[1] & 0xff)) * 300 + 150) / 4096;
        byte[] measureData = new byte[8];
        for (int i = 2; i < datas.length; i++) {
            measureData[i - 2] = datas[i];
        }
        String wave = "[" + String.valueOf(measureData[0] & 0xff) + ","
                + String.valueOf(measureData[1] & 0xff) + ","
                + String.valueOf(measureData[2] & 0xff) + ","
                + String.valueOf(measureData[3] & 0xff) + ","
                + String.valueOf(measureData[4] & 0xff) + ","
                + String.valueOf(measureData[5] & 0xff) + ","
                + String.valueOf(measureData[6] & 0xff) + ","
                + String.valueOf(measureData[7] & 0xff) + "]";

        JSONObject o = null;
        try {
            o = new JSONObject();
            o.put(BpProfile.BLOOD_PRESSURE_BP, pressure);
            o.put(BpProfile.FLAG_HEARTBEAT_BP, heartbeat);
            o.put(BpProfile.PULSEWAVE_BP, wave);
            mInsCallback.onNotify(mAddress, mType, BpProfile.ACTION_ONLINE_PULSEWAVE_BP, o.toString());
        } catch (Exception e) {
            Log.p(TAG, Log.Level.WARN, "Exception", e.getMessage());
        }

    }

    private int seq_before = 0;

    private void convertWaveForBp3l(byte[] command, boolean heartbeat) {
        int sequence = command[0] & 0xff;
        int pressure = (((command[1] & 0xff) * 256 + (command[2] & 0xff)) * 300 + 150) / 4096;
        String wave = "";
        if ((sequence == 0 && seq_before != 255) || sequence == seq_before + 1) {
            int[] measure_3l = new int[5];
            for (int i = 3; i < 8; i++) {
                measure_3l[i - 3] = (command[i] & 0xff);
            }
            wave = "[" + String.valueOf(measure_3l[0] & 0xff) + ","
                    + String.valueOf(measure_3l[1] & 0xff) + ","
                    + String.valueOf(measure_3l[2] & 0xff) + ","
                    + String.valueOf(measure_3l[3] & 0xff) + ","
                    + String.valueOf(measure_3l[4] & 0xff) + "]";
        } else {
            int[] measure_3l = new int[10];
            for (int i = 3; i < 13; i++) {
                measure_3l[i - 3] = (command[i] & 0xff);
            }
            wave = "[" + String.valueOf(measure_3l[0] & 0xff) + ","
                    + String.valueOf(measure_3l[1] & 0xff) + ","
                    + String.valueOf(measure_3l[2] & 0xff) + ","
                    + String.valueOf(measure_3l[3] & 0xff) + ","
                    + String.valueOf(measure_3l[4] & 0xff) + ","
                    + String.valueOf(measure_3l[5] & 0xff) + ","
                    + String.valueOf(measure_3l[6] & 0xff) + ","
                    + String.valueOf(measure_3l[7] & 0xff) + ","
                    + String.valueOf(measure_3l[8] & 0xff) + ","
                    + String.valueOf(measure_3l[9] & 0xff) + "]";
        }

        JSONObject o = null;
        try {
            o = new JSONObject();
            o = new JSONObject();
            o.put(BpProfile.BLOOD_PRESSURE_BP, pressure);
            o.put(BpProfile.PULSEWAVE_BP, wave);
            o.put(BpProfile.FLAG_HEARTBEAT_BP, heartbeat);
            mInsCallback.onNotify(mAddress, mType, BpProfile.ACTION_ONLINE_PULSEWAVE_BP, o.toString());
        } catch (Exception e) {
            Log.p(TAG, Log.Level.WARN, "Exception", e.getMessage());
        }
        seq_before = sequence;
    }

    private void convertOffline(byte[] datas) {
        final JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        int num = (datas.length - 2) / 8;
        int j = 0;
        try {
            for (int i = 0; i < num; i++) {
                String year = String.valueOf(2000 + (int) (datas[j + 2] & 0x7f));
                String month = String.valueOf((datas[j + 3] & 0x0f));
                String day = String.valueOf((datas[j + 4] & 0x1f));
                String hour = String.valueOf((datas[j + 5] & 0x3f));
                String min = String.valueOf((datas[j + 6] & 0x3f));
                int temp = (int) (datas[j + 7] & 0xff);
                int dia = (int) (datas[j + 8] & 0xff);
                int sys = temp + dia;
                int pulse = (int) (datas[j + 9] & 0xff);
                int ahr = (datas[j + 2] & 0xff) >> 7;
                int hsd = (datas[j + 3] & 0xff) >> 7;
                String dateStr = year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + "00";
                JSONObject stoneObject = new JSONObject();
                stoneObject.put(BpProfile.MEASUREMENT_DATE_BP, dateStr);
                stoneObject.put(BpProfile.HIGH_BLOOD_PRESSURE_BP, sys);
                stoneObject.put(BpProfile.LOW_BLOOD_PRESSURE_BP, dia);
                stoneObject.put(BpProfile.PULSE_BP, pulse);
                if (ahr == 0) {
                    stoneObject.put(BpProfile.MEASUREMENT_AHR_BP, false);
                } else {
                    stoneObject.put(BpProfile.MEASUREMENT_AHR_BP, true);
                }
                if (hsd == 0) {
                    stoneObject.put(BpProfile.MEASUREMENT_HSD_BP, false);
                } else {
                    stoneObject.put(BpProfile.MEASUREMENT_HSD_BP, true);
                }

                //add dataid
                stoneObject.put(BpProfile.DATAID, MD5.md5String(PublicMethod.getBPDataID(mAddress, pulse + "", PublicMethod.String2TS(dateStr))));    //mac+心率+测量时间
                array.put(stoneObject);
                j += 8;
            }
            object.putOpt(BpProfile.HISTORICAL_DATA_BP, array);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    mInsCallback.onNotify(mAddress, mType, BpProfile.ACTION_HISTORICAL_DATA_BP, object.toString());
                }
            }, 500);
        } catch (JSONException e) {
            Log.p(TAG, Log.Level.WARN, "Exception", e.getMessage());
        }
    }

    private void convertOnline(byte[] datas) {
        int high_pressure_temp = (datas[0] & 0xff);
        int low_pressure = (datas[1] & 0xff);
        int pulse = (datas[2] & 0xff);
        int ahr = (datas[3] & 0xff);
        int hsd = (datas[4] & 0xff);
        int high_pressure = high_pressure_temp + low_pressure;
        long ts = System.currentTimeMillis() / 1000;
        mA1DBtools.save(mContext, mUserName, mAddress, mType, high_pressure, low_pressure, pulse, ts);
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(BpProfile.HIGH_BLOOD_PRESSURE_BP, high_pressure);
            jsonObject.put(BpProfile.LOW_BLOOD_PRESSURE_BP, low_pressure);
            jsonObject.put(BpProfile.PULSE_BP, pulse);
            if (ahr == 0) {
                jsonObject.put(BpProfile.MEASUREMENT_AHR_BP, false);
            } else {
                jsonObject.put(BpProfile.MEASUREMENT_AHR_BP, true);
            }
            if (hsd == 0) {
                jsonObject.put(BpProfile.MEASUREMENT_HSD_BP, false);
            } else {
                jsonObject.put(BpProfile.MEASUREMENT_HSD_BP, true);
            }
            jsonObject.put(BpProfile.DATAID, MD5.md5String(PublicMethod.getBPDataID(mAddress, pulse + "", ts))); //mac+心率+当前系统时间戳
            mInsCallback.onNotify(mAddress, mType, BpProfile.ACTION_ONLINE_RESULT_BP, jsonObject.toString());
        } catch (JSONException e) {
            Log.p(TAG, Log.Level.WARN, "Exception", e.getMessage());
        }
    }

    /**
     * 是否允许离线测量，假的回调
     *
     * @param flag
     */
    private void offlineMeasureCallBack(byte flag) {
        switch (flag) {
            case (byte) 0x00:
                mInsCallback.onNotify(mAddress, mType, BpProfile.ACTION_DISENABLE_OFFLINE_BP, null);
                break;
            case (byte) 0x55:
                mInsCallback.onNotify(mAddress, mType, BpProfile.ACTION_ENABLE_OFFLINE_BP, null);
                break;
            default:
                break;
        }
    }

    @Override
    public void haveNewDataUuid(String uuid, byte[] command) {

    }

    public void destroy() {
        Log.p(TAG, Log.Level.INFO, "destroy");
        if (btcm != null)
            btcm.destroy();
        btcm = null;
        mContext = null;
        mBaseComm = null;
    }

}
