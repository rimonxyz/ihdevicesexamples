# iHealth SDK FAQ

### 1. Android permission explaination

```
    <!-- Internet communication and detect / manage Wi-Fi state -->
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />

    <!-- Bluetooth permission for communicating with health devices -->
    <uses-permission android:name="android.permission.BLUETOOTH" />
    <uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />

    <!-- Permission for writing on device memory -->
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

    <!-- Permission for locating user device in order to search beacons -->
    <!-- https://developer.android.com/reference/android/bluetooth/le/BluetoothLeScanner.html#startScan(android.bluetooth.le.ScanCallback) -->
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />

    <!-- Permission used to read the region and language from the phone's settings. With the region and language, we choose the best iHealth server to verify the clientID clientSecret username) -->
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    
    <!-- Permission for iHealth BG1 device (which connect with phone by headphone jack) -->
    <uses-permission android:name="android.permission.RECORD_AUDIO" 
    
   ```


###2. User status    

```
1.New-user registration succeeded.
2.User login succeeded.
3.The user is iHealth user as well, measurement via SDK has been activated, and the data from the measurement belongs to the user.
4. testing without Internet connection succeeded.
5.Userid/clientID/clientSecret verification failed.
6.SDK has not been authorized.
7.User has not been authorized.
8.Internet error, verification failed.

–PS: The measurement via SDK will be operated in the case of 1-4, and will be terminated if any of 5-8 occurs. The interface needs to be re-called after analyzing the return parameters. SDK application requires Internet connection; there is 10-day tryout if SDK cannot connect Internet, SDK is fully functional during tryout period, but will be terminated without verification through Internet after 10 days.   

```

###3. Android 6.0 location permission

```
	If targetSdkVersion >= 23, need location permission as follows:
	
	<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>

	
	Turn location function for the phone.
	
```

[Location Permission](https://github.com/iHealthDeviceLabs/iHealthDeviceLabs-Android/blob/master/public/Location_Permission.png?raw=true)  
[Location Function](https://github.com/iHealthDeviceLabs/iHealthDeviceLabs-Android/blob/master/public/LocationFunction.jpg?raw=true)
