package com.gpc.meinvxiupro.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.managers.DataRequestManager;
import com.gpc.meinvxiupro.utils.NetworkUtils;
import com.gpc.meinvxiupro.utils.PermissionUtils;
import com.gpc.meinvxiupro.utils.TimeUtils;
import com.gpc.meinvxiupro.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.Subscriber;

/**
 * Created by pcgu on 16-4-22.
 */
public class FeedbackActivity extends BaseActivity {
    private Toolbar mToolbar;
    private EditText mContent;
    private EditText mContact;
    private TextView mSendFeedback;
    private boolean mIsFeeding = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
    }

    @Override
    protected void findViewByIds() {
        super.findViewByIds();
        mToolbar = (Toolbar) findViewById(R.id.home_toolbar);
        mContent = (EditText) findViewById(R.id.feedback_content);
        mContact = (EditText) findViewById(R.id.feedback_contact);
        mSendFeedback = (TextView) findViewById(R.id.send_feedback);
    }

    @Override
    protected void initViews() {
        super.initViews();
        initToolbar();
        initListener();
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.findViewById(R.id.tool_left_view).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FeedbackActivity.this.finish();
                    }
                }
        );
        mToolbar.findViewById(R.id.tool_back).setVisibility(View.VISIBLE);
        ((TextView) mToolbar.findViewById(R.id.tool_title))
                .setText(getResources().getString(R.string.feedback));
        mToolbar.setBackgroundResource(R.color.colorPrimary);
    }

    private void initListener() {
        mSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIsFeeding) {
                    sendFeedback();
                } else {
                    ToastUtils.showShortSnakeBar(((Activity) mContext).findViewById(android.R.id.content),
                            getResources().getString(R.string.sending));
                }
            }
        });
    }

    private void sendFeedback() {
        mIsFeeding = true;
        if (!NetworkUtils.isNetworkAvailable()) {
            ToastUtils.showShortSnakeBar(((Activity) mContext).findViewById(android.R.id.content),
                    getResources().getString(R.string.network_dis_connect));
            mIsFeeding = false;
            return;
        }
        String content = mContent.getText()
                .toString();
        String contact = mContact.getText()
                .toString();
        if (TextUtils.isEmpty(content)) {
            ToastUtils.showShortSnakeBar(((Activity) mContext).findViewById(android.R.id.content),
                    getResources().getString(R.string.content_empty));
            mIsFeeding = false;
            return;
        }
        if (!TextUtils.isEmpty(contact)) {
            if (!isEmail(contact)) {
                ToastUtils.showShortSnakeBar(((Activity) mContext).findViewById(android.R.id.content),
                        getResources().getString(R.string.contact_not_correct));
                mIsFeeding = false;
                return;
            }
        }
        ToastUtils.showShortSnakeBar(((Activity) mContext).findViewById(android.R.id.content),
                getResources().getString(R.string.sending));
        Map<String, Object> params = new HashMap<>();
        params.put("feedback_app_type", "MeinvxiuPro");
        params.put("feedback_content", content);
        params.put("feedback_contact", contact);
        params.put("imei", PermissionUtils.getIMEI(mContext));
        params.put("time", TimeUtils.getStrTime());
        DataRequestManager.getInstance().sendFeedback(params,
                new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShortSnakeBar(((Activity) mContext).findViewById(android.R.id.content),
                                getResources().getString(R.string.send_failure));
                        mIsFeeding = false;
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        mIsFeeding = false;
                        if (aBoolean) {
                            ToastUtils.showShortSnakeBar(((Activity) mContext).findViewById(android.R.id.content),
                                    getResources().getString(R.string.send_succeed));
                            FeedbackActivity.this.finish();
                        } else {
                            ToastUtils.showShortSnakeBar(((Activity) mContext).findViewById(android.R.id.content),
                                    getResources().getString(R.string.send_failure));
                        }
                    }
                });
    }

    // 判断email格式是否正确
    public boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }

}
