package com.ihealth.communication.cloud;

/**
 * 接口返回信息
 * @author brave
 *
 */
public class ReturnDataUser {

	private String resultCode;//Json返回码  100为成功
	private String apiName;//应用程序的授权标识
	private String accessToken;//请求令牌
	private long expires;//过期时间
	private String refreshToken;//刷新Token
	private int id ;
	private int Status ;//获取用户状态
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getRegionHost() {
		return regionHost;
	}
	public void setRegionHost(String regionHost) {
		this.regionHost = regionHost;
	}
	private String regionHost; //当resultCode=208时,记录正确的服务器地址
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public long getExpires() {
		return expires;
	}
	public void setExpires(long expires) {
		this.expires = expires;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
}
