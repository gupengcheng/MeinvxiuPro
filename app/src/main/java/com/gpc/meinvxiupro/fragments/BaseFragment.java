package com.gpc.meinvxiupro.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gpc.meinvxiupro.utils.Constant;
import com.gpc.meinvxiupro.utils.LogUtil;


/**
 * Created by pcgu on 16-3-23.
 */
public class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";
    private boolean mIsLoadData = false;
    private int mStartIndex = 0;
    private String mTitleTag;
    private int mInflateLayoutId;
    private View mInflateView;
    protected Handler mHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        mTitleTag = bundle.getString(Constant.BundleConstant.FRAGMENT_TITLE);
        LogUtil.e(TAG, "onCreate ->" + getFragmentTitle());
        initInflateView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtil.e(TAG, "onCreateView ->" + getFragmentTitle());
        mInflateView = inflater.inflate(mInflateLayoutId, container, false);
        findViewByIds();
        initViews();
        setListeners();
        return mInflateView;
    }

    //此方法在控件初始化前调用，所以不能在此方法中直接操作控件会出现空指针
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && !mIsLoadData) {
            loadDataFirst();
        }
    }

    protected void loadCacheData() {

    }

    protected void loadData() {

    }

    //每一个Fragment第一次加载数据
    protected void loadDataFirst() {
        setIsLoadData(true);
        setStartIndex(0);
        loadCacheData();
        loadData();
    }

    //做一些初始化工作，比如setInflateLayout
    protected void initInflateView() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

            }
        };
    }

    protected void setListeners() {

    }

    //初始化控件之前调用这个方法得到当前View
    public View getInflateView() {
        return mInflateView;
    }

    //初始化控件
    protected void findViewByIds() {

    }

    protected void initViews() {

    }

    //子Fragment绑定layout_id,在findViewByIds之前调用
    public void setInflateLayout(int layoutId) {
        mInflateLayoutId = layoutId;
    }

    //得到当前页面的title,用于请求数据
    public String getFragmentTitle() {
        return mTitleTag;
    }

    public boolean isLoadData() {
        return mIsLoadData;
    }

    public void setIsLoadData(boolean isLoadData) {
        this.mIsLoadData = isLoadData;
    }

    public int getStartIndex() {
        return mStartIndex;
    }

    public void setStartIndex(int startIndex) {
        this.mStartIndex = startIndex;
    }
}
