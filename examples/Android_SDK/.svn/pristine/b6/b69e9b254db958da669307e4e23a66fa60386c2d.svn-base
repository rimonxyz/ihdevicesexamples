package com.ihealth.communication.base.protocol;

import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.NewDataCallback;

/**
 * @hide
 */
public class BleCommContinueProtocol implements BaseCommProtocol{

    private BaseComm mBaseComm;
    private String mAddress;
    private NewDataCallback mCommCallback;
    public BleCommContinueProtocol(BaseComm com, String mac, NewDataCallback commCallback) {
        this.mBaseComm = com;
        this.mAddress = mac;
        this.mCommCallback = commCallback;
        com.addCommContinueNotify(mac, this);
    }

    @Override
    public void packageData(String mac, byte[] ins) {

    }

    @Override
    public void packageData(byte[] ins) {

    }

    @Override
    public void unPackageData(byte[] data) {
    }

    @Override
    public void unPackageDataUuid(String uuid, byte[] data) {
        mCommCallback.haveNewDataUuid(uuid, data);
    }

    @Override
    public void setInsSet(NewDataCallback dataCallback) {

    }

    @Override
    public void packageDataAsk(byte[] returnCommand) {

    }

    @Override
    public void packageDataFinish() {

    }

    @Override
    public void destroy() {

    }
}
