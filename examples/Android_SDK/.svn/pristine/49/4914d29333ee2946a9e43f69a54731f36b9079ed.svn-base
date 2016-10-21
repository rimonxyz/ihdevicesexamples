package com.ihealth.communication.control;

import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;

public interface DeviceControl {
	public void init();
	/**
	 * Disconnect device
	 * <ul>
	 * <li>This is an asynchronous call, it will return immediately.</li>
	 * <li>If disconnect successfully, following callback will be triggered:<br/>
	 * {@link iHealthDevicesCallback#onDeviceConnectionStateChange(String mac, String deviceType, int status, int errorID)}<br/>
	 * <ul>
	 * <li>The <b>status</b> will be 2({@link iHealthDevicesManager#DEVICE_STATE_DISCONNECTED}).</li>
	 * </ul>
	 * </li>
	 * <li>
	 * Attention, it is mandatory to call following method before you call this method:<br/>
	 * {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)}
	 * </li>
	 * </ul>
	 */
	public void disconnect();
	public void destroy();
}
