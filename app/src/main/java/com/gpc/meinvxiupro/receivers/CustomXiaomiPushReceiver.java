package com.gpc.meinvxiupro.receivers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.gpc.meinvxiupro.activities.ImageDetailActivity;
import com.gpc.meinvxiupro.models.ImgsEntity;
import com.gpc.meinvxiupro.utils.Constant;
import com.gpc.meinvxiupro.utils.JsonUtils;
import com.gpc.meinvxiupro.utils.LogUtil;
import com.gpc.meinvxiupro.utils.SharedPreferencesUtils;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import java.util.ArrayList;

/**
 * Created by pcgu on 16-5-11.
 */
public class CustomXiaomiPushReceiver extends PushMessageReceiver {
    private static final String TAG = "CustomPushReceiver";
    private String mContent;
    private ArrayList<ImgsEntity> mItems = new ArrayList<>();

    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage message) {
        mContent = message.getContent();
        LogUtil.e(TAG,
                "onReceivePassThroughMessage is called. content-> " + mContent);
        ImgsEntity imgsEntity = JsonUtils.fromJson(mContent, ImgsEntity.class);
        if (imgsEntity != null && !TextUtils.isEmpty(imgsEntity.getDownloadUrl())) {
            SharedPreferencesUtils.setWelcomeUrl(context, imgsEntity.getDownloadUrl());
        }
    }

    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage message) {
        mContent = message.getContent();
        LogUtil.e(TAG,
                "onNotificationMessageClicked content->" + mContent);
        ImgsEntity imgsEntity = JsonUtils.fromJson(mContent, ImgsEntity.class);
        if (imgsEntity != null) {
            LogUtil.e(TAG, "imgsEntity is not empty id->" + imgsEntity.getId() + "|url ->" + imgsEntity.getDownloadUrl());
            mItems.clear();
            mItems.add(imgsEntity);
        }
        if (!mItems.isEmpty()) {
            LogUtil.e(TAG, "mItems is not empty ->" + mItems.size() + " " + context.getPackageName());
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(Constant.BundleConstant.IMAGE_DATAS, mItems);
            bundle.putBoolean(Constant.BundleConstant.FROM_PUSH, true);
            Intent intent = new Intent(context, ImageDetailActivity.class);
            intent.putExtra(Constant.ContextConstant.BUNDLE_NAME, bundle);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
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
