package com.scp.topbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Scp on 2015/11/17.
 */
public class TopBar extends RelativeLayout {
    //上下文
    private Context context;

    //标题
    private String title;
    //标题文字大小
    private float titleSize;
    //标题颜色
    private int titleColor;
    //标题是否显示
    private boolean titleVisible;
    //标题
    private TextView mTitle;

    //左边标题
    private String leftTitle;
    //左边标题颜色
    private int leftTitleColor;
    //左边标题背景
    private Drawable leftTitleBackground;
    //左边标题是否显示
    private boolean leftTitleVisible;
    //左边按钮
    private Button mLeftButton;

    //右边标题
    private String rightTitle;
    //右边标题颜色
    private int rightTitleColor;
    //右边背景
    private Drawable rightTitleBackground;
    //右边标题是否显示
    private boolean rightTitleVisible;
    //右边按钮
    private Button mRightButton;

    public TopBar(Context context) {
        this(context, null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initAttrs(attrs);
        initView();
    }

    private void initAttrs(AttributeSet attrs) {

        //获取自定义属性
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);

        //从TypedArray中取出对应的值来为要设置的属性赋值
        //获取标题名称
        title = ta.getString(R.styleable.TopBar_topTitle);
        //获取标题文字大小
        titleSize = ta.getDimension(R.styleable.TopBar_topTitleSize, 16);
        //获取标题文字颜色
        titleColor = ta.getColor(R.styleable.TopBar_topTitleColor, Color.BLACK);
        //获取标题是否显示
        titleVisible = ta.getBoolean(R.styleable.TopBar_topTitleVisible, true);

        //获取左边标题
        leftTitle = ta.getString(R.styleable.TopBar_leftTitle);
        //获取左边标题颜色
        leftTitleColor = ta.getColor(R.styleable.TopBar_leftTitleColor, Color.BLACK);
        //获取左边标题背景
        leftTitleBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);
        //获取左边标题是否显示
        leftTitleVisible = ta.getBoolean(R.styleable.TopBar_leftVisible, true);

        //获取右边标题
        rightTitle = ta.getString(R.styleable.TopBar_rightTitle);
        //获取右边标题颜色
        rightTitleColor = ta.getColor(R.styleable.TopBar_rightTitleColor, Color.BLACK);
        //获取右边背景
        rightTitleBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);
        //获取右边标题是否显示
        rightTitleVisible = ta.getBoolean(R.styleable.TopBar_rightVisible, true);

        //获取完TypedArray的值后，一定要调用recycle()方法来资源回收
        ta.recycle();
    }

    private void initView() {

        //初始化左边View
        initLeftView();

        //初始化标题
        initTitleView();

        //初始化右边View
        initRightView();

    }

    //初始化左边View
    private void initLeftView() {
        //判断左边标题是否显示
        if (leftTitleVisible) {
            //左边标题
            mLeftButton = new Button(context);
            mLeftButton.setText(leftTitle);
            mLeftButton.setTextSize(titleSize);
            mLeftButton.setTextColor(leftTitleColor);
            mLeftButton.setBackground(leftTitleBackground);
            mLeftButton.setGravity(Gravity.CENTER);
            mLeftButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    topBarClickListener.leftClick();
                }
            });

            //设置布局参数
            LayoutParams mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
            mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
            addView(mLeftButton, mLeftParams);
        } else {
            if (mLeftButton != null) {
                removeView(mLeftButton);
            }
        }
    }

    //初始化标题
    private void initTitleView() {
        //判断标题是否显示
        if (titleVisible) {
            //标题
            mTitle = new TextView(context);
            mTitle.setText(title);
            mTitle.setTextColor(titleColor);
            mTitle.setTextSize(titleSize);
            mTitle.setGravity(Gravity.CENTER);

            //设置布局参数
            LayoutParams mTitleParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
            mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
            addView(mTitle, mTitleParams);
        } else {
            if (mTitle != null) {
                removeView(mTitle);
            }
        }
    }

    //初始化右边View
    private void initRightView() {
        //判断右边标题是否显示
        if (rightTitleVisible) {
            //右边标题
            mRightButton = new Button(context);
            mRightButton.setText(rightTitle);
            mRightButton.setTextColor(rightTitleColor);
            mRightButton.setTextSize(titleSize);
            mRightButton.setBackground(rightTitleBackground);
            mRightButton.setGravity(Gravity.CENTER);
            mRightButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    topBarClickListener.rightClick();
                }
            });

            //设置布局参数
            LayoutParams mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
            mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
            addView(mRightButton, mRightParams);
        } else {
            if (mRightButton != null) {
                removeView(mRightButton);
            }
        }
    }

    //设置TopBar点击事件
    public void setOnTopBarClickListener(topBarClickListener topBarClickListener) {
        this.topBarClickListener = topBarClickListener;
    }

    //回调接口对象
    private topBarClickListener topBarClickListener;

    //接口对象，实现回调机制，在回调方法中通过映射的接口对象调用接口中的方法，而不用去考虑如何实现，具体的实现由调用者去创建
    public interface topBarClickListener {
        //左按钮点击事件
        void leftClick();

        //右按钮点击事件
        void rightClick();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getTitleSize() {
        return titleSize;
    }

    public void setTitleSize(float titleSize) {
        this.titleSize = titleSize;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public boolean isTitleVisible() {
        return titleVisible;
    }

    public void setTitleVisible(boolean titleVisible) {
        if (this.titleVisible != titleVisible) {
            this.titleVisible = titleVisible;
            //重新绘制
            initTitleView();
        }
    }

    public TextView getmTitle() {
        return mTitle;
    }

    public void setmTitle(TextView mTitle) {
        this.mTitle = mTitle;
    }

    public String getLeftTitle() {
        return leftTitle;
    }

    public void setLeftTitle(String leftTitle) {
        this.leftTitle = leftTitle;
    }

    public int getLeftTitleColor() {
        return leftTitleColor;
    }

    public void setLeftTitleColor(int leftTitleColor) {
        this.leftTitleColor = leftTitleColor;
    }

    public Drawable getLeftTitleBackground() {
        return leftTitleBackground;
    }

    public void setLeftTitleBackground(Drawable leftTitleBackground) {
        this.leftTitleBackground = leftTitleBackground;
    }

    public boolean isLeftTitleVisible() {
        return leftTitleVisible;
    }

    public void setLeftTitleVisible(boolean leftTitleVisible) {
        this.leftTitleVisible = leftTitleVisible;

    }

    public Button getmLeftButton() {
        return mLeftButton;
    }

    public void setmLeftButton(Button mLeftButton) {
        this.mLeftButton = mLeftButton;
    }

    public String getRightTitle() {
        return rightTitle;
    }

    public void setRightTitle(String rightTitle) {
        this.rightTitle = rightTitle;
    }

    public int getRightTitleColor() {
        return rightTitleColor;
    }

    public void setRightTitleColor(int rightTitleColor) {
        this.rightTitleColor = rightTitleColor;
    }

    public Drawable getRightTitleBackground() {
        return rightTitleBackground;
    }

    public void setRightTitleBackground(Drawable rightTitleBackground) {
        this.rightTitleBackground = rightTitleBackground;
    }

    public boolean isRightTitleVisible() {
        return rightTitleVisible;
    }

    public void setRightTitleVisible(boolean rightTitleVisible) {
        this.rightTitleVisible = rightTitleVisible;
    }

    public Button getmRightButton() {
        return mRightButton;
    }

    public void setmRightButton(Button mRightButton) {
        this.mRightButton = mRightButton;
    }
}
