package com.swapi.atry.tryswapi.repository.remote;

import com.swapi.atry.tryswapi.repository.dto.SWItem;

import io.reactivex.Observable;

public interface SWRemoteRepo {
    Observable<SWItem> getSWItem(String search);

}
