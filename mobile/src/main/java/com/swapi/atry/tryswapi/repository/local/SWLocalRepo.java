package com.swapi.atry.tryswapi.repository.local;

import android.arch.lifecycle.LiveData;

import com.swapi.atry.tryswapi.repository.dto.SWItem;
import com.swapi.atry.tryswapi.repository.dto.SWPlanet;

import io.reactivex.Observable;

public interface SWLocalRepo {
    Observable<SWItem> getSWItem(String search);
    LiveData<SWItem> getSWItemLiveData(String search);
    void addSWItem(SWItem swItem);

    Observable<SWPlanet> getSWPlanet(String id);
    LiveData<SWPlanet> getSWPlanetLiveData(String id);
    void addSWPlanet(SWPlanet swPlanet);

}
