package com.ihealth.communication.base.bt;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.params.Face;
import com.ihealth.communication.utils.Log;

import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.utils.ByteBufferUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

public class BtCommThreadEE extends Thread implements BaseComm{

	private boolean debug = true;
	private static final String TAG = "BtCommThreadEE";
	private BluetoothDevice mDevice = null;
	private BluetoothSocket mSocket = null;
	private InputStream mInputStream = null;
	private OutputStream mOutputStream = null;
	private int mReadBufferLen;
	private Context mContext;
	private byte[] mReadBuffer;
    private BaseCommCallback mBaseCommCallback;
	private boolean readEEFlag = false;

	public BtCommThreadEE(Context context, BluetoothDevice device, BluetoothSocket socket,BaseCommCallback baseCommCallback) throws IOException{
		this.mContext = context;
		this.mDevice = device;
		this.mSocket = socket;
		this.mInputStream = socket.getInputStream();
		this.mOutputStream = socket.getOutputStream();
		mReadBuffer = new byte[256];
        this.mBaseCommCallback = baseCommCallback;

	}

	@Override
	public void run() {
		while (true) {
			try {
				mReadBufferLen = mInputStream.read(mReadBuffer);
				String ee = "000";
				if(mReadBufferLen > 0){
					//取消计时
					if (timerTask != null) {
						timerTask.cancel();
						timerTask = null;
					}
					Log.p(TAG, Log.Level.VERBOSE,"Read", ByteBufferUtil.Bytes2HexString(mReadBuffer, mReadBufferLen));
					if(mReadBufferLen > 19) {
//						int temp1_0 = mReadBuffer[13];
//						if(debug)
//							Log.i("", "temp1_0:" + temp1_0);
//						int temp1_1 = mReadBuffer[14];
//						if(debug)
//							Log.i("", "temp1_1:" + temp1_1);
//						int temp2_0 = mReadBuffer[15];
//						if(debug)
//							Log.i("", "temp2_0:" + temp2_0);
//						int temp2_1 = mReadBuffer[16];
//						if(debug)
//							Log.i("", "temp2_1:" + temp2_1);
//						int temp3_0 = mReadBuffer[17];
//						if(debug)
//							Log.i("", "temp3_0:" + temp3_0);
//						int temp3_1 = mReadBuffer[18];
//						if(debug)
//							Log.i("", "temp3_1:" + temp3_1);
//						char tempc10 = (char) temp1_0;
//						char tempc11 = (char) temp1_1;
//						char tempc20 = (char) temp2_0;
//						char tempc21 = (char) temp2_1;
//						char tempc30 = (char) temp3_0;
//						char tempc31 = (char) temp3_1;
//						ee = String.valueOf(tempc10) + String.valueOf(tempc11) + String.valueOf(tempc20)
//								+ String.valueOf(tempc21) + String.valueOf(tempc30) + String.valueOf(tempc31);
						//jing 20160817  统一版本号格式： 100 代表1.0.0
						byte[] firmwareVersion = new byte[] {mReadBuffer[14],mReadBuffer[16],mReadBuffer[18]};
						ee = new String(firmwareVersion,"UTF-8");
					}
				}

				readEEFlag = true;
				Intent intent = new Intent(iHealthDevicesManager.IHEALTH_MSG_BG5_EE);
				intent.putExtra(iHealthDevicesManager.IHEALTH_DEVICE_MAC, mDevice.getAddress().replace(":", ""));
				intent.putExtra(iHealthDevicesManager.IHEALTH_DEVICE_NAME, mDevice.getName());
				intent.putExtra(iHealthDevicesManager.IHEALTH_MSG_BG5_EE_EXTRA, ee);
				intent.putExtra(BluetoothDevice.EXTRA_DEVICE, mDevice);//返回当前设备，继续连接
				//20160731 jing  加包名限制，防止多个App间影响
				intent.setPackage(mContext.getPackageName());

				mContext.sendBroadcast(intent);
			} catch (IOException e) {
				Log.w(TAG,"Read() -- Exception: " + e);
				if (readEEFlag == false) {
					mBaseCommCallback.onConnectionStateChange(mDevice.getAddress().replace(":", ""), mDevice.getName(), iHealthDevicesManager.DEVICE_STATE_CONNECTIONFAIL, 0, null);
					return;
				}
			}
		}
	}

	public void close(){
		try {
			if (this.mSocket != null) {
				this.mSocket.close();
			}
			this.mSocket = null;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			this.mSocket = null;
		}
	}

	Timer timer = new Timer();
	TimerTask timerTask;
	@Override
	public void sendData(String mac, byte[] data) {
		try {
			Log.p(TAG, Log.Level.VERBOSE,"sendData",mac,ByteBufferUtil.Bytes2HexString(data, data.length));
			this.mOutputStream.write(data);
			this.mOutputStream.flush();
			//20160728 jing 计时处理，BG5 2秒内未回复版本号，即可认为连接已失效
			timerTask = new TimerTask() {
				@Override
				public void run() {
					close();
				}
			};
			timer.schedule(timerTask,2000);
		} catch (IOException e) {
			Log.w(TAG,"sendData() -- IOException: " + e);
		}
	}

	@Override
	public void sendData(String mac, String deviceIP, byte[] data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void disconnect() {
		close();
	}

	@Override
	public void disconnect(String mac) {
		// TODO Auto-generated method stub
		close();
	}

	@Override
	public void addCommNotify(BaseCommProtocol dataCallBack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCommNotify(String mac, BaseCommProtocol dataCallBack) {
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
