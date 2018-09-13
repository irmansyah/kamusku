package com.irmansyah.kamusku.data.model.db;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by irmansyah on 06/03/18.
 */

public class EnglishIndonesia implements Parcelable {

    private int id;
    private String engSearch;
    private String indResult;

    public EnglishIndonesia() {}

    public EnglishIndonesia(String engSearch, String indResult) {
        this.engSearch = engSearch;
        this.indResult = indResult;
    }

    public EnglishIndonesia(int id, String engSearch, String indResult) {
        this.id = id;
        this.engSearch = engSearch;
        this.indResult = indResult;
    }

    protected EnglishIndonesia(Parcel in) {
        id = in.readInt();
        engSearch = in.readString();
        indResult = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(engSearch);
        dest.writeString(indResult);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<EnglishIndonesia> CREATOR = new Creator<EnglishIndonesia>() {
        @Override
        public EnglishIndonesia createFromParcel(Parcel in) {
            return new EnglishIndonesia(in);
        }

        @Override
        public EnglishIndonesia[] newArray(int size) {
            return new EnglishIndonesia[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEngSearch() {
        return engSearch;
    }

    public void setEngSearch(String engSearch) {
        this.engSearch = engSearch;
    }

    public String getIndResult() {
        return indResult;
    }

    public void setIndResult(String indResult) {
        this.indResult = indResult;
    }
}
