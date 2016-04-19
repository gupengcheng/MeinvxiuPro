package com.gpc.meinvxiupro.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;

import com.gpc.meinvxiupro.MeinvxiuApplication;

/**
 * create by pcgu
 */
public class NetworkUtils {

    public static boolean signalAvailable = false;

    public static boolean isSignalAvailable() {
        return signalAvailable;
    }

    public static boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) MeinvxiuApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean networkAvailable = false;
        if (cm.getActiveNetworkInfo() != null) {
            networkAvailable = cm.getActiveNetworkInfo().isAvailable();
        }
        return networkAvailable;
    }

    public static String getIMEI() {
        TelephonyManager tm = (TelephonyManager) MeinvxiuApplication.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
        if (tm != null)
            return tm.getDeviceId();

        return "";
    }
}
