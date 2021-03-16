package com.thewitcherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.transition.Slide;

import com.adapter.MonsterAdapter;
import com.adapter.MonsterTypeAdapter;
import com.adapter.OnSelectedItemListener;
import com.example.Bestiary;
import com.example.Monster;
import com.example.MonsterType;
import com.google.gson.Gson;
import com.viewpager.SlidePagerAdapter;
import com.viewpager.fragments.PageFragment1;
import com.viewpager.fragments.PageFragment2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnSelectedItemListener {
    private MonsterTypeAdapter mAdapter;
    private MonsterAdapter aMonsterAdapter;
    private RecyclerView mRcyMonsterType;
    private RecyclerView mRcyMonster;
    private List<Monster> mMonster;
    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarViewPager();
        cargarMonsterTypeAdapter();
    }

    private void cargarViewPager() {
        List<Fragment> list = new ArrayList<>();
        list.add(new PageFragment1());
        list.add(new PageFragment2());
        pager = findViewById(R.id.pager);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(),list);
        pager.setAdapter(pagerAdapter);
    }

    private void cargarMonsterTypeAdapter() {
        /*mRcyMonsterType = findViewById(R.id.rcyMonsterType);
        mRcyMonsterType.setLayoutManager(
                new LinearLayoutManager(,
                        LinearLayoutManager.HORIZONTAL,
                        false));
        mRcyMonsterType.setHasFixedSize(true);
        Bestiary b = loadBestiary();
        MonsterTypeAdapter aMonsterType;
        aMonsterType = new MonsterTypeAdapter(b,this,this);
        mRcyMonsterType.setAdapter(aMonsterType);*/
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
    public void onSelectedItem(MonsterType filaSeleccionada) {
        mRcyMonster = findViewById(R.id.rcyMonster);
        mMonster = filaSeleccionada.getEntries();
        aMonsterAdapter = new MonsterAdapter(filaSeleccionada,this,mMonster);
        mRcyMonster.setAdapter(aMonsterAdapter);
        mRcyMonster.setLayoutManager(
                new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false));
        mRcyMonsterType.setHasFixedSize(true);
    }
}