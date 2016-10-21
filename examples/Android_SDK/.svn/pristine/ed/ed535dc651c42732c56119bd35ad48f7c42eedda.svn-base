package com.ihealth.communication.control;

import android.content.Context;

import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.A1InsSetforBp7s;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesIDPS;
import com.ihealth.communication.manager.iHealthDevicesManager;

/**
 * Public API for the BP7S
 * <p> The class provides methods to control BP7S device.
 * You need to call the device method, and then call the connection method
 * <p> If you want to connect a BP7S device, you need to call{@link iHealthDevicesManager#startDiscovery} to discovery a new BP7S device,
 * and then call{@link iHealthDevicesManager#connectDevice}to connect BP7S device.
 */
public class Bp7sControl implements DeviceControl {

    private static final String TAG = "Bp7sControl";

    private Context mContext;
    private A1InsSetforBp7s a1InsSet;
    private BaseComm mComm;
    private String mAddress;

    /**
     * a constructor for Bp7SContorl.
     *
     * @param com               class for communication.
     * @param context           Context.
     * @param mac               valid Bluetooth address(without colon).
     * @param name              valid Bluetooth name.
     * @param mBaseCommCallback communication callback.
     * @param insCallback       Bp7S device callback.
     * @hide
     */
    public Bp7sControl(Context context, BaseComm com, String userName, String mac, String name, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.mComm = com;
        this.mContext = context;
        this.mAddress = mac;
        a1InsSet = new A1InsSetforBp7s(context, com, userName, mac, name, insCallback, mBaseCommCallback);
    }

    /**
     * Get the BP7S information
     * <ul>
     * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
     * <li>This is an asynchronous call, it will not return immediately. After get the battery status,
     * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
     * <li>The action of the callback is {@link iHealthDevicesIDPS#MSG_IHEALTH_DEVICE_IDPS}.</li>
     * <li>Thr Keys of message will show in the KeyList of {@link iHealthDevicesIDPS#MSG_IHEALTH_DEVICE_IDPS}.</li>
     * </ul>
     */
    public String getIdps() {
        return iHealthDevicesManager.getInstance().getDevicesIDPS(mAddress);
    }

    /**
     * Get the BP7S battery status
     * <ul>
     * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
     * <li>This is an asynchronous call, it will not return immediately. After get the battery status,
     * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
     * <li>The action of the callback is {@link BpProfile#ACTION_BATTERY_BP}.</li>
     * <li>Thr Keys of message will show in the KeyList of {@link BpProfile#ACTION_BATTERY_BP}.</li>
     * </ul>
     */
    public void getBattery() {
        a1InsSet.getBatteryLevel();
    }

    /**
     * Get the number of historical data in the BP7S
     * <ul>
     * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
     * <li>This is an asynchronous call, it will not return immediately.
     * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
     * <li>The action of the callback is {@link BpProfile#ACTION_HISTORICAL_NUM_BP}.</li>
     * <li>Thr Keys of return message will show in the KeyList of {@link BpProfile#ACTION_HISTORICAL_NUM_BP}.</li>
     * </ul>
     */
    public void getOfflineNum() {
        a1InsSet.getOfflineData = false;
        a1InsSet.setMemory_Size(1);
        a1InsSet.getOffLineDataNum();
    }

    /**
     * Get the value of historical data in the BP7S
     * <ul>
     * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
     * <li>This is an asynchronous call, it will not return immediately.
     * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
     * <li>The action of the callback is {@link BpProfile#ACTION_HISTORICAL_DATA_BP}.</li>
     * <li>Thr Keys of return message will show in the KeyList of {@link BpProfile#ACTION_HISTORICAL_DATA_BP}.</li>
     * </ul>
     */
    public void getOfflineData() {
        a1InsSet.getOfflineData = true;
        a1InsSet.setMemory_Size(1);
        a1InsSet.getOffLineDataNum();
    }

    /**
     * set the unit of measuring.
     * <ul>
     * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
     * <li>This is an asynchronous call, it will not return immediately.
     * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
     * <li>The action of the callback is {@link BpProfile#ACTION_SET_UNIT_SUCCESS_BP}.</li>
     * <li>Thr Keys of return message will show in the KeyList of {@link BpProfile#ACTION_SET_UNIT_SUCCESS_BP}.</li>
     * </ul>
     *
     * @param unit :0 mmHg;1 kPa
     */
    public void setUnit(int unit) {
        a1InsSet.setUnit(unit);
    }

    /**
     * set the angle of measuring.
     * <ul>
     * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
     * <li>This is an asynchronous call, it will not return immediately.
     * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
     * <li>The action of the callback is {@link BpProfile#ACTION_SET_ANGLE_SUCCESS_BP}.</li>
     * <li>Thr Keys of return message will show in the KeyList of {@link BpProfile#ACTION_SET_ANGLE_SUCCESS_BP}.</li>
     * </ul>
     *
     * @param leftUpper  the maximum angle of left hand
     * @param leftLow    the minimum angle of left hand
     * @param rightUpper the maximum angle of left hand
     * @param rightLow   the minimum angle of right hand
     */
    public void angleSet(byte leftUpper, byte leftLow, byte rightUpper, byte rightLow) {
        a1InsSet.angleSet(leftUpper, leftLow, rightUpper, rightLow);
    }

    /**
     * sync real time:Synchronize the system time to BP device.
     * <ul>
     * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
     * <li>This is an asynchronous call, it will not return immediately.
     * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
     * <li>The action of the callback is {@link BpProfile#ACTION_FUNCTION_INFORMATION_BP}.</li>
     * <li>Thr Keys of return message will show in the KeyList of {@link BpProfile#ACTION_FUNCTION_INFORMATION_BP}.</li>
     * </ul>
     */
    public void getFunctionInfo() {
        a1InsSet.getFunctionInfo();
    }

    /**
     * @hide
     */
    @Override
    public void init() {
        a1InsSet.getIdps();
    }

    /**
     * Disconnect the BP7s
     */
    @Override
    public void disconnect() {
        mComm.disconnect();
    }

    @Override
    public void destroy() {
        if (a1InsSet != null)
            a1InsSet.destroy();
        a1InsSet = null;
        mContext = null;
        mComm = null;
    }

}
