package com.ihealth.communication.base.usb;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import com.ihealth.communication.utils.Log;
import android.widget.Toast;

import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.utils.ByteBufferUtil;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import tw.com.prolific.driver.pl2303.PL2303Driver;

public class Pl2303Usb implements BaseComm{

	private static final String TAG = "PL2303Interface";
	private PL2303Driver.BaudRate mBaudrate = PL2303Driver.BaudRate.B57600;
	private PL2303Driver.DataBits mDataBits = PL2303Driver.DataBits.D8;
	private PL2303Driver.Parity mParity = PL2303Driver.Parity.NONE;
	private PL2303Driver.StopBits mStopBits = PL2303Driver.StopBits.S1;
	private PL2303Driver.FlowControl mFlowControl = PL2303Driver.FlowControl.OFF;
	private static final String ACTION_USB_PERMISSION = "com.prolific.pl2303hxdsimpletest.USB_PERMISSION";
	private PL2303Driver mSerial;
	private Context mContext;
	private BaseCommProtocol commProtocol;

	public Pl2303Usb(Context context) {
		this.mContext = context;
		mSerial = new PL2303Driver((UsbManager) mContext.getSystemService(Context.USB_SERVICE), mContext, ACTION_USB_PERMISSION);
		// check USB host function.
		if (!mSerial.PL2303USBFeatureSupported()) {
			Toast.makeText(mContext, "No Support USB host API", Toast.LENGTH_SHORT).show();
			mSerial = null;
		}
	}
	
	public void initSerialPort() {
		if (!mSerial.isConnected()) {
			if (!mSerial.enumerate()) {
				Toast.makeText(mContext, "no more devices found", Toast.LENGTH_SHORT).show();
				return;
			} else {
				Log.v(TAG, "onResume:enumerate succeeded!");
			}
		}// if isConnected
	}
	
	public void readUsbSerialThread(){
		readThread readthread = new readThread();
		readthread.start();
	}

	private UsbDevice myUsbDevice;
	private UsbManager myUsbManager;
	@SuppressWarnings("unused")
	private UsbDeviceConnection myUsbDeviceConnection;
	public void openUsbSerial() {
		if (null == mSerial){
			Log.w(TAG, "mSerial is null");
			return;
		}
		if (mSerial.isConnected()) {
			mBaudrate = PL2303Driver.BaudRate.B57600;
			myUsbDevice = PL2303Driver.sDevice;
			myUsbManager = (UsbManager) mContext.getSystemService(Context.USB_SERVICE);
			myUsbDeviceConnection = myUsbManager.openDevice(myUsbDevice);
			// if (!mSerial.InitByBaudRate(mBaudrate)) {
			if (!mSerial.InitByBaudRate(mBaudrate, 700)) {
				if (!mSerial.PL2303Device_IsHasPermission()) {
					Toast.makeText(mContext, "cannot open, maybe no permission",Toast.LENGTH_SHORT).show();
				}

				if (mSerial.PL2303Device_IsHasPermission() && (!mSerial.PL2303Device_IsSupportChip())) {
					Toast.makeText(mContext, "cannot open, maybe this chip has no support, please use PL2303HXD / RA / EA chip.", Toast.LENGTH_SHORT).show();
				}
			} else {
				Log.v(TAG, "mContext is connected");
			}
		}// isConnected

	}// openUsbSerial

	public byte[] mReadBuffer = new byte[4096];
	public int mReadBufferLength = -1;
	public byte[] readBuffer;
	private int readBufferLength = -1;
	@SuppressWarnings("unused")
	private byte[] Receiveytes;
	private class readThread extends Thread {

		readThread() {
//			this.setPriority(Thread.NORM_PRIORITY);
		}

		@Override
		public void run() {
			while (null != mSerial && mSerial.isConnected()) {
				readBufferLength = mSerial.read(mReadBuffer);
//				synchronized (mSerial) {
					if (readBufferLength > 0 && !(readBufferLength == 1 && mReadBuffer[0] == 0)) {
						Log.p(TAG, Log.Level.VERBOSE,"Read", ByteBufferUtil.Bytes2HexString(mReadBuffer, readBufferLength));
						addReadUsbData(mReadBuffer, readBufferLength);
					}
//				}
			}
		}

	}

	public int setSerialPort() {
		if (null == mSerial){
			Log.w(TAG, "setSerialPort null == mSerial");
			return -1;
		}
		if (!mSerial.isConnected()){
			Log.w(TAG, "setSerialPort !mSerial.isConnected()");
			return -1;
		}
		int res = 0;
		try {
			res = mSerial.setup(mBaudrate, mDataBits, mStopBits, mParity, mFlowControl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (res < 0) {
			Log.w(TAG, "fail to setup");
			return res;
		}
		return res;
	}

	private Queue<Byte> readDataQueue = new LinkedList<Byte>();

	private void addReadUsbData(byte[] data, int count) {
		for (int i = 0; i < count; i++) {
			readDataQueue.offer(data[i]);
		}
		isFullCommand();
		isFullCommand();
		isFullCommand();
	}
	
	private int quencesequenceID;
	private boolean isHead = false;
	private void isFullCommand() {
		int temp;
		byte[] datas = null;
		if (readDataQueue.size()<6) {
			Log.w(TAG, "command length is not wrong");
			return;
		}
		int length = readDataQueue.peek() & 0xff;
		if (160 == length) {
			isHead = true;
			readDataQueue.poll();
		}
		if(!isHead){
			readDataQueue.poll();
			return;
		}
		temp = readDataQueue.peek() & 0xff;
		int len = temp + 3;
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
			Log.w(TAG, "This is not full command");
			return;
		}
		if(datas.length <= 3){
			return;
		}
		temp = (datas[3] & 0xff);
		if (quencesequenceID != temp) {
			quencesequenceID = temp;
		} else {
			Log.v(TAG, "Duplicate command");
			return;
		}
		readBuffer = new byte[datas.length];
		mReadBufferLength = datas.length;
		for (int i = 0; i < datas.length; i++) {
			readBuffer[i] = datas[i];
		}

		isHead = false;
		commProtocol.unPackageData(readBuffer);
	}
	public void disConnectFunction(){
	    if(mSerial != null){
	        mSerial.end();
	        mSerial = null;
	    }
	}

	@Override
	public void sendData(String mac, byte[] data) {
		Log.p(TAG, Log.Level.VERBOSE,"sendData",mac,ByteBufferUtil.Bytes2HexString(data, data.length));
		if (null == mSerial){
			Log.w(TAG,"mSerial is null");
			return;
		}

		if (!mSerial.isConnected()){
			Log.w(TAG,"mSerial is not connected");
			return;
		}

		int res = mSerial.write(data, data.length);
		if (res < 0) {
			Log.w(TAG, "sendData() -- failed:" + res);
		}
	}

	@Override
	public void disconnect() {
		disConnectFunction();
	}

	@Override
	public void disconnect(String mac) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCommNotify(String mac, BaseCommProtocol dataCallBack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCommNotify(BaseCommProtocol dataCallBack) {
		commProtocol = dataCallBack;
	}

    /* (non-Javadoc)
     * @see com.ihealth.communication.base.comm.BaseComm#sendData(java.lang.String, java.lang.String, byte[])
     */
    @Override
    public void sendData(String mac, String deviceIP, byte[] data) {
        // TODO Auto-generated method stub
        
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
