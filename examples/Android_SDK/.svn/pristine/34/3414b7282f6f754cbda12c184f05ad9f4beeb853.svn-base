package com.ihealth.communication.control;

import com.ihealth.communication.manager.iHealthDevicesManager;

/**
 * Public APIs for the Bp serials device profiles
 * <p> Clients need to call{@link iHealthDevicesManager#registerClientCallback} register.
 * When new data from Bp device, The Callback{#iHealthDevicesCallback #onDeviceNotify} can be triggered should call.
 *
 */
public class AbiProfile {

	public static final String ABI_ARM = "abi_arm";
	public static final String ABI_LEG = "abi_leg";
	public static final String ABI_UNKNOWN = "abi_unknown";




	/***************************************************************************
	 *********         {@link #ACTION_ERROR_ABI}                 ***************
	 **************************************************************************/
	/**
	 * Callback indicating the error of Bp device.
	 * <ul>
	 * KeyList:
	 * <ul>
	 * <li>{@link #ERROR_NUM_ABI}
	 * </ul>
	 */
	public static final String ACTION_ERROR_ABI = "error_bp";
	
	/**
	 * Flag Error number of Bp device.
	 *
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
	public static final String ERROR_NUM_ABI = "error";





	/***************************************************************************
	 *********      {@link #ACTION_ZEROING_ABI}            ***************
	 **************************************************************************/
	/**
	 * Callback indicating the Bp device is initializing .
	 */
	public static final String ACTION_ZEROING_ABI = "zeroing_bp";




	/***************************************************************************
	 *********         {@link #ACTION_ZERO_OVER_ABI}             ***************
	 **************************************************************************/
	/**
	 * Callback indicating the Initialization of the Bp device has been completed.
	 */
	public static final String ACTION_ZERO_OVER_ABI = "zero_over_bp";





	/***************************************************************************
	 *********      {@link #ACTION_ONLINE_PRESSURE_ABI}          ***************
	 **************************************************************************/
	/**
	 * Callback indicating the blood pressure in the measurement.
	 * <ul>
	 * KeyList:
	 * <ul>
	 * <li>{@link #BLOOD_PRESSURE_ABI}
	 * </ul>
	 * eg.   {"pressure":5}
	 * </ul>
	 */
	public static final String ACTION_ONLINE_PRESSURE_ABI = "online_pressure_bp";
	/**
	 * The blood pressure
	 * <b>Value range:</b><br/>
	 * 0-300
	 */
	public static final String BLOOD_PRESSURE_ABI = "pressure";






	/***************************************************************************
	 *********      {@link #ACTION_ONLINE_PULSE_WAVE_ABI}        ***************
	 **************************************************************************/
	/**
	 * Callback indicating the blood pressure and pulse wave in the measurement.
	 * <ul>
	 * KeyList:
	 * <ul>
	 * <li>{@link #BLOOD_PRESSURE_ABI}
	 * <li>{@link #PULSE_WAVE_ABI}
	 * <li>{@link #FLAG_HEARTBEAT_ABI}
	 * </ul>
	 * eg.  {"pressure":25,"heartbeat":false,"pulse_wave":"[15,15,15,15,15,15,15,15]"}
	 * </ul>
	 */
	public static final String ACTION_ONLINE_PULSE_WAVE_ABI = "online_pulse_wave_bp";

	/**
	 * Blood pressure pulse wave.
	 * eg.
	 */
	public static final String PULSE_WAVE_ABI = "pulse_wave";
	/**
	 * Heartbeat graphical control flag.        true: heartbeat;    false: no heartbeat
	 */
	public static final String FLAG_HEARTBEAT_ABI = "heartbeat";






	/***************************************************************************
	 *********      {@link #ACTION_ONLINE_RESULT_ABI}            ***************
	 **************************************************************************/
	/**
	 * Callback indicating the result that the On-line has been completed.
	 * The keyList of measurement result.
	 * <ul>
	 * KeyList:
	 * <ul>
	 * <li>{@link #HIGH_BLOOD_PRESSURE_ABI}
	 * <li>{@link #LOW_BLOOD_PRESSURE_ABI}
	 * <li>{@link #PULSE_ABI}
	 * <li>{@link #MEASUREMENT_AHR_ABI}
	 * <li>{@link #MEASUREMENT_HSD_ABI}
	 * <li>{@link #DATAID}
	 * </ul>
	 * eg. {"high_pressure":109,"low_pressure":70,"pulse":66,"ahr":0,"hsd":0,"dataid":"25F5C4A479A2288BF204E32321DFB940"}
	 * </ul>
	 */
	public static final String ACTION_ONLINE_RESULT_ABI = "online_result_bp";

	/**
	 * The high pressure for blood pressure.
	 * <b>Value range:</b><br/>
	 * 0-300
	 */
	public static final String HIGH_BLOOD_PRESSURE_ABI = "high_pressure";

	/**
	 * The low pressure for blood pressure.
	 * <b>Value range:</b><br/>
	 * 0-300
	 */
	public static final String LOW_BLOOD_PRESSURE_ABI = "low_pressure";

	/**
	 * Heart rate.
	 * <b>Value range:</b><br/>
	 * 0-255
	 */
	public static final String PULSE_ABI = "pulse";

	/**
	 * Arrhythmia   0: normal heart rate   1: arrhythmia
	 */
	public static final String MEASUREMENT_AHR_ABI = "ahr";

	/**
	 * Hemodynamic stability diagnosis, that Determines whether the circulatory system is sufficiently at rest or not.
	 * true: detected HSD   false: no detected HSD
	 */
	public static final String MEASUREMENT_HSD_ABI = "hsd";
	/**
	 * return blood pressure data id.
	 */
	public static final String DATAID = "dataid";







	/***************************************************************************
	 *********      {@link #ACTION_BATTERY_ABI}                  ***************
	 **************************************************************************/
	/**
	 * Callback indicating power of Battery for Bp device.
	 ** The keyList of measurement result.
	 * <ul>
	 * KeyList:
	 * <ul>
	 * <li>{@link #BATTERY_ABI}
	 * </ul>
	 * eg. {"battery":100}
	 * </ul>
	 */

	public static final String ACTION_BATTERY_ABI = "battery_abi";
	
	/**
	 * The power of Battery for Bp device.
	 * <b>Value range:</b><br/>
	 * 0-100
	 */
	public static final String BATTERY_ABI = "battery";







	/***************************************************************************
	 *********             {@link #ACTION_STOP_ABI}             ***************
	 **************************************************************************/
	/**
	 * The action type of callback indicating stop measure from App.
	 */
	public static final String ACTION_STOP_ABI = "stop_app";


	/***************************************************************************
	 **********           {@link #ACTION_INTERRUPTED_ABI}        **************
	 **************************************************************************/
	/**
	 * The action type of callback indicating stop measure from meter.
	 */
	public static final String ACTION_INTERRUPTED_ABI = "stop_device";


}
