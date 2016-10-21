package com.ihealth.communication.control;

import android.content.Context;

import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.A1InsSetforBp7s;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;

/**
 * Public API for the KD723
 * <p> The class provides methods to control KD723 device.
 * You need to call the device method, and then call the connection method
 * <p> If you want to connect a KD926 device, you need to call{@link iHealthDevicesManager#startDiscovery} to discovery a new KD723 device,
 * and then call{@link iHealthDevicesManager#connectDevice}to connect KD723 device.
 */
public class Bp723Control extends Bp926Control implements DeviceControl {

    private static final String TAG = "Bp723Control";

    private Context mContext;
    private A1InsSetforBp7s a1InsSet;
    private BaseComm mComm;
    private String mAddress;

    /**
     * a constructor for Bp723Control.
     *
     * @param com               class for communication.
     * @param context           Context.
     * @param mac               valid Bluetooth address(without colon).
     * @param name              valid Bluetooth name.
     * @param mBaseCommCallback communication callback.
     * @param insCallback       Bp723 device callback.
     * @hide
     */
    public Bp723Control(Context context, BaseComm com, String userName, String mac, String name, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        super(context, com, userName, mac, name, insCallback, mBaseCommCallback);
    }

}
