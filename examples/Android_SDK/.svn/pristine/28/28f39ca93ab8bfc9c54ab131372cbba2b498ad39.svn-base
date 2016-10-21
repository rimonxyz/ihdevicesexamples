package com.ihealth.communication.cloud.data;

/**
 * 用户信息：登陆的时候会下载
 *	
 */
public class UserNetData {

	private static final int MaxSizeEmail = 100; // Email数组的最大元素个数

	public int ID;
	public long Birthday;
	public String[] Email;
	public int Gender;
	public int IsSporter;
	public String Name;
	public int Height;
	public float Weight;
	public String Nation;
	public String Language;
	public int usecloud;
	public long TS;
//	String imaData;
//	long imaData_TS;
	public int ActivityLevel;
    public LOGO logo;
	public String iHealthID;
	public int Age;
	
	public String UserNation;//用户国家
	public int UserIdx = 0;//三方用户Id

	public UserNetData() {
		this.Email = new String[MaxSizeEmail];
		for (int i = 0; i < MaxSizeEmail; i++) {
			this.Email[i] = "";
		}
		this.Name = new String();
		this.Nation = new String();
		this.Language = new String();
//		this.imaData = new String();
		this.logo = new LOGO();
		this.iHealthID = new String();
	}

	public LOGO getLogo() {
		return logo;
	}

	public void setLogo(LOGO logo) {
		this.logo = logo;
	}

	public String getiHealthID() {
		return iHealthID;
	}

	public void setiHealthID(String iHealthID) {
		this.iHealthID = iHealthID;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	// Logo类
	public class LOGO {
		public String data;
		public long TS;

		public LOGO() {
			data = new String();
		}
	}

	public String[] getEmail() {
		return Email;
	}

	public void setEmail(String[] email) {
		Email = email;
	}

	public static int getMaxsizeemail() {
		return MaxSizeEmail;
	}

//	public String getImaData() {
//		return imaData;
//	}
//
//	public void setImaData(String imaData) {
//		this.imaData = imaData;
//	}
//
//	public long getImaData_TS() {
//		return imaData_TS;
//	}
//
//	public void setImaData_TS(long imaData_TS) {
//		this.imaData_TS = imaData_TS;
//	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public long getBirthday() {
		return Birthday;
	}

	public void setBirthday(long birthday) {
		Birthday = birthday;
	}

	public int getGender() {
		return Gender;
	}

	public void setGender(int gender) {
		Gender = gender;
	}

	public int getIsSporter() {
		return IsSporter;
	}

	public void setIsSporter(int isSporter) {
		IsSporter = isSporter;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getHeight() {
		return Height;
	}

	public void setHeight(int height) {
		Height = height;
	}

	public float getWeight() {
		return Weight;
	}

	public void setWeight(float weight) {
		Weight = weight;
	}

	public String getNation() {
		return Nation;
	}

	public void setNation(String nation) {
		Nation = nation;
	}

	public String getLanguage() {
		return Language;
	}

	public void setLanguage(String language) {
		Language = language;
	}

	public int getUsecloud() {
		return usecloud;
	}

	public void setUsecloud(int usecloud) {
		this.usecloud = usecloud;
	}

	public long getTS() {
		return TS;
	}

	public void setTS(long tS) {
		TS = tS;
	}

	public int getActivityLevel() {
		return ActivityLevel;
	}

	public void setActivityLevel(int activityLevel) {
		ActivityLevel = activityLevel;
	}

	/**
	 * @return the userNation
	 */
	public String getUserNation() {
		return UserNation;
	}

	/**
	 * @param userNation the userNation to set
	 */
	public void setUserNation(String userNation) {
		UserNation = userNation;
	}

	/**
	 * @return the userIdx
	 */
	public int getUserIdx() {
		return UserIdx;
	}

	/**
	 * @param userIdx the userIdx to set
	 */
	public void setUserIdx(int userIdx) {
		UserIdx = userIdx;
	}

}
