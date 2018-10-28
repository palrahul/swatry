package com.swapi.atry.tryswapi.repository.remote;

import com.swapi.atry.tryswapi.network.SWRemote;
import com.swapi.atry.tryswapi.network.SWRemoteConfig;

import io.reactivex.Observable;

public class SWRemoteRepoImpl extends SWRemote implements SWRemoteRepo {

    @Override
    public Observable<SWSearch> getSWItem(String search) {
        return create(SWApiService.class, SWRemoteConfig.BASE_URL)
                .getSWItem(search);
    }
}
