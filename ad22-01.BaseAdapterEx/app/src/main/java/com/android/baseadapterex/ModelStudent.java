package com.android.baseadapterex;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017-06-29.
 */

public class ModelStudent implements Parcelable{
    String name        = ""; //
   String number      = ""; //
    String department  = ""; //


    //gettter & setter

    protected ModelStudent(Parcel in) {
        name = in.readString();
        number = in.readString();
        department = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(number);
        dest.writeString(department);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ModelStudent> CREATOR = new Creator<ModelStudent>() {
        @Override
        public ModelStudent createFromParcel(Parcel in) {
            return new ModelStudent(in);
        }

        @Override
        public ModelStudent[] newArray(int size) {
            return new ModelStudent[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    // toString

    @Override
    public String toString() {
        return "ModelStudent{}";
    }

    // constructor


    public ModelStudent() {
    }

    public ModelStudent(String name, String number, String department) {
        this.name = name;
        this.number = number;
        this.department = department;
    }



}
