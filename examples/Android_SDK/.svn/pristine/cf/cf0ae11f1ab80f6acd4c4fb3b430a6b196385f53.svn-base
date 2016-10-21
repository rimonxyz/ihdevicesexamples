package com.ihealth.communication.privatecontrol;

import android.content.Context;

import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.control.DeviceControl;
import com.ihealth.communication.ins.AbiInsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;

/**
 * @hide
 */
public class AbiControlSub implements DeviceControl{

private static final String TAG = "AbiContorlSub";
	
	private Context mContext;						
	private AbiInsSet abiInsSet;					
	private BaseComm mComm;							
	private String mAddress;
	
	/**
	 * a constructor for AbiContorlSub.
	 * @hide
	 */
	public AbiControlSub(Context context, BaseComm comm, BaseCommProtocol commProtocol, String username, String mac, String type, String abiType, InsCallback insCallback, BaseCommCallback mBaseCommCallback){
		this.mContext = context;
		this.mComm = comm;
		this.mAddress = mac;
		abiInsSet = new AbiInsSet(context, comm, commProtocol, username, mac, type, abiType, insCallback, mBaseCommCallback);
	}
	
	/**
	 * Get the Bp5 battery
	 */
	public void getBattery(){
		abiInsSet.getBatteryLevel();
	}
	
	/**
	 * Interrupt the measurement process immediately
	 */
	public void interruptMeasure(){
		abiInsSet.interruptMeasure();
	}

	/**
	 * Start On-line measurement 
	 */
	public void startMeasure() {
		abiInsSet.startMeasure();
	}
	
	/**
	 * @hide
	 */
	@Override
	public void init() {
		abiInsSet.identify();
	}

	/**
	 * Disconnect the Bp5
	 */
	@Override
	public void disconnect() {
		mComm.disconnect();
	}

	@Override
	public void destroy() {

	}
}
