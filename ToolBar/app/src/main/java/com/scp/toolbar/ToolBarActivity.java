package com.scp.toolbar;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * ToolBar的Activity
 */
public class ToolBarActivity extends AppCompatActivity {
    /**
     * ToolBarHelper
     */
    private ToolBarHelper mToolBarHelper;

    /**
     * Toolbar
     */
    private Toolbar mToolbar;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        //初始化ToolBarHelper
        mToolBarHelper = new ToolBarHelper(this, layoutResID);
        //获取Toolbar
        mToolbar = mToolBarHelper.getToolbar();
        //设置内容View
        setContentView(mToolBarHelper.getContentView());
        //把toolbar设置到Activity中
        setSupportActionBar(mToolbar);
        //自定义的一些操作
        onCreateCustomToolBar(mToolbar);
    }

    /**
     * 对ToolBar的自定义操作
     *
     * @param toolbar Toolbar
     */
    private void onCreateCustomToolBar(Toolbar toolbar) {
        toolbar.setContentInsetsRelative(0, 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
