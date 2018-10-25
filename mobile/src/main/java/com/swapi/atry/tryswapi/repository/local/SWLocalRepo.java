package com.swapi.atry.tryswapi.repository.local;

import com.swapi.atry.tryswapi.repository.dto.SWItem;

import io.reactivex.Observable;

public interface SWLocalRepo {
    Observable<SWItem> getSWItem(String search);
    void addSWItem(SWItem swItem);

}
