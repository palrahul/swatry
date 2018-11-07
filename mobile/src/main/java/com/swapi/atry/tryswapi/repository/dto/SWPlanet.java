package com.swapi.atry.tryswapi.repository.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.swapi.atry.tryswapi.repository.local.DBConstant;

import java.util.ArrayList;

@Entity(tableName = DBConstant.SW_PLANET_TABLE_NAME)
public class SWPlanet implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DBConstant.ID)
    private int id;

    @SerializedName("name")
    @ColumnInfo(name = DBConstant.NAME)
    private String name;

    @SerializedName("residents")
    @ColumnInfo(name = DBConstant.RESIDENT_ARRAY)
    private ArrayList<String> residents;

    public SWPlanet() {
    }

    public SWPlanet(Parcel in) {
        setName(in.readString());
        in.readList(residents, null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getResidents() {
        return residents;
    }

    public void setResidents(ArrayList<String> residents) {
        this.residents = residents;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getName());
        dest.writeArray(new ArrayList[]{getResidents()});
        //add more
    }

    public static final Parcelable.Creator<com.swapi.atry.tryswapi.repository.dto.SWPlanet> CREATOR = new Parcelable.Creator<SWPlanet>() {
        public com.swapi.atry.tryswapi.repository.dto.SWPlanet createFromParcel(Parcel source) {
            return new com.swapi.atry.tryswapi.repository.dto.SWPlanet(source);
        }
        @Override
        public com.swapi.atry.tryswapi.repository.dto.SWPlanet[] newArray(int size) {
            return new com.swapi.atry.tryswapi.repository.dto.SWPlanet[size];
        }
    };
}