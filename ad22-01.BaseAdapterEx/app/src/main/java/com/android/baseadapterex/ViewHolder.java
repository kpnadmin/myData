package com.android.baseadapterex;

import android.widget.TextView;

/**
 * Created by Administrator on 2017-06-29.
 */

public class ViewHolder {

    TextView nameTv;
    TextView numberTv;
    TextView departmentTv;

    public ViewHolder() {
    }

    public ViewHolder(TextView nameTv, TextView numberTv, TextView departmentTv) {
        this.nameTv = nameTv;
        this.numberTv = numberTv;
        this.departmentTv = departmentTv;
    }

    public TextView getNameTv() {
        return nameTv;
    }

    public void setNameTv(TextView nameTv) {
        this.nameTv = nameTv;
    }

    public TextView getNumberTv() {
        return numberTv;
    }

    public void setNumberTv(TextView numberTv) {
        this.numberTv = numberTv;
    }

    public TextView getDepartmentTv() {
        return departmentTv;
    }

    public void setDepartmentTv(TextView departmentTv) {
        this.departmentTv = departmentTv;
    }



}
