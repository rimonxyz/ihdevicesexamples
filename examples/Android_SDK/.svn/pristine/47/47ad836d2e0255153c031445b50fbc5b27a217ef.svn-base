package com.ihealth.communication.control;

import android.content.Context;

import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.A1InsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;

/**
 * Public API for the BP7
 * <p> The class provides methods to control BP7 device.
 * You need to call the device method, and then call the connection method
 * <p> If you want to connect a BP5 device, you need to call{@link iHealthDevicesManager#startDiscovery} to discovery a new BP5 device,
 * and then call{@link iHealthDevicesManager#connectDevice}to connect BP5 device.
 */
public class Bp7Control implements DeviceControl{

private static final String TAG = "Bp5Control";
	
	private Context mContext;						
	private A1InsSet a1InsSet;					
	private BaseComm mComm;							
	private String mAddress;
	
	/**
	 * a constructor for Bp7Contorl.
	 * @param com class for communication.
	 * @param context Context.
	 * @param mac  valid Bluetooth address(without colon). 
	 * @param name valid Bluetooth name.
	 * @param mBaseCommCallback communication callback.
	 * @param insCallback Bp5 device callback.
	 * @hide
	 */
	public Bp7Control(Context context, BaseComm com, String userName, String mac, String name, InsCallback insCallback, BaseCommCallback mBaseCommCallback){
		this.mComm = com;
		this.mContext = context;
		this.mAddress = mac;
		a1InsSet = new A1InsSet(context, com, userName, mac, name, insCallback, mBaseCommCallback);
	}
	
	/**
	 * Get the BP7 information
	 */
	public String getIdps(){
		return iHealthDevicesManager.getInstance().getDevicesIDPS(mAddress);
	}

	/**
	 * Get the BP7 battery
	 * <ul>
	 * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
	 * <li>The action of the callback is {@link BpProfile#ACTION_BATTERY_BP}.</li>
	 * <li>Thr Keys of message will show in the KeyList of {@link BpProfile#ACTION_BATTERY_BP}.</li>
	 * <li>This is an asynchronous call, it will not return immediately. After get the battery status,
	 * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
	 * </ul>
	 */
	public void getBattery(){
		a1InsSet.getBatteryLevel();
	}
	
	/**
	 * Interrupt the measurement process immediately
	 */
	public void interruptMeasure(){
		a1InsSet.interruptMeasure();
	}

	/**
	 * Enable the BP7 Off-line measure
     * <ul>
     * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
     * <li>The action of the callback is {@link BpProfile#ACTION_ENABLE_OFFLINE_BP}.</li>
     * <li>This is an asynchronous call, it will not return immediately.
     * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
     * </ul>
	 */
	public void enbleOffline(){
		a1InsSet.enableOfflineMeasure();
	}

	/**
	 * Disable the BP7 Off-line measure
     *  <ul>
     * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
     * <li>The action of the callback is {@link BpProfile#ACTION_DISENABLE_OFFLINE_BP}.</li>
     * <li>This is an asynchronous call, it will not return immediately.
     * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
     * </ul>
	 */
	public void disableOffline(){
		a1InsSet.disableOfflineMeasure();
	}

	/**
	 * Get the number of historical data in the BP7
     *  <ul>
     * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
     * <li>This is an asynchronous call, it will not return immediately.
     * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
     * <li>The action of the callback is {@link BpProfile#ACTION_HISTORICAL_NUM_BP}.</li>
     * <li>Thr Keys of return message will show in the KeyList of {@link BpProfile#ACTION_HISTORICAL_NUM_BP}.</li>
     * </ul>
	 */
	public void getOfflineNum(){
		a1InsSet.getOffLineDataNum();
	}
	
	/**
	 * Get the value of historical data in the BP7
     * <ul>
     * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
     * <li>This is an asynchronous call, it will not return immediately.
     * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
     * <li>The action of the callback is {@link BpProfile#ACTION_HISTORICAL_DATA_BP}.</li>
     * <li>Thr Keys of return message will show in the KeyList of {@link BpProfile#ACTION_HISTORICAL_DATA_BP}.</li>
     * </ul>
	 */
	public void getOfflineData(){
		a1InsSet.getOffLineData();
	}
	
	/**
	 * Return true if the BP7 is enable to Off-line measure, otherwise is disable to Off-line measure.
     * <ul>
     * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
     * <li>This is an asynchronous call, it will not return immediately.
     * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
     * <li>The action of the callback is {@link BpProfile#ACTION_IS_ENABLE_OFFLINE}.</li>
     * <li>Thr Keys of return message will show in the KeyList of {@link BpProfile#ACTION_IS_ENABLE_OFFLINE}.</li>
     * </ul>
	 */
	public void isEnableOffline(){
		a1InsSet.getFunctionInfo();
	}
	
	/**
	 * This angle is suitable for measurement and start On-line measurement
     * <ul>
     * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
     * <li>This is an asynchronous call, it will not return immediately.
     * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
     * <li>The actions of the callback are: <br />
     * <ol>
	 *     <li>{@link BpProfile#ACTION_ANGLE_BP}</li>
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
	 * Conform angle, start measure now.
	 */
	public void conformAngle(){
		a1InsSet.angleYes();
	}


	/**
	 * Conform angle, start measure now.
	 * @deprecated use {@link Bp7Control#conformAngle()} instead.
	 */
	@Deprecated
	public void getAngle(){
		a1InsSet.angleYes();
	}
	
	/**
	 * @hide
	 */
	@Override
	public void init() {
		a1InsSet.getIdps();
	}

	/**
	 * Disconnect the BP7
	 */
	@Override
	public void disconnect() {
		mComm.disconnect();
	}

	@Override
	public void destroy() {
		if(a1InsSet != null)
			a1InsSet.destroy();
		a1InsSet = null;
		mContext = null;
		mComm = null;
	}

}
