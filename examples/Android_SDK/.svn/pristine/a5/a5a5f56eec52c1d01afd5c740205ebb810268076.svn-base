/**
 * @title
 * @Description
 * @author
 * @date 2015年3月26日 上午11:45:13 
 * @version V1.0  
 */

package com.ihealth.communication.ins;

import com.ihealth.communication.utils.ByteBufferUtil;

import android.R.integer;
import com.ihealth.communication.utils.Log;

import java.util.Arrays;

/**
 * @ClassName: UserListInHs5
 * @Description: TODO
 * @author gaonana
 * @date 2015年3月26日 上午11:45:13
 */
public class UserListInHs5 {
    private static final String TAG = "UserListInHs5----";
    private static final String TAG1 = "HS5Wifi";
    boolean isDebug = true;
    private int userInList = 0;// 用户在HS5中的位置 //从1到20
    private int fristFreeInScale = 0;// HS5中第一个空位置
    private int userID_first = 0;// HS5第一个用户的userid（如果称满了，需要把第一个用户替换掉，根据协议，需要得到该用户ID）
    private String[] userList = new String[20]; // 20个用户
    private int[] userIds = new int[20]; // 0-19
    private int[] states = new int[20]; // 0代表该位置没有用户，1代表该位置有用户 ,2代表该位置是当前用户

    public int[] getUserIds() {
        return this.userIds;
    }

    public int[] getStates() {
        return this.states;
    }

    public void setUserInlist(int userInList) {
        this.userInList = userInList;
    }

    /**
     * 返回用户在称里的位置 范围0到19
     */
    public int getUserInList() {
        return this.userInList;
    }

    public void setFristFreeInScale(int fristFreeInScale) {
        this.fristFreeInScale = fristFreeInScale;
    }

    public int getFristFreeInScale() {
        return this.fristFreeInScale;
    }

    public void setUserID_first(int userID_first) {
        this.userID_first = userID_first;
    }

    public int getUserID_first() {
        return this.userID_first;
    }

    public void setUserList(String[] userList) {
        this.userList = userList;
    }

    public String[] getUserList() {
        return this.userList;
    }

    public void checkUserInHs5(int userId, String userListData) {
        userInList = 0;
        fristFreeInScale = 0;
        // Log.e(TAG1, TAG + "开始查找用户是否在HS5中");
        // Log.e(TAG1, TAG + "userListData=" + userListData);

        for (int i = 0; i < 20; i++) {
            String user = userListData.substring(i * 8, i * 8 + 8);
            byte[] userbyte = ByteBufferUtil.hexStringToByte(user);
            userIds[i] = (int) (userbyte[0] & 0xff) * 256 * 256 * 256
                    + (int) (userbyte[1] & 0xff) * 256 * 256
                    + (int) (userbyte[2] & 0xff) * 256
                    + (int) (userbyte[3] & 0xff);

            if (i == 0) {
                userID_first = userIds[i];
            }
            userList[i] = user;
            if (user.equals("FFFFFFFF")) { // 该位置没有用户
                states[i] = 0;
            } else {
                states[i] = 1;
            }
            if (ByteBufferUtil.decimalismToHexadecimal(userId).equals(user)) {
                userInList = i + 1;
                if (isDebug)
                    Log.e(TAG1, TAG + "找到用户了" + userInList);
                states[i] = 2;

            }
            if (user.equals("FFFFFFFF") && fristFreeInScale == 0) {
                fristFreeInScale = i + 1;
            }

        }
        if (isDebug) {
            Log.d(TAG1, TAG + "userId=" + userId);
            Log.i(TAG1, TAG + "userInList=" + userInList);
            Log.i(TAG1, TAG + "fristFreeInScale=" + fristFreeInScale);
            Log.i(TAG1, TAG + "userID_first=" + userID_first);
        }
    }

    @Override
    public String toString() {
        return "UserListInHs5{" +
                "userInList=" + userInList +
                ", fristFreeInScale=" + fristFreeInScale +
                ", userID_first=" + userID_first +
                ", userList=" + Arrays.toString(userList) +
                ", userIds=" + Arrays.toString(userIds) +
                ", states=" + Arrays.toString(states) +
                '}';
    }
}
