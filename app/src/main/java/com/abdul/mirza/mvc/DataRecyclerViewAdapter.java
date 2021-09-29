package com.abdul.mirza.mvc;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.abdul.mirza.mvc.Model.ListItem;

import java.util.List;

public class DataRecyclerViewAdapter  extends RecyclerView.Adapter<DataRecyclerViewAdapter.ViewHolder> {

    private final List<ListItem> mValues;
    private boolean showDataNames = true;

    public DataRecyclerViewAdapter(List<ListItem> items, boolean showDataNames) {
        mValues = items;
        this.showDataNames = showDataNames;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(showDataNames)
            holder.tv.setText(mValues.get(position).getEventGivenName().toString());
        else
            holder.tv.setText(Integer.toString(mValues.get(position).getEventNumber()));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setShowDataNames (boolean showDataNames) {
        this.showDataNames = showDataNames;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView tv;

        public ViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.tv);
        }

    }
}
