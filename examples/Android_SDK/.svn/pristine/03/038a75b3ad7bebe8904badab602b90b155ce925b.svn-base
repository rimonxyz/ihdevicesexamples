
package com.ihealth.communication.base.protocol;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.HandlerThread;
import com.ihealth.communication.utils.Log;

import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.base.wifi.WifiSendThread;
import com.ihealth.communication.control.HsProfile;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.utils.ByteBufferUtil;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class WifiCommProtocol implements BaseCommProtocol {
    private static final String TAG = "WifiCommProtocol-----";
    private static final String TAG1 = "HS5Wifi";
    private BaseComm comm;
    private byte trasmitHead = (byte) 0xb0;
    private String mDeviceMac = "", mDeviceIP = "";
    private Context mContext;
    private byte[] PHONE_MAC; // 手机的Mac地址
    private NewDataCallback dataCallback;
    private BaseCommCallback mBaseCommCallback;
    private String mType;

    private HandlerThread handlerthread;
    private Handler mHandler;
    private WifiSendThread mWifiSendThread;
    private InsCallback mInsCallback;

    public WifiCommProtocol(Context context, BaseComm comm, String deviceMac, String deviceIP, String type,
            BaseCommCallback baseCommCallback ,InsCallback insCallback) {
        this.mContext = context;
        this.comm = comm;
        this.mDeviceIP = deviceIP;
        this.mDeviceMac = deviceMac;
        this.mType = type;
        this.mInsCallback=insCallback;
        this.mBaseCommCallback = baseCommCallback;
        PHONE_MAC = (byte[]) ((WifiManager) context.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo()
                .getMacAddress()
                .getBytes();
        mWifiSendThread = new WifiSendThread(comm);

        if (handlerthread != null && handlerthread.isAlive()) {
            Log.v(TAG1,"handlerthread != null");

        } else {
            handlerthread = new HandlerThread("wificommthread");
            handlerthread.start();
        }
        if (mHandler != null) {
            Log.v(TAG1,"mHandler != null");
        } else {
            mHandler = new Handler(handlerthread.getLooper());
        }
    }

    private int commandSequenceID = 1;
    private int sendSequenceId = 1;

    private ConcurrentHashMap<Integer, byte[]> commandCountMap = new ConcurrentHashMap<Integer, byte[]>();

    public void packageData(byte[] command) {
        cancelRepeatSendTimer();
        commandCountMap.clear();
        int len = command.length + 8;
        int lenFull = len + 3;
        byte[] commandtemp = new byte[lenFull];
        commandtemp[0] = trasmitHead;
        commandtemp[1] = (byte) len;
        commandtemp[2] = (byte) 0x00;
        commandtemp[3] = (byte) commandSequenceID;
        for (int i = 0; i < command.length; i++) {
            commandtemp[4 + i] = command[i];
        }
        for (int i = 0; i < 6; i++) {
            commandtemp[4 + command.length + i] = PHONE_MAC[i];
        }
        commandtemp[lenFull - 1] = generateCKS(commandtemp);
        // comm.sendData(mDeviceMac, mDeviceIP, commandtemp);
        if (mHandler != null && mWifiSendThread != null) {
            mWifiSendThread.setData(mDeviceMac, mDeviceIP, commandtemp);
            mHandler.post(mWifiSendThread);
            commandCountMap.put((commandSequenceID), commandtemp);
            sendSequenceId = commandSequenceID;
            addSeqID();
            addSeqID();
            repeatSendTimer();
        }
    }

    public void packageDataAsk(byte[] command) {
        int len = command.length + 8;
        int lenFull = len + 3;
        byte[] commandtemp = new byte[lenFull];
        commandtemp[0] = trasmitHead;
        commandtemp[1] = (byte) len;
        commandtemp[2] = (byte) 0xA0;
        commandtemp[3] = (byte) commandSequenceID;
        for (int i = 0; i < command.length; i++) {
            commandtemp[4 + i] = command[i];
        }
        for (int i = 0; i < 6; i++) {
            commandtemp[4 + command.length + i] = PHONE_MAC[i];
        }
        commandtemp[lenFull - 1] = generateCKS(commandtemp);
        if (mHandler != null && mWifiSendThread != null) {
            mWifiSendThread.setData(mDeviceMac, mDeviceIP, commandtemp);
            mHandler.post(mWifiSendThread);
            addSeqID();

        }
        // comm.sendData(mDeviceMac, mDeviceIP, commandtemp);
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
    int i = 0;
    private final int REPEATSENT = 6;

    private void repeatSendTimer() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                if (i >= REPEATSENT) {
                    Log.w(TAG1, "repeatSendTimer() -- failed");
                    mBaseCommCallback.onConnectionStateChange(mDeviceMac, mType,
                            iHealthDevicesManager.DEVICE_STATE_DISCONNECTED, 0, null);
                    cancelRepeatSendTimer();
                    try {
                        JSONObject o = new JSONObject();
                        o.put(HsProfile.ERROR_NUM_HS, 600);
                        mInsCallback.onNotify(mDeviceMac, mType, HsProfile.ACTION_ERROR_HS, o.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    repeatSend();
                    i++;
                }
            }

        };
        timer.schedule(task, 500, 500);
    }

    private void cancelRepeatSendTimer() {
        i = 0;
        if (task != null) {
            task.cancel();
            timer.cancel();
        }
    }

    private void repeatSend() {
        for (Iterator<Integer> it = commandCountMap.keySet().iterator(); it.hasNext();) {
            Object key = it.next();
            if (commandCountMap.get(key) != null) {
                if (mHandler != null && mWifiSendThread != null) {
                    mWifiSendThread.setData(mDeviceMac, mDeviceIP, commandCountMap.get(key));
                    mHandler.post(mWifiSendThread);
                }
            }
        }
    }

    @Override
    public void packageData(String mac, byte[] ins) {
        // TODO Auto-generated method stub

    }

    byte priorSeqID = -1;
    byte[] priorReceiveData = null;

    @Override
    public void unPackageData(byte[] commandReceive) {
        // TODO Auto-generated method stub
        // 判断是不是下位机发过来的指令

        if (commandReceive[0] != (byte) 0xA0) {
            Log.w(TAG, "head byte is not A0");
            return;
        }
        int len = commandReceive[1] & 0xff;
        int lenR = commandReceive.length;
        if (len != lenR - 3) {
            Log.w(TAG, "This is not full command");
            return;
        }
        if (!checkCKS(2, lenR - 2, commandReceive)) {
            Log.w(TAG, "checksum is wrong");
            return;
        }
        // 获得当前下位机发过来的顺序ID
        byte seqID = commandReceive[3];
        int tempSeqID = 0;
        if (seqID == 0) {
            tempSeqID = 255;
        } else {
            tempSeqID = (seqID & 0xFF) - 1;
        }

        if (seqID != 0 && seqID == priorSeqID) {
            Log.v(TAG1, "Duplicate command");
            return;
        }

        commandCountMap.remove(tempSeqID);
        if (tempSeqID == sendSequenceId) {
            cancelRepeatSendTimer();
            // startSend = true;
        }
        commandCountMap.remove(tempSeqID);
        priorSeqID = seqID;
        priorReceiveData = commandReceive;
        setSeqID(seqID & 0xff);
        addSeqID();

        byte stateID = commandReceive[2];
        byte[] command = ByteBufferUtil.bytesCutt(6, lenR - 2, commandReceive);
        dataCallback.haveNewData(commandReceive[5] & 0xff, commandReceive[2] & 0xff, command);
    }

    @Override
    public void setInsSet(NewDataCallback dataCallback) {
        // TODO Auto-generated method stub
        this.dataCallback = dataCallback;

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
