package com.ihealth.communication.cloud.data;

/**
 * 血糖结果表
 * @author Brave
 */
public class Data_BG_Result {
	private int ChangeType; // 是否删除 1：存储 2：删除
	private long LastChangeTime; // 修改TS
	private String PhoneDataID; // 数据id
	private long PhoneCreateTime; // 第一次存入数据库时间
	private double Lat; // 纬度
	private double Lon;// 经度
	private float TimeZone;// 时区
	private float BGValue;// 血糖值，单位：mg/dL
	private int Medication;// 是否服药（0：pre 1:post）
	private int MTimeType;// 7点时间点（早餐前，后，午餐前，后，晚饭前，后，半夜），按顺序：1，2，3，4，5，6，7
	private int MeasureType;// 3Ctl模式 2批量上传(来自称) 1：手动数据 0:单次上传(来自称) (和数据库里保持一致)
	private long MeasureTime;// 测量时间
	private String Note;// 备注
	private String MechineType;// 测试来源
	private String MechineDeviceID;// 下位机编号
	private String BottleId;// 试条瓶ID
	private int Sports;// 是否运动锻炼（ 1：是 2：否）
	private int Snacks;// 是否吃过零食（ 1：是 2：否）
	private int Effective;// 数据是否有效（1；有效 2；无效）
	private String iHealthID;// iHealthId

	public Data_BG_Result(int changeType, long lastChangeTime, String phoneDataID, long phoneCreateTime, double lat, double lon, float timeZone, float bGValue, int medication,
			int mTimeType, int measureType, long measureTime, String note, String mechineType, String mechineDeviceID, String bottleId, int sports, int snacks, int effective,
			String iHealthID) {
		super();
		ChangeType = changeType;
		LastChangeTime = lastChangeTime;
		PhoneDataID = phoneDataID;
		PhoneCreateTime = phoneCreateTime;
		Lat = lat;
		Lon = lon;
		TimeZone = timeZone;
		BGValue = bGValue;
		Medication = medication;
		MTimeType = mTimeType;
		MeasureType = measureType;
		MeasureTime = measureTime;
		Note = note;
		MechineType = mechineType;
		MechineDeviceID = mechineDeviceID;
		BottleId = bottleId;
		Sports = sports;
		Snacks = snacks;
		Effective = effective;
		this.iHealthID = iHealthID;
	}

	public Data_BG_Result() {
		super();
		this.PhoneDataID = new String();
		this.Note = new String();
		this.MechineType = new String();
		this.MechineDeviceID = new String();
		this.BottleId = new String();
		this.iHealthID = new String();
	}

	public int getEffective() {
		return Effective;
	}

	public void setEffective(int effective) {
		Effective = effective;
	}

	public int getChangeType() {
		return ChangeType;
	}

	public void setChangeType(int changeType) {
		ChangeType = changeType;
	}

	public long getLastChangeTime() {
		return LastChangeTime;
	}

	public void setLastChangeTime(long lastChangeTime) {
		LastChangeTime = lastChangeTime;
	}

	public String getPhoneDataID() {
		return PhoneDataID;
	}

	public void setPhoneDataID(String phoneDataID) {
		PhoneDataID = phoneDataID;
	}

	public long getPhoneCreateTime() {
		return PhoneCreateTime;
	}

	public void setPhoneCreateTime(long phoneCreateTime) {
		PhoneCreateTime = phoneCreateTime;
	}

	public double getLat() {
		return Lat;
	}

	public void setLat(double lat) {
		Lat = lat;
	}

	public double getLon() {
		return Lon;
	}

	public void setLon(double lon) {
		Lon = lon;
	}

	public float getTimeZone() {
		return TimeZone;
	}

	public void setTimeZone(float timeZone) {
		TimeZone = timeZone;
	}

	public float getBGValue() {
		return BGValue;
	}

	public void setBGValue(float bGValue) {
		BGValue = bGValue;
	}

	public int getMedication() {
		return Medication;
	}

	public void setMedication(int medication) {
		Medication = medication;
	}

	public int getMTimeType() {
		return MTimeType;
	}

	public void setMTimeType(int mTimeType) {
		MTimeType = mTimeType;
	}

	public int getMeasureType() {
		return MeasureType;
	}

	public void setMeasureType(int measureType) {
		MeasureType = measureType;
	}

	public long getMeasureTime() {
		return MeasureTime;
	}

	public void setMeasureTime(long measureTime) {
		MeasureTime = measureTime;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

	public String getMechineType() {
		return MechineType;
	}

	public void setMechineType(String mechineType) {
		MechineType = mechineType;
	}

	public String getMechineDeviceID() {
		return MechineDeviceID;
	}

	public void setMechineDeviceID(String mechineDeviceID) {
		MechineDeviceID = mechineDeviceID;
	}

	public String getBottleId() {
		return BottleId;
	}

	public void setBottleId(String bottleId) {
		BottleId = bottleId;
	}

	public int getSports() {
		return Sports;
	}

	public void setSports(int sports) {
		Sports = sports;
	}

	public int getSnacks() {
		return Snacks;
	}

	public void setSnacks(int snacks) {
		Snacks = snacks;
	}

	public String getiHealthID() {
		return iHealthID;
	}

	public void setiHealthID(String iHealthID) {
		this.iHealthID = iHealthID;
	}

}
