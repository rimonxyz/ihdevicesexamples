/**
 * @title
 * @Description
 * @author
 * @date 2015年3月6日 上午9:09:34 
 * @version V1.0  
 */

package com.ihealth.communication.control;

import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.cloud.tools.AppsDeviceParameters;
import com.ihealth.communication.ins.A9InsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.ins.UserListInHs5;
import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.utils.IDPS;

import android.content.Context;
import com.ihealth.communication.utils.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Public API for the Hs5
 * <p>
 * The class provides methods to control Hs5 device.
 */
public class Hs5Control implements DeviceControl {
    private static final String TAG = "Hs5Control------";
    private static final String TAG1 = "HS5Wifi";
    // private boolean DEBUG = true;
    private IDPS mIdps;
    private A9InsSet mA9InsSet;
    private static UserListInHs5 mUserListInHs5;
    private String deviceMac;
    private BaseCommCallback mBaseCommCallback;
    private String mType;
    private InsCallback mInsCallback;

    /**
     * Constructor for Hs5Control.
     *
     * @param userName email of the user
     * @param context Context
     * @param deviceMac device mac
     * @param deviceIp device Ip address
     * @param comm communication class
     * @param baseCommCallback communication callback
     * @param insCallback Hs5 device callback.
     * @param type the type of ihealth device {@link iHealthDevicesManager#TYPE_HS5}
     * @hide
     */
    public Hs5Control(String userName, Context context, String deviceMac, String deviceIp, BaseComm comm,
            BaseCommCallback baseCommCallback, InsCallback insCallback, String type) {
        this.deviceMac = deviceMac;
        this.mBaseCommCallback = baseCommCallback;
        this.mInsCallback = insCallback;
        this.mType = type;
        this.mIdps = new IDPS();
        this.mUserListInHs5 = new UserListInHs5();
        this.mA9InsSet = new A9InsSet(userName, context, comm, deviceMac, deviceIp, mInsCallback, baseCommCallback,
                type,
                mUserListInHs5);
    }

    /**
     * start identify
     * 
     * @hide
     */
    @Override
    public void init() {
        mA9InsSet.identify();
    }

    private void notifyParameterError(String description) {
        Log.w(TAG, description);
        JSONObject object = new JSONObject();
        try {
            object.put(HsProfile.ERROR_NUM_HS, HsProfile.ERROR_ID_ILLEGAL_ARGUMENT);
            object.put(HsProfile.ERROR_DESCRIPTION_HS, description);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mInsCallback.onNotify(deviceMac, mType, HsProfile.ACTION_ERROR_HS, object.toString());
    }

    /**
     * Create user management.
     * <ul>
     * <li>This is an asynchronous call, it will return immediately.</li>
     * <li>If create successfully, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <ul>
     * <li>The <b>action</b> is {@link HsProfile#ACTION_MANAGEMENT_HS}.</li>
     * <li>The keys of message will show in the <u>{@linkplain HsProfile#ACTION_MANAGEMENT_HS KeyList of the action}</u>.</li>
     * </ul>
     * </li>
     * <li>If error happens, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <ul>
     * <li>The <b>action</b> will be {@link HsProfile#ACTION_ERROR_HS}</li>
     * <li>The <b>message</b> will be a JSON string with error ID and description,<br/>
     * the keys will show in the {@linkplain HsProfile#ACTION_ERROR_HS KeyList of the action}.
     * </li>
     * </ul>
     * </li>
     * <li>
     * Attention, it is mandatory to call following method before you call this method:<br/>
     * {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)}
     * </li>
     * </ul>
     * @param userId user's ID.<br/>
     *               <b>Range:</b> [0, 2147483647(0x7FFFFFFF)]
     */
    public void creatManagement(int userId) {
        if (userId < 0 || userId > 2147483647) {
            notifyParameterError("creatManagement() parameter userId should in the range [0, 2147483647(0x7FFFFFFF)");
            return;
        }

        mA9InsSet.setUserId(userId);
        mA9InsSet.createManagementCnn();
    }

    /**
     * Put the user information to the specified position.
     * <ul>
     * <li>This is an asynchronous call, it will return immediately.</li>
     * <li>If create successfully, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <ul>
     * <li>The <b>action</b> is {@link HsProfile#ACTION_ADDUSER_HS}.</li>
     * <li>The keys of message will show in the <u>{@linkplain HsProfile#ACTION_ADDUSER_HS KeyList of the action}</u>.</li>
     * </ul>
     * </li>
     * <li>If error happens, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <ul>
     * <li>The <b>action</b> will be {@link HsProfile#ACTION_ERROR_HS}</li>
     * <li>The <b>message</b> will be a JSON string with error ID and description,<br/>
     * the keys will show in the {@linkplain HsProfile#ACTION_ERROR_HS KeyList of the action}.
     * </li>
     * </ul>
     * </li>
     * <li>
     * Attention, it is mandatory to call following method before you call this method:<br/>
     * {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)}
     * </li>
     * </ul>
     * @param userPstCode the position of the scale from 0 to 19<br/>
     *                    <b>Range:</b> [0, 19]
     * @param userId      user identify card number<br/>
     *                    <b>Range:</b> [0, 2147483647(0x7FFFFFFF)]
     * @param age         the age of user<br/>
     *                    <b>Range:</b> [7, 99]
     * @param height      height of user (in cm)<br/>
     *                    <b>Range:</b> [81, 219]
     * @param isSporter   Whether user is sportsman or not.
     *                    <ul>
     *                    <li>0 indicates user is not sportsman</li>
     *                    <li>1 indicates user is sportsman</li>
     *                    </ul>
     * @param gender      User's gender
     *                    <ul>
     *                    <li>0 indicates female</li>
     *                    <li>1 indicates male</li>
     *                    </ul>
     */
    public void WriteUserToScale(int userPstCode, int userId, int age, int height, int isSporter, int gender) {
        if (userPstCode < 0 || userPstCode > 19) {
            notifyParameterError("WriteUserToScale() parameter userPstCode should be in the range [0, 19].");
            return;
        }
        if (userId < 0 || userId > 2147483647) {
            notifyParameterError("WriteUserToScale() parameter userId should be in the range [0, 2147483647(0x7FFFFFFF)].");
            return;
        }
        if (age < 7 || age > 99) {
            notifyParameterError("WriteUserToScale() parameter userId should be in the range [7, 99].");
            return;
        }
        if (height < 81 || height > 219) {
            notifyParameterError("WriteUserToScale() parameter height should be in the range [81, 219].");
            return;
        }
        if (isSporter < 0 || isSporter > 1) {
            notifyParameterError("WriteUserToScale() parameter isSporter should be 0 or 1.");
            return;
        }
        if (gender < 0 || gender > 1) {
            notifyParameterError("WriteUserToScale() parameter gender should be 0 or 1.");
            return;
        }
        mA9InsSet.WriteUserToScale(userPstCode, userId, age, height, isSporter, gender);
    }

    /**
     * Remove user information from the specified position of the scale.
     * <ul>
     * <li>This is an asynchronous call, it will return immediately.</li>
     * <li>If remove successfully, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <ul>
     * <li>The <b>action</b> is {@link HsProfile#ACTION_DELETEUSER_HS}.</li>
     * <li>The keys of message will show in the <u>{@linkplain HsProfile#ACTION_DELETEUSER_HS KeyList of the action}</u>.</li>
     * </ul>
     * </li>
     * <li>If error happens, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <ul>
     * <li>The <b>action</b> will be {@link HsProfile#ACTION_ERROR_HS}</li>
     * <li>The <b>message</b> will be a JSON string with error ID and description,<br/>
     * the keys will show in the {@linkplain HsProfile#ACTION_ERROR_HS KeyList of the action}.
     * </li>
     * </ul>
     * </li>
     * <li>
     * Attention, it is mandatory to call following method before you call this method:<br/>
     * {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)}
     * </li>
     * </ul>
     * @param userPstCode the position in scale<br/>
     *                    <b>Range:</b> [0, 19]
     */
    public void DeleteUserInScale(int userPstCode) {
        if (userPstCode < 0 || userPstCode > 19) {
            notifyParameterError("DeleteUserInScale() parameter userPstCode should be in the range [0, 19].");
            return;
        }
        int userId = getUserListInHs5().getUserIds()[userPstCode];
        mA9InsSet.DeleteUserInScale(userPstCode, userId);
    }

    /**
     * Update user information of the scale.
     * <ul>
     * <li>This is an asynchronous call, it will return immediately.</li>
     * <li>If update successfully, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <ul>
     * <li>The <b>action</b> is {@link HsProfile#ACTION_UPDATEUSER_HS}.</li>
     * <li>The keys of message will show in the <u>{@linkplain HsProfile#ACTION_UPDATEUSER_HS KeyList of the action}</u>.</li>
     * </ul>
     * </li>
     * <li>If error happens, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <ul>
     * <li>The <b>action</b> will be {@link HsProfile#ACTION_ERROR_HS}</li>
     * <li>The <b>message</b> will be a JSON string with error ID and description,<br/>
     * the keys will show in the {@linkplain HsProfile#ACTION_ERROR_HS KeyList of the action}.
     * </li>
     * </ul>
     * </li>
     * <li>
     * Attention, it is mandatory to call following method before you call this method:<br/>
     * {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)}
     * </li>
     * </ul>
     * @param userPstCode the position of the scale from 0 to 19<br/>
     *                    <b>Range:</b> [0, 19]
     * @param userId      user identify card number<br/>
     *                    <b>Range:</b> [0, 2147483647(0x7FFFFFFF)]
     * @param age         the age of user<br/>
     *                    <b>Range:</b> [7, 99]
     * @param height      height of user (in cm)<br/>
     *                    <b>Range:</b> [81, 219]
     * @param isSporter   Whether user is sportsman or not.
     *                    <ul>
     *                    <li>0 indicates user is not sportsman</li>
     *                    <li>1 indicates user is sportsman</li>
     *                    </ul>
     * @param gender      User's gender
     *                    <ul>
     *                    <li>0 indicates female</li>
     *                    <li>1 indicates male</li>
     *                    </ul>
     */
    public void updateUserInfo(int userPstCode, int userId, int age, int height, int isSporter, int gender) {
        if (userPstCode < 0 || userPstCode > 19) {
            notifyParameterError("updateUserInfo() parameter userPstCode should be in the range [0, 19].");
            return;
        }
        if (userId < 0 || userId > 2147483647) {
            notifyParameterError("updateUserInfo() parameter userId should be in the range [0, 2147483647(0x7FFFFFFF)].");
            return;
        }
        if (age < 7 || age > 99) {
            notifyParameterError("updateUserInfo() parameter userId should be in the range [7, 99].");
            return;
        }
        if (height < 81 || height > 219) {
            notifyParameterError("updateUserInfo() parameter height should be in the range [81, 219].");
            return;
        }
        if (isSporter < 0 || isSporter > 1) {
            notifyParameterError("updateUserInfo() parameter isSporter should be 0 or 1.");
            return;
        }
        if (gender < 0 || gender > 1) {
            notifyParameterError("updateUserInfo() parameter gender should be 0 or 1.");
            return;
        }
        mA9InsSet.updateUserInfo(userPstCode, userId, age, height, isSporter, gender);
    }

    /**
     * Get offline data of specified user
     * <ul>
     * <li>This is an asynchronous call, it will return immediately.</li>
     * <li>If get successfully, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <ul>
     * <li>The <b>action</b> is {@link HsProfile#ACTION_NO_HISTORICALDATA} or {@link HsProfile#ACTION_HISTORICAL_DATA_HS}.</li>
     * <li>The keys of message will show in the <u>{@linkplain HsProfile#ACTION_HISTORICAL_DATA_HS KeyList of the action}</u>.</li>
     * </ul>
     * </li>
     * <li>If error happens, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <ul>
     * <li>The <b>action</b> will be {@link HsProfile#ACTION_ERROR_HS}</li>
     * <li>The <b>message</b> will be a JSON string with error ID and description,<br/>
     * the keys will show in the {@linkplain HsProfile#ACTION_ERROR_HS KeyList of the action}.
     * </li>
     * </ul>
     * </li>
     * <li>
     * Attention, it is mandatory to call following method before you call this method:<br/>
     * {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)}
     * </li>
     * </ul>
     * @param userPstCode the position of the scale from 0 to 19<br/>
     *                    <b>Range:</b> [0, 19]
     * @param userId      user identify card number<br/>
     *                    <b>Range:</b> [0, 2147483647(0x7FFFFFFF)]
     */
    public void getOfflineData(int userPstCode, int userId) {
        if (userPstCode < 0 || userPstCode > 19) {
            notifyParameterError("getOfflineData() parameter userPstCode should be in the range [0, 19].");
            return;
        }
        if (userId < 0 || userId > 2147483647) {
            notifyParameterError("getOfflineData() parameter userId should be in the range [0, 2147483647(0x7FFFFFFF)].");
            return;
        }
        mA9InsSet.creatMemoryCnn(userPstCode, userId);
    }

    /**
     * Start measure to get live data and the result data.
     * <ul>
     * <li>This is an asynchronous call, it will return immediately.</li>
     * <li>If scale upload data, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <ul>
     * <li>The <b>action</b> will be:
     * <ol>
     * <li>{@link HsProfile#ACTION_LIVEDATA_HS}</li>
     * <li>{@link HsProfile#ACTION_STABLEDATA_HS}</li>
     * <li>{@link HsProfile#ACTION_IMPEDANCEDATA_HS}</li>
     * <li>{@link HsProfile#ACTION_ONLINE_RESULT_HS}</li>
     * </ol>
     * </li>
     * <li>The keys of message will show in the KeyList of the actions.</li>
     * </ul>
     * </li>
     * <li>If error happens, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <ul>
     * <li>The <b>action</b> will be {@link HsProfile#ACTION_ERROR_HS}</li>
     * <li>The <b>message</b> will be a JSON string with error ID and description,<br/>
     * the keys will show in the {@linkplain HsProfile#ACTION_ERROR_HS KeyList of the action}.
     * </li>
     * </ul>
     * </li>
     * <li>
     * Attention, it is mandatory to call following method before you call this method:<br/>
     * {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)}
     * </li>
     * </ul>
     * @param userPstCode the position of the scale from 0 to 19<br/>
     *                    <b>Range:</b> [0, 19]
     * @param userId      user identify card number<br/>
     *                    <b>Range:</b> [0, 2147483647(0x7FFFFFFF)]
     */
    public void startMeasure(int userPstCode, int userId) {
        if (userPstCode < 0 || userPstCode > 19) {
            notifyParameterError("startMeasure() parameter userPstCode should be in the range [0, 19].");
            return;
        }
        if (userId < 0 || userId > 2147483647) {
            notifyParameterError("startMeasure() parameter userId should be in the range [0, 2147483647(0x7FFFFFFF)].");
            return;
        }
        mA9InsSet.creatMeasurementCnn(userPstCode, userId);
    }

    /**
     * get The userList information in the scale {@link UserListInHs5}
     * 
     * @hide
     */
    public UserListInHs5 getUserListInHs5() {
        return this.mUserListInHs5;
    }

    /**
     * @param @param idps
     * @return void
     * @hide
     */
    public void setWifiIDPSData(IDPS idps) {
        this.mIdps = idps;
    }

    /**
     * Get idps information {@link IDPS}
     * 
     * @hide
     */
    public IDPS getWifiIDPSData() {
        return this.mIdps;
    }

    @Override
    public void disconnect() {
        mBaseCommCallback.onConnectionStateChange(deviceMac, mType,
                iHealthDevicesManager.DEVICE_STATE_DISCONNECTED, 0, null);
    }

    @Override
    public void destroy() {

    }
}
