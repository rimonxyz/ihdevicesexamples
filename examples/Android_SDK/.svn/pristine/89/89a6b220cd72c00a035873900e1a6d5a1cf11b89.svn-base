package com.ihealth.communication.base.ble;

import android.bluetooth.BluetoothDevice;
import java.util.List;
import java.util.UUID;

public interface BleCallback {

	void onConnectionStateChange(BluetoothDevice device, int status, int newState);
	void onServicesDiscovered(BluetoothDevice device, List<UUID> uuidlist, int status);
	void onScanResult(BluetoothDevice device, int rssi, byte[] status);
	void onServicesObtain();
	void onServicesObtain(UUID uuid, BluetoothDevice device, String para);
	void onCharacteristicChanged(BluetoothDevice device, byte[] data, UUID uuid);
	void onCharacteristicRead(BluetoothDevice device, byte[] data, UUID uuid, int status);
	void onCharacteristicWrite(BluetoothDevice device, UUID uuid, int status);
	void onDescriptorRead(byte[] data, UUID uuid, int status);
	void onDescriptorWrite();
	void onRssi(int rssi);
}