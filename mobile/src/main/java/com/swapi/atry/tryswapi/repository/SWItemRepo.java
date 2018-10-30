package com.swapi.atry.tryswapi.repository;

import android.arch.lifecycle.LiveData;

import com.swapi.atry.tryswapi.repository.dto.SWItem;

import io.reactivex.Observable;

public interface SWItemRepo {
    Observable<SWItem> getSWItemObservable(String search);

    LiveData<SWItem> getSWItemLiveData(String search);

}
