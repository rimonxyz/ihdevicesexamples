package com.ihealth.androidbg.audio;

/**
 * @time 2014-3-17
 * @author liujun
 * 这个类的作用就是实现数据类型之间的转换
 * 1.byte[]-->int[]－－指令分解成整数
 * 2.int[]-->int[]－－整数换算成频率
 * 3.int[]-->int[]－－频率换算成整数
 * 4.int[]-->byte[]－－整数解析成指令
 */
public class TransToneData {
	/**
	 * 2014-3-17
	 * liujun
	 * TODO 把指令分解成整数（包含头和尾，以及X因素）
	 * @return
	 */
	public static int[] getDataByOrder(byte[] order){
		int temp = -1;//用于判重
		int[] data = new int[order.length * 2 + 2];
		data[0] = 17;
		for(int i=0;i<order.length;i++){
			data[2*i+1] = (int)(order[i]&0xf0) >>> 4; 
			if(temp == 16){
				temp = data[2*i+1];
			}else{
				if(temp == data[2*i+1]){
					data[2*i+1] = 16;
					temp = data[2*i+1];
				}else{
					temp = data[2*i+1];
				}
			}
			
			data[2*i+2] = (int)(order[i]&0x0f);
			if(temp == 16){
				temp = data[2*i+2];
			}else{
				if(temp == data[2*i+2]){
					data[2*i+2] = 16;
					temp = data[2*i+2];
				}else{
					temp = data[2*i+2];
				}				
			}
		}
		data[order.length * 2 + 1] = 18;
		return data;
	}
	/**
	 * 2014-3-17
	 * liujun
	 * TODO 把要发送的数据转换成要发送的音頻数组（给什么样的数据返回什么样的音頻数组）
	 * @return
	 */
	public static int[] transDataToTone(int[] data){
		int[] tone = new int[data.length];
		for(int i=0;i<data.length;i++){
			if(data[i] == 0){//0
				tone[i] = 16600;
			}
			if(data[i] == 1){//1
				tone[i] = 13100;
			}
			if(data[i] == 2){//2
				tone[i] = 10900;
			}
			if(data[i] == 3){//3
				tone[i] = 9250;
			}
			if(data[i] == 4){//4
				tone[i] = 8050;
			}
			if(data[i] == 5){//5
				tone[i] = 7150;
			}
			if(data[i] == 6){//6
				tone[i] = 6400;
			}
			if(data[i] == 7){//7
				tone[i] = 5810;
			}
			if(data[i] == 8){//8
				tone[i] = 5310;
			}
			if(data[i] == 9){//9
				tone[i] = 4900;
			}
			if(data[i] == 10){//A
				tone[i] = 4550;
			}
			if(data[i] == 11){//B
				tone[i] = 4240;
			}
			if(data[i] == 12){//C
				tone[i] = 3970;
			}
			if(data[i] == 13){//D
				tone[i] = 3730;
			}
			if(data[i] == 14){//E
				tone[i] = 3520;
			}
			if(data[i] == 15){//F
				tone[i] = 3330;
			}
			if(data[i] == 16){//X
				tone[i] = 3165;
			}
			if(data[i] == 17){//HEAD
				tone[i] = 3015;
			}
			if(data[i] == 18){//TAIL
				tone[i] = 2875;
			}
			if(data[i] == 19){//充数
				tone[i] = 1000;
			}
		}
		return tone;
	}
	/**
	 * 2014-3-17
	 * liujun
	 * TODO 把接收到的音頻数据转换成数据数组（收到什么样的音頻返回什么样的数据数组）
	 * @return
	 */
	public static int[] transToneToData(int[] tone){
		int data[] = new int[tone.length];
		for(int i=0;i<tone.length;i++){
			if(tone[i] == 8929){//0
				data[i] = 0;
			}
			if(tone[i] == 7813){//1
				data[i] = 1;
			}
			if(tone[i] == 6944){//2
				data[i] = 2;
			}
			if(tone[i] == 6250){//3
				data[i] = 3;
			}
			if(tone[i] == 5682){//4
				data[i] = 4;
			}
			if(tone[i] == 5208){//5
				data[i] = 5;
			}
			if(tone[i] == 4808){//6
				data[i] = 6;
			}
			if(tone[i] == 4464){//7
				data[i] = 7;
			}
			if(tone[i] == 4167){//8
				data[i] = 8;
			}
			if(tone[i] == 3676){//9
				data[i] = 9;
			}
			if(tone[i] == 3289){//A
				data[i] = 10;
			}
			if(tone[i] == 2841){//B
				data[i] = 11;
			}
			if(tone[i] == 2500){//C
				data[i] = 12;
			}
			if(tone[i] == 2155){//D
				data[i] = 13;
			}
			if(tone[i] == 1838){//E
				data[i] = 14;
			}
			if(tone[i] == 1524){//F
				data[i] = 15;
			}
			if(tone[i] == 1202){//X
				data[i] = 16;
			}
			if(tone[i] == 906){//HEAD
				data[i] = 17;
			}
			if(tone[i] == 601){//TAIL
				data[i] = 18;
			}
		}
		return data;
	}
	public static int transToneToData(int tone){
		int data = 0;	
		if(tone == 8929){//0
				data = 0;
			}
			if(tone == 7813){//1
				data = 1;
			}
			if(tone == 6944){//2
				data = 2;
			}
			if(tone == 6250){//3
				data = 3;
			}
			if(tone == 5682){//4
				data = 4;
			}
			if(tone == 5208){//5
				data = 5;
			}
			if(tone == 4808){//6
				data = 6;
			}
			if(tone == 4464){//7
				data = 7;
			}
			if(tone == 4167){//8
				data = 8;
			}
			if(tone == 3676){//9
				data = 9;
			}
			if(tone == 3289){//A
				data = 10;
			}
			if(tone == 2841){//B
				data = 11;
			}
			if(tone == 2500){//C
				data = 12;
			}
			if(tone == 2155){//D
				data = 13;
			}
			if(tone == 1838){//E
				data = 14;
			}
			if(tone == 1524){//F
				data = 15;
			}
			if(tone == 1202){//X
				data = 16;
			}
			if(tone == 906){//HEAD
				data = 17;
			}
			if(tone == 601){//TAIL
				data = 18;
			}
		return data;
	}

	/**
	 * byte[]转十六进制的大写字符串
	 * @param b  待转字符串
	 * @param len 长度
	 */
	public static String Bytes2HexString(byte[] b,int len){
		String ret = ""; 
		for (int i = 0; i < len; i++) { 
			String hex = Integer.toHexString(b[i] & 0xFF); 
			if (hex.length() == 1) { 
				hex = '0' + hex; 
			} 
			ret += hex.toUpperCase(); 
		} 
		return ret; 
	} 
}
