/**
 * 
 */
package com.ihealth.communication.ins;

/**
 * @author zhaoyongguang
 * @hide
 */
public class GenerateKap {

	public native double processSampleDataByte(byte[] sample, int sampleRate);
	public native byte[] getKey(String type);//提取Key'+

	static {
		System.loadLibrary("iHealth");
	}

	public byte[] getKa(String type) {
		return getKey(type);
	}
	
	public double getDataFromByteArray(byte[] sample, int sampleRate) {
		return processSampleDataByte(sample, sampleRate);
	}
}
