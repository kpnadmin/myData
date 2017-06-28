package com.android.serialization.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-06-26.
 */

public class ModelSerializableData implements Serializable {


        private int intData = 0 ;
        private String strDate = "serialization";
    // getter & setter

    public int getIntData() {
        return intData;
    }

    public void setIntData(int intData) {
        this.intData = intData;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    // toSTring

    @Override
    public String toString() {
        return "ModelSerializableData{" +
                "intData=" + intData +
                ", strDate='" + strDate + '\'' +
                '}';
    }

    //constructor

    public ModelSerializableData() {
    }

    public ModelSerializableData(int intData, String strDate) {
        this.intData = intData;
        this.strDate = strDate;
    }
}
