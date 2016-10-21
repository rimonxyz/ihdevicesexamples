
package com.ihealth.communication.cloud.data;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.ihealth.communication.utils.Log;

public class DataBaseTools {

	private static String TAG = "DataBaseTools";
	private SQLiteDatabase db;

	public DataBaseTools(Context context) {
		db = DataBaseOpenHelper.getInstance(context);
	}

	/**
	 * @param tableName
	 * @param conditionStr
	 * @return 删除表中某条数据的方法 author_GYL 2013年12月23日 下午2:17:37
	 */
	public Boolean deleteData(String tableName, String conditionStr) {
		Boolean iResult = false;
		String sql = "";
		if (conditionStr != null && conditionStr.length() > 0) {
			sql = "DELETE FROM  " + tableName + " where " + conditionStr;
		} else {
			sql = "DELETE FROM  " + tableName;
		}
		db.beginTransaction();
		try {
			db.execSQL(sql);
			iResult = true;
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
			iResult = false;
		} finally {
			db.endTransaction();
		}
		return iResult;
	}

	/**
	 * @param tableName
	 * @return 直接删除表的方法 author_GYL 2013年12月23日 下午2:17:53
	 */
	public Boolean deleteData(String tableName) {
		Boolean isResult = false;

		String sql = "";
		sql = "DELETE FROM  " + tableName;
		db.beginTransaction();
		try {
			this.db.execSQL(sql);
			isResult = true;
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
			isResult = false;
		} finally {
			db.endTransaction();
		}
		return isResult;
	}

	/**
	 * 打印出有DB 中所有的表
	 *
	 * @return author_GYL 2013年12月25日 下午1:41:34
	 */
	public Boolean dsa1() {
		Boolean isResult = false;

		Cursor cursor = db
				.rawQuery(
						"select name from sqlite_master where type='table' order by name",
						null);
		while (cursor.moveToNext()) {
			// 遍历出表名
			String name = cursor.getString(0);
			Log.i("System.out", name);
		}
		cursor.close();
		return isResult;
	}

	/**
	 * 删除所有表的数据
	 *
	 * @return author_GYL 2013年12月25日 下午1:41:24
	 */
	public Boolean deleteAllTableData() {
		Boolean isResult = false;

		Cursor cursor = db
				.rawQuery(
						"select name from sqlite_master where type='table' order by name",
						null);
		while (cursor.moveToNext()) {
			// 遍历出表名
			String name = cursor.getString(0);
			String sql = "DELETE FROM  " + name;
			Log.i(TAG, "删除所有表的数据name：" + name);
			Log.i(TAG, "删除所有表的数据sql：" + sql);
			db.beginTransaction();
			try {
				this.db.execSQL(sql);
				isResult = true;
				db.setTransactionSuccessful();
			} catch (SQLException e) {
				e.printStackTrace();
				isResult = false;
				Log.e(TAG, "删除表的数据SQLException:" + name);
			} finally {
				db.endTransaction();
			}
		}
		cursor.close();
		return isResult;
	}

	/**
	 * @param tableName
	 * @param columns
	 * @param conditionStr
	 * @return 查数据 author_GYL 2013年12月23日 下午2:22:43
	 */
	public Cursor selectData(String tableName, String columns[],
							 String conditionStr) {
		Cursor cur = null;

		db.beginTransaction();
		try {
			cur = this.db.query(tableName, columns, conditionStr, null, null,
					null, null);
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}

		return cur;
	}

	public Cursor selectDataOrderBy(String tableName, String columns[],
									String conditionStr, String orderBy) {
		Cursor cur = null;
		db.beginTransaction();
		try {
			cur = this.db.query(tableName, columns, conditionStr, null, null,
					null, orderBy);
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}

		return cur;
	}

	/**
	 * @param tableName
	 * @param conditionStr
	 * @param valueStr
	 * @return 修改数据 author_GYL 2013年12月23日 下午2:28:27
	 */
	public Boolean updateData(String tableName, String conditionStr,
							  String valueStr) {
		Log.i(tableName, "tableName:" + tableName);
		Boolean isResult = false;
		String sql = "";
		if (conditionStr.length() > 0) {
			sql = "UPDATE " + tableName + " SET " + valueStr + " where "
					+ conditionStr + ";";
		} else {
			sql = "UPDATE " + tableName + " SET " + valueStr;
		}
		Log.i(TAG, "sql:" + sql);
		db.beginTransaction();
		try {
			this.db.execSQL(sql);
			isResult = true;
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			Log.e(TAG, "Update sql 进入Catch");
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			// System.out.println("*****="+sw.getBuffer().toString());
			Log.e(TAG, "catch = " + sw.getBuffer().toString());
			e.printStackTrace();
			isResult = false;
		} finally {
			db.endTransaction();
		}
		return isResult;
	}


	/**
	 * 添加数据类，到指定表
	 * @param tableName
	 * @param obj
	 * @return
	 */
	public Boolean addData(String tableName,Object obj)
	{
		Boolean iResult=false;
		String sql="";
		/**
		 *  AM
		 */
		if(tableName.equals(DataBaseConstants.TABLE_TB_AM_ACTIVITY)){
			sql="";
			sql="insert into "+ DataBaseConstants.TABLE_TB_AM_ACTIVITY+" ("
					+DataBaseConstants.ACTIVITY_CHANGETYPE+","
					+DataBaseConstants.ACTIVITY_LASTCHANGETIME+","
					+DataBaseConstants.ACTIVITY_PHONEDATAID+","
					+DataBaseConstants.ACTIVITY_PHONECREATETIME+","
					+DataBaseConstants.ACTIVITY_LAT+","
					+DataBaseConstants.ACTIVITY_LON+","
					+DataBaseConstants.ACTIVITY_TIMEZONE+","
					+DataBaseConstants.ACTIVITY_CALORIE+","
					+DataBaseConstants.ACTIVITY_STEPLENGTH+","
					+DataBaseConstants.ACTIVITY_STEPS+","
					+DataBaseConstants.ACTIVITY_SUMCALORIE+","
					+DataBaseConstants.ACTIVITY_SUMSTEPS+","
					+DataBaseConstants.ACTIVITY_MEASURETIME+","
					+DataBaseConstants.ACTIVITY_MECHINETYPE+","
					+DataBaseConstants.ACTIVITY_MECHINEDEVICEID+","
					+DataBaseConstants.ACTIVITY_IHEALTHID+
					")VALUES("
					+((Data_AM_Activity)obj).getChangeType()+", "
					+((Data_AM_Activity)obj).getLastChangeTime()+", '"
					+((Data_AM_Activity)obj).getPhoneDataID()+"', "
					+((Data_AM_Activity)obj).getPhoneCreateTime()+", "
					+((Data_AM_Activity)obj).getLat()+", "
					+((Data_AM_Activity)obj).getLon()+", "
					+((Data_AM_Activity)obj).getTimeZone()+", "

					+((Data_AM_Activity)obj).getCalorie()+", "
					+((Data_AM_Activity)obj).getStepLength()+", "
					+((Data_AM_Activity)obj).getSteps()+", "
					+((Data_AM_Activity)obj).getSumCalorie()+", "
					+((Data_AM_Activity)obj).getSumSteps()+", "

					+((Data_AM_Activity)obj).getMeasureTime()+", '"
					+((Data_AM_Activity)obj).getMechineType()+"', '"
					+((Data_AM_Activity)obj).getMechineDeviceID()+"', '"

					+((Data_AM_Activity)obj).getiHealthID()+"');";

		}
		if(tableName.equals(DataBaseConstants.TABLE_TB_AM_ACTIVITYREPORT)){
			sql="";
			sql="insert into "+DataBaseConstants.TABLE_TB_AM_ACTIVITYREPORT+" ("
					+DataBaseConstants.ACTIVITYREPORT_CHANGETYPE+","
					+DataBaseConstants.ACTIVITYREPORT_LASTCHANGETIME+","
					+DataBaseConstants.ACTIVITYREPORT_PHONEDATAID+","
					+DataBaseConstants.ACTIVITYREPORT_PHONECREATETIME+","
					+DataBaseConstants.ACTIVITYREPORT_LAT+","
					+DataBaseConstants.ACTIVITYREPORT_LON+","
					+DataBaseConstants.ACTIVITYREPORT_TIMEZONE+","
					+DataBaseConstants.ACTIVITYREPORT_CALORIE+","
					+DataBaseConstants.ACTIVITYREPORT_STEPLENGTH+","
					+DataBaseConstants.ACTIVITYREPORT_STEPS+","
					+DataBaseConstants.ACTIVITYREPORT_PLANSTEPS+","
					+DataBaseConstants.ACTIVITYREPORT_PLANCALORIE+","
					+DataBaseConstants.ACTIVITYREPORT_CITY+","
					+DataBaseConstants.ACTIVITYREPORT_WEATHER+","
					+DataBaseConstants.ACTIVITYREPORT_COMMENT+","
					+DataBaseConstants.ACTIVITYREPORT_MEASURETIME+","
					+DataBaseConstants.ACTIVITYREPORT_MECHINETYPE+","
					+DataBaseConstants.ACTIVITYREPORT_MECHINEDEVICEID+","
					+DataBaseConstants.ACTIVITYREPORT_IHEALTHID+
					")VALUES("
					+((Data_AM_ActivityDayReport)obj).getChangeType()+", "
					+((Data_AM_ActivityDayReport)obj).getLastChangeTime()+", '"
					+((Data_AM_ActivityDayReport)obj).getPhoneDataID()+"', "
					+((Data_AM_ActivityDayReport)obj).getPhoneCreateTime()+", "
					+((Data_AM_ActivityDayReport)obj).getLat()+", "
					+((Data_AM_ActivityDayReport)obj).getLon()+", "
					+((Data_AM_ActivityDayReport)obj).getTimeZone()+", "

					+((Data_AM_ActivityDayReport)obj).getCalorie()+", "
					+((Data_AM_ActivityDayReport)obj).getStepLength()+", "
					+((Data_AM_ActivityDayReport)obj).getSteps()+", "
					+((Data_AM_ActivityDayReport)obj).getPlanSteps()+", "
					+((Data_AM_ActivityDayReport)obj).getPlanCalorie()+", '"

					+((Data_AM_ActivityDayReport)obj).getCity()+"', '"
					+((Data_AM_ActivityDayReport)obj).getWeather()+"', '"
					+((Data_AM_ActivityDayReport)obj).getComment()+"', "

					+((Data_AM_ActivityDayReport)obj).getMeasureTime()+", '"
					+((Data_AM_ActivityDayReport)obj).getMechineType()+"', '"
					+((Data_AM_ActivityDayReport)obj).getMechineDeviceID()+"', '"

					+((Data_AM_ActivityDayReport)obj).getiHealthID()+"');";

		}
		if(tableName.equals(DataBaseConstants.TABLE_TB_AM_SLEEP)){
			sql="";
			sql="insert into "+DataBaseConstants.TABLE_TB_AM_SLEEP+" ("
					+DataBaseConstants.SLEEP_CHANGETYPE+","
					+DataBaseConstants.SLEEP_LASTCHANGETIME+","
					+DataBaseConstants.SLEEP_PHONEDATAID+","
					+DataBaseConstants.SLEEP_PHONECREATETIME+","
					+DataBaseConstants.SLEEP_LAT+","
					+DataBaseConstants.SLEEP_LON+","
					+DataBaseConstants.SLEEP_TIMEZONE+","

					+DataBaseConstants.SLEEP_SLEEPLEVEL+","
					+DataBaseConstants.SLEEP_TIMESECTIONID+","

					+DataBaseConstants.SLEEP_MEASURETIME+","
					+DataBaseConstants.SLEEP_MECHINETYPE+","
					+DataBaseConstants.SLEEP_MECHINEDEVICEID+","

					+DataBaseConstants.SLEEP_IHEALTHID+

					")VALUES("

					+((Data_AM_Sleep)obj).getChangeType()+", "
					+((Data_AM_Sleep)obj).getLastChangeTime()+", '"
					+((Data_AM_Sleep)obj).getPhoneDataID()+"', "
					+((Data_AM_Sleep)obj).getPhoneCreateTime()+", "
					+((Data_AM_Sleep)obj).getLat()+", "
					+((Data_AM_Sleep)obj).getLon()+", "
					+((Data_AM_Sleep)obj).getTimeZone()+", "

					+((Data_AM_Sleep)obj).getSleepLevel()+", '"
					+((Data_AM_Sleep)obj).getTimeSectionId()+"', "

					+((Data_AM_Sleep)obj).getMeasureTime()+", '"
					+((Data_AM_Sleep)obj).getMechineType()+"', '"
					+((Data_AM_Sleep)obj).getMechineDeviceID()+"', '"

					+((Data_AM_Sleep)obj).getiHealthID()+"');";

		}
		if(tableName.equals(DataBaseConstants.TABLE_TB_AM_SLEEPREPORT)){
			sql="";
			sql="insert into "+DataBaseConstants.TABLE_TB_AM_SLEEPREPORT+" ("
					+DataBaseConstants.SLEEPREPORT_CHANGETYPE+","
					+DataBaseConstants.SLEEPREPORT_LASTCHANGETIME+","
					+DataBaseConstants.SLEEPREPORT_PHONEDATAID+","
					+DataBaseConstants.SLEEPREPORT_PHONECREATETIME+","
					+DataBaseConstants.SLEEPREPORT_LAT+","
					+DataBaseConstants.SLEEPREPORT_LON+","
					+DataBaseConstants.SLEEPREPORT_TIMEZONE+","

					+DataBaseConstants.SLEEPREPORT_AWAKE+","
					+DataBaseConstants.SLEEPREPORT_DEEPSLEEP+","
					+DataBaseConstants.SLEEPREPORT_FALLSLEEP+","
					+DataBaseConstants.SLEEPREPORT_SLEEP+","
					+DataBaseConstants.SLEEPREPORT_AWAKENTIMES+","
					+DataBaseConstants.SLEEPREPORT_SLEEPSTARTTIME+","
					+DataBaseConstants.SLEEPREPORT_SLEEPENDTIME+","
					+DataBaseConstants.SLEEPREPORT_TIMESECTIONID+","
					+DataBaseConstants.SLEEPREPORT_NAP+","

					+DataBaseConstants.SLEEPREPORT_CITY+","
					+DataBaseConstants.SLEEPREPORT_WEATHER+","
					+DataBaseConstants.SLEEPREPORT_COMMENT+","

					+DataBaseConstants.SLEEPREPORT_MEASURETIME+","
					+DataBaseConstants.SLEEPREPORT_MECHINETYPE+","
					+DataBaseConstants.SLEEPREPORT_MECHINEDEVICEID+","

					+DataBaseConstants.SLEEPREPORT_MOOD+","
					+DataBaseConstants.SLEEPREPORT_ACTIVITY+","

					+DataBaseConstants.SLEEPREPORT_IHEALTHID+

					")VALUES("

					+((Data_AM_SleepSectionReport)obj).getChangeType()+", "
					+((Data_AM_SleepSectionReport)obj).getLastChangeTime()+", '"
					+((Data_AM_SleepSectionReport)obj).getPhoneDataID()+"', "
					+((Data_AM_SleepSectionReport)obj).getPhoneCreateTime()+", "
					+((Data_AM_SleepSectionReport)obj).getLat()+", "
					+((Data_AM_SleepSectionReport)obj).getLon()+", "
					+((Data_AM_SleepSectionReport)obj).getTimeZone()+", "

					+((Data_AM_SleepSectionReport)obj).getAwake()+", "
					+((Data_AM_SleepSectionReport)obj).getDeepSleep()+", "
					+((Data_AM_SleepSectionReport)obj).getFallSleep()+", "
					+((Data_AM_SleepSectionReport)obj).getSleep()+", "
					+((Data_AM_SleepSectionReport)obj).getAwakenTimes()+", "
					+((Data_AM_SleepSectionReport)obj).getSleepStartTime()+", "
					+((Data_AM_SleepSectionReport)obj).getSleepEndTime()+", '"
					+((Data_AM_SleepSectionReport)obj).getTimeSectionId()+"', "
					+((Data_AM_SleepSectionReport)obj).getNap()+", '"

					+((Data_AM_SleepSectionReport)obj).getCity()+"', '"
					+((Data_AM_SleepSectionReport)obj).getWeather()+"', '"
					+((Data_AM_SleepSectionReport)obj).getComment()+"', "

					+((Data_AM_SleepSectionReport)obj).getMeasureTime()+", '"
					+((Data_AM_SleepSectionReport)obj).getMechineType()+"', '"
					+((Data_AM_SleepSectionReport)obj).getMechineDeviceID()+"', "

					+((Data_AM_SleepSectionReport)obj).getMood()+", "
					+((Data_AM_SleepSectionReport)obj).getActivity()+", '"

					+((Data_AM_SleepSectionReport)obj).getiHealthID()+"');";

		}
		if (tableName.equals(DataBaseConstants.TABLE_TB_SWIMSECTION)) {
			sql = "insert into "
					+ DataBaseConstants.TABLE_TB_SWIMSECTION + " ("
					+ DataBaseConstants.SWIMSECTION_CITY + ","
					+ DataBaseConstants.SWIMSECTION_DEVICEID + ","
					+ DataBaseConstants.SWIMSECTION_DEVICESOURCE + ","
					+ DataBaseConstants.SWIMSECTION_LAT + ","
					+ DataBaseConstants.SWIMSECTION_LON + ","
					+ DataBaseConstants.SWIMSECTION_LASTCHANGETIME + ","
					+ DataBaseConstants.SWIMSECTION_NOTE + ","
					+ DataBaseConstants.SWIMSECTION_NOTETS + ","
					+ DataBaseConstants.SWIMSECTION_SWIMCOASTTIME + ","
					+ DataBaseConstants.SWIMSECTION_ENDTIME + ","
					+ DataBaseConstants.SWIMSECTION_STARTTIME + ","
					+ DataBaseConstants.SWIMSECTION_POOLLENGTH + ","
					+ DataBaseConstants.SWIMSECTION_DATAID + ","
					+ DataBaseConstants.SWIMSECTION_SPENDTIMEBACKSTROKE + ","
					+ DataBaseConstants.SWIMSECTION_SPENDTIMEBREASTSTROKE + ","
					+ DataBaseConstants.SWIMSECTION_SPENDTIMEFREESTROKE+ ","
					+ DataBaseConstants.SWIMSECTION_SPENDTIMEUNRECOGNIZED + ","
					+ DataBaseConstants.SWIMSECTION_SUMCALORIES + ","
					+ DataBaseConstants.SWIMSECTION_SUMTHRASHTIMES + ","
					+ DataBaseConstants.SWIMSECTION_SUMTRIPCOUNT + ","
					+ DataBaseConstants.SWIMSECTION_TIMEZONE + ","
					+ DataBaseConstants.SWIMSECTION_WEATHER + ","
					+ DataBaseConstants.SWIMSECTION_IHEALTHCLOUD
					+ ")VALUES('"
					+ ((Data_TB_SwimSection) obj).getSwimSection_City() + "','"
					+ ((Data_TB_SwimSection) obj).getSwimSection_DataID() + "','"
					+ ((Data_TB_SwimSection) obj).getSwimSection_DeviceSource() + "',"
					+ ((Data_TB_SwimSection) obj).getSwimSection_Lat() + ","
					+ ((Data_TB_SwimSection) obj).getSwimSection_Lon() + ","
					+ ((Data_TB_SwimSection) obj).getSwimSection_LastChangeTime() + ",'"
					+ ((Data_TB_SwimSection) obj).getSwimSection_Note().replace("'", "''") + "',"
					+ ((Data_TB_SwimSection) obj).getSwimSection_NoteTS() + ","
					+ ((Data_TB_SwimSection) obj).getSwimSection_SwimCoastTime() + ","
					+ ((Data_TB_SwimSection) obj).getSwimSection_Endtime() + ","
					+ ((Data_TB_SwimSection) obj).getSwimSection_StartTime() + ","
					+ ((Data_TB_SwimSection) obj).getSwimSection_PoolLength() + ",'"
					+ ((Data_TB_SwimSection) obj).getSwimSection_DataID() + "',"
					+ ((Data_TB_SwimSection) obj).getSwimSection_SpendTimeBackStroke() + ","
					+ ((Data_TB_SwimSection) obj).getSwimSection_SpendTimeBreastStroke() + ","
					+ ((Data_TB_SwimSection) obj).getSwimSection_SpendTimeFreeStroke() + ","
					+ ((Data_TB_SwimSection) obj).getSwimSection_SpendTimeUnrecognized() + ","
					+ ((Data_TB_SwimSection) obj).getSwimSection_SumCalories() + ","
					+ ((Data_TB_SwimSection) obj).getSwimSection_SumThrashTimes() + ","
					+ ((Data_TB_SwimSection) obj).getSwimSection_SumTripCount() + ","
					+ ((Data_TB_SwimSection) obj).getSwimSection_TimeZone() + ",'"
					+ ((Data_TB_SwimSection) obj).getSwimSection_Weather().replace("'", "''")+"','"
					+ ((Data_TB_SwimSection) obj).getSwimSection_iHealthCloud().replace("'", "''")
					+ "');";

		}

		if (tableName.equals(DataBaseConstants.TABLE_TB_SWIM)) {
			sql = "insert into "
					+ DataBaseConstants.TABLE_TB_SWIM + " ("
					+ DataBaseConstants.SWIM_CHANGETYPE + ","
					+ DataBaseConstants.SWIM_LASTCHANGETIME + ","
					+ DataBaseConstants.SWIM_PHONEDATAID + ","
					+ DataBaseConstants.SWIM_PHONECREATETIME + ","
					+ DataBaseConstants.SWIM_LAT + ","
					+ DataBaseConstants.SWIM_LON + ","
					+ DataBaseConstants.SWIM_TIMEZONE + ","
					+ DataBaseConstants.SWIM_SPENDMINUTES + ","
					+ DataBaseConstants.SWIM_PULLTIMES + ","
					+ DataBaseConstants.SWIM_STROKE + ","
					+ DataBaseConstants.SWIM_CYCLES + ","

					+ DataBaseConstants.SWIM_CUTINDIF + ","
					+ DataBaseConstants.SWIM_CUTOUTDIF + ","
					+ DataBaseConstants.SWIM_PROCESSFLAG + ","

					+ DataBaseConstants.SWIM_DISTANCE + ","
					+ DataBaseConstants.SWIM_CALORIES + ","
					+ DataBaseConstants.SWIM_CITY + ","
					+ DataBaseConstants.SWIM_TEMPERATURE + ","
					+ DataBaseConstants.SWIM_WEATHERCODE + ","
					+ DataBaseConstants.SWIM_HUMIDITY + ","
					+ DataBaseConstants.SWIM_ATMOSPHERE + ","
					+ DataBaseConstants.SWIM_COMMENTTS + ","
					+ DataBaseConstants.SWIM_ENDTIME + ","
					+ DataBaseConstants.SWIM_COMMENTPIC + ","
					+ DataBaseConstants.SWIM_MECHINETYPE + ","
					+ DataBaseConstants.SWIM_MECHINEDEVICEID + ","
					+ DataBaseConstants.SWIM_IHEALTHCLOUD + ","
					+ DataBaseConstants.SWIM_COMMENTNOTE
					+ ")VALUES("
					+ ((Data_TB_Swim) obj).getSwim_ChangeType() + ","
					+ ((Data_TB_Swim) obj).getSwim_LastChangeTime() + ",'"
					+ ((Data_TB_Swim) obj).getSwim_PhoneDataID() + "',"
					+ ((Data_TB_Swim) obj).getSwim_PhoneCreateTime() + ","
					+ ((Data_TB_Swim) obj).getSwim_Lat() + ","
					+ ((Data_TB_Swim) obj).getSwim_Lon() + ","
					+ ((Data_TB_Swim) obj).getSwim_TimeZone() + ","
					+ ((Data_TB_Swim) obj).getSwim_SpendMinutes() + ","
					+ ((Data_TB_Swim) obj).getSwim_PullTimes() + ","
					+ ((Data_TB_Swim) obj).getSwim_Stroke() + ","
					+ ((Data_TB_Swim) obj).getSwim_Cycles() + ","

					+ ((Data_TB_Swim) obj).getSwim_CutInTimeDif() + ","
					+ ((Data_TB_Swim) obj).getSwim_CutOutTimeDif() + ","
					+ ((Data_TB_Swim) obj).getSwim_ProcessFlag() + ","

					+ ((Data_TB_Swim) obj).getSwim_Distance() + ","
					+ ((Data_TB_Swim) obj).getSwim_Calories() + ",'"
					+ ((Data_TB_Swim) obj).getSwim_City() + "','"
					+ ((Data_TB_Swim) obj).getSwim_Temperature() + "','"
					+ ((Data_TB_Swim) obj).getSwim_WeatherCode() + "','"
					+ ((Data_TB_Swim) obj).getSwim_Humidity() + "','"
					+ ((Data_TB_Swim) obj).getSwim_Atmosphere() + "',"
					+ ((Data_TB_Swim) obj).getSwim_CommentTS() + ","
					+ ((Data_TB_Swim) obj).getSwim_endtime() + ",'"
					+ ((Data_TB_Swim) obj).getSwim_CommentPic() + "','"
					+ ((Data_TB_Swim) obj).getSwim_MechineType() + "','"
					+ ((Data_TB_Swim) obj).getSwim_MechineDeviceID() + "','"
					+ ((Data_TB_Swim) obj).getSwim_iHealthCloud().replace("'", "''") + "','"
					+ ((Data_TB_Swim) obj).getSwim_CommentNote().replace("'", "''")
					+ "');";
		}

		if (tableName.equals(DataBaseConstants.TABLE_TB_WORKOUT)) {
			sql = "insert into "
					+ DataBaseConstants.TABLE_TB_WORKOUT + " ("
					+ DataBaseConstants.WORKOUT_CHANGETYPE + ","
					+ DataBaseConstants.WORKOUT_LASTCHANGETIME + ","
					+ DataBaseConstants.WORKOUT_PHONEDATAID + ","
					+ DataBaseConstants.WORKOUT_PHONECREATETIME + ","
					+ DataBaseConstants.WORKOUT_LAT + ","
					+ DataBaseConstants.WORKOUT_LON + ","
					+ DataBaseConstants.WORKOUT_TIMEZONE + ","
					+ DataBaseConstants.WORKOUT_SPENDMINUTES + ","
					+ DataBaseConstants.WORKOUT_STEPS + ","
					+ DataBaseConstants.WORKOUT_DISTANCE + ","
					+ DataBaseConstants.WORKOUT_CALORIES + ","
					+ DataBaseConstants.WORKOUT_CITY + ","
					+ DataBaseConstants.WORKOUT_TEMPERATURE + ","
					+ DataBaseConstants.WORKOUT_WEATHERCODE + ","
					+ DataBaseConstants.WORKOUT_HUMIDITY + ","
					+ DataBaseConstants.WORKOUT_ATMOSPHERE + ","
					+ DataBaseConstants.WORKOUT_COMMENTTS + ","
					+ DataBaseConstants.WORKOUT_ENDTIME + ","
					+ DataBaseConstants.WORKOUT_COMMENTPIC + ","
					+ DataBaseConstants.WORKOUT_MECHINETYPE + ","
					+ DataBaseConstants.WORKOUT_MECHINEDEVICEID + ","
					+ DataBaseConstants.WORKOUT_IHEALTHCLOUD + ","
					+ DataBaseConstants.WORKOUT_COMMENTNOTE
					+ ")VALUES("
					+ ((Data_TB_Workout) obj).getWorkout_ChangeType() + ","
					+ ((Data_TB_Workout) obj).getWorkout_LastChangeTime() + ",'"
					+ ((Data_TB_Workout) obj).getWorkout_PhoneDataID() + "',"
					+ ((Data_TB_Workout) obj).getWorkout_PhoneCreateTime() + ","
					+ ((Data_TB_Workout) obj).getWorkout_Lat() + ","
					+ ((Data_TB_Workout) obj).getWorkout_Lon() + ","
					+ ((Data_TB_Workout) obj).getWorkout_TimeZone() + ","
					+ ((Data_TB_Workout) obj).getWorkout_SpendMinutes() + ","
					+ ((Data_TB_Workout) obj).getWorkout_Steps() + ","
					+ ((Data_TB_Workout) obj).getWorkout_Distance() + ","
					+ ((Data_TB_Workout) obj).getWorkout_Calories() + ",'"
					+ ((Data_TB_Workout) obj).getWorkout_City() + "','"
					+ ((Data_TB_Workout) obj).getWorkout_Temperature() + "','"
					+ ((Data_TB_Workout) obj).getWorkout_WeatherCode() + "','"
					+ ((Data_TB_Workout) obj).getWorkout_Humidity() + "','"
					+ ((Data_TB_Workout) obj).getWorkout_Atmosphere() + "',"
					+ ((Data_TB_Workout) obj).getWorkout_CommentTS() + ","
					+ ((Data_TB_Workout) obj).getWorkout_endtime() + ",'"
					+ ((Data_TB_Workout) obj).getWorkout_CommentPic() + "','"
					+ ((Data_TB_Workout) obj).getWorkout_MechineType() + "','"
					+ ((Data_TB_Workout) obj).getWorkout_MechineDeviceID() + "','"
					+ ((Data_TB_Workout) obj).getWorkout_iHealthCloud().replace("'", "''") + "','"
					+ ((Data_TB_Workout) obj).getWorkout_CommentNote().replace("'", "''")
					+ "');";
		}


		/**
		 *  BG
		 */
		if(tableName.equals(DataBaseConstants.TABLE_TB_BGRESULT)){
			sql="";
			sql="insert into "+DataBaseConstants.TABLE_TB_BGRESULT+" ("
					+DataBaseConstants.BGRESULT_CHANGETYPE+","
					+DataBaseConstants.BGRESULT_LASTCHANGETIME+","
					+DataBaseConstants.BGRESULT_PHONEDATAID+","
					+DataBaseConstants.BGRESULT_PHONECREATETIME+","
					+DataBaseConstants.BGRESULT_LAT+","
					+DataBaseConstants.BGRESULT_LON+","
					+DataBaseConstants.BGRESULT_TIMEZONE+","
					+DataBaseConstants.BGRESULT_BGVALUE+","
					+DataBaseConstants.BGRESULT_MEDICATION+","
					+DataBaseConstants.BGRESULT_MTIMETYPE+","
					+DataBaseConstants.BGRESULT_MEASURETYPE+","
					+DataBaseConstants.BGRESULT_MEASURETIME+","
					+DataBaseConstants.BGRESULT_NOTE+","
					+DataBaseConstants.BGRESULT_MECHINETYPE+","
					+DataBaseConstants.BGRESULT_MECHINEDEVICEID+","
					+DataBaseConstants.BGRESULT_BOTTLEID+","
					+DataBaseConstants.BGRESULT_SPORTS+","
					+DataBaseConstants.BGRESULT_SNACKS+","
					+DataBaseConstants.BGRESULT_EFFECTIVE+","
					+DataBaseConstants.BGRESULT_IHEALTHID+
					")VALUES("
					+((Data_BG_Result)obj).getChangeType()+", "
					+((Data_BG_Result)obj).getLastChangeTime()+", '"
					+((Data_BG_Result)obj).getPhoneDataID()+"', "
					+((Data_BG_Result)obj).getPhoneCreateTime()+", "
					+((Data_BG_Result)obj).getLat()+", "
					+((Data_BG_Result)obj).getLon()+", "
					+((Data_BG_Result)obj).getTimeZone()+", "
					+((Data_BG_Result)obj).getBGValue()+", "
					+((Data_BG_Result)obj).getMedication()+", "
					+((Data_BG_Result)obj).getMTimeType()+", "
					+((Data_BG_Result)obj).getMeasureType()+", "
					+((Data_BG_Result)obj).getMeasureTime()+", '"
					+((Data_BG_Result)obj).getNote()+"', '"
					+((Data_BG_Result)obj).getMechineType()+"', '"
					+((Data_BG_Result)obj).getMechineDeviceID()+"', '"
					+((Data_BG_Result)obj).getBottleId()+"', "
					+((Data_BG_Result)obj).getSports()+", "
					+((Data_BG_Result)obj).getSnacks()+", "
					+((Data_BG_Result)obj).getEffective()+", '"
					+((Data_BG_Result)obj).getiHealthID()+"');";
		}

		/**
		 *  BP
		 */
		if(tableName.equals(DataBaseConstants.TABLE_TB_BPMEASURERESULT)){
			sql="";
			sql="insert into "+DataBaseConstants.TABLE_TB_BPMEASURERESULT+" ("

					+DataBaseConstants.BPMEASURERESULT_CHANGETYPE+","
					+DataBaseConstants.BPMEASURERESULT_LASTCHANGETIME+","
					+DataBaseConstants.BPMEASURERESULT_PHONEDATAID+","
					+DataBaseConstants.BPMEASURERESULT_PHONECREATETIME+","
					+DataBaseConstants.BPMEASURERESULT_LAT+","
					+DataBaseConstants.BPMEASURERESULT_LON+","
					+DataBaseConstants.BPMEASURERESULT_TIMEZONE+","
					+DataBaseConstants.BPMEASURERESULT_BPL+","
					+DataBaseConstants.BPMEASURERESULT_LP+","
					+DataBaseConstants.BPMEASURERESULT_HP+","
					+DataBaseConstants.BPMEASURERESULT_HR+","
					+DataBaseConstants.BPMEASURERESULT_ISARR+","
					+DataBaseConstants.BPMEASURERESULT_WL+","
					+DataBaseConstants.BPMEASURERESULT_MEASURETYPE+","
					+DataBaseConstants.BPMEASURERESULT_MEASURETIME+","
					+DataBaseConstants.BPMEASURERESULT_NOTE+","
					+DataBaseConstants.BPMEASURERESULT_MECHINETYPE+","
					+DataBaseConstants.BPMEASURERESULT_MECHINEDEVICEID+","
					//云5.0新增BP字段
					+ DataBaseConstants.BPMEASURERESULT_NOTETS +","
					+ DataBaseConstants.BPMEASURERESULT_BPMOOD +","
					+ DataBaseConstants.BPMEASURERESULT_BPACTIVITY +","
					+ DataBaseConstants.BPMEASURERESULT_TEMP +","
					+ DataBaseConstants.BPMEASURERESULT_WEATHER +","
					+ DataBaseConstants.BPMEASURERESULT_HUMIDITY +","
					+ DataBaseConstants.BPMEASURERESULT_VISIBILITY +","
					+ DataBaseConstants.BPMEASURERESULT_USEDUSERID +","

					+DataBaseConstants.BPMEASURERESULT_UN+
					")VALUES("
					+((Data_BP_Result)	obj).getChangeType()+", "
					+((Data_BP_Result)	obj).getLastChangeTime()+", '"
					+((Data_BP_Result)	obj).getPhoneDataID()+"', "
					+((Data_BP_Result)	obj).getPhoneCreateTime()+", "
					+((Data_BP_Result)	obj).getLat()+", "
					+((Data_BP_Result)	obj).getLon()+", "
					+((Data_BP_Result)	obj).getTimeZone()+", "
					+((Data_BP_Result)	obj).getBPL()+", "
					+((Data_BP_Result)	obj).getLP()+", "
					+((Data_BP_Result)	obj).getHP()+", "
					+((Data_BP_Result)	obj).getHR()+", "
					+((Data_BP_Result)	obj).getIsArr()+", '"
					+((Data_BP_Result)	obj).getWL()+"', "
					+((Data_BP_Result)	obj).getMeasureType()+", "
					+((Data_BP_Result)	obj).getMeasureTime()+", '"
					+((Data_BP_Result)	obj).getNote()+"', '"
					+((Data_BP_Result)	obj).getMechineType()+"', '"
					+((Data_BP_Result)	obj).getMechineDeviceID()+"', "
					//5.0云新增字段
					+((Data_BP_Result)	obj).getNoteTS()+", "
					+((Data_BP_Result)	obj).getBpMood()+", "
					+((Data_BP_Result)	obj).getBpActivity()+", '"
					+((Data_BP_Result)	obj).getTemp()+"', '"
					+((Data_BP_Result)	obj).getWeather()+"', '"
					+((Data_BP_Result)	obj).getHumidity()+"', '"
					+((Data_BP_Result)	obj).getVisibility()+"', "
					+((Data_BP_Result)	obj).getUsedUserid()+", '"

					+((Data_BP_Result)	obj).getiHealthCloud()+"');";
		}

		/**
		 *  HS
		 */
		if(tableName.equals(DataBaseConstants.TABLE_TB_HSRESULT)){
			sql="";
			sql="insert into "+DataBaseConstants.TABLE_TB_HSRESULT+" ("
					+DataBaseConstants.HSRESULT_CHANGETYPE+","
					+DataBaseConstants.HSRESULT_LASTCHANGETIME+","
					+DataBaseConstants.HSRESULT_PHONEDATAID+","
					+DataBaseConstants.HSRESULT_PHONECREATETIME+","
					+DataBaseConstants.HSRESULT_LAT+","
					+DataBaseConstants.HSRESULT_LON+","
					+DataBaseConstants.HSRESULT_TIMEZONE+","
					+DataBaseConstants.HSRESULT_MEASURETYPE+","
					+DataBaseConstants.HSRESULT_MEASURETIME+","
					+DataBaseConstants.HSRESULT_NOTE+","
					+DataBaseConstants.HSRESULT_MECHINETYPE+","
					+DataBaseConstants.HSRESULT_MECHINEDEVICEID+","
					//add
					+DataBaseConstants.HSRESULT_BMI+","
					+DataBaseConstants.HSRESULT_BONEVALUE+","
					+DataBaseConstants.HSRESULT_DCI+","
					+DataBaseConstants.HSRESULT_FATVALUE+","
					+DataBaseConstants.HSRESULT_MUSCALEVALUE+","
					+DataBaseConstants.HSRESULT_WATERVALUE+","
					+DataBaseConstants.HSRESULT_WEIGHTVALUE+","
					+DataBaseConstants.HSRESULT_VISCERAFATLEVEL+","
					+DataBaseConstants.HSRESULT_EMOTIONS+","
					+DataBaseConstants.HSRESULT_NOTETS+","
					+DataBaseConstants.HSRESULT_MOOD+","
					+DataBaseConstants.HSRESULT_ACTIVITY+","
					+DataBaseConstants.HSRESULT_TEMP+","
					+DataBaseConstants.HSRESULT_WEATHER+","
					+DataBaseConstants.HSRESULT_HUMIDITY+","
					+DataBaseConstants.HSRESULT_VISIBILITY+","
					+DataBaseConstants.HSRESULT_USEDUSERID+","

					+DataBaseConstants.HSRESULT_IHEALTHID+
					")VALUES("
					+((Data_HS_Result)obj).getChangeType()+", "
					+((Data_HS_Result)obj).getLastChangeTime()+", '"
					+((Data_HS_Result)obj).getPhoneDataID()+"', "
					+((Data_HS_Result)obj).getPhoneCreateTime()+", "
					+((Data_HS_Result)obj).getLat()+", "
					+((Data_HS_Result)obj).getLon()+", "
					+((Data_HS_Result)obj).getTimeZone()+", "
					+((Data_HS_Result)obj).getMeasureType()+", "
					+((Data_HS_Result)obj).getMeasureTime()+", '"
					+((Data_HS_Result)obj).getNote()+"', '"
					+((Data_HS_Result)obj).getMechineType()+"', '"
					+((Data_HS_Result)obj).getMechineDeviceID()+"', "
					//add
					+((Data_HS_Result)obj).getBMI()+", "
					+((Data_HS_Result)obj).getBoneValue()+", "
					+((Data_HS_Result)obj).getDCI()+", "
					+((Data_HS_Result)obj).getFatValue()+", "
					+((Data_HS_Result)obj).getMuscaleValue()+", "
					+((Data_HS_Result)obj).getWaterValue()+", "
					+((Data_HS_Result)obj).getWeightValue()+", "
					+((Data_HS_Result)obj).getVisceraFatLevel()+", "
					+((Data_HS_Result)obj).getEmotions()+", "
					+((Data_HS_Result)obj).getNoteTS()+", "
					+((Data_HS_Result)obj).getMood()+", "
					+((Data_HS_Result)obj).getActivity()+", '"
					+((Data_HS_Result)obj).getTemp()+"', '"
					+((Data_HS_Result)obj).getWeather()+"', '"
					+((Data_HS_Result)obj).getHumidity()+"', '"
					+((Data_HS_Result)obj).getVisibility()+"', "
					+((Data_HS_Result)obj).getUsedUserid()+", '"

					+((Data_HS_Result)obj).getiHealthID()+"');";

		}

		/**
		 *  PO
		 */
		if (tableName.equals(DataBaseConstants.TABLE_TB_PO)) {
			try {
				sql = "insert into " + DataBaseConstants.TABLE_TB_PO + " ("
						+ DataBaseConstants.PO_CHANGETYPE + ","
						+ DataBaseConstants.PO_LASTCHANGETIME + ","
						+ DataBaseConstants.PO_PHONEDATAID + ","
						+ DataBaseConstants.PO_PHONECREATETIME + ","
						+ DataBaseConstants.PO_LAT + ","
						+ DataBaseConstants.PO_LON + ","
						+ DataBaseConstants.PO_TIMEZONE + ","
						+ DataBaseConstants.PO_ACTIVITY + ","
						+ DataBaseConstants.PO_WAVE + ","
						+ DataBaseConstants.PO_PR + ","
						+ DataBaseConstants.PO_RESULT + ","
						+ DataBaseConstants.PO_FLOWRATE + ","
						+ DataBaseConstants.PO_RESULTSOURCE + ","
						+ DataBaseConstants.PO_NOTE + ","
						+ DataBaseConstants.PO_NOTETS + ","
						+ DataBaseConstants.PO_MEASURETIME + ","
						+ DataBaseConstants.PO_MECHINETYPE + ","
						+ DataBaseConstants.PO_MECHINEDEVICEID + ","
						+ DataBaseConstants.PO_MOOD + ","
						+ DataBaseConstants.PO_IHEALTHID
						+ ")VALUES("
						+ ((Data_PO_Result) obj).getChangeType() + ","
						+ ((Data_PO_Result) obj).getLastChangeTime() + ",'"
						+ ((Data_PO_Result) obj).getPhoneDataID() + "',"
						+ ((Data_PO_Result) obj).getPhoneCreateTime() + ","
						+ ((Data_PO_Result) obj).getLat() + ","
						+ ((Data_PO_Result) obj).getLon() + ","
						+ ((Data_PO_Result) obj).getTimeZone() + ","
						+ ((Data_PO_Result) obj).getActivity() + ",'"
						+ ((Data_PO_Result) obj).getWave() + "',"
						+ ((Data_PO_Result) obj).getPR() + ","
						+ ((Data_PO_Result) obj).getResult() + ","
						+ ((Data_PO_Result) obj).getFlowrate() + ","
						+ ((Data_PO_Result) obj).getResultSource() + ",'"
						+ ((Data_PO_Result) obj).getNote().replace("'", "''") + "',"
						+ ((Data_PO_Result) obj).getNoteTS() + ","
						+ ((Data_PO_Result) obj).getMeasureTime() + ",'"
						+ ((Data_PO_Result) obj).getMechineType() + "','"
						+ ((Data_PO_Result) obj).getMechineDeviceID() + "',"
						+ ((Data_PO_Result) obj).getMood() + ",'"
						+ ((Data_PO_Result) obj).getiHealthID().replace("'", "''") + "');";

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		try {
			this.db.beginTransaction(); // 手动设置开始事务
			this.db.execSQL(sql);
			this.db.setTransactionSuccessful(); // 设置事务处理成功，不设置会自动回滚不提交
			iResult = true;
		} catch (SQLException e) {
			e.printStackTrace();
			iResult = false;
		} finally {
			this.db.endTransaction(); // 处理完成
			sql = null;
		}
		return iResult;
	}
}
