package com.pbl5.chickenapplication.viewmodel;

import com.pbl5.chickenapplication.model.ChickenApi;
import com.pbl5.chickenapplication.model.ChickenBreed;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChickenApiService  {
    private static final String BASE_URL="http://nglam.xyz/";
    private ChickenApi api;
    public  ChickenApiService(){
        api= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(ChickenApi.class);
    }
    public Single<List<ChickenBreed>> getChickens(){
        return api.getChickens();
    }

}
