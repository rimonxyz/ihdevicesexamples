
package com.ihealth.communication.control;

import android.content.Context;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.Hs3InsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;

/**
 * Public API for the HS3 bluetooth
 * <p>
 * The class provides methods to control HS3 device. You need to call the device method, and then
 * call the connection method
 * <p>
 * If you want to connect a HS3 device, you need to call
 * {@link iHealthDevicesManager#startDiscovery} to discovery a new HS3 device, and then call
 * {@link iHealthDevicesManager#connectDevice}to connect HS3 device.
 */
public class Hs3Control implements DeviceControl {

    private static final String TAG = "Hs3Control";
    protected Context mContext;
    protected Hs3InsSet hs3InsSet;
    private BaseComm mCom;
    private String username;

    /**
     * the construct of Hs3Control.
     * 
     * @param com a class of communication.
     * @param context Context.
     * @param deviceMac valid Bluetooth address(without colon).
     * @param type type of iHealth device {@link iHealthDevicesManager#TYPE_HS3}
     * @param userName  user identify card
     * @param baseCommCallback communication callback
     * @param insCallback HS3 device callback
     * @hide
     */
    public Hs3Control(String userName, BaseComm com, Context context, String deviceMac, String type,
            BaseCommCallback baseCommCallback, InsCallback insCallback) {
        this.mCom = com;
        this.mContext = context;
        this.username = userName;
        hs3InsSet = new Hs3InsSet(userName, com, context, deviceMac, type, baseCommCallback, insCallback);
    }

    // public void syncTime() {
    // hs3InsSet.syncTime();
    // }

    /**
     * Initializes method
     * <ul>
     * <li>
     * Used to create communication channels with HS3.
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
        hs3InsSet.createChannel();
    }

    /**
     * Get the value of historical data in the Hs3
     * <ul>
     * <li>
     * This is an asynchronous call, it will not return immediately.
     * </li>
     * <li>
     * After getting the activity data, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <ul>
     * <li>
     * The action will be {@linkplain HsProfile#ACTION_HISTORICAL_DATA_HS ACTION_HISTORICAL_DATA_HS}.
     * </li>
     * <li>
     * The keys of message will show in the <u>{@linkplain HsProfile#ACTION_HISTORICAL_DATA_HS KeyList of the action}</u>.
     * </li>
     * </ul>
     * <li>
     * Attention, it is mandatory to call following method before you call this method:<br/>
     * {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)}
     * </li>
     * </ul>
     */
    public void getOfflineData() {
        hs3InsSet.getOffLineDataNum();
    }

    /**
     * Disconnect the device
     * <p>
     * When the APP exit or need to disconnect, call the method
     */
    @Override
    public void disconnect() {
        mCom.disconnect();
    }

    @Override
    public void destroy() {
        if(hs3InsSet != null)
            hs3InsSet.destroy();
        hs3InsSet = null;
        mCom = null;
        mContext = null;
    }

}
