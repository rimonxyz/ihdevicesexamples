package com.ihealth.communication.ins;

import android.content.Context;

import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.Log;

import com.ihealth.communication.base.ble.BleComm;
import com.ihealth.communication.base.ble.BleConfig;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.base.protocol.BleCommContinueProtocol;
import com.ihealth.communication.control.BsProfile;
import com.ihealth.communication.utils.ContinuaDataAnalysis;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

/**
 * Created by jing on 16/8/30.
 */
public class BsInsSet implements NewDataCallback {
    private static final String TAG = "BsInsSet";
    private Context mContext;
    private BaseComm mComm;
    private BleComm mBleCom;
    private String mAddress;
    private String mType;
    private InsCallback mInsCallback;
    private BleCommContinueProtocol mbleCommContinueProtocol;
    private boolean enableMeasurementFlag;

    public BsInsSet(Context context, BaseComm com, BleComm bleComm, String userName, String mac, String type, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        Log.p(TAG, Log.Level.INFO, "BsInsSet_Constructor", userName, mac, type);
        this.mContext = context;
        this.mComm = com;
        this.mBleCom = bleComm;
        this.mAddress = mac;
        this.mType = type;
        this.mInsCallback = insCallback;
        this.mbleCommContinueProtocol = new BleCommContinueProtocol(com, mac, this);
    }

    public boolean getBattery() {
        Log.p(TAG, Log.Level.INFO, "getBattery");
        BleComm bleComm = null;
        if (!(mComm instanceof BleComm))
            return false;
        else
            bleComm = (BleComm) mComm;
        UUID serviceUuid = UUID.fromString(BleConfig.UUID_BTM_BATTERY_SERVICE);
        UUID charaticUuid = UUID.fromString(BleConfig.UUID_BTM_BATTERY_LEVEL_CHARACTERISTIC);
        return bleComm.Obtain(mAddress, serviceUuid, charaticUuid);
    }

    /**
     * get the feature of measurement
     */
    public boolean getFeature() {
        Log.p(TAG, Log.Level.INFO, "getFeature");
        BleComm bleComm;
        if (!(mComm instanceof BleComm))
            return false;
        else
            bleComm = (BleComm) mComm;
        UUID serviceUuid = UUID.fromString(BleConfig.UUID_BS_SERVICE);
        UUID charaticUuid = UUID.fromString(BleConfig.UUID_BS_READ);
        return bleComm.Obtain(mAddress, serviceUuid, charaticUuid);
    }

    /**
     * get measurement context
     */
    public void getMeasurement() {
        Log.p(TAG, Log.Level.INFO, "getMeasurement");
        if (!enableMeasurementFlag) {
            //使能BS的Measurement Notify，之后BS会自动发送数据上来
            mBleCom.getService(mAddress, BleConfig.UUID_BS_SERVICE, null, BleConfig.UUID_BS_RECEIVE, BleConfig.UUID_180A, true);
        }
    }


    @Override
    public void haveNewData(int what, int stateId, byte[] command) {

    }

    @Override
    public void haveNewDataUuid(String uuid, byte[] command) {
        Log.p(TAG, Log.Level.DEBUG, "haveNewData", uuid, ByteBufferUtil.Bytes2HexString(command));
        if (uuid.equals(BleConfig.UUID_BTM_BATTERY_LEVEL_CHARACTERISTIC)) {
            int batteryLevel = command[0];
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put(BsProfile.CBS_BATTERY, batteryLevel);
                mInsCallback.onNotify(mAddress, mType, BsProfile.ACTION_BATTERY_CBS, jsonObject.toString());
            } catch (JSONException e) {
                Log.p(TAG, Log.Level.WARN, "Exception", e.getMessage());
            }
        } else if (uuid.equals(BleConfig.UUID_HS_RECEIVE)) {
            if (command == null) {
                enableMeasurementFlag = true;
                return;
            }
            //解析标准Continua协议
            try {
                JSONObject temp = decodeCBSResult(command);
                if (temp == null) {
                    return;
                }
                mInsCallback.onNotify(mAddress, mType, BsProfile.ACTION_CBS_MEASUREMENT_DATA, temp.toString());
            } catch (Exception e) {
                Log.p(TAG, Log.Level.WARN, "Exception", e.getMessage());
            }
        }
    }

    /**
     * This method decode bp value received from Health bp device
     */
    private JSONObject decodeCBSResult(byte[] command) {
        if (command.length < 4) {
            return null;
        }

        //数据解析过程
        ContinuaDataAnalysis dataAnalysis = new ContinuaDataAnalysis(command, command.length);
        JSONObject data = new JSONObject();
        try {
            short flag = dataAnalysis.read16ByteValue();
            //计量单位
            int unitFlag = flag & 0x01;
            data.put(BsProfile.CBS_UNIT_FLAG, unitFlag);
            //有无时间戳
            int timestampFlag = (flag >> 1) & 0x01;
            data.put(BsProfile.CBS_TIMESTAMP_FLAG, timestampFlag);
            //有无用户ID
            int userIDFlag = (flag >> 2) & 0x01;
            data.put(BsProfile.CBS_USER_ID_FLAG, userIDFlag);
            //有无基础代谢物
            int BMFlag = (flag >> 3) & 0x01;
            data.put(BsProfile.CBS_BASAL_METABOLISM_FLAG, BMFlag);
            //有无肌肉百分比
            int MPFlag = (flag >> 4) & 0x01;
            data.put(BsProfile.CBS_MUSCLE_PERCENTAGE_FLAG, MPFlag);
            //有无肌肉呈现
            int MMFlag = (flag >> 5) & 0x01;
            data.put(BsProfile.CBS_MUSCLE_MASS_FLAG, MMFlag);
            //有无脂肪质量
            int FFMFlag = (flag >> 6) & 0x01;
            data.put(BsProfile.CBS_FAT_FREE_MASS_FLAG, FFMFlag);
            //有无软精益质量
            int SLMFlag = (flag >> 7) & 0x01;
            data.put(BsProfile.CBS_SOFT_LEAN_MASS_FLAG, SLMFlag);
            //有无身体水质量
            int BWMFlag = (flag >> 8) & 0x01;
            data.put(BsProfile.CBS_BODY_WATER_MASS_FLAG, BWMFlag);
            //有无阻抗
            int impedanceFlag = (flag >> 9) & 0x01;
            data.put(BsProfile.CBS_IMPEDANCE_FLAG, impedanceFlag);
            //有无体重
            int weightFlag = (flag >> 10) & 0x01;
            data.put(BsProfile.CBS_WEIGHT_FLAG, weightFlag);
            //有无身高
            int heightFlag = (flag >> 11) & 0x01;
            data.put(BsProfile.CBS_HEIGHT_FLAG, heightFlag);
            //有无多个包测量
            int MPMFlag = (flag >> 12) & 0x01;
            data.put(BsProfile.CBS_MULTIPLE_PACKET_MEASUREMENT_FLAG, MPMFlag);

            //体脂百分比
            data.put(BsProfile.CBS_BODY_FAT_PERCENTAGE, dataAnalysis.readUInt16Value());

            //测量时间
            if (timestampFlag == 1) {
                String measureTime = dataAnalysis.readDateValue();
                data.put(BsProfile.CBS_TIME_STAMP, measureTime);
            }

            //用户ID
            if (userIDFlag == 1) {
                int userID = dataAnalysis.readUInt8Value() & 0xFF;
                data.put(BsProfile.CBS_USER_ID, userID);
            }

            if (BMFlag == 1) {
                data.put(BsProfile.CBS_BASAL_METABOLISM, dataAnalysis.readUInt16Value());
            }

            if (MPFlag == 1) {
                data.put(BsProfile.CBS_MUSCLE_PERCENTAGE, dataAnalysis.readUInt16Value());
            }

            if (unitFlag == 0) {
                if (MMFlag == 1) {
                    data.put(BsProfile.CBS_MUSCLE_MASS_KILOGRAMS, dataAnalysis.readUInt16Value());
                }
                if (FFMFlag == 1) {
                    data.put(BsProfile.CBS_FAT_FREE_MASS_KILOGRAMS, dataAnalysis.readUInt16Value());
                }
                if (SLMFlag == 1) {
                    data.put(BsProfile.CBS_SOFT_LEAN_MASS_KILOGRAMS, dataAnalysis.readUInt16Value());
                }
                if (BWMFlag == 1) {
                    data.put(BsProfile.CBS_BODY_WATER_MASS_KILOGRAMS, dataAnalysis.readUInt16Value());
                }
            } else {
                if (MMFlag == 1) {
                    data.put(BsProfile.CBS_MUSCLE_MASS_POUNDS, dataAnalysis.readUInt16Value());
                }
                if (FFMFlag == 1) {
                    data.put(BsProfile.CBS_FAT_FREE_MASS_POUNDS, dataAnalysis.readUInt16Value());
                }
                if (SLMFlag == 1) {
                    data.put(BsProfile.CBS_SOFT_LEAN_MASS_POUNDS, dataAnalysis.readUInt16Value());
                }
                if (BWMFlag == 1) {
                    data.put(BsProfile.CBS_BODY_WATER_MASS_POUNDS, dataAnalysis.readUInt16Value());
                }
            }

            if (impedanceFlag == 1) {
                data.put(BsProfile.CBS_IMPEDANCE, dataAnalysis.readUInt16Value());
            }

            if (unitFlag == 0) {
                if (weightFlag == 1) {
                    data.put(BsProfile.CBS_WEIGHT_KILOGRAMS, dataAnalysis.readUInt16Value());
                }
                if (heightFlag == 1) {
                    data.put(BsProfile.CBS_HEIGHT_KILOGRAMS, dataAnalysis.readUInt16Value());
                }
            } else {
                if (weightFlag == 1) {
                    data.put(BsProfile.CBS_WEIGHT_POUNDS, dataAnalysis.readUInt16Value());
                }
                if (heightFlag == 1) {
                    data.put(BsProfile.CBS_HEIGHT_POUNDS, dataAnalysis.readUInt16Value());
                }
            }
        } catch (JSONException exception) {
            Log.p(TAG, Log.Level.WARN, "Exception", exception.getMessage());
        }

        return data;
    }


    public void destroy() {
        Log.p(TAG, Log.Level.INFO, "destroy");
        mInsCallback = null;
        mContext = null;
        if (mbleCommContinueProtocol != null)
            mbleCommContinueProtocol.destroy();
        mbleCommContinueProtocol = null;
    }
}
