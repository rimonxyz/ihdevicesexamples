
package com.ihealth.communication.cloud.data;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ihealth.communication.cloud.tools.Method;
import com.ihealth.communication.utils.PublicMethod;

public class Make_Data_Util {

    /**
     * 运动5分钟数据
     * 
     * @param userName
     * @param mac
     * @param type
     * @param result
     * @param calorie
     * @param steps
     * @param stepLen
     * @return
     */
    public static Data_AM_Activity makeDataSingleAMA(String userName, String mac, String type, ActivityData result,
            int calorie, int steps, int stepLen) {
        // TODO Auto-generated method stub
        // TS
        long ts = Method.getTS();
        // 赋值
        Data_AM_Activity ob = new Data_AM_Activity();

        ob.setChangeType(1);
        ob.setLastChangeTime(ts);
        ob.setPhoneDataID(PublicMethod.getAMDataID(mac, result.getSteps()+"", PublicMethod.String2TS(result.getTime())));
        ob.setPhoneCreateTime(ts);
        ob.setLat(0.0);
        ob.setLon(0.0);
        ob.setTimeZone(Method.getTimeZone());

        ob.setStepLength(stepLen);
        ob.setCalorie(calorie);
        ob.setSteps(steps);
        ob.setSumCalorie(result.getCalorie());
        ob.setSumSteps(result.getSteps());

        ob.setMeasureTime(Method.String2TS(result.getTime()));
        ob.setMechineType(type);
        ob.setMechineDeviceID(mac);

        ob.setiHealthID(userName);

        return ob;
    }

    /**
     * 睡眠5分钟数据
     * 
     * @param userName
     * @param mac
     * @param type
     * @param sleepLevel
     * @param timeSectionId
     * @param measureTime
     * @return
     */
    public static Data_AM_Sleep makeDataSingleAMS(String userName, String mac, String type, int sleepLevel,
            String timeSectionId, String measureTime) {
        // TODO Auto-generated method stub
        // TS
        long ts = Method.getTS();
        // 赋值
        Data_AM_Sleep ob = new Data_AM_Sleep();

        ob.setChangeType(1);
        ob.setLastChangeTime(ts);
        ob.setPhoneDataID(PublicMethod.getAMDataID(mac, sleepLevel + "", PublicMethod.String2TS(measureTime)));
        ob.setPhoneCreateTime(ts);
        ob.setLat(0.0);
        ob.setLon(0.0);
        ob.setTimeZone(Method.getTimeZone());

        ob.setSleepLevel(sleepLevel);
        ob.setTimeSectionId(timeSectionId);

        ob.setMeasureTime(Method.String2TS(measureTime));
        ob.setMechineType(type);
        ob.setMechineDeviceID(mac);

        ob.setiHealthID(userName);

        return ob;
    }

    /**
     * 添加数据库--运动日报表
     * 
     * @param userName
     * @param mac
     * @param type
     * @param calorie
     * @param steps
     * @param stepLen
     * @param TS
     * @return
     */
    public static Data_AM_ActivityDayReport makeDataSingleAMADR(String userName, String mac, String type, int calorie,
            int steps, int stepLen, long TS) {
        // TODO Auto-generated method stub
        // TS
        long ts = Method.getTS();
        // 赋值
        Data_AM_ActivityDayReport ob = new Data_AM_ActivityDayReport();

        ob.setChangeType(1);
        ob.setLastChangeTime(ts);
        ob.setPhoneDataID(ts + mac + steps);
        ob.setPhoneCreateTime(ts);
        ob.setLat(0.0);
        ob.setLon(0.0);
        ob.setTimeZone(Method.getTimeZone());

        ob.setStepLength(stepLen);
        ob.setCalorie(calorie);
        ob.setSteps(steps);
        ob.setPlanSteps(10000);
        ob.setPlanCalorie(50);

        ob.setCity("");
        ob.setWeather("");
        ob.setComment("");

        ob.setMeasureTime(TS);
        ob.setMechineType(type);
        ob.setMechineDeviceID(mac);

        ob.setiHealthID(userName);

        return ob;
    }

    /**
     * 添加数据库--睡眠段报表
     * 
     * @param userName
     * @param mac
     * @param type
     * @param awake
     * @param deepSleep
     * @param fallSleep
     * @param sleep
     * @param awakenTime
     * @param startTime
     * @param endTime
     * @param timeSectionId
     * @return
     */
    public static Data_AM_SleepSectionReport makeDataSingleAMSSR(String userName, String mac, String type, int awake,
            int deepSleep, int fallSleep, int sleep, int awakenTime, String startTime, String endTime,
            String timeSectionId) {
        // TODO Auto-generated method stub
        // TS
        long ts = Method.getTS();
        // 赋值
        Data_AM_SleepSectionReport ob = new Data_AM_SleepSectionReport();

        ob.setChangeType(1);
        ob.setLastChangeTime(ts);
        ob.setPhoneDataID(ts + mac + sleep + "");
        ob.setPhoneCreateTime(ts);
        ob.setLat(0.0);
        ob.setLon(0.0);
        ob.setTimeZone(Method.getTimeZone());

        ob.setAwake(awake);
        ob.setDeepSleep(deepSleep);
        ob.setFallSleep(fallSleep);
        ob.setSleep(sleep);
        ob.setAwakenTimes(awakenTime);
        ob.setSleepStartTime(Method.String2TS(startTime));
        ob.setSleepEndTime(Method.String2TS(endTime));
        ob.setTimeSectionId(timeSectionId);

        ob.setCity("");
        ob.setWeather("");
        ob.setComment("");

        ob.setNap(0);
        ob.setMood(1);
        ob.setActivity(2);

        ob.setMeasureTime(Method.String2TS(startTime));
        ob.setMechineType(type);
        ob.setMechineDeviceID(mac);

        ob.setiHealthID(userName);

        return ob;
    }

    public static Data_TB_Workout makeDataSingleAMWorkOut(String dataID, int workout_SpendMinutes, int workout_Steps, int workout_Distance, float workout_Calories,
                                                            String workout_MechineType, String workout_MechineDeviceID, String workout_iHealthCloud) {
        // TODO Auto-generated method stub
        // TS
        long ts = Method.getTS();
        // 赋值
        Data_TB_Workout ob = new Data_TB_Workout();

        ob.setWorkout_ChangeType(1);
        ob.setWorkout_LastChangeTime(ts);
        ob.setWorkout_PhoneDataID(dataID);
        ob.setWorkout_PhoneCreateTime(ts);
        ob.setWorkout_Lat(0);
        ob.setWorkout_Lon(0);
        ob.setWorkout_TimeZone(Method.getTimeZone());

        ob.setWorkout_SpendMinutes(workout_SpendMinutes);
        ob.setWorkout_Steps(workout_Steps);
        ob.setWorkout_Distance(workout_Distance);
        ob.setWorkout_Calories(workout_Calories);

        ob.setWorkout_MechineType(workout_MechineType);
        ob.setWorkout_MechineDeviceID(workout_MechineDeviceID);

        ob.setWorkout_iHealthCloud(workout_iHealthCloud);

        return ob;
    }
    //游泳的数据直接使用数据类的构造函数即可
    public static Data_TB_Swim makeDataSingleAMSwim (String userName, String dataID, String mac, String type, float calories, int pullTimes, int cycles,
                                                     int storke, int distance, long endTime, int spendTimes, int cutIn, int cutOut, int flag) {
        long ts = Method.getTS();
        Data_TB_Swim swim = new Data_TB_Swim();

        swim.setSwim_ChangeType(1);
        swim.setSwim_PhoneDataID(dataID);
        swim.setSwim_LastChangeTime(ts);
        swim.setSwim_PhoneCreateTime(ts);
        swim.setSwim_Lat(0);
        swim.setSwim_Lon(0);
        swim.setSwim_TimeZone(Method.getTimeZone());

        swim.setSwim_Cycles(cycles);
        swim.setSwim_SpendMinutes(spendTimes);
        swim.setSwim_Calories(calories);
        swim.setSwim_Distance(distance);
        swim.setSwim_Storke(storke);
        swim.setSwim_PullTimes(pullTimes);
        swim.setSwim_endtime(endTime);

        swim.setSwim_CutInTimeDif(cutIn);
        swim.setSwim_CutOutTimeDif(cutOut);
        swim.setSwim_ProcessFlag(flag);

        swim.setSwim_MechineType(type);
        swim.setSwim_MechineDeviceID(mac);

        swim.setSwim_iHealthCloud(userName);
        return swim;
    }

    public static Data_TB_SwimSection makeDataSingleAMSwimSection (String userName, String dataID, String mac, String type, Data_TB_SwimSection swimSection) {
        long ts = Method.getTS();

        swimSection.setSwimSection_DeviceID(mac);
        swimSection.setSwimSection_DataID(dataID);
        swimSection.setSwimSection_DeviceSource(type);
        swimSection.setSwimSection_iHealthCloud(userName);
        swimSection.setSwimSection_LastChangeTime(ts);
        swimSection.setSwimSection_Lat(0);
        swimSection.setSwimSection_Lon(0);
        swimSection.setSwimSection_NoteTS(ts);
        swimSection.setSwimSection_TimeZone(Method.getTimeZone());
//        swimSection.setSwimSection_Weather();
//        swimSection.setSwimSection_Note();

        return swimSection;
    }

    /**
     * 生成BG数据表
     * 
     * @param userName
     * @param result
     * @param type BG1 BG5
     * @param deviceId Mac
     * @param bottleId
     * @return
     */
    public static Data_BG_Result makeDataSingleBg(String userName, int result, String type, String deviceId,
            long bottleId) {
        // TODO Auto-generated method stub
        // TS
        long ts = Method.getTS();
        // MTimeType
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String datestr = sdf.format(date);
        int hour = Integer.parseInt(datestr.split(" ")[1].split(":")[0]);
        // 赋值
        Data_BG_Result BGOb = new Data_BG_Result();
        BGOb.setChangeType(1);
        BGOb.setLastChangeTime(ts);
        BGOb.setPhoneDataID(Method.getBgDataId(deviceId, ts, result));
        BGOb.setPhoneCreateTime(ts);
        BGOb.setLat((float) 0.0);
        BGOb.setLon((float) 0.0);
        BGOb.setTimeZone(Method.getTimeZone());
        BGOb.setBGValue(result);
        BGOb.setMedication(0);
        if (hour >= 4 & hour < 7) {
            BGOb.setMTimeType(1);
        } else if (hour >= 7 & hour < 10) {
            BGOb.setMTimeType(1);
        } else if (hour >= 10 & hour < 13) {
            BGOb.setMTimeType(3);
        } else if (hour >= 13 & hour < 16) {
            BGOb.setMTimeType(3);
        } else if (hour >= 16 & hour < 19) {
            BGOb.setMTimeType(5);
        } else if (hour >= 19 & hour < 22) {
            BGOb.setMTimeType(5);
        } else {
            BGOb.setMTimeType(7);
        }
        BGOb.setMeasureType(0);
        BGOb.setSnacks(1);
        BGOb.setSports(1);
        BGOb.setMeasureTime(System.currentTimeMillis() / 1000);
        BGOb.setNote("");
        BGOb.setMechineType(type);
        BGOb.setMechineDeviceID(deviceId);
        BGOb.setEffective(1);
        BGOb.setBottleId(bottleId + "");
        BGOb.setiHealthID(userName);

        return BGOb;
    }

    /**
     * 生成BP数据表
     * 
     * @param userName
     * @param Hp
     * @param Lp
     * @param HR
     * @param type BP3 BP5 BP7...
     * @param mac
     * @return
     */
    public static Data_BP_Result makeDataSingleBp(String userName, int Hp, int Lp, int HR, String type, String mac, long TS) {
        // TODO Auto-generated method stub
        // TS
        long ts = Method.getTS();
        // 赋值
        Data_BP_Result asd = new Data_BP_Result();

        asd.setChangeType(1);
        asd.setLastChangeTime(ts);
        asd.setPhoneCreateTime(ts);
        asd.setPhoneDataID(PublicMethod.getBPDataID(mac, HR+"", TS));
        asd.setLat((float) 0.0);
        asd.setLon((float) 0.0);
        asd.setTimeZone(Method.getTimeZone());
        asd.setBPL(1);
        asd.setHP(Hp);
        asd.setHR(HR);
        asd.setLP(Lp);
        asd.setIsArr(0);
        asd.setMeasureType(0);
        asd.setMeasureTime(ts);
        asd.setNote("");
        asd.setNoteTS(ts);
        asd.setMechineType(type);
        asd.setMechineDeviceID(mac);
        asd.setWL("");
        asd.setiHealthCloud(userName);// 数据类中的iHealthCloud=配置中的un=数据库中的pbun字段
        // 5.0云协议新增
        asd.setBpMood(1);// 心情 1~6等级
        asd.setBpActivity(1);// 活动 1~5等级
        asd.setTemp("");
        asd.setWeather("");
        asd.setHumidity("");
        asd.setVisibility("");
        asd.setUsedUserid(0);

        return asd;
    }

    /**
     * 生成HS数据表
     * 
     * @param userName
     * @param weightR, fat, water, muscle, skeleton, vFatLevel, DCI
     * @param type HS3 HS4 HS5 HS6....
     * @param mac
     * @return
     */
    public static Data_HS_Result makeDataSingleHs(String dataId,String userName, float weightR, float fat, float water, float muscle,
            float skeleton, int vFatLevel, float DCI, String type, String mac) {
        // TODO Auto-generated method stub
        // TS
        long ts = Method.getTS();
        // 赋值
        Data_HS_Result HSOb = new Data_HS_Result();

        HSOb.setChangeType(1);
        HSOb.setLastChangeTime(ts);
        HSOb.setPhoneDataID(dataId);
        HSOb.setPhoneCreateTime(ts);

        HSOb.setLat(0.0);
        HSOb.setLon(0.0);
        HSOb.setTimeZone(Method.getTimeZone());
        HSOb.setBMI(0);

        HSOb.setBoneValue(skeleton);
        HSOb.setDCI(DCI);
        HSOb.setFatValue(fat);
        HSOb.setWaterValue(water);
        HSOb.setMuscaleValue(muscle);

        HSOb.setMeasureType(1);
        HSOb.setWeightValue(weightR);
        HSOb.setMeasureTime(ts);

        HSOb.setNote("");
        HSOb.setVisceraFatLevel(vFatLevel);
        HSOb.setMechineType(type);
        HSOb.setMechineDeviceID(mac);
        HSOb.setiHealthID(userName);
        HSOb.setEmotions(1);

        HSOb.setNoteTS(ts);
        HSOb.setMood(1);
        HSOb.setActivity(1);
        HSOb.setTemp("");
        HSOb.setWeather("");
        HSOb.setHumidity("");
        HSOb.setVisibility("");
        HSOb.setUsedUserid(0);

        return HSOb;
    }

    /**
     * 生成PO数据表
     * 
     * @param userName
     * @param PR 心律
     * @param Result 血氧
     * @param Flowrate 吸氧量
     * @param mac
     * @return
     */
    public static Data_PO_Result makeDataSinglePo(String dataId,String userName, int PR, int Result, int Flowrate, String mac,String wave) {
        // TODO Auto-generated method stub
        // TS
        long ts = Method.getTS();
        // 赋值
        Data_PO_Result POOb = new Data_PO_Result();

        POOb.setChangeType(1);
        POOb.setLastChangeTime(ts);
        POOb.setPhoneDataID(dataId);
        POOb.setPhoneCreateTime(ts);

        POOb.setLat(0.0);
        POOb.setLon(0.0);
        POOb.setTimeZone(Method.getTimeZone());

        POOb.setActivity(0);
        POOb.setWave(wave);
        POOb.setPR(PR);
        POOb.setResult(Result);
        POOb.setFlowrate(Flowrate);
        POOb.setResultSource(0);

        POOb.setMood(0);
        POOb.setWeather("");

        POOb.setNote("");
        POOb.setNoteTS(ts);

        POOb.setMeasureTime(ts);
        POOb.setMechineType("PO3");
        POOb.setMechineDeviceID(mac);

        POOb.setiHealthID(userName);

        return POOb;
    }

}
