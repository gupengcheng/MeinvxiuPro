package com.gpc.meinvxiupro.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.utils.Constant;

import java.util.Random;

/**
 * Created by pcgu on 16-3-23.
 */
public class BaseFragment extends Fragment {
    private boolean mIsLoadData = false;
    private String mTitleTag;
    private int mInflateLayoutId;
    private View mInflateView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        mTitleTag = bundle.getString(Constant.BundleConstant.FRAGMENT_TITLE);
        initViews();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflateView = inflater.inflate(mInflateLayoutId, container, false);
        int[] colors = new int[]{R.color.colorPrimary,
                R.color.rgb_7789ff,
                R.color.rgb_898900,
                R.color.rgb_cdcdff,
                R.color.rgb_ef89ff};
        mInflateView.setBackgroundResource(colors[new Random().nextInt(colors.length)]);
        findViewByIds();
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

    //每一个Fragment第一次加载数据
    protected void loadDataFirst() {
        setIsLoadData(true);
    }

    //做一些初始化工作，比如setInflateLayout
    protected void initViews() {

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
}
