package com.ihealth.communication.control;

import android.content.Context;

import com.ihealth.communication.base.ble.BleComm;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.HsInsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;

/**
 * Created by jing on 16/8/30.
 */
public class HsControl implements DeviceControl {

    final private String TAG = this.getClass().getName();
    private BaseComm mComm;
    private Context mContext;
    private String mAddress;
    private String mType;

    private HsInsSet hsInsSet;
    private BaseCommCallback mBaseCommCallback;

    /**
     * the construct of Hs3Control.
     *
     * @param context           Context.
     * @param mBaseComm         a class of communication.
     * @param bleComm           a class of communication.
     * @param userName          user identify card
     * @param mac               valid Bluetooth address(without colon).
     * @param type              type of iHealth device {@link iHealthDevicesManager#TYPE_CHS}
     * @param insCallback       Continua HS device callback
     * @param mBaseCommCallback communication callback
     * @hide
     */
    public HsControl(Context context, BaseComm mBaseComm, BleComm bleComm, String userName, String mac, String type, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.mComm = mBaseComm;
        this.mContext = context;
        this.mAddress = mac;
        this.mType = type;

        this.mBaseCommCallback = mBaseCommCallback;

        hsInsSet = new HsInsSet(context, mBaseComm, bleComm, userName, mac, type, insCallback, mBaseCommCallback);
    }


    /**
     * Initializes method
     * <ul>
     * <li>
     * Used to create communication channels with CHS.
     * </li>
     * <li>
     * Attention, this method calls after creating the Hs3Control object.<br/>
     * </li>
     * <li>
     * In {@link iHealthDevicesManager#createControl(BaseComm btct, String mac, String name, boolean needReadEE)}
     * method is invoked for the first time.
     * </li>
     * </ul>
     *
     * @hide
     */
    @Override
    public void init() {
        this.mBaseCommCallback.onConnectionStateChange(mAddress, mType, iHealthDevicesManager.DEVICE_STATE_CONNECTED, 0, null);
    }

    /**
     * Get battery for Continua HS device
     * <ul>
     * <li>
     * This is an asynchronous call, it will not return immediately.
     * </li>
     * <li>
     * After getting the activity data, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <ul>
     * <li>
     * The action will be {@linkplain HsProfile#ACTION_BATTERY_CHS ACTION_BATTERY_CHS}.
     * </li>
     * <li>
     * The keys of message will show in the <u>{@linkplain HsProfile#ACTION_BATTERY_CHS KeyList of the action}</u>.
     * </li>
     * </ul>
     * <li>
     * Attention, it is mandatory to call following method before you call this method:<br/>
     * {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)}
     * </li>
     * </ul>
     */
    public void getBattery(){
        hsInsSet.getBattery();
    }

    /**
     * get the feature for Continua HS device
     * <ul>
     * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
     * </ul>
     * <ul>
     * <li>This is an asynchronous call, it will not return immediately. After get the battery status,
     * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
     * </ul>
     * <ul>
     * <li>The action of the callback is {@link HsProfile#ACTION_FEATURE_CBG}, the keys of message will show in {@linkplain HsProfile#ACTION_FEATURE_CBG KeyList of the action}.
     * </li>
     * </ul>
     */
    public void getFeature() {
        hsInsSet.getFeature();
    }

    /**
     * get measurement result for Continua HS device
     * <ul>
     * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
     * </ul>
     * <ul>
     * <li>This is an asynchronous call, it will not return immediately. After get the measurement result,
     * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
     * </ul>
     * <ul>
     * <li>The action of the callback is {@link HsProfile#ACTION_CHS_MEASUREMENT_DATA}, the keys of message will show in {@linkplain HsProfile#ACTION_CHS_MEASUREMENT_DATA KeyList of the action}.
     * </li>
     * </ul>
     */
    public void getMeasurement() {
        hsInsSet.getMeasurement();
    }

    @Override
    public void disconnect() {
        mComm.disconnect(mAddress);
    }

    @Override
    public void destroy() {
        if (hsInsSet != null)
            hsInsSet.destroy();
        hsInsSet = null;
        mContext = null;
        mComm = null;
    }
}
