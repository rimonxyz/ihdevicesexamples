package com.ihealth.androidbg.audio.BG1;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by zhaoyongguang on 15/8/3.
 */
public class BG1_Command_Interface_Subject implements BG1_Command_Interface_Notify {

    @SuppressWarnings("rawtypes")
    private Vector observersVector = new Vector();

    @SuppressWarnings("rawtypes")
    public Enumeration observers(){
        return ((Vector)observersVector.clone()).elements();
    }

    @Override
    public void attach(BG1_Command_Interface observer) {
        observersVector.add(observer);
    }

    @Override
    public void detach(BG1_Command_Interface observer) {
        observersVector.remove(observer);
    }

    @Override
    public void notifyBytes(byte[] msg) {
        @SuppressWarnings("rawtypes")
        Enumeration enumeration = observers();
        while(enumeration.hasMoreElements()){
            ((BG1_Command_Interface)enumeration.nextElement()).msgBytes(msg);
        }
    }
}
