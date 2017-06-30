package com.android.baseadapterex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-06-29.
 */

public class BaseAdapterEx extends BaseAdapter{

    Context mContext = null;
    ArrayList<ModelStudent> mData = null;
    LayoutInflater mLayoutInflater = null;
    ViewHolder viewHolder = null;

    public BaseAdapterEx(Context context, ArrayList<ModelStudent> data) {
        mContext = context;
        mData = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }




    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public ModelStudent getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       //
       // View itemLayout = mLayoutInflater.inflate(R.layout.list_view_item_layout, null);

        View itemLayout = convertView;
        if(itemLayout == null) {
            itemLayout = mLayoutInflater.inflate(R.layout.list_view_item_layout, null);

            viewHolder = new ViewHolder();
            viewHolder.nameTv = (TextView) itemLayout.findViewById(R.id.name_text);
            viewHolder.numberTv = (TextView) itemLayout.findViewById(R.id.number_text);
            viewHolder.departmentTv = (TextView) itemLayout.findViewById(R.id.department_text);

            itemLayout.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) itemLayout.getTag();
        }


        viewHolder.nameTv.setText(mData.get(position).getName());
        viewHolder.numberTv.setText(mData.get(position).getNumber());
        viewHolder.departmentTv.setText(mData.get(position).getDepartment());
        // 2. 현재 아이템에 내용을 변경할 뷰를 찾는다.
       /* TextView nameTv = (TextView) itemLayout.findViewById(R.id.name_text);
        TextView numberTv = (TextView) itemLayout.findViewById(R.id.number_text);
        TextView departmentTv = (TextView) itemLayout.findViewById(R.id.department_text);
        //
        nameTv.setText(mData.get(position).getName());
        numberTv.setText(mData.get(position).getNumber());
        departmentTv.setText(mData.get(position).getDepartment());*/

        return itemLayout;

    }


    public void add(int index, ModelStudent addData){
     mData.add(index, addData);
        notifyDataSetChanged();
    }
    public void delete(int index){
        mData.remove(index);
        notifyDataSetChanged();
    }
    public void delete(String search){

        for (int i = 0; i < mData.size(); i++) {
            if (((ModelStudent) mData.get(i)).getName().equals(search)) {
                //Do whatever you want here
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
