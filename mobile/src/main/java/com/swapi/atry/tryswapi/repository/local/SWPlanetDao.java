package com.swapi.atry.tryswapi.repository.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.swapi.atry.tryswapi.repository.dto.SWPlanet;

@Dao
public interface SWPlanetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addSWPlanet(SWPlanet swPlanet);

    @Query("SELECT *" + " FROM  " + DBConstant.SW_PLANET_TABLE_NAME + " WHERE "
            + DBConstant.NAME + " LIKE " + ":id" + " LIMIT 1")
    SWPlanet getSWPlanet(String id);

    @Query("SELECT *" + " FROM  " + DBConstant.SW_PLANET_TABLE_NAME + " WHERE "
            + DBConstant.NAME + " LIKE " + ":id" + " LIMIT 1")
    LiveData<SWPlanet> getSWPlanetLiveData(String id);

}
