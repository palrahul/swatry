package com.swapi.atry.tryswapi.repository.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.swapi.atry.tryswapi.repository.dto.SWItem;

@Database(entities = {SWItem.class}, version = 1, exportSchema = false)
public abstract class LocalDB extends RoomDatabase {

    public abstract UsersDao usersDao();

}
