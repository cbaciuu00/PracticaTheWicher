package com.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MonsterType;
import com.example.Vulnerabilities;

import java.util.List;

public class PotionsAdapter extends RecyclerView.Adapter<PotionsAdapter.ViewHolder> {

    private OnSelectedItemListener aListener;
    private Context aContext;
    private Vulnerabilities vulnerabilities;

    public PotionsAdapter(Vulnerabilities v, OnSelectedItemListener listener, Context c){

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
