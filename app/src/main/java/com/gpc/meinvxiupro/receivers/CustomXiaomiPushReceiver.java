package com.gpc.meinvxiupro.receivers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.gpc.meinvxiupro.MeinvxiuApplication;
import com.gpc.meinvxiupro.activities.ImageDetailActivity;
import com.gpc.meinvxiupro.models.ImgsEntity;
import com.gpc.meinvxiupro.utils.Constant;
import com.gpc.meinvxiupro.utils.ContextUtils;
import com.gpc.meinvxiupro.utils.JsonUtils;
import com.gpc.meinvxiupro.utils.LogUtil;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pcgu on 16-5-11.
 */
public class CustomXiaomiPushReceiver extends PushMessageReceiver {
    private static final String TAG = "CustomPushReceiver";
    private String mContent;
    private ArrayList<ImgsEntity> mItems = new ArrayList<>();

    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage message) {
        LogUtil.e(TAG,
                "onReceivePassThroughMessage is called. " + message.toString());
        mContent = message.getContent();

    }

    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage message) {
        mContent = message.getContent();
        LogUtil.e(TAG,
                "onNotificationMessageClicked content->" + mContent);
        ImgsEntity imgsEntity = JsonUtils.fromJson(mContent, ImgsEntity.class);
        if (imgsEntity != null) {
            LogUtil.e(TAG, "imgsEntity is not empty ->" + imgsEntity.getDownloadUrl());
            mItems.clear();
            mItems.add(imgsEntity);
        }
        if (!mItems.isEmpty()) {
            LogUtil.e(TAG, "mItems is not empty ->" + mItems.size());
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(Constant.BundleConstant.IMAGE_DATAS, mItems);
            ContextUtils.goActivity(context, ImageDetailActivity.class, bundle);
        }
    }

    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage message) {
        LogUtil.e(TAG,
                "onNotificationMessageArrived is called. " + message.toString());
        mContent = message.getContent();
    }

    @Override
    public void onCommandResult(Context context, MiPushCommandMessage message) {
    }

    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage message) {
    }
}
