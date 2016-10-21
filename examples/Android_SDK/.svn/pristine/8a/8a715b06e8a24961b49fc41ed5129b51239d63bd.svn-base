package com.ihealth.communication.control;

import java.util.List;

import com.ihealth.communication.utils.FirmWare;


public interface UpDeviceControl {
	
	void setInformation(List<Byte> list);
	void setData(FirmWare firmware, List<byte[]> list);
	void startUpgrade();
	void stopUpgrade();
	void borrowComm();
	void returnComm();

	void judgUpgrade();

	//返回当前是否为自升级状态
	boolean isUpgradeState();

	//设置当前自升级的设备mac
	void setCurrentMac(String mac);
	//获取当前自升级的设备mac
	String getCurrentMac();
}
