package com.mike.triggasounds.triggasounds.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mike.triggasounds.triggasounds.R;

/**
 * Created by e590436 on 3/20/14.
 */
public class MenuAdapter extends ArrayAdapter<String> {

    Context context;
    int layoutResourceId;
    String[] mMenuArray = null;

    public MenuAdapter(Context context, int layoutResourceId, String[] mMenuArray) {
        super(context, layoutResourceId, mMenuArray);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.mMenuArray = mMenuArray;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MenuHolder holder = null;

        if(row == null) {
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            row = layoutInflater.inflate(layoutResourceId, parent, false);

            holder = new MenuHolder();
            holder.txtMenuItem = (TextView)row.findViewById(R.id.txtMenuItem);

            row.setTag(holder);
        } else {
            holder = (MenuHolder)row.getTag();
        }

        String menuItem = mMenuArray[position];
        holder.txtMenuItem.setText(menuItem);

        return row;

    }
    static class MenuHolder {
        TextView txtMenuItem;
    }
}
