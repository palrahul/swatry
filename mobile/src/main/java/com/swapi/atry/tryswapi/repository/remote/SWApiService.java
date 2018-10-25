package com.swapi.atry.tryswapi.repository.remote;

import com.swapi.atry.tryswapi.repository.dto.SWItem;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SWApiService {

    @GET("people")
    Observable<SWItem> getSWItem(@Query("search") String search_keyword);

}
