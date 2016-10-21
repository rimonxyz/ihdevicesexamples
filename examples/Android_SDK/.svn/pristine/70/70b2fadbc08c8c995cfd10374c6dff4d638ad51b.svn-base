
package com.ihealth.communication.base.wifi;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.utils.ByteBufferUtil;


import com.ihealth.communication.utils.Log;

public class WifiUnpackageData {
    private boolean DEBUG = true;
    private static final String TAG1 = "HS5Wifi";
    private static final String TAG = "WifiUnpackageData::";
    private byte[] readBuffer;
    private Queue<Byte> readDataQueue = new LinkedList<Byte>();
    private Map<String, BaseCommProtocol> mapWifiProtocols;
    private UdpSearchCallback mUdpSearchCallback;

    public WifiUnpackageData(UdpSearchCallback udpSearchCallback) {
        mapWifiProtocols = new ConcurrentHashMap<String, BaseCommProtocol>();
        this.mUdpSearchCallback = udpSearchCallback;
    }

    public void addCommNotify(String mac, BaseCommProtocol mWifiCommProtocol) {
        mapWifiProtocols.put(mac, mWifiCommProtocol);
    }

    public void addReadData(byte[] data, int count) {
        for (int i = 0; i < count; i++) {
            readDataQueue.offer(data[i]);
        }
        isFullCommand();
    }

    private void isFullCommand() {
        int temp; // 数据长度
        byte[] datas = null;
        if (readDataQueue.size() < 11) {
            Log.w(TAG, "command length is not wrong");
            return;
        }
        int head = readDataQueue.peek() & 0xff;
        if (160 == head) { // 数据头是A0
            readDataQueue.poll();
        } else {
            Log.w(TAG1, "head byte is not A0");
            readDataQueue.poll();
            return;
        }
        temp = readDataQueue.peek() & 0xff;
        int len = temp + 3; // 数据包长度
        if (readDataQueue.size() >= temp + 2) {
            datas = new byte[len];
            datas[0] = (byte) 0xA0;
            for (int i = 1; i < len; i++) {
                Byte b = readDataQueue.poll().byteValue();
                if (null != b) {
                    datas[i] = b;
                }
            }

        } else {
            Log.w(TAG, "checksum is wrong");
            return;
        }
        notifyProtocol(datas);
        // temp = (datas[3] & 0xff);
        // readBuffer = new byte[datas.length];
        // for (int i = 0; i < datas.length; i++) {
        // readBuffer[i] = datas[i];
        // }

    }

    /**
     * 判断是否是idps指令，抛给基础通信协议解析
     */
    private void notifyProtocol(byte[] datas) {
        if (datas == null || datas.length < 13) { // 指令不全
            return;
        }
        int commandId = datas[5] & 0xff;
        int idps = 0xf0 & 0xff;
        if (commandId == idps) { // idps 指令
            mUdpSearchCallback.searchUdpNotify(datas);
        } else {
            byte[] mac = ByteBufferUtil.bytesCutt(datas.length - 7, datas.length - 2, datas);
            String receiveMac = ByteBufferUtil.Bytes2HexString(mac);
            mapWifiProtocols.get(receiveMac).unPackageData(datas);
        }
    }
}
