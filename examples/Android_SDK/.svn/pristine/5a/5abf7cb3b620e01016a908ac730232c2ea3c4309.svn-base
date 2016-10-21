package com.ihealth.communication.cloud.data;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.ihealth.communication.cloud.UserCheckSDK;
import com.ihealth.communication.cloud.tools.AppsDeviceParameters;

import android.content.Context;
import android.database.Cursor;
import com.ihealth.communication.utils.Log;

/**
 * Start_timer->执行SDK上传Start_timer接口
 * @author brave
 *
 */
public class PO_Up {

	private static final String TAG = "PO_sdk_Up";

	public final Timer timer = new Timer();
	public TimerTask task;
	private String un;
	public Context context;
	private String accessToken, refreshToken, host;

	public PO_Up(Context context) {
		if(AppsDeviceParameters.isLog)
			Log.i(TAG, "实例化sdk_Up,取本地配置-jiuan.sdk.author");

		un = context.getSharedPreferences("jiuan.sdk.author", 0).getString("email", "");
		accessToken = context.getSharedPreferences("jiuan.sdk.author", 0).getString("accessToken", "");
		refreshToken = context.getSharedPreferences("jiuan.sdk.author", 0).getString("refreshToken", "");
		host = context.getSharedPreferences("jiuan.sdk.author", 0).getString("Host", "");

		if("".equals(host)){
			host = AppsDeviceParameters.webSite;
		}
		this.context = context;
		if(AppsDeviceParameters.isLog){
			Log.i(TAG, "取得 un = " + un);
			Log.i(TAG, "取得 verifyToken = " + accessToken);
			Log.i(TAG, "取得 refreshToken = " + refreshToken);
			Log.i(TAG, "取得 host = " + host);
		}
	}

	/**
	 * @param un2 
	 * @return 开启PO云定时器，30秒更新一次
	 */
	public void Start_timer() {

		task = new TimerTask() {
			public void run() {

				if(UserCheckSDK.isNetworkAvailable(context)) {
					ArrayList<Data_PO_Result> poList = null;
					poList = collectActivity();// 从数据库取数

					if (poList == null) {
						if (AppsDeviceParameters.isLog)
							Log.i(TAG, "无数据上传");
					} else if (poList != null && poList.size() > 0) {
						if (AppsDeviceParameters.isLog)
							Log.i(TAG, "有数据上传");
						boolean upState = false;// 上传标记 true 上传成功
						try {
							PO_CommCloud comm = new PO_CommCloud(context);
							PO_ReturnData bgUploadReturn = comm.am_po_upload(
									un, accessToken, poList, host);
							String resultMessage = bgUploadReturn
									.getResultMessage();

							if ("100".equals(resultMessage)) {
								upState = true;
							} else {
								upState = false;
							}
							if (AppsDeviceParameters.isLog)
								Log.i(TAG, "上传结果 = " + upState);
						} catch (Exception e) {
							e.printStackTrace();
						}

						// 上传成功后删除数据库中的数据
						if (upState) {

							DataBaseTools db = new DataBaseTools(context);
							boolean asd = false;
							for(Data_PO_Result por:poList) {
								asd = db.deleteData(DataBaseConstants.TABLE_TB_PO,DataBaseConstants.PO_IHEALTHID + " = '" + un 
										+ "' and " + DataBaseConstants.PO_PHONEDATAID + " = '" + por.getPhoneDataID() + "'");
							}

							if (AppsDeviceParameters.isLog)
								Log.i(TAG, "删除结果 = " + asd);

							// 关闭数据库连接
						}

						// 再取一次验证删除
						DataBaseTools db1 = new DataBaseTools(context);
						Cursor cur = db1.selectData(DataBaseConstants.TABLE_TB_PO, null,
								DataBaseConstants.PO_IHEALTHID + "='" + un + "'");
						if (AppsDeviceParameters.isLog)
							Log.i(TAG, "再取一次验证删除成功 ==> 取得条目数 = " + cur.getCount());
					}
				} else {
					if(AppsDeviceParameters.isLog)
						Log.e(TAG, "当前无网络连接!");
				}
			}
		};
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
	private ArrayList<Data_PO_Result> collectActivity() {

		DataBaseTools db = new DataBaseTools(this.context);
		Cursor cur = db.selectData(DataBaseConstants.TABLE_TB_PO, null, DataBaseConstants.PO_IHEALTHID + "='" + un + "'");
		cur.moveToFirst();

		if (cur.getCount() > 0) {

			ArrayList<Data_PO_Result> poArr = new ArrayList<Data_PO_Result>();//返回的数据集合

			for (int i = 0; i < cur.getCount(); i++) {
				Data_PO_Result bpo = new Data_PO_Result();

				//添加来自数据库的数据到数据类-准备上传
				bpo.setChangeType(cur.getInt(cur.getColumnIndex(DataBaseConstants.PO_CHANGETYPE)));
				bpo.setLastChangeTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.PO_LASTCHANGETIME)));
				bpo.setPhoneDataID(cur.getString(cur.getColumnIndex(DataBaseConstants.PO_PHONEDATAID)));
				bpo.setPhoneCreateTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.PO_PHONECREATETIME)));
				bpo.setLat(cur.getFloat(cur.getColumnIndex(DataBaseConstants.PO_LAT)));
				bpo.setLon(cur.getFloat(cur.getColumnIndex(DataBaseConstants.PO_LON)));
				bpo.setTimeZone(cur.getFloat(cur.getColumnIndex(DataBaseConstants.PO_TIMEZONE)));

				bpo.setActivity(cur.getInt(cur.getColumnIndex(DataBaseConstants.PO_ACTIVITY)));
				bpo.setWave(cur.getString(cur.getColumnIndex(DataBaseConstants.PO_WAVE)));
				bpo.setPR(cur.getInt(cur.getColumnIndex(DataBaseConstants.PO_PR)));
				bpo.setResult(cur.getInt(cur.getColumnIndex(DataBaseConstants.PO_RESULT)));
				bpo.setFlowrate(cur.getInt(cur.getColumnIndex(DataBaseConstants.PO_FLOWRATE)));
				bpo.setResultSource(cur.getInt(cur.getColumnIndex(DataBaseConstants.PO_RESULTSOURCE)));

				bpo.setMood(cur.getInt(cur.getColumnIndex(DataBaseConstants.PO_MOOD)));
				bpo.setWeather(cur.getString(cur.getColumnIndex(DataBaseConstants.PO_WEATHER)));

				bpo.setNote(cur.getString(cur.getColumnIndex(DataBaseConstants.PO_NOTE)));
				bpo.setNoteTS(cur.getLong(cur.getColumnIndex(DataBaseConstants.PO_NOTETS)));

				bpo.setMeasureTime(cur.getLong(cur.getColumnIndex(DataBaseConstants.PO_MEASURETIME)));
				bpo.setMechineType(cur.getString(cur.getColumnIndex(DataBaseConstants.PO_MECHINETYPE)));
				bpo.setMechineDeviceID(cur.getString(cur.getColumnIndex(DataBaseConstants.PO_MECHINEDEVICEID)));

				bpo.setiHealthID(cur.getString(cur.getColumnIndex(DataBaseConstants.PO_IHEALTHID)));

				poArr.add(bpo);
				cur.moveToNext();
				if(poArr.size() > 50) {
					break;
				}
			}

			//关闭游标,DataBase,数据库连接
			cur.close();
			return poArr;
		} else {
			cur.close();
			return null;
		}
	}
}
