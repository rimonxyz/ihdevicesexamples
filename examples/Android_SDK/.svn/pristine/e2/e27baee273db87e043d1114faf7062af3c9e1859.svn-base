
package com.ihealth.communication.control;

import com.ihealth.communication.manager.iHealthDevicesManager;

/**
 * Public APIs for the Bs serials device profiles
 * <p>
 * Clients need to call {@link iHealthDevicesManager#registerClientCallback} register. When new data from Hs
 * device, The Callback {#iHealthDevicesCallback #onDeviceNotify} can be triggered should call.
 */
public interface BsProfile {
    /**
     * Callback indicating power of Battery for body composition device.
     */
    public static final String ACTION_BATTERY_CBS = "battery_cbs";
    /**
     * The power of Battery for Continua body composition device.
     */
    public static final String CBS_BATTERY = "cbs_battery";

    /**
     * <b>KeyList</b> of the message:
     * <ul>
     * <li>{@link #CBS_MEASUREMENT_DATA}</li>
     * <li>{@link #CBS_UNIT_FLAG}</li>
     * <li>{@link #CBS_TIMESTAMP_FLAG}</li>
     * <li>{@link #CBS_USER_ID_FLAG}</li>
     * <li>{@link #CBS_BASAL_METABOLISM_FLAG}</li>
     * <li>{@link #CBS_MUSCLE_PERCENTAGE_FLAG}</li>
     * <li>{@link #CBS_MUSCLE_MASS_FLAG}</li>
     * <li>{@link #CBS_FAT_FREE_MASS_FLAG}</li>
     * <li>{@link #CBS_SOFT_LEAN_MASS_FLAG}</li>
     * <li>{@link #CBS_BODY_WATER_MASS_FLAG}</li>
     * <li>{@link #CBS_IMPEDANCE_FLAG}</li>
     * <li>{@link #CBS_WEIGHT_FLAG}</li>
     * <li>{@link #CBS_HEIGHT_FLAG}</li>
     * <li>{@link #CBS_MULTIPLE_PACKET_MEASUREMENT_FLAG}</li>
     * <li>{@link #CBS_BODY_FAT_PERCENTAGE}</li>
     * <li>{@link #CBS_TIME_STAMP}</li>
     * <li>{@link #CBS_USER_ID}</li>
     * <li>{@link #CBS_BASAL_METABOLISM}</li>
     * <li>{@link #CBS_MUSCLE_PERCENTAGE}</li>
     * <li>{@link #CBS_MUSCLE_MASS_KILOGRAMS}</li>
     * <li>{@link #CBS_MUSCLE_MASS_POUNDS}</li>
     * <li>{@link #CBS_FAT_FREE_MASS_KILOGRAMS}</li>
     * <li>{@link #CBS_FAT_FREE_MASS_POUNDS}</li>
     * <li>{@link #CBS_SOFT_LEAN_MASS_KILOGRAMS}</li>
     * <li>{@link #CBS_SOFT_LEAN_MASS_POUNDS}</li>
     * <li>{@link #CBS_BODY_WATER_MASS_KILOGRAMS}</li>
     * <li>{@link #CBS_BODY_WATER_MASS_POUNDS}</li>
     * <li>{@link #CBS_IMPEDANCE}</li>
     * <li>{@link #CBS_WEIGHT_KILOGRAMS}</li>
     * <li>{@link #CBS_WEIGHT_POUNDS}</li>
     * <li>{@link #CBS_HEIGHT_KILOGRAMS}</li>
     * <li>{@link #CBS_HEIGHT_POUNDS}</li>
     * </ul>
     */
    public static final String ACTION_CBS_MEASUREMENT_DATA = "com.msg.cbs.measurement";
    /**
     * The unit for body composition : 0 is Weight and Mass in units of kilogram (kg) and Height in units of meter, 1 is Weight and Mass in units of pound (lb) and Height in units of inch (in).
     */
    public static final String CBS_UNIT_FLAG = "cbs_unit";
    /**
     * The timestamp flag for body composition :  0 is invalid, 1 is valid.
     */
    public static final String CBS_TIMESTAMP_FLAG = "cbs_timestamp_flag";
    /**
     * The user id flag for body composition :  0 is invalid, 1 is valid.
     */
    public static final String CBS_USER_ID_FLAG = "cbs_user_id_flag";
    /**
     * The Basal Metabolism present flag for body composition :  0 is invalid, 1 is valid.
     */
    public static final String CBS_BASAL_METABOLISM_FLAG = "cbs_basal_metabolism_flag";
    /**
     * The Muscle Percentage present flag for body composition :  0 is invalid, 1 is valid.
     */
    public static final String CBS_MUSCLE_PERCENTAGE_FLAG = "cbs_muscle_percentage_flag";
    /**
     * The Muscle Mass present flag for body composition :  0 is invalid, 1 is valid.
     */
    public static final String CBS_MUSCLE_MASS_FLAG = "cbs_muscle_mass_flag";
    /**
     * The Fat Free Mass present flag for body composition :  0 is invalid, 1 is valid.
     */
    public static final String CBS_FAT_FREE_MASS_FLAG = "cbs_fat_free_mass_flag";
    /**
     * The Soft Lean Mass present flag for body composition :  0 is invalid, 1 is valid.
     */
    public static final String CBS_SOFT_LEAN_MASS_FLAG = "cbs_soft_lean_mass_flag";
    /**
     * The Body Water Mass present flag for body composition :  0 is invalid, 1 is valid.
     */
    public static final String CBS_BODY_WATER_MASS_FLAG = "cbs_body_water_mass_flag";
    /**
     * The Impedance present flag for body composition :  0 is invalid, 1 is valid.
     */
    public static final String CBS_IMPEDANCE_FLAG = "cbs_impedance_flag";
    /**
     * The Weight present flag for body composition :  0 is invalid, 1 is valid.
     */
    public static final String CBS_WEIGHT_FLAG = "cbs_weight_flag";
    /**
     * The Height present flag for body composition :  0 is invalid, 1 is valid.
     */
    public static final String CBS_HEIGHT_FLAG = "cbs_height_flag";
    /**
     * The Multiple Packet Measurement flag for body composition :  0 is invalid, 1 is valid.
     */
    public static final String CBS_MULTIPLE_PACKET_MEASUREMENT_FLAG = "cbs_multiple_packet_measurement_flag";
    /**
     * The Body Fat Percentage for body composition :  0 is invalid, 1 is valid.
     */
    public static final String CBS_BODY_FAT_PERCENTAGE = "cbs_body_fat_percentage";
    /**
     * The Time Stamp for body composition.
     */
    public static final String CBS_TIME_STAMP = "cbs_time_stamp";
    /**
     * The user id for body composition.
     */
    public static final String CBS_USER_ID = "cbs_user_id";
    /**
     * The Basal Metabolism for body composition.
     */
    public static final String CBS_BASAL_METABOLISM = "cbs_basal_metabolism";
    /**
     * The Muscle Percentage for body composition.
     */
    public static final String CBS_MUSCLE_PERCENTAGE = "cbs_muscle_percentage";
    /**
     * The Muscle Mass - Kilograms for body composition.
     */
    public static final String CBS_MUSCLE_MASS_KILOGRAMS = "cbs_muscle_mass_kilograms";
    /**
     * The Muscle Mass - Pounds for body composition.
     */
    public static final String CBS_MUSCLE_MASS_POUNDS = "cbs_muscle_mass_pounds";
    /**
     * The Fat Free Mass - Kilograms for body composition.
     */
    public static final String CBS_FAT_FREE_MASS_KILOGRAMS = "cbs_fat_free_mass_kilograms";
    /**
     * The Fat Free Mass - Pounds for body composition.
     */
    public static final String CBS_FAT_FREE_MASS_POUNDS = "cbs_fat_free_mass_pounds";
    /**
     * The Soft Lean Mass - Kilograms for body composition.
     */
    public static final String CBS_SOFT_LEAN_MASS_KILOGRAMS = "cbs_soft_lean_mass_kilograms";
    /**
     * The Soft Lean Mass - Pounds for body composition.
     */
    public static final String CBS_SOFT_LEAN_MASS_POUNDS = "cbs_soft_lean_mass_pounds";
    /**
     * The Body Water Mass - Kilograms for body composition.
     */
    public static final String CBS_BODY_WATER_MASS_KILOGRAMS = "cbs_body_water_mass_kilograms";
    /**
     * The Body Water Mass - Pounds for body composition.
     */
    public static final String CBS_BODY_WATER_MASS_POUNDS = "cbs_body_water_mass_pounds";
    /**
     * The Impedance for body composition.
     */
    public static final String CBS_IMPEDANCE = "cbs_impedance";
    /**
     * The Weight - Kilograms for body composition.
     */
    public static final String CBS_WEIGHT_KILOGRAMS = "cbs_weight_kilograms";
    /**
     * The Weight - Pounds for body composition.
     */
    public static final String CBS_WEIGHT_POUNDS = "cbs_weight_pounds";
    /**
     * The Height - Kilograms for body composition.
     */
    public static final String CBS_HEIGHT_KILOGRAMS = "cbs_height_kilograms";
    /**
     * The Height - Pounds for body composition.
     */
    public static final String CBS_HEIGHT_POUNDS = "cbs_height_pounds";

}
