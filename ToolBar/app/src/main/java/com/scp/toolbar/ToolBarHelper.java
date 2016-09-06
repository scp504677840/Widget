package com.scp.toolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Activity与ToolBar的关联类
 */
public class ToolBarHelper {
    /**
     * 上下文
     */
    private Context mContext;

    /**
     * LayoutInflater
     */
    private LayoutInflater mInflater;

    /**
     * BaseView
     */
    private FrameLayout mContentView;

    /**
     * 用户自定义View
     */
    private View mUserView;

    /**
     * 两个属性
     * 1.toolbar是否悬浮在窗口之上
     * 2.toolbar的高度获取
     */
    private static int[] ATTRS = {
            R.attr.windowActionBarOverlay,
            R.attr.actionBarSize
    };

    /**
     * Toolbar
     */
    private Toolbar mToolbar;

    public ToolBarHelper(Context context, int layoutID) {
        mContext = context;
        //获取LayoutInflater
        mInflater = LayoutInflater.from(mContext);
        //初始化整个内容
        initContentView();
        //初始化用户自定义的布局
        initUserView(layoutID);
        //初始化ToolBar
        initToolBar();
    }

    private void initToolBar() {
        //将Toolbar布局转化为ToolbarView
        View toolBar = mInflater.inflate(R.layout.toolbar, mContentView);
        //获取Toolbar
        mToolbar = (Toolbar) toolBar.findViewById(R.id.tool_bar);
    }

    /**
     * 初始化整个内容
     */
    private void initContentView() {
        //直接创建一个帧布局，作为视图容器的父容器
        mContentView = new FrameLayout(mContext);
        //创建布局参数
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //设置布局参数
        mContentView.setLayoutParams(lp);
    }

    /**
     * 初始化用户自定义View
     *
     * @param layoutID 布局ID
     */
    private void initUserView(int layoutID) {
        //获取用户自定义View
        mUserView = mInflater.inflate(layoutID, null);
        //创建布局参数
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        //获取TypedArray
        TypedArray typedArray = mContext.getTheme().obtainStyledAttributes(ATTRS);
        //获取主题中定义的悬浮标志
        boolean overly = typedArray.getBoolean(0, false);
        //获取主题中定义的toolbar的高度
        int toolBarSize = (int) typedArray.getDimension(1, (int) mContext.getResources().getDimension(R.dimen.abc_action_bar_default_height_material));
        //释放资源
        typedArray.recycle();
        //如果是悬浮状态，则不需要设置间距
        lp.topMargin = overly ? 0 : toolBarSize;
        //添加用户自定义View
        mContentView.addView(mUserView, lp);
    }

    public FrameLayout getContentView() {
        return mContentView;
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }
}
