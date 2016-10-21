package com.ihealth.communication.control;

import android.content.Context;

import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.A1InsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;

public class Bp3lControl implements DeviceControl{

	private static final String TAG = "Bp3lControl";

	private Context mContext;						
	private A1InsSet a1InsSet;					
	private BaseComm mComm;							
	private String mAddress;
	
	/**
	 * A constructor for Bp3lContorl.
	 * @param com class for communication.
	 * @param context Context.
	 * @param mac  valid Ble address(without colon). 
	 * @param name valid Ble name.
	 * @param mBaseCommCallback communication callback.
	 * @param insCallback Bp5 device callback.
	 * @hide
	 */
	public Bp3lControl(Context context, BaseComm com, String userName, String mac, String name, BaseCommCallback mBaseCommCallback, InsCallback insCallback){
		this.mComm = com;
		this.mContext = context;
		this.mAddress = mac;
		a1InsSet = new A1InsSet(context, com, userName, mac, name, insCallback, mBaseCommCallback);
	}
	
	/**
	 * Get the Bp3l battery status
	 * <ul>
	 * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
	 * <li>This is an asynchronous call, it will not return immediately. After get the battery status,
	 * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
	 * <li>The action of the callback is {@link BpProfile#ACTION_BATTERY_BP}.</li>
	 * <li>Thr Keys of message will show in the KeyList of {@link BpProfile#ACTION_BATTERY_BP}.</li>
	 * </ul>
	 *
	 */
	public void getBattery(){
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
	 *     <li>{@link BpProfile#ACTION_ZOREING_BP}</li>
	 *     <li>{@link BpProfile#ACTION_ZOREOVER_BP}</li>
	 *     <li>{@link BpProfile#ACTION_ONLINE_PRESSURE_BP}</li>
	 *     <li>{@link BpProfile#ACTION_ONLINE_PULSEWAVE_BP}</li>
	 *     <li>{@link BpProfile#ACTION_ONLINE_RESULT_BP}</li>
	 * </ol>
	 * <li>Thr Keys of return message will show in the KeyList of {@link BpProfile#ACTION_ONLINE_PRESSURE_BP},
	 * {@link BpProfile#ACTION_ONLINE_PULSEWAVE_BP} and {@link BpProfile#ACTION_ONLINE_RESULT_BP}.</li>
	 * </ul>
	 */
	public void startMeasure() {
		a1InsSet.startMeasure();
	}
	
	/**
	 *  Interrupt the measurement process immediately
	 */
	public void interruptMeasure(){
		a1InsSet.interruptMeasure();
	}
	
	/**
	 * @hide
	 */
	@Override
	public void init() {
		a1InsSet.identify();
	}

	/**
	 * Disconnect the Bp3l
	 */
	@Override
	public void disconnect() {
		mComm.disconnect(mAddress);
	}

	@Override
	public void destroy() {
		mContext = null;
		if(a1InsSet != null)
			a1InsSet.destroy();
		a1InsSet = null;
		mComm = null;
	}
}
