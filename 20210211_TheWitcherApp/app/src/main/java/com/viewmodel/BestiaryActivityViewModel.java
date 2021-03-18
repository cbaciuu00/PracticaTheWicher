package com.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.Bestiary;
import com.network.NetworkUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class BestiaryActivityViewModel extends ViewModel {

    public List<Bestiary> mBestiary;

    /*public MainActivityViewModel() {
        String json = NetworkUtils.getJSon("https://pokeapi.co/api/v2/pokemon/?offset=0&limit=200");
        try {
            Bestiary b = null;
            mBestiary = b.getSections();
        } catch (JSONException e) {
            Log.e("XXX", "error carregant json");
            mBestiary = new ArrayList<>();
        }
    }*/

}
