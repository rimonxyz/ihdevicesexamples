package com.ihealth.communication.manager;

import android.content.Context;
import android.content.pm.PackageManager;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by apple on 4/13/16.
 */
public class PluginSupport {

    public String registerApp(Context context) {
        String packagename = context.getPackageName();
        String version = null;
        try {
            version = context.getPackageManager().getPackageInfo(packagename, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String keyString = "Mr. Liu is Awesome!";
        String finalversion = "";
        if (version.contains("."))
            finalversion = version.split("\\.")[0];
        else
            finalversion = version;
        String md5 = makeMD5(new StringBuilder().append(packagename).append(finalversion).append(keyString).toString());
        return md5;
    }

    private String makeMD5(String secretString) {
        byte[] secrets;
        try {
            secrets = MessageDigest.getInstance("MD5").digest(secretString.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e1) {
            throw new RuntimeException("Huh, MD5 should be supported?", e1);
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e2);
        }
        StringBuilder md5String = new StringBuilder(secrets.length * 2);
        byte[] temps = secrets;
        int i = temps.length;
        for (int j = 0; j < i; ++j) {
            int k = temps[j];
            if ((k & 0xFF) < 16)
                md5String.append("0");
            md5String.append(Integer.toHexString(k & 0xFF));
        }
        return md5String.toString();
    }
}
