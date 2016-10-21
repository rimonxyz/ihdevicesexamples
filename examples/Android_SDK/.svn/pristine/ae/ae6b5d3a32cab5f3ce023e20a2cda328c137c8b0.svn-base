
package com.ihealth.communication.base.protocol;

import com.ihealth.communication.utils.Log;

import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.DataNotify;
import com.ihealth.communication.base.comm.DataNotifyImpl;
import com.ihealth.communication.base.comm.NewDataCallback;


import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class Hs3CommProtocol implements BaseCommProtocol {

    private static final String TAG = "Hs3CommProtocol >>>";
    private BaseComm comm;
    private byte trasmitHead = (byte) 0xb0;
    private DataNotify btNotify;

    public Hs3CommProtocol(BaseComm com, NewDataCallback dataCallBack) {
        this.comm = com;
        com.addCommNotify(this);
        btNotify = new DataNotifyImpl();
        btNotify.attach(dataCallBack);
    }

    private int commandSequenceID = 1;
    private ConcurrentHashMap<Integer, byte[]> commandCountMap = new ConcurrentHashMap<Integer, byte[]>();

    @Override
    public void packageData(String mac, byte[] command) {
        // cancelRepeatSendTimer();
        commandCountMap.clear();
        int len = command.length + 1;
        int lenFull = len + 3;

        byte[] commandtemp = new byte[lenFull];
        commandtemp[0] = trasmitHead;
        commandtemp[1] = (byte) len;
        commandtemp[2] = command[0];
        commandtemp[3] = command[1];
        commandtemp[4] = (byte) commandSequenceID;
        for (int i = 2; i < command.length; i++) {
            commandtemp[3 + i] = command[i];
        }
        commandtemp[lenFull - 1] = generateCKS(commandtemp);
        comm.sendData(null, commandtemp);
        commandCountMap.put((commandSequenceID & 0xFF), commandtemp);
        addSeqID();
        addSeqID();
        // repeatSendTimer();
    }

    public void packageDataAsk(byte[] command) {
        int len = command.length + 2;
        int lenFull = len + 3;
        byte[] commandtemp = new byte[lenFull];
        commandtemp[0] = trasmitHead;
        commandtemp[1] = (byte) len;
        commandtemp[2] = (byte) 0xa0;
        commandtemp[3] = (byte) commandSequenceID;
        for (int i = 0; i < command.length; i++) {
            commandtemp[4 + i] = command[i];
        }
        commandtemp[lenFull - 1] = generateCKS(commandtemp);
        comm.sendData(null, commandtemp);
        addSeqID();
    }

    public void unPackageData(byte[] commandReceive) {
        // 判断是不是下位机发过来的指令
        if (commandReceive[0] != (byte) 0xA0) {
            Log.w(TAG, "head byte is not A0");
            return;
        }
        int len = commandReceive[1] & 0xff;
        int lenR = commandReceive.length;

        // 获得当前下位机发过来的顺序ID
        byte seqID = commandReceive[4];

        int tempSeqID = 0;
        if (seqID == 0) {
            tempSeqID = 255;
        } else {
            tempSeqID = (seqID & 0xFF) - 1;
        }

        commandCountMap.remove(tempSeqID);
        setSeqID(seqID);
        if (len != (lenR - 3)) {
            Log.w(TAG, "This is not full command");
            return;
        }
        if (!checkCKS(2, lenR - 2, commandReceive)) {
            Log.w(TAG, "checksum is wrong");
            return;
        }

        byte[] command = bytesCutt(5, lenR - 2, commandReceive);
        // 顺序ID +1 为以后发指令做准备
        addSeqID();
        btNotify.haveNewData(commandReceive[3] & 0xFF, commandReceive[2] & 0xff, command);
    }

    private byte[] bytesCutt(int start, int stop, byte[] data) {
        int len = stop - start + 1;
        byte[] dataR = new byte[len];
        for (int i = 0; i < dataR.length; i++) {
            dataR[i] = data[start + i];
        }
        return dataR;
    }

    private boolean checkCKS(int start, int end, byte[] data) {
        int cks = data[end + 1] & 0xFF;
        int sum = 0;
        for (int i = start; i < end + 1; i++) {
            sum = sum + data[i] & 0xFF;
        }
        if (sum == cks) {
            return true;
        } else {
            return false;
        }
    }

    private byte generateCKS(byte[] command) {
        int sum = 0;
        for (int i = 2; i < command.length - 1; i++) {
            sum = sum + command[i];
        }
        return (byte) sum;
    }

    private void addSeqID() {
        if (commandSequenceID == 255) {
            commandSequenceID = 0;
        } else {
            commandSequenceID += 1;
        }
    }

    private void setSeqID(int seqID) {
        commandSequenceID = seqID;
    }

    private Timer timer;
    private TimerTask task;

    private void repeatSendTimer() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                int i = 0;
                do {
                    repeatSend();
                    ++i;
                } while (i < 2);
            }
        };
        timer.schedule(task, 500);
    }

    private void cancelRepeatSendTimer() {
        if (task != null) {
            task.cancel();
            timer.cancel();
        }
    }

    private void repeatSend() {
        for (Iterator<Integer> it = commandCountMap.keySet().iterator(); it.hasNext();) {
            Object key = it.next();
            if (commandCountMap.get(key) != null) {
                comm.sendData(null, commandCountMap.get(key));
            }
        }
    }

    @Override
    public void setInsSet(NewDataCallback dataCallback) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * @see com.ihealth.communication.base.protocol.BaseCommProtocol#packageData(byte[])
     */
    @Override
    public void packageData(byte[] ins) {
        // TODO Auto-generated method stub

    }

    @Override
    public void unPackageDataUuid(String uuid, byte[] data) {

    }

    @Override
    public void packageDataFinish() {
        
    }

    @Override
    public void destroy() {

    }
}
