package com.swapi.atry.tryswapi.repository.remote;

import com.swapi.atry.tryswapi.repository.dto.SWPlanet;

import io.reactivex.Observable;

public interface SWRemoteRepo {
    Observable<SWSearch> getSWItem(String search);

    Observable<SWPlanet> getSWPlanet(String id);

}
