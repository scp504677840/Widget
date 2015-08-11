package imahe.cn.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import itemanimator.ScaleInOutItemAnimator;
import itemanimator.SlideInOutBottomItemAnimator;
import itemanimator.SlideInOutLeftItemAnimator;
import itemanimator.SlideInOutRightItemAnimator;
import itemanimator.SlideInOutTopItemAnimator;
import itemanimator.SlideScaleInOutRightItemAnimator;

/**
 * RecyclerView：
 * 普通ListView展现
 * Grid展现
 * 瀑布流展现
 *
 * 本身RecyclerView是没有OnItemClickListener监听的，需要我们自己写，本例子有实现，大家可以去研究。
 *
 * 说明：
 * itemanimator包下的都是动画类，使用说明详情请看菜单项点击事件。
 * 开源项目地址：https://github.com/gabrielemariotti/RecyclerViewItemAnimators#download
 *
 * DividerItemDecoration.java是自定义分隔条类，上面这个开源项目中和本实例就有，网上也有开源,大家自行百度。
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;//数据源
    private SimpleAdapter adapet;//适配器

    private RecyclerView.ItemAnimator mItemAnimator = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        initEvent();
    }

    private void initData() {
        //初始化数据源
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.main_RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));//布局管理器
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapet = new SimpleAdapter(this, mDatas);//初始化适配器
        mRecyclerView.setAdapter(adapet);//设置适配器
        //mRecyclerView.setItemAnimator(new DefaultItemAnimator());//系统默认的动画
    }

    private void initEvent() {
        adapet.setOnItemClickListener(new SimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                System.out.println("点击" + position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                System.out.println("长按" + position);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                adapet.addData(1);
                break;
            case R.id.action_delete:
                adapet.deleteData(1);
                break;
            case R.id.action_listview:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                break;
            case R.id.action_GridView:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
                break;
            case R.id.action_hor_GridView:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case R.id.action_staggered:
                Intent intent = new Intent(this, StaggeredActivity.class);
                startActivity(intent);
                break;
            case R.id.action_outitem:
                mItemAnimator = new ScaleInOutItemAnimator(mRecyclerView) {
                    @Override
                    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
                        return false;
                    }
                };
                break;
            case R.id.action_outbottom:
                mItemAnimator = new SlideInOutBottomItemAnimator(mRecyclerView) {
                    @Override
                    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
                        return false;
                    }
                };
                break;
            case R.id.action_outleft:
                mItemAnimator = new SlideInOutLeftItemAnimator(mRecyclerView) {
                    @Override
                    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
                        return false;
                    }
                };
                break;
            case R.id.action_outright:
                mItemAnimator = new SlideInOutRightItemAnimator(mRecyclerView) {
                    @Override
                    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
                        return false;
                    }
                };
                break;
            case R.id.action_outtop:
                mItemAnimator = new SlideInOutTopItemAnimator(mRecyclerView) {
                    @Override
                    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
                        return false;
                    }
                };
                break;
            case R.id.action_outssright:
                mItemAnimator = new SlideScaleInOutRightItemAnimator(mRecyclerView) {
                    @Override
                    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
                        return false;
                    }
                };
                break;
        }
        if (mItemAnimator != null) {
            mItemAnimator.setAddDuration(200);//添加动画持续时间
            mItemAnimator.setRemoveDuration(200);//移除动画持续时间
            mRecyclerView.setItemAnimator(mItemAnimator);
        }
        return super.onOptionsItemSelected(item);
    }
}
