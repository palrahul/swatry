package com.swapi.atry.tryswapi;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;

import com.swapi.atry.tryswapi.repository.SWItemRepo;
import com.swapi.atry.tryswapi.repository.SWItemRepoImpl;
import com.swapi.atry.tryswapi.repository.dto.SWItem;
import com.swapi.atry.tryswapi.repository.local.DBConstant;
import com.swapi.atry.tryswapi.repository.local.SWDB;
import com.swapi.atry.tryswapi.repository.local.SWLocalRepo;
import com.swapi.atry.tryswapi.repository.local.SWLocalRepoImpl;
import com.swapi.atry.tryswapi.repository.remote.SWRemoteRepo;
import com.swapi.atry.tryswapi.repository.remote.SWRemoteRepoImpl;

public class SWItemViewModel extends AndroidViewModel {

    SWItemRepo swItemRepo;

    public SWItemViewModel(@NonNull Application application) {
        super(application);
        SWRemoteRepo swRemoteRepo = new SWRemoteRepoImpl();
        SWDB swdb = Room.databaseBuilder(application.getApplicationContext(),
                SWDB.class, DBConstant.DB_NAME).build();
        SWLocalRepo swLocalRepo = new SWLocalRepoImpl(swdb.swDao());
        swItemRepo = new SWItemRepoImpl(swLocalRepo, swRemoteRepo);
    }

    LiveData<SWItem> getSWItemLiveData(String search) {
        return swItemRepo.getSWItemLiveData(search);
    }
}
