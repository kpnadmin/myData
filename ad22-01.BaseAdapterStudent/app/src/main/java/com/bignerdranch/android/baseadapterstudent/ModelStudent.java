package com.bignerdranch.android.baseadapterstudent;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017-07-28.
 */

public class ModelStudent implements Parcelable{

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

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public ModelStudent() {
    }

    public ModelStudent(String name, String number, String dept) {
        this.name = name;
        this.number = number;
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "ModelStudent{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", dept='" + dept + '\'' +
                '}';
    }

    private String name = "";
    private String number = "";
    private String dept = "";





    protected ModelStudent(Parcel in) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(number);
        dest.writeString(dept);

    }


}
