package com.example.francisco.w3d1weekday;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by FRANCISCO on 14/08/2017.
 */

public class Randoms implements Parcelable{
    String random;

    public Randoms(String random) {
        this.random = random;
    }

    protected Randoms(Parcel in) {
        random = in.readString();
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(random);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Randoms> CREATOR = new Creator<Randoms>() {
        @Override
        public Randoms createFromParcel(Parcel in) {
            return new Randoms(in);
        }

        @Override
        public Randoms[] newArray(int size) {
            return new Randoms[size];
        }
    };
}
