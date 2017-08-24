package seveno.android.miniseconds.recyclertimeex;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-08-24.
 */

public class TimeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<ListItems> itemList;

    private OnItemClickListener listener;

    public static class TimeViewHolder extends RecyclerView.ViewHolder {
        public TextView timeItemView;

        public TimeViewHolder(View v) {
            super(v);
            timeItemView = (TextView) v.findViewById(R.id.timeItemView);
        }
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView timeView, nameView;

        private OnViewHolderClickListener listener;

        public DataViewHolder(View v, OnViewHolderClickListener listener) {
            super(v);
            timeView = (TextView) v.findViewById(R.id.timeView);
            nameView = (TextView) v.findViewById(R.id.nameView);
            v.setOnClickListener(this);
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            if(listener != null)
                listener.onViewHolderClick(getPosition());
        }

        private interface OnViewHolderClickListener {
            void onViewHolderClick(int position);
        }
    }

    public TimeListAdapter(ArrayList<ListData> dataset) {
        itemList = initItemList(orderByTimeDesc(dataset));
    }

    private ArrayList<ListItems> initItemList(ArrayList<ListData> dataset) {
        ArrayList<ListItems> result = new ArrayList<>();

        int year = 0, month = 0, dayOfMonth = 0;
        for(ListData data:dataset) {
            if(year != data.getYear() || month != data.getMonth() || dayOfMonth != data.getDayOfMonth()) {
                result.add(new TimeItems(data.getYear(), data.getMonth(), data.getDayOfMonth()));
                year = data.getYear();
                month = data.getMonth();
                dayOfMonth = data.getDayOfMonth();
            }
            result.add(data);
        }
        return result;
    }

    private ArrayList<ListData> orderByTimeDesc(ArrayList<ListData> dataset) {
        ArrayList<ListData> result = dataset;
        for(int i=0; i<result.size()-1; i++) {
            for(int j=0; j<result.size()-i-1; j++) {
                if(result.get(j).getTime() < result.get(j+1).getTime()) {
                    ListData temp2 = result.remove(j+1);
                    ListData temp1 = result.remove(j);
                    result.add(j, temp2);
                    result.add(j+1, temp1);
                }
            }
        }
        return result;
    }

    @Override
    public int getItemViewType(int position) {
        return itemList.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == ListItems.TYPE_TIME)
            return new TimeViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.list_item_time, parent, false));
        else
            return new DataViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.list_item_data, parent, false),
                    new DataViewHolder.OnViewHolderClickListener() {
                        @Override
                        public void onViewHolderClick(int position) {
                            if(listener != null)
                                listener.onItemClick(position);
                        }
                    }
            );
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof TimeViewHolder) {
            TimeViewHolder tHolder = (TimeViewHolder) holder;
            tHolder.timeItemView.setText(itemList.get(position).getTimeToString());
        } else {
            DataViewHolder dHolder = (DataViewHolder) holder;
            dHolder.timeView.setText(itemList.get(position).getTimeToString());
            dHolder.nameView.setText(
                    ((ListData)itemList.get(position))
                            .getName());
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public ListData getItem(int position) {
        return (ListData)itemList.get(position);
    }

    public interface OnItemClickListener {
        public void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
