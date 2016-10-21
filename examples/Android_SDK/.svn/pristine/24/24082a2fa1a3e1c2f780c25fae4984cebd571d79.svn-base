
package com.ihealth.communication.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.TimeZone;

import android.annotation.SuppressLint;
import com.ihealth.communication.utils.Log;

public class ByteBufferUtil {

    public static int[] hexByteToInt(byte[] bt, int len) {
        int[] in = new int[len];
        for (int i = 0; i < len; i++) {
            in[i] = (int) bt[i];
            if (in[i] < 0) {
                in[i] = 256 + in[i];
            }
        }
        return in;
    }

    // 最后一位校验和不要
    public static byte[] bytesCuttForProductProtocol(int start, byte[] data) {
        int len = data.length - start - 1;
        byte[] dataR = new byte[len];
        for (int i = 0; i < dataR.length; i++) {
            dataR[i] = data[i + start];
        }
        return dataR;
    }

    public static byte[] intToByteForuserId(int userId) {
        byte userNum[] = new byte[] {
                (byte) ((userId & 0xFF000000) >> 24),
                (byte) ((userId & 0x00FF0000) >> 16),
                (byte) ((userId & 0x0000FF00) >> 8),
                (byte) ((userId & 0x000000FF))
        };
        return userNum;
    }

    public static byte[] bytesCutt(int start, int stop, byte[] data) {
        int len = stop - start + 1;
        byte[] dataR = new byte[len];
        for (int i = 0; i < dataR.length; i++) {
            dataR[i] = data[start + i];
        }
        return dataR;
    }

    /**
     * 字符数组复制
     * 
     * @param src 复制源
     * @param srcBegin 复制起始点（含）
     * @param length 复制数据的长度
     * @param dst 复制目的地
     * @param dstBegin 复制目的地起始点（含）
     * @return dst中下一位置（不含）
     */
    public static int ByteBufferCopy(byte[] src, int srcBegin, int length,
            byte[] dst, int dstBegin) {
        int i = 0;
        for (i = srcBegin; i < srcBegin + length; i++)
        {
            dst[dstBegin + i] = src[i];
        }
        return i + dstBegin;
    }

    public static String BufferCut(byte[] buffer, int begin, int length) {
        byte[] temp = new byte[length];
        for (int i = begin; i < begin + length; i++) {
            temp[i - begin] = buffer[i];
        }
        return Bytes2HexString(temp, length);
    }

    public static byte[] bufferCut(byte[] buffer, int begin, int length) {
        byte[] temp = new byte[length];
        for (int i = begin; i < begin + length; i++) {
            temp[i - begin] = buffer[i];
        }
        return temp;
    }

    static final char digits[] = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };

    /**
     * @Description: TODO 整型转换成HexString
     */
    public static String decimalismToHexadecimal(int Num) {
        int length = 8;
        String num = "";
        char[] result = {
                '0', '0', '0', '0', '0', '0', '0', '0'
        };
        do {
            result[--length] = digits[Num & 15];
            Num >>>= 4;
        } while (Num != 0);
        for (int i = 0; i < result.length; i++) {
            num += result[i];
        }
        return num;
    }

    // public static byte[] bytesCutt(int start, int stop, byte[] data) {
    // int len = stop - start + 1;
    // byte[] dataR = new byte[len];
    // for (int i = 0; i < dataR.length; i++) {
    // dataR[i] = data[start + i];
    // }
    // return dataR;
    // }

    public static byte[] BufferMerger(byte[] buffer1, byte[] buffer2) {
        byte[] temp = null;
        if (buffer1 == null) {
            return buffer2;
        } else {
            temp = new byte[buffer1.length + buffer2.length];
            for (int i = 0; i < buffer1.length; i++) {
                temp[i] = buffer1[i];
            }
            for (int i = 0; i < buffer2.length; i++) {
                temp[buffer1.length + i] = buffer2[i];
            }
            return temp;
        }
    }

    public static byte[] BufferMergerEx(byte[] buffer1, byte[] buffer2, int start, int stop) {
        byte[] temp = null;
        if (buffer1 == null) {
            return buffer2;
        } else {
            temp = new byte[buffer1.length + (stop - start)];
            for (int i = 0; i < buffer1.length; i++) {
                temp[i] = buffer1[i];
            }
            for (int i = start; i < stop; i++) {
                temp[buffer1.length + (i - 1)] = buffer2[i];
            }
            return temp;
        }
    }

    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    /**
     * bytes字符串转换为Byte值
     * 
     * @param String src Byte字符串，每个Byte之间没有分隔符
     * @return byte[]
     */
    public static byte[] hexStr2Bytes(String src)
    {
        int m = 0, n = 0;
        int l = src.length() / 2;
        System.out.println(l);
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++)
        {
            m = i * 2 + 1;
            n = m + 1;
            ret[i] = Byte.decode("0x" + src.substring(i * 2, m) + src.substring(m, n));
        }
        return ret;
    }

    private static byte toByte(char cr) {
        byte b = (byte) "0123456789ABCDEF".indexOf(cr);
        return b;
    }

    @SuppressLint("DefaultLocale")
    public static String mac2Address(byte[] b) {
        String ret = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
            if (i != b.length - 1) {
                ret += ":";
            }
        }
        return ret;
    }

    @SuppressLint("DefaultLocale")
    public static String Bytes2HexString(byte[] b) {
        String ret = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }

    @SuppressLint("DefaultLocale")
    public static String Bytes2HexString(byte[] b, int start, int len) {
        String ret = "";
        for (int i = start; i < len; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }

    // public static int byteToInt(byte[] bs) {
    // int req = 0;
    // for (int i = 0; i < bs.length; i++) {
    // req += (bs[i] << (bs.length - i - 1) * 8);
    // }
    // return req;
    // }

    public static int byte2ToInt(byte[] res) {
        int targets = 0;
        targets = (res[1] & 0xff) | ((res[0] << 8) & 0xff00);
        return targets;
    }

    public static int byteToInt(byte[] res) {
        // 一个byte数据左移24位变成0x00000000，再右移8位变成0x00000000
        int targets = 0;
        if (res.length == 2) {
            targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00); // | 表示安位或
        } else {
            targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00) // | 表示安位或
                    | (((res[2] << 24) >>> 8)) | ((res[3] << 24));
        }
        return targets;
    }

    public static int byteToUserId(byte[] res) {
        int userId = 0;
        int userId1 = (res[0] & 0xff) * 256 * 256 * 256;
        int userId2 = (res[1] & 0xff) * 256 * 256;
        int userId3 = (res[2] & 0xff) * 256;
        int userId4 = res[3] & 0xff;
        userId = userId1 + userId2 + userId3 + userId4;
        return userId;
    }

    public static byte[] intToUserId(int userId) {
        byte[] userIds = new byte[4];
        userIds[0] = (byte) ((userId & 0xFF000000) >> 24);
        userIds[1] = (byte) ((userId & 0xFF0000) >> 16);
        userIds[2] = (byte) ((userId & 0xFF00) >> 8);
        userIds[3] = (byte) (userId & 0xFF);
        return userIds;
    }

    public static byte[] intTo2Byte(int res) {
        byte[] targets = new byte[2];
        targets[1] = (byte) (res & 0xff);// 最低位
        targets[0] = (byte) ((res >> 8) & 0xff);// 次低位
        return targets;
    }

    // public static byte[] intTo4Byte(int n) {
    // byte[] b = new byte[4];
    // for (int i = 0; i < 4; i++) {
    // b[i] = (byte) (n >> ((3 - i) * 8));
    // }
    // return b;
    // }

    public static byte[] intTo4Byte(long res) {
        byte[] targets = new byte[4];
        targets[3] = (byte) (res & 0xff);// 最低位
        targets[2] = (byte) ((res >> 8) & 0xff);// 次低位
        targets[1] = (byte) ((res >> 16) & 0xff);// 次高位
        targets[0] = (byte) (res >>> 24);// 最高位,无符号右移。
        return targets;
    }

    public static byte[] rejectBuffer(byte[] data) {
        byte[] tempData = new byte[data.length - 4];
        for (int i = 0; i < tempData.length; i++) {
            tempData[i] = data[i];
        }
        return tempData;
    }

    @SuppressLint("DefaultLocale")
    public static String Bytes2HexString(byte[] b, int len) {
        String ret = "";
        for (int i = 0; i < len; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }

    public static boolean bytesEqual(byte[] array1, byte[] array2) {
        if (array1.length != array2.length) {
            return false;
        }
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }

    public int[] StringtoInts(String str) {

        int ret[] = new int[str.length()];

        return ret;

    }

    // 时间数据处理 精确到秒
    public static long String2TS(String dateStr) {
        long ret = -1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = sdf.parse(dateStr);
            ret = date.getTime();
            ret = ret / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ret;
    }
    /**
     * 生日格式转换成可以变成long的格式 2010-3-3-------20100303
     */
    public static long BirthdayToLong(String Birthday) {

        long BirthLong = 0;

        SimpleDateFormat sdfResouce = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdfResouce.setTimeZone(TimeZone.getDefault());

        Date fromData = new Date();
        try {
            fromData = sdfResouce.parse(Birthday + " 00:00:00");
            BirthLong = fromData.getTime() / 1000;
        } catch (Exception e) {
            Log.w("ByteBufferUtil", "getDefaultTimerStr Exception ");
            e.printStackTrace();
        }

        return BirthLong;
    }
    public static String TS2String(long TS) {
        String time = "";
        Date date = new Date();
        date.setTime(TS * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        time = sdf.format(date);
        return time;
    }
}
