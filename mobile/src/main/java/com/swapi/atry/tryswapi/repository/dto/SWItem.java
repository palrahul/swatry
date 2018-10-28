package com.swapi.atry.tryswapi.repository.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.swapi.atry.tryswapi.repository.local.DBConstant;

import java.util.ArrayList;

@Entity(tableName = DBConstant.SW_ITEM_TABLE_NAME)
public class SWItem {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DBConstant.ID)
    private int id;

    @SerializedName("homeworld")
    @ColumnInfo(name = DBConstant.HOME_WORLD)
    private String homeWorld;

    @SerializedName("birth_year")
    @ColumnInfo(name = DBConstant.BIRTH_YEAR)
    private String birthYear;

    @SerializedName("skin_color")
    @ColumnInfo(name = DBConstant.SKIN_COLOR)
    private String skinColor;

    //TODO test array serialization using TypeConverters
    @SerializedName("vehicles")
    @ColumnInfo(name = DBConstant.VEHICLES_ARRAY)
    private ArrayList<String> vehicles;

    @SerializedName("url")
    @ColumnInfo(name = DBConstant.URL)
    private String url;

    @SerializedName("created")
    @ColumnInfo(name = DBConstant.CREATED)
    private String created;

    @SerializedName("edited")
    @ColumnInfo(name = DBConstant.EDITED)
    private String edited;

    @SerializedName("hair_color")
    @ColumnInfo(name = DBConstant.HAIR_COLOR)
    private String hairColor;

    @SerializedName("height")
    @ColumnInfo(name = DBConstant.HEIGHT)
    private String height;

    @SerializedName("species")
    @ColumnInfo(name = DBConstant.SPECIES)
    private ArrayList<String> species;

    @SerializedName("films")
    @ColumnInfo(name = DBConstant.FILMS_ARRAY)
    private ArrayList<String> films;

    @SerializedName("gender")
    @ColumnInfo(name = DBConstant.GENDER)
    private String gender;

    @SerializedName("mass")
    @ColumnInfo(name = DBConstant.MASS)
    private String mass;

    @SerializedName("name")
    @ColumnInfo(name = DBConstant.NAME)
    private String name;

    @SerializedName("eye_color")
    @ColumnInfo(name = DBConstant.EYE_COLOR)
    private String eyeColor;

    @SerializedName("starships")
    @ColumnInfo(name = DBConstant.STARSHIP_ARRAY)
    private ArrayList<String> starships;


    public int getId() {
        return id;
    }

    public String getHomeWorld() {
        return homeWorld;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public String getUrl() {
        return url;
    }

    public String getCreated() {
        return created;
    }

    public String getEdited() {
        return edited;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getHeight() {
        return height;
    }

    public String getGender() {
        return gender;
    }

    public String getMass() {
        return mass;
    }

    public String getName() {
        return name;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHomeWorld(String homeWorld) {
        this.homeWorld = homeWorld;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public ArrayList<String> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<String> vehicles) {
        this.vehicles = vehicles;
    }

    public ArrayList<String> getFilms() {
        return films;
    }

    public void setFilms(ArrayList<String> films) {
        this.films = films;
    }

    public ArrayList<String> getStarships() {
        return starships;
    }

    public void setStarships(ArrayList<String> starships) {
        this.starships = starships;
    }

    public ArrayList<String> getSpecies() {
        return species;
    }

    public void setSpecies(ArrayList<String> species) {
        this.species = species;
    }
}
