package com.ihealth.communication.cloud.data;

import java.util.ArrayList;

/**
 * 运返回的数据结构
 * @author brave
 * 	1)上传:resultMessage
 * 	2)下载:resultMessage 
 *	3)Token失败->重新登录 :会记录最新accessToken refreshToken regionHost expires
 */
public class PO_ReturnData {

	private String resultMessage; //返回码
	private String accessToken;	  //accessToken
	private String refreshToken;  //refreshToken
	private String regionHost;    //regionHost
	private long   expires;	      //TS
	
	private int leftNumber;//剩余多少条待下载
	private String ownerId;//用户在云端数据库的ID
	private ArrayList<Data_AM_Activity> wtArr;
	
	
	public int getLeftNumber() {
		return leftNumber;
	}
	public void setLeftNumber(int leftNumber) {
		this.leftNumber = leftNumber;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public ArrayList<Data_AM_Activity> getWtArr() {
		return wtArr;
	}
	public void setWtArr(ArrayList<Data_AM_Activity> wtArr) {
		this.wtArr = wtArr;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getRegionHost() {
		return regionHost;
	}
	public void setRegionHost(String regionHost) {
		this.regionHost = regionHost;
	}
	public long getExpires() {
		return expires;
	}
	public void setExpires(long expires) {
		this.expires = expires;
	}
}
