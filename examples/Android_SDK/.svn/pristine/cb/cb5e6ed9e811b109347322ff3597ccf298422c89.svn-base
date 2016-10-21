package com.ihealth.communication.control;

import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.privatecontrol.AbiControlSubManager;

import java.util.Map;

public class AbiControl {

	/**
	 * a constructor for AbiControl.
	 * @hide
	 */
	private String mMac;
	public AbiControl(String mac){
		this.mMac = mac;
	}

	/**
	 * Get the mac address of arm blood pressure meter, if you only want to measure arm blood pressure.
	 * @return  the mac address of arm blood pressure meter, return null if no device connected.
	 */
	public static String getAbiConnectedForArm() {
		String armMac = AbiControlSubManager.getInstance().getAbiConnectedForArm();
		return armMac;
	}

	/**
	 * Get the mac address of leg blood pressure meter, if you  want to measure arm and len blood pressure at the same time (ABI).
	 * @return the mac address of leg blood pressure meter, return null if no device connected.
	 */
	public static String getAbiConnected() {
		String legMac = AbiControlSubManager.getInstance().getAbiConnected();
		return legMac;
	}

	/**
	 * Get battery for arm blood pressure meter, if only arm blood pressure meteor is connected; Get battery for arm and
	 * leg blood pressure if both arm and leg blood pressure are connected.
	 * <ul>
	 * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
	 * <li>This is an asynchronous call, it will not return immediately. After get the battery status,
	 * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
	 * <li>The action of the callback is {@link AbiProfile#ACTION_BATTERY_ABI}.</li>
	 * <li>Thr Keys of message will show in the KeyList of {@link AbiProfile#ACTION_BATTERY_ABI}.</li>
	 * </ul>
	 * @return true if one arm blood pressure meter and a leg blood pressure meter are connected at least
	 */
	public boolean getBattery(){
		return AbiControlSubManager.getInstance().getBattery(mMac);
	}


	/**
	 * Start to measure blood pressure for arm blood pressure meter, if only arm blood pressure meteor is connected; Start to measure blood pressure for arm and
	 * leg blood pressure if both arm and leg blood pressure are connected.
	 * <ul>
	 * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
	 * <li>This is an asynchronous call, it will not return immediately. After get the battery status,
	 * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
	 * <li>The actions of the callback are: <br />
	 * <ol>
	 *     <li>{@link AbiProfile#ACTION_ZEROING_ABI}</li>
	 *     <li>{@link AbiProfile#ACTION_ZERO_OVER_ABI}</li>
	 *     <li>{@link AbiProfile#ACTION_ONLINE_PRESSURE_ABI}</li>
	 *     <li>{@link AbiProfile#ACTION_ONLINE_PULSE_WAVE_ABI}</li>
	 *     <li>{@link AbiProfile#ACTION_ONLINE_RESULT_ABI}
	 *     <li>{@link AbiProfile#ACTION_ERROR_ABI}
	 *     <li>{@link AbiProfile#ACTION_INTERRUPTED_ABI}
	 * </ol>
	 * @return true if one arm blood pressure meter and a leg blood pressure meter are connected at least
	 */
	public boolean startMeasure(){
		return AbiControlSubManager.getInstance().startMeasure(mMac);
	}
	

	/**
	 * Stop measure for blood pressure meter.
	 * <ul>
	 * <li>Attention, call {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)} firstly.</li>
	 * <li>This is an asynchronous call, it will not return immediately. After get the battery status,
	 * Callback {@link iHealthDevicesCallback#onDeviceNotify(String, String, String, String)} will be triggered.</li>
	 * <li>The action of the callback is {@link AbiProfile#ACTION_STOP_ABI}.</li>
	 * </ul>
	 * @return true if one arm blood pressure meter and a leg blood pressure meter are connected at least
	 */
	public boolean stopMeasure(){
		return AbiControlSubManager.getInstance().stopMeasure(mMac);
	}
	
	/**
	 * Disconnect the connected blood pressure meter currently. After disconnect, Callback{@link iHealthDevicesCallback#onDeviceConnectionStateChange(String, String, int, int, Map)} will be triggered.
	 * @return true if one arm blood pressure meter and a leg blood pressure meter are connected at least
	 */
	public boolean disconnect(){
		return AbiControlSubManager.getInstance().disconnect(mMac);
	}
}
