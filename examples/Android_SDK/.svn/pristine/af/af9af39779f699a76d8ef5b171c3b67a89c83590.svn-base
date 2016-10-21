/**
 * @title
 * @Description
 * @author
 * @date 2015年5月21日 上午11:39:03
 * @version V1.0
 */

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
 * Public API for the BP550BT
 * <p> The class provides methods to control BP550BT device.
 * You need to call the device method, and then call the connection method
 * <p> If you want to connect a BP550BT device, you need to call{@link iHealthDevicesManager#startDiscovery} to discovery a new BP550BT device,
 * and then call{@link iHealthDevicesManager#connectDevice}to connect BP550BT device.
 */
public class Bp550BTControl implements DeviceControl {
    private Context mContext;
    private A1InsSetforBp7s a1InsSet;
    private BaseComm mComm;
    private String mAddress;

    /**
     * a constructor for Bp550BTContorl.
     *
     * @param com               class for communication.
     * @param context           Context.
     * @param mac               valid Bluetooth address(without colon).
     * @param name              valid Bluetooth name.
     * @param mBaseCommCallback communication callback.
     * @param insCallback       Bp550BT device callback.
     * @hide
     */
    public Bp550BTControl(Context context, BaseComm com, String userName, String mac, String name, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.mComm = com;
        this.mContext = context;
        this.mAddress = mac;
        a1InsSet = new A1InsSetforBp7s(context, com, userName, mac, name, insCallback, mBaseCommCallback);
    }

    /**
     * Get the BP550BT information
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
     * Get the BP550BT battery status
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
     * Get the number of historical data in the BP550BT
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
     * Get the value of historical data in the BP550BT
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
        a1InsSet.identify();
    }

    /**
     * Disconnect the BP550BT
     */
    @Override
    public void disconnect() {
        mComm.disconnect(mAddress);
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
