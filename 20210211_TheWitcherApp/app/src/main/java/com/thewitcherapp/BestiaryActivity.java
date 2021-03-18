package com.thewitcherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
    //private Bestiary b;
    //private String json;
    //private Boolean aAcabat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestiary);

        mRcyMonsterType = findViewById(R.id.rcyMonsterType);
        mRcyMonsterType.setLayoutManager(
                new LinearLayoutManager(this,
                        LinearLayoutManager.HORIZONTAL,
                        false));
        mRcyMonsterType.setHasFixedSize(true);
        Bestiary b = loadBestiary();
        MonsterTypeAdapter aMonsterTypeAdapter;
        aMonsterTypeAdapter = new MonsterTypeAdapter(b,BestiaryActivity.this,this);
        mRcyMonsterType.setAdapter(aMonsterTypeAdapter);

        /*Gson gson = new Gson();
        // Crida assíncrona per descarregar el JSON
        Observable.fromCallable(() -> {
            //---------------- START OF THREAD ------------------------------------
            // Això és el codi que s'executarà en un fil
            aAcabat = false;
            return NetworkUtils.getJSon("https://raw.githubusercontent.com/cbaciuu00/PracticaTheWicher/main/bestiary.json");
            //--------------- END OF THREAD-------------------------------------
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((retornInutil) -> {
                    //-------------  UI THREAD ---------------------------------------
                    // El codi que tenim aquí s'executa només quan el fil
                    // ha acabat !! A més, aquest codi s'executa en el fil
                    // d'interfície gràfica.
                    b = loadBestiary(json);
                    aAcabat = true;
                    //-------------  END OF UI THREAD ---------------------------------------
                });
        if(aAcabat){
            MonsterTypeAdapter aMonsterTypeAdapter;
            aMonsterTypeAdapter = new MonsterTypeAdapter(b,BestiaryActivity.this,this);
            mRcyMonsterType.setAdapter(aMonsterTypeAdapter);
        }*/

    }

    /*public Bestiary loadBestiary(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Bestiary.class);
    }*/

    public Bestiary loadBestiary() {
        String json = loadJSON();
        Gson gson = new Gson();
        return gson.fromJson(json, Bestiary.class);
    }

    private String loadJSON(){
        String json = "";
        InputStream is = null;
        try {
            is = getResources().openRawResource(R.raw.bestiary);
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader reader2 = new BufferedReader(reader);

            String line = "";
            try {
                while ((line = reader2.readLine()) != null) {
                    json += line + "\n";
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } finally {
            if(is!=null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return json;
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


}