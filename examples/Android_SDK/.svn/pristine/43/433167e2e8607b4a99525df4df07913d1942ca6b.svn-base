
package com.ihealth.communication.control;

import android.content.Context;

import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.ins.AcInsSet;
import com.ihealth.communication.ins.F0InsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.utils.FirmWare;

import java.util.List;

/**
 * Public API for the PO3
 * <p>
 * The class provides methods to control PO3 device. You need to call the device method, and then
 * call the connection method
 * <p>
 * If you want to connect a PO3 device, you need to call
 * {@link iHealthDevicesManager#startDiscovery} to discovery a new PO3 device, and then call
 * {@link iHealthDevicesManager#connectDevice}to connect PO3 device.
 */
public class Po3Control implements DeviceControl {

    private BaseComm mComm;
    private AcInsSet mAcInsSet;
    private F0InsSet mf0InsSet;
    private String mAddress;
    private String username;
    private String currentUpgradeDevice = null;

    /**
     * A construct of Po3Control
     *
     * @param username         the identification of the user, could be the form of email address or mobile phone.
     * @param context          Context.
     * @param accessoryFirm    Accessory Firmware Version, get from IDPS.
     * @param mBaseComm        class for communication.
     * @param mac              valid Ble address(without colon).
     * @param type             valid Ble type.
     * @param baseCommCallback communication callback.
     * @param insCallback      Po3 device callback.
     * @hide
     */
    public Po3Control(String username, Context context, String accessoryFirm, BaseComm mBaseComm, String mac,
                      String type,
                      BaseCommCallback baseCommCallback,
                      InsCallback insCallback) {
        this.username = username;
        this.mComm = mBaseComm;
        this.mAddress = mac;
        mAcInsSet = new AcInsSet(username, context, mBaseComm, mac, accessoryFirm, type, baseCommCallback, insCallback);
        BaseCommProtocol baseCommProtocol = mAcInsSet.getBaseCommProtocol();
        mf0InsSet = new F0InsSet(mBaseComm, baseCommProtocol, context, mac, type, insCallback);
    }

    /**
     * Initializes method
     * <ul>
     * <li>
     * Used to send random group of R1, identify the connection device.
     * </li>
     * <li>
     * Attention, this method calls after creating the Po3Control object.<br/>
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
        mAcInsSet.identify();
    }

    /**
     * Disconnect the PO3
     * <p>
     * When the APP exit or need to disconnect, call the method
     */
    @Override
    public void disconnect() {
        mComm.disconnect(mAddress);
    }

    /**
     * Get the PO3 battery status
     * <ul>
     * <li>
     * This is an asynchronous call, it will not return immediately.
     * </li>
     * <li>
     * After getting the activity data, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <ul>
     * <li>
     * The action will be {@linkplain PoProfile#ACTION_BATTERY_PO ACTION_BATTERY_PO}
     * </li>
     * <li>
     * The keys of message will show in the <u>{@linkplain PoProfile#ACTION_BATTERY_PO KeyList of the action}</u>.
     * </li>
     * </ul>
     * </li>
     * <li>
     * Attention, it is mandatory to call following method before you call this method:<br/>
     * {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)}
     * </li>
     * </ul>
     */
    public void getBattery() {
        mAcInsSet.c1Ins();
    }

    /**
     * Start real-time measurement
     * <ul>
     * <li>
     * This is an asynchronous call, it will not return immediately.
     * </li>
     * <li>
     * After getting the activity data, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <ul>
     * <li>
     * The action will be {@linkplain PoProfile#ACTION_LIVEDA_PO ACTION_LIVEDA_PO}
     * and {@linkplain PoProfile#ACTION_RESULTDATA_PO ACTION_RESULTDATA_PO}
     * </li>
     * <li>
     * The keys of message will show in the <u>{@linkplain PoProfile#ACTION_LIVEDA_PO KeyList of the live action}</u>
     * and <u>{@linkplain PoProfile#ACTION_RESULTDATA_PO KeyList of the result action}</u>.
     * </li>
     * </ul>
     * </li>
     * <li>
     * Attention, it is mandatory to call following method before you call this method:<br/>
     * {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)}
     * </li>
     * </ul>
     */
    public void startMeasure() {
        mAcInsSet.a2Ins();
    }
    /**
     * Get the value of historical data in the PO3
     * <ul>
     * <li>
     * This is an asynchronous call, it will not return immediately.
     * </li>
     * <li>
     * After getting the activity data, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <ul>
     * <li>
     * The action will be {@linkplain PoProfile#ACTION_OFFLINEDATA_PO ACTION_OFFLINEDATA_PO}
     * and {@linkplain PoProfile#ACTION_NO_OFFLINEDATA_PO ACTION_NO_OFFLINEDATA_PO}
     * </li>
     * <li>
     * The keys of message will show in the <u>{@linkplain PoProfile#ACTION_OFFLINEDATA_PO KeyList of the action}</u>
     * and <u>{@linkplain PoProfile#ACTION_NO_OFFLINEDATA_PO KeyList of the no result action}</u>.
     * </li>
     * </ul>
     * </li>
     * <li>
     * Attention, it is mandatory to call following method before you call this method:<br/>
     * {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)}
     * </li>
     * </ul>
     */
    public void getHistoryData() {
        mAcInsSet.a9Ins();
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
            mAcInsSet.getBaseCommProtocol().setInsSet(mf0InsSet);

            //设置当前为自升级状态
            mf0InsSet.setCurrentState(mAddress, true);
        }

        @Override
        public void returnComm() {
            mf0InsSet.getBaseCommProtocol().setInsSet(mAcInsSet);

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
        if (mAcInsSet != null)
            mAcInsSet.destroy();
        mAcInsSet = null;
        if (mf0InsSet != null)
            mf0InsSet.destroy();
        mf0InsSet = null;
        mComm = null;

    }
}
