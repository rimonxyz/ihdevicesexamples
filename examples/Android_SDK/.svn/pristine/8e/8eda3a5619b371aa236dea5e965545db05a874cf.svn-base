package com.ihealth.communication.control;

import com.ihealth.communication.manager.iHealthDevicesManager;

/**
 * Public APIs for the Bp serials device profiles
 * <p> Clients need to call{@link iHealthDevicesManager#registerClientCallback} register.
 * When new data from Bp device, The Callback{#iHealthDevicesCallback #onDeviceNotify} can be triggered should call.
 */
public class BpProfile {

    /**
     * The action type of callback indicating the error of Bp device.<br />
     * <b>KeyList of the message:</b>
     * <ul>
     * <li>{@link #ERROR_NUM_BP}</li>
     * </ul>
     * <b>Example message:</b><br />
     * {"error",13}
     */
    public static final String ACTION_ERROR_BP = "error_bp";

    /**
     * Flag Error number of Bp device.
     * <p>
     * <p>Error code and Description</p>
     * <p>0:not find a suitable zero in 20s.</p>
     * <p>1:not find high pressure.</p>
     * <p>2:not find low pressure or the high pressure value is lower than the low pressure value.</p>
     * <p>3:pressurization fast.</p>
     * <p>4:pressurization slow.</p>
     * <p>5:pressure exceeds 300mmHg.</p>
     * <p>6:time of pressure greater than 15 mmHg exceeds 160s.</p>
     * <p>7:EE read and write error.</p>
     * <p>8:EE three backup data error.</p>
     * <p>9:retention.</p>
     * <p>10:SPAN value error.</p>
     * <p>11:CRC errors.</p>
     * <p>12:connect error.</p>
     * <p>13:low power tips.</p>
     * <p>14:high or low pressure value of measurement exceeds the set upper limit.</p>
     * <p>15:high or low pressure value of measurement exceeds the set lower limit.</p>
     * <p>16:arm movement during the measurement over the machine set point.</p>
     */
    public static final String ERROR_NUM_BP = "error";

    /**
     * The action type of callback indicating the result that the On-line has been completed.<br />
     * <b>KeyList</b> of th message:
     * <ul>
     * <li>{@link #HIGH_BLOOD_PRESSURE_BP}</li>
     * <li>{@link #LOW_BLOOD_PRESSURE_BP}</li>
     * <li>{@link #PULSE_BP}</li>
     * <li>{@link #MEASUREMENT_AHR_BP}</li>
     * <LI>{@link #MEASUREMENT_HSD_BP}</LI>
     * <li>{@link #DATAID}</li>
     * </ul>
     * <b>Example message:</b><br />
     * {"highpressure":100,"lowpressure":60,"heartrate":70,<br />
     * "arrhythmia":false,"hsd":false,"dataID":"810D4F4CED95759AB738EBC1E74EA8F6"}
     */
    public static final String ACTION_ONLINE_RESULT_BP = "online_result_bp";

    /**
     * The action type of callback indicating the Bp device is being initialized and the parameter is reset.
     */
    public static final String ACTION_ZOREING_BP = "zoreing_bp";

    /**
     * The action type of callback indicating the Initialization of the Bp device has been completed.
     */
    public static final String ACTION_ZOREOVER_BP = "zoreover_bp";

    /**
     * The action of callback indicating the blood pressure in the measurement
     * after startMeasure() method is called.
     * <b>KeyList</b> of the message :
     * <ul>
     * <li>{@link #BLOOD_PRESSURE_BP}</li>
     * </ul>
     * <b>Example message :</b><br />
     * {"pressure":20}
     */
    public static final String ACTION_ONLINE_PRESSURE_BP = "online_pressure_bp";

    /**
     * The action of callback indicating the blood pressure and pulse wave in the measurement
     * after startMeasure() method is called.<br />
     * <b>KeyList </b> of the message:
     * <ul>
     * <li>{@link #BLOOD_PRESSURE_BP}</li>
     * <li>{@link #FLAG_HEARTBEAT_BP}</li>
     * <li>{@link #PULSEWAVE_BP}</li>
     * </ul>
     * <p>Example message:</p>
     * {"pressure":25,"heartbeat":false,"wave":"[15,15,15,15,15,15,15,15]"}
     */
    public static final String ACTION_ONLINE_PULSEWAVE_BP = "online_pulsewave_bp";

    /**
     * The action of callback indicating the blood pressure interrupted. Only for BP3L
     */
    public static final String ACTION_INTERRUPTED_BP = "interrupted_bp";

    /**
     * The blood pressure <br />
     * <b>Value Range: </b><br />
     * 0-255(0xFF)
     */
    public static final String BLOOD_PRESSURE_BP = "pressure";

    /**
     * The high pressure for blood pressure.<br />
     * <b>Value Range: </b><br />
     * 0-255(0xFF)
     */
    public static final String HIGH_BLOOD_PRESSURE_BP = "highpressure";

    /**
     * The low pressure for blood pressure.<br />
     * <b>Value Range: </b><br />
     * 0-255(0xFF)
     */
    public static final String LOW_BLOOD_PRESSURE_BP = "lowpressure";

    /**
     * Heartbeat graphical control flag.
     */
    public static final String FLAG_HEARTBEAT_BP = "heartbeat";

    /**
     * Blood pressure pulse wave.<br />
     * <b>Value Range: </b><br />
     * 0-255(0xFF)
     */
    public static final String PULSEWAVE_BP = "wave";

    /**
     * Blood pressure pulse.
     */
    public static final String PULSE_BP = "heartrate";

    /**
     * The action type of callback indicating historical data for Bp device
     * after getOfflineData() method is called.<br />
     * <p>
     * <b>KeyList</b> of the return message:
     * <ul>
     * <li>{@link #HISTORICAL_DATA_BP}</li>
     * <li>{@link #MEASUREMENT_DATE_BP}</li>
     * <li>{@link #HIGH_BLOOD_PRESSURE_BP}</li>
     * <li>{@link #LOW_BLOOD_PRESSURE_BP}</li>
     * <li>{@link #PULSEWAVE_BP}</li>
     * <li>{@link #MEASUREMENT_AHR_BP}</li>
     * <li>{@link #MEASUREMENT_HSD_BP}</li>
     * </ul>
     * <p>
     * <p>Message example:</p>
     * {<br/>
     * &nbsp; &nbsp; "data": [<br/>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;{<br/>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "date": "2016-07-18 09:00:00",<br/>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "highpressure": 99,<br/>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "lowpressure": 60<br/>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "wave": 60,<br/>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "arrhythmia":false,<br/>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "hsd": "false<br/>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; },<br/>
     * &nbsp; &nbsp; &nbsp; &nbsp; {<br/>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "date": "2016-07-19 09:05:00",<br/>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "highpressure": 105,<br/>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "lowpressure": 65,<br/>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "wave": 60,<br/>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"arrhythmia":false,<br/>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "hsd": "false<br/>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; }<br/>
     * &nbsp; &nbsp; ]<br/>
     * }<br/>
     */
    public static final String ACTION_HISTORICAL_DATA_BP = "historicaldata_bp";

    /**
     * The key of historical data for Bp device.
     */
    public static final String HISTORICAL_DATA_BP = "data";

    /**
     * The key of measurement time.<br />
     * <b>Value format: </b><br/>
     * yyyy-MM-dd HH:mm:ss<br/>
     * <b>Example: </b><br/>
     * 2016-07-18 09:00:00
     */
    public static final String MEASUREMENT_DATE_BP = "date";

    /**
     * The key of measurement is arrhythmia or not.<br />
     * <b>Value Range:</b> True/False
     */
    public static final String MEASUREMENT_AHR_BP = "arrhythmia";

    /**
     * The key of hemodynamic stability diagnosis, that Determines
     * whether the circulatory system is sufficiently at rest or not.<br />
     * <b>Value Range:</b> True/False
     */
    public static final String MEASUREMENT_HSD_BP = "hsd";

    /**
     * The key of start angle for measurement.<br />
     * <b>Value Range:</b>
     * 0-180
     */
    public static final String MEASUREMENT_STRAT_ANGLE_BP = "start_angle";

    /**
     * The key of angle change during measurement.<br />
     * <b>Value Range:</b>
     * 0-180
     */
    public static final String MEASUREMENT_ANGLE_CHANGE_BP = "angle_change";

    /**
     * The key of hand for measurement.<br />
     * <b>Value Range:</b>
     * 0, left hand;
     * 1, right hand;
     * 2, unknown
     */
    public static final String MEASUREMENT_HAND_BP = "hand";

    /**
     * Activity amount for ABPM.
     */
    public static final String ACTIVITY_AMOUNT_BP = "activity_amount";

    /**
     * The action type of callback indicating number of historical data for Bp device
     * after getOfflineNum() method is called.<br />
     * <p>
     * <b>KeyList</b> of the return mesage :
     * <ul>
     * <li>{@link #HISTORICAL_NUM_BP}</li>
     * </ul>
     * <p>
     * <p>Return message example:</p>
     * {"offlinenum":22}
     */
    public static final String ACTION_HISTORICAL_NUM_BP = "offlinenum";

    /**
     * Number of historical data for Bp device.<br />
     * <b>Value Range :</b>
     * 0-255(0xFF)
     */
    public static final String HISTORICAL_NUM_BP = "num";

    /**
     * The action type of callback indicating power of Battery for Bp device
     * after getBattery() method is called.<br />
     * <p>
     * <b>KeyList</b> of th message:
     * <ul>
     * <li>{@link #BATTERY_BP}</li>
     * <li>{@link #VOLTAGE_BP}</li>
     * <li>{@link #REMAINING_TIMES_BP}</li>
     * </ul>
     * <p>
     * <P>Message example: </P>
     * {"battery":90}
     */
    public static final String ACTION_BATTERY_BP = "battery_bp";

    /**
     * The power of Battery for Bp device.<br />
     * <b>Value Range :</b>
     * 0-100
     */
    public static final String BATTERY_BP = "battery";

    /**
     * The voltage of Battery for Bp device.<br />
     * <b>Value Range :</b>
     * 12
     */
    public static final String VOLTAGE_BP = "voltage";

    /**
     * The remaining measuring times for Bp device.<br />
     * <b>Value Range :</b>
     * 12
     */
    public static final String REMAINING_TIMES_BP = "remaining_times";


    /**
     * The action type of callback indicating stop.
     */
    public static final String ACTION_STOP_BP = "stop";

    /**
     * The action type of callback indicating current angle for Bp device(Bp7 only).<br />
     * <b>KeyList</b> of the message :
     * <ul>
     * <li>{@link #ANGLE_BP}</li>
     * </ul>
     * <p>Example message:</p>
     * {"value":0}
     */
    public static final String ACTION_ANGLE_BP = "angle_bp";

    /**
     * The current angle for Bp device(Bp7 only).<br />
     * <b>Value Range:</b>
     * 0-90
     */
    public static final String ANGLE_BP = "value";

    /**
     * The action of callback indicating Whether off-line measurement or not(only for Bp5 and Bp7).<br />
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>{@link #IS_ENABLE_OFFLINE}</li>
     * </ul>
     * <p>Example message:</p>
     * {"offlinestatus",true}
     */
    public static final String ACTION_IS_ENABLE_OFFLINE = "offlinestatus";

    /**
     * The key of whether off-line measurement or not(only for Bp5 and Bp7).<br />
     * <b>Value Range:</b> True/False
     */
    public static final String IS_ENABLE_OFFLINE = "offlinestatus";

    /**
     * The action type of callback indicating enable Off-line measurement operation is success(only for Bp5 and Bp7).
     */
    public static final String ACTION_ENABLE_OFFLINE_BP = "enable_offline_bp";

    /**
     * The action type of callback indicating disenable Off-line measurement operation is success
     * after disenableOffline() method is called(only for Bp5 and Bp7).
     */
    public static final String ACTION_DISENABLE_OFFLINE_BP = "disenable_offline_bp";

    /**
     * Callback indicating set unit success is success(for Bp7S and 550BT).
     */
    public static final String ACTION_SET_UNIT_SUCCESS_BP = "set_unit_success";

    /**
     * Callback indicating set angle success is success(for Bp7S and 550BT).
     */
    public static final String ACTION_SET_ANGLE_SUCCESS_BP = "set_angle_success";

    /**
     * The function information data of bp devices.
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>{@link #FUNCTION_OPERATING_STATE}</li>
     * <li>{@link #FUNCTION_IS_UPAIR_MEASURE}</li>
     * <li>{@link #FUNCTION_IS_ARM_MEASURE}</li>
     * <li>{@link #FUNCTION_HAVE_ANGLE_SENSOR}</li>
     * <li>{@link #FUNCTION_HAVE_POWEROFF}</li>
     * <li>{@link #FUNCTION_HAVE_OFFLINE}</li>
     * <li>{@link #FUNCTION_ALLOW_DELETE_OFFLINEDATA}</li>
     * <li>{@link #FUNCTION_HAVE_HSD}</li>
     * <li>{@link #FUNCTION_HAVE_ANGLE_SETTING}</li>
     * <li>{@link #MEMORY_GROUP_BP}</li>
     * <li>{@link #FUNCTION_MAX_MEMORY_CAPACITY}</li>
     * <li>{@link #FUNCTION_HAVE_SHOW_UNIT_SETTING}</li>
     * <li>{@link #FUNCTION_SHOW_UNIT}</li>
     * <li>{@link #FUNCTION_ONLY_UPDATE_MEMORY}</li>
     * <li>{@link #FUNCTION_IS_AUTO_UPDATE}</li>
     * <li>{@link #FUNCTION_HAVE_ACTIVITY_DETECTION}</li>
     * <li>{@link #FUNCTION_HAVE_ALARM_SETTING}</li>
     * </ul>
     */
    public static final String ACTION_FUNCTION_INFORMATION_BP = "function_info_bp";

    /**
     * The function information:The last time measurement.
     */
    public static final String FUNCTION_OPERATING_STATE = "operating_state";

    /**
     * The function information:device is up air or low air, up is "true", low is "false".
     */
    public static final String FUNCTION_IS_UPAIR_MEASURE = "is_upari_measure";

    /**
     * The function information:device is arm or wrist, arm is "true", wrist is "false".
     */
    public static final String FUNCTION_IS_ARM_MEASURE = "is_arm_measure";

    /**
     * The function information:device had angle or not, have is "true", otherwise is "false".
     */
    public static final String FUNCTION_HAVE_ANGLE_SENSOR = "have_angle_sensor";

    /**
     * The function information: have power off function or not, have is "true", otherwise is "false".
     */
    public static final String FUNCTION_HAVE_POWEROFF = "have_poweroff_func";

    /**
     * The function information:device had offline function or not, have is "true", otherwise is "false".
     */
    public static final String FUNCTION_HAVE_OFFLINE = "have_offline_function";

    /**
     * The function information: allow delete offline data or not, allow is "true", otherwise is "false".
     */
    public static final String FUNCTION_ALLOW_DELETE_OFFLINEDATA = "allow_delete_offlinedata";

    /**
     * The function information:device had HSD or not, have is "true", otherwise is "false".
     */
    public static final String FUNCTION_HAVE_HSD = "have_hsd";

    /**
     * The function information:device had angle setting function or not, have is "true", otherwise is "false".
     */
    public static final String FUNCTION_HAVE_ANGLE_SETTING = "have_angle_setting";

    /**
     * Callback indicating memory group of historical data for kd926 and kd723
     */
    public static final String MEMORY_GROUP_BP = "memory_group";

    /**
     * The function information: the max memory capacity.
     */
    public static final String FUNCTION_MAX_MEMORY_CAPACITY = "max_memory_capacity";

    /**
     * The function information: have show unit setting or not, have is "true", otherwise is "false".
     */
    public static final String FUNCTION_HAVE_SHOW_UNIT_SETTING = "have_show_unit_set";

    /**
     * The function information: show unit,1 is kPa, 0 is mmHg
     */
    public static final String FUNCTION_SHOW_UNIT = "show_unit";

    /**
     * The function information: Upload the data set number at a time,1 is Upload multiple sets of data at a time, 0 is Upload a set of data at a time
     */
    public static final String FUNCTION_ONLY_UPDATE_MEMORY = "only_update_memory";

    /**
     * The function information: Whether to support automatic upgrade,1 is support, 0 is not support
     */
    public static final String FUNCTION_IS_AUTO_UPDATE = "is_auto_update";

    /**
     * The function information: Any activity detection,1 is have, 0 is not have
     */
    public static final String FUNCTION_HAVE_ACTIVITY_DETECTION = "have_activity_detection";

    /**
     * The function information: Any alarm setting,1 is have, 0 is not have
     */
    public static final String FUNCTION_HAVE_ALARM_SETTING = "have_alarm_setting";

    /**
     * allback indicating get automatic cycle measure (for ABPM).
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>{@link #MEASURE_IS_COMPLETE_BP}</li>
     * <li>{@link #MEASURE_IS_MEDICINE_BP}</li>
     * <li>{@link #GET_FIRST_TIME_BP}</li>
     * <li>{@link #GET_SECOND_TIME_BP}</li>
     * <li>{@link #GET_THIRD_TIME_BP}</li>
     * <li>{@link #GET_FORTH_TIME_BP}</li>
     * </ul>
     */
    public static final String ACTION_GET_CYCLE_MEASURE = "get_cycle_measure";
    /**
     * The function information:The measure to be achieved, true is complete, false is not complete.
     */
    public static final String MEASURE_IS_COMPLETE_BP = "is_complete_measure";
    /**
     * The function information:Whether to take medicine, true is yes, false is no.
     */
    public static final String MEASURE_IS_MEDICINE_BP = "is_medicine_measure";
    /**
     * The measure time of user setting.
     */
    public static final String GET_MEASURE_TIME_BP = "get_measure_time";
    /**
     * The first time setting, for morning.
     */
    public static final String GET_FIRST_TIME_BP = "first_time";
    /**
     * The second time setting, for night.
     */
    public static final String GET_SECOND_TIME_BP = "second_time";
    /**
     * The third time setting, for noon rest.
     */
    public static final String GET_THIRD_TIME_BP = "third_time";
    /**
     * The forth time setting, for noon get up.
     */
    public static final String GET_FORTH_TIME_BP = "forth_time";

    /**
     * The alarm setting data of bp devices.
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>{@link #ALARM_SETTING_BP}</li>
     * <li>{@link #ALARM_TYPE_BP}</li>
     * <li>{@link #IS_SOUND_ALARM_BP}</li>
     * <li>{@link #IS_VISUAL_ALARM_BP}</li>
     * <li>{@link #IS_VIBRATION_ALARM_BP}</li>
     * <li>{@link #ALARM_HIGH_BP}</li>
     * <li>{@link #ALARM_LOW_BP}</li>
     * </ul>
     */
    public static final String ACTION_ALARM_SETTING_BP = "action_alarm_setting_bp";
    /**
     * The key of alarm setting data for ABPM device.
     */
    public static final String ALARM_SETTING_BP = "alarm_setting_bp";
    /**
     * The key of alarm type data for ABPM device.
     * 1:High pressure
     * 2:low pressure
     * 3:average pressure
     * 4:Heart rate
     * 5:Blood pressure in excess of the range
     * 6:other
     */
    public static final String ALARM_TYPE_BP = "alarm_type_bp";
    /**
     * The key of sound the alarm is on.
     * eg.true
     */
    public static final String IS_SOUND_ALARM_BP = "alarm_is_sound_bp";
    /**
     * The key of visual the alarm is on.
     * eg.true
     */
    public static final String IS_VISUAL_ALARM_BP = "alarm_is_visual_bp";
    /**
     * The key of vibration the alarm is on.
     * eg.true
     */
    public static final String IS_VIBRATION_ALARM_BP = "alarm_is_vibration_bp";
    /**
     * The key of alarm Alarm limit data for ABPM device.
     */
    public static final String ALARM_HIGH_BP = "alarm_high_bp";
    /**
     * The key of alarm Alarm low limit data for ABPM device.
     */
    public static final String ALARM_LOW_BP = "alarm_low_bp";

    /**
     * The alarm setting data of bp devices.
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>{@link #ALARM_TYPE_HIGH_BP}</li>
     * <li>{@link #ALARM_TYPE_LOW_BP}</li>
     * <li>{@link #ALARM_TYPE_AVERAGE_BP}</li>
     * <li>{@link #ALARM_TYPE_HEART_RATE_BP}</li>
     * <li>{@link #ALARM_TYPE_EXCESS_BP}</li>
     * <li>{@link #ALARM_TYPE_OTHER_BP}</li>
     * </ul>
     */
    public static final String ACTION_ALARM_TYPE_BP = "action_alarm_type_bp";
    /**
     * The key of high pressure the alarm is on.
     * eg.true
     */
    public static final String ALARM_TYPE_HIGH_BP = "alarm_type_high_bp";
    /**
     * The key of low pressure the alarm is on.
     * eg.true
     */
    public static final String ALARM_TYPE_LOW_BP = "alarm_type_low_bp";
    /**
     * The key of Heart rate the alarm is on.
     * eg.true
     */
    public static final String ALARM_TYPE_AVERAGE_BP = "alarm_type_average_bp";
    /**
     * The key of average pressure the alarm is on.
     * eg.true
     */
    public static final String ALARM_TYPE_HEART_RATE_BP = "alarm_type_heart_rate_bp";
    /**
     * The key of Blood pressure in excess of the range the alarm is on.
     * eg.true
     */
    public static final String ALARM_TYPE_EXCESS_BP = "alarm_type_excess_bp";
    /**
     * The key of other the alarm is on.
     * eg.true
     */
    public static final String ALARM_TYPE_OTHER_BP = "alarm_type_other_bp";


    /**
     * The function information:device is multi upload or single upload, multi is "true", single is "false".
     */
    public static final String FUNCTION_IS_MULTI_UPLOAD = "is_multi_upload";

    /**
     * The function information:device had self update function or not, have is "true", otherwise is "false".
     */
    public static final String FUNCTION_HAVE_SELF_UPDATE = "have_self_update";

    /**
     * The function information: is automatic cycle measure device or single measure device.
     */
    public static final String FUNCTION_IS_AUTOCYCLE_MEASURE = "is_auto_cycle_measure";

    /**
     * The function information: have automatic back connect measure or not, have is "true", otherwise is "false".
     */
    public static final String FUNCTION_HAVE_AUTO_BACKCONNECT = "have_auto_backconnect";

    /**
     * The function information: automatic back connect measure switch, true is open, otherwise is close.
     */
    public static final String FUNCTION_AUTO_BACKCONNECT_SWITCH = "auto_backconnect_switch";

    /**
     * The function information: have offline measure setting or not, have is "true", otherwise is "false".
     */
    public static final String FUNCTION_HAVE_OFFLINE_SETTING = "have_offline_set";

    /**
     * The function information: offline measure switch, true is open, otherwise is close.
     */
    public static final String FUNCTION_OFFLINE_SWITCH = "offline_set_switch";

    /**
     * The function information: is abi device or not.
     */
    public static final String FUNCTION_IS_ABI_DEVICE = "is_abi_device";

    /**
     * The function information: is upper limb device or lower limb device, 1 is lower climb, 0 is upper limb.
     */
    public static final String FUNCTION_UP_LOW_LIMB = "up_low_limb";

    /**
     * The function information: is left and right limbs simultaneous measure device or not.
     */
    public static final String FUNCTION_IS_SIMULTANEOUS_MEASURE = "is_simultaneous_measure";

    /**
     * The function information: is left limb device or right limb device, 1 is right climb, 0 is left limb.
     */
    public static final String FUNCTION_LEFT_RIGHT_LIMB = "left_right_limb";

    /**
     * Callback indicating historical data over for Bp device.
     */
    public static final String ACTION_HISTORICAL_OVER_BP = "get_historical_over_bp";

    /**
     * Callback indicating set automatic cycle measure is success(for ABPM).
     */
    public static final String ACTION_SET_CYCLE_MEASURE_SUCCESS = "set_cycle_measure_success";
    /**
     * Measure times with remaining battery.
     */
    public static final String CYCLE_MEASURE_TIMES_BP = "cycle_measure_times";


    /**
     * return blood pressure data id.<br />
     * <b>Value Format:</b> 32 bits Hex String.
     * <p>Example:</p>
     * "810D4F4CED95759AB738EBC1E74EA8F6"
     */
    public static final String DATAID = "dataID";


    /***************************************************************************
     *********         {@link #ACTION_BATTERY_CBP}              ***************
     **************************************************************************/
    /**
     * Callback indicating  the battery for Continua Bp device .
     * <ul>
     * KeyList:
     * <ul>
     * <li>{@link #BATTERY_CBP}
     * </ul>
     * eg.
     * </ul
     */
    public static final String ACTION_BATTERY_CBP = "action_battery";

    /**
     * Battery for Continua Bp device.
     */
    public static final String BATTERY_CBP = "battery";


    /***************************************************************************
     *********         {@link #ACTION_FEATURE_CBP}       ***********************
     **************************************************************************/
    /**
     * Callback indicating the feature of blood pressure.
     * <ul>
     * KeyList:
     * <ul>
     * <li>{@link #FEATURE_BODY_MOVEMENT_CBP}
     * <li>{@link #FEATURE_FIT_DETECTION_CBP}
     * <li>{@link #FEATURE_IRREGULAR_PULSE_DETECTION_CBP}
     * <li>{@link #FEATURE_PULSE_RATE_RANGE_DETECTION_CBP}
     * <li>{@link #FEATURE_MEASUREMENT_POSITION_DETECTION_CBP}
     * <li>{@link #FEATURE_MULTIPLE_BOND_CBP}
     * </ul>
     * eg.
     * </ul
     */
    public static final String ACTION_FEATURE_CBP = "action_feature";

    /**
     * Body Movement Detection Support;  0:false   1:true
     */
    public static final String FEATURE_BODY_MOVEMENT_CBP = "body_movement";

    /**
     * Cuff Fit Detection Support;  0:false   1:true
     */
    public static final String FEATURE_FIT_DETECTION_CBP = "fit_detection";

    /**
     * Irregular Pulse Detection Support;  0:false   1:true
     */
    public static final String FEATURE_IRREGULAR_PULSE_DETECTION_CBP = "irregular_pulse_detection";

    /**
     * Pulse Rate Range Detection Suppor;  0:false   1:true
     */
    public static final String FEATURE_PULSE_RATE_RANGE_DETECTION_CBP = "pulse_rate_range_detection";

    /**
     * Measurement Position Detection Support;  0:false   1:true
     */
    public static final String FEATURE_MEASUREMENT_POSITION_DETECTION_CBP = "measurement_position_detection";

    /**
     * Multiple Bond Support ;  0:false   1:true
     */
    public static final String FEATURE_MULTIPLE_BOND_CBP = "multiple_bond";


    /***************************************************************************
     *********         {@link #ACTION_HISTORY_DATA_CBP}          ***************
     **************************************************************************/
    /**
     * Callback indicating receive the history data for Continua Bp device .
     * <ul>
     * KeyList:
     * <ul>
     * <li>{@link #HISTORY_DATA_CBP}
     * <ul>
     * <li>{@link #CBPINFO_UNIT}
     * <li>{@link #CBPINFO_TIMESTAMP_FLAG}
     * <li>{@link #CBPINFO_PULSE_RATE_FLAG}
     * <li>{@link #CBPINFO_USER_ID_FLAG}
     * <li>{@link #CBPINFO_MEASURE_STATUS_FLAG}
     * <li>{@link #CBPINFO_HSD_FLAG}
     * <li>{@link #CBPINFO_SYS}
     * <li>{@link #CBPINFO_DIA}
     * <li>{@link #CBPINFO_MAP}
     * <li>{@link #CBPINFO_TIMESTAMP}
     * <li>{@link #CBPINFO_PULSE_RATE}
     * <li>{@link #CBPINFO_USER_ID}
     * <li>{@link #CBPINFO_BODY_MOVEMENT}
     * <li>{@link #CBPINFO_CUF_FIT}
     * <li>{@link #CBPINFO_IRREGULAR}
     * <li>{@link #CBPINFO_PULSE_RATE_RANGE}
     * <li>{@link #CBPINFO_POSITION}
     * <li>{@link #CBPINFO_HSD}
     * </ul>
     * </ul>
     * eg.
     * </ul
     */
    public static final String ACTION_HISTORY_DATA_CBP = "action_history_data";

    /**
     * Historical data for Continua Bp device.
     */
    public static final String HISTORY_DATA_CBP = "history_data";

    /**
     * The unit for blood pressure : 0 is mmHg, 1 is kPa.
     */
    public static final String CBPINFO_UNIT = "bp_unit";
    /**
     * The timestamp flag for blood pressure :  0 is invalid, 1 is valid.
     */
    public static final String CBPINFO_TIMESTAMP_FLAG = "bp_timestamp_flag";
    /**
     * The pulse rate flag for blood pressure :  0 is invalid, 1 is valid.
     */
    public static final String CBPINFO_PULSE_RATE_FLAG = "bp_pulse_rate_flag";
    /**
     * The pulse rate flag for user id : 0 is invalid, 1 is valid.
     */
    public static final String CBPINFO_USER_ID_FLAG = "bp_user_id_flag";
    /**
     * The pulse rate flag for measure status : 0 is not present, 1 is present.
     */
    public static final String CBPINFO_MEASURE_STATUS_FLAG = "bp_measure_status_flag";
    /**
     * The pulse rate flag for HSD : 0 is not present, 1 is present.
     */
    public static final String CBPINFO_HSD_FLAG = "bp_hsd_flag";

    /**
     * The systolic pressure for blood pressure.
     */
    public static final String CBPINFO_SYS = "bp_sys";
    /**
     * The diastolic pressure for blood pressure.
     */
    public static final String CBPINFO_DIA = "bp_dia";
    /**
     * The MAP for blood pressure.
     */
    public static final String CBPINFO_MAP = "bp_map";
    /**
     * The measure time for blood pressure. Format:yyyy-MM-dd HH:mm:ss
     */
    public static final String CBPINFO_TIMESTAMP = "bp_timestamp";
    /**
     * The pulse rate for blood pressure.
     */
    public static final String CBPINFO_PULSE_RATE = "bp_pulse_rate";
    /**
     * The user id for blood pressure.
     */
    public static final String CBPINFO_USER_ID = "bp_user_id";

    /**
     * The body movement during measurement :  0 no body movement,  1 body movement.
     */
    public static final String CBPINFO_BODY_MOVEMENT = "bp_body_movement";
    /**
     * The cuff status during measurement :  0 cuff fits properly,  1 cuff too lose.
     */
    public static final String CBPINFO_CUF_FIT = "bp_cuf_fit";
    /**
     * The irregular status for blood pressure :  0 no irregular pulse detected,  1 irregualr pulse detected.
     */
    public static final String CBPINFO_IRREGULAR = "bp_irregular";
    /**
     * The pulse rate status for blood pressure :  0 pulse rate is within the range,  1 pulse rate exceeds upper limit, 2 pulse rate is less than lower limit.
     */
    public static final String CBPINFO_PULSE_RATE_RANGE = "bp_pulse_rate_range";
    /**
     * The position status during measurement :  0 proper measurement position,  1 improper measurement position.
     */
    public static final String CBPINFO_POSITION = "bp_position";
    /**
     * The HSD status for blood pressure :  0 HSD is not dected,  1 HSD detected.
     */
    public static final String CBPINFO_HSD = "bp_hsd";


    /***************************************************************************
     *********      {@link #ACTION_CONFORM_CHOOSE_USER_CBP}      ***************
     **************************************************************************/
    /**
     * Callback indicating receive conform information for choose user.
     * <ul>
     * KeyList:
     * <ul>
     * <li>{@link #CHOOSE_USER_CBP}
     * </ul>
     * eg.
     * </ul
     */
    public static final String ACTION_CONFORM_CHOOSE_USER_CBP = "action_conform__choose_user";

    /**
     * The result for choose user :  0 failed,  1 success.
     */
    public static final String CHOOSE_USER_CBP = "choose_user";


}
