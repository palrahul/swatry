package com.swapi.atry.tryswapi.repository.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.swapi.atry.tryswapi.repository.dto.SWItem;
import com.swapi.atry.tryswapi.repository.dto.SWPlanet;

@Database(entities = {SWItem.class, SWPlanet.class}, version = 1, exportSchema = false)
@TypeConverters({RoomConvertor.class})
public abstract class SWDB extends RoomDatabase {

    public abstract SWItemDao swItemDao();
    public abstract SWPlanetDao swPlanetDao();

}
