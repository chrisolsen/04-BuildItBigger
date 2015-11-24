package org.chrisolsen.common;

import android.os.Parcel;
import android.os.Parcelable;

public class Joke implements Parcelable {

    public String text;

    public Joke(String content) {
        this.text = content;
    }

    @Override
    public String toString() {
        return "this si the bad joke";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
    }

    protected Joke(Parcel in) {
        this.text = in.readString();
    }

    public static final Parcelable.Creator<Joke> CREATOR = new Parcelable.Creator<Joke>() {
        public Joke createFromParcel(Parcel source) {
            return new Joke(source);
        }

        public Joke[] newArray(int size) {
            return new Joke[size];
        }
    };
}
