package com.ihealth.communication.control;

/**
 * Public APIs for the Bg1 serials device profiles
 * <p> Clients need to register BroadcastReceiver to receive these Actions.
 */
public class Bg1Profile {

    /**
     * Callback indicating the new device get ready.
     */
    public static final String ACTION_BG1_DEVICE_READY = "action_device_ready_for_bg1";

    /**
     * Callback indicating the idps info of new bg1 device.
     * <ul>
     * KeyList:
     * <ul>
     * <li>
     * {@link #BG1_IDPS}
     * </li>
     */
    public static final String ACTION_BG1_IDPS = "action_idps_for_bg1";

    /**
     * The idps info of new bg1 device.
     * <p/>
     * eg. {"IDPS":[{"DeviceId":"611289355662","FirmWare":"13.7.0","HardWare":"13.7.0"}]}
     */
    public static final String BG1_IDPS = "idps_for_bg1";

    /**
     * Callback indicating the connect result of bg1 device.
     * <ul>
     * KeyList:
     * <ul>
     * <li>
     * {@link #BG1_CONNECT_RESULT}
     * </li>
     */
    public static final String ACTION_BG1_CONNECT_RESULT = "action_connect_result_for_bg1";

    /**
     * The connect result of bg1 device.
     * <p/>
     * value : 0 success; other error
     */
    public static final String BG1_CONNECT_RESULT = "connect_result_for_bg1";//0 success; other error

    /**
     * Callback indicating the send code result of bg1 device.
     * <ul>
     * KeyList:
     * <ul>
     * <li>
     * {@link #BG1_SENDCODE_RESULT}
     * </li>
     */
    public static final String ACTION_BG1_SENDCODE_RESULT = "action_sendcode_result_for_bg1";

    /**
     * The send code result of bg1 device.
     * <p/>
     * value : 0 success; other error
     */
    public static final String BG1_SENDCODE_RESULT = "sendcode_result_for_bg1";

    /**
     * Callback indicating the error of Bg1 device.
     * <ul>
     * KeyList:
     * <ul>
     * <li>
     * {@link #BG1_MEASURE_ERROR}
     * </li>
     * </ul>
     * eg. {"error_num_for_bg1":0}
     * </ul>
     */
    public static final String ACTION_BG1_MEASURE_ERROR = "action_measure_error_for_bg1";

    /**
     * Flag Error number of Bg1 device.
     * <p>Error code and Description</p>
     * <p>0:Battery is low.</p>
     * <p>1:Glucose test result is out of the measurement range.</p>
     * <p>2:Unknown interference detected, please repeat the test.</p>
     * <p>3:Strip is used or unknown moisture detected, discard the test strip and repeat the test with a new strip..</p>
     * <p>4:Communication error,resend the code to repeat the test. {@link Bg1Control#sendCode(String)}</p>
     * <p>5:The environmental temperature is beyond normal range, place the meter at room temperature for at least 30 minutes, then repeat the test.</p>
     * <p>6:The environmental temperature is beyond normal range, place the meter at room temperature for at least 30 minutes, then repeat the test.</p>
     * <p>7:Test strip coding error.</p>
     * <p>8:Communication error,rescan the code to repeat the test.</p>
     * <p>9:Communication error,Repeat the test with a new test strip. If the problem persists, contact iHealth customer service for assistance.</p>
     * <p>10:Communication error,Repeat the test with a new test strip. If the problem persists, contact iHealth customer service for assistance.</p>
     * <p>11:Communication error,Repeat the test with a new test strip. If the problem persists, contact iHealth customer service for assistance.</p>
     * <p>12:Glucose test result is low.</p>
     * <p>13:Glucose test result is high.</p>
     * <p>400:Parameters out of range.</p>
     * <p>401:Dolby is on ,please turn it off.</p>
     */
    public static final String BG1_MEASURE_ERROR = "error_num_for_bg1";

    /**
     * Callback indicating the strip in action.
     */
    public static final String ACTION_BG1_MEASURE_STRIP_IN = "action_measure_strip_in_for_bg1";

    /**
     * Callback indicating the get blood action.
     */
    public static final String ACTION_BG1_MEASURE_GET_BLOOD = "action_measure_get_blood_for_bg1";

    /**
     * Callback indicating the measure result.
     * <ul>
     * KeyList:
     * <ul>
     * <li>
     * {@link #BG1_MEASURE_RESULT}
     * </li>
     */
    public static final String ACTION_BG1_MEASURE_RESULT = "action_measure_result_for_bg1";

    /**
     * The measure result
     * <p/>
     * Range : 20-600 mg/dL
     */
    public static final String BG1_MEASURE_RESULT = "measure_result_for_bg1";

    /**
     * Callback indicating the strip out action.
     */
    public static final String ACTION_BG1_MEASURE_STRIP_OUT = "action_measure_strip_out_for_bg1";

    /**
     * Callback indicating the Bg1 device get in standby mode.
     */
    public static final String ACTION_BG1_MEASURE_STANDBY = "action_measure_standby_for_bg1";

    /**
     * the data id
     */
    public static final String DATA_ID = "dataID";
}
