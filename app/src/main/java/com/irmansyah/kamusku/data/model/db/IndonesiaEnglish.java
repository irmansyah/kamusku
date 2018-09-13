package com.irmansyah.kamusku.data.model.db;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by irmansyah on 06/03/18.
 */

public class IndonesiaEnglish implements Parcelable {

    private int id;
    private String indSearch;
    private String engResult;

    public IndonesiaEnglish() {
    }

    public IndonesiaEnglish(String indSearch, String engResult) {
        this.indSearch = indSearch;
        this.engResult = engResult;
    }

    public IndonesiaEnglish(int id, String indSearch, String engResult) {
        this.id = id;
        this.indSearch = indSearch;
        this.engResult = engResult;
    }

    protected IndonesiaEnglish(Parcel in) {
        id = in.readInt();
        indSearch = in.readString();
        engResult = in.readString();
    }

    public static final Creator<IndonesiaEnglish> CREATOR = new Creator<IndonesiaEnglish>() {
        @Override
        public IndonesiaEnglish createFromParcel(Parcel in) {
            return new IndonesiaEnglish(in);
        }

        @Override
        public IndonesiaEnglish[] newArray(int size) {
            return new IndonesiaEnglish[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(indSearch);
        parcel.writeString(engResult);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIndSearch() {
        return indSearch;
    }

    public void setIndSearch(String indSearch) {
        this.indSearch = indSearch;
    }

    public String getEngResult() {
        return engResult;
    }

    public void setEngResult(String engResult) {
        this.engResult = engResult;
    }

    public static Creator<IndonesiaEnglish> getCREATOR() {
        return CREATOR;
    }
}
