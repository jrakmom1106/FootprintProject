package kr.ac.mjc.footprint.model.research;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SameName implements Parcelable {

    @SerializedName("region")
    @Expose
    private List<Object> region = null;
    @SerializedName("keyword")
    @Expose
    private String keyword;
    @SerializedName("selected_region")
    @Expose
    private String selectedRegion;

    public List<Object> getRegion() {
        return region;
    }

    public void setRegion(List<Object> region) {
        this.region = region;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSelectedRegion() {
        return selectedRegion;
    }

    public void setSelectedRegion(String selectedRegion) {
        this.selectedRegion = selectedRegion;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.region);
        dest.writeString(this.keyword);
        dest.writeString(this.selectedRegion);
    }

    public SameName() {
    }

    protected SameName(Parcel in) {
        this.region = new ArrayList<Object>();
        in.readList(this.region, Object.class.getClassLoader());
        this.keyword = in.readString();
        this.selectedRegion = in.readString();
    }

    public static final Creator<kr.ac.mjc.footprint.model.research.SameName> CREATOR = new Creator<kr.ac.mjc.footprint.model.research.SameName>() {
        @Override
        public kr.ac.mjc.footprint.model.research.SameName createFromParcel(Parcel source) {
            return new kr.ac.mjc.footprint.model.research.SameName(source);
        }

        @Override
        public kr.ac.mjc.footprint.model.research.SameName[] newArray(int size) {
            return new kr.ac.mjc.footprint.model.research.SameName[size];
        }
    };
}