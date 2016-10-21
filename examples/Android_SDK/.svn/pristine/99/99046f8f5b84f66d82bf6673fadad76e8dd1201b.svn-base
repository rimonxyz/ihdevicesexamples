package com.ihealth.communication.base.ble;

import com.ihealth.communication.base.comm.BaseComm;

import java.util.UUID;

public interface BleComm {
	
	BaseComm getBaseComm();
	void scan(boolean b);
	boolean connectDevice(String address);
	void getService(String mac, String comm, String trans, String rece, String idps, boolean neddIndication);
	void Obtain(UUID uuid);
	boolean Obtain(String mac, UUID serviceUuid, UUID charactUuid);
	void refresh(String mac);
	void destory();
	void disconnect(String mac);
}
