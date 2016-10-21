
package com.ihealth.communication.base.wifi;

import android.content.Context;
import android.net.wifi.WifiManager;
import com.ihealth.communication.utils.Log;

import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.utils.ByteBufferUtil;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class WifiCommThread extends Thread implements BaseComm {
    private static final String TAG = "WifiCommThread------";
    private static final String TAG1 = "HS5Wifi";

    private DatagramSocket mUdpSocket;
    public static boolean stopFlag = false;
    private static boolean DEBUG = true;
    private byte[] receiveBuffer = new byte[256];
    private byte[] receiveData = new byte[256];
    private WifiUnpackageData mWifiUnpackageData;
    private InetAddress serverAddress;
    private Context mContext;

    public WifiCommThread(UdpSearchCallback udpSearchCallback, Context context, DatagramSocket udpSocket,
            WifiManager wifiManager) {
        mContext = context;
        this.mUdpSocket = udpSocket;
        mWifiUnpackageData = new WifiUnpackageData(udpSearchCallback);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
        while (!stopFlag) {
            try {
                DatagramPacket packageBack = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                mUdpSocket.receive(packageBack);
                receiveData = packageBack.getData();
                byte[] commData = getCommandData(receiveData);
                if (commData.length > 0) {
                    Log.p(TAG, Log.Level.VERBOSE,"Read",ByteBufferUtil.Bytes2HexString(commData,commData.length));
                    mWifiUnpackageData.addReadData(commData, commData.length);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                break;
            }
        }

    }

    // 去掉尾部的0，解成通讯指令
    private byte[] getCommandData(byte[] recData) {
        int length = ((int) recData[1] & 0xFF) + 3;// 整个指令的长度
        byte[] command = new byte[length];

        for (int i = 0; i < length; i++) {
            command[i] = recData[i];
        }
        return command;
    }

    @Override
    public void sendData(String mac, String deviceIP, byte[] data) {
        // TODO Auto-generated method stub
        try
        {
            mUdpSocket.setBroadcast(true);
            serverAddress = InetAddress.getByName(deviceIP);
            Log.p(TAG, Log.Level.VERBOSE,"sendData",mac,deviceIP,ByteBufferUtil.Bytes2HexString(data, data.length));
            DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, 10000); // 下位机UDP搜索端口为10000
            mUdpSocket.send(packet);

        } catch (Exception e) {
            // Intent intent = new Intent(WifiDeviceManager.MSG_WIFI_DISCONNECT);
            // intent.putExtra(DeviceManager.MSG_MAC, mac.replace(":", ""));
            // mContext.sendBroadcast(intent);
            e.printStackTrace();
        }
    }

    @Override
    public void sendData(String mac, byte[] data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void disconnect() {
        // TODO Auto-generated method stub

    }

    @Override
    public void disconnect(String mac) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * @see
     * com.ihealth.communication.base.comm.BaseComm#addCommNotify(com.ihealth.communication.base
     * .protocol.BaseCommProtocol)
     */
    @Override
    public void addCommNotify(BaseCommProtocol dataCallBack) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * @see com.ihealth.communication.base.comm.BaseComm#addCommNotify(java.lang.String,
     * com.ihealth.communication.base.protocol.BaseCommProtocol)
     */
    @Override
    public void addCommNotify(String mac, BaseCommProtocol dataCallBack) {
        // TODO Auto-generated method stub
        mWifiUnpackageData.addCommNotify(mac, dataCallBack);

    }

    @Override
    public void addCommContinueNotify(String mac, BaseCommProtocol dataCallBack) {

    }

    @Override
    public void removeCommNotify(String mac) {

    }

    @Override
    public void removeCommNotify(BaseCommProtocol dataCallBack) {

    }

    @Override
    public Context getContext() {
        return mContext;
    }
}
