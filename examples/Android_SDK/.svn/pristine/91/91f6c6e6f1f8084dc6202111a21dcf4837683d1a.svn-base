package com.ihealth.communication.cloud.data;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.ihealth.communication.cloud.UserCheckSDK;
import com.ihealth.communication.cloud.tools.AppsDeviceParameters;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import com.ihealth.communication.utils.Log;

public class BP_Up {

	protected static final String TAG = "BP_SDK";
	public final Timer timer = new Timer();
	public TimerTask task;
	private String un;
	public Context context;
	private String accessToken, refreshToken, host;

	public BP_Up(Context context) {
		un = context.getSharedPreferences("jiuan.sdk.author", 0).getString("email", "");
		accessToken = context.getSharedPreferences("jiuan.sdk.author", 0).getString("accessToken", "");
		refreshToken = context.getSharedPreferences("jiuan.sdk.author", 0).getString("refreshToken", "");
		host = context.getSharedPreferences("jiuan.sdk.author", 0).getString("Host", "");

		if("".equals(host)){
			host = AppsDeviceParameters.webSite;
		}
		this.context = context;

	}

	/**
	 * @param un2 
	 * @return 开启AM云定时器，30秒更新一次
	 */
	public void Start_timer() {

		task = new TimerTask() {
			public void run() {

				if(UserCheckSDK.isNetworkAvailable(context)) {

					ArrayList<Data_BP_Result> bpList = null;
					bpList = collecBpData();

					if (bpList == null) {
						if(AppsDeviceParameters.isLog)
							Log.i(TAG, "无数据上传");
					} else if (bpList != null && bpList.size() > 0) {
						//上传血压数据
						if(AppsDeviceParameters.isLog)
							Log.i(TAG, "有数据上传");
						boolean upState = false;//上传标记 true 上传成功
						try {
							BP_CommCloud ct = new BP_CommCloud(context);
							BP_ReturnData bpUploadReturn = ct.bp_upload(un, accessToken, bpList, host);
							String resultMessage = bpUploadReturn.getResultMessage();

							if ("100".equals(resultMessage)) {
								upState = true;
							}else{
								upState = false;
							}
							if(AppsDeviceParameters.isLog)
								Log.i(TAG, "上传结果 = " + upState);
						} catch (Exception e) {
							e.printStackTrace();
						}

						//上传成功后删除数据库中的数据
						if (upState) {

							DataBaseTools db = new DataBaseTools(context);
							boolean asd = false;
							for(Data_BP_Result bpr:bpList) {
								asd = db.deleteData(DataBaseConstants.TABLE_TB_BPMEASURERESULT, DataBaseConstants.BPMEASURERESULT_UN + " = '" + un 
										+ "' and " + DataBaseConstants.BPMEASURERESULT_PHONEDATAID + " = '" + bpr.getPhoneDataID() + "'");
							}
							if(AppsDeviceParameters.isLog)
								Log.i(TAG, "删除结果 = " + asd);
						}

						//再取一次验证删除
						DataBaseTools db1 = new DataBaseTools(context);
						Cursor cur = db1.selectData(DataBaseConstants.TABLE_TB_BPMEASURERESULT, null, DataBaseConstants.BPMEASURERESULT_UN + "='" + un + "'");

						if(AppsDeviceParameters.isLog)
							Log.i(TAG, "再取一次验证删除成功 ==> 取得条目数 = " + cur.getCount());
					}
				} else {
					if(AppsDeviceParameters.isLog)
						Log.e(TAG, "当前无网络连接!");
				}

			}

		};

		// 　 add exception by GYL
		try {
			timer.schedule(task, 1000, 30000);
		} catch (IllegalStateException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return 停止云定时器
	 */
	public void Stop_timer() {
		timer.cancel();
	}

	//取存储在数据库中的数据放入List准备上传到服务器
	private ArrayList<Data_BP_Result> collecBpData() {

		DataBaseTools db = new DataBaseTools(this.context);
		Cursor cur = db.selectData(DataBaseConstants.TABLE_TB_BPMEASURERESULT, null, DataBaseConstants.BPMEASURERESULT_UN + "='" + un + "'");
		cur.moveToFirst();

		if (cur.getCount() > 0) {

			ArrayList<Data_BP_Result> bpArr = new ArrayList<Data_BP_Result>();//返回的数据集合

			for (int i = 0; i < cur.getCount(); i++) {
				Data_BP_Result bpo = new Data_BP_Result();

				bpo.setBPL(cur.getInt(cur.getColumnIndex(DataBaseConstants.BPMEASURERESULT_BPL)));
				bpo.setChangeType(cur.getInt(cur.getColumnIndex(DataBaseConstants.BPMEASURERESULT_CHANGETYPE)));
				bpo.setHP(cur.getInt(cur.getColumnIndex(DataBaseConstants.BPMEASURERESULT_HP)));
				bpo.setHR(cur.getInt(cur.getColumnIndex(DataBaseConstants.BPMEASURERESULT_HR)));
				bpo.setIsArr(cur.getInt(cur.getColumnIndex(DataBaseConstants.BPMEASURERESULT_ISARR)));
				bpo.setLastChangeTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.BPMEASURERESULT_LASTCHANGETIME)));
				bpo.setLat(cur.getFloat(cur.getColumnIndex(DataBaseConstants.BPMEASURERESULT_LAT)));
				bpo.setLon(cur.getFloat(cur.getColumnIndex(DataBaseConstants.BPMEASURERESULT_LON)));
				bpo.setLP(cur.getInt(cur.getColumnIndex(DataBaseConstants.BPMEASURERESULT_LP)));
				bpo.setMeasureTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.BPMEASURERESULT_MEASURETIME)));
				bpo.setMeasureType(cur.getInt(cur.getColumnIndex(DataBaseConstants.BPMEASURERESULT_MEASURETYPE)));
				bpo.setMechineDeviceID(cur.getString(cur.getColumnIndex(DataBaseConstants.BPMEASURERESULT_MECHINEDEVICEID)));
				bpo.setMechineType(cur.getString(cur.getColumnIndex(DataBaseConstants.BPMEASURERESULT_MECHINETYPE)));
				bpo.setNote(cur.getString(cur.getColumnIndex(DataBaseConstants.BPMEASURERESULT_NOTE)));
				bpo.setPhoneCreateTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.BPMEASURERESULT_PHONECREATETIME)));
				bpo.setPhoneDataID(cur.getString(cur.getColumnIndex(DataBaseConstants.BPMEASURERESULT_PHONEDATAID)));
				bpo.setTimeZone(cur.getFloat(cur.getColumnIndex(DataBaseConstants.BPMEASURERESULT_TIMEZONE)));
				bpo.setWL(cur.getString(cur.getColumnIndex(DataBaseConstants.BPMEASURERESULT_WL)));
				bpo.setiHealthCloud(cur.getString(cur.getColumnIndex(DataBaseConstants.BPMEASURERESULT_UN)));

				//云5.0新增BP字段
				bpo.setNoteTS(cur.getLong(cur.getColumnIndex(DataBaseConstants.BPMEASURERESULT_NOTETS)));// 备注时间
				bpo.setBpMood(cur.getInt(cur.getColumnIndex(DataBaseConstants.BPMEASURERESULT_BPMOOD)));// 心情
				bpo.setBpActivity(cur.getInt(cur.getColumnIndex(DataBaseConstants.BPMEASURERESULT_BPACTIVITY)));// 运动
				bpo.setTemp(cur.getString(cur.getColumnIndex(DataBaseConstants.BPMEASURERESULT_TEMP)));// 温度
				bpo.setWeather(cur.getString(cur.getColumnIndex(DataBaseConstants.BPMEASURERESULT_WEATHER)));// 天气码
				bpo.setHumidity(cur.getString(cur.getColumnIndex(DataBaseConstants.BPMEASURERESULT_HUMIDITY)));// 湿度
				bpo.setVisibility(cur.getString(cur.getColumnIndex(DataBaseConstants.BPMEASURERESULT_VISIBILITY)));
				bpo.setUsedUserid(cur.getInt(cur.getColumnIndex(DataBaseConstants.BPMEASURERESULT_USEDUSERID)));

				bpArr.add(bpo);
				cur.moveToNext();

				if(bpArr.size() > 50) {
					break;
				}
			}

			//关闭游标,DataBase,数据库连接
			cur.close();
			return bpArr;
		} else {
			cur.close();
			return null;
		}
	}

}
