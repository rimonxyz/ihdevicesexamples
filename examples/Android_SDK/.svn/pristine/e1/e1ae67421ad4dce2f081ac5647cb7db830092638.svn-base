
package com.ihealth.communication.manager;

import android.bluetooth.BluetoothGatt;

import java.util.Map;

/**
 * This abstract class is used to implement iHealth device callback
 */
public abstract class iHealthDevicesCallback {

    /**
     * Callback indicating when a iHealth device has found.
     * @param mac iHealth device mac
     * @param deviceType iHealth device type
     * @param rssi The signal strength of iHealth device    eg. -60
     */
    public void onScanDevice(String mac, String deviceType, int rssi) {}

    /**
     * Callback indicating when a iHealth device has found.
     * @param mac iHealth device mac
     * @param deviceType iHealth device type
     * @param rssi The signal strength of iHealth device    eg. -60
     * @param manufactorData Additional information for iHealth device   eg.  {serialnumber=EE00352E, modenumber=KD-723 GG006}
     */
    public void onScanDevice(String mac, String deviceType, int rssi, Map<String, Object> manufactorData) {}
    
    /**
     * Callback indicating the Discovery finish.
     */
    public void onScanFinish(){}

    /**
     * Callback indicating when a iHealth device has connected/disconnect/connecting/reconnecting.
     * 
     * @param mac iHealth device mac
     * @param deviceType iHealth device type
     * @param status status of the connecting, connected, disconnect  or reconnecting operation
     *            {@link iHealthDevicesManager#DEVICE_STATE_CONNECTING}
     *            {@link iHealthDevicesManager#DEVICE_STATE_CONNECTIONFAIL}
     *            {@link iHealthDevicesManager#DEVICE_STATE_CONNECTED}
     *            {@link iHealthDevicesManager#DEVICE_STATE_DISCONNECTED}
     *            {@link iHealthDevicesManager#DEVICE_STATE_RECONNECTING}
     * @param errorID The error of connect fail.
     *                For BT3.0, errorID: 1, you have to pair iHealth device with your phone on bluetooth page manually.
     *                For BLE, errorID: returned by android os api {@link android.bluetooth.BluetoothGattCallback#onConnectionStateChange(BluetoothGatt, int, int)}.
     */
    public void onDeviceConnectionStateChange(String mac, String deviceType, int status, int errorID) {}

    /**
     * Callback indicating when a iHealth device has connected/disconnect/connecting/reconnecting.
     *
     * @param mac iHealth device mac
     * @param deviceType iHealth device type
     * @param status status of the connecting, connected, disconnect  or reconnecting operation
     *            {@link iHealthDevicesManager#DEVICE_STATE_CONNECTING}
     *            {@link iHealthDevicesManager#DEVICE_STATE_CONNECTIONFAIL}
     *            {@link iHealthDevicesManager#DEVICE_STATE_CONNECTED}
     *            {@link iHealthDevicesManager#DEVICE_STATE_DISCONNECTED}
     *            {@link iHealthDevicesManager#DEVICE_STATE_RECONNECTING}
     * @param errorID The error of connect fail.
     * @param manufactorData manufactorData Additional information for iHealth device   eg.  {subtype=abi, position=arm}.
     *                       Attention: If (subtype=abi && position=unknown) , please charge the device fully， then tap the button for 10 seconds to reset the device.
     */
    public void onDeviceConnectionStateChange(String mac, String deviceType, int status, int errorID, Map manufactorData) {}
    /**
     * Callback reporting the result of user status. 
     * 
     * @param username iHealth username
     * @param userStatus user status:
     *  <p>1 New-user registration succeeded.
     *  <p>2 User login succeeded.
     *  <p>3 The user is iHealth user as well, measurement via SDK has been activated, and the data from the measurement belongs to the user.
     *  <p>4 Testing without Internet connection succeeded.
     *  <p>5 Userid/clientID/clientSecret verification failed.
     *  <p>6 SDK has not been authorized.
     *  <p>7 User has not been authorized.
     *  <p>8 Internet error, verification failed.
     *  <p>PS: The measurement via SDK will be operated in the case of 1-4, and will be terminated if any of 5-8 occurs.
     *      The interface needs to be re-called after analyzing the return parameters.
     *      SDK application requires Internet connection; there is 10-day tryout if SDK cannot connect Internet,
     *      SDK is fully functional during tryout period,but will be terminated without verification through Internet after 10 days.
     *
     *
     */
    public void onUserStatus(String username, int userStatus){}
   
    /**
     * Callback triggered as a result of a remote device message notification.
     * 
     * @param mac iHealth device mac
     * @param deviceType iHealth type
     * @param action notification event
     * @param message notification message
     */
    public void onDeviceNotify(String mac, String deviceType, String action, String message) {}
}
