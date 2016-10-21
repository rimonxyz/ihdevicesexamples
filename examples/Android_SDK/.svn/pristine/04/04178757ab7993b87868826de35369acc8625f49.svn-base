package com.ihealth.communication.privatecontrol;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import android.content.Context;
import com.ihealth.communication.utils.Log;

import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.control.AbiControl;
import com.ihealth.communication.control.AbiProfile;
import com.ihealth.communication.ins.InsCallback;

/**
 * @hide
 */
public class AbiControlSubManager {

	private static final String TAG = "BtAbiControlSubManager";
	private static class SingletonHolder {
		static final AbiControlSubManager INSTANCE = new AbiControlSubManager();
	}

	public static AbiControlSubManager getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private AbiControlSubManager() {

	}
	
	private Map<String, AbiControlSub> mapContorlSubUp;
	private Map<String, AbiControlSub> mapContorlSubDown;
	private Map<String, AbiControl> mapControl;
	private Map<String, AbiControlSub[]> mapControlSub;
	private Map<String, Boolean> mapZoring;
	private Map<String, Boolean> mapPressure;
	
	public void init(){
		mapContorlSubUp = new ConcurrentHashMap<String, AbiControlSub>();
		mapContorlSubDown = new ConcurrentHashMap<String, AbiControlSub>();
		mapControl = new ConcurrentHashMap<String, AbiControl>();
		mapControlSub = new ConcurrentHashMap<String, AbiControlSub[]>();
		mapZoring = new ConcurrentHashMap<String, Boolean>();
		mapPressure = new ConcurrentHashMap<String, Boolean>();
	}
	
	public void destory(){
		mapContorlSubUp.clear();
		mapContorlSubDown.clear();
		mapControl.clear();
		mapControlSub.clear();
		mapZoring.clear();
		mapPressure.clear();
	}
	
	public boolean getBattery(String mac){
		AbiControlSub abiSub = mapContorlSubUp.get(mac);
		if(abiSub != null){
			abiSub.getBattery();
			return true;
		}
		AbiControlSub[] mAbiControlSub = mapControlSub.get(mac);
		if(mAbiControlSub == null || mAbiControlSub.length < 2)
			return false;
		for (AbiControlSub abiContorlSub : mAbiControlSub) {
			abiContorlSub.getBattery();
		}
		return true;
	}
	
	public void setZore(String mac){
		mapZoring.put(mac, true);
	}
	
	public void setPressure(String mac){
		mapPressure.put(mac, true);
	}
	
	private boolean onlyArm = false;
	public boolean startMeasure(String mac){
		mapZoring.clear();
		mapPressure.clear();
		onlyArm = false;
		AbiControlSub abiSub = mapContorlSubUp.get(mac);
		if(abiSub != null){
			abiSub.startMeasure();
			onlyArm = true;
			return true;
		}
		AbiControlSub[] mAbiControlSub = mapControlSub.get(mac);
		if(mAbiControlSub == null || mAbiControlSub.length < 2)
			return false;
		for (AbiControlSub abiContorlSub : mAbiControlSub) {
			abiContorlSub.startMeasure();
		}
		return true;
	}
	
	public boolean stopMeasure(String mac){
		AbiControlSub abiSub = mapContorlSubUp.get(mac);
		if(abiSub != null){
			abiSub.interruptMeasure();
			return true;
		}
		AbiControlSub[] mAbiControlSub = mapControlSub.get(mac);
		if(mAbiControlSub.length < 2)
			return false;
		for (AbiControlSub abiContorlSub : mAbiControlSub) {
			abiContorlSub.interruptMeasure();
		}
		return true;
	}
	
	public boolean disconnect(String mac) {
		AbiControlSub abiSub = mapContorlSubUp.get(mac);
		if(abiSub != null){
			abiSub.disconnect();
			return true;
		}
		AbiControlSub[] mAbiContorlSub = mapControlSub.get(mac);
		if(mAbiContorlSub.length < 2)
			return false;
		for (AbiControlSub abiContorlSub : mAbiContorlSub) {
			abiContorlSub.disconnect();
		}
		return true;
	}
	
	public void remove(String mac){
		mapContorlSubUp.remove(mac);
		mapContorlSubDown.remove(mac);
		mapControl.remove(mac);
		mapControlSub.remove(mac);
	}
	
	private AbiControlSub mAbiControlSubUp;
	private AbiControlSub mAbiControlSubDown;
	private AbiControlSub mAbiControlSubUnkonwn;
	
	public void createControlUp(Context context, BaseComm comm, BaseCommProtocol baseCommProtocol, String username, String mac, String type, String abiType, InsCallback insCallback, BaseCommCallback mBaseCommCallback){
		mAbiControlSubUp = new AbiControlSub(context, comm, baseCommProtocol, username, mac, type, abiType, insCallback, mBaseCommCallback);
		mAbiControlSubUp.init();
	}
	
	public void createControlDown(Context context, BaseComm comm, BaseCommProtocol baseCommProtocol, String username, String mac, String type, String abiType, InsCallback insCallback, BaseCommCallback mBaseCommCallback){
		mAbiControlSubDown = new AbiControlSub(context, comm, baseCommProtocol, username, mac, type, abiType, insCallback, mBaseCommCallback);
		mAbiControlSubDown.init();
	}

	public void createControlUnkonwn(Context context, BaseComm comm, BaseCommProtocol baseCommProtocol, String username, String mac, String type, String abiType, InsCallback insCallback, BaseCommCallback mBaseCommCallback){
		mAbiControlSubUnkonwn = new AbiControlSub(context, comm, baseCommProtocol, username, mac, type, abiType, insCallback, mBaseCommCallback);
		mAbiControlSubUnkonwn.init();
	}
	
	public void addAbiControlSubUp(String mac){
		Log.v(TAG, "addAbiControlSubUp - " + mac);
		if(mac != null)
			mapContorlSubUp.put(mac, mAbiControlSubUp);
	}
	
	public void addAbiControlSubDown(String mac){
		Log.v(TAG, "addAbiControlSubDown - " + mac);
		if(mac != null)
			mapContorlSubDown.put(mac, mAbiControlSubDown);
	}
	
	public String getAbiConnectedForArm(){
		for (String string : mapContorlSubUp.keySet()) {
			return string;
		}
		return null;
	}
	
	public String getAbiConnected(){
		String macDown = "";
		for (String string : mapContorlSubDown.keySet()) {
			macDown = string;
		}
		if(mapContorlSubUp.size() > 0){
			return macDown;
		}else{
			return null;
		}
		
	}

	public synchronized AbiControl getAbiControlforUp(String mac) {
		if(mac == null)
			return null;
		AbiControl abiControl = mapControl.get(mac);
		if(abiControl != null){
			return mapControl.get(mac);
		}else{
			if(!(mapContorlSubUp.size() > 0))
				return null;
			AbiControlSub abiContorlSub = mapContorlSubUp.get(mac);
			if(abiContorlSub != null){
				AbiControl newabiControl = new AbiControl(mac);
				mapControl.put(mac, newabiControl);	
				return newabiControl;
			}else{
				return null;
			}
		}
	}

	public synchronized AbiControl getAbiControl(String mac) {
		if(mac == null){
			Log.v(TAG, "mac is null");
			return null;
		}
			
		AbiControl abiControl = mapControl.get(mac);
		if(abiControl != null){
			return mapControl.get(mac);
		}else{
			if(!(mapContorlSubDown.size() > 0 && mapContorlSubUp.size() > 0)){
				Log.v(TAG, "!(mapContorlSubDown.size() > 0 && mapContorlSubUp.size() > 0)");
				return null;
			}
			AbiControlSub abiContorlSubDown = mapContorlSubDown.get(mac);
			AbiControlSub abiContorlSubUp = null;
			for (String key : mapContorlSubUp.keySet()) {
				abiContorlSubUp = mapContorlSubUp.get(key);
				break;
			}
			if(abiContorlSubDown != null && abiContorlSubUp != null){
				AbiControl newabiControl = new AbiControl(mac);
				mapControl.put(mac, newabiControl);	
				addAbiControl(mac, abiContorlSubUp, abiContorlSubDown);
				return newabiControl;
			}else{
				return null;
			}
		}
	}
	
	private void addAbiControl(String mac, AbiControlSub... abiContorlSubs){
		mapControlSub.put(mac, abiContorlSubs);
	}

	
	public boolean notNeedWaitZore(String mAddress) {
		if(onlyArm){
			return mapZoring.get(mAddress);
		}else{
			//获取当前连接的ABI mac
			String armMac = getAbiConnectedForArm();
			String legMac = getAbiConnected();
			if (armMac == null || legMac == null) {
				return false;
			}
			if (mapZoring.get(armMac) != null &&  mapZoring.get(legMac) != null && mapZoring.get(armMac) == true && mapZoring.get(legMac) == true) {
				return true;
			}
			return false;
		}
	}
	
	public boolean notNneedWaitPressure(String mAddress) {
		if(onlyArm){
			return mapPressure.get(mAddress);
		}else{
			//获取当前连接的ABI mac
			String armMac = getAbiConnectedForArm();
			String legMac = getAbiConnected();
			if (armMac == null || legMac == null) {
				return false;
			}
			if (mapZoring.get(armMac) != null &&  mapZoring.get(legMac) != null && mapZoring.get(armMac) == true && mapZoring.get(legMac) == true) {
				return true;
			}
			return false;
		}
	}
}
