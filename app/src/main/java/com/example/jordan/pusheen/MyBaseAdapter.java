package com.example.jordan.pusheen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MyBaseAdapter extends BaseAdapter {
    LayoutInflater inflater;
    ArrayList<Rank> postArrayList;
    public MyBaseAdapter(Context context, ArrayList<Rank> _postArrayList){
        inflater = LayoutInflater.from(context);
        postArrayList=_postArrayList;
    }
    @Override
    public int getCount() {
        return postArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return postArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    static class ViewHolder{
        TextView rk;
        TextView name;
        TextView times;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_item_name,null);
            holder = new ViewHolder();
            holder.rk = (TextView) convertView.findViewById(R.id.rank);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.times = (TextView) convertView.findViewById(R.id.times);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        Rank rank = (Rank) getItem(position);
        holder.rk.setText((position+1)+"");
        holder.name.setText(rank.getName());
        holder.times.setText(rank.getTimes()+"");
        return convertView;
    }
    public void updateRankArrayList(ArrayList<Rank> _postArrayList){
        postArrayList=_postArrayList;
        Collections.sort(postArrayList,new RankComp());
        notifyDataSetChanged();
    }
}