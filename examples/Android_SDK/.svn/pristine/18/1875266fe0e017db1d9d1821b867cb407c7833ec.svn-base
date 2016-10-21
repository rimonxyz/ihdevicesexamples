package com.ihealth.communication.ins;

import android.content.Context;

import com.ihealth.communication.cloud.data.BP_InAuthor;
import com.ihealth.communication.cloud.data.DataBaseConstants;
import com.ihealth.communication.cloud.data.DataBaseTools;
import com.ihealth.communication.cloud.data.Data_BP_Result;
import com.ihealth.communication.cloud.data.Make_Data_Util;
import com.ihealth.communication.cloud.tools.AppsDeviceParameters;

public class A1DBtools {

	public A1DBtools(){}
	public void save(Context context, String userName, String mac, String type, int Hp, int Lp, int HR, long TS){

		if (AppsDeviceParameters.isUpLoadData) {
			Data_BP_Result Bpr = Make_Data_Util.makeDataSingleBp(userName, Hp, Lp, HR, type, mac, TS);


			DataBaseTools db = new DataBaseTools(context);
			db.addData(DataBaseConstants.TABLE_TB_BPMEASURERESULT, Bpr);

			BP_InAuthor sdk_InAuthor = BP_InAuthor.getInstance();
			sdk_InAuthor.initAuthor(context, userName);
			sdk_InAuthor.run();
		}
	}
}
