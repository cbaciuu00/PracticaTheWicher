package com.thewitcherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.adapter.MonsterAdapter;
import com.adapter.MonsterTypeAdapter;
import com.adapter.OnSelectedItemListener;
import com.example.Bestiary;
import com.example.Monster;
import com.example.MonsterType;
import com.google.gson.Gson;
import com.network.NetworkUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BestiaryActivity extends AppCompatActivity implements OnSelectedItemListener {

    private MonsterTypeAdapter mAdapter;
    private MonsterAdapter aMonsterAdapter;
    private RecyclerView mRcyMonsterType;
    private RecyclerView mRcyMonster;
    private List<Monster> mMonster;
    private Bestiary b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestiary);

        Observable.fromCallable(() -> {
            return NetworkUtils.getJSon("https://raw.githubusercontent.com/cbaciuu00/PracticaTheWicher/main/bestiary.json");
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((retornInutil) -> {
                });
    }

    public void adapterBestiary(String json){
        mRcyMonsterType = findViewById(R.id.rcyMonsterType);
        mRcyMonsterType.setLayoutManager(
                new LinearLayoutManager(this,
                        LinearLayoutManager.HORIZONTAL,
                        false));
        mRcyMonsterType.setHasFixedSize(true);
        b = loadBestiary(json);
        MonsterTypeAdapter aMonsterTypeAdapter;
        aMonsterTypeAdapter = new MonsterTypeAdapter(b,this,this);
        mRcyMonsterType.setAdapter(aMonsterTypeAdapter);
    }

    public Bestiary loadBestiary(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Bestiary.class);
    }

    @Override
    public void onSelectedItem(MonsterType monsterTypeSeleccionat) {
        mRcyMonster = findViewById(R.id.rcyMonster);
        mRcyMonster.setLayoutManager(
                new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL,
                        false));
        mRcyMonster.setHasFixedSize(true);
        mMonster = monsterTypeSeleccionat.getEntries();
        aMonsterAdapter = new MonsterAdapter(monsterTypeSeleccionat,this,mMonster);
        mRcyMonster.setAdapter(aMonsterAdapter);
    }

    public void cerrarActivity(View view) {
        finish();
    }

}