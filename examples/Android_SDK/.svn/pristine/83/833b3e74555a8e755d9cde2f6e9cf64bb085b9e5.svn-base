package com.ihealth.communication.manager;

import android.content.Context;
import android.os.Environment;
import android.os.SystemClock;
import com.ihealth.communication.utils.Log;

import com.ihealth.communication.cloud.tools.CommCloudAMInstall;
import com.ihealth.communication.control.UpDeviceControl;
import com.ihealth.communication.control.UpgradeProfile;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.CrcCheck;
import com.ihealth.communication.utils.FirmWare;

import org.apache.http.util.EncodingUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Upgrade util class.<br/>
 * <ul>
 * <li>This is a singleton class, use {@link #getInstance()} to get the instance.</li>
 * <li>Use {@link #queryUpgradeInfoFromDeviceAndCloud(String, String)} to get version info from device and cloud.</li>
 * <li>Use {@link #isUpgradeState(String, String)} to get the upgrade state of the device.</li>
 * <li>Use {@link #startUpGrade(String, String)} to start upgrade.</li>
 * <li>Use {@link #stopUpgrade(String, String)} to stop upgrade</li>
 * </ul>
 *
 */
public class iHealthDevicesUpgradeManager {

    private static final String TAG = "iHealthDUM";

    private String mProductnum = "";
    private String mProductmodel = "";
    private String mFirwareVersion = "";
    private String mCloudFirwareVersion = "";
    private String mHardwareVersion = "";
    private String mAddress = "";
    private String mType = "";
    private JSONObject jsonObject;
    private Map<String, JSONObject> jsonObjectMap = new HashMap<>();
    /**
     * @hide
     */
    private static class SingletonHolder {
        static final iHealthDevicesUpgradeManager INSTANCE = new iHealthDevicesUpgradeManager();
    }

    /**
     * Get iHealthDevicesUpgradeManager's singleton instance.
     */
    public static iHealthDevicesUpgradeManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * @hide
     */
    private iHealthDevicesUpgradeManager() {

    }

    private Context mContext;
    private UpDeviceControl mUpDeviceControl;
    private CommCloudAMInstall mCommCloudAMInstall;
    private InsCallback mInsCallback;

    /**
     * Initialize upgrade manager class <br/>
     * Should be called in {@link iHealthDevicesManager#init(Context)}
     */
    /* package */ void init(Context context, InsCallback insCallback) {
        this.mContext = context;
        this.mInsCallback = insCallback;
        mCommCloudAMInstall = new CommCloudAMInstall();
    }

    /**
     * (Internal method)Get UpGrade Device control<br/>
     * Should not call directly.
     * @param mac the mac address of the device.
     * @param type the type of the device.
     *
     * {@hide}
     */
    @Deprecated
    public UpDeviceControl getUpDeviceControl(String mac, String type) {
        Log.p(TAG, Log.Level.VERBOSE,"getUpDeviceControl",mac,type);
        try {
            if(type.equals("AM3")) {
                return iHealthDevicesManager.getInstance().getAm3Control(mac).getUpDeviceControl();
            } else if(type.equals("AM3S")) {
                return iHealthDevicesManager.getInstance().getAm3sControl(mac).getUpDeviceControl();
            } else if(type.equals("PO3")) {
                return iHealthDevicesManager.getInstance().getPo3Control(mac).getUpDeviceControl();
            } else if(type.equals("HS4")) {
                return iHealthDevicesManager.getInstance().getHs4Control(mac).getUpDeviceControl();
            } else if(type.equals("HS4S")) {
                return iHealthDevicesManager.getInstance().getHs4sControl(mac).getUpDeviceControl();
            } else if(type.equals("AM4")) {
                return iHealthDevicesManager.getInstance().getAm4Control(mac).getUpDeviceControl();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     *
     * @return
     * @hide
     */
    private static FirmWare readIndexData() {
        FirmWare firmWare = new FirmWare();
        firmWare.setInblocknumber(new byte[] {
                0x00, 0x00
        });
        // 读SD中的文件
        String resInedx = "";
        try {
            // 放入SD卡根目录下的文件夹下，要先mkdir创建文件夹，获取文件夹对象之后 在创建文件
            File path = new File(getSDPath() + "/iHealth/");
            if (!path.exists()) {
                path.mkdirs();
            }
            File file = new File(path, "AMIndex.txt");
            FileInputStream fin = new FileInputStream(file);
            byte[] buffer = new byte[fin.available()];
            fin.read(buffer);
            int n = 0;
            for (int i = 0; i < 16; i++) {
                if (buffer[i] == 0) {
                    break;
                } else {
                    n++;
                }
            }


            byte[] softwareVersionNumber = new byte[3];
            softwareVersionNumber[0] = buffer[0x15];
            softwareVersionNumber[1] = buffer[0x16];
            softwareVersionNumber[2] = buffer[0x17];
            firmWare.setSoftwareVersionNumber(softwareVersionNumber);

            int codeLength = buffer[0x18] & 0xFF + (buffer[0x19] & 0xFF) * 256 + (buffer[0x1A] & 0xFF) * 256 * 256;
            byte[] softwareTotalLenght = new byte[3];
            softwareTotalLenght[0] = buffer[0x18];
            softwareTotalLenght[1] = buffer[0x19];
            softwareTotalLenght[2] = buffer[0x1A];
            firmWare.setSoftwareTotalLenght(softwareTotalLenght);

            int amVersionNum = buffer[0x1B] & 0xFF;

            int softVersionNum = buffer[0x1C + 3 * amVersionNum] & 0xFF;

            for (int i = 0; i < softVersionNum; i++) {

                byte[] temp1 = new byte[3];
                temp1[0] = buffer[0x1C + 3 * amVersionNum + 6 * i + 1 + 0];
                temp1[1] = buffer[0x1C + 3 * amVersionNum + 6 * i + 1 + 1];
                temp1[2] = buffer[0x1C + 3 * amVersionNum + 6 * i + 1 + 2];
                firmWare.setSoftwareVersionList(temp1);

                byte[] temp2 = new byte[3];
                temp2[0] = buffer[0x1C + 3 * amVersionNum + 6 * i + 1 + 3];
                temp2[1] = buffer[0x1C + 3 * amVersionNum + 6 * i + 1 + 3 + 1];
                temp2[2] = buffer[0x1C + 3 * amVersionNum + 6 * i + 1 + 3 + 2];
                firmWare.setSoftwareLenList(temp2);

            }

            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return firmWare;
    }

    /**
     * 获取sd卡根目录
     * @hide
     */
    private static String getSDPath() {
        File sdDir = null;
        sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
        return sdDir.toString();
    }


    private List<byte[]> codelist = new ArrayList<byte[]>();

    /**
     *
     * @return
     * @hide
     */
    private List<byte[]> readCodeData() {
        codelist.clear();
        // 读SD中的文件
        String res = "";
        try {
            File path = new File(getSDPath() + "/iHealth/");
            if (!path.exists()) {
                path.mkdirs();
            }
            File file = new File(path, "AMDownload.txt");
            FileInputStream fin = new FileInputStream(file);
            byte[] buffer = new byte[fin.available()];
            fin.read(buffer);
            fin.close();
//            Log.v(TAG, "CODE  crc = " + getCRClong(buffer));

            FileInputStream fin1 = new FileInputStream(file);
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] bufferParagraph = new byte[128];
            int len = 0;
            int d = 0;
            while ((len = fin1.read(bufferParagraph)) != -1) {
                outStream.write(bufferParagraph, 0, len);
                byte[] temp = new byte[128];
                for (int i = 0; i < len; i++) {
                    temp[i] = bufferParagraph[i];
                }
                if (len < 128) {
                    for (int i = len; i < 128; i++) {
                        temp[i] = (byte) 0xFF;
                    }
                }
//                Log.v(TAG, "Bag" + d + ":" + ByteBufferUtil.Bytes2HexString(temp));
                codelist.add(temp);
                d++;
            }

            // 得到文件的二进制数据
            byte[] data = outStream.toByteArray();
            res = EncodingUtils.getString(data, "UTF-8");
            outStream.close();
            fin1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.v(TAG, "code bags:" + codelist.size());
        return codelist;
    }


    /**
     * Get version information from device and cloud.
     * <ul>
     * <li>This is an asynchronous call, it will return immediately.</li>
     * <li>If get successfully, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <ul>
     * <li>The <b>action</b> is {@link UpgradeProfile#ACTION_DEVICE_UP_INFO} or {@link UpgradeProfile#ACTION_DEVICE_CLOUD_FIRMWARE_VERSION}.</li>
     * <li>The keys of the <b>message</b> will show in KeyList of the action.</li>
     * </ul>
     * </li>
     * <li>If error happens, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <ul>
     * <li>The <b>action</b> will be {@link UpgradeProfile#ACTION_DEVICE_ERROR}</li>
     * <li>The <b>message</b> will be a JSON string with error ID,<br/>
     * the keys will show in the {@linkplain UpgradeProfile#ACTION_DEVICE_ERROR KeyList of the action}.
     * </li>
     * </ul>
     * </li>
     * <li>
     * Attention, it is mandatory to call following method before you call this method:<br/>
     * {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)}
     * </li>
     * </ul>
     * @param mac The mac address of the device.
     * @param type The type of the device.
     */
    public synchronized void queryUpgradeInfoFromDeviceAndCloud(final String mac, final String type){
        //jing 20160824 检查输入参数是否正确
        if (mac == null || type == null || mac.length() != 12 || type.length()==0) {
            jsonObject = new JSONObject();
            try {
                //获取设备管理失败
                jsonObject.put(UpgradeProfile.DEVICE_UP_ERROR, 302);
                mInsCallback.onNotify(mac, type, UpgradeProfile.ACTION_DEVICE_ERROR, jsonObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return;
        }
        new Thread(){
            @Override
            public void run() {
                super.run();
                mUpDeviceControl = getUpDeviceControl(mac, type);
                if(mUpDeviceControl == null){
                    Log.w(TAG, "mUpDeviceControl is null for mac = " + mac + "   type = " + type);
                    jsonObject = new JSONObject();
                    try {
                        //获取设备管理失败
                        jsonObject.put(UpgradeProfile.DEVICE_UP_ERROR, 300);
                        mInsCallback.onNotify(mac, type, UpgradeProfile.ACTION_DEVICE_ERROR, jsonObject.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//					return;
                }else {
                    if (!isUpgradeState(mac, type)) {
                        SystemClock.sleep(500);
                        mUpDeviceControl.borrowComm();
                        mUpDeviceControl.judgUpgrade();
                    }
                }
            }
        }.start();
    }

    /**
     * Stop upgrade for specified device.
     * <ul>
     * <li>This is an asynchronous call, it will return immediately.</li>
     * <li>If stop successfully, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <ul>
     * <li>The <b>action</b> is {@link UpgradeProfile#ACTION_DEVICE_STOP_UP}.</li>
     * <li>The <b>message</b> will be null.</li>
     * </ul>
     * </li>
     * <li>If error happens, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <ul>
     * <li>The <b>action</b> will be {@link UpgradeProfile#ACTION_DEVICE_ERROR}</li>
     * <li>The <b>message</b> will be a JSON string with error ID,<br/>
     * the keys will show in the {@linkplain UpgradeProfile#ACTION_DEVICE_ERROR KeyList of the action}.
     * </li>
     * </ul>
     * </li>
     * <li>
     * Attention, it is mandatory to call following method before you call this method:<br/>
     * {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)}
     * </li>
     * </ul>
     * @param mac the mac address of the device.
     * @param type the type of the device.
     */
    public void stopUpgrade(final String mac, final String type) {
        //jing 20160824 检查输入参数是否正确
        if (mac == null || type == null || mac.length() != 12 || type.length()==0) {
            jsonObject = new JSONObject();
            try {
                //获取设备管理失败
                jsonObject.put(UpgradeProfile.DEVICE_UP_ERROR, 302);
                mInsCallback.onNotify(mac, type, UpgradeProfile.ACTION_DEVICE_ERROR, jsonObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //清除该设备的云端缓存
        jsonObjectMap.remove(mac);
        new Thread(){
            @Override
            public void run() {
                super.run();
                mUpDeviceControl = getUpDeviceControl(mac, type);
                if(mUpDeviceControl == null){
                    Log.w(TAG, "mUpDeviceControl is null for mac = " + mac + "   type = " + type);
                    jsonObject = new JSONObject();
                    try {
                        //获取设备管理失败
                        jsonObject.put(UpgradeProfile.DEVICE_UP_ERROR, 300);
                        mInsCallback.onNotify(mac, type, UpgradeProfile.ACTION_DEVICE_ERROR, jsonObject.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return;
                }else {
                    SystemClock.sleep(500);
                    if (mUpDeviceControl != null) {
                        mUpDeviceControl.stopUpgrade();
                        mUpDeviceControl.returnComm();
                    }
                    mInsCallback.onNotify(mAddress, mType, UpgradeProfile.ACTION_DEVICE_STOP_UP, null);
                }

            }
        }.start();
    }

    /**
     * Get device upgrade state.
     * <ul>
     * <li>If error happens, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <ul>
     * <li>The <b>action</b> will be {@link UpgradeProfile#ACTION_DEVICE_ERROR}</li>
     * <li>The <b>message</b> will be a JSON string with error ID,<br/>
     * the keys will show in the {@linkplain UpgradeProfile#ACTION_DEVICE_ERROR KeyList of the action}.
     * </li>
     * </ul>
     * </li>
     * <li>
     * Attention, it is mandatory to call following method before you call this method:<br/>
     * {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)}
     * </li>
     * </ul>
     * @param mac the mac address of the device.
     * @param type the type of the device.
     * @return true if device is in upgrade progress, false if not.
     */
    public boolean isUpgradeState (String mac, String type) {
        //jing 20160824 检查输入参数是否正确
        if (mac == null || type == null || mac.length() != 12 || type.length()==0) {
            jsonObject = new JSONObject();
            try {
                //获取设备管理失败
                jsonObject.put(UpgradeProfile.DEVICE_UP_ERROR, 302);
                mInsCallback.onNotify(mac, type, UpgradeProfile.ACTION_DEVICE_ERROR, jsonObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        mUpDeviceControl = getUpDeviceControl(mac, type);
        if(mUpDeviceControl == null){
            Log.v(TAG, "mUpDeviceControl is null for mac = " + mac + "   type = " + type);
            jsonObject = new JSONObject();
            try {
                //获取设备管理失败
                jsonObject.put(UpgradeProfile.DEVICE_UP_ERROR, 300);
                mInsCallback.onNotify(mac, type, UpgradeProfile.ACTION_DEVICE_ERROR, jsonObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }else {
            return mUpDeviceControl.isUpgradeState();
        }
    }

    /**
     * (Internal method)Get latest device firmware version from cloud.<br/>
     * Should not call directly.<br/>
     * Please use {@link #queryUpgradeInfoFromDeviceAndCloud(String, String)} instead.
     * @param mac Mac address of the device.
     * @param type Type of the device.
     * @param o JSON string for the detailed device information.
     *
     * @hide
     */
    @Deprecated
    public synchronized void queryLatestVersionFromCloud (final String mac, final String type, final JSONObject o) {
        if(mCommCloudAMInstall == null)
            return;
        mType = type;
        if (o != null) {
            try {

                mProductnum = (String) o.get(UpgradeProfile.DEVICE_TYPE);
                mProductmodel = (String) o.get(UpgradeProfile.DEVICE_MODE);
                mHardwareVersion = (String) o.get(UpgradeProfile.DEVICE_HARDWARE_VERSION);
                mFirwareVersion = (String) o.get(UpgradeProfile.DEVICE_FIRMWARE_VERSION);

                if(mProductnum == null || mProductnum.length()==0 || mProductmodel == null || mProductmodel.length()==0 ||
                        mHardwareVersion == null || mHardwareVersion.length()==0 || mFirwareVersion == null || mFirwareVersion.length()==0) {
                    if (isUpgradeState(mac, type)) {
                        //add 取消自升级
                        stopUpgrade(mac, type);
                    }
                    if (mUpDeviceControl != null) {
                        mUpDeviceControl.returnComm();
                    }
                    jsonObject = new JSONObject();
                    jsonObject.put(UpgradeProfile.DEVICE_UP_ERROR, 200);
                    mInsCallback.onNotify(mac, type, UpgradeProfile.ACTION_DEVICE_ERROR, jsonObject.toString());
                    return;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            if (isUpgradeState(mac, type)) {
                //add 取消自升级
                stopUpgrade(mac, type);
            }
            return;
        }

        new Thread() {
            @Override
            public void run() {
                Log.v(TAG, "query info: mFirwareVersion:" + mFirwareVersion
                        + " - mProductnum:" + mProductnum + " - mProductmodel:"
                        + mProductmodel + " - mHardwareVersion:"
                        + mHardwareVersion);
                if (mCommCloudAMInstall.cloundAMVersionCheck213(mFirwareVersion, mProductnum, mProductmodel, mHardwareVersion, "")) {
                    mCloudFirwareVersion = "0";
                    mCloudFirwareVersion = mCommCloudAMInstall.amCheckReturn.latestversion;
                    if (mCloudFirwareVersion.equals("")) {
                        mCloudFirwareVersion = "0.0.0";
                    }

                    //jing 20160824  存储所有设备的升级信息，可以支持多次查询，选择任一个升级的功能
                    try {
                        o.put(UpgradeProfile.DEVICE_CLOUD_FIRMWARE_VERSION,mCloudFirwareVersion);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonObjectMap.put(mac,o);

                    Log.v(TAG, "latest version from cloud: "+mCloudFirwareVersion);
                    try {
                        JSONObject json = new JSONObject();
                        json.put(UpgradeProfile.DEVICE_CLOUD_FIRMWARE_VERSION, mCloudFirwareVersion);
                        json.put(UpgradeProfile.DEVICE_MANDATORY_FLAG,mCommCloudAMInstall.amCheckReturn.mandatoryupgrade);
                        mInsCallback.onNotify(mac, type, UpgradeProfile.ACTION_DEVICE_CLOUD_FIRMWARE_VERSION, json.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    if (isUpgradeState(mac, type)) {
                        //add 取消自升级
                        stopUpgrade(mac, type);
                    }
                    Log.w(TAG, "queryLatestVersionFromCloud() -- failed");
                    jsonObject = new JSONObject();
                    try {
                        if (mUpDeviceControl != null) {
                            mUpDeviceControl.returnComm();
                        }
                        jsonObject.put(UpgradeProfile.DEVICE_UP_ERROR, 200);
                        mInsCallback.onNotify(mac, type, UpgradeProfile.ACTION_DEVICE_ERROR, jsonObject.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return;
                }
            }
        }.start();
    }

    /**
     * Start to upgrade for the specified device.
     * <ul>
     * <li>This is an asynchronous call, it will return immediately.</li>
     * <li>If start successfully, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <ul>
     * <li>The <b>action</b> will be:
     * <ul>
     * <li>{@link UpgradeProfile#ACTION_DEVICE_UP_START_DOWNLOAD}</li>
     * <li>{@link UpgradeProfile#ACTION_DEVICE_UP_DOWNLOAD_COMPLETED}</li>
     * <li>{@link UpgradeProfile#ACTION_DEVICE_START_UP}</li>
     * <li>{@link UpgradeProfile#ACTION_DEVICE_UP_PROGRESS}</li>
     * <li>{@link UpgradeProfile#ACTION_DEVICE_FINISH_UP}</li>
     * </ul>
     * </li>
     * <li>The keys of the <b>message</b> will show in KeyList of the action.</li>
     * </ul>
     * </li>
     * <li>If error happens, following callback will be triggered:<br/>
     * {@link iHealthDevicesCallback#onDeviceNotify(String mac, String deviceType, String action, String message)}<br/>
     * <ul>
     * <li>The <b>action</b> will be {@link UpgradeProfile#DEVICE_UP_ERROR}</li>
     * <li>The <b>message</b> will be a JSON string with error ID,<br/>
     * the keys will show in the {@linkplain UpgradeProfile#DEVICE_UP_ERROR KeyList of the action}.
     * </li>
     * </ul>
     * </li>
     * <li>
     * Attention, it is mandatory to call following method before you call this method:<br/>
     * {@link iHealthDevicesManager#registerClientCallback(iHealthDevicesCallback)}
     * </li>
     * </ul>
     * @param mac the mac address of the device.
     * @param type the type of the device.
     */
	public synchronized void startUpGrade (final String mac, final String type) {
        //jing 20160824 检查输入参数是否正确
        if (mac == null || type == null || mac.length() != 12 || type.length()==0) {
            jsonObject = new JSONObject();
            try {
                //获取设备管理失败
                jsonObject.put(UpgradeProfile.DEVICE_UP_ERROR, 302);
                mInsCallback.onNotify(mac, type, UpgradeProfile.ACTION_DEVICE_ERROR, jsonObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
            new Thread() {
			@Override
			public void run() {
                JSONObject upgradeInfo = jsonObjectMap.get(mac);
                if (upgradeInfo == null) {
                    //获取硬件版本失败
                    jsonObject = new JSONObject();
                    try {
                        if(mUpDeviceControl != null) {
                            mUpDeviceControl.returnComm();
                        }
                        jsonObject.put(UpgradeProfile.DEVICE_UP_ERROR, 201);
                        mInsCallback.onNotify(mac, type, UpgradeProfile.ACTION_DEVICE_ERROR, jsonObject.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                String hardwareversion = null;
                String productnum = null;
                String productmodel = null;
                String version = null;
                String cloudVersion = null;
                int upgradeFlag = 0;

                try {
                    hardwareversion = (String) upgradeInfo.get(UpgradeProfile.DEVICE_HARDWARE_VERSION);
                    productnum = (String) upgradeInfo.get(UpgradeProfile.DEVICE_TYPE);
                    productmodel = (String) upgradeInfo.get(UpgradeProfile.DEVICE_MODE);
                    version = (String) upgradeInfo.get(UpgradeProfile.DEVICE_FIRMWARE_VERSION);
                    cloudVersion = (String) upgradeInfo.get(UpgradeProfile.DEVICE_CLOUD_FIRMWARE_VERSION);
                    upgradeFlag = (int) upgradeInfo.get(UpgradeProfile.DEVICE_UPGRADE_FLAG);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


				if(hardwareversion == null||hardwareversion.length() == 0 || productmodel == null||productmodel.length() == 0 ||
                        productnum == null||productnum.length() == 0 || version == null||version.length() == 0 ||
                        cloudVersion == null||cloudVersion.length() == 0) {
					if (isUpgradeState(mac, type)) {
						//add 取消自升级
						stopUpgrade(mac, type);
					}
                    //获取硬件版本失败
                    jsonObject = new JSONObject();
                    try {
                        if (mUpDeviceControl != null) {
                            mUpDeviceControl.returnComm();
                        }
                        jsonObject.put(UpgradeProfile.DEVICE_UP_ERROR, 201);
                        mInsCallback.onNotify(mac, type, UpgradeProfile.ACTION_DEVICE_ERROR, jsonObject.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
					return;
				} //jing 20160823 重复升级，直接报错
                else if (version.equals(cloudVersion) && upgradeFlag==0){
                    jsonObject = new JSONObject();
                    try {
                        if (mUpDeviceControl != null) {
                            mUpDeviceControl.returnComm();
                        }
                        jsonObject.put(UpgradeProfile.DEVICE_UP_ERROR, 400);
                        mInsCallback.onNotify(mac, type, UpgradeProfile.ACTION_DEVICE_ERROR, jsonObject.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return;
                }

                //开始下载固件 jing 20160724
                mInsCallback.onNotify(mac, type, UpgradeProfile.ACTION_DEVICE_UP_START_DOWNLOAD, null);

				boolean b2 = false, b3 = false;
				if (mCommCloudAMInstall.cloundAMVersionDownload(cloudVersion, productnum, productmodel, hardwareversion, "", 1, 0, 10000000)) {
					b2 = true;
				} else {
					Log.w(TAG, "download info failed");
				}
				if (mCommCloudAMInstall.cloundAMVersionDownload(cloudVersion, productnum, productmodel, hardwareversion, "", 0, 0, 10000000)) {
					b3 = true;
				} else {
					Log.w(TAG, "download firmware failed");
				}

				List<Byte> list = new ArrayList<Byte>();
				if (b2 && b3) {
                    //下载固件完成 jing 20160724
                    mInsCallback.onNotify(mac, type, UpgradeProfile.ACTION_DEVICE_UP_DOWNLOAD_COMPLETED, null);
					FirmWare firmWare = readIndexData();
					List<byte[]> list2 = readCodeData();
					if (firmWare.getInblocknumber() != null) {
						byte[] temp = firmWare.getInblocknumber();
						list.add(temp[0]);
						list.add(temp[1]);
					}
					if (firmWare.getSoftwareVersionNumber() != null) {
						byte[] temp = firmWare.getSoftwareVersionNumber();
						list.add(temp[0]);
						list.add(temp[1]);
						list.add(temp[2]);
					}
					if (firmWare.getSoftwareTotalLenght() != null) {
						byte[] temp = firmWare.getSoftwareTotalLenght();
						list.add(temp[0]);
						list.add(temp[1]);
						list.add(temp[2]);
					}
					List<byte[]> tempList1 = firmWare.getSoftwareVersionList();
					List<byte[]> tempList2 = firmWare.getSoftwareLenList();
					for (int i = 0; i < tempList1.size(); i++) {
						byte[] temp1 = tempList1.get(i);
						byte[] temp2 = tempList2.get(i);
						list.add(temp1[0]);
						list.add(temp1[1]);
						list.add(temp1[2]);
						list.add(temp2[0]);
						list.add(temp2[1]);
						list.add(temp2[2]);
					}

					for (byte[] bs : list2) {
						CrcCheck cc = new CrcCheck(ByteBufferUtil.hexByteToInt(bs, bs.length));
						int checksum = cc.getCRCValue();
						byte[] temps = new byte[2];
						temps[0] = (byte) (checksum & 0Xff);
						temps[1] = (byte) ((checksum & 0Xff00) >> 8);
						firmWare.setCrcList(temps);
					}
					mUpDeviceControl = getUpDeviceControl(mac, type);
					if(mUpDeviceControl == null){
						if (isUpgradeState(mac, type)) {
							//add 取消自升级
							stopUpgrade(mac, type);
						}
						return;
					}

					mUpDeviceControl.borrowComm();
					mUpDeviceControl.setInformation(list);
					mUpDeviceControl.setData(firmWare, list2);

                    if (b2 && b3) {
                        Log.v(TAG, "start transmit firmware");
                        mUpDeviceControl.startUpgrade();
					}
				} else {
					if (isUpgradeState(mac, type)) {
						//add 取消自升级
						stopUpgrade(mac, type);
					}
                    //net error
                    jsonObject = new JSONObject();
                    try {
                        if (mUpDeviceControl != null) {
                            mUpDeviceControl.returnComm();
                        }
                        jsonObject.put(UpgradeProfile.DEVICE_UP_ERROR, 200);
                        mInsCallback.onNotify(mac, type, UpgradeProfile.ACTION_DEVICE_ERROR, jsonObject.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
				}
			}
		}.start();
	}
}
