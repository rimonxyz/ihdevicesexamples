
package com.ihealth.communication.control;

import android.content.Context;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.ins.A6InsSet;
import com.ihealth.communication.ins.F0InsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.utils.FirmWare;

import java.util.List;

/**
 * Public API for the HS4 bluetooth
 * <p>
 * The class provides methods to control HS4 device. You need to call the device method, and then
 * call the connection method
 * <p>
 * If you want to connect a HS4 device, you need to call
 * {@link iHealthDevicesManager#startDiscovery} to discovery a new HS4 device, and then call
 * {@link iHealthDevicesManager#connectDevice}to connect HS4 device.
 */
public class Hs4Control implements DeviceControl {

    private BaseComm mComm;
    private A6InsSet mA6InsSet;
    private F0InsSet mf0InsSet;
    private String mAddress;
    private String userName;
    private String currentUpgradeDevice = null;

    /**
     * construct of Hs4Control
     * 
     * @param mBaseComm a class of communication
     * @param mac valid bluetooth address (without colon)
     * @param type type of ihealth device {@link iHealthDevicesManager#TYPE_HS4}
     * @param baseCommCallback communication callback
     * @param insCallback device callback
     * @hide
     */
    public Hs4Control(String userName, Context context, BaseComm mBaseComm, String mac,
            String type, BaseCommCallback baseCommCallback, InsCallback insCallback) {
        this.mComm = mBaseComm;
        this.mAddress = mac;
        this.userName = userName;
        mA6InsSet = new A6InsSet(userName, context, mBaseComm, mac, type, baseCommCallback, insCallback);
        BaseCommProtocol baseCommProtocol = mA6InsSet.getBaseCommProtocol();
        mf0InsSet = new F0InsSet(mBaseComm, baseCommProtocol, context, mac, type, insCallback);
    }

    /**
     * Get the value of historical data in the Hs4
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
        mA6InsSet.stopLink(A6InsSet.MEASURETYPE_OFFLINE);
    }

    /**
     * Create measure connection
     * <ul>
     * <li>
     * This is an asynchronous call, it will not return immediately.
     * </li>
     * <li>
     * After getting the activity data, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <ul>
     * <li>
     * The action will be {@linkplain HsProfile#ACTION_LIVEDATA_HS ACTION_LIVEDATA_HS}
     * and {@linkplain HsProfile#ACTION_ONLINE_RESULT_HS ACTION_ONLINE_RESULT_HS}
     * </li>
     * <li>
     * The keys of message will show in the <u>{@linkplain HsProfile#ACTION_LIVEDATA_HS KeyList of the live action}</u>
     * and <u>{@linkplain HsProfile#ACTION_ONLINE_RESULT_HS KeyList of the result action}</u>.
     * </li>
     * </ul>
     * </li>
     * <li>
     * Attention, it is mandatory to call following method before you call this method:<br/>
     * {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)}
     * </li>
     * </ul>
     *
     * @param unit 1 kg;2 lb; 3 st
     * @param userId user identify number
     */
    public void measureOnline(int unit, int userId) {
        mA6InsSet.setUnitAndUserId(unit, userId);
        mA6InsSet.stopLink(A6InsSet.MEASURETYPE_ONLINE);
    }

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
     * In {@link iHealthDevicesManager#identify(String, String)} method is invoked for the first time.
     * </li>
     * </ul>
     *
     * @hide
     */
    @Override
    public void init() {
        mA6InsSet.identify();
    }

    /**
     * Disconnect the device
     * <p>
     * When the APP exit or need to disconnect, call the method
     */
    @Override
    public void disconnect() {
        mComm.disconnect(mAddress);
    }

    private UpDeviceControl mUpDeviceControl = new UpDeviceControl() {
        @Override
        public void setInformation(List<Byte> list) {
            mf0InsSet.setInfo(list);
        }

        @Override
        public void setData(FirmWare firmware, List<byte[]> list) {
            mf0InsSet.setFirmWare(firmware, list);
        }

        @Override
        public void startUpgrade() {
            mf0InsSet.startUpdate();
        }

        @Override
        public void stopUpgrade() {
            mf0InsSet.stopUpdate();
        }

        @Override
        public void borrowComm() {
            mA6InsSet.getBaseCommProtocol().setInsSet(mf0InsSet);

            //设置当前为自升级状态
            mf0InsSet.setCurrentState(mAddress, true);
        }

        @Override
        public void returnComm() {
            mf0InsSet.getBaseCommProtocol().setInsSet(mA6InsSet);

            //设置当前不是自升级状态
            mf0InsSet.setCurrentState(mAddress, false);
        }

        @Override
        public void judgUpgrade() {
            mf0InsSet.queryInformation();
        }

        @Override
        public boolean isUpgradeState() {
            return mf0InsSet.getCurrentState(mAddress);
        }

        @Override
        public void setCurrentMac(String mac) {
            currentUpgradeDevice = mac;
        }

        @Override
        public String getCurrentMac() {
            return currentUpgradeDevice;
        }
    };

    /**
     * Get UpDeviceControl's implementation. <br/>
     * We recommend not use this method,
     * use {@linkplain com.ihealth.communication.manager.iHealthDevicesUpgradeManager iHealthDevicesUpgradeManager} instead.
     * @see com.ihealth.communication.manager.iHealthDevicesUpgradeManager
     * @return UpDeviceControl's implementation
     */
    @Deprecated
    public UpDeviceControl getUpDeviceControl() {
        return mUpDeviceControl;
    }

    @Override
    public void destroy() {
        if(mA6InsSet != null)
            mA6InsSet.destroy();
        mA6InsSet = null;
        if(mf0InsSet != null)
            mf0InsSet.destroy();
        mf0InsSet = null;
        mComm = null;
    }
}
