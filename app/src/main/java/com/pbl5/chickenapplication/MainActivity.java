package com.pbl5.chickenapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.pbl5.chickenapplication.viewmodel.ChickenAdapter;
import com.pbl5.chickenapplication.viewmodel.ChickenApiService;
import com.pbl5.chickenapplication.databinding.ActivityMainBinding;
import com.pbl5.chickenapplication.model.AppDatabase;
import com.pbl5.chickenapplication.model.ChickenBreed;
import com.pbl5.chickenapplication.model.ChickenDAO;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements ChickenAdapter.OnChickenListener {
    private ChickenApiService apiService;
    private ChickenAdapter chickensAdapter;
    private List<ChickenBreed> chickenList;
    private ActivityMainBinding binding;
    private ChickenDAO ChickenDAO;
    private AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);
        binding.rvChickenApp.setLayoutManager(new GridLayoutManager(this,2));
        chickenList=new ArrayList<>();
        chickensAdapter=new ChickenAdapter(chickenList,this);
        binding.rvChickenApp.setAdapter(chickensAdapter);
        apiService=new ChickenApiService();
        apiService.getChickens()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<ChickenBreed>>() {
                    @Override
                    public void onSuccess(@NonNull List<ChickenBreed> chickenBreeds) {
                        Log.d("DEBUG","success");
                        for(ChickenBreed chicken: chickenBreeds){
                            ChickenBreed i= new ChickenBreed(chicken.getId(),chicken.getUuid(), chicken.getUrl());
                            chickenList.add(i);
                            Log.d("DEBUG",i.getUuid());
                            chickensAdapter.notifyDataSetChanged();
                        }
                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                appDatabase = AppDatabase.getInstance(getApplicationContext());
                                ChickenDAO = appDatabase.contactDAO();
                                for(ChickenBreed chicken:chickenList){
                                    ChickenBreed i = new ChickenBreed(chicken.getId(),chicken.getUuid(), chicken.getUrl());
                                    ChickenDAO.insert(i);
                                }
                            }
                        });
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("DEBUG","Fail"+e.getMessage());
                    }
                });
    }

    @Override
    public void onChickenClick(int position) {

    }
}