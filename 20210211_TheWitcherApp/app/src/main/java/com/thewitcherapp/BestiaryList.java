package com.thewitcherapp;

import androidx.appcompat.app.AppCompatActivity;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class BestiaryList extends AppCompatActivity implements OnSelectedItemListener {

    private MonsterTypeAdapter mAdapter;
    private MonsterAdapter aMonsterAdapter;
    private RecyclerView mRcyMonsterType;
    private RecyclerView mRcyMonster;
    private List<Monster> mMonster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestiary_list);

        mRcyMonsterType = findViewById(R.id.rcyMonsterType);
        mRcyMonsterType.setLayoutManager(
                new LinearLayoutManager(this,
                        LinearLayoutManager.HORIZONTAL,
                        false));
        mRcyMonsterType.setHasFixedSize(true);
        Bestiary b = loadBestiary();
        MonsterTypeAdapter aMonsterTypeAdapter;
        aMonsterTypeAdapter = new MonsterTypeAdapter(b,this,this);
        mRcyMonsterType.setAdapter(aMonsterTypeAdapter);
    }

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