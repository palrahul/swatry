package com.swapi.atry.tryswapi.repository;

import android.util.Log;

import com.swapi.atry.tryswapi.repository.dto.SWItem;
import com.swapi.atry.tryswapi.repository.local.SWLocalRepo;
import com.swapi.atry.tryswapi.repository.remote.SWRemoteRepo;
import com.swapi.atry.tryswapi.repository.remote.SWSearch;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SWItemRepoImpl implements SWItemRepo{

    SWLocalRepo swLocalRepo;
    SWRemoteRepo swRemoteRepo;

    public SWItemRepoImpl(SWLocalRepo swLocalRepo, SWRemoteRepo swRemoteRepo) {
        this.swLocalRepo = swLocalRepo;
        this.swRemoteRepo = swRemoteRepo;
    }

    @Override
    public Observable<SWItem> getSWItem(String search) {

        return Observable.mergeDelayError(
                swRemoteRepo
                    .getSWItem(search).doOnNext(new Consumer<SWSearch>() {
                        @Override
                        public void accept(SWSearch swSearch) throws Exception {
                            if(swSearch != null  && swSearch.results != null &&
                                    !swSearch.results.isEmpty()) {
                                swLocalRepo.addSWItem(swSearch.results.get(0));
                                Log.d("TAG", "Remote: " + swSearch.results.get(0).getName());
                            }
                        }
                    })
                    .switchMap(swSearch -> {
                        if(swSearch != null  && swSearch.results != null &&
                                !swSearch.results.isEmpty()) {
                            return Observable.just(swSearch.results.get(0));
                        } else {
                            return Observable.just(null);
                        }
                    })
                    .doOnError(some -> Log.e("TAG", "Remote: " +some.getLocalizedMessage()))
                    .subscribeOn(Schedulers.io()),
                swLocalRepo
                        .getSWItem(search)
                        .doOnError(some -> Log.e("TAG", "Local: " + some.getLocalizedMessage()))
                        .doOnNext(some -> Log.d("TAG", "Local: " + some.getName()))
                        .subscribeOn(Schedulers.io())
        );

    }
}
