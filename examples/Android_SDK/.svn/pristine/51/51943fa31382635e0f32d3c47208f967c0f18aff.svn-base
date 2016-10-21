package com.ihealth.communication.ins;

import android.content.Context;

import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.base.protocol.BleCommProtocol;
import com.ihealth.communication.base.protocol.BtCommProtocol;
import com.ihealth.communication.cloud.data.BG_InAuthor;
import com.ihealth.communication.cloud.data.DataBaseConstants;
import com.ihealth.communication.cloud.data.DataBaseTools;
import com.ihealth.communication.cloud.data.Data_BG_Result;
import com.ihealth.communication.cloud.data.Make_Data_Util;
import com.ihealth.communication.cloud.tools.AppsDeviceParameters;
import com.ihealth.communication.cloud.tools.Method;
import com.ihealth.communication.control.Bg5Profile;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.utils.BgSyncData;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.CrcCheck;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.PublicMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class Bg5InsSet extends IdentifyIns implements NewDataCallback {

    private static final String TAG = "Bg5InsSet";
    private static final byte deviceType = (byte) 0xa2;
    private Context mContext;
    private BaseCommProtocol btcm;
    private String mAddress = "";
    private String mType = "";
    private long UpbottleId;
    private String mUserName = "";

    private BaseComm mComm;
    /* Product protocol callback */
    private InsCallback mInsCallback;
    /* Communication callback */
    private BaseCommCallback mBaseCommCallback;

    private String QRCode;
    private int stripNum;
    private String overDate;


    private static final String ctlCode = "02323c64323c01006400fa00e103016800f000f0" +
            "015e025814012c0000a0002800a003d100320046" +
            "005a006e0082009600aa00b400e601040118012c" +
            "01400168017c0190064d05f905a8055c051304cf" +
            "048f047103e803a203790353033202fc02e702d7" +
            "1027383d4e6f646464646464646464640319010b" +
            "0701";
    //For BG1 1303 1304 1305 1307 BG5  BG5L
    private static final String stripCode = "02323C64323C01006400FA00E1030168003C003C" +
            "01F4025814015E3200A0005A00A0032000320046" +
            "005A006E0082009600AA00B400E6010401180140" +
            "0168017C0190019A04C604A8048B04700456043E" +
            "0428041D03E803E803E803B303A3039D03990398" +
            "10273D464E6F646464646464646464640319010B" +
            "0701";
    //ONECODE
    private static final String oneCodeBG5Str = "02323C46323C01006400FA00E103016800F000F0" +
            "015E025814015E3200A0002800A003D100320046" +
            "005A006E0082009600AA00B400E6010401180140" +
            "0168017C0190019A04CA04B10497047D04630449" +
            "0430042303E203BB03A2036E033A0321030702FA" +
            "0C27383D4E6F271A4557ED14194760FF03FF0163" +
            "0C10";

    /**
     * a constructor for Bg5InsSet.
     *
     * @param com              class for communication.
     * @param context          Context.
     * @param mac              valid Bluetooth address(without colon).
     * @param type             valid Bluetooth name.
     * @param baseCommCallback communication callback.
     * @param insCallback      Bg series protocol callback.
     * @hide
     */
    public Bg5InsSet(String userName, BaseComm com, Context context, String mac, String type, BaseCommCallback baseCommCallback, InsCallback insCallback) {
        this.mUserName = userName;
        this.mComm = com;
        if (type.equals(iHealthDevicesManager.TYPE_BG5l)) {
            this.btcm = new BleCommProtocol(context, com, mac, deviceType, this);
        } else {
            this.btcm = new BtCommProtocol(com, this);
        }
        this.mContext = context;
        this.mAddress = mac;
        this.mType = type;
        this.mInsCallback = insCallback;
        this.mBaseCommCallback = baseCommCallback;

        setInsSetCallbak(insCallback, mac, type, com);
    }

    /**
     * Send code to Bg5 Device and set the leftnum
     *
     * @param qr
     * @param leftnum
     */
    public void sendCode(String qr, byte leftnum) {
        byte[] allCodeBuf = ByteBufferUtil.hexStringToByte(qr);
        byte[] returnCommand = new byte[128];
        byte commandID = (byte) 0x25;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;

        allCodeBuf[117] = leftnum;

        CrcCheck cc = new CrcCheck(hexByteToInt(allCodeBuf, 122));
        int chechsum = cc.getCRCValue();
        allCodeBuf[122] = (byte) ((chechsum & 0Xff00) >> 8);
        allCodeBuf[123] = (byte) (chechsum & 0X00ff);


        for (int i = 0; i < allCodeBuf.length; i++) {
            returnCommand[2 + i] = allCodeBuf[i];
        }
        returnCommand[126] = 0x00;
        returnCommand[127] = 0x01;

        btcm.packageData(mAddress, returnCommand);
    }

    /**
     * Get version of BG device
     *
     * @hide
     */
    public void getBtEE() {
        byte[] commR = ByteBufferUtil.hexStringToByte("0D0A2B495353435F525F4545503A3031386130360D0A");
        mComm.sendData(mAddress, commR);
    }

    /**
     * Get device IDPS
     *
     * @hide
     */
    public void getIdps() {
        byte[] returnCommand = new byte[2];
        byte commandID = (byte) 0x3f;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        btcm.packageData(mAddress, returnCommand);
    }

    /**
     * Authentication
     *
     * @hide
     */
    public void identify() {
        startTimeout(0xfa, AppsDeviceParameters.Delay_Medium, 0xfb, 0xfd, 0xfe);
        btcm.packageData(mAddress, identify(deviceType));
    }

    /**
     * @hide
     */
    private void ask(byte commandID) {
        try {
            if (mType.equals(iHealthDevicesManager.TYPE_BG5)) {
                byte[] returnCommand = new byte[1];
                returnCommand[0] = deviceType;
                btcm.packageDataAsk(returnCommand);
            } else {
                byte[] returnCommand = new byte[2];
                returnCommand[0] = deviceType;
                returnCommand[1] = commandID;
                btcm.packageData(mAddress, returnCommand);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * set time to Bg device.
     *
     * @hide
     */
    public void setTime() {

        Log.p(TAG, Log.Level.INFO, "setTime");
        byte[] returnCommand = new byte[8];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getDefault());
        String date = sdf.format(new Date(System.currentTimeMillis()));

        Integer year = Integer.parseInt(date.split(" ")[0].split("-")[0]) - 2000;
        Integer month = Integer.parseInt(date.split(" ")[0].split("-")[1]);
        Integer day = Integer.parseInt(date.split(" ")[0].split("-")[2]);

        Integer hour = Integer.parseInt(date.split(" ")[1].split(":")[0]);
        Integer min = Integer.parseInt(date.split(" ")[1].split(":")[1]);
        Integer sec = Integer.parseInt(date.split(" ")[1].split(":")[2]);
        startTimeout(0x21, AppsDeviceParameters.Delay_Short, 0x21);
        byte commandID = (byte) 0x21;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        returnCommand[2] = year.byteValue();
        returnCommand[3] = month.byteValue();
        returnCommand[4] = day.byteValue();
        returnCommand[5] = hour.byteValue();
        returnCommand[6] = min.byteValue();
        returnCommand[7] = sec.byteValue();
        btcm.packageData(mAddress, returnCommand);
    }

    /**
     * set unit to current Bg device
     *
     * @param unit the unit flag,1:mmol/L 2:mg/dL
     * @hide
     */
    public void setUnit(int unit) {
        Log.p(TAG, Log.Level.INFO, "setUnit", unit);

        byte[] returnCommand = new byte[5];
        byte commandID = (byte) 0x23;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        returnCommand[2] = (byte) unit;
        returnCommand[3] = 0;
        returnCommand[4] = 0;
        btcm.packageData(mAddress, returnCommand);
    }

    /**
     * send bottleId to current Bg device
     *
     * @param bottleId
     * @hide
     */
    public void setBottleId(long bottleId, String QRCode, int stripNum, String overDate, boolean innerFlag) {

        Log.p(TAG, Log.Level.INFO, "setBottleId", bottleId, QRCode, stripNum, overDate, innerFlag);

        UpbottleId = bottleId;
        //jing 20160818  set bottleid set bottlemessage 串行
        this.QRCode = QRCode;
        this.stripNum = stripNum;
        this.overDate = overDate;

        //jing 20160907 对校期格式和试条数进行验证
        if (innerFlag == false) {
            boolean checkPara = true;
            String error = "";
            if (stripNum < 1 || stripNum > 255) {
                checkPara = false;
                error = "setBottleMessage(String QRCode, byte stripNum, String overDate) parameter(stripNum) should not  be in the range [1, 255].";
            }

            if (checkPara == true) {
                if (overDate != null) {
                    String[] splitDate = overDate.split("-");
                    if (splitDate.length != 3) {
                        checkPara = false;
                        error = "setBottleMessage(String QRCode, byte stripNum, String overDate) parameter(overDate) should not be the format(yyyy-MM-dd).";
                    }
                } else {
                    checkPara = false;
                    error = "setBottleMessage(String QRCode, byte stripNum, String overDate) parameter(overDate) should not be null.";
                }
            }

            if (checkPara == false) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put(Bg5Profile.ERROR_NUM_BG, 400);
                    jsonObject.put("description", error);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mInsCallback.onNotify(mAddress, mType, Bg5Profile.ACTION_ERROR_BG, jsonObject.toString());
                return;
            }
        }

        startTimeout(0x2d, AppsDeviceParameters.Delay_Short, 0x2d);
        byte[] userIds = ByteBufferUtil.intTo4Byte(bottleId);
        byte[] returnCommand = new byte[6];
        byte commandID = (byte) 0x2d;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        returnCommand[2] = userIds[0];
        returnCommand[3] = userIds[1];
        returnCommand[4] = userIds[2];
        returnCommand[5] = userIds[3];
        btcm.packageData(mAddress, returnCommand);
    }

    /**
     * get bottleId of current Bg device
     *
     * @hide
     */
    public void getBottleId() {
        Log.p(TAG, Log.Level.INFO, "getBottleId");
        byte[] returnCommand = new byte[6];
        byte commandID = (byte) 0x2e;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        returnCommand[2] = (byte) 0;
        returnCommand[3] = (byte) 0;
        returnCommand[4] = (byte) 0;
        returnCommand[5] = (byte) 0;
        btcm.packageData(mAddress, returnCommand);
    }

    /**
     * get historical data num
     *
     * @hide
     */
    public void readEENum() {
        Log.p(TAG, Log.Level.INFO, "readEENum");
        byte[] returnCommand = new byte[5];
        byte commandID = (byte) 0x42;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        returnCommand[2] = (byte) 0;
        returnCommand[3] = (byte) 0;
        returnCommand[4] = (byte) 0;
        btcm.packageData(mAddress, returnCommand);
    }

    /**
     * get historical data
     *
     * @param i the package num.
     * @hide
     */
    private void readEE(int i) {
        byte[] returnCommand = new byte[5];
        byte commandID = (byte) 0x40;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        returnCommand[2] = (byte) 0;
        returnCommand[3] = (byte) 0;
        returnCommand[4] = (byte) i;
        btcm.packageData(mAddress, returnCommand);
    }

    /**
     * delete historical data from current Bg device
     *
     * @hide
     */
    public void deleteEE() {

        Log.p(TAG, Log.Level.INFO, "deleteEE");
        byte[] returnCommand = new byte[5];
        byte commandID = (byte) 0x43;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        returnCommand[2] = (byte) 0;
        returnCommand[3] = (byte) 0;
        returnCommand[4] = (byte) 0;
        btcm.packageData(mAddress, returnCommand);
    }

    /**
     * start measure
     *
     * @param type test flag 1:test with blood; 2: test with control liquid
     * @hide
     */
    public void startMeasure(int type) {
        Log.p(TAG, Log.Level.INFO, "startMeasure", type);
        byte[] returnCommand = new byte[5];
        byte commandID = (byte) 0x31;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        returnCommand[2] = (byte) 0x00;
        returnCommand[3] = (byte) 0x00;
        if (type == 1) {
            returnCommand[4] = (byte) 0x01;
        } else if (type == 2) {
            returnCommand[4] = (byte) 0x02;
        }
        btcm.packageData(mAddress, returnCommand);

    }

    /**
     * set bottleInfo to current Bg device
     *
     * @param qr the QRCode of scan
     * @hide
     */
    public void setBottleMessage(String qr) {
        byte[] codes = new byte[126];
        if (PublicMethod.isOneCode(qr)) {
            byte[] temp = ByteBufferUtil.hexStringToByte(oneCodeBG5Str);
            System.arraycopy(temp, 0, codes, 0, 122);
            com.ihealth.androidbg.audio.CrcCheck cc = new com.ihealth.androidbg.audio.CrcCheck(hexByteToInt(codes, 122));
            int chechsum = cc.getCRCValue();

            codes[122] = (byte) ((chechsum & 0Xff00) >> 8);
            codes[123] = (byte) (chechsum & 0X00ff);

            codes[124] = (byte) 0x00;
            codes[125] = (byte) 0x01;
        } else if (PublicMethod.isCtlCode(qr)) {
            byte[] temp = ByteBufferUtil.hexStringToByte(ctlCode);
            System.arraycopy(temp, 0, codes, 0, 122);
            com.ihealth.androidbg.audio.CrcCheck cc = new com.ihealth.androidbg.audio.CrcCheck(hexByteToInt(codes, 122));
            int chechsum = cc.getCRCValue();

            codes[122] = (byte) ((chechsum & 0Xff00) >> 8);
            codes[123] = (byte) (chechsum & 0X00ff);

            codes[124] = (byte) 0x00;
            codes[125] = (byte) 0x01;
        } else {
            codes = compileQR(qr);
        }

        byte[] returnCommand = new byte[128];
        byte commandID = (byte) 0x25;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        for (int i = 0; i < 126; i++) {
            returnCommand[2 + i] = codes[i];
        }

        btcm.packageData(mAddress, returnCommand);
    }

    /**
     * set bottleInfo to current Bg device
     *
     * @param qr      the QRCode of scan
     * @param leftnum set the strip leftnum
     * @param year    set the strip expire time
     * @param month
     * @param day
     * @hide
     */
    public void setBottleMessage(String qr, int leftnum, byte year, byte month, byte day) {
        byte[] codes = new byte[126];
        byte[] returnCommand = new byte[128];
        byte commandID = (byte) 0x25;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        if (PublicMethod.isOneCode(qr)) {
            byte[] temp = ByteBufferUtil.hexStringToByte(oneCodeBG5Str);
            System.arraycopy(temp, 0, codes, 0, 122);
            com.ihealth.androidbg.audio.CrcCheck cc = new com.ihealth.androidbg.audio.CrcCheck(hexByteToInt(codes, 122));
            int chechsum = cc.getCRCValue();

            codes[122] = (byte) ((chechsum & 0Xff00) >> 8);
            codes[123] = (byte) (chechsum & 0X00ff);

            codes[124] = (byte) 0x00;
            codes[125] = (byte) 0x01;

            for (int i = 0; i < 126; i++) {
                returnCommand[2 + i] = codes[i];
            }
        } else if (PublicMethod.isCtlCode(qr)) {
            byte[] temp = ByteBufferUtil.hexStringToByte(ctlCode);
            System.arraycopy(temp, 0, codes, 0, 122);
            com.ihealth.androidbg.audio.CrcCheck cc = new com.ihealth.androidbg.audio.CrcCheck(hexByteToInt(codes, 122));
            int chechsum = cc.getCRCValue();

            codes[122] = (byte) ((chechsum & 0Xff00) >> 8);
            codes[123] = (byte) (chechsum & 0X00ff);

            codes[124] = (byte) 0x00;
            codes[125] = (byte) 0x01;

            for (int i = 0; i < 126; i++) {
                returnCommand[2 + i] = codes[i];
            }
        } else {
            codes = compileQR(qr);

            codes[117] = (byte) leftnum;
            codes[119] = year;
            codes[120] = month;
            codes[121] = day;

            CrcCheck cc = new CrcCheck(hexByteToInt(codes, 122));
            int chechsum = cc.getCRCValue();
            codes[122] = (byte) ((chechsum & 0Xff00) >> 8);
            codes[123] = (byte) (chechsum & 0X00ff);

            for (int i = 0; i < 126; i++) {
                returnCommand[2 + i] = codes[i];
            }
        }
        btcm.packageData(mAddress, returnCommand);
    }

    /**
     * @param bt
     * @param len
     * @return
     * @hide
     */
    private static int[] hexByteToInt(byte[] bt, int len) {
        int[] in = new int[len];
        for (int i = 0; i < len; i++) {
            in[i] = (int) bt[i];
            if (in[i] < 0) {
                in[i] = 256 + in[i];
            }
        }
        return in;
    }

    /**
     * compile the qr
     *
     * @param qr
     * @return
     * @hide
     */
    private byte[] compileQR(String qr) {
        byte[] allCodeBuf;
        allCodeBuf = new byte[126];
        byte[] someCodeBuf = ByteBufferUtil.hexStringToByte(qr);
        byte[] temp = ByteBufferUtil.hexStringToByte(stripCode);
        for (int i = 0; i < 6; i++) {
            allCodeBuf[i] = someCodeBuf[i];
        }
        allCodeBuf[6] = 0x01;
        int sampleTime = (int) ((someCodeBuf[6] & 0xFF) * 0.1 * 1000 / 20);
        allCodeBuf[7] = (byte) ((sampleTime & 0xFF00) >> 8);
        allCodeBuf[8] = (byte) (sampleTime & 0x00FF);

        sampleTime = (int) ((someCodeBuf[7] & 0xFF) * 0.1 * 1000 / 20);
        allCodeBuf[9] = (byte) ((sampleTime & 0xFF00) >> 8);
        allCodeBuf[10] = (byte) (sampleTime & 0x00FF);
        sampleTime = (int) ((someCodeBuf[8] & 0xFF) * 0.1 * 1000 / 20);
        allCodeBuf[11] = (byte) ((sampleTime & 0xFF00) >> 8);
        allCodeBuf[12] = (byte) (sampleTime & 0x00FF);

        // 13-29
        for (int i = 13; i < 30; i++) {
            allCodeBuf[i] = temp[i];
        }
        sampleTime = (int) ((someCodeBuf[9] & 0xFF) * 0.1 * 1000 / 20);
        allCodeBuf[30] = (byte) ((sampleTime & 0xFF00) >> 8);
        allCodeBuf[31] = (byte) (sampleTime & 0x00FF);
        allCodeBuf[32] = someCodeBuf[10];
        allCodeBuf[33] = someCodeBuf[11];
        for (int i = 34; i < 106; i++) {
            allCodeBuf[i] = temp[i];
        }
        allCodeBuf[106] = someCodeBuf[12];
        allCodeBuf[107] = someCodeBuf[13];
        allCodeBuf[108] = someCodeBuf[14];
        allCodeBuf[109] = someCodeBuf[15];
        allCodeBuf[110] = someCodeBuf[16];
        allCodeBuf[111] = someCodeBuf[17];
        allCodeBuf[112] = someCodeBuf[18];
        allCodeBuf[113] = someCodeBuf[19];
        allCodeBuf[114] = someCodeBuf[20];
        allCodeBuf[115] = someCodeBuf[21];
        allCodeBuf[116] = temp[116];
        allCodeBuf[117] = someCodeBuf[22];// strip name
        allCodeBuf[118] = someCodeBuf[23];
        Integer year = (someCodeBuf[24] & 0xFE) >> 1;
        Integer month = (someCodeBuf[24] & 0x01) * 8 + ((someCodeBuf[25] & 0xE0) >> 5);
        Integer day = (int) (someCodeBuf[25] & 0x1F);
        allCodeBuf[119] = year.byteValue();
        allCodeBuf[120] = month.byteValue();
        allCodeBuf[121] = day.byteValue();
        CrcCheck cc = new CrcCheck(ByteBufferUtil.hexByteToInt(allCodeBuf, 122));
        int chechsum = cc.getCRCValue();

        allCodeBuf[122] = (byte) ((chechsum & 0Xff00) >> 8);
        allCodeBuf[123] = (byte) (chechsum & 0X00ff);

        allCodeBuf[124] = (byte) 0x00;
        allCodeBuf[125] = (byte) 0x01;
        return allCodeBuf;
    }

    /**
     * get codeinfo from current Bg device
     *
     * @hide
     */
    public void getCode() {
        Log.p(TAG, Log.Level.INFO, "getCode");
        byte[] returnCommand = new byte[5];
        byte commandID = (byte) 0x46;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        returnCommand[2] = (byte) 0;
        returnCommand[3] = (byte) 0;
        returnCommand[4] = (byte) 0;
        btcm.packageData(mAddress, returnCommand);
    }

    /**
     * keep in connected
     *
     * @hide
     */
    public void linkHold() {

        Log.p(TAG, Log.Level.INFO, "linkHold");
        byte[] sendCommand = new byte[5];
        sendCommand[0] = (byte) 0xA2;
        sendCommand[1] = (byte) 0x45;
        sendCommand[2] = (byte) 0x00;
        sendCommand[3] = (byte) 0x00;
        sendCommand[4] = (byte) 0x00;
        btcm.packageData(mAddress, sendCommand);
    }

    /**
     * Get battery of the current Bg device.
     *
     * @hide
     */
    public void getBattery() {

        Log.p(TAG, Log.Level.INFO, "getBattery");
        if (mType.equals(iHealthDevicesManager.TYPE_BG5)) {
            try {
                if (iHealthDevicesManager.getInstance().getEE(mAddress).compareTo("600") < 0) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put(Bg5Profile.BATTERY_BG, -1);
                    mInsCallback.onNotify(mAddress, mType, Bg5Profile.ACTION_BATTERY_BG, jsonObject.toString());
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        startTimeout(0x26, AppsDeviceParameters.Delay_Short, 0x26);
        byte[] returnCommand = new byte[5];
        byte commandID = (byte) 0x26;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        returnCommand[2] = 0;
        returnCommand[3] = 0;
        returnCommand[4] = 0;
        btcm.packageData(mAddress, returnCommand);
    }

    private int eeTime = 0;
    private int eeCount = 0;
    private long syncTS = 0;

    @Override
    public void haveNewData(int what, int stateId, byte[] returnData) {
        Log.p(TAG, Log.Level.DEBUG, "haveNewData", Integer.toHexString(what), stateId, ByteBufferUtil.Bytes2HexString(returnData));
        stopTimeout(what);
        JSONObject jsonObject = new JSONObject();
        switch (what) {
            case 0xfb:
                byte[] req = deciphering(returnData, mType, deviceType);
                //协议号相同  使用的key也应该相同
                if (mType.equals(iHealthDevicesManager.TYPE_BG5l)) {
                    req = deciphering(returnData, iHealthDevicesManager.TYPE_BG5, deviceType);
                }
                startTimeout(0xfc, AppsDeviceParameters.Delay_Medium, 0xfd, 0xfe);
                btcm.packageData(null, req);
                break;

            case 0xfd:
                if (AppsDeviceParameters.isLog)
                    Log.p(TAG, Log.Level.DEBUG, "BG5 AUTHOR SUCCESS");
                this.mBaseCommCallback.onConnectionStateChange(mAddress, mType, iHealthDevicesManager.DEVICE_STATE_CONNECTED, 0, null);
                break;

            case 0xfe:
                if (AppsDeviceParameters.isLog)
                    Log.p(TAG, Log.Level.DEBUG, "BG5 AUTHOR FAIL");
                mComm.disconnect();
                break;

            case 0x21:
                syncTS = Method.getTS();
                if (AppsDeviceParameters.isLog)
                    Log.p(TAG, Log.Level.DEBUG, "SET TIME");
                try {
                    jsonObject.put(Bg5Profile.SET_TIME, true);
                    mInsCallback.onNotify(mAddress, mType, Bg5Profile.ACTION_SET_TIME, jsonObject.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case 0x23:
                if (AppsDeviceParameters.isLog)
                    Log.p(TAG, Log.Level.DEBUG, "SET UNIT");
                try {
                    jsonObject.put(Bg5Profile.SET_UNIT, true);
                    mInsCallback.onNotify(mAddress, mType, Bg5Profile.ACTION_SET_UNIT, jsonObject.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case 0x25:
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            if (AppsDeviceParameters.isLog)
                                Log.p(TAG, Log.Level.DEBUG, "SET BOTTLE_MESSAGE_SUCCESS");
                            JSONObject setBottleMessageJsonObject = new JSONObject();
                            setBottleMessageJsonObject.put(Bg5Profile.SET_BOTTLE_MESSAGE, true);
                            mInsCallback.onNotify(mAddress, mType, Bg5Profile.ACTION_SET_BOTTLE_MESSAGE_SUCCESS, setBottleMessageJsonObject.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, 200);

                break;

            case 0x26:
                int batteryLevel = returnData[0] & 0xff;
                //0xff  代表充电中
                if (!((batteryLevel > 0) && (batteryLevel <= 100))) {
                /* if the battery beyond 100, set battery to 255. */
                    batteryLevel = 255;
                }
                if (AppsDeviceParameters.isLog)
                    Log.p(TAG, Log.Level.DEBUG, "GET BATTERY VALUE :" + batteryLevel);

                try {
                    jsonObject.put(Bg5Profile.BATTERY_BG, batteryLevel);
                    mInsCallback.onNotify(mAddress, mType, Bg5Profile.ACTION_BATTERY_BG, jsonObject.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;

            case 0x2c:
                ask((byte) what);
                if (AppsDeviceParameters.isLog)
                    Log.p(TAG, Log.Level.DEBUG, "GET ERROR VALUE :" + returnData[0]);

                try {
                    jsonObject.put(Bg5Profile.ERROR_NUM_BG, (int) (returnData[0] & 0xff));
                    mInsCallback.onNotify(mAddress, mType, Bg5Profile.ACTION_ERROR_BG, jsonObject.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 0x2d:
                try {
                    if (AppsDeviceParameters.isLog)
                        Log.p(TAG, Log.Level.DEBUG, "SET BOTTLE_ID_SUCCESS");
                    jsonObject.put(Bg5Profile.SET_BOTTLE_MESSAGE, true);
                    mInsCallback.onNotify(mAddress, mType, Bg5Profile.ACTION_SET_BOTTLE_ID_SUCCESS, jsonObject.toString());
                    if (QRCode != null && QRCode.length() > 0 && overDate != null && stripNum > 0) {
                        byte year = 18;
                        byte month = 10;
                        byte day = 1;
                        try {
                            year = (byte) Integer.parseInt(overDate.split("-")[0].substring(2));
                            month = (byte) Integer.parseInt(overDate.split("-")[1]);
                            day = (byte) Integer.parseInt(overDate.split("-")[2]);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                        setBottleMessage(QRCode, stripNum, year, month, day);
                    } else if (QRCode != null && QRCode.length() > 0) {
                        setBottleMessage(QRCode);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case 0x2e:
                long value = (long) (returnData[0] & 0xFF) * 256 * 256 * 256
                        + (long) (returnData[1] & 0xFF) * 256 * 256
                        + (long) (returnData[2] & 0xFF) * 256
                        + (long) (returnData[3] & 0xFF);
                if (AppsDeviceParameters.isLog)
                    Log.p(TAG, Log.Level.DEBUG, "GET BOTTLE ID :" + value);

                try {
                    jsonObject.put(Bg5Profile.GET_BOTTLEID, value);
                    mInsCallback.onNotify(mAddress, mType, Bg5Profile.ACTION_GET_BOTTLEID, jsonObject.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case 0x31:
                if (AppsDeviceParameters.isLog)
                    Log.p(TAG, Log.Level.DEBUG, "START MEASURE");

                try {
                    jsonObject.put(Bg5Profile.START_MEASURE, true);
                    mInsCallback.onNotify(mAddress, mType, Bg5Profile.ACTION_START_MEASURE, jsonObject.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case 0x33:
                ask((byte) what);
                if (AppsDeviceParameters.isLog)
                    Log.p(TAG, Log.Level.DEBUG, "STRIP IN");

                mInsCallback.onNotify(mAddress, mType, Bg5Profile.ACTION_STRIP_IN, jsonObject.toString());
                break;

            case 0x34:
                ask((byte) what);
                if (AppsDeviceParameters.isLog)
                    Log.p(TAG, Log.Level.DEBUG, "BLOOD IN");

                mInsCallback.onNotify(mAddress, mType, Bg5Profile.ACTION_GET_BLOOD, jsonObject.toString());
                break;

            case 0x35:
                ask((byte) what);
                if (AppsDeviceParameters.isLog)
                    Log.p(TAG, Log.Level.DEBUG, "STRIP OUT");

                mInsCallback.onNotify(mAddress, mType, Bg5Profile.ACTION_STRIP_OUT, jsonObject.toString());
                break;

            case 0x36:
                ask((byte) what);
                int value36 = (int) (returnData[1] & 0xff) + ((int) (returnData[0] & 0xff)) * 256;
                if (AppsDeviceParameters.isLog)
                    Log.p(TAG, Log.Level.DEBUG, "GET VALUE :" + value36);

                try {
                    jsonObject.put(Bg5Profile.ONLINE_RESULT_BG, value36);
                    jsonObject.put(Bg5Profile.DATA_ID, Method.getBgDataId(mAddress, Method.getTS(), value36));
                    mInsCallback.onNotify(mAddress, mType, Bg5Profile.ACTION_ONLINE_RESULT_BG, jsonObject.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (AppsDeviceParameters.isUpLoadData) {
                    //将结果存数据库
                    Data_BG_Result Bgr = Make_Data_Util.makeDataSingleBg(mUserName, value36, mType, mAddress, UpbottleId);
                    DataBaseTools db = new DataBaseTools(this.mContext);
                    db.addData(DataBaseConstants.TABLE_TB_BGRESULT, Bgr);
                    //开启数据上云
                    BG_InAuthor sdk_InAuthor = BG_InAuthor.getInstance();
                    sdk_InAuthor.initAuthor(mContext, mUserName);
                    sdk_InAuthor.run();
                }

                break;

            case 0x39:
                ask((byte) what);

                break;

            case 0x3a:
                ask((byte) what);
//			startMeasure(1);
//                try {
//                    jsonObject.put(Bg5Profile.START_MODE_EXTRA, (int) returnData[2]);
//                    mInsCallback.onNotify(mAddress, mType, Bg5Profile.ACTION_START_MODE, jsonObject.toString());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                break;

            case 0x40://每包30条数据
                String[] dataOffLine = getBGDataString(returnData);
                if (AppsDeviceParameters.isLog)
                    Log.p(TAG, Log.Level.DEBUG, "process the offline data");

                if (array == null) {
                    array = createStringArrayToJson(dataOffLine);
                } else {
                    array = addStringArrayToJson(array, dataOffLine);
                }
                if (eeTime > 0) {
                    readEE(eeCount);
                    eeTime -= 1;
                    eeCount += 1;
                } else {

                    if (iHealthDevicesManager.getInstance().getEE(mAddress).equals("300")) {

                        new Thread() {
                            public void run() {
                                if (array.length() > 0) {
                                    //start timer to holdlink with BG5 device
                                    startTimer();
                                    array = BgSyncData.processData(mContext, mAddress, array);
                                    closeTimer();
                                }
                                try {
                                    JSONObject object = new JSONObject();
                                    object.put(Bg5Profile.HISTORICAL_DATA_BG, arrayToJson("history", array));
                                    mInsCallback.onNotify(mAddress, mType, Bg5Profile.ACTION_HISTORICAL_DATA_BG, object.toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                array = null;
                                BgSyncData.uploadSyncTime(mContext, mAddress, (syncTS == 0) ? Method.getTS() : syncTS);
                            }
                        }.start();

                    } else {
                        try {
                            jsonObject.put(Bg5Profile.HISTORICAL_DATA_BG, arrayToJson("history", array));
                            mInsCallback.onNotify(mAddress, mType, Bg5Profile.ACTION_HISTORICAL_DATA_BG, jsonObject.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        array = null;
                    }
                }
                break;

            case 0x42:
                int len = (returnData[0] & 0xff) * 256 + (returnData[1] & 0xff);
                //jing 20160923  BG5L每包发送17条记录, BG5每包发送30条记录
                if (mType.equals(iHealthDevicesManager.TYPE_BG5l)) {
                    eeTime = len / 17 + 1;
                } else {
                    eeTime = len / 30 + 1;
                }
                eeCount = 0;
                try {
                    jsonObject.put(Bg5Profile.HISTORICAL_NUM_BG, len);
                    mInsCallback.onNotify(mAddress, mType, Bg5Profile.ACTION_HISTORICAL_NUM_BG, jsonObject.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (eeTime != 0) {
                    readEE(eeCount);
                    eeTime -= 1;
                    eeCount += 1;
                }
                if (AppsDeviceParameters.isLog)
                    Log.p(TAG, Log.Level.DEBUG, "MACHINE OFFLINE DATA COUNT :" + len);

                break;

            case 0x43:
                try {
                    jsonObject.put(Bg5Profile.DELETE_HISTORICAL_DATA, true);
                    mInsCallback.onNotify(mAddress, mType, Bg5Profile.ACTION_DELETE_HISTORICAL_DATA, jsonObject.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (AppsDeviceParameters.isLog)
                    Log.p(TAG, Log.Level.DEBUG, "DELETE ALL MEMORY DATA");

                break;
            case 0x45:
                try {
                    jsonObject.put(Bg5Profile.KEEP_LINK, true);
                    mInsCallback.onNotify(mAddress, mType, Bg5Profile.ACTION_KEEP_LINK, jsonObject.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case 0x46:
                int usenum = (int) (returnData[117] & 0xff);
                int year = 2000 + (int) (returnData[119] & 0xff);
                int month = (int) (returnData[120] & 0xff);
                int day = (int) (returnData[121] & 0xff);
                Calendar time = Calendar.getInstance();
                if (AppsDeviceParameters.isLog)
                    Log.p(TAG, Log.Level.DEBUG, "TIME UPLOAD FROM MACHINE :" + year + "_" + month + "_" + day);

                time.clear();
                time.set(Calendar.YEAR, year);
                time.set(Calendar.MONTH, month - 1);
                time.set(Calendar.DATE, day);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String timestr = df.format(time.getTime());

                if (AppsDeviceParameters.isLog) {
                    Log.p(TAG, Log.Level.DEBUG, "UPLOAD FROM MACHINE CODE :", ByteBufferUtil.Bytes2HexString(returnData));
                    Log.p(TAG, Log.Level.DEBUG, "UPLOAD FROM MACHINE USENUM :", usenum);
                    Log.p(TAG, Log.Level.DEBUG, "UPLOAD FROM MACHINE EXPIRETIME:", timestr);

                }

                try {
                    jsonObject.put(Bg5Profile.GET_USENUM, usenum);
                    jsonObject.put(Bg5Profile.GET_EXPIRECTIME, timestr + "");

                    mInsCallback.onNotify(mAddress, mType, Bg5Profile.ACTION_GET_CODEINFO, jsonObject.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case 0x3f://IDPS

                break;
            case 0xff:
                identify();
                break;

            default:
                if (AppsDeviceParameters.isLog)
                    Log.p(TAG, Log.Level.DEBUG, "UNKNOWN COMMAND");

                break;
        }
    }

    private JSONObject dataJson = null;
    private JSONArray array = null;

    /**
     * @param data
     * @return
     * @hide
     */
    private String[] getBGDataString(byte[] data) {

        int len = (int) (data[0] & 0xFF);//数据的个数
        byte[] alltemp = new byte[data.length - 1];
        for (int i = 1; i < data.length; i++) {
            alltemp[i - 1] = data[i];//纯数据数组
        }
        String[] dataOffLine = new String[len * 2];
        //		ArrayList<BGData> list = new ArrayList<BGData>();
        int j = 0;
        for (int i = 0; i < len; i++) {

            String year = String.valueOf((int) (alltemp[0 + j] & 0xFF) + 2000);
            String month = null;
            if ((int) (alltemp[1 + j] & 0xFF) < 10) {
                month = "0" + String.valueOf((int) (alltemp[1 + j] & 0xFF));
            } else {
                month = String.valueOf((int) (alltemp[1 + j] & 0xFF));
            }
            String day = null;
            if ((int) (alltemp[2 + j] & 0xFF) < 10) {
                day = "0" + String.valueOf((int) (alltemp[2 + j] & 0xFF));
            } else {
                day = String.valueOf((int) (alltemp[2 + j] & 0xFF));
            }
            String hour = null;
            if ((int) (alltemp[3 + j] & 0xFF) < 10) {
                hour = "0" + String.valueOf((int) (alltemp[3 + j] & 0xFF));
            } else {
                hour = String.valueOf((int) (alltemp[3 + j] & 0xFF));
            }
            String min = null;
            if ((int) (alltemp[4 + j] & 0xFF) < 10) {
                min = "0" + String.valueOf((int) (alltemp[4 + j] & 0xFF));
            } else {
                min = String.valueOf((int) (alltemp[4 + j] & 0xFF));
            }
            String str;
            if (i < 10) {
                str = "0" + i;
            } else {
                str = "" + i;
            }
            String date = year + "-" + month + "-" + day + " " + hour + ":"
                    + min + ":" + str;
//			//按照协议
//			String date = year + "-" + month + "-" + day + " " + hour + ":"
//					+ min+":"+"00";
            int value = (int) (alltemp[5 + j] & 0xFF) * 256
                    + (int) (alltemp[6 + j] & 0xFF);
            dataOffLine[2 * i] = date;
            dataOffLine[2 * i + 1] = value + "";
            j += 8;

        }
        return dataOffLine;
    }

    /**
     * @param arr
     * @return
     * @hide
     */
    private JSONArray createStringArrayToJson(String[] arr) {
        ArrayList<BGData> list = new ArrayList<BGData>();
        for (int i = 0; i < (arr.length / 2); i++) {
            BGData data = new BGData();
            data.setBGData_Date(arr[2 * i]);
            data.setBGData_Value(Integer.parseInt(arr[2 * i + 1]));
            list.add(data);
        }
        try {
            JSONArray array = new JSONArray();
            int length = list.size();
            for (int i = 0; i < length; i++) {
                BGData data = list.get(i);
                String date = data.getBGData_Date();
                int value = data.getBGData_Value();
                JSONObject stoneObject = new JSONObject();
                stoneObject.put("date", date);
                stoneObject.put("value", value);
                stoneObject.put(Bg5Profile.DATA_ID, Method.getBgDataId(mAddress, Method.String2TS(date), Integer.valueOf(value)));
                array.put(stoneObject);
            }
            return array;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param dataJson
     * @param arr
     * @return
     * @hide
     */
    private JSONArray addStringArrayToJson(JSONArray dataJson, String[] arr) {
        ArrayList<BGData> list = new ArrayList<BGData>();
        for (int i = 0; i < (arr.length / 2); i++) {
            BGData data = new BGData();
            data.setBGData_Date(arr[2 * i]);
            data.setBGData_Value(Integer.parseInt(arr[2 * i + 1]));
            list.add(data);
        }

        try {
            int length = list.size();
            for (int i = 0; i < length; i++) {
                BGData data = list.get(i);
                String date = data.getBGData_Date();
                int value = data.getBGData_Value();
                JSONObject stoneObject = new JSONObject();
                stoneObject.put("date", date);
                stoneObject.put("value", value);
                stoneObject.put(Bg5Profile.DATA_ID, Method.getBgDataId(mAddress, Method.String2TS(date), Integer.valueOf(value)));
                dataJson.put(stoneObject);
            }
            return dataJson;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param objName
     * @param array
     * @return
     * @hide
     */
    public static JSONObject arrayToJson(String objName, JSONArray array) {
        JSONObject object = new JSONObject();
        try {
            object.put(objName, array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public class BGData {

        String BGData_Date;
        int BGData_Value;

        public BGData() {
            super();
        }

        public BGData(String bGData_Date, int bGData_Value) {
            super();
            BGData_Date = bGData_Date;
            BGData_Value = bGData_Value;
        }

        public String getBGData_Date() {
            return BGData_Date;
        }

        public int getBGData_Value() {
            return BGData_Value;
        }

        public void setBGData_Date(String bGData_Date) {
            BGData_Date = bGData_Date;
        }

        public void setBGData_Value(int bGData_Value) {
            BGData_Value = bGData_Value;
        }
    }

    @Override
    public void haveNewDataUuid(String uuid, byte[] command) {

    }

    public void destroy() {
        mBaseCommCallback = null;
        mInsCallback = null;
        mContext = null;
        if (btcm != null) {
            btcm.destroy();
        }
        btcm = null;
        closeTimer();
    }

    private Timer mTimer;
    private TimerTask mTimerTask;

    private void startTimer() {
        closeTimer();
        mTimer = new Timer();
        mTimerTask = new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stubf
                linkHold();
            }
        };
        mTimer.schedule(mTimerTask, 5000, 5000);
    }

    private void closeTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }
}
