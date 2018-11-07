package com.swapi.atry.tryswapi.repository;

import android.arch.lifecycle.LiveData;

import com.swapi.atry.tryswapi.repository.dto.SWItem;
import com.swapi.atry.tryswapi.repository.dto.SWPlanet;

import io.reactivex.Observable;

public interface SWItemRepo {
    Observable<SWItem> getSWItemObservable(String search);

    LiveData<SWItem> getSWItemLiveData(String search);

    Observable<SWPlanet> getSWPlanetObservable(String search);

    LiveData<SWPlanet> getSWPlanetLiveData(String search);

}
