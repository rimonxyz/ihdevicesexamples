# iHealth SDK Release Note


### 1. V2.0
```
Description: New architecture compared V1.0
Release Date: 2015-12-14
```
More info:   
*[ReleaseNotes_DeviceManager.md](https://github.com/iHealthDeviceLabs/iHealthDeviceLabs-Android/blob/master/doc/ReleaseNotes_DeviceManager.md)*       
*[ReleaseNotes_AMDeviceManager.md](https://github.com/iHealthDeviceLabs/iHealthDeviceLabs-Android/blob/master/doc/ReleaseNotes_AMDeviceManager.md)*  
*[ReleaseNotes_BPDeviceManager.md](https://github.com/iHealthDeviceLabs/iHealthDeviceLabs-Android/blob/master/doc/ReleaseNotes_BPDeviceManager.md)*

### 2. V2.1.0
```
Description: Support BP7S KD550 
Release Date: 2016-1-11
```

### 3. V2.1.1
```
Description: Fix bug
Release Date: 2016-1-11
```


### 4. V2.2.0
```
Description: Support KD926
Release Date: 2016-1-20
```


### 5. V2.2.1
```
Description: Support bg g680
Release Date: 2016-2-05
```


### 6. V2.2.2
```
Description: 
	1.add weight to HS6
	2.solve no stage data of AM device 
Release Date: 2016-2-19
```


### 7. V2.2.3
```
Description: 
	1.modify po offline data
	2.modify time、step and calories error of AM device
	3.modify user target data of AM device
	4.modify repeat data of BP7S 550BT KD926
Release Date: 2016-3-02
```

### 8. V2.2.4
```
Description: 
	1.add dataid to callback data
	2.callback data up cloud
	3.modify real data to calories and total calories of 	AM device
		e.g.{
				"TotalCalories":1062,
				"step":0,
				"Calories":0
			}
	4.modify key
	5.modify some bugs
Release Date: 2016-5-05
```

### 9. V2.2.5
```
Description: 
	1.Optimize BG1Api interface
Release Date: 2016-5-12
```

### 10. V2.2.6
```
Description: 
	1. Fix BP3L measure bug
Release Date: 2016-5-13
```

### 11. V2.2.7
```
Description: 
	1.solve up cloud bug
Release Date: 2016-5-16
```

### 12. V2.2.8
```
Description: 
	1.Fix PO3 HS4 connection bug
	2.Add RSSI for Device with low energy
	Before:
	public void onScanDevice(String mac, String deviceType) {}
	After:
	public void onScanDevice(String mac, String deviceType, int rssi) {}
	3.Other modify
Release Date: 2016-6-8
```

### 13. V2.2.9
```
Description: 
	1.Fix register bug
Release Date: 2016-6-20
```

### 14. V2.3.0
```
Description: 
	1.Support OneCode
	2.Support Continua BP
	3.Support BTM
Release Date: 2016-6-28
```

### 15. V2.3.1
```
Description: 
	1. Support update the firmware of device (AM3 AM3S AM4 HS4 HS4S)
	2. Support FDTH
	3. Optimize the api documents
	4. Optimize the connection for BG1
	5. Optimize the connection for BLE device
	6. Check the paraments for all the api
	7. Support connect without discovery(BT BLE device)
	8. Add log version for debug
	9. Fix bugs
Release Date: 2016-10-21
```

### 15. V2.3.2
```
Description: 
	1. Support cache function for Api, you can call Api one by one without waiting the callback. 
	2. Optimize the connection for BG1
	3. Support GDH strip (OneCode) for BG1 BG5
	4. Support HS5 for Android 7.0
	5. Fix bugs
	6. New api to replace the old one
		
		/* iHealthDevicesManager */
		Deprecated:
			public boolean connectDevice(String userName, final String mac)
		New: 
			public boolean connectDevice(String userName, final String mac, final String type)
		
		Deprecated:
			public IDPS getIdps(String mac)
		New: 
			public String getDevicesIDPS(String mac)
			
		/* Bg5Control */
		Deprecated:
			public void setBottleMessage(final String QRCode)
			public void setBottleMessage(final String QRCode, final int stripNum, final String overDate)
		New: 
			public void setBottleMessageWithInfo(final int stripType, final int measureType, final String QRCode, final int stripNum, final String overDate)
			
			
		/* Bg1Control */
		Deprecated:
			public void sendCode(final String QRCode)
		New: 
			public void sendCode(final String QRCode, int stripType, int measureType) 
			
		/* Bp7Control */
		Deprecated:
			public void getAngle()
		New: 
			public void conformAngle()
				
			
Release Date: 2017-1-21
```

