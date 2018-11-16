package com.swapi.atry.tryswapi.repository.local;

import android.arch.lifecycle.LiveData;

import com.swapi.atry.tryswapi.repository.dto.SWItem;
import com.swapi.atry.tryswapi.repository.dto.SWPlanet;

import java.util.concurrent.Callable;

import io.reactivex.Observable;

public class SWLocalRepoImpl implements SWLocalRepo {
    private SWItemDao swItemDao;
    private SWPlanetDao swPlanetDao;

    public SWLocalRepoImpl(SWItemDao swItemDao, SWPlanetDao swPlanetDao) {
        this.swItemDao = swItemDao;
        this.swPlanetDao = swPlanetDao;
    }

    @Override
    public Observable<SWItem> getSWItem(final String search) {
        return Observable.fromCallable(new Callable<SWItem>() {
            @Override
            public SWItem call() throws Exception {
                return swItemDao.getSWItem("%" + search + "%");
            }
        });
    }

    @Override
    public LiveData<SWItem> getSWItemLiveData(String search) {
        return swItemDao.getSWItemLiveData("%" + search + "%");
    }

    @Override
    public void addSWItem(SWItem swItem) {
        swItemDao.addSWItem(swItem);
    }

    @Override
    public Observable<SWPlanet> getSWPlanet(String id) {
        return Observable.fromCallable(new Callable<SWPlanet>() {
            @Override
            public SWPlanet call() throws Exception {
                return swPlanetDao.getSWPlanet("%" + id + "%");
            }
        });
    }

    @Override
    public LiveData<SWPlanet> getSWPlanetLiveData(String id) {
        return swPlanetDao.getSWPlanetLiveData("%/" + id + "/");
    }

    @Override
    public void addSWPlanet(SWPlanet swPlanet) {
        swPlanetDao.addSWPlanet(swPlanet);
    }
}
