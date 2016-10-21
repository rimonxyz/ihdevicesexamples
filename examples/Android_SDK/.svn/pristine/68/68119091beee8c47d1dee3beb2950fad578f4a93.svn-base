package com.ihealth.communication.ins;

import android.content.Context;
import com.ihealth.communication.utils.Log;

import com.ihealth.communication.base.ble.BleComm;
import com.ihealth.communication.base.ble.BleConfig;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.base.protocol.BleCommContinueProtocol;
import com.ihealth.communication.control.BpProfile;
import com.ihealth.communication.utils.ContinuaDataAnalysis;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

/**
 * Created by jing on 16/6/22.
 */
public class BPInsSet implements NewDataCallback {

    private static final String TAG = "BPInsSet";
    private Context mContext;
    private BaseComm mComm;
    private BleComm mBleCom;
    private String mAddress;
    private String mType;
    private InsCallback mInsCallback;
    private int currentUserID = 0;
    private boolean enableiHealthFlag = false;
    private boolean enableContinuaFlag = false;


    private BleCommContinueProtocol mbleCommContinueProtocol;

    public BPInsSet(Context context, BaseComm com,  BleComm bleComm, String userName, String mac, String type, InsCallback insCallback, BaseCommCallback mBaseCommCallback){
        this.mContext = context;
        this.mComm = com;
        this.mBleCom = bleComm;
        this.mAddress = mac;
        this.mType = type;
        this.mInsCallback = insCallback;
        this.mbleCommContinueProtocol = new BleCommContinueProtocol(com, mac, this);
    }

    public boolean getBattery(){
        BleComm bleComm = null;
        if (!(mComm instanceof BleComm))
            return false;
        else
            bleComm = (BleComm)mComm;
        UUID serviceUuid = UUID.fromString(BleConfig.UUID_BTM_BATTERY_SERVICE);
        UUID charaticUuid = UUID.fromString(BleConfig.UUID_BTM_BATTERY_LEVEL_CHARACTERISTIC);
        return bleComm.Obtain(mAddress, serviceUuid, charaticUuid);
    }

    /**
     * get the feature of measurement
     */
    public boolean getFeature(){
        BleComm bleComm = null;
        if (!(mComm instanceof BleComm))
            return false;
        else
            bleComm = (BleComm)mComm;
        UUID serviceUuid = UUID.fromString(BleConfig.UUID_BP_SERVICE);
        UUID charaticUuid = UUID.fromString(BleConfig.UUID_BP_READ);
        return bleComm.Obtain(mAddress, serviceUuid, charaticUuid);
    }

    /**
     * get the nub of data for user
     */
    public void getNubForUser(){
        //使能BP的iHealth 自定义的Indicate，用来发送辅助指令，补充Continua 的不足之处
        mBleCom.getService(mAddress, BleConfig.UUID_BP_SERVICE, BleConfig.UUID_BP_SEND, BleConfig.UUID_BP_RECEIVE_iHealth, BleConfig.UUID_180A, false);

    }


    /**
     * set current user
     */
    public void commandSetUser(int UserID){
        currentUserID = UserID;
        if (enableiHealthFlag == true) {
            byte[] returnCommand = new byte[2];
            returnCommand[0] = (byte) 0xA0;
            //发送iHealth设置用户指令   currentUserID
            returnCommand[1] = (byte) currentUserID;
            mComm.sendData(mAddress, returnCommand);
        } else {
            //使能BP的iHealth 自定义的Indicate，用来发送辅助指令，补充Continua 的不足之处
            mBleCom.getService(mAddress, BleConfig.UUID_BP_SERVICE, BleConfig.UUID_BP_SEND, BleConfig.UUID_BP_RECEIVE_iHealth, BleConfig.UUID_180A, false);
        }
    }


    /**
     * get the data for the current user
     */
    public void getData(){
        if (enableContinuaFlag == false) {
            //使能BP的Indicate，之后BP会自动发送数据上来
            mBleCom.getService(mAddress, BleConfig.UUID_BP_SERVICE, BleConfig.UUID_BP_SEND, BleConfig.UUID_BP_RECEIVE, BleConfig.UUID_180A, true);
        }
    }


    @Override
    public void haveNewData(int what, int stateId, byte[] command) {

    }

    @Override
    public void haveNewDataUuid(String uuid, byte[] command) {
        if (uuid.equals(BleConfig.UUID_BTM_BATTERY_LEVEL_CHARACTERISTIC)){
            int batteryLevel = command[0];
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put(BpProfile.BATTERY_CBP, batteryLevel);
                mInsCallback.onNotify(mAddress, mType,  BpProfile.ACTION_BATTERY_CBP, jsonObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if(uuid.equals(BleConfig.UUID_BP_RECEIVE)){
            if (command == null) {
                enableContinuaFlag = true;
                Log.i(TAG, "Enable indicate success");
                return;
            }
            //解析标准Continua协议
            JSONObject jsonObject = new JSONObject();
            JSONObject temp = null;
            try {
                temp = decodeBPResult(command);
            }catch (Exception e){
                e.printStackTrace();
            }

            if (temp == null) {
                return;
            }
            try {
                jsonObject.put(BpProfile.HISTORY_DATA_CBP, temp);
                mInsCallback.onNotify(mAddress, mType, BpProfile.ACTION_HISTORY_DATA_CBP, jsonObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if(uuid.equals(BleConfig.UUID_BP_RECEIVE_iHealth)) {
            if (command == null) {
                enableiHealthFlag = true;
                Log.i(TAG, "Enable indicate success for iHealth");
                byte[] returnCommand = new byte[2];
                returnCommand[0] = (byte) 0xA0;
                //发送iHealth设置用户指令   currentUserID
                returnCommand[1] = (byte) currentUserID;
                mComm.sendData(mAddress, returnCommand);
                return;
            }
            //解析iHealth协议  返回UI设置用户成功，UI 再调用获取数据方法
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put(BpProfile.CHOOSE_USER_CBP, 1);
                mInsCallback.onNotify(mAddress, mType, BpProfile.ACTION_CONFORM_CHOOSE_USER_CBP, jsonObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if(uuid.equals(BleConfig.UUID_BP_READ)) {
            //解析标准Continua协议
            JSONObject temp = null;
            try {
                temp = decodeBPFeature(command, command.length);
            }catch (Exception e){
                e.printStackTrace();
            }

            if (temp == null) {
                return;
            }
            mInsCallback.onNotify(mAddress, mType, BpProfile.ACTION_FEATURE_CBP, temp.toString());
        }

    }

    /**
     * This method decode bp value received from Health bp device
     *
     */
    private JSONObject decodeBPResult(byte[] command) throws Exception {
//        Log.e(TAG, "Have new bp data:" + ByteBufferUtil.Bytes2HexString(command));
        if (command.length<8) {
            Log.e(TAG,"Invalidate data");
            return null;
        }
        //数据解析过程
        ContinuaDataAnalysis dataAnalysis = new ContinuaDataAnalysis(command,command.length);
        JSONObject offlineData = new JSONObject();
        try {
            byte measureFlag = dataAnalysis.read8ByteValue();
            //单位
            offlineData.put(BpProfile.CBPINFO_UNIT,measureFlag & 0x01);
            //有无时间戳
            int timestampFlag = (measureFlag>>1) & 0x01;
            offlineData.put(BpProfile.CBPINFO_TIMESTAMP_FLAG,timestampFlag);
            //有无脉率
            int pulseRateFlag = (measureFlag>>2) & 0x01;
            offlineData.put(BpProfile.CBPINFO_PULSE_RATE_FLAG,pulseRateFlag);
            //有无用户ID
            int userIDFlag = (measureFlag>>3) & 0x01;
            offlineData.put(BpProfile.CBPINFO_USER_ID_FLAG,userIDFlag);
            //有无测量状态
            int measureStatusFlag = (measureFlag>>4) & 0x01;
            offlineData.put(BpProfile.CBPINFO_MEASURE_STATUS_FLAG,measureStatusFlag);
            //有无HSD检测
            int hsdFlag = (measureFlag>>6) & 0x01;
            offlineData.put(BpProfile.CBPINFO_HSD_FLAG,hsdFlag);

            //高压
            float sys = dataAnalysis.readSFloatValue();
            offlineData.put(BpProfile.CBPINFO_SYS,sys);

            //高压
            float dia = dataAnalysis.readSFloatValue();
            offlineData.put(BpProfile.CBPINFO_DIA,dia);

            //平均压
            float map = dataAnalysis.readSFloatValue();
            offlineData.put(BpProfile.CBPINFO_MAP,map);

            //测量时间
            if (timestampFlag == 1) {
                String measureTime = dataAnalysis.readDateValue();
                offlineData.put(BpProfile.CBPINFO_TIMESTAMP, measureTime);
            }

            //脉率
            if (pulseRateFlag == 1) {
                float pulseRate = dataAnalysis.readSFloatValue();
                offlineData.put(BpProfile.CBPINFO_PULSE_RATE, pulseRate);
            }

            //用户ID
            if (userIDFlag == 1) {
                int userID = dataAnalysis.readUInt8Value();
                offlineData.put(BpProfile.CBPINFO_USER_ID, userID);
            }

            if (measureStatusFlag == 1) {
                int status = dataAnalysis.read16ByteValue();
                //测量过程中是否移动
                offlineData.put(BpProfile.CBPINFO_BODY_MOVEMENT, status & 0x01);
                //袖带松紧合适
                offlineData.put(BpProfile.CBPINFO_CUF_FIT, (status >> 1) & 0x01);
                //是否脉率不齐
                offlineData.put(BpProfile.CBPINFO_IRREGULAR, (status >> 2) & 0x01);
                //脉率是否超出正常范围
                offlineData.put(BpProfile.CBPINFO_PULSE_RATE_RANGE, (status >> 3) & 0x03);
                //测量姿势正确
                offlineData.put(BpProfile.CBPINFO_POSITION, (status >> 4) & 0x01);
                if (hsdFlag == 1) {
                    //检测到HSD
                    offlineData.put(BpProfile.CBPINFO_HSD, (status >> 6) & 0x01);
                }
            }

//            Log.i(TAG, "offlineData:" + offlineData);
        } catch (JSONException exception) {
            Log.e(TAG,exception.toString());
        }

        return offlineData;
    }

    private JSONObject decodeBPFeature(byte[] data, int length) {
        if (length<2) {
            Log.e(TAG,"Invalidate data");
            return null;
        }
        JSONObject jsonObject = new JSONObject();

        try {

            ContinuaDataAnalysis dataAnalysis = new ContinuaDataAnalysis(data, length);

            short status = dataAnalysis.read16ByteValue();

            jsonObject.put(BpProfile.FEATURE_BODY_MOVEMENT_CBP, status&0x01);
            jsonObject.put(BpProfile.FEATURE_FIT_DETECTION_CBP, (status>>1)&0x01);
            jsonObject.put(BpProfile.FEATURE_IRREGULAR_PULSE_DETECTION_CBP, (status>>2)&0x01);
            jsonObject.put(BpProfile.FEATURE_PULSE_RATE_RANGE_DETECTION_CBP, (status>>3)&0x01);
            jsonObject.put(BpProfile.FEATURE_MEASUREMENT_POSITION_DETECTION_CBP, (status>>4)&0x01);
            jsonObject.put(BpProfile.FEATURE_MULTIPLE_BOND_CBP, (status>>5)&0x01);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;

    }

    private short fabs(byte octet) {
        if (octet < 0) {
            return (short) (octet & 0xFF);
        } else {
            return octet;
        }
    }

    public void destroy(){
        mInsCallback = null;
        mContext = null;
        if(mbleCommContinueProtocol != null)
            mbleCommContinueProtocol.destroy();
        mbleCommContinueProtocol = null;
    }
}
