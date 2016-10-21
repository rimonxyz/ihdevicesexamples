
package com.ihealth.communication.control;

import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;

/**
 * /** Public APIs for the PO serials device profiles Clients need to call
 * <p>
 * {@link iHealthDevicesManager#registerClientCallback} register. When new data from Po device, The Callback
 * {#iHealthDevicesCallback #onDeviceNotify} can be triggered should call.
 */
public class PoProfile {
    /**
     * The action type of callback after getHistoryData() method is called.<br/>
     * Callback indicating historical data for Po device.<br/>
     * <b>See Also:</b><br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>
     * {@link #OFFLINEDATA_PO}
     * </li>
     * <li>
     * {@link #DATAID}
     * </li>
     * <li>
     * {@link #MEASURE_DATE_PO}
     * </li>
     * <li>
     * {@link #BLOOD_OXYGEN_PO}
     * </li>
     * <li>
     * {@link #PULSE_RATE_PO}
     * </li>
     * <li>
     * {@link #PULSE_WAVE_PO}
     * </li>
     * </ul>
     */
    public static final String ACTION_OFFLINEDATA_PO = "offlineData_po";
    /**
     * The key of the historical data for Po device.<br/>
     * Returns the message Key corresponding relation:<br/>
     * <ul>
     * <li>
     * OFFLINEDATA_PO ----> "offlineData".<br/>
     * </li>
     * </ul>
     */
    public static final String OFFLINEDATA_PO = "offlineData";
    /**
     * The key of the data id for Po device.<br/>
     * Returns the message Key corresponding relation:<br/>
     * <ul>
     * <li>
     * DATAID ----> "dataID".<br/>
     * </li>
     * </ul>
     * <b>Example: </b><br/>
     * 67FFC7A454148723718144A604D4152B
     */
    public static final String DATAID = "dataID";
    /**
     * Measure date  for Po device.
     */
    /**
     * The key of the measure date for Po device.<br/>
     * Returns the message Key corresponding relation:<br/>
     * <ul>
     * <li>
     * MEASURE_DATE_PO ----> "measureDate".<br/>
     * </li>
     * </ul>
     */
    public static final String MEASURE_DATE_PO = "measureDate";
    /**
     * The key of the blood oxygen data for Po device.<br/>
     * Returns the message Key corresponding relation:<br/>
     * <ul>
     * <li>
     * BLOOD_OXYGEN_PO ----> "bloodoxygen".<br/>
     * </li>
     * </ul>
     * <b>Value range:</b><br/>
     * 0-255(0xFF)
     */
    public static final String BLOOD_OXYGEN_PO = "bloodoxygen";
    /**
     * The key of the pulse rate data for Po device.<br/>
     * Returns the message Key corresponding relation:<br/>
     * <ul>
     * <li>
     * PULSE_RATE_PO ----> "heartrate".<br/>
     * </li>
     * </ul>
     * <b>Value range:</b><br/>
     * 0-255(0xFF)
     */
    public static final String PULSE_RATE_PO = "heartrate";
    /**
     * The key of the pulse wave for Po device.<br/>
     * Returns the message Key corresponding relation:<br/>
     * <ul>
     * <li>
     * PULSE_WAVE_PO ----> "pulseWave".<br/>
     * </li>
     * </ul>
     * <b>Example: </b><br/>
     * [2415,2371,2279]
     */
    public static final String PULSE_WAVE_PO = "pulseWave";
    /**
     * The action type of callback after getHistoryData() method is called.<br/>
     * Callback indicating no historical data for Po device.<br/>
     * <b>See Also:</b><br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <b>message example:</b><br/>
     * {}<br/>
     */
    public static final String ACTION_NO_OFFLINEDATA_PO = "noOfflineData_po";
    /**
     * The action type of callback after getBattery() method is called.<br/>
     * <b>See Also:</b><br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>
     * {@link #BATTERY_PO}
     * </li>
     * </ul>
     * <b>message example:</b><br/>
     * {<br/>
     * &nbsp; &nbsp; "battery": 90<br/>
     * }<br/>
     */
    public static final String ACTION_BATTERY_PO = "battery_po";
    /**
     * The key of the power of battery for Po device.<br/>
     * Returns the message Key corresponding relation:<br/>
     * <ul>
     * <li>
     * BATTERY_PO ----> "battery".<br/>
     * </li>
     * </ul>
     * <b>Value range:</b><br/>
     * 0-100(0xff)
     */
    public static final String BATTERY_PO = "battery";
    /**
     * The action type of callback after startMeasure() method is called.<br/>
     * Show the real-time measuring data for Po device.<br/>
     * <b>See Also:</b><br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>
     * {@link #PULSE_WAVE_PO}
     * </li>
     * <li>
     * {@link #PI_PO}
     * </li>
     * <li>
     * {@link #PULSE_STRENGTH_PO}
     * </li>
     * <li>
     * {@link #BLOOD_OXYGEN_PO}
     * </li>
     * <li>
     * {@link #PULSE_RATE_PO}
     * </li>
     * </ul>
     * <b>message example:</b><br/>
     * {<br/>
     * &nbsp; &nbsp; "pulseWave": [2415,2371,2279],<br/>
     * &nbsp; &nbsp; "pi": 0.03999999910593033,<br/>
     * &nbsp; &nbsp; "pulsestrength": 6,<br/>
     * &nbsp; &nbsp; "bloodoxygen": 70,<br/>
     * &nbsp; &nbsp; "heartrate": 30<br/>
     * }<br/>
     */
    public static final String ACTION_LIVEDA_PO = "liveData_po";
    /**
     * The key of the PI data for Po device.<br/>
     * Returns the message Key corresponding relation:<br/>
     * <ul>
     * <li>
     * PI_PO ----> "pi".<br/>
     * </li>
     * </ul>
     * <b>Example: </b><br/>
     * 0.03999999910593033
     */
    public static final String PI_PO = "pi";
    /**
     * The key of the pulse strength data for Po device.<br/>
     * Returns the message Key corresponding relation:<br/>
     * <ul>
     * <li>
     * PULSE_STRENGTH_PO ----> "pulsestrength".<br/>
     * </li>
     * </ul>
     * <b>Value range:</b><br/>
     * 0-255(0xff)
     */
    public static final String PULSE_STRENGTH_PO = "pulsestrength";
    /**
     * The action type of callback after startMeasure() method is called.<br/>
     * Callback indicating result data for Po device.<br/>
     * <b>See Also:</b><br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>
     * {@link #PULSE_WAVE_PO}
     * </li>
     * <li>
     * {@link #DATAID}
     * </li>
     * <li>
     * {@link #PI_PO}
     * </li>
     * <li>
     * {@link #PULSE_STRENGTH_PO}
     * </li>
     * <li>
     * {@link #BLOOD_OXYGEN_PO}
     * </li>
     * <li>
     * {@link #PULSE_RATE_PO}
     * </li>
     * </ul>
     * <b>message example:</b><br/>
     * {<br/>
     * &nbsp; &nbsp; "pulseWave": [0,0,0],<br/>
     * &nbsp; &nbsp; "dataID": 67FFC7A454148723718144A604D4152B,<br/>
     * &nbsp; &nbsp; "pi": 0.0430000014603138,<br/>
     * &nbsp; &nbsp; "pulsestrength": 0,<br/>
     * &nbsp; &nbsp; "bloodoxygen": 99,<br/>
     * &nbsp; &nbsp; "heartrate": 65<br/>
     * }<br/>
     */
    public static final String ACTION_RESULTDATA_PO = "resultData_po";
    /**
     * Callback indicating no the error for Po device.<br/>
     * <b>See Also:</b><br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     */
    public static final String ACTION_ERROR_PO = "error_po";

    /**
     * Callback indicating get PLX Spot-check Measurement for Po device.<br/>
     * <b>See Also:</b><br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>
     * {@link #SPOT_CHECK_CPO}
     * </li>
     * <li>
     * {@link #CPO_TIMESTAMP_FLAG}
     * </li>
     * <li>
     * {@link #CPO_MEASUREMENT_STATUS_FLAG}
     * </li>
     * <li>
     * {@link #CPO_DS_STATUS_FLAG}
     * </li>
     * <li>
     * {@link #CPO_PA_INDEX_FLAG}
     * </li>
     * <li>
     * {@link #CPO_CLOCK_SET_FLAG}
     * </li>
     * <li>
     * {@link #CPO_SPO2_DATA}
     * </li>
     * <li>
     * {@link #CPO_PR_DATA}
     * </li>
     * <li>
     * {@link #CPO_TIMESTAMP_DATA}
     * </li>
     * <li>
     * {@link #CPO_MEASUREMENT_ONGOING_DATA}
     * </li>
     * <li>
     * {@link #CPO_EARLY_ESTIMATED_DATA}
     * </li>
     * <li>
     * {@link #CPO_VALIDATED_DATA}
     * </li>
     * <li>
     * {@link #CPO_FULLY_QUALIFIED_DATA}
     * </li>
     * <li>
     * {@link #CPO_MEASUREMENT_STORAGE_DATA}
     * </li>
     * <li>
     * {@link #CPO_DEMONSTRATION_DATA}
     * </li>
     * <li>
     * {@link #CPO_TESTING_DATA}
     * </li>
     * <li>
     * {@link #CPO_CALIBRATION_ONGOING_DATA}
     * </li>
     * <li>
     * {@link #CPO_MEASUREMENT_UNAVAILABLE_DATA}
     * </li>
     * <li>
     * {@link #CPO_QUESTIONABLE_DETECTED_DATA}
     * </li>
     * <li>
     * {@link #CPO_INVALID_DETECTED_DATA}
     * </li>
     * <li>
     * {@link #CPO_EXTENDED_DISPLAY_UPDATE_ONGOING_DATA}
     * </li>
     * <li>
     * {@link #CPO_EQUIPMENT_MALFUNCTION_DATA}
     * </li>
     * <li>
     * {@link #CPO_SIGNAL_PROCESSING_IRREGULARITY_DATA}
     * </li>
     * <li>
     * {@link #CPO_INADEQUATE_SIGNAL_DETECTED_DATA}
     * </li>
     * <li>
     * {@link #CPO_POOR_SIGNAL_DETECTED_DATA}
     * </li>
     * <li>
     * {@link #CPO_LOW_PERFUSION_DETECTED_DATA}
     * </li>
     * <li>
     * {@link #CPO_ERRATIC_SIGNAL_DETECTED_DATA}
     * </li>
     * <li>
     * {@link #CPO_NONPULSATILE_SIGNAL_DETECTED_DATA}
     * </li>
     * <li>
     * {@link #CPO_QUESTIONABLE_PULSE_DETECTED_DATA}
     * </li>
     * <li>
     * {@link #CPO_SIGNAL_ANALYSIS_ONGOING_DATA}
     * </li>
     * <li>
     * {@link #CPO_SENSOR_INTERFACE_DETECTED_DATA}
     * </li>
     * <li>
     * {@link #CPO_SENSOR_UNCONNECTED_TO_USER_DATA}
     * </li>
     * <li>
     * {@link #CPO_UNKNOWN_SENSOR_CONNECTED_DATA}
     * </li>
     * <li>
     * {@link #CPO_SENSOR_DISPLACED_DATA}
     * </li>
     * <li>
     * {@link #CPO_SENSOR_MALFUNCTION_DATA}
     * </li>
     * <li>
     * {@link #CPO_SENSOR_DISCONNECTED_DATA}
     * </li>
     * <li>
     * {@link #CPO_PULSE_AMPLITUDE_INDEX_DATA}
     * </li>
     * </ul>
     */
    public static final String ACTION_SPOT_CHECK_CPO = "spot_check_cpo";
    /**
     * The timestamp flag for pulse oximeter :  0 is invalid, 1 is valid.
     */
    public static final String CPO_TIMESTAMP_FLAG = "cpo_timestamp_flag";
    /**
     * The Measurement Status Field for pulse oximeter :  0 is invalid, 1 is valid.
     */
    public static final String CPO_MEASUREMENT_STATUS_FLAG = "cpo_measurement_status_flag";
    /**
     * The Device and Sensor Status Field for pulse oximeter :  0 is invalid, 1 is valid.
     */
    public static final String CPO_DS_STATUS_FLAG = "cpo_ds_status_flag";
    /**
     * The Pulse Amplitude Index Field for pulse oximeter :  0 is invalid, 1 is valid.
     */
    public static final String CPO_PA_INDEX_FLAG = "cpo_pa_index_flag";
    /**
     * The Device Clock is Not Set Field for pulse oximeter :  0 is invalid, 1 is valid.
     */
    public static final String CPO_CLOCK_SET_FLAG = "cpo_clock_set_flag";
    /**
     * The SpO2PR-Spot-Check - SpO2 for pulse oximeter :  Unit is percentage with a resolution of 1.
     */
    public static final String CPO_SPO2_DATA = "cpo_spo2_data";
    /**
     * The SpO2PR-Spot-Check - PR for pulse oximeter :  Unit is beats per minute with a resolution of 1 .
     */
    public static final String CPO_PR_DATA = "cpo_pr_data";
    /**
     * The Timestamp for pulse oximeter :  Unit is smallest unit in seconds with a resolution of 1 .
     */
    public static final String CPO_TIMESTAMP_DATA = "cpo_timestamp_data";
    /**
     * The Measurement Ongoing for Measurement Status:  0 is invalid, 1 is valid.
     */
    public static final String CPO_MEASUREMENT_ONGOING_DATA = "cpo_measurement_ongoing_data";
    /**
     * The Early Estimated Data for Measurement Status  0 is invalid, 1 is valid.
     */
    public static final String CPO_EARLY_ESTIMATED_DATA = "cpo_early_estimated_data";
    /**
     * The Validated Data for Measurement Status  0 is invalid, 1 is valid.
     */
    public static final String CPO_VALIDATED_DATA = "cpo_validated_data";
    /**
     * The Fully Qualified Data for Measurement Status  0 is invalid, 1 is valid.
     */
    public static final String CPO_FULLY_QUALIFIED_DATA = "cpo_fully_qualified_data";
    /**
     * The Measurement Storage Data for Measurement Status  0 is invalid, 1 is valid.
     */
    public static final String CPO_MEASUREMENT_STORAGE_DATA = "cpo_measurement_storage_data";
    /**
     * The Demonstration Data for Measurement Status  0 is invalid, 1 is valid.
     */
    public static final String CPO_DEMONSTRATION_DATA = "cpo_demonstration_data";
    /**
     * The Testing Data for Measurement Status  0 is invalid, 1 is valid.
     */
    public static final String CPO_TESTING_DATA = "cpo_testing_data";
    /**
     * The Calibration Ongoing Data for Measurement Status  0 is invalid, 1 is valid.
     */
    public static final String CPO_CALIBRATION_ONGOING_DATA = "cpo_calibration_ongoing_data";
    /**
     * The Measurement Unavailable Data for Measurement Status  0 is invalid, 1 is valid.
     */
    public static final String CPO_MEASUREMENT_UNAVAILABLE_DATA = "cpo_measurement_unavailable_data";
    /**
     * The Questionable Measurement Detected Data for Measurement Status  0 is invalid, 1 is valid.
     */
    public static final String CPO_QUESTIONABLE_DETECTED_DATA = "cpo_questionable_detected_data";
    /**
     * The Invalid Measurement Detected Data for Measurement Status  0 is invalid, 1 is valid.
     */
    public static final String CPO_INVALID_DETECTED_DATA = "cpo_invalid_detected_data";
    /**
     * The Extended Display Update Ongoing Data for Device and Sensor Status  0 is invalid, 1 is valid.
     */
    public static final String CPO_EXTENDED_DISPLAY_UPDATE_ONGOING_DATA = "cpo_extended_display_update_ongoing_data";
    /**
     * The Equipment Malfunction Detected Data for Device and Sensor Status  0 is invalid, 1 is valid.
     */
    public static final String CPO_EQUIPMENT_MALFUNCTION_DATA = "cpo_equipment_malfunction_data";
    /**
     * The Signal Processing Irregularity Detected Data for Device and Sensor Status  0 is invalid, 1 is valid.
     */
    public static final String CPO_SIGNAL_PROCESSING_IRREGULARITY_DATA = "cpo_signal_processing_irregularity_data";
    /**
     * The Inadequate Signal Detected Data for Device and Sensor Status  0 is invalid, 1 is valid.
     */
    public static final String CPO_INADEQUATE_SIGNAL_DETECTED_DATA = "cpo_inadequate_signal_detected_data";
    /**
     * The Poor Signal Detected Data for Device and Sensor Status  0 is invalid, 1 is valid.
     */
    public static final String CPO_POOR_SIGNAL_DETECTED_DATA = "cpo_poor_signal_detected_data";
    /**
     * The Low Perfusion Detected Data for Device and Sensor Status  0 is invalid, 1 is valid.
     */
    public static final String CPO_LOW_PERFUSION_DETECTED_DATA = "cpo_low_perfusion_detected_data";
    /**
     * The Erratic Signal Detected Data for Device and Sensor Status  0 is invalid, 1 is valid.
     */
    public static final String CPO_ERRATIC_SIGNAL_DETECTED_DATA = "cpo_erratic_signal_detected_data";
    /**
     * The Non Pulsatile Signal Detected Data for Device and Sensor Status  0 is invalid, 1 is valid.
     */
    public static final String CPO_NONPULSATILE_SIGNAL_DETECTED_DATA = "cpo_nonpulsatile_signal_detected_data";
    /**
     * The Questionable Pulse Detected Data for Device and Sensor Status  0 is invalid, 1 is valid.
     */
    public static final String CPO_QUESTIONABLE_PULSE_DETECTED_DATA = "cpo_questionable_pulse_detected_data";
    /**
     * The Signal Analysis Ongoing Data for Device and Sensor Status  0 is invalid, 1 is valid.
     */
    public static final String CPO_SIGNAL_ANALYSIS_ONGOING_DATA = "cpo_signal_analysis_ongoing_data";
    /**
     * The Sensor Interface Detected Data for Device and Sensor Status  0 is invalid, 1 is valid.
     */
    public static final String CPO_SENSOR_INTERFACE_DETECTED_DATA = "cpo_sensor_interface_detected_data";
    /**
     * The Sensor Unconnected to User Data for Device and Sensor Status  0 is invalid, 1 is valid.
     */
    public static final String CPO_SENSOR_UNCONNECTED_TO_USER_DATA = "cpo_sensor_unconnected_to_user_data";
    /**
     * The Unknown Sensor Connected Data for Device and Sensor Status  0 is invalid, 1 is valid.
     */
    public static final String CPO_UNKNOWN_SENSOR_CONNECTED_DATA = "cpo_unknown_sensor_connected_data";
    /**
     * The Sensor Displaced Data for Device and Sensor Status  0 is invalid, 1 is valid.
     */
    public static final String CPO_SENSOR_DISPLACED_DATA = "cpo_sensor_displaced_data";
    /**
     * The Sensor Malfunctioning Data for Device and Sensor Status  0 is invalid, 1 is valid.
     */
    public static final String CPO_SENSOR_MALFUNCTION_DATA = "cpo_sensor_malfunctioning_data";
    /**
     * The Sensor Disconnected Data for Device and Sensor Status  0 is invalid, 1 is valid.
     */
    public static final String CPO_SENSOR_DISCONNECTED_DATA = "cpo_sensor_disconnected_data";
    /**
     * The Pulse Amplitude Index Data for Device and Sensor Status  Unit is percentage with a resolution of 1 .
     */
    public static final String CPO_PULSE_AMPLITUDE_INDEX_DATA = "cpo_pulse_amplitude_index_data";

    /**
     * Callback indicating get PLX Continuous Measurement for Po device.<br/>
     * <b>See Also:</b><br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>
     * {@link #CONTINUOUS_CPO}
     * </li>
     * <li>
     * {@link #CPO_SPO2_PR_FAST_FLAG}
     * </li>
     * <li>
     * {@link #CPO_SPO2_PR_SLOW_FLAG}
     * </li>
     * <li>
     * {@link #CPO_MEASUREMENT_STATUS_FLAG}
     * </li>
     * <li>
     * {@link #CPO_DS_STATUS_FLAG}
     * </li>
     * <li>
     * {@link #CPO_PA_INDEX_FLAG}
     * </li>
     * <li>
     * {@link #CPO_SPO2_DATA}
     * </li>
     * <li>
     * {@link #CPO_PR_DATA}
     * </li>
     * <li>
     * {@link #CPO_SPO2_FAST_DATA}
     * </li>
     * <li>
     * {@link #CPO_PR_FAST_DATA}
     * </li>
     * <li>
     * {@link #CPO_SPO2_SLOW_DATA}
     * </li>
     * <li>
     * {@link #CPO_PR_SLOW_DATA}
     * </li>
     * <li>
     * {@link #CPO_MEASUREMENT_ONGOING_DATA}
     * </li>
     * <li>
     * {@link #CPO_EARLY_ESTIMATED_DATA}
     * </li>
     * <li>
     * {@link #CPO_VALIDATED_DATA}
     * </li>
     * <li>
     * {@link #CPO_FULLY_QUALIFIED_DATA}
     * </li>
     * <li>
     * {@link #CPO_MEASUREMENT_STORAGE_DATA}
     * </li>
     * <li>
     * {@link #CPO_DEMONSTRATION_DATA}
     * </li>
     * <li>
     * {@link #CPO_TESTING_DATA}
     * </li>
     * <li>
     * {@link #CPO_CALIBRATION_ONGOING_DATA}
     * </li>
     * <li>
     * {@link #CPO_MEASUREMENT_UNAVAILABLE_DATA}
     * </li>
     * <li>
     * {@link #CPO_QUESTIONABLE_DETECTED_DATA}
     * </li>
     * <li>
     * {@link #CPO_INVALID_DETECTED_DATA}
     * </li>
     * <li>
     * {@link #CPO_EXTENDED_DISPLAY_UPDATE_ONGOING_DATA}
     * </li>
     * <li>
     * {@link #CPO_EQUIPMENT_MALFUNCTION_DATA}
     * </li>
     * <li>
     * {@link #CPO_SIGNAL_PROCESSING_IRREGULARITY_DATA}
     * </li>
     * <li>
     * {@link #CPO_INADEQUATE_SIGNAL_DETECTED_DATA}
     * </li>
     * <li>
     * {@link #CPO_POOR_SIGNAL_DETECTED_DATA}
     * </li>
     * <li>
     * {@link #CPO_LOW_PERFUSION_DETECTED_DATA}
     * </li>
     * <li>
     * {@link #CPO_ERRATIC_SIGNAL_DETECTED_DATA}
     * </li>
     * <li>
     * {@link #CPO_NONPULSATILE_SIGNAL_DETECTED_DATA}
     * </li>
     * <li>
     * {@link #CPO_QUESTIONABLE_PULSE_DETECTED_DATA}
     * </li>
     * <li>
     * {@link #CPO_SIGNAL_ANALYSIS_ONGOING_DATA}
     * </li>
     * <li>
     * {@link #CPO_SENSOR_INTERFACE_DETECTED_DATA}
     * </li>
     * <li>
     * {@link #CPO_SENSOR_UNCONNECTED_TO_USER_DATA}
     * </li>
     * <li>
     * {@link #CPO_UNKNOWN_SENSOR_CONNECTED_DATA}
     * </li>
     * <li>
     * {@link #CPO_SENSOR_DISPLACED_DATA}
     * </li>
     * <li>
     * {@link #CPO_SENSOR_MALFUNCTION_DATA}
     * </li>
     * <li>
     * {@link #CPO_SENSOR_DISCONNECTED_DATA}
     * </li>
     * <li>
     * {@link #CPO_PULSE_AMPLITUDE_INDEX_DATA}
     * </li>
     * </ul>
     */
    public static final String ACTION_CONTINUOUS_CPO = "continuous_cpo";
    /**
     * The SpO2PR–Fast flag for pulse oximeter :  0 is invalid, 1 is valid.
     */
    public static final String CPO_SPO2_PR_FAST_FLAG = "cpo_spo2_pr_fast_flag";
    /**
     * The SpO2PR-Slow flag for pulse oximeter :  0 is invalid, 1 is valid.
     */
    public static final String CPO_SPO2_PR_SLOW_FLAG = "cpo_spo2_pr_slow_flag";
    /**
     * The SpO2PR-Fast - SpO2 Data for pulse oximeter :  0 is invalid, 1 is valid.
     */
    public static final String CPO_SPO2_FAST_DATA = "cpo_spo2_fast_data";
    /**
     * The SpO2PR-Fast - PR Data for pulse oximeter :  0 is invalid, 1 is valid.
     */
    public static final String CPO_PR_FAST_DATA = "cpo_pr_fast_data";
    /**
     * The SpO2PR-Slow - SpO2 Data for pulse oximeter :  0 is invalid, 1 is valid.
     */
    public static final String CPO_SPO2_SLOW_DATA = "cpo_spo2_slow_data";
    /**
     * The SpO2PR-Slow - PR Data for pulse oximeter :  0 is invalid, 1 is valid.
     */
    public static final String CPO_PR_SLOW_DATA = "cpo_pr_slow_data";

}
