package com.ihealth.communication.control;

import android.content.Context;

import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.A1InsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;

/**
 * Public API for the Bp3m
 * <p> The class provides methods to control Bp3m device.
 * You need to call the device method, and then call the connection method
 * <p> If you want to connect a BP3M device, you need to call{@link iHealthDevicesManager#mBaseCommCallback} to discovery a new BP5 device,
 * and then call{@link iHealthDevicesManager#connectDevice}to connect BP5 device.
 */
public class Bp3mControl implements DeviceControl {

    private static final String TAG = "Bp3mControl";

    private Context mContext;
    private A1InsSet a1InsSet;
    private BaseComm mComm;
    private String mAddress;

    /**
     * a constructor for Bp3mContorl.
     *
     * @param com               class for communication.
     * @param context           Context.
     * @param mac               valid USB address(without colon).
     * @param name              valid USB name.
     * @param mBaseCommCallback communication callback.
     * @param insCallback       Bp5 device callback.
     * @hide
     */
    public Bp3mControl(Context context, BaseComm com, String userName, String mac, String name, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.mComm = com;
        this.mContext = context;
        this.mAddress = mac;
        a1InsSet = new A1InsSet(context, com, userName, mac, name, insCallback, mBaseCommCallback);
    }

    /**
     * Get the Bp3m battery status
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
     * Start On-line measurement
     * <ul>
     * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
     * <li>This is an asynchronous call, it will not return immediately.
     * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
     * <li>The actions of the callback are: <br />
     * <ol>
     * <li>{@link BpProfile#ACTION_ZOREING_BP}</li>
     * <li>{@link BpProfile#ACTION_ZOREOVER_BP}</li>
     * <li>{@link BpProfile#ACTION_ONLINE_PRESSURE_BP}</li>
     * <li>{@link BpProfile#ACTION_ONLINE_PULSEWAVE_BP}</li>
     * <li>{@link BpProfile#ACTION_ONLINE_RESULT_BP}</li>
     * </ol>
     * <li>Thr Keys of return message will show in the KeyList of {@link BpProfile#ACTION_ONLINE_PRESSURE_BP},
     * {@link BpProfile#ACTION_ONLINE_PULSEWAVE_BP} and {@link BpProfile#ACTION_ONLINE_RESULT_BP}.</li>
     * </ul>
     */
    public void startMeasure() {
        a1InsSet.startMeasure();
    }

    /**
     * Interrupt the measurement process immediately
     */
    public void interruptMeasure() {
        a1InsSet.interruptMeasure();
    }

    /**
     * @hide
     */
    @Override
    public void init() {
        a1InsSet.getIdps();
    }

    /**
     * Disconnect the Bp3m
     * <p>
     * When the APP exit or need to disconnect, call the method
     */
    @Override
    public void disconnect() {
    }

    @Override
    public void destroy() {

    }
}
