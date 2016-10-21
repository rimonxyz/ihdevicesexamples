package com.ihealth.communication.cloud.data;

/**
 * HS6上下云数据类
 * 
 * @author zhaoyongguang
 * @createdate 20141127
 */
public class Date_TB_HS6MeasureResult {
	
	//绑定解绑上传信息
	private String MAC = ""; // 设备唯一标示
	private String Model = ""; // 下位机型号 如HS6
	private long TS; // 时间戳 TS
	//绑定解绑返回信息
	private int Status; // 1：绑定 2：解绑  (给云的字段）
	private int Action; // 1:绑定成功 2：用户已满，绑定失败 3,:ts错误绑定失败 4其他原因
	private int Position; // 该用户在秤绑定的用户中，排第几（绑定失败，该字段值为-1）
	/* 新添加字段 zyg 2014年12月24日 星期三 18时49分35秒 CST */
	private int SetWifi; // 设备是否连过wifi，1：连过0：未连接wifi

	private int BindNum; // 设备所能绑定的最大数目
	
	//版本相关信息
	private String LatestVersion = ""; // 最新固件版本，例如1.2.1
	private String Mandatoryupgrade = ""; // 标识固件版本是否需要强制升级 0：不强制 1：强制
	private String Description = ""; // 更新的文字说明
	private String [] BeforeImage = {}; // 更新之前的图片数组,如果没有,则返回[]
	private String [] AfterImage = {}; // 更新之后的图片数组,如果没有,则返回[]
	
	private int ChangeType; // 1:已绑定，2：已解绑
	private int Agree; // 1同意2不同意
	
	//下载温湿度相关
	private long Time; // 时间点
	private int TimeZone; // 时区
	private double Temperature; // 温度(摄氏度)
	private int Humidity; // 湿度(百分比)
	private int Light; // 光强(预留)
		
	public Date_TB_HS6MeasureResult(){
		super();
	}
	
	public Date_TB_HS6MeasureResult(String mac, String model, long ts, int status, int action, int position, String latestversion,
			String mandatoryupgrade, String description, String[] beforeimage, String[] afterimage, int changetype,
			int agree, long time, int timezone, int temperature, int humidity, int light){
		super();
		MAC = mac;
		Model = model;
		TS = ts;
		Status = status;
		Action = action;
		Position =position;
		LatestVersion = latestversion;
		Mandatoryupgrade = mandatoryupgrade;
		Description = description;
		BeforeImage = new String[beforeimage.length];
		for (int i=0; i < beforeimage.length; i++){
			BeforeImage[i] = beforeimage[i];
		}
		AfterImage = new String[afterimage.length];
		for (int i=0; i < afterimage.length; i++){
			AfterImage[i] = afterimage[i];
		}
		ChangeType = changetype;
		Agree = agree;
		Time = time;
		TimeZone = timezone;
		Temperature = temperature;
		Humidity = humidity;
		Light = light;
	}

	public String getMAC() {
		return MAC;
	}

	public void setMAC(String mac) {
		MAC = mac;
	}
	
	public String getModel() {
		return Model;
	}

	public void setModel(String model) {
		Model = model;
	}
	
	public long getTS() {
		return TS;
	}

	public void setTS(long ts) {
		TS = ts;
	}
	
	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}
	
	public int getAction() {
		return Action;
	}

	public void setAction(int action) {
		Action = action;
	}
	
	public int getPosition() {
		return Position;
	}

	public void setPosition(int position) {
		Position = position;
	}
	
	public String getLatestVersion() {
		return LatestVersion;
	}
	
	public void setLatestVersion(String latestversion){
		LatestVersion = latestversion;
	}
	
	public String getMandatoryupgrade() {
		return Mandatoryupgrade;
	}
	
	public void setMandatoryupgrade(String mandatoryupgrade){
		Mandatoryupgrade = mandatoryupgrade;
	}
	
	public String getDescription() {
		return Description;
	}
	
	public void setDescription(String description){
		Description = description;
	}
	
	public String[] getBeforeImage() {
		return BeforeImage;
	}
	
	public void setBeforeImage(String[] beforeimage) {
		BeforeImage = new String[beforeimage.length];
		for (int i=0; i < beforeimage.length; i++){
			BeforeImage[i] = beforeimage[i];
		}
	}
	
	public String[] getAfterImage() {
		return AfterImage;
	}
	
	public void setAfterImage(String[] afterimage) {
		AfterImage = new String[afterimage.length];
		for (int i=0; i < afterimage.length; i++){
			AfterImage[i] = afterimage[i];
		}
	}
	
	public int getChangeType() {
		return ChangeType;
	}
	
	public void setChangeType(int changetype) {
		ChangeType = changetype;
	}
	
	public int getAgree() {
		return Agree;
	}
	
	public void setAgree(int agree) {
		Agree = agree;
	}
	
	public long getTime() {
		return Time;
	}
	
	public void setTime(long time){
		Time = time;
	}
	
	public int getTimeZone(){
		return TimeZone;
	}
	
	public void setTimeZone(int timezone){
		TimeZone = timezone;
	}
	
	public double getTemperature(){
		return Temperature;
	}
	
	public void setTemperature(double d){
		Temperature = d;
	}
	
	public int getHumidity() {
		return Humidity;
	}
	
	public void setHumidity(int humidity){
		Humidity = humidity;
	}
	
	public int getLight(){
		return Light;
	}
	
	public void setLight(int light){
		Light = light;
	}
	
	public int getSetWifi() {
		return SetWifi;
	}
	
	public void setSetWifi(int setWifi) {
		SetWifi = setWifi;
	}

	/**
	 * @return the bindNum
	 */
	public int getBindNum() {
		return BindNum;
	}

	/**
	 * @param bindNum the bindNum to set
	 */
	public void setBindNum(int bindNum) {
		BindNum = bindNum;
	}
}
