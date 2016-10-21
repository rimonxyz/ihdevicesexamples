package com.ihealth.communication.control;

import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.manager.iHealthDevicesUpgradeManager;

/**
 * Public APIs for the Upgrade serials device profiles
 * <p> Clients need to call{@link iHealthDevicesManager#registerClientCallback} register.
 * When new data from Upgrade device, The Callback{#iHealthDevicesCallback #onDeviceNotify} will be triggered.
 *
 */
public abstract class UpgradeProfile {

    /**
     * The action type of callback after {@link iHealthDevicesUpgradeManager#queryUpgradeInfoFromDeviceAndCloud} method is called.<br/>
     * <b>See Also:</b><br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>{@link #DEVICE_UPGRADE_FLAG}</li>
     * <li>{@link #DEVICE_FIRMWARE_VERSION}</li>
     * <li>{@link #DEVICE_UP_FLAG}</li>
     * <li>{@link #DEVICE_UP_MODE}</li>
     * </ul>
     * <b>message example:</b><br/>
     * {<br/>
     * &nbsp; &nbsp; "up.device.upgrade.flag": 0,<br/>
     * &nbsp; &nbsp; "up.device.firmware.version": "1.3.7",<br/>
     * &nbsp; &nbsp; "status": 0,<br/>
     * &nbsp; &nbsp; "up.device.up.mode": "101"<br/>
     * }<br/>
     */
    public static final String ACTION_DEVICE_UP_INFO = "action.up.device.up.info";
    /**
     * The key of the upgrade resume state.<br/>
     * <b>Value:</b><br/>
     * <ul>
     * <li>0 indicates Upgrade from begin</li>
     * <li>1 indicates Upgrade resume from last time.</li>
     * </ul>
     */
    public static final String DEVICE_UPGRADE_FLAG = "up.device.upgrade.flag";
    /**
     * The key of the device firmware version.
     */
    public static final String DEVICE_FIRMWARE_VERSION = "up.device.firmware.version";
    /**
     * The key of the device upgrade status.<br/>
     * <b>Value:</b><br/>
     * <ul>
     * <li>0 indicates it's ready to upgrade</li>
     * <li>1 indicates low power, not allow to upgrade.</li>
     * </ul>
     */
    public static final String DEVICE_UP_FLAG = "status";
    /**
     * The type of the device upgrade mode.
     * <b>Value:</b><br/>
     * <ul>
     * <li>100 indicates AM3 PO3</li>
     * <li>101 indicates AM3S AM4 HS4 HS4S.</li>
     * </ul>
     */
    public static final String DEVICE_UP_MODE = "up.device.up.mode";
    /**
     * device upgrade device type
     */
    public static final String DEVICE_TYPE = "up.device.up.type";
    /**
     * device upgrade device mode
     */
    public static final String DEVICE_MODE = "up.device.up.mode";
    /**
     * device upgrade device hardware version
     */
    public static final String DEVICE_HARDWARE_VERSION = "up.device.hardware.version";


    /**
     * The action type of callback after {@link iHealthDevicesUpgradeManager#queryUpgradeInfoFromDeviceAndCloud} method is called.<br/>
     * <b>See Also:</b><br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>{@link #DEVICE_CLOUD_FIRMWARE_VERSION}</li>
     * <li>{@link #DEVICE_MANDATORY_FLAG}</li>
     * </ul>
     * <b>message example:</b><br/>
     * {<br/>
     * &nbsp; &nbsp; "up.device.cloud.firmware.version": "1.3.9",<br/>
     * &nbsp; &nbsp; "up.device.mandatory.flag": 1<br/>
     * }<br/>
     */
    public static final String ACTION_DEVICE_CLOUD_FIRMWARE_VERSION = "action.device.cloud.firmware_version";
    /**
     * The key of the latest firmware version on cloud
     */
    public static final String DEVICE_CLOUD_FIRMWARE_VERSION = "up.device.cloud.firmware.version";
    /**
     * The key of the firmware upgrade option.<br/>
     * <b>Value:</b><br/>
     * <ul>
     * <li>0 indicates the upgrade is not mandatory.</li>
     * <li>1 indicates the upgrade is mandatory.</li>
     * </ul>
     */
    public static final String DEVICE_MANDATORY_FLAG = "up.device.mandatory.flag";





    /**
     * The action type of callback after {@link iHealthDevicesUpgradeManager#startUpGrade} method is called.<br/>
     * It indicates starting to download firmware from cloud.<br/>
     * <b>See Also:</b><br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     */
    public static final String ACTION_DEVICE_UP_START_DOWNLOAD = "action.up.start.download";

    /**
     * The action type of callback after {@link iHealthDevicesUpgradeManager#startUpGrade} method is called.<br/>
     * It indicates download firmware from cloud completely.<br/>
     * <b>See Also:</b><br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     */
    public static final String ACTION_DEVICE_UP_DOWNLOAD_COMPLETED = "action.up.download.completed";


    /**
     * The action type of callback after {@link iHealthDevicesUpgradeManager#startUpGrade} method is called.<br/>
     * It indicates starting to transmit firmware to device.<br/>
     * <b>See Also:</b><br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>{@link #DEVICE_START_UP_FLAG}</li>
     * </ul>
     * <b>message example:</b><br/>
     * {<br/>
     * &nbsp; &nbsp; "status": 0<br/>
     * }<br/>
     */
    public static final String ACTION_DEVICE_START_UP = "action.device.start.up";
    /**
     * The key of the device status for upgrade.<br/>
     * <b>Value:</b><br/>
     * <ul>
     * <li>0 indicates the device is ready to upgrade.</li>
     * <li>1 indicates low power, can not start to upgrade.</li>
     * </ul>
     */
    public static final String DEVICE_START_UP_FLAG = "status";



    /**
     * The action type of callback after {@link iHealthDevicesUpgradeManager#startUpGrade} method is called.<br/>
     * It indicates firmware transmission is in progress.<br/>
     * <b>See Also:</b><br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>{@link #DEVICE_PROGRESS_VALUE}</li>
     * </ul>
     * <b>message example:</b><br/>
     * {<br/>
     * &nbsp; &nbsp; "progress": 45<br/>
     * }<br/>
     */
    public static final String ACTION_DEVICE_UP_PROGRESS = "com.device.up.progress";
    /**
     * The key of the progress of the firmware transmission.<br/>
     * <b>Value range:</b><br/>
     * 0-100
     */
    public static final String DEVICE_PROGRESS_VALUE = "progress";




    /**
     * The action type of callback after {@link iHealthDevicesUpgradeManager#startUpGrade} method is called.<br/>
     * It indicates firmware transmission finishes.<br/>
     * <b>See Also:</b><br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>{@link #DEVICE_FINISH_UP_FLAG}</li>
     * </ul>
     * <b>message example:</b><br/>
     * {<br/>
     * &nbsp; &nbsp; "status": 1<br/>
     * }<br/>
     */
    public static final String ACTION_DEVICE_FINISH_UP = "action.up.device.finish.up";
    /**
     * The key of the upgrade result.<br/>
     * <b>Value:</b><br/>
     * <ul>
     * <li>0 indicates upgrade failed.</li>
     * <li>1 indicates upgrade/transmit successfully.</li>
     * </ul>
     * <b>Note:</b><br/>
     * <ul>
     * <li>If the value is 1, if DEVICE_UP_MODE == 100, it indicates transmit successfully, device will upgrade itself.</li>
     * <li>If the value is 1, if DEVICE_UP_MODE == 101, it indicates upgrade successfully.</li>
     * <li>If the value is 1, device will disconnect automatically, need to discovery and connect again.</li>
     * </ul>
     */
    public static final String DEVICE_FINISH_UP_FLAG = "status";

    /**
     * The action type of callback after {@link iHealthDevicesUpgradeManager#stopUpgrade(String, String)} method is called.<br/>
     * It indicates stop upgrade successfully.<br/>
     * <b>See Also:</b><br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}
     */
    public static final String ACTION_DEVICE_STOP_UP = "action.up.device.stop.up";



    /**
     * The action type of callback indicating the error of upgrade.<br/>
     * <b>KeyList of the message:</b>
     * <ul>
     * <li>{@link #DEVICE_UP_ERROR}</li>
     * </ul>
     * <b>Example message:</b><br/>
     * {<br/>
     * &nbsp; &nbsp; "device.up.error": 301<br/>
     * }<br/>
     */
    public static final String ACTION_DEVICE_ERROR = "action.device.error";
    /**
     * The key of the error code of upgrade.<br/>
     * <b>Value:</b><br/>
     * <ul>
     * <li>200 indicates network error.</li>
     * <li>201 indicates no call {@link iHealthDevicesUpgradeManager#queryUpgradeInfoFromDeviceAndCloud(String, String)}
     * before {@link iHealthDevicesUpgradeManager#startUpGrade(String, String)} </li>
     * <li>300 indicates device connection error.</li>
     * <li>301 indicates device stop upgrade.</li>
     * <li>302 indicates invalid input parameters.</li>
     * <li>400 indicates no need to upgrade.</li>
     * </ul>
     */
    public static final String DEVICE_UP_ERROR = "device.up.error";
}
