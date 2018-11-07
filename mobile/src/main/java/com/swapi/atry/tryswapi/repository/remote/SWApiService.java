package com.swapi.atry.tryswapi.repository.remote;

import com.swapi.atry.tryswapi.repository.dto.SWPlanet;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SWApiService {

    @GET("people")
    Observable<SWSearch> getSWItem(@Query("search") String search_keyword);

    @GET("planets/{id}")
    Observable<SWPlanet> getSWPlanet(@Path("id") String planet_id);

}
