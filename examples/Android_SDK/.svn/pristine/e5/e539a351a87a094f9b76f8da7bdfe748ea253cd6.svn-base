package com.ihealth.communication.cloud.data;

/**
 * HS数据类
 * @author Brave
 */
public class Data_HS_Result {
	
	private int ChangeType; // 是否删除 1：存储 2：删除
	private long LastChangeTime; // 修改TS
	private String PhoneDataID; // 数据id
	private long PhoneCreateTime; // 第一次存入数据库时间
	
	private double Lat; // 纬度
	private double Lon;// 经度
	private float TimeZone;// 时区
	private float BMI;// BMI
	
	private float BoneValue;// 骨密度
	private float DCI;// DCI每天最低摄入量
	private float FatValue;// 体脂
	private float MuscaleValue;// 肌肉
	
	private int MeasureType;//0：单次上传 1：手动输入 2：批量上传
	private float WaterValue;// 水分
	private float WeightValue;// 体重
	private long MeasureTime;// 测量时间
	
	private String Note;// 备注
	private int VisceraFatLevel;// 内脏脂肪等级
	private String MechineType;// 测试来源
	private String MechineDeviceID;// 下位机编号
	private String iHealthID;// iHealthId
	private int Emotions;// 心情 1：极好 2：好 3：一般 4：差 5：极差
	
	//Add 云5.0新增字段
	private long NoteTS;//备注时间
	private int Mood; //心情
	private int Activity;//活动
	private String temp; // 温度
	private String weather; // 天气码
	private String humidity; // 湿度
	private String visibility; // 可见度（最新需求改为大气压！！！！！！所以此字段存的是大气压！！！！）
	private int UsedUserid;//已挑选过这条数据的userid
	
	public Data_HS_Result(int ChangeType, long LastChangeTime,
			String PhoneDataID, long PhoneCreateTime, double Lat, double Lon,
			float TimeZone, float BMI, float BoneValue, float DCI,
			float FatValue, float MuscaleValue, int MeasureType,
			float WaterValue, float WeightValue, long MeasureTime, String Note,
			int VisceraFatLevel, String MechineType, String MechineDeviceID,
			String iHealthID, int Emotions, long NoteTS, int Mood, int Activity, String weather
			, String temp, String humidity, String visibility, int usedUserid) {
		super();
		this.ChangeType = ChangeType;
		this.LastChangeTime = LastChangeTime;
		this.PhoneDataID = PhoneDataID;
		this.PhoneCreateTime = PhoneCreateTime;
		this.Lat = Lat;
		this.Lon = Lon;
		this.TimeZone = TimeZone;
		this.BMI = BMI;
		this.BoneValue = BoneValue;
		this.DCI = DCI;
		this.FatValue = FatValue;
		this.MuscaleValue = MuscaleValue;
		this.MeasureType = MeasureType;
		this.WaterValue = WaterValue;
		this.WeightValue = WeightValue;
		this.MeasureTime = MeasureTime;
		this.Note = Note;
		this.VisceraFatLevel = VisceraFatLevel;
		this.MechineType = MechineType;
		this.MechineDeviceID = MechineDeviceID;
		this.iHealthID = iHealthID;
		this.Emotions = Emotions;
		this.NoteTS = NoteTS;
		this.Mood = Mood;
		this.Activity = Activity;
		this.temp = temp;
		this.humidity = humidity;
		this.visibility = visibility;
		this.weather = weather;
		this.UsedUserid = usedUserid;
	}

	public Data_HS_Result(){
		
	}
	public int getUsedUserid() {
		return UsedUserid;
	}

	public void setUsedUserid(int usedUserid) {
		UsedUserid = usedUserid;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public int getMood() {
		return Mood;
	}

	public void setMood(int mood) {
		Mood = mood;
	}

	public int getActivity() {
		return Activity;
	}

	public void setActivity(int activity) {
		Activity = activity;
	}

	public long getNoteTS() {
		return NoteTS;
	}

	public void setNoteTS(long noteTS) {
		NoteTS = noteTS;
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

	public float getBMI() {
		return BMI;
	}

	public void setBMI(float bMI) {
		BMI = bMI;
	}

	public float getBoneValue() {
		return BoneValue;
	}

	public void setBoneValue(float boneValue) {
		BoneValue = boneValue;
	}

	public float getDCI() {
		return DCI;
	}

	public void setDCI(float dCI) {
		DCI = dCI;
	}

	public float getFatValue() {
		return FatValue;
	}

	public void setFatValue(float fatValue) {
		FatValue = fatValue;
	}

	public float getMuscaleValue() {
		return MuscaleValue;
	}

	public void setMuscaleValue(float muscaleValue) {
		MuscaleValue = muscaleValue;
	}

	public int getMeasureType() {
		return MeasureType;
	}

	public void setMeasureType(int measureType) {
		MeasureType = measureType;
	}

	public float getWaterValue() {
		return WaterValue;
	}

	public void setWaterValue(float waterValue) {
		WaterValue = waterValue;
	}

	public float getWeightValue() {
		return WeightValue;
	}

	public void setWeightValue(float weightValue) {
		WeightValue = weightValue;
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

	public int getVisceraFatLevel() {
		return VisceraFatLevel;
	}

	public void setVisceraFatLevel(int visceraFatLevel) {
		VisceraFatLevel = visceraFatLevel;
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

	public String getiHealthID() {
		return iHealthID;
	}

	public void setiHealthID(String iHealthID) {
		this.iHealthID = iHealthID;
	}

	public int getEmotions() {
		return Emotions;
	}

	public void setEmotions(int emotions) {
		Emotions = emotions;
	}

}
