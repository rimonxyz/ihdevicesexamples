package com.ihealth.communication.control;

import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;

/**
 * Public APIs for the Bg5 serials device profiles
 * <p> Clients need to call{@link iHealthDevicesManager#registerClientCallback} register.
 * When new data from Bg5 device, The Callback{@link iHealthDevicesCallback#onDeviceNotify} can be triggered should call.
 */
public class Bg5Profile {

    /**
     * Callback indicating the bottleId of Bg5 device.
     * <ul>
     * KeyList:
     * <ul>
     * <li>
     * {@link #GET_BOTTLEID}
     * </li>
     * </ul>
     * eg. {"bottleid":926305}
     * </ul>
     */
    public static final String ACTION_GET_BOTTLEID = "action_get_bottleid";

    /**
     * The bottleId of Bg5 device.
     * <p/>
     * Range: 0~0xFFFFFFFF
     */
    public static final String GET_BOTTLEID = "bottleid";

    /**
     * Callback indicating the code info of Bg5 device.
     * <ul>
     * KeyList:
     * <ul>
     * <li>
     * {@link #GET_USENUM}
     * {@link #GET_EXPIRECTIME}
     * </li>
     * </ul>
     * eg. {"usenum":0,"expiretime":"2016-02-14"}
     * </ul>
     */
    public static final String ACTION_GET_CODEINFO = "action_get_codeinfo";

    /**
     * The strip used num of Bg5 device.
     */
    public static final String GET_USENUM = "usenum";

    /**
     * The strip expire time of Bg5 device.
     */
    public static final String GET_EXPIRECTIME = "expiretime";

    /**
     * Callback indicating power of Battery for Bg5 device.
     * <ul>
     * KeyList:
     * <ul>
     * <li>
     * {@link #BATTERY_BG}
     * </li>
     * </ul>
     * eg. {"battery":36}
     * </ul>
     */
    public static final String ACTION_BATTERY_BG = "action_battery_bg";

    /**
     * The power of Battery for Bg device.
     * <p/>
     * Validate Range : 0~100     -1 : not support     255: in charging
     */
    public static final String BATTERY_BG = "battery";

    /**
     * Callback indicating the error of Bg5 device.
     * <ul>
     * KeyList:
     * <ul>
     * <li>
     * {@link #ERROR_NUM_BG}
     * </li>
     */
    public static final String ACTION_ERROR_BG = "action_measure_error";

    /**
     * Flag Error number of Bg5 device.
     * <p>Error code and Description</p>
     * <p>0:Battery is low.</p>
     * <p>1:Glucose test result is out of the measurement range.</p>
     * <p>2:Unknown interference detected, please repeat the test.</p>
     * <p>3:Strip is used or unknown moisture detected, discard the test strip and repeat the test with a new strip..</p>
     * <p>4:Reading transmission error. Repeat the test with a new test strip. If the problem persists, contact iHealth customer service for assistance.</p>
     * <p>5:The environmental temperature is beyond normal range, place the meter at room temperature for at least 30 minutes, then repeat the test.</p>
     * <p>6:The environmental temperature is beyond normal range, place the meter at room temperature for at least 30 minutes, then repeat the test.</p>
     * <p>7:Test strip coding error.</p>
     * <p>8:Communication error, press"START" or rescan the code to repeat the test.</p>
     * <p>9:Strip removed in the middle of reading, repeat the test with a new strip.</p>
     * <p>10:Insert a new test strip and repeat the test.</p>
     * <p>11:Cannot write to SN or KEY.</p>
     * <p>12:Please set time.</p>
     * <p>13:0 test strips remaining.</p>
     * <p>14:Test strip expired.</p>
     * <p>15:Unplug the charging cable before testing.</p>
     * <p>112:Device don't support to query energy.</p>
     * <p>400:Parameters out of range.</p>
     */
    public static final String ERROR_NUM_BG = "error_num";

    /**
     * Callback indicating the strip in action.
     */
    public static final String ACTION_STRIP_IN = "action_measure_strip_in";

    /**
     * Callback indicating the get blood action.
     */
    public static final String ACTION_GET_BLOOD = "action_measure_get_blood";

    /**
     * Callback indicating the result that the On-line has been completed.
     * <ul>
     * KeyList:
     * <ul>
     * <li>
     * {@link #ONLINE_RESULT_BG}
     * {@link #DATA_ID}
     * </li>
     * </ul>
     * eg. {"ONLINE_RESULT_BG":100,"dataID":"DABCDABCD236ACF789A"}
     * </ul
     */
    public static final String ACTION_ONLINE_RESULT_BG = "action_value_bg";

    /**
     * The result that the On-line has been completed.
     * <p/>
     * Range: 0~0xFFFF (the normal range is 20~600 mg/mL)
     */
    public static final String ONLINE_RESULT_BG = "result";

    /**
     * Measurement time.
     */
    public static final String MEASUREMENT_DATE_BG = "measure_date_bg";

    /**
     * Callback indicating the strip out action.
     */
    public static final String ACTION_STRIP_OUT = "action_measure_strip_out";

    /**
     * Callback indicating historical data for Bg5 device.
     * <ul>
     * KeyList:
     * <ul>
     * <li>
     * {@link #HISTORICAL_DATA_BG}
     * </li>
     * </ul>
     * eg. {"his_data_bg":{"history":[]}}
     * </ul>
     */
    public static final String ACTION_HISTORICAL_DATA_BG = "action_historicaldata_bg";

    /**
     * historical data for Bg5 device.
     */
    public static final String HISTORICAL_DATA_BG = "his_data_bg";

    /**
     * Callback indicating set time to Bg5 device.
     * <ul>
     * KeyList:
     * <ul>
     * <li>
     * {@link #SET_TIME}
     * </li>
     * </ul>
     * eg. {"set_time":true}
     * </ul>
     */
    public static final String ACTION_SET_TIME = "action_set_time";

    /**
     * The result of set time.
     * <p/>
     * boolean : true   false
     */
    public static final String SET_TIME = "set_time";

    /**
     * Callback indicating set unit to Bg5 device.
     * <ul>
     * KeyList:
     * <ul>
     * <li>
     * {@link #SET_UNIT}
     * </li>
     * </ul>
     * eg. {"set_unit":true}
     * </ul>
     */
    public static final String ACTION_SET_UNIT = "action_set_unit";

    /**
     * The result of set time.
     * <p/>
     * boolean : true   false
     */
    public static final String SET_UNIT = "set_unit";

    /**
     * Callback indicating the start mode of Bg5 device.
     */
    public static final String ACTION_START_MODE = "action_start_mode";

    /**
     * the start mode of Bg5 device.
     */
    public static final String START_MODE_EXTRA = "start_mode";

    /**
     * Callback indicating the start measure action of Bg5 device.
     * <ul>
     * KeyList:
     * <ul>
     * <li>
     * {@link #START_MEASURE}
     * </li>
     * </ul>
     * eg. {"start_measure":true}
     * </ul>
     */
    public static final String ACTION_START_MEASURE = "action_start_measure";

    /**
     * The result of start measure.
     * <p/>
     * boolean : true   false
     */
    public static final String START_MEASURE = "start_measure";

    /**
     * Callback indicating delete historical data of Bg5 device.
     * <ul>
     * KeyList:
     * <ul>
     * <li>
     * {@link #DELETE_HISTORICAL_DATA}
     * </li>
     * </ul>
     * eg. {"delete_historical_data":true}
     * </ul>
     */
    public static final String ACTION_DELETE_HISTORICAL_DATA = "action_delete_historical_data";

    /**
     * The result of delete historical_data
     * <p/>
     * boolean : true   false
     */
    public static final String DELETE_HISTORICAL_DATA = "delete_historical_data";

    /**
     * the data id
     */
    public static final String DATA_ID = "dataID";

    /**
     * Callback indicating the set set bottle message to Bg5 device.
     * <ul>
     * KeyList:
     * <ul>
     * <li>
     * {@link #SET_BOTTLE_MESSAGE}
     * </li>
     * </ul>
     * eg. {"set_bottle_message":true}
     * </ul>
     */
    public static final String ACTION_SET_BOTTLE_MESSAGE_SUCCESS = "action_set_bottle_message_success";

    /**
     * The result of set bottle message.
     * <p/>
     * boolean : true   false
     */
    public static final String SET_BOTTLE_MESSAGE = "set_bottle_message";

    public static final String ACTION_SET_BOTTLE_ID_SUCCESS = "action_setbottleid_success";

    /**
     * Bottle id
     */
    public static final String CODESTRIPANALYSIS_BOTTLEID = "bottleid";

    /**
     * Strip number
     */
    public static final String CODESTRIPANALYSIS_STRIPNUM = "stripnum";

    /**
     * Due date
     */
    public static final String CODESTRIPANALYSIS_DUEDATE = "duedate";

    /**
     * Callback indicating keep linking with Bg5 device.
     * <ul>
     * KeyList:
     * <ul>
     * <li>
     * {@link #KEEP_LINK}
     * </li>
     * </ul>
     * eg. {"keep_link":true}
     * </ul>
     */
    public static final String ACTION_KEEP_LINK = "action_keep_link";

    /**
     * The result of keep linking with Bg5 device.
     * <p/>
     * boolean : true   false
     */
    public static final String KEEP_LINK = "keep_link";

    /**
     * Callback indicating historical data number
     * <ul>
     * KeyList:
     * <ul>
     * <li>
     * {@link #HISTORICAL_NUM_BG}
     * </li>
     * </ul>
     * eg. {"count":0}
     * </ul>
     */
    public static final String ACTION_HISTORICAL_NUM_BG = "action_historicalnum_bg";

    /**
     * Number of historical data
     * <p/>
     * Range:0~255
     */
    public static final String HISTORICAL_NUM_BG = "count";
}
