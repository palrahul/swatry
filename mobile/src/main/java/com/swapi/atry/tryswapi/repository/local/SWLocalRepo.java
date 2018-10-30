package com.swapi.atry.tryswapi.repository.local;

import android.arch.lifecycle.LiveData;

import com.swapi.atry.tryswapi.repository.dto.SWItem;

import io.reactivex.Observable;

public interface SWLocalRepo {
    Observable<SWItem> getSWItem(String search);
    LiveData<SWItem> getSWItemLiveData(String search);
    void addSWItem(SWItem swItem);

}
