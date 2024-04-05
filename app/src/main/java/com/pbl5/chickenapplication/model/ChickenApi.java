package com.pbl5.chickenapplication.model;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.DELETE;
import retrofit2.http.GET;

public interface ChickenApi {
    @GET("getimages")
    Single<List<ChickenBreed>> getChickens();
}
