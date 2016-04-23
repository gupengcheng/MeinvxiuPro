package com.gpc.meinvxiupro.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gupengcheng on 16/4/23.
 */
public class TimeUtils {
    public static String getStrTime() {
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        Date date = new Date(currentTime);
        return format.format(date);
    }
}
