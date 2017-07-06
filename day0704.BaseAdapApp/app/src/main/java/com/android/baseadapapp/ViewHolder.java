package com.android.baseadapapp;

import android.widget.TextView;

/**
 * Created by 김태훈 on 2017-07-04.
 */

public class ViewHolder {
    TextView nameTv;
    TextView numberTv;
    TextView departmentTv;
    public ViewHolder(){

    }
    public ViewHolder(TextView nameTv, TextView numberTv, TextView departmentTv){
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
