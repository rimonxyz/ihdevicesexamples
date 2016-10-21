
package com.ihealth.communication.base.wifi;

import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.base.protocol.BaseCommProtocol;

public interface WifiBaseComm {
    void sendData(byte[] data);

    void sendData(String mac, byte[] data);

    void sendData(String mac, String deviceIP, byte[] data);

    void disconnect();

    void disconnect(String mac);

    void addCommNotify(NewDataCallback dataCallBack);

    void addCommNotify(String mac, BaseCommProtocol dataCallBack);
}
