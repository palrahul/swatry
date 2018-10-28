package com.swapi.atry.tryswapi.repository.remote;

import io.reactivex.Observable;

public interface SWRemoteRepo {
    Observable<SWSearch> getSWItem(String search);

}
