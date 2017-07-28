package com.bignerdranch.android.baseadapterstudent;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2017-07-28.
 */

public class BaseAdapterEx extends BaseAdapter {


    Context context = null;
    List<ModelStudent> data = null;
    LayoutInflater layoutInflater = null;

    public BaseAdapterEx(Context context, List<ModelStudent> data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(this.context);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public ModelStudent getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemLayout = convertView;
        ViewHolder viewHolder = null;

        // 1. 어댑터가 재사용할 뷰를 넘겨주지 않을 경우에만 새로운 뷰를 생성한다.
        if(itemLayout == null){
            itemLayout = layoutInflater.inflate(R.layout.list_view_item_layout, null);
            // view Holder 를 생성하고 사용할 자식뷰를 찾아 ViewHolder 에 참조시킨다.
            // 생성된 viewHolder 은 아이템에 설정해두고 다음에 아이템 재사용시 참조한다 .
            viewHolder = new ViewHolder();

            viewHolder.mNametv = (TextView) itemLayout.findViewById(R.id.txt_name);
            viewHolder.mNumtv = (TextView) itemLayout.findViewById(R.id.txt_num);
            viewHolder.mDepttv = (TextView) itemLayout.findViewById(R.id.txt_dept);
            //

            itemLayout.setTag(viewHolder);



        }else{
            // 재사용 아이템에는 이전에 ViewHolder 객체를 설정해 두었다.
            // 설정된 ViewHolder 객체를 사용하여 findByView 함수를 사용하지 않고 원하는 뷰 참조
            viewHolder = (ViewHolder) itemLayout.getTag();
        }

        // 2. 이름, 학번, 학과 , 데이터를 참조하여 레이아웃을 갱신한다.
        viewHolder.mNametv.setText(data.get(position).getName());
        viewHolder.mNumtv.setText(data.get(position).getNumber());
        viewHolder.mDepttv.setText(data.get(position).getDept());



        return itemLayout;
    }

    public void add(ModelStudent addData){
        data.add(addData);
        notifyDataSetChanged();
    }

    public void insert(ModelStudent addData, int index){
        data.add(index, addData);
        notifyDataSetChanged();
    }
    public void remove(int index){
        data.remove(index);
        notifyDataSetChanged();
    }
    public void clear(){
        data.clear();
        notifyDataSetChanged();
    }

    public void sort(){
        MiniComparator comp = new MiniComparator();
        Collections.sort(data, comp);
        notifyDataSetChanged();
    }

    class MiniComparator implements Comparator<ModelStudent>{

        @Override
        public int compare(ModelStudent o1, ModelStudent o2) {

            int firstValue  = Integer.valueOf(o1.getNumber());
            int secondValue = Integer.valueOf(o2.getNumber());

            if(firstValue > secondValue){
                return 1;
            }else if(firstValue < secondValue){
                return -1;
            }else{
                return 0 ;
            }
        }
    }

}
