package org.gowoon.inum.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.model.DataCategoryParent;

import java.util.Vector;

public class ExpandableListCategory_Adapter extends BaseExpandableListAdapter {

    private static final int PARENT = R.layout.item_expandablelist_category_parent;
    private static final int CHILD = R.layout.item_expandablelist_category_child;
    private final Context context;
    private LayoutInflater inflater = null;
    public ImageView imageView;

    private Vector<DataCategoryParent> data;

    public ExpandableListCategory_Adapter(Context context, Vector<DataCategoryParent> data){
        this.data = data;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(groupPosition).child.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).child.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        if(view == null){
            view = inflater.inflate(PARENT,parent,false);
        }
        ImageView icon = view.findViewById(R.id.iv_category_expandable_icon);
        TextView name = view.findViewById(R.id.tv_category_expandable_parent);

        icon.setImageDrawable(data.get(groupPosition).getCategory_image());
        name.setText(data.get(groupPosition).getCategory_name());
        view.setTag("viewtag?");

        return view;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        if(view == null){
            view = inflater.inflate(CHILD,parent,false);
        }
        final TextView childname = view.findViewById(R.id.tv_category_expandable_child);
        childname.setText(data.get(groupPosition).child.get(childPosition));

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
