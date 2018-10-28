package com.swapi.atry.tryswapi.repository;

import com.swapi.atry.tryswapi.repository.dto.SWItem;

import io.reactivex.Observable;

public interface SWItemRepo {
    Observable<SWItem> getSWItem(String search);

}
