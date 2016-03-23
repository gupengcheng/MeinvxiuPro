package com.gpc.meinvxiupro.utils;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * log utils
 *
 * @author pcgu
 */
public class LogUtil {

    public static int LOG_D = 1;
    public static int LOG_I = 2;
    public static int LOG_W = 3;
    public static int LOG_E = 4;

    public static String DEFAULT_LOG_TAG = "MeinvxiuPro";

    /**
     * log toggle
     */
    private static boolean logFlag = Constant.IS_DEBUG;

    public static void d(String tag, String msg) {
        log(tag, msg, LOG_D);
    }

    public static void d(String msg) {
        log(DEFAULT_LOG_TAG, msg, LOG_D);
    }

    public static void i(String tag, String msg) {
        log(tag, msg, LOG_I);
    }

    public static void i(String msg) {
        log(DEFAULT_LOG_TAG, msg, LOG_I);
    }

    public static void w(String tag, String msg, Throwable tr) {
        log(tag, msg, tr, LOG_W);
    }

    public static void w(Throwable tr) {
        log(DEFAULT_LOG_TAG, "", tr, LOG_W);
    }

    public static void w(String msg, Throwable tr) {
        log(DEFAULT_LOG_TAG, msg, tr, LOG_W);
    }

    public static void w(String tag, String msg) {
        log(tag, msg, LOG_W);
    }

    public static void e(String tag, String msg, Throwable tr) {
        log(tag, msg, tr, LOG_E);
    }

    public static void e(String tag, String msg) {
        log(tag, msg, LOG_E);
    }

    public static void e(String msg) {
        log(DEFAULT_LOG_TAG, msg, LOG_E);
    }

    public static void e(Throwable tr) {
        log(DEFAULT_LOG_TAG, "", tr, LOG_E);
    }

    private static void log(String tag, String msg, int logType) {
        if (!logFlag) {
            return;
        }
        if (msg == null) {
            msg = "null";
        }
        if (LOG_D == logType) {
            Log.d(tag, msg);
        } else if (LOG_I == logType) {
            Log.i(tag, msg);
        } else if (LOG_W == logType) {
            Log.w(tag, msg);
        } else if (LOG_E == logType) {
            Log.e(tag, msg);
        } else {
            Log.d(tag, msg);
        }
    }

    private static void log(String tag, String msg, Throwable tr, int logType) {
        if (!logFlag) {
            return;
        }
        log(tag, msg + '\n' + getStackTraceString(tr), logType);
    }

    private static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        }
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        return sw.toString();
    }

}
