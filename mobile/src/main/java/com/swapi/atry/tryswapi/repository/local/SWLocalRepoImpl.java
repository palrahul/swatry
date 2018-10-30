package com.swapi.atry.tryswapi.repository.local;

import android.arch.lifecycle.LiveData;

import com.swapi.atry.tryswapi.repository.dto.SWItem;

import java.util.concurrent.Callable;

import io.reactivex.Observable;

public class SWLocalRepoImpl implements SWLocalRepo {
    private SWItemDao swItemDao;

    public SWLocalRepoImpl(SWItemDao swItemDao) {
        this.swItemDao = swItemDao;
    }

    @Override
    public Observable<SWItem> getSWItem(final String search) {
        return Observable.fromCallable(new Callable<SWItem>() {
            @Override
            public SWItem call() throws Exception {
                return swItemDao.getSWItem(search);
            }
        });
    }

    @Override
    public LiveData<SWItem> getSWItemLiveData(String search) {
        return swItemDao.getSWItemLiveData(search);
    }

    @Override
    public void addSWItem(SWItem swItem) {
        swItemDao.addSWItem(swItem);
    }
}
