package com.android.baseadapapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 김태훈 on 2017-07-04.
 */

public class BaseAdapterEx extends BaseAdapter {
    Context mContext = null;
    ArrayList<Student> mData = null;
    LayoutInflater mLayoutInflater = null;
    ViewHolder viewHolder = null;

    public BaseAdapterEx(Context context, ArrayList<Student> data) {
        mContext = context;
        mData = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
      return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemLayout  = convertView;
        if(itemLayout == null){
            itemLayout = mLayoutInflater.inflate(R.layout.listview_item, null);
            viewHolder = new ViewHolder();
            viewHolder.nameTv = (TextView) itemLayout.findViewById(R.id.name_txt);
            viewHolder.numberTv = (TextView) itemLayout.findViewById(R.id.number_txt);
            viewHolder.departmentTv = (TextView) itemLayout.findViewById(R.id.dept_txt);
            itemLayout.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) itemLayout.getTag();
        }
        viewHolder.nameTv.setText(mData.get(position).getName());
        viewHolder.numberTv.setText(mData.get(position).getNumber());
        viewHolder.departmentTv.setText(mData.get(position).getDepartment());
        return itemLayout;
    }
    public void add(int index, Student addData){
        mData.add(index, addData);
        notifyDataSetChanged();
    }
    public void delete(int index){
        mData.remove(index);
        notifyDataSetChanged();
    }
    public void delete(String search){
        for(int i = 0 ; i<mData.size(); i++){
            if(((Student) mData.get(i)).getName().equals(search)){
                mData.remove(i);
            }
        }
        notifyDataSetChanged();
    }
    public void clear(){
        mData.clear();
        notifyDataSetChanged();
    }
}
