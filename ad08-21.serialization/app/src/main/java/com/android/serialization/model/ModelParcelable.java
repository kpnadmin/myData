package com.android.serialization.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017-06-27.
 */

public class ModelParcelable implements Parcelable {

    private int intData = 0;
    private String strData = "Superoid";


    protected ModelParcelable(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(intData);
        dest.writeString(strData);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ModelParcelable> CREATOR = new Creator<ModelParcelable>() {
        @Override
        public ModelParcelable createFromParcel(Parcel in) {

            ModelParcelable sampleData = new ModelParcelable();
            sampleData.setIntData(in.readInt());
            sampleData.setStrData(in.readString());


            return new ModelParcelable(in);
        }

        @Override
        public ModelParcelable[] newArray(int size) {
            return new ModelParcelable[size];
        }
    };


    public ModelParcelable() {
    }

    public int getIntData() {
        return intData;
    }

    public void setIntData(int intData) {
        this.intData = intData;
    }

    public String getStrData() {
        return strData;
    }

    public void setStrData(String strData) {
        this.strData = strData;
    }

    public static Creator<ModelParcelable> getCREATOR() {
        return CREATOR;
    }



}
