package com.ihealth.communication.control;

import android.content.Context;

import com.ihealth.communication.base.ble.BleComm;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.BPInsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.manager.iHealthDevicesCallback;

/**
 * Created by jing on 16/6/22.
 */

/**
 * Public API for Continua BP
 * <p> The class provides methods to control Continua BP.
 * You need to call the device method, and then call the connection method
 * <p> If you want to connect a Continua BP, you need to call{@link iHealthDevicesManager#startDiscovery} to discovery a new Continua BP,
 * and then call{@link iHealthDevicesManager#connectDevice}to connect Continua BP.
 * <p>A {@link iHealthDevicesCallback#onDeviceNotify} callback is
 * triggered to report the memory.
 * Action : ACTION_HISTORY_DATA_CBP
 * Example:{"bp_unit":0,"bp_timestampflag":1,"bp_pulserateflag":1,"bp_useridflag":1,"bp_measurestatusflag":1,"bp_hsdflag":0,"bp_sys":119,"bp_dia":76,"bp_map":0,"bp_timestamp":"2015-1-1 1:0:0","bp_pulserate":78,"bp_userid":1,"bp_body_movement":0,"bp_cufffit":0,"bp_irregular":0,"bp_pulseraterange":0,"bp_position":0,"bp_hsd":0}
 */
public class BPControl implements DeviceControl{

    final private String TAG = "BPControl";
    private BaseComm mComm;
    private Context mContext;
    private String mAddress;
    private String mType;

    private BPInsSet bpInsSet;
    private BaseCommCallback mBaseCommCallback;



    public BPControl(Context context, BaseComm mBaseComm, BleComm bleComm, String userName, String mac, String type, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.mComm = mBaseComm;
        this.mContext = context;
        this.mAddress = mac;
        this.mType = type;

        this.mBaseCommCallback = mBaseCommCallback;

        bpInsSet = new BPInsSet(context,mBaseComm,bleComm,userName,mac,type,insCallback,mBaseCommCallback);
    }

    /**
     * @hide
     */
    @Override
    public void init() {
        this.mBaseCommCallback.onConnectionStateChange(mAddress, mType, iHealthDevicesManager.DEVICE_STATE_CONNECTED, 0, null);
        //自己调用使能通知  启动BP上传数据
//        bpInsSet.getData();
    }


    /**
     * get battery for Continua Bp device
     * <ul>
     * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
     * </ul>
     * <ul>
     * <li>This is an asynchronous call, it will not return immediately. After get the battery status,
     * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
     * </ul>
     * <ul>
     * <li>The action of the callback is {@link BpProfile#ACTION_BATTERY_CBP}, the keys of message will show in {@linkplain BpProfile#ACTION_BATTERY_CBP KeyList of the action}.
     * </li>
     * </ul>
     */
    public void getBattery(){
        bpInsSet.getBattery();
    }

    /**
     * get the feature for Continua Bg device
     * <ul>
     * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
     * </ul>
     * <ul>
     * <li>This is an asynchronous call, it will not return immediately. After get the battery status,
     * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
     * </ul>
     * <ul>
     * <li>The action of the callback is {@link BpProfile#ACTION_FEATURE_CBP}, the keys of message will show in {@linkplain BpProfile#ACTION_FEATURE_CBP KeyList of the action}.
     * </li>
     * </ul>
     */
    public void getFeature(){
        bpInsSet.getFeature();
    }

    /**
     * set current user
     * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
     * </ul>
     * <ul>
     * <li>This is an asynchronous call, it will not return immediately. After get the battery status,
     * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
     * </ul>
     * <ul>
     * <li>The action of the callback is {@link BpProfile#ACTION_CONFORM_CHOOSE_USER_CBP}, the keys of message will show in {@linkplain BpProfile#ACTION_CONFORM_CHOOSE_USER_CBP KeyList of the action}.
     * </li>
     * </ul>
     */
    public void commandSetUser(int UserID){
        bpInsSet.commandSetUser(UserID);
    }

    /**
     * get the data for the current user
     * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
     * </ul>
     * <ul>
     * <li>This is an asynchronous call, it will not return immediately. After get the battery status,
     * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
     * </ul>
     * <ul>
     * <li>The action of the callback is {@link BpProfile#ACTION_HISTORY_DATA_CBP}, the keys of message will show in {@linkplain BpProfile#ACTION_HISTORY_DATA_CBP KeyList of the action}.
     * </li>
     * </ul>
     */
    public void getData(){
        bpInsSet.getData();
    }

    /**
     * disconnect bp device
     */
    @Override
    public void disconnect() {
        mComm.disconnect(mAddress);
    }

    @Override
    public void destroy() {
        if(bpInsSet != null)
            bpInsSet.destroy();
        bpInsSet = null;
        mContext = null;
        mComm = null;
    }
}
