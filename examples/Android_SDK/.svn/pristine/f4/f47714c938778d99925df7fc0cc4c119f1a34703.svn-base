package com.ihealth.communication.utils;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jing on 16/10/9.
 */

public class Logger {

    static private String TAG = "Logger";
    static private boolean clearOutDateLogFlag = false;
    static private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    static private SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static private String logName = "_SDK_Debug.txt";
    static private String sdCardPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
    static private int validLog = 7;

    public static void v(String tag, String msg) {
        Log.v(tag, msg);
        commandHandleLog("V", tag, msg);
    }

    public static void d(String tag, String msg) {
        Log.d(tag, msg);
        commandHandleLog("D", tag, msg);
    }


    public static void i(String tag, String msg) {
        Log.i(tag, msg);
        commandHandleLog("I", tag, msg);
    }

    public static void w(String tag, String msg) {
        Log.w(tag, msg);
        commandHandleLog("W", tag, msg);
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
        commandHandleLog("E", tag, msg);
    }

    /**
     * 处理Log
     * @param level
     * @param tag
     * @param info
     */
    private static void commandHandleLog(String level, String tag, String info) {

        Date currentDate  = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        //保留7天内的Log
        calendar.add(Calendar.DATE, 0-validLog);

        String outDateStr = dateFormatter.format(calendar.getTime());
        String currentTimeStr = timeFormatter.format(currentDate);
        String currentDateStr = dateFormatter.format(currentDate);

        //Clear
        if (clearOutDateLogFlag == false) {
            clearOutDateLogFlag = true;
            commandClearOutDateLog(outDateStr);
        }

        //Save
        commandSaveLog(sdCardPath + currentDateStr + logName, currentTimeStr, level, tag, info);

    }

    /**
     * 清除7天之前的文件
     * @param outDateStr
     */
    private static void commandClearOutDateLog(String outDateStr) {
        String outDateFileName = outDateStr + logName;
        File rootFile = new File(sdCardPath);
        File[] files = rootFile.listFiles();
        for(int i=files.length-1; i>=0; i--) {
            String fileName = files[i].getName();
            if (fileName.endsWith(logName) && (fileName.compareTo(outDateFileName)<0)) {
                files[i].delete();
            }
        }
    }

    /**
     * 存储Log
     * @param time
     * @param level
     * @param tag
     * @param info
     */
    private static void commandSaveLog(String fileName, String time, String level, String tag, String info) {
        File file = new File(fileName);

        try {
            FileWriter filerWriter = new FileWriter(file, true);
            BufferedWriter bufWriter = new BufferedWriter(filerWriter);
            String logInfo = time + "  " + level + "/" + tag + ":  " + info;
            bufWriter.write(logInfo);
            bufWriter.newLine();
            bufWriter.close();
            filerWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
