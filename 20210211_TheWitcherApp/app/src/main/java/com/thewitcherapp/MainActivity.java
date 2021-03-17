package com.thewitcherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;

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

public class MainActivity extends AppCompatActivity{

    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarViewPager();

    }

    private void cargarViewPager() {
        List<Fragment> list = new ArrayList<>();
        list.add(new PageFragment1());
        list.add(new PageFragment2());
        pager = findViewById(R.id.pager);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(),list);
        pager.setAdapter(pagerAdapter);
    }

    public void toBestiary(View view) {
        Intent i = new Intent(MainActivity.this,BestiaryActivity.class);
        startActivity(i);
    }

    public void toAlchemy(View view) {
        Intent i = new Intent(MainActivity.this,AlchemyActivity.class);
        startActivity(i);
    }
}