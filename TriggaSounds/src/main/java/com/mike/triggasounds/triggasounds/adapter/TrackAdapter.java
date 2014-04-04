package com.mike.triggasounds.triggasounds.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mike.triggasounds.triggasounds.R;
import com.mike.triggasounds.triggasounds.model.Track;


/**
 * Created by e590436 on 3/18/14.
 */
public class TrackAdapter extends ArrayAdapter<Track> {

    Context context;
    int layoutResourceId;
    Track[] trackList = null;

    public TrackAdapter(Context context, int layoutResourceId, Track[] trackList) {
        super(context, layoutResourceId, trackList);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.trackList = trackList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        TrackHolder holder = null;

        if(row == null) {
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            row = layoutInflater.inflate(layoutResourceId, parent, false);

            holder = new TrackHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.trackTitle = (TextView)row.findViewById(R.id.txtRowItemTitle);

            row.setTag(holder);
        } else {
            holder = (TrackHolder)row.getTag();
        }

        Track track = trackList[position];
        holder.trackTitle.setText(track.getName());
        holder.imgIcon.setImageResource(track.getIcon());

        return row;

    }
    static class TrackHolder {
        ImageView imgIcon;
        TextView trackTitle;
    }
}
