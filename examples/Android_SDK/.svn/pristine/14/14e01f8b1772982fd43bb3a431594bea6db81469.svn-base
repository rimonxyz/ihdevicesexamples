package com.ihealth.communication.base.comm;

import android.content.Context;

import com.ihealth.communication.base.protocol.BaseCommProtocol;

public interface BaseComm {
	
//	public static final int CONNECTING = 0;
//	public static final int CONNECTED = 1;
//	public static final int DISCONNECTING = 2;
	
	void sendData(String mac, byte[] data);
    void sendData(String mac, String deviceIP, byte[] data);
	void disconnect();
	void disconnect(String mac);
	void addCommNotify(BaseCommProtocol dataCallBack);
	void addCommNotify(String mac, BaseCommProtocol dataCallBack);
	void addCommContinueNotify(String mac, BaseCommProtocol dataCallBack);
	void removeCommNotify(String mac);
	void removeCommNotify(BaseCommProtocol dataCallBack);
	Context getContext();
}
