package com.scp.aidlservice;

import android.os.Parcel;
import android.os.Parcelable;

public final class Person implements Parcelable {
    public int left;
    public int top;
    public int right;
    public int bottom;

    public Person() {
    }

    private Person(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        left = in.readInt();
        top = in.readInt();
        right = in.readInt();
        bottom = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(left);
        dest.writeInt(top);
        dest.writeInt(right);
        dest.writeInt(bottom);
    }

    public static final Parcelable.Creator<Person> CREATOR = new
            Parcelable.Creator<Person>() {
                @Override
                public Person createFromParcel(Parcel in) {
                    return new Person(in);
                }

                @Override
                public Person[] newArray(int size) {
                    return new Person[size];
                }
            };
}