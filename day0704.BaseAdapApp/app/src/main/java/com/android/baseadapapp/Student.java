package com.android.baseadapapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 김태훈 on 2017-07-04.
 */

public class Student implements Parcelable {
    String name = "";
    String number = "";
    String department = "";

    public Student() {
    }

    public Student(String name, String number, String department) {
        this.name = name;
        this.number = number;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    //getter setter


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

    protected Student(Parcel in) {
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

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
