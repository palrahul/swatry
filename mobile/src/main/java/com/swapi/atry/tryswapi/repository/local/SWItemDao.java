package com.swapi.atry.tryswapi.repository.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.swapi.atry.tryswapi.repository.dto.SWItem;

@Dao
public interface SWItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addSWItem(SWItem swItem);

    @Query("SELECT " + DBConstant.NAME + " FROM  " + DBConstant.SW_ITEM_TABLE_NAME + " WHERE "
            + DBConstant.NAME + " LIKE " + ":search")
    SWItem getSWItem(String search);

}