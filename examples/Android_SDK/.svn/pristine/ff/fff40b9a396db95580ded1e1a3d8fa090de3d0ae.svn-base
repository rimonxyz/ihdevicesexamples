package com.ihealth.communication.control;

import android.content.Context;

import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;

/**
 * Public API for the AM4
 * <p> The class provides methods to control AM4 device.
 * You need to call the device method, and then call the connection method
 * <p> If you want to connect a AM4 device, you need to call{@link iHealthDevicesManager#startDiscovery} to discovery a new AM4 device,
 * and then call{@link iHealthDevicesManager#connectDevice}to connect AM4 device.
 */
public class Am4Control extends AmControlBase {

	private static final String TAG = "Am4Control";
	/**
	 * Constructor
	 * @param com BaseComm
	 * @param context
	 * @param mac device address
	 * @param type device name
	 * @param baseCommCallback BaseCommCallback
	 * @param insCallback insCallback
	 * @hide
	 */
	public Am4Control(BaseComm com, Context context, String mac, String type, String userName,
			BaseCommCallback baseCommCallback, InsCallback insCallback) {
        super(TAG, com, context, mac, type, userName, baseCommCallback, insCallback);
	}

    /**
     * Disabled method, should not be called.<br/>
     * If called, UnsupportedOperationException will be thrown.<br/>
     * Should call this: {@link #setUserInfo(int, int, float, int, int, int, int)}
     */
    @Override
    protected void setUserInfo(int age, int height, float weight, int gender, int unit, int target, int activityLevel) {
        throw new UnsupportedOperationException("AM4 not support this method, please call setUserInfo(int, int, float, int, int, int, int, int) instead.");
    }

    // Enable it
    @Override
    public void setUserInfo(int age, int height, float weight, int gender, int unit, int target, int activityLevel, int min) {
        super.setUserInfo(age, height, weight, gender, unit, target, activityLevel, min);
    }

    /**
     * Disabled method, should not be called.<br/>
     * If called, UnsupportedOperationException will be thrown.
     */
    @Override
    protected void setMode(int mode) {
        throw new UnsupportedOperationException("AM4 not support setMode, it will change mode automatically.");
    }

    // Enable it
    @Override
    public void syncStageReprotData() {
        super.syncStageReprotData();
    }

    // Enable it
    @Override
    public void sendRandom() {
        super.sendRandom();
    }

    // Enable it
    @Override
    public void checkSwimPara() {
        super.checkSwimPara();
    }

    // Enable it
    @Override
    public void setSwimPara(boolean isOpen, int poolLength, int hours, int minutes, int unit) {
        super.setSwimPara(isOpen, poolLength, hours, minutes, unit);
    }

    /**
     * Disabled method, should not be called.<br/>
     * If called, UnsupportedOperationException will be thrown.
     */
    @Override
    protected void getPicture() {
        throw new UnsupportedOperationException("AM4 not support getPicture.");
    }

    /**
     * Disabled method, should not be called.<br/>
     * If called, UnsupportedOperationException will be thrown.
     */
    @Override
    protected void setPicture(int index) {
        throw new UnsupportedOperationException("AM4 not support setPicture.");
    }
}
