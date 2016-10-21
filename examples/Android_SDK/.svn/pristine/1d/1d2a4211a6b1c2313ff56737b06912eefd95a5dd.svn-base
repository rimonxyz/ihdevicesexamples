package com.ihealth.communication.control;

/**
 * Created by jing on 16/9/5.
 */

import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;

/**
 * Public APIs for the Bg serials device profiles
 * <p> Clients need to call{@link iHealthDevicesManager#registerClientCallback} register.
 * When new data from Bg5 device, The Callback{@link iHealthDevicesCallback#onDeviceNotify} can be triggered should call.
 */
public class BgProfile {

    /***************************************************************************
     *********  {@link #ACTION_MEASUREMENT_RESULT_CBG}   ***********************
     **************************************************************************/
    /**
     * Callback indicating the result of glucose concentration.
     * The keyList of measurement result.
     * <ul>
     * KeyList:
     * <ul>
     * <li>{@link #CBGINFO_TIME_OFFSET_FLAG}
     * <li>{@link #CBGINFO_TYPE_LOCATION_FLAG}
     * <li>{@link #CBGINFO_UNIT}
     * <li>{@link #CBGINFO_SENSOR_STATUS_FLAG}
     * <li>{@link #CBGINFO_CONTEXT_INFORMATION_FLAG}
     * <li>{@link #SEQUENCE_NUMBER_CBG}
     * <li>{@link #MEASURE_TIME_CBG}
     * <li>{@link #TIME_OFFSET_CBG}
     * <li>{@link #RESULT_CBG}
     * <li>{@link #TYPE_MEASUREMENT_CBG}
     * <li>{@link #LOCATION_MEASUREMENT_CBG}
     * <li>{@link #CBGINFO_LOW_BATTERY}
     * <li>{@link #CBGINFO_SENSOR_MAL_FUNCTION}
     * <li>{@link #CBGINFO_INSUFFICIENT_SAMPLE}
     * <li>{@link #CBGINFO_STRIP_INSERTION_ERROR}
     * <li>{@link #CBGINFO_TYPE_INCORRECT}
     * <li>{@link #CBGINFO_RESULT_HIGHER}
     * <li>{@link #CBGINFO_RESULT_LOWER}
     * <li>{@link #CBGINFO_TEMPERATURE_TOO_HIGH}
     * <li>{@link #CBGINFO_TEMPERATURE_TOO_LOW}
     * <li>{@link #CBGINFO_STRIP_PULL_TOO_EARLY}
     * <li>{@link #CBGINFO_SENSOR_FAULT}
     * <li>{@link #CBGINFO_TIME_FAULT}
     * </ul>
     * eg.
     * </ul>
     */
    public static final String ACTION_MEASUREMENT_RESULT_CBG = "action_measurement_result";


    /**
     * The timestamp flag for glucose measurement :  0 is false, 1 is true.
     */
    public static final String CBGINFO_TIME_OFFSET_FLAG = "bg_time_offset_flag";


    /**
     * The type and location flag for glucose measurement :  0 is false, 1 is true.
     */
    public static final String CBGINFO_TYPE_LOCATION_FLAG = "bg_timestamp_flag";

    /**
     * The unit for glucose concentration :  0 is kg/L, 1 is mol/L.
     */
    public static final String CBGINFO_UNIT = "bg_unit";

    /**
     * The sensor status for glucose measurement :  0 is false, 1 is true.
     */
    public static final String CBGINFO_SENSOR_STATUS_FLAG = "bg_sensor_status_flag";

    /**
     * The context information flag for glucose measurement :  0 is false, 1 is true.
     */
    public static final String CBGINFO_CONTEXT_INFORMATION_FLAG = "bg_context_information_flag";


    /**
     * The sequence number  for glucose measurement.
     * <b>Value range:</b><br/>
     * 0-65535(0xFFFF)
     */
    public static final String SEQUENCE_NUMBER_CBG = "sequence_number";

    /**
     * The measure time  for glucose measurement.
     * Format:yyyy-MM-dd HH:mm:ss
     */
    public static final String MEASURE_TIME_CBG = "measure_time";

    /**
     * The time offset  for glucose measurement.
     * <b>Value range:</b><br/>
     * -32768-32767<br/>
     * unit:minute
     */
    public static final String TIME_OFFSET_CBG = "time_offset";


    /**
     * The result  for glucose measurement.
     * Unit:{@link #CBGINFO_UNIT}
     */
    public static final String RESULT_CBG = "result";

    /**
     * The type  for glucose measurement.
     * Enum:
     * <p>0	Reserved for future use</p>
     * <p>1	Capillary Whole blood</p>
     * <p>2	Capillary Plasma</p>
     * <p>3	Venous Whole blood</p>
     * <p>4	Venous Plasma</p>
     * <p>5	Arterial Whole blood</p>
     * <p>6	Arterial Plasma</p>
     * <p>7	Undetermined Whole blood</p>
     * <p>8	Undetermined Plasma</p>
     * <p>9	Interstitial Fluid (ISF)</p>
     * <p>10 Control Solution</p>
     */
    public static final String TYPE_MEASUREMENT_CBG = "type_measurement";

    /**
     * The location  for glucose measurement.
     * Enum:
     * <p>0	Reserved for future use</p>
     * <p>1	Finger</p>
     * <p>2	Alternate Site Test (AST)</p>
     * <p>3	Earlobe</p>
     * <p>4	Control solutiona</p>
     * <p>15 Sample Location value not available</p>
     * <p>5-14 	Reserved for future use</p>
     */
    public static final String LOCATION_MEASUREMENT_CBG = "location_measurement";


    /**
     * Device battery low at time of measurement :  0 is false, 1 is true.
     */
    public static final String CBGINFO_LOW_BATTERY = "low_battery";


    /**
     * Sensor malfunction or faulting at time of measurement :  0 is false, 1 is true.
     */
    public static final String CBGINFO_SENSOR_MAL_FUNCTION = "sensor_mal_function";

    /**
     * Sample size for blood or control solution insufficient at time of measurement :  0 is false, 1 is true.
     */
    public static final String CBGINFO_INSUFFICIENT_SAMPLE = "insufficient_sample";


    /**
     * Strip insertion error :  0 is false, 1 is true.
     */
    public static final String CBGINFO_STRIP_INSERTION_ERROR = "strip_insertion_error";


    /**
     * Strip type incorrect for device :  0 is false, 1 is true.
     */
    public static final String CBGINFO_TYPE_INCORRECT = "type_incorrect";

    /**
     * Sensor result higher than the device can process :  0 is false, 1 is true.
     */
    public static final String CBGINFO_RESULT_HIGHER = "result_higher";


    /**
     * Sensor result lower than the device can process :  0 is false, 1 is true.
     */
    public static final String CBGINFO_RESULT_LOWER = "result_lower";


    /**
     * Sensor temperature too high for true test/result at time of measurement:  0 is false, 1 is true.
     */
    public static final String CBGINFO_TEMPERATURE_TOO_HIGH = "temperature_too_high";


    /**
     * Sensor temperature too low for true test/result at time of measurement:  0 is false, 1 is true.
     */
    public static final String CBGINFO_TEMPERATURE_TOO_LOW = "temperature_too_low";


    /**
     * Sensor read interrupted because strip was pulled too soon at time of measurement:  0 is false, 1 is true.
     */
    public static final String CBGINFO_STRIP_PULL_TOO_EARLY = "strip_pull_too_early";


    /**
     * General device fault has occurred in the sensor:  0 is false, 1 is true.
     */
    public static final String CBGINFO_SENSOR_FAULT = "sensor_fault";

    /**
     * Time fault has occurred in the sensor and time may be inaccurate:  0 is false, 1 is true.
     */
    public static final String CBGINFO_TIME_FAULT = "time_fault";






    /***************************************************************************
     *********             {@link #ACTION_BATTERY_CBG}   ***********************
     **************************************************************************/
    /**
     * Callback indicating the battery of continua bg device.
     * <ul>
     * KeyList:
     * <ul>
     * <li>{@link #BATTERY_CBG}
     * </ul>
     * eg.
     * </ul>
     */
    public static final String ACTION_BATTERY_CBG = "action_battery";

    /**
     * Battery for Continua Bg device.
     */
    public static final String BATTERY_CBG = "battery";



    /***************************************************************************
     *********         {@link #ACTION_FEATURE_CBG}       ***********************
     **************************************************************************/
    /**
     * Callback indicating the feature of glucose concentration.
     * <ul>
     * KeyList:
     * <ul>
     * <li>{@link #FEATURE_LOW_BATTERY_CBG}
     * <li>{@link #FEATURE_SENSOR_MALFUNCTION_DETECTION_CBG}
     * <li>{@link #FEATURE_SAMPLE_SIZE_CBG}
     * <li>{@link #FEATURE_STRIP_INSERTION_ERROR_CBG}
     * <li>{@link #FEATURE_STRIP_TYPE_ERROR_CBG}
     * <li>{@link #FEATURE_RESULT_HIGH_LOW_DETECTION_CBG}
     * <li>{@link #FEATURE_TEMPERATURE_HIGH_LOW_DETECTION_CBG}
     * <li>{@link #FEATURE_READ_INTERRUPT_CBG}
     * <li>{@link #FEATURE_GENERAL_FAULT_SUPPORT_CBG}
     * <li>{@link #FEATURE_TIME_FAULT_SUPPORT_CBG}
     * <li>{@link #FEATURE_MULTIPLE_BOND_SUPPORTED_CBG}
     * </ul>
     * eg.
     * </ul
     */
    public static final String ACTION_FEATURE_CBG = "action_feature";

    /**
     * Low Battery Detection During Measurement Supported;  0:false   1:true
     */
    public static final String FEATURE_LOW_BATTERY_CBG = "low_battery";


    /**
     * Sensor Malfunction Detection Supported;  0:false   1:true
     */
    public static final String FEATURE_SENSOR_MALFUNCTION_DETECTION_CBG = "sensor_malfunction_detection";


    /**
     * Sensor Sample Size Supported;  0:false   1:true
     */
    public static final String FEATURE_SAMPLE_SIZE_CBG = "sample_size";


    /**
     * Sensor Strip Insertion Error Detection Supported;  0:false   1:true
     */
    public static final String FEATURE_STRIP_INSERTION_ERROR_CBG = "strip_insertion_error";


    /**
     * Sensor Strip Type Error Detection Supported;  0:false   1:true
     */
    public static final String FEATURE_STRIP_TYPE_ERROR_CBG = "strip_type_error";


    /**
     * Sensor Result High-Low Detection Supported;  0:false   1:true
     */
    public static final String FEATURE_RESULT_HIGH_LOW_DETECTION_CBG = "result_high_low_detection";


    /**
     * Sensor Temperature High-Low Detection Supported;  0:false   1:true
     */
    public static final String FEATURE_TEMPERATURE_HIGH_LOW_DETECTION_CBG = "temperature_high_low_detection";


    /**
     * Sensor Read Interrupt Detection Supported;  0:false   1:true
     */
    public static final String FEATURE_READ_INTERRUPT_CBG = "read_interrupt";


    /**
     * General Device Fault Supported;  0:false   1:true
     */
    public static final String FEATURE_GENERAL_FAULT_SUPPORT_CBG = "general_fault_support";


    /**
     * Time Fault Supported;  0:false   1:true
     */
    public static final String FEATURE_TIME_FAULT_SUPPORT_CBG = "time_fault_support";


    /**
     * Multiple Bond Supported;  0:false   1:true
     */
    public static final String FEATURE_MULTIPLE_BOND_SUPPORTED_CBG = "multiple_bond_support";



    /***************************************************************************
     *********         {@link #ACTION_MEASUREMENT_CONTEXT_CBG}   ***************
     **************************************************************************/
    /**
     * Callback indicating the feature of glucose concentration.
     * <ul>
     * KeyList:
     * <ul>
     * <li>{@link #CONTEXT_CARBOGYDRATE_PRESNET_CBG}
     * <li>{@link #CONTEXT_MEAL_PRESNET_CBG}
     * <li>{@link #CONTEXT_TESTER_HEALTH_PRESNET_CBG}
     * <li>{@link #CONTEXT_EXERCISE_PRESNET_CBG}
     * <li>{@link #CONTEXT_MEDICATION_PRESNET_CBG}
     * <li>{@link #CONTEXT_MEDICATION_UNIT_CBG}
     * <li>{@link #CONTEXT_HBA1C_PRESNET_CBG}
     * <li>{@link #CONTEXT_EXTENDED_FLAG_PRESNET_CBG}
     * <li>{@link #CONTEXT_SEQUENCE_NUMBER_CBG}
     * <li>{@link #CONTEXT_EXTENDED_FLAG_CBG}
     * <li>{@link #CONTEXT_CARBOHYDRATE_ID_CBG}
     * <li>{@link #CONTEXT_CARBOHYDRATE_CBG}
     * <li>{@link #CONTEXT_MEAL_CBG}
     * <li>{@link #CONTEXT_TESTER_CBG}
     * <li>{@link #CONTEXT_HEALTH_CBG}
     * <li>{@link #CONTEXT_EXERCISE_DURATION_CBG}
     * <li>{@link #CONTEXT_EXERCISE_INTENSITY_CBG}
     * <li>{@link #CONTEXT_MEDICATION_ID_CBG}
     * <li>{@link #CONTEXT_MEDICATION_CBG}
     * <li>{@link #CONTEXT_HBA1C_CBG}
     * </ul>
     * eg.
     * </ul
     */
    public static final String ACTION_MEASUREMENT_CONTEXT_CBG = "action_measurement_context";

    /**
     * Carbohydrate ID And Carbohydrate Present;  0:false   1:true
     */
    public static final String CONTEXT_CARBOGYDRATE_PRESNET_CBG = "carbohydrate_present";

    /**
     * Meal Present;  0:false   1:true
     */
    public static final String CONTEXT_MEAL_PRESNET_CBG = "meal_present";

    /**
     * Tester-Health Present;  0:false   1:true
     */
    public static final String CONTEXT_TESTER_HEALTH_PRESNET_CBG = "tester_health_present";

    /**
     * Exercise Duration And Exercise Intensity Present;  0:false   1:true
     */
    public static final String CONTEXT_EXERCISE_PRESNET_CBG = "exercise_present";

    /**
     * Medication ID And Medication Present;  0:false   1:true
     */
    public static final String CONTEXT_MEDICATION_PRESNET_CBG = "medication_present";

    /**
     * Medication Value Units;  0:kilograms   1:liters
     */
    public static final String CONTEXT_MEDICATION_UNIT_CBG = "medication_unit";

    /**
     * HbA1c Present;  0:false   1:true
     */
    public static final String CONTEXT_HBA1C_PRESNET_CBG = "hba1c_present";

    /**
     * Extended Flags Present;  0:false   1:true
     */
    public static final String CONTEXT_EXTENDED_FLAG_PRESNET_CBG = "extended_flags_present";


    /**
     * Sequence Number
     * <b>Value range:</b>
     * 0-65535(0xFFFF)
     */
    public static final String CONTEXT_SEQUENCE_NUMBER_CBG = "sequence_number";

    /**
     * Extended Flags
     * <b>Value range:</b>
     * 0-255(0xFF)
     */
    public static final String CONTEXT_EXTENDED_FLAG_CBG = "extended_flag";

    /**
     * Carbohydrate ID
     * Enum:
     * <p>0	Reserved for future use</p>
     * <p>1	Breakfast</p>
     * <p>2	Lunch</p>
     * <p>3	Dinner</p>
     * <p>4	Snack</p>
     * <p>5	Drink</p>
     * <p>6	Supper</p>
     * <p>7	Brunch</p>
     * <p>8-255	Reserved for future use</p>
     */
    public static final String CONTEXT_CARBOHYDRATE_ID_CBG = "carbohydrate_id";


    /**
     * Carbohydrate - units of kilograms
     */
    public static final String CONTEXT_CARBOHYDRATE_CBG = "carbohydrate";


    /**
     * Meal
     * Enum:
     * <p>0	Reserved for future use</p>
     * <p>1	Preprandial (before meal)</p>
     * <p>2	Postprandial (after meal)</p>
     * <p>3	Fasting</p>
     * <p>4	Casual (snacks, drinks, etc.)</p>
     * <p>5	Bedtime</p>
     * <p>6-255	Reserved for future use</p>
     */
    public static final String CONTEXT_MEAL_CBG = "meal";

    /**
     * Tester
     * Enum:
     * <p>0	Reserved for future use</p>
     * <p>1	Self</p>
     * <p>2	Health Care Professional</p>
     * <p>3	Lab test</p>
     * <p>15 Tester value not available</p>
     * <p>4-14	Reserved for future use</p>
     */
    public static final String CONTEXT_TESTER_CBG = "tester";


    /**
     * Health
     * Enum:
     * <p>0	Reserved for future use</p>
     * <p>1	Minor health issues</p>
     * <p>2	Major health issues</p>
     * <p>3	During menses</p>
     * <p>4 Under stress</p>
     * <p>5 No health issues</p>
     * <p>15 Health value not available</p>
     * <p>6-14	Reserved for future use</p>
     */
    public static final String CONTEXT_HEALTH_CBG = "health";


    /**
     * Exercise Duration in seconds
     * <b>Value range:</b>
     * 0-65534(0xFFFE)
     */
    public static final String CONTEXT_EXERCISE_DURATION_CBG = "exercise_duration";


    /**
     * Exercise Intensity
     * percentage
     */
    public static final String CONTEXT_EXERCISE_INTENSITY_CBG = "exercise_intensity";


    /**
     * Medication ID
     * Enum:
     * <p>0	Reserved for future use</p>
     * <p>1	Rapid acting insulin</p>
     * <p>2	Short acting insulin</p>
     * <p>3	Intermediate acting insulin</p>
     * <p>4 Long acting insulin</p>
     * <p>5 Pre-mixed insulin</p>
     * <p>6-255	Reserved for future use</p>
     */
    public static final String CONTEXT_MEDICATION_ID_CBG = "medication_id";

    /**
     * Medication
     */
    public static final String CONTEXT_MEDICATION_CBG = "medication";


    /**
     * HbA1c
     */
    public static final String CONTEXT_HBA1C_CBG = "hba1c";






}
