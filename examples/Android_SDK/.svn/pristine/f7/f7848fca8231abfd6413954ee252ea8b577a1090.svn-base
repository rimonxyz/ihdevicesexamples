package com.ihealth.communication.utils;
public class CrcCheck {

	private int[] crcstr;
	int i, j;
	int CRCX;
	int[] CRCY = new int[2];

	public CrcCheck(int[] crcstr) {
		super();
		this.crcstr = crcstr;
	}

	public int[] getCrcstr() {
		return crcstr;
	}

	public void setCrcstr(int[] crcstr) {
		this.crcstr = crcstr;
	}

	public int getCRCValue() {

		CRCX = 0xFFFF;
		CRCY[0] = CRCX & 0x00FF;
		CRCY[1] = CRCX & 0xFF00 >> 8;
		
		for (i = 0; i < crcstr.length; i++) {
			CRCY[0] = CRCY[0] ^ crcstr[i];
			int tempy1 = CRCY[0];
			int tempy2 = CRCY[1]<<8;
			CRCX = tempy1  + tempy2;
			for (j = 0; j < 8; j++) {
				if ((CRCX & 0x0001) == 1) {
					CRCX = (CRCX >> 1) ^ 0x1021;
				} else {
					CRCX >>= 1;
				}
				
			}
			CRCY[0] = CRCX & 0x00FF;
			CRCY[1] = (CRCX & 0xFF00) >> 8;
		}
		return CRCX;
	}
}
