package com.ihealth.communication.base.ble;

import android.bluetooth.BluetoothDevice;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface BtleCallback {

	void onConnectionStateChange(BluetoothDevice device, int status, int newState);
	void onServicesDiscovered(BluetoothDevice device, List<UUID> uuids, int status);
	void onScanResult(BluetoothDevice scandevice, int rssi, String serviceUuid, Map<String, Object> manufactorData);
	void onCharacteristicChanged(BluetoothDevice device, byte[] characteristicChangedValue);
	void onCharacteristicRead(byte[] characteristicReadValue, UUID charUuid, int status);
	void onServicesObtain();
}
