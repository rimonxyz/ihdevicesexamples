package com.ihealth.communication.cloud.data;

public class Data_BP_Result {

	int ChangeType; // 是否删除:1存储2删除 当为删的时候只要有唯一ID即可
	long LastChangeTime; // 19923928382 数据在客户端上的最后修改时间
	String PhoneDataID; // 数据在apps上的唯一编号(DataID)
	long PhoneCreateTime; // 19923928382 这条数据的创建时间(只读)
	double Lat; // 纬度
	double Lon; // 经度
	float TimeZone; // 时区,8,-2,3.6
	int BPL; // 血压等级
	int LP; // 低压, 单位:mmHG
	int HP; // 高压, 单位:mmHG
	int HR; // 心率
	int IsArr; // 是否心律不齐
	String WL; // 小波数组形式字符串
	int MeasureType; // 2：批量上传(来自称) 1：手动数据 0：单次上传(来自称)
	long MeasureTime; // 19923928382
	String Note; // 备注说明
	String MechineType; // 测试来源(BP5/BP7)
	String MechineDeviceID; // 下位机设备编号, MAC地址
	
	String iHealthCloud;//=UN=数据库中的bpun
	
	//云5,0新增BP字段
	private long NoteTS; // 备注时间
	private int bpMood; // 心情
	private int bpActivity; // 运动
	
	private String temp; // 温度
	private String weather; // 天气码
	private String humidity; // 湿度
	private String visibility; // 可见度（最新需求改为大气压！！！！！！所以此字段存的是大气压！！！！）
	
	private int UsedUserid;
	
	public Data_BP_Result() {
		this.WL = new String();
		this.Note = new String();
		this.MechineType = new String();
		this.MechineDeviceID = new String();
		this.iHealthCloud = new String();

	}

	public long getNoteTS() {
		return NoteTS;
	}

	public void setNoteTS(long noteTS) {
		NoteTS = noteTS;
	}

	public int getBpMood() {
		return bpMood;
	}

	public void setBpMood(int bpMood) {
		this.bpMood = bpMood;
	}

	public int getBpActivity() {
		return bpActivity;
	}

	public void setBpActivity(int bpActivity) {
		this.bpActivity = bpActivity;
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

	public int getUsedUserid() {
		return UsedUserid;
	}

	public void setUsedUserid(int usedUserid) {
		UsedUserid = usedUserid;
	}

	public String getiHealthCloud() {
		return iHealthCloud;
	}

	public void setiHealthCloud(String iHealthCloud) {
		this.iHealthCloud = iHealthCloud;
	}

	public int getIsArr() {
		return IsArr;
	}

	public void setIsArr(int isArr) {
		IsArr = isArr;
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

	public int getBPL() {
		return BPL;
	}

	public void setBPL(int bPL) {
		BPL = bPL;
	}

	public int getLP() {
		return LP;
	}

	public void setLP(int lP) {
		LP = lP;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public int getHR() {
		return HR;
	}

	public void setHR(int hR) {
		HR = hR;
	}

	public String getWL() {
		return WL;
	}

	public void setWL(String wL) {
		WL = wL;
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

}
