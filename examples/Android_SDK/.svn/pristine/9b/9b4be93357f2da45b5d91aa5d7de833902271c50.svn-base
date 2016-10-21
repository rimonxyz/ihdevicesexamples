
package com.ihealth.communication.base.protocol;

import com.ihealth.communication.base.comm.NewDataCallback;

public interface BaseCommProtocol {

    public void packageData(String mac, byte[] ins);

    public void packageData(byte[] ins);

    public void unPackageData(byte[] data);

    public void unPackageDataUuid(String uuid, byte[] data);

    public void setInsSet(NewDataCallback dataCallback);

    public void packageDataAsk(byte[] returnCommand);

    public void packageDataFinish();

    public void destroy();
}
