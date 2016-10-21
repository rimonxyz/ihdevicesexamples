package com.ihealth.communication.control;

import android.content.Context;

import com.ihealth.communication.base.ble.BleComm;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.BgInsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;

/**
 * Created by jing on 16/8/30.
 */
public class BgControl implements DeviceControl{


    final private String TAG = this.getClass().getName();
    private BaseComm mComm;
    private Context mContext;
    private String mAddress;
    private String mType;

    private BgInsSet bgInsSet;
    private BaseCommCallback mBaseCommCallback;



    public BgControl(Context context, BaseComm mBaseComm, BleComm bleComm, String userName, String mac, String type, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.mComm = mBaseComm;
        this.mContext = context;
        this.mAddress = mac;
        this.mType = type;

        this.mBaseCommCallback = mBaseCommCallback;

        bgInsSet = new BgInsSet(context,mBaseComm,bleComm,userName,mac,type,insCallback,mBaseCommCallback);
    }



    @Override
    public void init() {
        this.mBaseCommCallback.onConnectionStateChange(mAddress, mType, iHealthDevicesManager.DEVICE_STATE_CONNECTED, 0, null);

    }


    /**
     * get battery for Continua Bg device
     * <ul>
     * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
     * </ul>
     * <ul>
     * <li>This is an asynchronous call, it will not return immediately. After get the battery status,
     * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
     * </ul>
     * <ul>
     * <li>The action of the callback is {@link BgProfile#ACTION_BATTERY_CBG}, the keys of message will show in {@linkplain BgProfile#ACTION_BATTERY_CBG KeyList of the action}.
     * </li>
     * </ul>
     */
    public void getBattery(){
        bgInsSet.getBattery();
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
     * <li>The action of the callback is {@link BgProfile#ACTION_FEATURE_CBG}, the keys of message will show in {@linkplain BgProfile#ACTION_FEATURE_CBG KeyList of the action}.
     * </li>
     * </ul>
     */
    public void getFeature(){
        bgInsSet.getFeature();
    }


    /**
     * get measurement result for Continua Bg device
     * <ul>
     * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
     * </ul>
     * <ul>
     * <li>This is an asynchronous call, it will not return immediately. After get the measurement result,
     * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
     * </ul>
     * <ul>
     * <li>The action of the callback is {@link BgProfile#ACTION_MEASUREMENT_RESULT_CBG}, the keys of message will show in {@linkplain BgProfile#ACTION_MEASUREMENT_RESULT_CBG KeyList of the action}.
     * </li>
     * </ul>
     */
    public void getMeasurement(){
        bgInsSet.getMeasurement();
    }

    /**
     * get measurement context
     */
    /**
     * get measurement context  for Continua Bg device
     * <ul>
     * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
     * </ul>
     * <ul>
     * <li>This is an asynchronous call, it will not return immediately. After get the measurement result,
     * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
     * </ul>
     * <ul>
     * <li>The action of the callback is {@link BgProfile#ACTION_MEASUREMENT_CONTEXT_CBG}, the keys of message will show in {@linkplain BgProfile#ACTION_MEASUREMENT_CONTEXT_CBG KeyList of the action}.
     * </li>
     * </ul>
     */
    public void getMeasurementContext(){
        bgInsSet.getMeasurementContext();
    }

    /**
     * get measurement data
     */
    public void getRecord(){
        bgInsSet.getRecord();
    }



    @Override
    public void disconnect() {
        mComm.disconnect(mAddress);
    }

    @Override
    public void destroy() {
        if(bgInsSet != null)
            bgInsSet.destroy();
        bgInsSet = null;
        mContext = null;
        mComm = null;
    }
}
