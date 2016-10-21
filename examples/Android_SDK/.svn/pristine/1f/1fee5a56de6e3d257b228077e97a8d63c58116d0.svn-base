
package com.ihealth.communication.control;

import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;

/**
 * Public APIs for the Hs serials device profiles
 * <p>
 * Clients need to call {@link iHealthDevicesManager#registerClientCallback} register. When new data from Hs
 * device, The Callback {#iHealthDevicesCallback #onDeviceNotify} can be triggered should call.
 */
public interface HsProfile {

    /**
     * The action type of callback indicating the error of HS device.<br/>
     * <b>KeyList of the message:</b>
     * <ul>
     * <li>{@link #ERROR_NUM_HS}</li>
     * <li>{@link #ERROR_DESCRIPTION_HS}</li>
     * </ul>
     * <b>Example message:</b><br/>
     * {<br/>
     * &nbsp; &nbsp; "error": 400,<br/>
     * &nbsp; &nbsp; "description": "getOfflineData() parameter userPstCode should be in the range [0, 19]."<br/>
     * }<br/>
     */
    public static final String ACTION_ERROR_HS = "error_hs";

    /**
     * The key of error ID number of HS device.<br/>
     * <b>Value:</b>
     * <ul>
     * <li>{@link #ERROR_ID_ILLEGAL_ARGUMENT} indicates parameter error.</li>
     * <li>{@link #ERROR_ID_WIFI_DISABLED} indicates wifi is disabled.</li>
     * </ul>
     */
    public static final String ERROR_NUM_HS = "error";
    /**
     * The error ID indicates parameter error.
     */
    public static final int ERROR_ID_ILLEGAL_ARGUMENT = 400;
    /**
     * The error ID indicates wifi is disabled.
     */
    public static final int ERROR_ID_WIFI_DISABLED = 500;
    /**
     * The key of error description.<br/>
     * The value string will show the detail description of the error.
     */
    public static final String ERROR_DESCRIPTION_HS = "description";

    /**
     * The action type of callback after startMeasure() method is called and measurement finish.<br/>
     * <b>See Also:</b><br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>{@link #DATAID}</li>
     * <li>{@link #WEIGHT_HS}</li>
     * <li>{@link #FAT_HS}</li>
     * <li>{@link #WATER_HS}</li>
     * <li>{@link #MUSCLE_HS}</li>
     * <li>{@link #SKELETON_HS}</li>
     * <li>{@link #FATELEVEL_HS}</li>
     * <li>{@link #DCI_HS}</li>
     * </ul>
     * <b>message example:</b><br/>
     * {<br/>
     * &nbsp; &nbsp; "dataID": "D433708291F0427F8D412268B91C44F3",<br/>
     * &nbsp; &nbsp; "value": 84.19999694824219,<br/>
     * &nbsp; &nbsp; "fat": 47.70000076293945,<br/>
     * &nbsp; &nbsp; "water": 45.29999923706055,<br/>
     * &nbsp; &nbsp; "muscle": 41.29999923706055,<br/>
     * &nbsp; &nbsp; "skeleton": 2.200000047683716,<br/>
     * &nbsp; &nbsp; "fatelevel": 8,<br/>
     * &nbsp; &nbsp; "DCI": 2502<br/>
     * }<br/>
     */
    public static final String ACTION_ONLINE_RESULT_HS = "online_result_hs";

    /**
     * The action type of callback after getOfflineData() method is called.<br/>
     * <b>See Also:</b><br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>{@link #HISTORDATA__HS}</li>
     * <li>{@link #DATAID}</li>
     * <li>{@link #MEASUREMENT_DATE_HS}</li>
     * <li>{@link #WEIGHT_HS}</li>
     * <li>{@link #FAT_HS}</li>
     * <li>{@link #WATER_HS}</li>
     * <li>{@link #MUSCLE_HS}</li>
     * <li>{@link #SKELETON_HS}</li>
     * <li>{@link #FATELEVEL_HS}</li>
     * <li>{@link #DCI_HS}</li>
     * </ul>
     * <b>message example:</b><br/>
     * {<br/>
     * &nbsp; &nbsp; "historyData": [<br/>
     * &nbsp; &nbsp; &nbsp; &nbsp; {<br/>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "dataID": "001DC911F3A014698592218400000000",<br/>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "date": "2016-07-30 14:13:41",<br/>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "value": 84.19999694824219,<br/>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "fat": 49.599998474121094,<br/>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "water": 44.79999923706055,<br/>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "muscle": 39.400001525878906,<br/>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "skeleton": 2.200000047683716,<br/>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "fatelevel": 8,<br/>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "DCI": 2408<br/>
     * &nbsp; &nbsp; &nbsp; &nbsp; }<br/>
     * &nbsp; &nbsp; ]<br/>
     * }<br/>
     */
    public static final String ACTION_HISTORICAL_DATA_HS = "historicaldata_hs";

    /**
     * The key of the measurement time.<br/>
     * <b>Value format:</b>
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String MEASUREMENT_DATE_HS = "date";

    /**
     * Callback indicating number of historical data for Hs device.
     */
    public static final String ACTION_HISTORICAL_NUM_HS = "historicalnum_hs";
    /**
     * Callback indicating no historical data for Hs device.
     */
    public static final String ACTION_NO_HISTORICALDATA = "noHistoryData";
    /**
     * The key of the whole data of history data.<br/>
     * <b>Value:</b><br/>
     * The value is JSONArray, contains all the history data.<br/>
     * The keys of the item are:<br/>
     * <li>{@link #DATAID}</li>
     * <li>{@link #MEASUREMENT_DATE_HS}</li>
     * <li>{@link #WEIGHT_HS}</li>
     * <li>{@link #FAT_HS}</li>
     * <li>{@link #WATER_HS}</li>
     * <li>{@link #MUSCLE_HS}</li>
     * <li>{@link #SKELETON_HS}</li>
     * <li>{@link #FATELEVEL_HS}</li>
     * <li>{@link #DCI_HS}</li>
     * </ul>
     */
    public static final String HISTORDATA__HS = "historyData";

    /**
     * Callback indicating power of Battery for Hs device.
     */
    public static final String ACTION_BATTERY_HS = "battery_hs";

    /**
     * The power of Battery for Hs device.
     */
    public static final String BATTERY_HS = "battery";

    /**
     * The action type of callback after startMeasure() method is called and measurement is ongoing.<br/>
     * <b>See Also:</b><br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>{@link #LIVEDATA_HS}</li>
     * </ul>
     * <b>message example:</b><br/>
     * {<br/>
     * &nbsp; &nbsp; "value": 62.79999923706055<br/>
     * }<br/>
     */
    public static final String ACTION_LIVEDATA_HS = "liveData_hs";

    /**
     * The key of live data weight<br/>
     * The value is float type, it's unit is kg.<br/>
     * <b>Value range:</b><br/>
     * 0.0-150.0
     */
    public static final String LIVEDATA_HS = "value";
    /**
     * The action type of callback after startMeasure() method is called and measurement data is stable.<br/>
     * <b>See Also:</b><br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>{@link #STABLEDATA_HS}</li>
     * </ul>
     * <b>message example:</b><br/>
     * {<br/>
     * &nbsp; &nbsp; "stableData": 84.19999694824219<br/>
     * }<br/>
     */
    public static final String ACTION_STABLEDATA_HS = "stableData_hs";
    /**
     * The key of stable data weight<br/>
     * The value is float type, it's unit is kg.<br/>
     * <b>Value range:</b><br/>
     * 0.0-150.0
     */
    public static final String STABLEDATA_HS = "stableData";

    /**
     * The action type of callback after startMeasure() method is called and scale in impedance status.<br/>
     * <b>See Also:</b><br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>{@link #IMPEDANCEDATA_HS}</li>
     * </ul>
     * <b>message example:</b><br/>
     * {<br/>
     * &nbsp; &nbsp; "impedanceData": 84.19999694824219<br/>
     * }<br/>
     */
    public static final String ACTION_IMPEDANCEDATA_HS = "impedanceData_hs";
    /**
     * The key of stable data weight<br/>
     * The value is float type, it's unit is kg.<br/>
     * <b>Value range:</b><br/>
     * 0.0-150.0
     */
    public static final String IMPEDANCEDATA_HS = "impedanceData";

    /**
     * The action type of callback after creatManagement() method is called.<br/>
     * <b>See Also:</b><br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>{@link #USERINFO_IN_HS}</li>
     * <li>{@link #USERPOSITION_HS}</li>
     * <li>{@link #EMPTYPOSITION_HS}</li>
     * <li>{@link #STATUS_HS}</li>
     * </ul>
     * <b>message example:</b><br/>
     * {<br/>
     * &nbsp; &nbsp; "userInfoInScale": 2,<br/>
     * &nbsp; &nbsp; "userPosition": -1,<br/>
     * &nbsp; &nbsp; "emptyPosition": 0,<br/>
     * &nbsp; &nbsp; "status": [0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]<br/>
     * }<br/>
     */
    public static final String ACTION_MANAGEMENT_HS = "com.msg.hs5.managent";
    /**
     * The key of whether user information in the scale or not.
     * <ul>
     * <li>1 indicates that user is in scale</li>
     * <li>2 indicates that user isn't in scale and the sale has free position</li>
     * <li>3 indicates that user isn't in scale but the sale is full need delete other user with method DeleteUserInScale(int)</li>
     * </ul>
     */
    public static final String USERINFO_IN_HS = "userInfoInScale";

    /**
     * The key of user's position in the scale<br/>
     * <b>Value range:</b><br/>
     * -1 ~ 19(-1 indicates that the user isn't in scale)
     */
    public static final String USERPOSITION_HS = "userPosition";
    /**
     * The key of the first empty position in scale.<br/>
     * <b>Value range:</b><br/>
     * -1 ~ 19(-1 indicates that the scale is full)
     */
    public static final String EMPTYPOSITION_HS = "emptyPosition";
    /**
     * user ids in the scale
     */
    public static final String USERIDS_HS = "userIds";
    /**
     * The key of the status of each user of the scale.<br/>
     * <b>Value:</b><br/>
     * The value is an 20-size integer array, each item means:<br/>
     * <ul>
     * <li>0 indicates this position is empty</li>
     * <li>1 indicates the position has user but not current user</li>
     * <li>2 indicates the current user is in this position</li>
     * </ul>
     */
    public static final String STATUS_HS = "status";
    /**
     * The action type of callback after WriteUserToScale() method is called.<br/>
     * <b>See Also:</b><br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>{@link #ACTION_ADDUSER_HS}</li>
     * </ul>
     * <b>message example:</b><br/>
     * {<br/>
     * &nbsp; &nbsp; "addUserResult": true<br/>
     * }<br/>
     * <br/>
     */
    public static final String ACTION_ADDUSER_HS = "addUser_hs";
    /**
     * The key of result of add user information to the scale.<br/>
     * <b>Value:</b><br/>
     * <ul>
     * <li>true indicates adding user successfully.</li>
     * <li>false indicates adding user failed.</li>
     * </ul>
     */
    public static final String ADDUSER_RESULT_HS = "addUserResult";

    /**
     * The action type of callback after DeleteUserInScale() method is called.<br/>
     * <b>See Also:</b><br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>{@link #DELETEUSER_RESULT_HS}</li>
     * </ul>
     * <b>message example:</b><br/>
     * {<br/>
     * &nbsp; &nbsp; "deleteResult": true<br/>
     * }<br/>
     * <br/>
     */
    public static final String ACTION_DELETEUSER_HS = "deleteUser_hs";
    /**
     * The key of result of remove user information from the scale.<br/>
     * <b>Value:</b><br/>
     * <ul>
     * <li>true indicates removing user information successfully.</li>
     * <li>false indicates removing user information failed.</li>
     * </ul>
     */
    public static final String DELETEUSER_RESULT_HS = "deleteResult";

    /**
     * The action type of callback after updateUserInfo() method is called.<br/>
     * <b>See Also:</b><br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>{@link #UPDATEUSER_RESULT_HS}</li>
     * </ul>
     * <b>message example:</b><br/>
     * {<br/>
     * &nbsp; &nbsp; "updateResult": true<br/>
     * }<br/>
     * <br/>
     */
    public static final String ACTION_UPDATEUSER_HS = "updteUser_hs";
    /**
     * The key of result of update user information in the scale.<br/>
     * <b>Value:</b><br/>
     * <ul>
     * <li>true indicates updating user information successfully.</li>
     * <li>false indicates updating user information failed.</li>
     * </ul>
     */
    public static final String UPDATEUSER_RESULT_HS = "updateResult";

    /**
     * The key of the weight result.<br/>
     * The value's unit is kg.<br/>
     * <b>Value range:</b><br/>
     * 0.0-150.0
     */
    public static final String WEIGHT_HS = "value";
    /**
     * The key of the data's ID
     */
    public static final String DATAID = "dataID";

    /**
     * The key of the body fat rate.<br/>
     * <b>Value range:</b><br/>
     * 0.0-100.0
     */
    public static final String FAT_HS = "fat";
    /**
     * The key of the body water rate.<br/>
     * <b>Value range:</b><br/>
     * 0.0-100.0
     */
    public static final String WATER_HS = "water";
    /**
     * The key of the the body muscle rate.<br/>
     * <b>Value range:</b><br/>
     * 0.0-100.0
     */
    public static final String MUSCLE_HS = "muscle";
    /**
     * The key of the the bone mass<br/>
     * <b>Value range:</b><br/>
     * 0.0-25.5(0xFF/10.0)
     */
    public static final String SKELETON_HS = "skeleton";
    /**
     * The key of the fat level<br/>
     * <b>Value range:</b><br/>
     * 0-255(0xFF)
     */
    public static final String FATELEVEL_HS = "fatelevel";
    /**
     * The key of the DCI.<br/>
     * <b>Value range:</b><br/>
     * 0-65535(0xFFFF)
     */
    public static final String DCI_HS = "DCI";

    /**
     * Callback indication it is setting wifi
     */
    public static final String ACTION_SETTINGWIFI = "settingWifi_hs";
    /**
     * Callback indication set wifi success
     */
    public static final String ACTION_SETWIFI_SUCCESS = "setWifiSuccess_hs";
    /**
     * Callback indication set wifi fail
     */
    public static final String ACTION_SETWIFI_FAIL = "setWifiFail_hs";
    /**
     * Callback indication set wifi unknow state
     */
    public static final String ACTION_SETWIFI_UNKNOW = "setWifiUnknow_hs";


    /**
     * Callback indicating power of Battery for Hs device.
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>{@link #CHS_BATTERY}</li>
     * </ul>
     */
    public static final String ACTION_BATTERY_CHS = "battery_chs";
    /**
     * The power of Battery for Continua HS device.
     */
    public static final String CHS_BATTERY = "chs_battery";

    /**
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>{@link #CHS_UNIT_FLAG}</li>
     * <li>{@link #CHS_TIMESTAMP_FLAG}</li>
     * <li>{@link #CHS_USER_ID_FLAG}</li>
     * <li>{@link #CHS_BMI_AND_Height_FLAG}</li>
     * <li>{@link #CHS_WEIGHT_SI}</li>
     * <li>{@link #CHS_WEIGHT_IMPERIAL}</li>
     * <li>{@link #CHS_TIME_STAMP}</li>
     * <li>{@link #CHS_BMI_AND_Height_FLAG}</li>
     * <li>{@link #CHS_BMI_AND_Height_FLAG}</li>
     * </ul>
     */
    public static final String ACTION_CHS_MEASUREMENT_DATA = "com.msg.chs.measurement";
    /**
     * The unit for weight scale : 0 is Weight and Mass in units of kilogram (kg) and Height in units of meter, 1 is Weight and Mass in units of pound (lb) and Height in units of inch (in).
     */
    public static final String CHS_UNIT_FLAG = "chs_unit";
    /**
     * The timestamp flag for weight scale :  0 is invalid, 1 is valid.
     */
    public static final String CHS_TIMESTAMP_FLAG = "chs_timestamp_flag";
    /**
     * The user id flag for weight scale :  0 is invalid, 1 is valid.
     */
    public static final String CHS_USER_ID_FLAG = "chs_user_id_flag";
    /**
     * The BMI and Height flag for weight scale :  0 is invalid, 1 is valid.
     */
    public static final String CHS_BMI_AND_Height_FLAG = "chs_bmi_and_height_flag";

    /**
     * The Weight - SI for weight scale.
     */
    public static final String CHS_WEIGHT_SI = "chs_weight_si";
    /**
     * The Weight - Imperial for weight scale.
     */
    public static final String CHS_WEIGHT_IMPERIAL = "chs_weight_imperial";
    /**
     * The Time Stamp for weight scale.
     */
    public static final String CHS_TIME_STAMP = "chs_time_stamp";
    /**
     * The user id for weight scale.
     */
    public static final String CHS_USER_ID = "chs_user_id";
    /**
     * The BMI for weight scale.
     */
    public static final String CHS_BMI = "chs_BMI";
    /**
     * The Height - SI for weight scale.
     */
    public static final String CHS_HEIGHT_SI = "chs_height_si";
    /**
     * The Height - Imperial for weight scale.
     */
    public static final String CHS_HEIGHT_IMPERIAL = "chs_height_imperial";

    /**
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>{@link #CHS_UNIT_FLAG}</li>
     * <li>{@link #CHS_TIMESTAMP_FLAG}</li>
     * <li>{@link #CHS_USER_ID_FLAG}</li>
     * <li>{@link #CHS_BMI_AND_Height_FLAG}</li>
     * <li>{@link #CHS_WEIGHT_SI}</li>
     * <li>{@link #CHS_WEIGHT_IMPERIAL}</li>
     * <li>{@link #CHS_TIME_STAMP}</li>
     * <li>{@link #CHS_BMI_AND_Height_FLAG}</li>
     * <li>{@link #CHS_BMI_AND_Height_FLAG}</li>
     * </ul>
     */
//    public static final String ACTION_CHS_FEATURE_DATA = "com.msg.chs.feature";
    /**
     * The key of the feature data.<br/>
     */
//    public static final String CHS_FEATURE_DATA = "chs_feature_data";
}
