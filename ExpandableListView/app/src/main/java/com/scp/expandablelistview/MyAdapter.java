package com.scp.expandablelistview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * ExpandableListView适配器
 */
public class MyAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> parentList;
    private Map<String, List<String>> childrenMap;
    private ExpandableListView elv;

    public MyAdapter(Context context, List<String> parentList, Map<String, List<String>> childrenMap, ExpandableListView elv) {
        this.context = context;
        this.parentList = parentList;
        this.childrenMap = childrenMap;
        this.elv = elv;
    }

    /**
     * 获取父类组的总个数
     *
     * @return 父类组的总个数
     */
    @Override
    public int getGroupCount() {
        return parentList.size();
    }

    /**
     * 获取某个子类组的总个数
     *
     * @param groupPosition 父类组的位置
     * @return 子类的总个数
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        String key = parentList.get(groupPosition);
        return childrenMap.get(key).size();
    }

    /**
     * 获取指定位置的父类
     *
     * @param groupPosition 父类的位置
     * @return 指定位置的父类
     */
    @Override
    public Object getGroup(int groupPosition) {
        return parentList.get(groupPosition);
    }

    /**
     * 获取子Item需要关联的数据
     *
     * @param groupPosition 组的位置
     * @param childPosition 子Item的位置
     * @return 获取子item需要关联的数据
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String key = parentList.get(groupPosition);
        String result = childrenMap.get(key).get(childPosition);
        return result;
    }

    /**
     * 获取父类组的ID
     *
     * @param groupPosition 父类组的位置
     * @return 父类组的ID
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * 获取子Item的ID
     *
     * @param groupPosition 组的位置
     * @param childPosition 子Item的位置
     * @return 子Item的位置
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * 是否有固定的ID
     *
     * @return true代表有固定的ID，false则相反
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    /**
     * 设置父item组件
     *
     * @param groupPosition 父类的位置
     * @param isExpanded    是否是扩展的
     * @param convertView   转变View
     * @param parent        父类ViewGroup
     * @return 父类的View
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ParentViewHolder parentViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_parent, null);
            parentViewHolder = new ParentViewHolder(convertView);
            convertView.setTag(parentViewHolder);
        } else {
            parentViewHolder = (ParentViewHolder) convertView.getTag();
        }

        parentViewHolder.parentTV.setText(parentList.get(groupPosition));

        return convertView;
    }

    /**
     * 设置子Item组件
     *
     * @param groupPosition 父类位置
     * @param childPosition 子类位置
     * @param isLastChild   是否是最后一个子Item
     * @param convertView   转变View
     * @param parent        父类ViewGroup
     * @return 子类View
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childVH = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_children, null);
            childVH = new ChildViewHolder(convertView);
            convertView.setTag(childVH);
        } else {
            childVH = (ChildViewHolder) convertView.getTag();
        }
        String key = parentList.get(groupPosition);
        String info = childrenMap.get(key).get(childPosition);
        childVH.childerTV.setText(info);
        return convertView;
    }

    /**
     * 子类是否可选
     *
     * @param groupPosition 父类的位置
     * @param childPosition 子类的位置
     * @return true代表可选，false代表不可选
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private class ParentViewHolder {
        TextView parentTV;

        public ParentViewHolder(View view) {
            parentTV = (TextView) view.findViewById(R.id.parent_tv);
        }
    }

    private class ChildViewHolder {
        TextView childerTV;

        public ChildViewHolder(View view) {
            childerTV = (TextView) view.findViewById(R.id.children_tv);
        }
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        for (int i = 0; i < parentList.size(); i++) {
            if (i == groupPosition) {
                continue;
            }
            if (elv.isGroupExpanded(i)) {
                elv.collapseGroup(i);
            }
        }
        Log.e("MainActivity", "打开父类的位置：" + groupPosition);
    }
}
