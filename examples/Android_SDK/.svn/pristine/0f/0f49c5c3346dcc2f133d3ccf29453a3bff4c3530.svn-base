
package com.ihealth.communication.cloud.data;

import com.ihealth.communication.cloud.tools.AppsDeviceParameters;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.ihealth.communication.utils.Log;

/**
 * @author zhaoyongguang 
 * @version 2  just as sdk2.0
 */
public class DataBaseOpenHelper extends SQLiteOpenHelper {

	private static final String TAG = "DataBaseOpenHelper";
	private Context mContext;
	private static DataBaseOpenHelper dbT;
	private static SQLiteDatabase myDatabase;
	private static final String DATABASE_NAME = "iHealthLibrarySDK.DB";
	private static final int DATABASE_VERSION = 3;

	// 双重检查加锁的单例
	public static SQLiteDatabase getInstance(Context context) {
		// DataBaseOpenHelper
		if (dbT == null) {
			synchronized (DataBaseOpenHelper.class) {
				if (dbT == null) {
					dbT = new DataBaseOpenHelper(context);
				}
			}
		}
		// DataBaseOpenHelper.getWritableDatabase
		if (myDatabase == null) {
			synchronized (DataBaseOpenHelper.class) {
				if (myDatabase == null && dbT != null) {
					myDatabase = dbT.getWritableDatabase();
				}
			}
		}
		return myDatabase;
	}

	private DataBaseOpenHelper(Context context) {
		super(context,  DATABASE_NAME, null, DATABASE_VERSION);
		this.mContext = context;
		// myDatabase = getInstance(context);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d(TAG, "DataBaseOpenHelper onCreate()");
		this.tableCreat(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase myDataBase, int oldVersion, int newVersion) {
		Log.d(TAG, "onUpgrade() oldVersion:" + oldVersion + " newVersion:" + newVersion);
		dropTable(myDataBase);
		tableCreat(myDataBase);
	}

	public Boolean dropTable(SQLiteDatabase db) {

		Boolean isResult = false;

		Cursor cursor = db.rawQuery("select name from sqlite_master where type='table' order by name", null);
		while (cursor.moveToNext()) {
			// 遍历出表名
			String name = cursor.getString(0);
			Log.i("TAG", "dropTable  = " + name);
			String sql = "DROP TABLE  " + name;

			try {
				db.execSQL(sql);
				isResult = true;
			} catch (SQLException e) {
				e.printStackTrace();
				isResult = false;
			} finally {

			}
		}
		return isResult;
	}

	/**
	 * @param db
	 */
	public void tableCreat(SQLiteDatabase db) {
		Log.d(TAG, "tableCreat()  start");

		Create_TABLE_TB_AM_ACTIVITY(db);
		Create_TABLE_TB_AM_ACTIVITYREPORT(db);
		Create_TABLE_TB_AM_SLEEP(db);
		Create_TABLE_TB_AM_SLEEPREPORT(db);
		Create_TABLE_TB_AM_WORKOUT(db);
		Create_TABLE_TB_AM_SWIM(db);
		Create_TABLE_TB_AM_SWIMREPORT(db);

		Create_TABLE_TB_BGRESULT(db);
		Create_TABLE_TB_BPMEASURERESULT(db);
		Create_TABLE_TB_HSRESULT(db);
		Create_TABLE_TB_PO(db);

		Log.d(TAG, "tableCreat()  end");
	}

	private void Create_TABLE_TB_AM_ACTIVITY(SQLiteDatabase db) {
		String sql = "";
		sql = "CREATE TABLE IF NOT EXISTS " + DataBaseConstants.TABLE_TB_AM_ACTIVITY+ " (" 
				+ DataBaseConstants.ACTIVITY_CHANGETYPE        + " int(4,0) default 1," 
				+ DataBaseConstants.ACTIVITY_LASTCHANGETIME    + " long(25,0)," 
				+ DataBaseConstants.ACTIVITY_PHONEDATAID       + " varchar(128,0)," 
				+ DataBaseConstants.ACTIVITY_PHONECREATETIME   + " long(25,0)," 
				+ DataBaseConstants.ACTIVITY_LAT               + " double(64,0) default 0.0," 
				+ DataBaseConstants.ACTIVITY_LON               + " double(64,0) default 0.0," 
				+ DataBaseConstants.ACTIVITY_TIMEZONE          + " float(8,0)," 

		        + DataBaseConstants.ACTIVITY_CALORIE           + " float(16,0) default 0.0,"
		        + DataBaseConstants.ACTIVITY_STEPLENGTH        + " int(4,0) default 0," 
		        + DataBaseConstants.ACTIVITY_STEPS             + " int(4,0) default 0," 
		        + DataBaseConstants.ACTIVITY_SUMCALORIE        + " int(4,0) default 0," 
		        + DataBaseConstants.ACTIVITY_SUMSTEPS          + " int(4,0) default 0," 

				+ DataBaseConstants.ACTIVITY_MEASURETIME       + " long(25,0),"
				+ DataBaseConstants.ACTIVITY_MECHINETYPE       + " varchar(128,0),"
				+ DataBaseConstants.ACTIVITY_MECHINEDEVICEID   + " varchar(128,0),"

				+ DataBaseConstants.ACTIVITY_IHEALTHID         + " varchar(128,0)" + ");";
		try {
			db.beginTransaction();
			db.execSQL(sql);
			db.setTransactionSuccessful();
			if(AppsDeviceParameters.isLog)
				Log.d(TAG, "1,tableCreat()  execSql: " + DataBaseConstants.TABLE_TB_AM_ACTIVITY);
		} catch (SQLException e) {
			if(AppsDeviceParameters.isLog)
				Log.d(TAG, "1,tableCreat()  execSql: " + e);
		} finally {
			db.endTransaction();
		}

	}

	private void Create_TABLE_TB_AM_ACTIVITYREPORT(SQLiteDatabase db) {
		String sql = "";
		sql = "CREATE TABLE IF NOT EXISTS " + DataBaseConstants.TABLE_TB_AM_ACTIVITYREPORT+ " (" 
				+ DataBaseConstants.ACTIVITYREPORT_CHANGETYPE        + " int(4,0) default 1," 
				+ DataBaseConstants.ACTIVITYREPORT_LASTCHANGETIME    + " long(25,0)," 
				+ DataBaseConstants.ACTIVITYREPORT_PHONEDATAID       + " varchar(128,0)," 
				+ DataBaseConstants.ACTIVITYREPORT_PHONECREATETIME   + " long(25,0)," 
				+ DataBaseConstants.ACTIVITYREPORT_LAT               + " double(64,0) default 0.0," 
				+ DataBaseConstants.ACTIVITYREPORT_LON               + " double(64,0) default 0.0," 
				+ DataBaseConstants.ACTIVITYREPORT_TIMEZONE          + " float(8,0)," 

		        + DataBaseConstants.ACTIVITYREPORT_CALORIE           + " float(16,0) default 0.0,"
		        + DataBaseConstants.ACTIVITYREPORT_STEPLENGTH        + " int(4,0) default 0," 
		        + DataBaseConstants.ACTIVITYREPORT_STEPS             + " int(4,0) default 0," 
		        + DataBaseConstants.ACTIVITYREPORT_PLANSTEPS         + " int(4,0) default 0," 
		        + DataBaseConstants.ACTIVITYREPORT_PLANCALORIE       + " float(16,0) default 0.0,"

				+ DataBaseConstants.ACTIVITYREPORT_CITY              + " varchar(128,0),"
				+ DataBaseConstants.ACTIVITYREPORT_WEATHER           + " varchar(128,0),"
				+ DataBaseConstants.ACTIVITYREPORT_COMMENT            + " varchar(128,0),"

				+ DataBaseConstants.ACTIVITYREPORT_MEASURETIME       + " long(25,0),"
				+ DataBaseConstants.ACTIVITYREPORT_MECHINETYPE       + " varchar(128,0),"
				+ DataBaseConstants.ACTIVITYREPORT_MECHINEDEVICEID   + " varchar(128,0),"

				+ DataBaseConstants.ACTIVITYREPORT_IHEALTHID         + " varchar(128,0)" + ");";
		try {
			db.beginTransaction();
			db.execSQL(sql);
			db.setTransactionSuccessful();
			if(AppsDeviceParameters.isLog)
				Log.d(TAG, "2,tableCreat()  execSql: " + DataBaseConstants.TABLE_TB_AM_ACTIVITYREPORT);
		} catch (SQLException e) {
			if(AppsDeviceParameters.isLog)
				Log.d(TAG, "2,tableCreat()  execSql: " + e);
		} finally {
			db.endTransaction();
		}
	}

	private void Create_TABLE_TB_AM_SLEEP(SQLiteDatabase db) {
		String sql = "";
		sql = "CREATE TABLE IF NOT EXISTS " + DataBaseConstants.TABLE_TB_AM_SLEEP+ " (" 
				+ DataBaseConstants.SLEEP_CHANGETYPE        + " int(4,0) default 1," 
				+ DataBaseConstants.SLEEP_LASTCHANGETIME    + " long(25,0)," 
				+ DataBaseConstants.SLEEP_PHONEDATAID       + " varchar(128,0)," 
				+ DataBaseConstants.SLEEP_PHONECREATETIME   + " long(25,0)," 
				+ DataBaseConstants.SLEEP_LAT               + " double(64,0) default 0.0," 
				+ DataBaseConstants.SLEEP_LON               + " double(64,0) default 0.0," 
				+ DataBaseConstants.SLEEP_TIMEZONE          + " float(8,0)," 

				+ DataBaseConstants.SLEEP_SLEEPLEVEL        + " int(4,0) default 0," 
				+ DataBaseConstants.SLEEP_TIMESECTIONID     + " varchar(128,0),"

				+ DataBaseConstants.SLEEP_MEASURETIME       + " long(25,0),"
				+ DataBaseConstants.SLEEP_MECHINETYPE       + " varchar(128,0),"
				+ DataBaseConstants.SLEEP_MECHINEDEVICEID   + " varchar(128,0),"

				+ DataBaseConstants.SLEEP_IHEALTHID         + " varchar(128,0)" + ");";

		try {
			db.beginTransaction();
			db.execSQL(sql);
			db.setTransactionSuccessful();
			if(AppsDeviceParameters.isLog)
				Log.d(TAG, "4,tableCreat()  execSql: " + DataBaseConstants.TABLE_TB_AM_SLEEP);
		} catch (SQLException e) {
			if(AppsDeviceParameters.isLog)
				Log.d(TAG, "4,tableCreat()  execSql: " + e);
		} finally {
			db.endTransaction();
		}

	}

	private void Create_TABLE_TB_AM_SLEEPREPORT(SQLiteDatabase db) {
		String sql = "";
		sql = "CREATE TABLE IF NOT EXISTS " + DataBaseConstants.TABLE_TB_AM_SLEEPREPORT+ " (" 
				+ DataBaseConstants.SLEEPREPORT_CHANGETYPE        + " int(4,0) default 1," 
				+ DataBaseConstants.SLEEPREPORT_LASTCHANGETIME    + " long(25,0)," 
				+ DataBaseConstants.SLEEPREPORT_PHONEDATAID       + " varchar(128,0)," 
				+ DataBaseConstants.SLEEPREPORT_PHONECREATETIME   + " long(25,0)," 
				+ DataBaseConstants.SLEEPREPORT_LAT               + " double(64,0) default 0.0," 
				+ DataBaseConstants.SLEEPREPORT_LON               + " double(64,0) default 0.0," 
				+ DataBaseConstants.SLEEPREPORT_TIMEZONE          + " float(8,0)," 

				+ DataBaseConstants.SLEEPREPORT_AWAKE             + " int(4,0) default 0," 
				+ DataBaseConstants.SLEEPREPORT_DEEPSLEEP         + " int(4,0) default 0," 
				+ DataBaseConstants.SLEEPREPORT_FALLSLEEP         + " int(4,0) default 0," 
				+ DataBaseConstants.SLEEPREPORT_SLEEP             + " int(4,0) default 0," 
				+ DataBaseConstants.SLEEPREPORT_AWAKENTIMES       + " int(4,0) default 0," 
				+ DataBaseConstants.SLEEPREPORT_SLEEPSTARTTIME    + " long(25,0),"
				+ DataBaseConstants.SLEEPREPORT_SLEEPENDTIME      + " long(25,0),"
				+ DataBaseConstants.SLEEPREPORT_TIMESECTIONID     + " varchar(128,0),"
				+ DataBaseConstants.SLEEPREPORT_NAP               + " int(4,0) default 0," 

				+ DataBaseConstants.SLEEPREPORT_CITY              + " varchar(128,0),"
				+ DataBaseConstants.SLEEPREPORT_WEATHER           + " varchar(128,0),"
				+ DataBaseConstants.SLEEPREPORT_COMMENT           + " varchar(128,0),"

				+ DataBaseConstants.SLEEPREPORT_MOOD              + " int(4,0) default 0," 
				+ DataBaseConstants.SLEEPREPORT_ACTIVITY          + " int(4,0) default 0," 

				+ DataBaseConstants.SLEEPREPORT_MEASURETIME       + " long(25,0),"
				+ DataBaseConstants.SLEEPREPORT_MECHINETYPE       + " varchar(128,0),"
				+ DataBaseConstants.SLEEPREPORT_MECHINEDEVICEID   + " varchar(128,0),"

				+ DataBaseConstants.SLEEP_IHEALTHID         + " varchar(128,0)" + ");";
		try {
			db.beginTransaction();
			db.execSQL(sql);
			db.setTransactionSuccessful();
			if(AppsDeviceParameters.isLog)
				Log.d(TAG, "5,tableCreat()  execSql: " + DataBaseConstants.TABLE_TB_AM_SLEEPREPORT);
		} catch (SQLException e) {
			if(AppsDeviceParameters.isLog)
				Log.d(TAG, "5,tableCreat()  execSql: " + e);
		} finally {
			db.endTransaction();
		}
	}

	private void Create_TABLE_TB_AM_WORKOUT(SQLiteDatabase db) {
		String sql = "";
		sql = "CREATE TABLE IF NOT EXISTS " + DataBaseConstants.TABLE_TB_WORKOUT
				+ " ("
				+ DataBaseConstants.WORKOUT_CHANGETYPE + " int(4,0),"
				+ DataBaseConstants.WORKOUT_LASTCHANGETIME + " long(10,0),"
				+ DataBaseConstants.WORKOUT_PHONEDATAID + " varchar(128,0),"
				+ DataBaseConstants.WORKOUT_PHONECREATETIME + " long(10,0),"
				+ DataBaseConstants.WORKOUT_LAT + " double(4,0),"
				+ DataBaseConstants.WORKOUT_LON + " double(4,0),"
				+ DataBaseConstants.WORKOUT_TIMEZONE + " float(12,0),"
				+ DataBaseConstants.WORKOUT_SPENDMINUTES + " int(4,0),"
				+ DataBaseConstants.WORKOUT_STEPS + " int(4,0),"
				+ DataBaseConstants.WORKOUT_DISTANCE + " int(4,0),"
				+ DataBaseConstants.WORKOUT_CALORIES + " float(12,0),"
				+ DataBaseConstants.WORKOUT_CITY + " varchar(128,0),"
				+ DataBaseConstants.WORKOUT_TEMPERATURE + " varchar(128,0),"
				+ DataBaseConstants.WORKOUT_WEATHERCODE + " varchar(128,0),"
				+ DataBaseConstants.WORKOUT_HUMIDITY + " varchar(128,0),"
				+ DataBaseConstants.WORKOUT_ATMOSPHERE + " varchar(128,0),"
				+ DataBaseConstants.WORKOUT_COMMENTTS + " varchar(128,0),"
				+ DataBaseConstants.WORKOUT_COMMENTPIC + " varchar(128,0),"
				+ DataBaseConstants.WORKOUT_ENDTIME + " long(10,0),"
				+ DataBaseConstants.WORKOUT_MECHINETYPE + " varchar(128,0),"
				+ DataBaseConstants.WORKOUT_MECHINEDEVICEID + " varchar(128,0),"
				+ DataBaseConstants.WORKOUT_IHEALTHCLOUD + " varchar(128,0),"
				+ DataBaseConstants.WORKOUT_COMMENTNOTE + " varchar(200,0)"
				+ ");";
		try {
			db.beginTransaction();
			db.execSQL(sql);
			db.setTransactionSuccessful();
			if(AppsDeviceParameters.isLog)
				Log.d(TAG, "AM,tableCreat()  execSql: " + DataBaseConstants.TABLE_TB_WORKOUT);
		} catch (SQLException e) {
			if(AppsDeviceParameters.isLog)
				Log.d(TAG, "AM,tableCreat()  execSql: " + e);
		} finally {
			db.endTransaction();
		}
	}

	private void Create_TABLE_TB_AM_SWIM(SQLiteDatabase db) {
		String sql = "";
		sql = "CREATE TABLE IF NOT EXISTS " + DataBaseConstants.TABLE_TB_SWIM
				+ " ("
				+ DataBaseConstants.SWIM_CHANGETYPE + " int(4,0),"
				+ DataBaseConstants.SWIM_LASTCHANGETIME + " long(10,0),"
				+ DataBaseConstants.SWIM_PHONEDATAID + " varchar(128,0),"
				+ DataBaseConstants.SWIM_PHONECREATETIME + " long(10,0),"
				+ DataBaseConstants.SWIM_LAT + " double(4,0),"
				+ DataBaseConstants.SWIM_LON + " double(4,0),"
				+ DataBaseConstants.SWIM_TIMEZONE + " float(12,0),"
				+ DataBaseConstants.SWIM_SPENDMINUTES + " int(4,0),"
				+ DataBaseConstants.SWIM_PULLTIMES + " int(4,0),"
				+ DataBaseConstants.SWIM_STROKE + " int(4,0),"
				+ DataBaseConstants.SWIM_CYCLES + " int(4,0),"
				+ DataBaseConstants.SWIM_DISTANCE + " int(4,0),"
				+ DataBaseConstants.SWIM_CALORIES + " float(12,0),"
				+ DataBaseConstants.SWIM_CITY + " varchar(128,0),"
				+ DataBaseConstants.SWIM_TEMPERATURE + " varchar(128,0),"
				+ DataBaseConstants.SWIM_WEATHERCODE + " varchar(128,0),"
				+ DataBaseConstants.SWIM_HUMIDITY + " varchar(128,0),"
				+ DataBaseConstants.SWIM_ATMOSPHERE + " varchar(128,0),"
				+ DataBaseConstants.SWIM_COMMENTTS + " varchar(128,0),"
				+ DataBaseConstants.SWIM_COMMENTPIC + " varchar(128,0),"
				+ DataBaseConstants.SWIM_ENDTIME + " long(10,0),"
				+ DataBaseConstants.SWIM_MECHINETYPE + " varchar(128,0),"
				+ DataBaseConstants.SWIM_MECHINEDEVICEID + " varchar(128,0),"
				+ DataBaseConstants.SWIM_IHEALTHCLOUD + " varchar(128,0),"
				+ DataBaseConstants.SWIM_COMMENTNOTE + " varchar(200,0),"
				+ DataBaseConstants.SWIM_CUTINDIF + " int(4,0),"
				+ DataBaseConstants.SWIM_CUTOUTDIF + " int(4,0),"
				+ DataBaseConstants.SWIM_PROCESSFLAG + " int(4,0)"
				+ ");";
		try {
			db.beginTransaction();
			db.execSQL(sql);
			db.setTransactionSuccessful();
			if(AppsDeviceParameters.isLog)
				Log.d(TAG, "AM,tableCreat()  execSql: " + DataBaseConstants.TABLE_TB_SWIM);
		} catch (SQLException e) {
			if(AppsDeviceParameters.isLog)
				Log.d(TAG, "AM,tableCreat()  execSql: " + e);
		} finally {
			db.endTransaction();
		}
	}

	private void Create_TABLE_TB_AM_SWIMREPORT(SQLiteDatabase db) {
		String sql = "";
		sql = "CREATE TABLE IF NOT EXISTS " + DataBaseConstants.TABLE_TB_SWIMSECTION
				+ " ("
				+ DataBaseConstants.SWIMSECTION_CITY + " varchar(128,0),"
				+ DataBaseConstants.SWIMSECTION_DEVICEID + " varchar(128,0),"
				+ DataBaseConstants.SWIMSECTION_DEVICESOURCE + " varchar(128,0),"
				+ DataBaseConstants.SWIMSECTION_LAT + " double(4,0),"
				+ DataBaseConstants.SWIMSECTION_LON + " double(4,0),"
				+ DataBaseConstants.SWIMSECTION_LASTCHANGETIME + " long(10,0),"
				+ DataBaseConstants.SWIMSECTION_NOTE + " varchar(200,0),"
				+ DataBaseConstants.SWIMSECTION_NOTETS + " long(10,0),"
				+ DataBaseConstants.SWIMSECTION_SWIMCOASTTIME + " int(4,0),"
				+ DataBaseConstants.SWIMSECTION_ENDTIME + " long(10,0),"
				+ DataBaseConstants.SWIMSECTION_STARTTIME + " long(10,0),"
				+ DataBaseConstants.SWIMSECTION_POOLLENGTH + " int(4,0),"
				+ DataBaseConstants.SWIMSECTION_DATAID + " varchar(128,0),"
				+ DataBaseConstants.SWIMSECTION_SPENDTIMEBACKSTROKE + " int(4,0),"
				+ DataBaseConstants.SWIMSECTION_SPENDTIMEBREASTSTROKE + " int(4,0),"
				+ DataBaseConstants.SWIMSECTION_SPENDTIMEFREESTROKE + " int(4,0),"
				+ DataBaseConstants.SWIMSECTION_SPENDTIMEUNRECOGNIZED + " int(4,0),"
				+ DataBaseConstants.SWIMSECTION_SUMCALORIES + " float(12,0),"
				+ DataBaseConstants.SWIMSECTION_SUMTHRASHTIMES + " int(4,0),"
				+ DataBaseConstants.SWIMSECTION_SUMTRIPCOUNT + " int(4,0),"
				+ DataBaseConstants.SWIMSECTION_TIMEZONE + " float(12,0),"
				+ DataBaseConstants.SWIMSECTION_WEATHER + " varchar(128,0),"
				+ DataBaseConstants.SWIMSECTION_IHEALTHCLOUD + " varchar(128,0)"
				+ ");";
		try {
			db.beginTransaction();
			db.execSQL(sql);
			db.setTransactionSuccessful();
			if(AppsDeviceParameters.isLog)
				Log.d(TAG, "AM,tableCreat()  execSql: " + DataBaseConstants.TABLE_TB_SWIMSECTION);
		} catch (SQLException e) {
			if(AppsDeviceParameters.isLog)
				Log.d(TAG, "AM,tableCreat()  execSql: " + e);
		} finally {
			db.endTransaction();
		}
	}

	/**
	 * 创建BG 结果表
	 * @param db
	 */
	private void Create_TABLE_TB_BGRESULT(SQLiteDatabase db) {
		String sql = "";
		sql = "CREATE TABLE IF NOT EXISTS " + DataBaseConstants.TABLE_TB_BGRESULT + " (" 
				+ DataBaseConstants.BGRESULT_CHANGETYPE          + " int(4,0) default 1,"
				+ DataBaseConstants.BGRESULT_LASTCHANGETIME      + " long(25,0)," 
				+ DataBaseConstants.BGRESULT_PHONEDATAID         + " varchar(128,0),"
				+ DataBaseConstants.BGRESULT_PHONECREATETIME     + " long(25,0)," 
				+ DataBaseConstants.BGRESULT_LAT                 + " double(64,0) default 0.0," 
				+ DataBaseConstants.BGRESULT_LON                 + " double(64,0) default 0.0,"
				+ DataBaseConstants.BGRESULT_TIMEZONE            + " float(8,0),"
				+ DataBaseConstants.BGRESULT_BGVALUE             + " float(16,0) default 60.0,"
				+ DataBaseConstants.BGRESULT_MEDICATION          + " int(4,0) default 0,"
				+ DataBaseConstants.BGRESULT_MTIMETYPE           + " int(4,0) default 1,"
				+ DataBaseConstants.BGRESULT_MEASURETYPE         + " int(4,0) default 0,"
				+ DataBaseConstants.BGRESULT_MEASURETIME         + " long(25,0),"
				+ DataBaseConstants.BGRESULT_NOTE                + "  varchar(128,0),"
				+ DataBaseConstants.BGRESULT_MECHINETYPE         + "  varchar(128,0),"
				+ DataBaseConstants.BGRESULT_MECHINEDEVICEID     + "  varchar(128,0),"
				+ DataBaseConstants.BGRESULT_BOTTLEID            + "  varchar(128,0),"
				+ DataBaseConstants.BGRESULT_SPORTS              + "  int(4,0) default 1,"
				+ DataBaseConstants.BGRESULT_SNACKS              + "  int(4,0) default 1,"
				+ DataBaseConstants.BGRESULT_EFFECTIVE           + "  int(4,0) default 1,"
				+ DataBaseConstants.BGRESULT_IHEALTHID           + "  varchar(128,0)" + ");";
		try {
			db.beginTransaction();
			db.execSQL(sql);
			db.setTransactionSuccessful();
			if(AppsDeviceParameters.isLog)
				Log.d(TAG, "7,tableCreat()  execSql: " + DataBaseConstants.TABLE_TB_BGRESULT);
		} catch (SQLException e) {
			if(AppsDeviceParameters.isLog)
				Log.d(TAG, "7,tableCreat()  execSql: " + e);
		} finally {
			db.endTransaction();
		}
	}

	/**
	 * 创建BP 结果表
	 * @param db
	 */
	private void Create_TABLE_TB_BPMEASURERESULT(SQLiteDatabase db) {
		String sql = "";
		sql = "CREATE TABLE IF NOT EXISTS " + DataBaseConstants.TABLE_TB_BPMEASURERESULT + " ("

				+ DataBaseConstants.BPMEASURERESULT_CHANGETYPE + " int(4,0) default 1 ,"
				+ DataBaseConstants.BPMEASURERESULT_LASTCHANGETIME + " long(10,0) ,"
				+ DataBaseConstants.BPMEASURERESULT_PHONEDATAID + " varchar(128,0) ,"
				+ DataBaseConstants.BPMEASURERESULT_PHONECREATETIME + " long(10,0) ,"
				+ DataBaseConstants.BPMEASURERESULT_LAT + " int(4,0) default 0 ,"
				+ DataBaseConstants.BPMEASURERESULT_LON + " int(4,0) default 0 ,"
				+ DataBaseConstants.BPMEASURERESULT_TIMEZONE + " int(4,0) default 0 ,"
				+ DataBaseConstants.BPMEASURERESULT_BPL + " int(4,0) default 0 ,"
				+ DataBaseConstants.BPMEASURERESULT_LP + " int(4,0) default 0 ,"
				+ DataBaseConstants.BPMEASURERESULT_HP + " int(4,0) default 0 ,"
				+ DataBaseConstants.BPMEASURERESULT_HR + " int(4,0) default 0 ,"
				+ DataBaseConstants.BPMEASURERESULT_ISARR + " int(4,0) default 0 ,"
				+ DataBaseConstants.BPMEASURERESULT_WL + " varchar(1024,0) ,"
				+ DataBaseConstants.BPMEASURERESULT_MEASURETYPE + " int(4,0) default 0 ,"
				+ DataBaseConstants.BPMEASURERESULT_MEASURETIME + " long(10,0) ,"
				+ DataBaseConstants.BPMEASURERESULT_NOTE + " varchar(1024,0) ,"
				+ DataBaseConstants.BPMEASURERESULT_MECHINETYPE + " varchar(128,0) ," 
				+ DataBaseConstants.BPMEASURERESULT_MECHINEDEVICEID + " varchar(128,0) ," 
				//云5.0新增字段
				+ DataBaseConstants.BPMEASURERESULT_NOTETS + " long(10,0) ,"
				+ DataBaseConstants.BPMEASURERESULT_BPMOOD + " int(4,0) default 0 ,"
				+ DataBaseConstants.BPMEASURERESULT_BPACTIVITY + " int(4,0) default 0 ,"
				+ DataBaseConstants.BPMEASURERESULT_TEMP + " varchar(128,0) ,"
				+ DataBaseConstants.BPMEASURERESULT_WEATHER + " varchar(128,0) ,"
				+ DataBaseConstants.BPMEASURERESULT_HUMIDITY + " varchar(128,0) ,"
				+ DataBaseConstants.BPMEASURERESULT_VISIBILITY + " varchar(128,0) ,"
				+ DataBaseConstants.BPMEASURERESULT_USEDUSERID + " int(4,0) default 0 ,"
				+ DataBaseConstants.BPMEASURERESULT_UN + " varchar(128,0)" + ");";
		try {
			db.beginTransaction();
			db.execSQL(sql);
			db.setTransactionSuccessful();
			if(AppsDeviceParameters.isLog)
				Log.d(TAG, "8,tableCreat()  execSql: " + DataBaseConstants.TABLE_TB_BPMEASURERESULT);
		} catch (SQLException e) {
			if(AppsDeviceParameters.isLog)
				Log.d(TAG, "8,tableCreat()  execSql: " + e);
		} finally {
			db.endTransaction();
		}
	}

	/**
	 * 创建HS 结果表
	 * @param db
	 */
	private void Create_TABLE_TB_HSRESULT(SQLiteDatabase db) {
		String sql = "";
		sql = "CREATE TABLE IF NOT EXISTS " + DataBaseConstants.TABLE_TB_HSRESULT + " (" 
				+ DataBaseConstants.HSRESULT_CHANGETYPE + " int(4,0) default 1," 
				+ DataBaseConstants.HSRESULT_LASTCHANGETIME+ " long(25,0)," 
				+ DataBaseConstants.HSRESULT_PHONEDATAID+ " varchar(128,0)," 
				+ DataBaseConstants.HSRESULT_PHONECREATETIME+ " long(25,0)," 
				+ DataBaseConstants.HSRESULT_LAT+ " double(64,0) default 0.0," 
				+ DataBaseConstants.HSRESULT_LON + " double(64,0) default 0.0,"
				+ DataBaseConstants.HSRESULT_TIMEZONE+ " float(8,0)," 
				+ DataBaseConstants.HSRESULT_BMI+ " float(16,0) default 0.0,"
				+ DataBaseConstants.HSRESULT_BONEVALUE + " float(16,0) default 0.0,"
				+ DataBaseConstants.HSRESULT_DCI + " float(16,0) default 0.0,"
				+ DataBaseConstants.HSRESULT_FATVALUE + " float(16,0) default 0.0,"
				+ DataBaseConstants.HSRESULT_MUSCALEVALUE + " float(16,0) default 0.0,"
				+ DataBaseConstants.HSRESULT_WATERVALUE + " float(16,0) default 0.0,"
				+ DataBaseConstants.HSRESULT_WEIGHTVALUE + " float(16,0) default 0.0,"
				+ DataBaseConstants.HSRESULT_VISCERAFATLEVEL + " int(4,0) default 0,"
				+ DataBaseConstants.HSRESULT_EMOTIONS + " int(4,0) default 0,"
				+ DataBaseConstants.HSRESULT_NOTETS + " long(25,0)," 
				+ DataBaseConstants.HSRESULT_MOOD + " int(4,0) default 0,"
				+ DataBaseConstants.HSRESULT_ACTIVITY + " int(4,0) default 0,"
				+ DataBaseConstants.HSRESULT_TEMP + "  varchar(128,0),"
				+ DataBaseConstants.HSRESULT_WEATHER + "  varchar(128,0),"
				+ DataBaseConstants.HSRESULT_HUMIDITY + "  varchar(128,0),"
				+ DataBaseConstants.HSRESULT_VISIBILITY + "  varchar(128,0),"
				+ DataBaseConstants.HSRESULT_USEDUSERID + " int(4,0) default 0,"
				+ DataBaseConstants.HSRESULT_MEASURETYPE + " int(4,0) default 0,"
				+ DataBaseConstants.HSRESULT_MEASURETIME + " long(25,0),"
				+ DataBaseConstants.HSRESULT_NOTE + "  varchar(128,0),"
				+ DataBaseConstants.HSRESULT_MECHINETYPE + "  varchar(128,0),"
				+ DataBaseConstants.HSRESULT_MECHINEDEVICEID + "  varchar(128,0),"
				+ DataBaseConstants.HSRESULT_IHEALTHID + "  varchar(128,0)" + ");";
		try {
			db.beginTransaction();
			db.execSQL(sql);
			db.setTransactionSuccessful();
			if(AppsDeviceParameters.isLog)
				Log.d(TAG, "9,tableCreat()  execSql: " + DataBaseConstants.TABLE_TB_HSRESULT);
		} catch (SQLException e) {
			if(AppsDeviceParameters.isLog)
				Log.d(TAG, "9,tableCreat()  execSql: " + e);
		} finally {
			db.endTransaction();
		}
	}

	/**
	 * 创建PO 结果表
	 * @param db
	 */
	private void Create_TABLE_TB_PO(SQLiteDatabase db) {
		String sql = "";
		sql = "CREATE TABLE IF NOT EXISTS " + DataBaseConstants.TABLE_TB_PO+ " (" 
				+ DataBaseConstants.PO_CHANGETYPE        + " int(4,0) default 1," 
				+ DataBaseConstants.PO_LASTCHANGETIME    + " long(25,0)," 
				+ DataBaseConstants.PO_PHONEDATAID       + " varchar(128,0)," 
				+ DataBaseConstants.PO_PHONECREATETIME   + " long(25,0)," 
				+ DataBaseConstants.PO_LAT               + " double(64,0) default 0.0," 
				+ DataBaseConstants.PO_LON               + " double(64,0) default 0.0," 
				+ DataBaseConstants.PO_TIMEZONE          + " float(8,0)," 

		        + DataBaseConstants.PO_ACTIVITY          + " int(4,0) default 0," 
		        + DataBaseConstants.PO_WAVE              + " varchar(128,0)," 
		        + DataBaseConstants.PO_PR                + " int(4,0) default 0," 
		        + DataBaseConstants.PO_RESULT            + " int(4,0) default 0," 
		        + DataBaseConstants.PO_FLOWRATE          + " int(4,0) default 0," 
		        + DataBaseConstants.PO_RESULTSOURCE      + " int(4,0) default 0," 

				+ DataBaseConstants.PO_MOOD              + " int(4,0) default 0," 
				+ DataBaseConstants.PO_WEATHER           + " varchar(128,0)," 

				+ DataBaseConstants.PO_NOTE              + " varchar(128,0)," 
				+ DataBaseConstants.PO_NOTETS            + " long(25,0),"

				+ DataBaseConstants.PO_MEASURETIME       + " long(25,0),"
				+ DataBaseConstants.PO_MECHINETYPE       + " varchar(128,0),"
				+ DataBaseConstants.PO_MECHINEDEVICEID   + " varchar(128,0),"

				+ DataBaseConstants.PO_IHEALTHID         + " varchar(128,0)" + ");";
		try {
			db.beginTransaction();
			db.execSQL(sql);
			db.setTransactionSuccessful();
			if(AppsDeviceParameters.isLog)
				Log.d(TAG, "10,tableCreat()  execSql: " + DataBaseConstants.TABLE_TB_PO);
		} catch (SQLException e) {
			if(AppsDeviceParameters.isLog)
				Log.d(TAG, "10,tableCreat()  execSql: " + e);
		} finally {
			db.endTransaction();
		}
	}
}
