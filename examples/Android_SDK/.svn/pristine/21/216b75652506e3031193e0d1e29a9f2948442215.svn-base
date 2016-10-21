package com.ihealth.communication.control;


import android.R.integer;

import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;

/**
 * Public APIs for the AM serials device profiles
 * <p> Clients need to call{@link iHealthDevicesManager#registerClientCallback} register.
 * When new data from AM device, The Callback{#iHealthDevicesCallback #onDeviceNotify} can be triggered should call.
 *
 */
public interface AmProfile {
	
	/******************************************* Am Macro *************************************************/
	/**
	 * The action type of callback indicating the error of AM device.<br/>
	 * <b>KeyList of the message:</b>
	 * <ul>
	 * <li>{@link #ERROR_NUM_AM}</li>
	 * <li>{@link #ERROR_DESCRIPTION_AM}</li>
	 * </ul>
	 * <b>Example message:</b><br/>
	 * {<br/>
	 * &nbsp; &nbsp; "error": 400,<br/>
	 * &nbsp; &nbsp; "description": "setAlarmClock() parameter min should be in the range [0, 59]."<br/>
	 * }<br/>
	 */
	public static final String ACTION_ERROR_AM = "error_am";

	/**
	 * The key of error ID number of AM device.<br/>
	 * <b>Value:</b>
	 * <ul>
	 * <li>{@link #ERROR_ID_ILLEGAL_ARGUMENT} indicates parameter error.</li>
	 * <li>{@link #ERROR_ID_VERSION_NOT_SUPPORT} indicates version not support error.</li>
	 * </ul>
	 */
	public static final String ERROR_NUM_AM = "error";
	/**
	 * The error ID indicates parameter error.
	 */
	public static final int ERROR_ID_ILLEGAL_ARGUMENT = 400;
	/**
	 * The error ID indicates version not support error.
	 */
	public static final int ERROR_ID_VERSION_NOT_SUPPORT = 402;

	/**
	 * The key of error description.<br/>
	 * The value string will show the detail description of the error.
	 */
	public static final String ERROR_DESCRIPTION_AM = "description";

	
	/**
	 * The action tye of callback after reset() method is called.<br/>
	 * <b>See Also:</b><br/>
	 * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
	 * <b>KeyList</b> of the message:
	 * <ul>
	 * <li>{@link #RESET_AM}</li>
	 * </ul>
	 * <b>message example:</b><br/>
	 * {<br/>
	 * &nbsp; &nbsp; "reset": 1<br/>
	 * }<br/>
	 */
	public static final String ACTION_RESET_AM = "reset_am";
	/**
	 * The key of the reset result.<br/>
	 * <b>Value:</b><br/>
	 * <ul>
	 * <li>0 indicates reset failed.</li>
	 * <li>1 indicates reset successfully.</li>
	 * </ul>
	 */
	public static final String RESET_AM = "reset";
	
	/**
	 * The action tye of callback after getUserId() method is called.<br/>
	 * <b>See Also:</b><br/>
	 * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
	 * <b>KeyList</b> of the message:
	 * <ul>
	 * <li>{@link #USERID_AM}</li>
	 * </ul>
	 * <b>message example:</b><br/>
	 * {<br/>
	 * &nbsp; &nbsp; "userid": 123456<br/>
	 * }<br/>
	 */
	public static final String ACTION_USERID_AM = "userid_am";
	/**
	 * The key of the user's ID.<br/>
	 * <b>Value range:</b><br/>
	 * 0-4294967295(0xFFFFFFFF)
	 */
	public static final String USERID_AM = "userid";
	
	/**
	 * The action tye of callback after setUserId() method is called.<br/>
	 * It indicates that set user ID successfully.<br/>
	 * <b>See Also:</b><br/>
	 * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}
	 */
	public static final String ACTION_SET_USERID_SUCCESS_AM = "set_userid_success_am";

	/**
	 * The action tye of callback after syncRealTime() method is called.<br/>
	 * It indicates that set user ID successfully.<br/>
	 * <b>See Also:</b><br/>
	 * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}
	 */
	public static final String ACTION_SYNC_TIME_SUCCESS_AM = "set_sync_time_success_am";

	/**
	 * The action tye of callback after setUserInfo() method is called.<br/>
	 * It indicates that set user information successfully.<br/>
	 * <b>See Also:</b><br/>
	 * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}
	 */
	public static final String ACTION_SET_USERINFO_SUCCESS_AM = "set_userinfo_success_am";
	
	/**
	 * The action tye of callback after getUserInfo() method is called.<br/>
	 * <b>See Also:</b><br/>
	 * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
	 * <b>KeyList</b> of the message:
	 * <ul>
	 * <li>{@link #GET_USER_AGE_AM}</li>
	 * <li>{@link #GET_USER_STEP_AM}</li>
	 * <li>{@link #GET_USER_HEIGHT_AM}</li>
	 * <li>{@link #GET_USER_SEX_AM}</li>
	 * <li>{@link #GET_USER_WEIGHT_AM}</li>
	 * <li>{@link #GET_USER_UNIT_AM}</li>
	 * <li>{@link #GET_USER_TARGET1_AM}</li>
	 * <li>{@link #GET_USER_TARGET2_AM}</li>
	 * <li>{@link #GET_USER_TARGET3_AM}</li>
	 * <li>{@link #GET_USER_SWIMTARGET_AM}</li>
	 * </ul>
	 * <b>message example:</b><br/>
	 * {<br/>
	 * &nbsp; &nbsp; "age": 29,<br/>
	 * &nbsp; &nbsp; "step": 80,<br/>
	 * &nbsp; &nbsp; "height": 177,<br/>
	 * &nbsp; &nbsp; "gender": 1,<br/>
	 * &nbsp; &nbsp; "weight": 65,<br/>
	 * &nbsp; &nbsp; "unit": 1,<br/>
	 * &nbsp; &nbsp; "target1": 2500,<br/>
	 * &nbsp; &nbsp; "target2": 1250,<br/>
	 * &nbsp; &nbsp; "target3": 625,<br/>
	 * &nbsp; &nbsp; "swim_target": 59<br/>
	 * }<br/>
	 */
	public static final String ACTION_GET_USERINFO_AM = "get_userinfo_am";
	/**
	 * The key of the user's age.<br/>
	 * <b>Value range:</b><br/>
	 * 0-255(0xFF)
	 */
	public static final String GET_USER_AGE_AM = "age";
	/**
	 * The key of the user's step length.<br/>
	 * <b>Value range:</b><br/>
	 * 0-255(0xFF)
	 */
	public static final String GET_USER_STEP_AM = "step";
	/**
	 * The key of the user's height.<br/>
	 * <b>Value range:</b><br/>
	 * 0-255(0xFF)
	 */
	public static final String GET_USER_HEIGHT_AM = "height";
	/**
	 * The key of the user's gender.<br/>
	 * <b>Value:</b><br/>
	 * <ul>
	 * <li>{@link #AM_SET_FEMALE}</li>
	 * <li>{@link #AM_SET_MALE}</li>
	 * </ul>
	 */
	public static final String GET_USER_SEX_AM = "gender";
	/**
	 * The key of the user's weight.<br/>
	 * The value is double type<br/>
	 * <b>Value range:</b><br/>
	 * 0.0-255.255(0xFF.0xFF)
	 */
	public static final String GET_USER_WEIGHT_AM = "weight";
	/**
	 * The key of the unit type.<br/>
	 * <b>Value:</b><br/>
	 * <ul>
	 * <li>{@link #AM_SET_UNIT_METRIC}</li>
	 * <li>{@link #AM_SET_UNIT_IMPERIAL_STANDARD}</li>
	 * </ul>
	 */
	public static final String GET_USER_UNIT_AM = "unit";
	/**
	 * The key of the the goal of max step number.<br/>
	 * <b>Value range:</b><br/>
	 * 0-4294967295(0xFFFFFFFF)
	 */
	public static final String GET_USER_TARGET1_AM = "target1";
	/**
	 * The key of the the goal of middle step number.<br/>
	 * <b>Value range:</b><br/>
	 * 0-4294967295(0xFFFFFFFF)
	 */
	public static final String GET_USER_TARGET2_AM = "target2";
	/**
	 * The key of the the goal of min step number.<br/>
	 * <b>Value range:</b><br/>
	 * 0-4294967295(0xFFFFFFFF)
	 */
	public static final String GET_USER_TARGET3_AM = "target3";
	/**
	 * The key of the the goal of swim time(in minutes).<br/>
	 * <b>Value range:</b><br/>
	 * 0-15360(0xFF*60 + 60)
	 */
	public static final String GET_USER_SWIMTARGET_AM = "swim_target";
	
	/**
	 * The action tye of callback after getAlarmClockNum() method is called.<br/>
	 * <b>See Also:</b><br/>
	 * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
	 * <b>KeyList</b> of the message:
	 * <ul>
	 * <li>{@link #GET_ALARMNUM_AM}</li>
	 * <li>{@link #GET_ALARMNUM_ID_AM}</li>
	 * </ul>
	 * <b>message example:</b><br/>
	 * {<br/>
	 * &nbsp; &nbsp; "alarmclocknumber": 2,<br/>
	 * &nbsp; &nbsp; "alarmclocknumberid": [1, 2]<br/>
	 * }<br/>
	 */
	public static final String ACTION_GET_ALARMNUM_AM = "get_alarmnum_am";
	/**
	 * The key of the alarms' count.<br/>
	 * <b>Value range:</b><br/>
	 * 0-3(currently only support 3 alarms)
	 */
	public static final String GET_ALARMNUM_AM = "alarmclocknumber";

	/**
	 * The key of the alarms's id array.<br/>
	 * <b>Value:</b><br/>
	 * Integer array contains set alarms.
	 */
	public static final String GET_ALARMNUM_ID_AM = "alarmclocknumberid";

	/**
	 * The action tye of callback after getAlarmClockDetail() method is called.<br/>
	 * <b>See Also:</b><br/>
	 * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
	 * <b>KeyList</b> of the message:
	 * <ul>
	 * <li>{@link #GET_ALARM_CLOCK_DETAIL}</li>
	 * <li>{@link #GET_ALARM_ID_AM}</li>
	 * <li>{@link #GET_ALARM_TIME_AM}</li>
	 * <li>{@link #GET_ALARM_ISREPEAT_AM}</li>
	 * <li>{@link #GET_ALARM_WEEK_AM}</li>
	 * <li>{@link #GET_ALARM_WEEK_SUNDAY_AM}</li>
	 * <li>{@link #GET_ALARM_WEEK_MONDAY_AM}</li>
	 * <li>{@link #GET_ALARM_WEEK_TUESDAY_AM}</li>
	 * <li>{@link #GET_ALARM_WEEK_WEDNESDAY_AM}</li>
	 * <li>{@link #GET_ALARM_WEEK_THURSDAY_AM}</li>
	 * <li>{@link #GET_ALARM_WEEK_FRIDAY_AM}</li>
	 * <li>{@link #GET_ALARM_WEEK_SATURDAY_AM}</li>
	 * <li>{@link #GET_ALARM_ISON_AM}</li>
	 * </ul>
	 * <b>message example:</b>(In the example, alarm 1 is not repeat and unset, alarm 2 is repeat and set.)<br/>
	 * {<br/>
	 * &nbsp; &nbsp; "alarmclockdetail": [<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "alarmid": 1,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "time": "16:46",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "repeat": false,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "get_alarm_week": {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "sun": true,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "mon": true,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "tue": true,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "wed": true,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "thu": false,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "fri": true,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "sat": true<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; },<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "switch": false<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; },<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "alarmid": 2,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "time": "16:50",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "repeat": true,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "get_alarm_week": {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "sun": true,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "mon": true,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "tue": true,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "wed": true,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "thu": false,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "fri": true,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "sat": true<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; },<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "switch": true<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; }<br/>
	 * &nbsp; &nbsp; ]<br/>
	 * }<br/>
	 * <b>Note:</b> If specified alarm not set yet, the result will not include the id.<br/>
	 */
	public static final String ACTION_GET_ALARMINFO_AM = "get_alarminfo_am";
	/**
	 * The key of the whole alarm information.
	 */
	public static final String GET_ALARM_CLOCK_DETAIL = "alarmclockdetail";
	/**
	 * The key of the alarm id.<br/>
	 * Value should be 1, 2, 3.<br/>
	 * <b>Note:</b> If specified alarm not set yet, the result will not include the id.<br/>
	 *
	 */
	public static final String GET_ALARM_ID_AM = "alarmid";
	/**
	 * The key of the alarm time.<br/>
	 * <b>Value format:</b><br/>
	 * HH:mm
	 */
	public static final String GET_ALARM_TIME_AM = "time";
	/**
	 * The key of whether alarm repeat or not.<br/>
	 * If value is true, it indicates that alarm will repeat as following values:
	 * <ul>
	 * <li>{@link #GET_ALARM_WEEK_SUNDAY_AM}</li>
	 * <li>{@link #GET_ALARM_WEEK_MONDAY_AM}</li>
	 * <li>{@link #GET_ALARM_WEEK_TUESDAY_AM}</li>
	 * <li>{@link #GET_ALARM_WEEK_WEDNESDAY_AM}</li>
	 * <li>{@link #GET_ALARM_WEEK_THURSDAY_AM}</li>
	 * <li>{@link #GET_ALARM_WEEK_FRIDAY_AM}</li>
	 * <li>{@link #GET_ALARM_WEEK_SATURDAY_AM}</li>
	 * </ul>
	 * If the value is false, it indicates that alarm will only play one time.
	 */
	public static final String GET_ALARM_ISREPEAT_AM = "repeat";
	/**
	 * The key of week repeat record.<br/>
	 * It will contains 7 JSON key-values:
	 * <ul>
	 * <li>{@link #GET_ALARM_WEEK_SUNDAY_AM}</li>
	 * <li>{@link #GET_ALARM_WEEK_MONDAY_AM}</li>
	 * <li>{@link #GET_ALARM_WEEK_TUESDAY_AM}</li>
	 * <li>{@link #GET_ALARM_WEEK_WEDNESDAY_AM}</li>
	 * <li>{@link #GET_ALARM_WEEK_THURSDAY_AM}</li>
	 * <li>{@link #GET_ALARM_WEEK_FRIDAY_AM}</li>
	 * <li>{@link #GET_ALARM_WEEK_SATURDAY_AM}</li>
	 * </ul>
	 */
	public static final String GET_ALARM_WEEK_AM = "get_alarm_week";
	/**
	 * The key of whether alarm should play on Sunday.
	 * <ul>
	 * <li>If the value is true, it indicates that it should play.</li>
	 * <li>If the value is false, it indicates that it should NOT play.</li>
	 * </ul>
	 */
	public static final String GET_ALARM_WEEK_SUNDAY_AM = "sun";
	/**
	 * The key of whether alarm should play on Monday.
	 * <ul>
	 * <li>If the value is true, it indicates that it should play.</li>
	 * <li>If the value is false, it indicates that it should NOT play.</li>
	 * </ul>
	 */
	public static final String GET_ALARM_WEEK_MONDAY_AM = "mon";
	/**
	 * The key of whether alarm should play on Tuesday.
	 * <ul>
	 * <li>If the value is true, it indicates that it should play.</li>
	 * <li>If the value is false, it indicates that it should NOT play.</li>
	 * </ul>
	 */
	public static final String GET_ALARM_WEEK_TUESDAY_AM = "tue";
	/**
	 * The key of whether alarm should play on Wednesday.
	 * <ul>
	 * <li>If the value is true, it indicates that it should play.</li>
	 * <li>If the value is false, it indicates that it should NOT play.</li>
	 * </ul>
	 */
	public static final String GET_ALARM_WEEK_WEDNESDAY_AM = "wed";
	/**
	 * The key of whether alarm should play on Thursday.
	 * <ul>
	 * <li>If the value is true, it indicates that it should play.</li>
	 * <li>If the value is false, it indicates that it should NOT play.</li>
	 * </ul>
	 */
	public static final String GET_ALARM_WEEK_THURSDAY_AM = "thu";
	/**
	 * The key of whether alarm should play on Friday.
	 * <ul>
	 * <li>If the value is true, it indicates that it should play.</li>
	 * <li>If the value is false, it indicates that it should NOT play.</li>
	 * </ul>
	 */
	public static final String GET_ALARM_WEEK_FRIDAY_AM = "fri";
	/**
	 * The key of whether alarm should play on Saturday.
	 * <ul>
	 * <li>If the value is true, it indicates that it should play.</li>
	 * <li>If the value is false, it indicates that it should NOT play.</li>
	 * </ul>
	 */
	public static final String GET_ALARM_WEEK_SATURDAY_AM = "sat";
	/**
	 * The key of whether alarm is set/unset.
	 * <ul>
	 * <li>If the value is true, it indicates that it is set.</li>
	 * <li>If the value is false, it indicates that it is unset.</li>
	 * </ul>
	 */
	public static final String GET_ALARM_ISON_AM = "switch";
	
	/**
	 * The action type of callback after setAlarm() method is called.<br/>
	 * It indicates that set alarm successfully.<br/>
	 * <b>See Also:</b><br/>
	 * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}
	 */
	public static final String ACTION_SET_ALARMINFO_SUCCESS_AM = "set_alarminfo_success_am";
	
	/**
	 * The action type of callback after deleteAlarmClock() method is called.<br/>
	 * It indicates that delete alarm successfully.<br/>
	 * <b>See Also:</b><br/>
	 * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}
	 */
	public static final String ACTION_DELETE_ALARM_SUCCESS_AM = "delete_alarm_success_am";
	
	/**
	 * The action tye of callback after getActivityRemind() method is called.<br/>
	 * <b>See Also:</b><br/>
	 * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
	 * <b>KeyList</b> of the message:
	 * <ul>
	 * <li>{@link #GET_ACTIVITY_REMIND_TIME_AM}</li>
	 * <li>{@link #GET_ACTIVITY_REMIND_ISON_AM}</li>
	 * </ul>
	 * <b>message example:</b><br/>
	 * {<br/>
	 * &nbsp; &nbsp; "time": "01:20",<br/>
	 * &nbsp; &nbsp; "switch": true<br/>
	 * }<br/>
	 */
	public static final String ACTION_GET_ACTIVITY_REMIND_AM = "get_activity_remind_am";
	/**
	 * The key of time of activity reminder.<br/>
	 * <b>Value format:</b><br/>
	 * HH:mm
	 */
	public static final String GET_ACTIVITY_REMIND_TIME_AM = "time";
	/**
	 * The key of whether activity reminder open or not.<br/>
	 * <b>Value:</b><br/>
	 * <ul>
	 * <li>true indicates open</li>
	 * <li>false indicates not open</li>
	 * </ul>
	 */
	public static final String GET_ACTIVITY_REMIND_ISON_AM = "switch";
	/**
	 * The action type of callback after setActivityRemind() method is called.<br/>
	 * <b>See Also:</b><br/>
	 * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
	 */
	public static final String ACTION_SET_ACTIVITYREMIND_SUCCESS_AM = "set_activityremind_success_am";
	
	/**
	 * The action type of callback after syncActivityData() method is called.<br/>
	 * <b>See Also:</b><br/>
	 * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
	 * <b>KeyList</b> of the message:
	 * <ul>
	 * <li>{@link #SYNC_ACTIVITY_DATA_AM}</li>
	 * <li>{@link #SYNC_ACTIVITY_EACH_DATA_AM}</li>
	 * <li>{@link #SYNC_ACTIVITY_DATA_TIME_AM}</li>
	 * <li>{@link #SYNC_ACTIVITY_DATA_STEP_AM}</li>
	 * <li>{@link #SYNC_ACTIVITY_DATA_CALORIE_AM}</li>
	 * <li>{@link #SYNC_ACTIVITY_DATA_STEP_LENGTH_AM}</li>
	 * <li>{@link #DATAID}</li>
	 * </ul>
	 * <b>message example:</b><br/>
	 * {<br/>
	 * &nbsp; &nbsp; "activity": [<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "activity_each_data": [<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "time": "2016-07-18 09:00:00",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stepsize": 77,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "step": 1428,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "calorie": 60,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "dataID": "C7AAB9E0561836DB2DF1F3B6DFFE89CF"<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; },<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "time": "2016-07-18 09:05:00",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stepsize": 77,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "step": 1492,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "calorie": 62,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "dataID": "7CC5AFB17BB1AA967EF00D224F727C12"<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; }<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ]<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; },<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "activity_each_data": [<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "time": "2016-07-18 11:40:00",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stepsize": 77,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "step": 1868,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "calorie": 72,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "dataID": "DDC376F96B1245E1B5AD91BB5C57BC21"<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; },<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "time": "2016-07-18 11:45:00",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stepsize": 77,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "step": 1884,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "calorie": 73,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "dataID": "544D49E3C5CAFC5B2D2D4076635A0ABA"<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; }<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ]<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; }<br/>
	 * &nbsp; &nbsp; ]<br/>
	 * }<br/>
	 */
	public static final String ACTION_SYNC_ACTIVITY_DATA_AM = "sync_activity_data_am";
	/**
	 * The key of the whole activity data.
	 */
	public static final String SYNC_ACTIVITY_DATA_AM = "activity";
	/**
	 * The key of the time for every 5 min activity data.<br/>
	 * <b>Value format: </b><br/>
	 * yyyy-MM-dd HH:mm:ss<br/>
	 * <b>Example: </b><br/>
	 * 2016-07-18 09:00:00
	 */
	public static final String SYNC_ACTIVITY_DATA_TIME_AM = "time";
	/**
	 * The key of the step number for every 5 min activity data.<br/>
	 * <b>Value range:</b><br/>
	 * 0-4294967295(0xFFFFFFFF)
	 */
	public static final String SYNC_ACTIVITY_DATA_STEP_AM = "step";
	/**
	 * The key of the step length for every 5 min activity data.<br/>
	 * <b>Value range:</b><br/>
	 * 0-255(0xFF)
	 */
	public static final String SYNC_ACTIVITY_DATA_STEP_LENGTH_AM = "stepsize";
	/**
	 * The key of the calorie for every 5 min activity data.<br/>
	 * <b>Value range:</b><br/>
	 * 0-65535(0xFFFF)
	 */
	public static final String SYNC_ACTIVITY_DATA_CALORIE_AM = "calorie";
	/**
	 * The key of the whole data for every 5 min activity data.
	 */
	public static final String SYNC_ACTIVITY_EACH_DATA_AM = "activity_each_data";
	
	/**
	 * The action tye of callback after syncSleepData() method is called.<br/>
	 * <b>See Also:</b><br/>
	 * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
	 * <b>KeyList</b> of the message:
	 * <ul>
	 * <li>{@link #SYNC_SLEEP_DATA_AM}</li>
	 * <li>{@link #SYNC_SLEEP_EACH_DATA_AM}</li>
	 * <li>{@link #SYNC_SLEEP_DATA_TIME_AM}</li>
	 * <li>{@link #SYNC_SLEEP_DATA_LEVEL_AM}</li>
	 * <li>{@link #DATAID}</li>
	 * </ul>
	 * <b>message example:</b><br/>
	 * {<br/>
	 * &nbsp; &nbsp; "sleep": [<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "sleep_each_data": [<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "time": "2016-07-28 23:39:40",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "level": "1",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "dataID": "B278297A6DE0B28D3B317B5DC23F830A"<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; },<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "time": "2016-07-28 23:44:40",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "level": "1",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "dataID": "DE4E5FAAA6CC79B53EECC8F833BA433E"<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; },<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "time": "2016-07-28 23:49:40",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "level": "1",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "dataID": "B1855AC96F036CE7AA3384D5B00834B5"<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; },<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "time": "2016-07-28 23:54:40",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "level": "1",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "dataID": "3DC6AC62CDCAC8BAF1FCA7A2B4FDCA38"<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; },<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "time": "2016-07-28 23:59:40",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "level": "0",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "dataID": "FD8507F4B25F15B33513315FFF1BFA15"<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; }<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ]<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; },<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "sleep_each_data": [<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "time": "2016-07-29 00:05:01",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "level": "1",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "dataID": "60D6048F3BFBA17CD7FD137E68CE29DD"<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; },<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "time": "2016-07-29 00:10:01",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "level": "0",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "dataID": "1C0E2BBDE85C0D4C4E8895F98C9F6AC4"<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; }<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ]<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; }<br/>
	 * &nbsp; &nbsp; ]<br/>
	 * }<br/>
	 * <br/>
	 */
	public static final String ACTION_SYNC_SLEEP_DATA_AM = "sync_sleep_data_am";
	/**
	 * The key of the whole sleep data.<br/>
	 */
	public static final String SYNC_SLEEP_DATA_AM = "sleep";
	/**
	 * The key of the each 5 minute's sleep time.<br/>
	 * <b>Value format:</b><br/>
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String SYNC_SLEEP_DATA_TIME_AM = "time";
	/**
	 * The key of the each 5 minute's sleep level.<br/>
	 * <b>Value:</b><br/>
	 * <ul>
	 * <li>0 indicates awake</li>
	 * <li>1 indicates light sleep</li>
	 * <li>2 indicates deep sleep</li>
	 * </ul>
	 */
	public static final String SYNC_SLEEP_DATA_LEVEL_AM = "level";

	/**
	 * The key of the each 5 minute data array.<br/>
	 * <b>Value example:</b><br/>
	 * [<br/>
	 * &nbsp; &nbsp; {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; "time": "2016-07-29 00:05:01",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; "level": "1",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; "dataID": "60D6048F3BFBA17CD7FD137E68CE29DD"<br/>
	 * &nbsp; &nbsp; },<br/>
	 * &nbsp; &nbsp; {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; "time": "2016-07-29 00:10:01",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; "level": "0",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; "dataID": "1C0E2BBDE85C0D4C4E8895F98C9F6AC4"<br/>
	 * &nbsp; &nbsp; }<br/>
	 * ]<br/>
	 */
	public static final String SYNC_SLEEP_EACH_DATA_AM = "sleep_each_data";

	/**
	 * The action tye of callback after syncStageReprotData() method is called.<br/>
	 * <b>See Also:</b><br/>
	 * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
	 * <b>KeyList</b> of the message:
	 * <ul>
	 * <li>{@link #SYNC_STAGE_DATA_AM}</li>
	 * <li>{@link #SYNC_STAGE_DATA_TYPE_AM}</li>
	 * <li>{@link #SYNC_STAGE_DATA_STOP_TIME_AM}</li>
	 * <li>{@link #SYNC_STAGE_DATA_USED_TIME_AM}</li>
	 * <li>{@link #SYNC_STAGE_DATA_SLEEP_EFFICIENCY_AM}</li>
	 * <li>{@link #SYNC_STAGE_DATA_SLEEP_IS50MIN_AM}</li>
	 * <li>{@link #SYNC_STAGE_DATA_SWIM_PULL_TIMES_AM}</li>
	 * <li>{@link #SYNC_STAGE_DATA_CALORIE_AM}</li>
	 * <li>{@link #SYNC_STAGE_DATA_SWIM_STROKE_AM}</li>
	 * <li>{@link #SYNC_STAGE_DATA_SWIM_TURNS_AM}</li>
	 * <li>{@link #SYNC_STAGE_DATA_SWIMPOOL_LENGTH_AM}</li>
	 * <li>{@link #SYNC_STAGE_DATA_SWIM_CUTINDIF_AM}</li>
	 * <li>{@link #SYNC_STAGE_DATA_SWIM_CUTOUTDIF_AM}</li>
	 * <li>{@link #SYNC_STAGE_DATA_SWIM_PROCESSFLAG_AM}</li>
	 * <li>{@link #DATAID}</li>
	 * <li>{@link #SYNC_STAGE_DATA_WORKOUT_STEP_AM}</li>
	 * <li>{@link #SYNC_STAGE_DATA_DISTANCE_AM}</li>
	 * <li>{@link #SYNC_STAGE_DATA_VIEW_SUMMARY_DATE_AM}</li>
	 * <li>{@link #SYNC_STAGE_DATA_VIEW_SUMMARY_STEP_AM}</li>
	 * <li>{@link #SYNC_STAGE_DATA_VIEW_SUMMARY_DISTANCE_AM}</li>
	 * <li>{@link #SYNC_STAGE_DATA_VIEW_SUMMARY_CALORIE_AM}</li>
	 * <li>{@link #SYNC_STAGE_DATA_VIEW_SUMMARY_TARGET_AM}</li>
	 * <li>{@link #SYNC_STAGE_DATA_VIEW_SUMMARY_SWIM_AM}</li>
	 *
	 * </ul>
	 * <b>message example:</b><br/>
	 * {<br/>
	 * &nbsp; &nbsp; "stage_data": [<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "type": "page_view_summary",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_view_summary_date": "2016-08-02",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_view_summary_step": 38,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_view_summary_distance": 13,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_view_summary_calorie": 5,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_view_summary_target": 1,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_view_summary_swim": 0<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; },<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "type": "stage_data_type_workout",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stoptime": "2016-07-30 17:54:00",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "usedtime": 34,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_workout_step": 2257,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_distance": "1.73",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "calorie": 63,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "dataID": "5BC76712509E372168FCF2C4B30BAACB"<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; },<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "type": "sleep",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stoptime": "2016-07-30 23:54:00",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "usedtime": 95,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "sleepefficiency": 947,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "is50min": 1<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; },<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "type": "swim",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stoptime": "2016-07-31 09:37:55",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "usedtime": 37,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "number of strokes": 12,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "calorie": 1,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "swimming stroke": 2,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "number of turns": 1,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_swimpool_length": 50,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_cutindif": 14,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_cutoutdif": 0,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_processflag": 1,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "dataID": "F0323A2534E7506E6C063BD1DA42A882"<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; },<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "type": "swim",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stoptime": "2016-07-31 09:39:05",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "usedtime": 23,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "number of strokes": 19,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "calorie": 2,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "swimming stroke": 2,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "number of turns": 2,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_swimpool_length": 50,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_cutindif": 0,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_cutoutdif": 0,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_processflag": 0,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "dataID": "6D309E2FBB447B50D332F6F5E55AC8DA"<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; },<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "type": "swim",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stoptime": "2016-07-31 09:40:43",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "usedtime": 23,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "number of strokes": 30,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "calorie": 3,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "swimming stroke": 2,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "number of turns": 3,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_swimpool_length": 50,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_cutindif": 0,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_cutoutdif": 0,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_processflag": 0,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "dataID": "C68D26A036D2BCBAD9AA62E3B956B0E7"<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; },<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "type": "swim",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stoptime": "2016-07-31 09:42:14",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "usedtime": 23,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "number of strokes": 38,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "calorie": 4,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "swimming stroke": 2,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "number of turns": 4,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_swimpool_length": 50,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_cutindif": 0,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_cutoutdif": 0,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_processflag": 0,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "dataID": "E823F5EBC035B20A90B2C999E5148806"<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; },<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; {<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "type": "swim",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stoptime": "2016-07-31 09:44:55",<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "usedtime": 23,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "number of strokes": 47,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "calorie": 5,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "swimming stroke": 2,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "number of turns": 5,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_swimpool_length": 50,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_cutindif": 0,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_cutoutdif": 714,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "stage_data_processflag": 2,<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "dataID": "002AA68A5DCD05F6595F769D9245E4F2"<br/>
	 * &nbsp; &nbsp; &nbsp; &nbsp; }<br/>
	 * &nbsp; &nbsp; ]<br/>
	 * }<br/>
	 */
	public static final String ACTION_SYNC_STAGE_DATA_AM = "sync_stage_data_am";
	/**
	 * The key of data array of the stage data.
	 */
	public static final String SYNC_STAGE_DATA_AM = "stage_data";
	/**
	 * The key of the type of stage report.<br/>
	 * <b>Value:</b><br/>
	 * <ul>
	 * <li>{@link #SYNC_STAGE_DATA_TYPE_SWIM_AM}</li>
	 * <li>{@link #SYNC_STAGE_DATA_TYPE_WORKOUT_AM}</li>
	 * <li>{@link #SYNC_STAGE_DATA_TYPE_SLEEP_AM}</li>
	 * <li>{@link #SYNC_STAGE_DATA_TYPE_PAGE_VIEW_SUMMARY}</li>
	 * </ul>
	 */
	public static final String SYNC_STAGE_DATA_TYPE_AM = "type";
	/**
	 * Type of work out.
	 */
	public static final String SYNC_STAGE_DATA_TYPE_WORKOUT_AM = "stage_data_type_workout";
	/**
	 * Type of sleep.
	 */
	public static final String SYNC_STAGE_DATA_TYPE_SLEEP_AM = "sleep";
	/**
	 * Type of swim.
	 */
	public static final String SYNC_STAGE_DATA_TYPE_SWIM_AM = "swim";
	/**
	 * Type of page view summary
	 */
	public static final String SYNC_STAGE_DATA_TYPE_PAGE_VIEW_SUMMARY = "page_view_summary";
	/**
	 * The key of stage report stop time<br/>
	 * <b>Value format:</b><br/>
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String SYNC_STAGE_DATA_STOP_TIME_AM = "stoptime";
	/**
	 * The key of stage report used time(in minutes)<br/>
	 * <b>Value range:</b><br/>
	 * 0-65535(0xFFFF)
	 */
	public static final String SYNC_STAGE_DATA_USED_TIME_AM = "usedtime";
	/**
	 * The key of the step number of work out.<br/>
	 * <b>Value range:</b><br/>
	 * 0-4294967295(0xFFFFFFFF)
	 */
	public static final String SYNC_STAGE_DATA_WORKOUT_STEP_AM = "stage_data_workout_step";
	/**
	 * The key of the distance.<br/>
	 * It's string type<br/>
	 * <b>Value format&range:</b><br/>
	 * "123.456"("0.0"-"255.255"(0xFF.0xFF))
	 */
	public static final String SYNC_STAGE_DATA_DISTANCE_AM = "stage_data_distance";
	/**
	 * The key of the calorie.<br/>
	 * <b>Value range:</b><br/>
	 * 0-65535(0xFFFF)
	 */
	public static final String SYNC_STAGE_DATA_CALORIE_AM = "calorie";
	/**
	 * The key of sleep efficiency.<br/>
	 * <b>Value range:</b><br/>
	 * 0.0-100.0
	 */
	public static final String SYNC_STAGE_DATA_SLEEP_EFFICIENCY_AM = "sleepefficiency";
	/**
	 * The key of whether need to extend sleep 50 minutes or not.<br/>
	 * <b>Value:</b><br/>
	 * <ul>
	 * <li>0 indicates not need to extend.</li>
	 * <li>1 indicates need to extend.</li>
	 * </ul>
	 */
	public static final String SYNC_STAGE_DATA_SLEEP_IS50MIN_AM = "is50min";
	/**
	 * The key of swim stroke.<br/>
	 * <b>Value:</b><br/>
	 * <ul>
	 * <li>0 indicates freestyle.</li>
	 * <li>1 indicates breaststroke.</li>
	 * <li>2 indicates backstroke.</li>
	 * <li>5 indicates unknown.</li>
	 * </ul>
	 */
	public static final String SYNC_STAGE_DATA_SWIM_STROKE_AM = "swimming stroke";
	/**
	 * The key of swim arm pulling time.<br/>
	 * <b>Value range:</b><br/>
	 * 0-65535(0xFFFF)
	 */
	public static final String SYNC_STAGE_DATA_SWIM_PULL_TIMES_AM = "number of strokes";
	/**
	 * The key of swim round number.<br/>
	 * <b>Value range:</b><br/>
	 * 0-255(0xFF)
	 */
	public static final String SYNC_STAGE_DATA_SWIM_TURNS_AM = "number of turns";
	/**
	 * The key of the swimming pool length.<br/>
	 * <b>Value range:</b><br/>
	 * 0-255(0xFF)
	 */
	public static final String SYNC_STAGE_DATA_SWIMPOOL_LENGTH_AM = "stage_data_swimpool_length";

	/**
	 * The key of the time of cut in swim and begin swim.(in minutes)<br/>
	 * <b>Value range:</b><br/>
	 * 0-65535(0xFFFF)
	 */
	public static  final String SYNC_STAGE_DATA_SWIM_CUTINDIF_AM = "stage_data_cutindif";

	/**
	 * The key of the time of cut out swim and end swim.(in minutes)<br/>
	 * <b>Value range:</b><br/>
	 * 0-65535(0xFFFF)
	 */
	public static  final String SYNC_STAGE_DATA_SWIM_CUTOUTDIF_AM = "stage_data_cutoutdif";

	/**
	 * The key of the swim process flag.<br/>
	 * <b>Value:</b><br/>
	 * <ul>
	 * <li>0 indicates swim in process.</li>
	 * <li>1 indicates start of swimming.</li>
	 * <li>2 indicates end of swimming.</li>
	 * <li>3 indicates the swim is only a single round.</li>
	 * </ul>
	 */
	public static  final String SYNC_STAGE_DATA_SWIM_PROCESSFLAG_AM = "stage_data_processflag";
	/**
	 * The key of the date of page view summary<br/>
	 * <b>Value format:</b><br/>
	 * yyyy-MM-dd
	 */
	public static  final String SYNC_STAGE_DATA_VIEW_SUMMARY_DATE_AM = "stage_data_view_summary_date";
	/**
	 * The key of the count that user view the step page.<br/>
	 * <b>Value range:</b><br/>
	 * 0-65535(0xFFFF)
	 */
	public static  final String SYNC_STAGE_DATA_VIEW_SUMMARY_STEP_AM = "stage_data_view_summary_step";
	/**
	 * The key of the count that user view the distance page.<br/>
	 * <b>Value range:</b><br/>
	 * 0-65535(0xFFFF)
	 */
	public static  final String SYNC_STAGE_DATA_VIEW_SUMMARY_DISTANCE_AM = "stage_data_view_summary_distance";
	/**
	 * The key of the count that user view the calorie page.<br/>
	 * <b>Value range:</b><br/>
	 * 0-65535(0xFFFF)
	 */
	public static  final String SYNC_STAGE_DATA_VIEW_SUMMARY_CALORIE_AM = "stage_data_view_summary_calorie";
	/**
	 * The key of the count that user view the activity target page.<br/>
	 * <b>Value range:</b><br/>
	 * 0-65535(0xFFFF)
	 */
	public static  final String SYNC_STAGE_DATA_VIEW_SUMMARY_TARGET_AM = "stage_data_view_summary_target";
	/**
	 * The key of the count that user view the swim summary page.<br/>
	 * <b>Value range:</b><br/>
	 * 0-65535(0xFFFF)
	 */
	public static  final String SYNC_STAGE_DATA_VIEW_SUMMARY_SWIM_AM = "stage_data_view_summary_swim";



	/**
	 * The action tye of callback after queryAMState() method is called.<br/>
	 * <b>See Also:</b><br/>
	 * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
	 * <b>KeyList</b> of the message:
	 * <ul>
	 * <li>{@link #QUERY_STATE_AM}</li>
	 * <li>{@link #QUERY_BATTERY_AM}</li>
	 * </ul>
	 * <b>message example:</b><br/>
	 * {<br/>
	 * &nbsp; &nbsp; "query_state": 1,<br/>
	 * &nbsp; &nbsp; "battery": 6<br/>
	 * }<br/>
	 */
	public static final String ACTION_QUERY_STATE_AM = "query_state_am";
	/**
	 * The key of state information.<br/>
	 * <b>Value:</b><br/>
	 * <ul>
	 * <li>0 indicates waist</li>
	 * <li>1 indicates wrist</li>
	 * <li>2 indicates sleep</li>
	 * </ul>
	 */
	public static final String QUERY_STATE_AM = "query_state";
	/**
	 * The key of battery level.<br/>
	 * <b>Value range:</b><br/>
	 * 0-10(10 indicates full)
	 */
	public static final String QUERY_BATTERY_AM = "battery";
	
	/**
	 * The action tye of callback after syncRealData() method is called.<br/>
	 * <b>See Also:</b><br/>
	 * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
	 * <b>KeyList</b> of the message:
	 * <ul>
	 * <li>{@link #SYNC_REAL_STEP_AM}</li>
	 * <li>{@link #SYNC_REAL_CALORIE_AM}</li>
	 * <li>{@link #SYNC_REAL_TOTALCALORIE_AM}</li>
	 * </ul>
	 * <b>message example:</b><br/>
	 * {<br/>
	 * &nbsp; &nbsp; "step": 2771,<br/>
	 * &nbsp; &nbsp; "calorie": 151,<br/>
	 * &nbsp; &nbsp; "totalcalories": 1206<br/>
	 * }<br/>
	 */
	public static final String ACTION_SYNC_REAL_DATA_AM = "sync_real_data_am";
	/**
	 * The key of the real step count.<br/>
	 * <b>Value range:</b><br/>
	 * 0-4294967295(0xFFFFFFFF)
	 */
	public static final String SYNC_REAL_STEP_AM = "step";
	/**
	 * The key of the real calorie(not including BMR).<br/>
	 * <b>Value range:</b><br/>
	 * 0-65535(0xFFFF)
	 */
	public static final String SYNC_REAL_CALORIE_AM = "calorie";

	/**
	 * The key of the real summary calorie(including BMR).<br/>
	 * <b>Value range:</b><br/>
	 * 0-65535 + BMR(0xFFFF + BMR)
	 */
	public static final String SYNC_REAL_TOTALCALORIE_AM = "totalcalories";
	
	/**
	 * The action tye of callback after setUserBmr() or setUserInfo() method is called.<br/>
	 * It indicates that set BMR successfully.<br/>
	 * <b>See Also:</b><br/>
	 * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}
	 */
	public static final String ACTION_SET_BMR_SUCCESS_AM = "set_bmr_success_am";
	
	/**
	 * The action tye of callback after checkSwimPara() method is called.<br/>
	 * <b>See Also:</b><br/>
	 * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
	 * <b>KeyList</b> of the message:
	 * <ul>
	 * <li>{@link #GET_SWIM_SWITCH_AM}</li>
	 * <li>{@link #GET_SWIMLANE_LENGTH_AM}</li>
	 * <li>{@link #GET_SWIM_CUTOUT_HOUR_AM}</li>
	 * <li>{@link #GET_SWIM_CUTOUT_MINUTE_AM}</li>
	 * <li>{@link #GET_SWIM_UNIT_AM}</li>
	 * </ul>
	 * <b>message example:</b><br/>
	 * {<br/>
	 * &nbsp; &nbsp; "get_swim_switch_am": 1,<br/>
	 * &nbsp; &nbsp; "get_swimlane_length": 50,<br/>
	 * &nbsp; &nbsp; "get_swim_cutout_hour_am": 0,<br/>
	 * &nbsp; &nbsp; "get_swim_cutout_min_am": 30,<br/>
	 * &nbsp; &nbsp; "get_swim_unit_am": 0<br/>
	 * }<br/>
	 */
	public static final String ACTION_GET_SWIMINFO_AM = "get_swiminfo_am";
	/**
	 * The key of swimming lane length.<br/>
	 * <b>Value range:</b><br/>
	 * 0-255(0xFF)
	 */
	public static final String GET_SWIMLANE_LENGTH_AM = "get_swimlane_length";
	/**
	 * The key of whether the swim function is open or not.<br/>
	 * <b>Value:</b><br/>
	 * <ul>
	 * <li>0 indicates swim function closed</li>
	 * <li>1/2 indicates swim function open</li>
	 * </ul>
	 */
	public static final String GET_SWIM_SWITCH_AM = "get_swim_switch_am";
	/**
	 * The key of the hour part of cut out swim function.<br/>
	 * <b>Value range:</b><br/>
	 * 0-255(0xFF)
	 */
	public static final String GET_SWIM_CUTOUT_HOUR_AM = "get_swim_cutout_hour_am";
	/**
	 * The key of the minute part of cut out swim function.<br/>
	 * <b>Value range:</b><br/>
	 * 0-255(0xFF)
	 */
	public static final String GET_SWIM_CUTOUT_MINUTE_AM = "get_swim_cutout_min_am";
	/**
	 * The key of swim unit type.<br/>
	 * <b>Value:</b><br/>
	 * <ul>
	 * <li>{@link #AM_SET_UNIT_METRIC}</li>
	 * <li>{@link #AM_SET_UNIT_IMPERIAL_STANDARD}</li>
	 * </ul>
	 */
	public static final String GET_SWIM_UNIT_AM = "get_swim_unit_am";
	
	/**
	 * The action tye of callback after setSwimPara() method is called.<br/>
	 * It indicates that set swim parameters successfully.<br/>
	 * <b>See Also:</b><br/>
	 * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}
	 */
	public static final String ACTION_SET_SWIMINFO_AM = "set_swiminfo_am";
	
	/**
	 * The action type of callback after sendRandom() method is called.<br/>
	 * It indicates that send random number to device successfully,<br/>
	 * you can the number in the JSON string to distinguish the devices.<br/>
	 * <b>See Also:</b><br/>
	 * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
	 * <b>KeyList</b> of the message:
	 * <ul>
	 * <li>{@link #GET_RANDOM_AM}</li>
	 * </ul>
	 * <b>message example:</b><br/>
	 * {<br/>
	 * &nbsp; &nbsp; "random": "150305"<br/>
	 * }<br/>
	 */
	public static final String ACTION_GET_RANDOM_AM = "get_random_am";
	/**
	 * The key of the random number sent to device.<br/>
	 * The value will be a 6-long number decimal string.<br/>
	 * <b>Value range:</b><br/>
	 * "000000"-"999999"
	 */
	public static final String GET_RANDOM_AM = "random";
	
	
	/**
	 * the switch of am device alarm and remind function.
	 */
	public static final boolean AM_SWITCH_OPEN = true;
	
	/**
	 * the switch of am device alarm and remind function.
	 */
	public static final boolean AM_SWITCH_CLOSE = false;
	
	/**
	 * am device alarm and remind function is repeat or not.
	 */
	public static final boolean AM_SWITCH_REPEAT = true;
	
	/**
	 * am device alarm and remind function is repeat or not.
	 */
	public static final boolean AM_SEITCH_NOT_REPEAT = false;
	
	/**
	 * The value indicates male.
	 */
	public static final int AM_SET_MALE = 1;
	
	/**
	 * The value indicates female.
	 */
	public static final int AM_SET_FEMALE = 0;
	
	/**
	 * The value indicates metric unit type(kilometre/metre).
	 */
	public static final int AM_SET_UNIT_METRIC = 1;
	
	/**
	 * The value indicates imperial standard unit type(miles/yard).
	 */
	public static final int AM_SET_UNIT_IMPERIAL_STANDARD = 0;
	
	/**
	 * The value indicates whole world 12 hour mode
	 */
	public static final int AM_SET_12_HOUR_MODE = 0;
	
	/**
	 * The value indicates whole world 24 hour mode
	 */
	public static final int AM_SET_24_HOUR_MODE = 1;

	/**
	 * The value indicates Europe world 12 hour mode
	 */
	public static final int AM_SET_EUROPE_12_HOUR_MODE = 3;

	/**
	 * The value indicates Europe world 24 hour mode
	 */
	public static final int AM_SET_EUROPE_24_HOUR_MODE = 5;

	/**
	 * The value indicates except Europe world 12 hour mode
	 */
	public static final int AM_SET_EXCEPT_EUROPE_12_HOUR_MODE = 2;

	/**
	 * The value indicates except Europe world 24 hour mode
	 */
	public static final int AM_SET_EXCEPT_EUROPE_24_HOUR_MODE = 4;

	/**
	 * The action type of callback after setHourMode() method is called.<br/>
	 * It indicates that set time mode successfully.<br/>
	 * <b>See Also:</b><br/>
	 * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}
	 */
	public static final String ACTION_SET_HOUR_MODE_SUCCESS_AM = "set_hour_mode_success_am";

	/**
	 * The action type of callback after getHourMode() method is called<br/>
	 * <b>See Also:</b><br/>
	 * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
	 * <b>KeyList</b> of the message:
	 * <ul>
	 * <li>{@link #GET_HOUR_MODE_AM}</li>
	 * </ul>
	 * <b>message example:</b><br/>
	 * {<br/>
	 * &nbsp; &nbsp; "hourtype": 1<br/>
	 * }<br/>
	 */
	public static final String ACTION_GET_HOUR_MODE_AM = "get_hour_mode_am";
	/**
	 * The key of the time mdoe.<br/>
	 * <b>Value:</b><br/>
	 * <ul>
	 * <li>{@link #AM_SET_12_HOUR_MODE}</li>
	 * <li>{@link #AM_SET_24_HOUR_MODE}</li>
	 * <li>{@link #AM_SET_EXCEPT_EUROPE_12_HOUR_MODE}</li>
	 * <li>{@link #AM_SET_EUROPE_12_HOUR_MODE}</li>
	 * <li>{@link #AM_SET_EXCEPT_EUROPE_24_HOUR_MODE}</li>
	 * <li>{@link #AM_SET_EUROPE_24_HOUR_MODE}</li>
	 * </ul>
	 */
	public static final String GET_HOUR_MODE_AM = "hourtype";

	/**
	 * The action type of callback after setMode() method is called<br/>
	 * It indicates that set device mode successfully.
	 * <b>See Also:</b><br/>
	 * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}
	 */
	public static final String ACTION_SET_DEVICE_MODE_AM = "set_device_mode_am";
	/**
	 * Indicates device is sleep mode.
	 */
	public static final int AM_DEVICE_MODE_SLEEP = 0;
	/**
	 * Indicates device is activity mode.
	 */
	public static final int AM_DEVICE_MODE_ACTIVITY = 1;
	/**
	 * Indicates device is flight mode.
	 */
	public static final int AM_DEVICE_MODE_FLIGHT = 2;
	/**
	 * Indicates device is driving mode.
	 */
	public static final int AM_DEVICE_MODE_DRIVING = 3;

	/**
	 * The result of binding am to cloud: success.
	 */
	public static final String ACTION_CLOUD_BINDING_AM_SUCCESS = "cloud_bind_am_success";

	/**
	 * The result of binding am to cloud: fail.
	 */
	public static final String ACTION_CLOUD_BINDING_AM_FAIL = "cloud_bind_am_fail";

	/**
	 * The result of unbinding am to cloud: success.
	 */
	public static final String ACTION_CLOUD_UNBINDING_AM_SUCCESS = "cloud_unbind_am_success";

	/**
	 * The result of unbinding am to cloud: fail.
	 */
	public static final String ACTION_CLOUD_UNBINDING_AM_FAIL = "cloud_unbind_am_fail";

	/**
	 * Search the device mac of user from cloud.
	 */
	public static final String ACTION_CLOUD_SEARCH_AM = "cloud_search_am";

	/**
	 * Search the device mac of user from cloud fail.
	 */
	public static final String ACTION_CLOUD_SEARCH_FAIL_AM = "cloud_search_fail";

	/**
	 * Device mac of cloud user.
	 */
	public static final String CLOUD_SEARCH_AM = "search_mac";

	/**
	 * The key of the MD5 hash of the data.
	 */
	public static final String DATAID = "dataID";
	/**
	 * The action type of callback after setPicture() method is called<br/>
	 * It indicates that set picture successfully.
	 * <b>See Also:</b><br/>
	 * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}
	 */
	public static final String ACTION_SET_PICTURE_SUCCESS_AM = "set_picture_success_am";
	/**
	 * The action tye of callback after getPicture() method is called.<br/>
	 * <b>See Also:</b><br/>
	 * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
	 * <b>KeyList</b> of the message:
	 * <ul>
	 * <li>{@link #GET_PICTURE_AM}</li>
	 * </ul>
	 * <b>message example:</b><br/>
	 * {<br/>
	 * &nbsp; &nbsp; "get_picture_am": 0<br/>
	 * }<br/>
	 */
	public static final String ACTION_GET_PICTURE_AM = "get_picture_am";
	/**
	 * The key of the picture index.<br/>
	 * <b>Value:</b><br/>
	 * <ul>
	 * <li>0 indicates the frog picture.</li>
	 * <li>1 indicates the default picture.</li>
	 * </ul>
	 */
	public static final String GET_PICTURE_AM = "get_picture_am";
}
