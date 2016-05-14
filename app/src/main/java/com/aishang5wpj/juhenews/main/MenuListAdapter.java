package com.aishang5wpj.juhenews.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aishang5wpj.juhenews.R;

/**
 * Created by wpj on 16/5/14下午3:37.
 */
public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.MyHolder> {

    private String[] mMenuNames;
    private int[] mMenuIcons;

    public MenuListAdapter(String[] menuItems, int[] menuIcons) {
        mMenuNames = menuItems;
        mMenuIcons = menuIcons;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // 给ViewHolder设置布局文件
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_menu_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        holder.menuName.setText(mMenuNames[position]);
        holder.menuIcon.setImageResource(mMenuIcons[position]);
    }

    @Override
    public int getItemCount() {
        return mMenuNames == null ? 0 : mMenuNames.length;
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView menuName;
        ImageView menuIcon;

        public MyHolder(View itemView) {
            super(itemView);
            menuName = (TextView) itemView.findViewById(R.id.menu_item_name_tv);
            menuIcon = (ImageView) itemView.findViewById(R.id.menu_item_icon_iv);
        }
    }
}
