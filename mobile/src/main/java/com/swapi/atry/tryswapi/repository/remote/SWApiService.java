package com.swapi.atry.tryswapi.repository.remote;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SWApiService {

    @GET("people")
    Observable<SWSearch> getSWItem(@Query("search") String search_keyword);

}
