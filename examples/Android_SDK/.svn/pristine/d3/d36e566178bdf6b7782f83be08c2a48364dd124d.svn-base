
package com.ihealth.communication.base.ble;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.os.Build;
import android.os.SystemClock;

import com.ihealth.communication.utils.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class Ble {

    private static String TAG = "Runtime_ble";
    private Context mContext;
    private BleCallback mBleCallback;
    private BluetoothAdapter mBluetoothAdapter;
    private Map<String, BluetoothGatt> mapBleGatt = new ConcurrentHashMap<String, BluetoothGatt>();
    private Map<String, BluetoothGattCharacteristic> mapBleRec = new ConcurrentHashMap<String, BluetoothGattCharacteristic>();
    private Map<String, BluetoothGattCharacteristic> mapBleTrans = new ConcurrentHashMap<String, BluetoothGattCharacteristic>();
    //jing 20160524
    private List<String> reConnectMacList = new ArrayList<String>();
    private List<String> connectingMacList = new ArrayList<String>();
    private List<String> connectedMacList = new ArrayList<String>();
    private int connectTimeoutInterval = 20000;


    public Ble(Context context, BleCallback bleCallback) {
        this.mContext = context;
        this.mBleCallback = bleCallback;
        BluetoothManager bluetoothManager = (BluetoothManager) mContext.getSystemService(Context.BLUETOOTH_SERVICE);
        this.mBluetoothAdapter = bluetoothManager.getAdapter();
        this.mapBleGatt.clear();
        this.mapBleTrans.clear();
        this.mapBleTrans.clear();
        //jing 20160524
        this.reConnectMacList.clear();
        this.connectingMacList.clear();
        this.connectedMacList.clear();
    }

    public BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        public void onLeScan(BluetoothDevice arg0, int arg1, byte[] arg2) {
            mBleCallback.onScanResult(arg0, arg1, arg2);
        }
    };

    private BluetoothGatt mBluetoothGatt;

    private final BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {

        @Override
        public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
            mBleCallback.onRssi(rssi);
        }

        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
        }

        public synchronized void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            String address = gatt.getDevice().getAddress();
            String mac = address.replace(":", "");
            Log.p(TAG, Log.Level.VERBOSE, "onConnectionStateChange", mac, status, newState);
            if (status == BluetoothGatt.GATT_SUCCESS) {
                if (newState == BluetoothProfile.STATE_CONNECTED) {
                    mapBleGatt.put(mac, mBluetoothGatt);
                    //jing 20160612 取消连接超时
                    commandCancelTimeoutForDevice(address);
                    //jing 20160524
                    if (reConnectMacList.contains(address)) {
                        reConnectMacList.remove(address);
                    }
                    if (connectingMacList.contains(address)) {
                        connectingMacList.remove(address);
                    }
                    if (!(connectedMacList.contains(address))) {
                        connectedMacList.add(address);
                    } else {
                        Log.v(TAG, "Duplicate connection success");
                        return;
                    }
                    Log.v(TAG, "Connection Success");
                    mBleCallback.onConnectionStateChange(gatt.getDevice(), status, newState);
                    SystemClock.sleep(300);
                    gatt.discoverServices();
                } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                    //判断是正常断开？还是连接失败？以及连接失败是否需要重连？
                    commandCheckReconnectDevice(gatt, status, newState);
                } else {
                    mBleCallback.onConnectionStateChange(gatt.getDevice(), status, newState);
                }

            } else {
                //判断是正常断开？还是连接失败？以及连接失败是否需要重连？
                commandCheckReconnectDevice(gatt, status, newState);
            }

        }


        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                List<BluetoothGattService> mslist = gatt.getServices();
                Log.p(TAG, Log.Level.VERBOSE, "onServicesDiscovered", mslist.size());
                List<UUID> uuids = new ArrayList<UUID>();
                for (BluetoothGattService bluetoothGattService : mslist) {
                    uuids.add(bluetoothGattService.getUuid());
                    for (BluetoothGattCharacteristic bc : bluetoothGattService.getCharacteristics()) {
                        uuids.add(bc.getUuid());
                    }
                }
                mBleCallback.onServicesDiscovered(gatt.getDevice(), uuids, status);
            } else {
                Log.e(TAG, "onServicesDiscovered() -- failed");
                gatt.disconnect();
            }
        }

        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.p(TAG, Log.Level.VERBOSE, "onDescriptorWrite", "success");
                UUID characterUUID = descriptor.getCharacteristic().getUuid();
                if (isSettingNotification) {
                    //20160630 jing 可以删除，但是为了兼容新旧版本，暂不删除
                    mBleCallback.onServicesObtain();
                    mBleCallback.onServicesObtain(characterUUID, gatt.getDevice(), null);
                    isSettingNotification = false;

                } else if (isSettingIndication) {
                    //20160630 jing 可以删除，但是为了兼容新旧版本，暂不删除
                    mBleCallback.onServicesObtain();
                    mBleCallback.onServicesObtain(characterUUID, gatt.getDevice(), null);
                    isSettingIndication = false;
                }
            } else {
                Log.e(TAG, "onDescriptorWrite() -- failed");
                gatt.disconnect();
            }
        }

        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                BluetoothDevice device = gatt.getDevice();
                byte[] charac = characteristic.getValue();
                UUID uuid = characteristic.getUuid();
                mBleCallback.onCharacteristicRead(device, charac, uuid, status);
            } else {
                Log.e(TAG, "onCharacteristicRead() -- failed");
                gatt.disconnect();
            }
        }

        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            UUID uuid = characteristic.getUuid();
            mBleCallback.onCharacteristicChanged(gatt.getDevice(), characteristic.getValue(), uuid);
        }

        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                mBleCallback.onCharacteristicWrite(gatt.getDevice(), characteristic.getUuid(), status);
            } else {
                Log.e(TAG, "onCharacteristicWrite() -- failed");
            }
        }

    };

    private void close(String mac) {
        SystemClock.sleep(500);
        if (mapBleGatt.get(mac) != null) {
            mapBleGatt.get(mac).disconnect();
            mapBleGatt.get(mac).close();
            mapBleGatt.remove(mac);
            mapBleTrans.remove(mac);
            mapBleRec.remove(mac);
        } else {
            if (mBluetoothGatt != null) {
                mBluetoothGatt.disconnect();
                mBluetoothGatt.close();
            }
            mBluetoothGatt = null;
        }
    }


    private BluetoothGattService mGattServiceIDPS;
    private BluetoothGattService mGattServiceComm;
    private BluetoothGattCharacteristic mGattCharacteristicTrans;
    private BluetoothGattCharacteristic mGattcharacteristicReceive;
    private String CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9b34fb";

    public void scan(boolean b) {
        Log.p(TAG, Log.Level.VERBOSE, "scan", b);
        if (b) {
            mBluetoothAdapter.startLeScan(mLeScanCallback);
        } else {
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
    }

    public boolean connectDevice(String address) {
        Log.p(TAG, Log.Level.VERBOSE, "connectDevice", address, mBluetoothAdapter);
        if ((this.mBluetoothAdapter == null) || (address == null)) {
            return false;
        }
        //jing 20160524
        //判断传入的设备是否正在连接或已经连接成功，满足任一条件，均返回false，防止重复连接
        if (connectingMacList.size() > 0 || connectingMacList.contains(address) || connectedMacList.contains(address)) {
            return false;
        }
        connectingMacList.add(address);


        BluetoothDevice device = this.mBluetoothAdapter.getRemoteDevice(address);
        if (device == null) {
            Log.e(TAG, "Device not found.  Unable to connect.");
            return false;
        }
        this.mBluetoothGatt = device.connectGatt(this.mContext, false, this.mGattCallback);
        if (this.mBluetoothGatt != null) {
            //jing 20160612 增加连接超时机制
            commandAddTimeoutForDevice(address, this.mBluetoothGatt, 0, 0);
            return true;
        }
        return false;
    }

    //连接计时器
    private Timer connectTimer = new Timer();
    private TimerTask connectTimerTask = null;

    //启动连接计时
    private void commandAddTimeoutForDevice(final String mac, final BluetoothGatt gatt, final int status, final int newState) {
        if (connectTimerTask != null) {
            connectTimerTask.cancel();
            connectTimerTask = null;
        }
        connectTimerTask = new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.w(TAG, "commandAddTimeoutForDevice() time out");
                        Ble.this.disconnect(mac);
                        SystemClock.sleep(300);
                        commandCheckReconnectDevice(gatt, 0, 0);
                    }
                }).start();
            }

        };
        //连接超时时间
        connectTimer.schedule(connectTimerTask, connectTimeoutInterval);
    }

    //取消连接计时
    private void commandCancelTimeoutForDevice(final String mac) {
        if (connectTimerTask != null) {
            connectTimerTask.cancel();
            connectTimerTask = null;
        }
    }


    //判断是正常断开？还是连接失败？以及连接失败是否需要重连？
    private void commandCheckReconnectDevice(BluetoothGatt gatt, int status, int newState) {
        String address = gatt.getDevice().getAddress();
        String mac = address.replace(":", "");
        //jing 20160612 取消连接超时
        commandCancelTimeoutForDevice(address);
        //jing 20160524
        if (connectedMacList.contains(address)) {
            connectedMacList.remove(address);
        }

        //jing 20160524   是否需要二次重连标示位
        boolean needReconnectFlag = false;

        //判断是断开连接，还是连接失败。true，代表连接失败；false，代表正常断开
        if (connectingMacList.contains(address)) {
            //判断是否进行内部二次重连。false，需进行二次重连；true，不需进行二次重连
            if (!(reConnectMacList.contains(address))) {
                boolean reconnectFlag = gatt.connect();
                if (reconnectFlag == true) {
                    Log.v(TAG, "Reconnecting...");
                    reConnectMacList.add(address);
                    //jing 20160612 增加连接超时机制
                    needReconnectFlag = true;
                    commandAddTimeoutForDevice(address, gatt, status, newState);
                }
            }
        } else {
            Log.v(TAG, "DisConnection Success");
        }
        //不需要重连，或重连初始化失败
        if (needReconnectFlag == false) {
            close(mac);
            reConnectMacList.remove(address);
            if (connectingMacList.contains(address) == true) {
                connectingMacList.remove(address);
            }
            mBleCallback.onConnectionStateChange(gatt.getDevice(), status, newState);
        } else {
            //返回正在重连的状态
            mBleCallback.onConnectionStateChange(gatt.getDevice(), status, 4);
        }
    }

    public boolean readRemoteRssi(String mac) {
        if (null == mapBleGatt.get(mac)) {
            return false;
        } else {
            return mapBleGatt.get(mac).readRemoteRssi();

        }
    }

    public void disconnect(String mac) {
        Log.p(TAG, Log.Level.VERBOSE, "disconnect", mac);
        if (mac.length() != 12) {
            return;
        }
        String address = "";
        for (int i = 0; i < 6; i++) {
            address = address + mac.substring(2 * i, 2 * i + 2);
            if (i < 5) {
                address = address + ":";
            }
        }
        //主动断开后，清除正在连接缓存  停止连接计时
        if (connectingMacList.contains(address)) {
            connectingMacList.remove(address);
            commandCancelTimeoutForDevice(mac);
        }
        BluetoothGatt bluetoothGatt = (BluetoothGatt) mapBleGatt.get(mac);
        BluetoothGattCharacteristic bluetoothCharacteristic = mapBleRec.get(mac);
        if (bluetoothGatt != null) {
            disableNotifications(bluetoothGatt, bluetoothCharacteristic);
            SystemClock.sleep(300);
            bluetoothGatt.disconnect();

        } else if (mBluetoothGatt != null) {
            disableNotifications(bluetoothGatt, mGattcharacteristicReceive);
            SystemClock.sleep(300);
            mBluetoothGatt.disconnect();
        }
    }

    void getCommService(String mac, UUID commUuid, UUID transUuid, UUID receUuid, UUID idpsUuid, boolean needIndication) {
        BluetoothGatt bluetoothGatt = mapBleGatt.get(mac);
        if (bluetoothGatt == null) {
            Log.e(TAG, "bluetoothGatt -- null");
            return;
        }
        mGattServiceIDPS = bluetoothGatt.getService(idpsUuid);
        if (mGattServiceIDPS == null) {
            Log.e(TAG, "mGattServiceIDPS --- ERROR");
            bluetoothGatt.disconnect();
            return;
        }

        mGattServiceComm = bluetoothGatt.getService(commUuid);
        if (mGattServiceComm == null) {
            Log.e(TAG, "mGattServiceComm --- ERROR");
            bluetoothGatt.disconnect();
            return;
        }

        if (transUuid != null)
            mGattCharacteristicTrans = mGattServiceComm.getCharacteristic(transUuid);

        if (mGattCharacteristicTrans == null) {
            List<BluetoothGattService> servicesList = bluetoothGatt.getServices();
            for (BluetoothGattService tempService : servicesList) {
                List<BluetoothGattCharacteristic> characterList = tempService.getCharacteristics();
                for (BluetoothGattCharacteristic tempCharacteristic : characterList) {
                    if (tempCharacteristic.getUuid().equals(transUuid)) {
                        mGattCharacteristicTrans = tempCharacteristic;
                        break;
                    }
                }
            }
        }


        if (mGattCharacteristicTrans != null) {
            mGattCharacteristicTrans.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE);
            mapBleTrans.put(mac, mGattCharacteristicTrans);
        } else {
            Log.v(TAG, "mGattCharacteristicTrans -- Null");
        }

        mGattcharacteristicReceive = mGattServiceComm.getCharacteristic(receUuid);
        if (mGattcharacteristicReceive == null) {
            Log.v(TAG, "mGattcharacteristicReceive --- ERROR");
        }

        SystemClock.sleep(300);

        boolean enableResult;
        if (needIndication) {
            enableResult = enableIndications(mGattcharacteristicReceive, bluetoothGatt);
        } else {
            enableResult = enableNotifications(mGattcharacteristicReceive, bluetoothGatt);
        }
        if (enableResult) {
            if (!needIndication) {
                mapBleRec.put(mac, mGattcharacteristicReceive);
            }
        } else {
            Log.e(TAG, "enableNotifications(true, mGattcharacteristicReceive) --- failed");
            bluetoothGatt.disconnect();
        }
    }

    public boolean readCharacteristicExtra(String mac, UUID serviceUuid, UUID characteristicUUid) {
        BluetoothGatt bluetoothGatt = mapBleGatt.get(mac);
        if (bluetoothGatt == null || serviceUuid == null || characteristicUUid == null)
            return false;

        BluetoothGattService mGattService = bluetoothGatt.getService(serviceUuid);
        if (mGattService == null)
            return false;

        BluetoothGattCharacteristic mGattCharacteristic = mGattService.getCharacteristic(characteristicUUid);
        if (mGattCharacteristic == null)
            return false;

        return bluetoothGatt.readCharacteristic(mGattCharacteristic);
    }

    public void readCharacteristic(UUID uuid) {
        if (mGattServiceIDPS == null) {
            Log.w(TAG, "Device Information Service Not Found!!!");
            return;
        }
        BluetoothGattCharacteristic mMNS = mGattServiceIDPS.getCharacteristic(uuid);
        if (mMNS == null) {
            boolean result = refreshDeviceCache(mBluetoothGatt);
            refreshGatt = mBluetoothGatt;
            cancelRefresh();

            refreshTimer = new Timer();
            refreshTask = new TimerTask() {

                @Override
                public void run() {
                    refreshGatt.disconnect();
                }
            };
            refreshTimer.schedule(refreshTask, 4000);
            return;
        }
        mBluetoothGatt.readCharacteristic(mMNS);
    }

    private boolean isSettingNotification = false;

    private final boolean enableNotifications(final BluetoothGattCharacteristic characteristic, BluetoothGatt bluetoothGatt) {
        final BluetoothGatt gatt = bluetoothGatt;
        if (gatt == null || characteristic == null)
            return false;

        if (!(gatt.setCharacteristicNotification(characteristic, true)))
            return false;

        BluetoothGattDescriptor clientConfig = characteristic.getDescriptor(UUID.fromString(this.CLIENT_CHARACTERISTIC_CONFIG));
        if (clientConfig == null)
            return false;

        Log.v(TAG, "enable notification");
        isSettingNotification = true;
        clientConfig.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);

        return mBluetoothGatt.writeDescriptor(clientConfig);
    }

    private boolean disableNotifications(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic characteristic) {
        final BluetoothGatt gatt = bluetoothGatt;
        if (gatt == null || characteristic == null)
            return false;

        if (!(gatt.setCharacteristicNotification(characteristic, false)))
            return false;

        BluetoothGattDescriptor clientConfig = characteristic.getDescriptor(UUID.fromString(this.CLIENT_CHARACTERISTIC_CONFIG));
        if (clientConfig == null)
            return false;

        Log.v(TAG, "disable notification");
        clientConfig.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);

        return gatt.writeDescriptor(clientConfig);
    }

    private boolean isSettingIndication = false;

    private final boolean enableIndications(final BluetoothGattCharacteristic characteristic, BluetoothGatt bluetoothGatt) {
        isSettingIndication = true;
        final BluetoothGatt gatt = bluetoothGatt;
        if (gatt == null || characteristic == null) {
            Log.w(TAG, "gatt == null || characteristic == null");
            return false;
        }


//		final int properties = characteristic.getProperties();
//		if ((properties & BluetoothGattCharacteristic.PROPERTY_INDICATE) == 0)
//			return false;
        if (!(gatt.setCharacteristicNotification(characteristic, true)))
            return false;

        Log.v(TAG, "enable indications");
        final BluetoothGattDescriptor descriptor = characteristic.getDescriptor(UUID.fromString(this.CLIENT_CHARACTERISTIC_CONFIG));
        if (descriptor != null) {
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
            return gatt.writeDescriptor(descriptor);
        }
        return false;
    }

    public void sendData(String mac, byte[] command) {
        BluetoothGatt mBluetoothGatt = (BluetoothGatt) this.mapBleGatt.get(mac);
        BluetoothGattCharacteristic mGattCharacteristicTrans = (BluetoothGattCharacteristic) this.mapBleTrans.get(mac);
        if (mBluetoothGatt == null) {
            Log.e(TAG, "sendData() not find valid device");
            return;
        }
        if (mGattCharacteristicTrans == null) {
            Log.e(TAG, "mGattCharacteristicTrans == null");
            return;
        }
        if (mGattCharacteristicTrans.getUuid().toString().equals("0000ff03-0000-1000-8000-00805f9b34fb")) {
            mGattCharacteristicTrans.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT);
        }
        mGattCharacteristicTrans.setValue(command);
        mBluetoothGatt.writeCharacteristic(mGattCharacteristicTrans);
    }

    private Timer refreshTimer;
    private TimerTask refreshTask;
    private BluetoothGatt refreshGatt;

    public boolean refresh(String mac) {
        BluetoothGatt bluetoothGatt = (BluetoothGatt) mapBleGatt.get(mac);
        if (bluetoothGatt != null) {
            refreshGatt = bluetoothGatt;
            cancelRefresh();

            refreshTimer = new Timer();
            refreshTask = new TimerTask() {

                @Override
                public void run() {
                    refreshGatt.disconnect();
                }
            };
            refreshTimer.schedule(refreshTask, 4000);
            return refreshDeviceCache((BluetoothGatt) mapBleGatt.get(mac));
        } else {
            return false;
        }
    }

    private boolean refreshDeviceCache(BluetoothGatt gatt) {
        try {
            BluetoothGatt localBluetoothGatt = gatt;
            Method localMethod = localBluetoothGatt.getClass().getMethod("refresh", new Class[0]);
            if (localMethod != null) {
                boolean bool = ((Boolean) localMethod.invoke(localBluetoothGatt, new Object[0])).booleanValue();
                return bool;
            }
        } catch (Exception localException) {
            Log.w(TAG, "refreshDeviceCache() -- Exception: " + localException);
        }
        return false;
    }

    private void cancelRefresh() {
        if (refreshTask != null) {
            refreshTask.cancel();
            refreshTask = null;
        }
        if (refreshTimer != null) {
            refreshTimer.cancel();
            refreshTimer = null;
        }
    }

    //jing 20160607清空防止二次重连的相关缓存  针对手动开关蓝牙引起的bug
    public void commandClearCache() {
        this.reConnectMacList.clear();
        this.connectingMacList.clear();
        this.connectedMacList.clear();
    }
}
