package com.ihealth.communication.control;

import android.content.Context;

import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.BtmInsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;

/**
 * Created by apple on 1/13/16.
 */
public class BtmControl implements DeviceControl {
    public static final Byte TEMPERATURE_UNIT_C = 0x01;
    public static final Byte TEMPERATURE_UNIT_F = 0x02;
    public static final Byte MEASURING_TARGET_BODY = 0x01;
    public static final Byte MEASURING_TARGET_OBJECT = 0x02;
    public static final Byte FUNCTION_TARGET_ONLINE = 0x01;
    public static final Byte FUNCTION_TARGET_OFFLINE = 0x02;

    private Context mContext;
    private BtmInsSet mBtmInsSet;
    private BaseComm mComm;
    private String mAddress;
    private String mType;
    private BaseCommCallback mBaseCommCallback;

    /**
     * A construct of ABPMControl
     *
     * @param context           Context.
     * @param comm              class for communication.
     * @param userName          the identification of the user, could be the form of email address or mobile phone.
     * @param mac               valid Ble address(without colon).
     * @param insCallback       Po3 device callback.
     * @param mBaseCommCallback class for communication.
     * @hide
     */
    public BtmControl(Context context, BaseComm comm, String userName, String mac, String type, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.mComm = comm;
        this.mContext = context;
        this.mAddress = mac;
        this.mType = type;
        this.mBaseCommCallback = mBaseCommCallback;
        mBtmInsSet = new BtmInsSet(context, comm, userName, mac, type, insCallback, mBaseCommCallback);
    }

    /**
     * Initializes method
     * <ul>
     * <li>
     * Used to create communication channels with BTM.
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
     * Get the BTM battery status
     * <ul>
     * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
     * <li>This is an asynchronous call, it will not return immediately. After get the battery status,
     * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
     * <li>The action of the callback is {@link BtmProfile#BTM_BATTERY}.</li>
     * <li>Thr Keys of message will show in the KeyList of {@link BtmProfile#ACTION_BTM_BATTERY}.</li>
     * <li>
     * Attention, it is mandatory to call following method before you call this method:<br/>
     * {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)}
     * </li>
     * </ul>
     */
    public void getBattery() {
        mBtmInsSet.getBattery();
    }

    /**
     * Set the standby time<br/>
     * <ul>
     * <li>This is an asynchronous call, it will return immediately.</li>
     * <li>If set/unset successfully, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * </li>
     * <li>
     * Attention, it is mandatory to call following method before you call this method:<br/>
     * {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)}
     * </li>
     * </ul>
     *
     * @param hour   Standby time hours.
     * @param minute Standby time minute.
     * @param second Standby time second.
     */
    public void setStandbyTime(int hour, int minute, int second) {
        mBtmInsSet.setStandbyTime(hour, minute, second);
    }

    /**
     * Set the temperature unit<br/>
     * <ul>
     * <li>This is an asynchronous call, it will return immediately.</li>
     * <li>If set/unset successfully, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * </li>
     * <li>
     * Attention, it is mandatory to call following method before you call this method:<br/>
     * {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)}
     * </li>
     * </ul>
     *
     * @param unit temperature unit.
     *             The unit of {@link #TEMPERATURE_UNIT_C} and {@link #TEMPERATURE_UNIT_F}.
     */
    public void setTemperatureUnit(byte unit) {
        mBtmInsSet.setTemperatureUnit(unit);
    }

    /**
     * Set the measuring target<br/>
     * <ul>
     * <li>This is an asynchronous call, it will return immediately.</li>
     * <li>If set/unset successfully, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * </li>
     * <li>
     * Attention, it is mandatory to call following method before you call this method:<br/>
     * {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)}
     * </li>
     * </ul>
     *
     * @param target measuring target.
     *               The target of {@link #MEASURING_TARGET_BODY} and {@link #MEASURING_TARGET_OBJECT}.
     */
    public void setMeasuringTarget(byte target) {
        mBtmInsSet.setMeasuringTarget(target);
    }

    /**
     * Set the offline target<br/>
     * <ul>
     * <li>This is an asynchronous call, it will return immediately.</li>
     * <li>If set/unset successfully, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * </li>
     * <li>
     * Attention, it is mandatory to call following method before you call this method:<br/>
     * {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)}
     * </li>
     * </ul>
     *
     * @param target offline target.
     *               The target of {@link #FUNCTION_TARGET_OFFLINE} and {@link #FUNCTION_TARGET_ONLINE}.
     */
    public void setOfflineTarget(byte target) {
        mBtmInsSet.setOfflineTarget(target);
    }

    /**
     * Get the BTM memory data
     * <ul>
     * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
     * <li>This is an asynchronous call, it will not return immediately. After get the battery status,
     * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
     * <li>The action of the callback is {@linkplain BtmProfile#ACTION_BTM_MEMORY ACTION_BTM_MEMORY}.</li>
     * <li>Thr Keys of message will show in the KeyList of {@linkplain BtmProfile#ACTION_BTM_MEMORY KeyList of the action}.</li>
     * <li>If error happens, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <li>
     * Attention, it is mandatory to call following method before you call this method:<br/>
     * {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)}
     * </li>
     * </ul>
     */
    public void getMemoryData() {
        mBtmInsSet.getMemoryData();
    }

    /**
     * disconnect am device
     */
    @Override
    public void disconnect() {
        mComm.disconnect(mAddress);
    }

    @Override
    public void destroy() {
        if (mBtmInsSet != null)
            mBtmInsSet.destroy();
        mBtmInsSet = null;
        mContext = null;
        mComm = null;
    }

}
