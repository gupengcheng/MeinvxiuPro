package com.gpc.meinvxiupro.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.UUID;

/**
 * Created by gupengcheng on 16/4/23.
 */
public class PermissionUtils {
    public static String getIMEI(Context context) {
        String imei = SharedPreferencesUtils.getDeviceIMEI(context);
        if (TextUtils.isEmpty(imei)) {
            if (isSelfPermissionGranted(context, Manifest.permission.READ_PHONE_STATE)) {
                TelephonyManager telephonyManager = (TelephonyManager) context
                        .getSystemService(Context.TELEPHONY_SERVICE);
                imei = telephonyManager.getDeviceId();
                if (TextUtils.isEmpty(imei)) {
                    // set the default imei when can't get device id
                    imei = getPhoneUUID(context);
                }
                SharedPreferencesUtils.setDeviceIMEI(context, imei);
            } else {
                imei = getRandomUUID(context);
                SharedPreferencesUtils.setDeviceIMEI(context, imei);
            }
        }
        return imei;
    }

    public static boolean isSelfPermissionGranted(Context context, String permission) {
        // For Android < Android M, self permissions are always granted.
        boolean result = true;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (getTargetSdkVersion(context) >= Build.VERSION_CODES.M) {
                // targetSdkVersion >= Android M, we can
                // use Context#checkSelfPermission
                result = ContextCompat.checkSelfPermission(context, permission)
                        == PackageManager.PERMISSION_GRANTED;
            } else {
                // targetSdkVersion < Android M, we have to use PermissionChecker
                result = PermissionChecker.checkSelfPermission(context, permission)
                        == PermissionChecker.PERMISSION_GRANTED;
            }
        }

        return result;
    }

    public static String getPhoneUUID(Context context) {

        final TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice, tmSerial, tmPhone, androidId;

        tmDevice = "" + tm.getDeviceId();

        tmSerial = "" + tm.getSimSerialNumber();

        androidId = ""
                + android.provider.Settings.Secure.getString(context.getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32)
                | tmSerial.hashCode());

        String uniqueId = deviceUuid.toString();

        return uniqueId;

    }

    public static String getRandomUUID(Context context) {

        final String androidId;

        androidId = ""
                + android.provider.Settings.Secure.getString(context.getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), UUID.randomUUID().getLeastSignificantBits());

        String uniqueId = deviceUuid.toString();

        return uniqueId;

    }

    public static int getTargetSdkVersion(Context context) {
        int targetSdkVersion = 19;
        try {
            final PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            targetSdkVersion = info.applicationInfo.targetSdkVersion;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return targetSdkVersion;
    }
}
