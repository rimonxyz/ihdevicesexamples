package com.ihealth.communication.base.comm;

import java.util.Map;

/**
 * Callback interface used to deliver LE scan results.
 * @hide
 */
public abstract class BaseCommCallback {

    /**
     * Callback reporting an LE device
     * @param mac The mac for the remote device.
     * @param type The type for the remote device.
     * @hide
     */
    public void onScanDevice(String mac, String type) {
    }

    /**
     * Callback indicating when a device has connected/connecting/disconnect.
     *
     * @param mac The mac for the remote device.
     * @param type The type for the remote device.
     * @param status Status of the connect, connecting or disconnect operation.
     * @param errorID The error of connect fail.
     * @param manufactorData Additional information for iHealth device
     * @hide
     */
    public void onConnectionStateChange(String mac, String type, int status, int errorID, Map manufactorData) {
    }

}
