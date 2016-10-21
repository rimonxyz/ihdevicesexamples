/**
 * @title
 * @Description
 * @author
 * @date 2015年7月15日 上午10:59:04 
 * @version V1.0  
 */

package com.ihealth.communication.cloud.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @ClassName: Data_TB_SWIM
 * @Description: TODO
 * @author gaonana
 * @date 2015年7月15日 上午10:59:04
 */
public class Data_TB_Swim implements Parcelable {

    private int swim_ChangeType;
    private long swim_LastChangeTime;
    private String swim_PhoneDataID;
    private long swim_PhoneCreateTime;
    private double swim_Lat;
    private double swim_Lon;
    private float swim_TimeZone;
    private long swim_endtime;
    private int swim_SpendMinutes;// 用时 传递的单位为s
//    private Long swim_StartTimeStamp;   //每一程游泳开始时间戳
    private int swim_PullTimes; // 划水次数
    private float swim_Calories;
    private int swim_Stroke; // 泳姿 00 自由泳，01蛙泳，02 仰泳， 05 未识别
    private int swim_Distance;	//泳池长度
    private int swim_Cycles;	//当前程数
    
    private int swim_CutInTimeDif;	//与切入游泳时间差 单位：s
	private int swim_CutOutTimeDif; //与切出游泳时间差
    private int swim_ProcessFlag;	//游泳进程标志 1代表开始 0代表正在游 2代表结束 3代表此次进入游泳就一程

    private String swim_City;
    private String swim_Temperature;// 温度
    private String swim_WeatherCode;// 天气码
    private String swim_Humidity;// 湿度
    private String swim_Atmosphere;// 大气压
    private long swim_CommentTS;// 备注时间
    private String swim_CommentPic;// 备注图片
    private String swim_CommentNote;// 备注

    // ADD BY GYL
    // private long workout_ChangeTime;
    // private long workout_CreateTime;
    private String swim_MechineType;
    private String swim_MechineDeviceID;
    private String swim_iHealthCloud;

    public Data_TB_Swim() {
        super();
        // TODO Auto-generated constructor stub
        swim_PhoneDataID = new String();
        swim_City = new String();
        swim_Temperature = new String();
        swim_WeatherCode = new String();
        swim_Humidity = new String();
        swim_Atmosphere = new String();
        swim_CommentPic = new String();
        swim_CommentNote = new String();
        swim_MechineType = new String();
        swim_MechineDeviceID = new String();
        swim_iHealthCloud = new String();
    }

    public Data_TB_Swim(int swim_ChangeType, long swim_LastChangeTime, String swim_PhoneDataID,
                        long swim_PhoneCreateTime, double swim_Lat, double swim_Lon,
                        float swim_TimeZone, int swim_SpendMinutes, int swim_PullTimes, float swim_Calories, int swim_Stroke,
                        int swim_Distance, int swim_Cycles, String swim_City, String swim_Temperature,
                        String swim_WeatherCode, String swim_Humidity, String swim_Atmosphere, long swim_CommentTS,
                        String swim_CommentPic, String swim_CommentNote, long swim_endtime,
                        String swim_MechineType, String swim_MechineDeviceID, String swim_iHealthCloud) {
        super();
        this.swim_ChangeType = swim_ChangeType;
        this.swim_LastChangeTime = swim_LastChangeTime;
        this.swim_PhoneDataID = swim_PhoneDataID;
        this.swim_PhoneCreateTime = swim_PhoneCreateTime;
        this.swim_Lat = swim_Lat;
        this.swim_Lon = swim_Lon;
        this.swim_TimeZone = swim_TimeZone;
        this.swim_SpendMinutes = swim_SpendMinutes;
        this.swim_PullTimes = swim_PullTimes;
        this.swim_Calories = swim_Calories;
        this.swim_Stroke = swim_Stroke;
        this.swim_Distance = swim_Distance;
        this.swim_Cycles = swim_Cycles;
        this.swim_City = swim_City;
        this.swim_Temperature = swim_Temperature;
        this.swim_WeatherCode = swim_WeatherCode;
        this.swim_Humidity = swim_Humidity;
        this.swim_Atmosphere = swim_Atmosphere;
        this.swim_CommentTS = swim_CommentTS;
        this.swim_CommentPic = swim_CommentPic;
        this.swim_CommentNote = swim_CommentNote;
        this.swim_endtime = swim_endtime;

        this.swim_MechineType = swim_MechineType;
        this.swim_MechineType = swim_MechineType;
        this.swim_iHealthCloud = swim_iHealthCloud;

    }

    @Override
    public String toString() {
        return "Data_TB_Swim [swim_ChangeType=" + swim_ChangeType
                + ", swim_LastChangeTime=" + swim_LastChangeTime
                + ", swim_PhoneDataID=" + swim_PhoneDataID
                + ", swim_PhoneCreateTime=" + swim_PhoneCreateTime
                + ", swim_Lat=" + swim_Lat + ", swim_Lon="
                + swim_Lon + ", swim_TimeZone=" + swim_TimeZone
                + ", swim_SpendMinutes=" + swim_SpendMinutes
                + ", swim_PullTimes=" + swim_PullTimes
                + ", swim_Stroke=" + swim_Stroke
                + ", swim_Cycles=" + swim_Cycles
                + ",  swim_Distance=" + swim_Distance + ", "
                + "swim_Calories=" + swim_Calories
                + ", swim_City=" + swim_City + ", swim_Temperature="
                + swim_Temperature + ", swim_WeatherCode="
                + swim_WeatherCode + ", swim_Humidity="
                + swim_Humidity + ", swim_Atmosphere="
                + swim_Atmosphere + ", swim_CommentTS="
                + swim_CommentTS + ", swim_CommentPic="
                + swim_CommentPic + ", swim_CommentNote="
                + swim_CommentNote + ", swim_endtime=" + swim_endtime
                + ", swim_MechineType=" + swim_MechineType
                + ", swim_MechineDeviceID=" + swim_MechineDeviceID
                + ", swim_iHealthCloud=" + swim_iHealthCloud + "]";
    }

    public String getSwim_iHealthCloud() {
        return swim_iHealthCloud;
    }

    public void setSwim_iHealthCloud(String swim_iHealthCloud) {
        this.swim_iHealthCloud = swim_iHealthCloud;
    }

    public String getSwim_MechineType() {
        return swim_MechineType;
    }

    public void setSwim_MechineType(String swim_MechineType) {
        this.swim_MechineType = swim_MechineType;
    }

    public String getSwim_MechineDeviceID() {
        return swim_MechineDeviceID;
    }

    public void setSwim_MechineDeviceID(String swim_MechineDeviceID) {
        this.swim_MechineDeviceID = swim_MechineDeviceID;
    }

    public int getSwim_ChangeType() {
        return swim_ChangeType;
    }

    public void setSwim_ChangeType(int swim_ChangeType) {
        this.swim_ChangeType = swim_ChangeType;
    }

    public long getSwim_LastChangeTime() {
        return swim_LastChangeTime;
    }

    public void setSwim_LastChangeTime(long swim_LastChangeTime) {
        this.swim_LastChangeTime = swim_LastChangeTime;
    }

    public String getSwim_PhoneDataID() {
        return swim_PhoneDataID;
    }

    public void setSwim_PhoneDataID(String swim_PhoneDataID) {
        this.swim_PhoneDataID = swim_PhoneDataID;
    }

    public long getSwim_PhoneCreateTime() {
        return swim_PhoneCreateTime;
    }

    public void setSwim_PhoneCreateTime(long swim_PhoneCreateTime) {
        this.swim_PhoneCreateTime = swim_PhoneCreateTime;
    }

    public double getSwim_Lat() {
        return swim_Lat;
    }

    public void setSwim_Lat(double swim_Lat) {
        this.swim_Lat = swim_Lat;
    }

    public double getSwim_Lon() {
        return swim_Lon;
    }

    public void setSwim_Lon(double swim_Lon) {
        this.swim_Lon = swim_Lon;
    }

    public float getSwim_TimeZone() {
        return swim_TimeZone;
    }

    public void setSwim_TimeZone(float swim_TimeZone) {
        this.swim_TimeZone = swim_TimeZone;
    }

    public int getSwim_SpendMinutes() {
        return swim_SpendMinutes;
    }

    public void setSwim_SpendMinutes(int swim_SpendMinutes) {
        this.swim_SpendMinutes = swim_SpendMinutes;
    }
//    public Long getSwim_StartTimeStamp() {
//        return swim_StartTimeStamp;
//    }
//
//    public void setSwim_StartTimeStamp(Long swim_StartTimeStamp) {
//        this.swim_StartTimeStamp = swim_StartTimeStamp;
//    }

    public int getSwim_Cycles() {
        return swim_Cycles;
    }

    public void setSwim_Cycles(int swim_Cycles) {
        this.swim_Cycles = swim_Cycles;
    }

    public int getSwim_PullTimes() {
        return swim_PullTimes;
    }

    public void setSwim_PullTimes(int swim_PullTimes) {
        this.swim_PullTimes = swim_PullTimes;
    }

    public int getSwim_Stroke() {
        return swim_Stroke;
    }

    public void setSwim_Storke(int swim_Stroke) {
        this.swim_Stroke = swim_Stroke;
    }

    public int getSwim_Distance() {
        return swim_Distance;
    }

    public void setSwim_Distance(int swim_Distance) {
        this.swim_Distance = swim_Distance;
    }

    public float getSwim_Calories() {
        return swim_Calories;
    }

    public void setSwim_Calories(float swim_Calories) {
        this.swim_Calories = swim_Calories;
    }

    public String getSwim_City() {
        return swim_City;
    }

    public void setSwim_City(String swim_City) {
        this.swim_City = swim_City;
    }

    public String getSwim_Temperature() {
        return swim_Temperature;
    }

    public void setSwim_Temperature(String swim_Temperature) {
        this.swim_Temperature = swim_Temperature;
    }

    public String getSwim_WeatherCode() {
        return swim_WeatherCode;
    }

    public void setSwim_WeatherCode(String swim_WeatherCode) {
        this.swim_WeatherCode = swim_WeatherCode;
    }

    public String getSwim_Humidity() {
        return swim_Humidity;
    }

    public void setSwim_Humidity(String swim_Humidity) {
        this.swim_Humidity = swim_Humidity;
    }

    public String getSwim_Atmosphere() {
        return swim_Atmosphere;
    }

    public void setSwim_Atmosphere(String swim_Atmosphere) {
        this.swim_Atmosphere = swim_Atmosphere;
    }

    public long getSwim_CommentTS() {
        return swim_CommentTS;
    }

    public void setSwim_CommentTS(long swim_CommentTS) {
        this.swim_CommentTS = swim_CommentTS;
    }

    public String getSwim_CommentPic() {
        return swim_CommentPic;
    }

    public void setSwim_CommentPic(String swim_CommentPic) {
        this.swim_CommentPic = swim_CommentPic;
    }

    public String getSwim_CommentNote() {
        return swim_CommentNote;
    }

    public void setSwim_CommentNote(String swim_CommentNote) {
        this.swim_CommentNote = swim_CommentNote;
    }

    public long getSwim_endtime() {
        return swim_endtime;
    }

    public void setSwim_endtime(long swim_endtime) {
        this.swim_endtime = swim_endtime;
    }

    public int getSwim_CutInTimeDif() {
		return swim_CutInTimeDif;
	}

	public void setSwim_CutInTimeDif(int swim_CutInTimeDif) {
		this.swim_CutInTimeDif = swim_CutInTimeDif;
	}

	public int getSwim_CutOutTimeDif() {
		return swim_CutOutTimeDif;
	}

	public void setSwim_CutOutTimeDif(int swim_CutOutTimeDif) {
		this.swim_CutOutTimeDif = swim_CutOutTimeDif;
	}

	public int getSwim_ProcessFlag() {
		return swim_ProcessFlag;
	}

	public void setSwim_ProcessFlag(int swim_ProcessFlag) {
		this.swim_ProcessFlag = swim_ProcessFlag;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		//int
		out.writeInt(swim_ChangeType);
		out.writeInt(swim_SpendMinutes);
		out.writeInt(swim_PullTimes);
		out.writeInt(swim_Stroke);
		out.writeInt(swim_Distance);
		out.writeInt(swim_Cycles);
		out.writeInt(swim_CutInTimeDif);
		out.writeInt(swim_CutOutTimeDif);
		out.writeInt(swim_ProcessFlag);
		//string
		out.writeString(swim_PhoneDataID);
		out.writeString(swim_City);
		out.writeString(swim_Temperature);
		out.writeString(swim_WeatherCode);
		out.writeString(swim_Humidity);
		out.writeString(swim_Atmosphere);
		out.writeString(swim_CommentPic);
		out.writeString(swim_CommentNote);
		out.writeString(swim_MechineType);
		out.writeString(swim_MechineDeviceID);
		out.writeString(swim_iHealthCloud);
		//float
		out.writeFloat(swim_TimeZone);
		out.writeFloat(swim_Calories);
		//double
		out.writeDouble(swim_Lat);
		out.writeDouble(swim_Lon);
		//long
		out.writeLong(swim_LastChangeTime);
		out.writeLong(swim_PhoneCreateTime);
		out.writeLong(swim_endtime);
        out.writeLong(swim_CommentTS);
//		out.writeLong(swim_StartTimeStamp);
	}
	
	public static final Parcelable.Creator<Data_TB_Swim> CREATOR = new Creator<Data_TB_Swim>() {

		@Override
		public Data_TB_Swim createFromParcel(Parcel in) {
			// TODO Auto-generated method stub
			return new Data_TB_Swim(in);
		}

		@Override
		public Data_TB_Swim[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Data_TB_Swim[size];
		}
		
	};
	
	public Data_TB_Swim(Parcel in){
		swim_ChangeType = in.readInt();
		swim_SpendMinutes = in.readInt();
		swim_PullTimes = in.readInt();
		swim_Stroke = in.readInt();
		swim_Distance = in.readInt();
		swim_Cycles = in.readInt();
		swim_CutInTimeDif = in.readInt();
		swim_CutOutTimeDif = in.readInt();
		swim_ProcessFlag = in.readInt();
		//string
		swim_PhoneDataID = in.readString();
		swim_City = in.readString();
		swim_Temperature = in.readString();
		swim_WeatherCode = in.readString();
		swim_Humidity = in.readString();
		swim_Atmosphere = in.readString();
		swim_CommentPic = in.readString();
		swim_CommentNote = in.readString();
		swim_MechineType = in.readString();
		swim_MechineDeviceID = in.readString();
		swim_iHealthCloud = in.readString();
		//float
		swim_TimeZone = in.readFloat();
		swim_Calories = in.readFloat();
		//double
		swim_Lat = in.readDouble();
		swim_Lon = in.readDouble();
		//long
		swim_LastChangeTime = in.readLong();
		swim_PhoneCreateTime = in.readLong();
		swim_endtime = in.readLong();
		swim_CommentTS = in.readLong();
//        swim_StartTimeStamp = in.readLong();
	}
}
