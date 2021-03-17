package com.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Monster;
import com.example.MonsterType;
import com.thewitcherapp.R;

import java.util.List;

public class MonsterAdapter extends RecyclerView.Adapter<MonsterAdapter.ViewHolder> {

    private List<Monster> aMonster;
    private Context aContext;
    private MonsterType aMonsterType ;
    private int isSeleccionat=-1;

    public MonsterAdapter(MonsterType mt,Context c,List<Monster> m){
        aMonster = m;
        aContext = c;
        aMonsterType = mt;
    }

    // creea les files
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = R.layout.fila_monster;
        View filaView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(filaView);
    }
    // omple les dades a les files
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Monster actual = aMonster.get(position);
        holder.txvNom.setText(actual.getTitle());
        int drawableResourceId = aContext.getResources().getIdentifier(actual.getImage(), "drawable", aContext.getPackageName());
        holder.imvSprite.setImageResource(drawableResourceId);
        /*boolean iSeleccionat = this.isSeleccionat == position;
        holder.itemView.setSelected(iSeleccionat);*/
    }

    @Override
    public int getItemCount(){return aMonster.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imvSprite;
        public TextView txvNom;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvSprite = itemView.findViewById(R.id.imvMonsterImg);
            txvNom = itemView.findViewById(R.id.txvMonsterName);
            //itemView.setOnClickListener(new View.OnClickListener());
        }
    }
}
