package com.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Bestiary;
import com.example.MonsterType;
import com.thewitcherapp.R;

import java.util.List;

public class MonsterTypeAdapter extends RecyclerView.Adapter<MonsterTypeAdapter.ViewHolder> {
    private List<MonsterType> aMonsterType;
    private OnSelectedItemListener aListener;
    private Context aContext;
    private int mTypeSeleccionado=-1;


    public MonsterTypeAdapter(Bestiary b,OnSelectedItemListener listener,Context c) {
        aMonsterType = b.getSections();
        aListener = listener;
        aContext = c;
    }

    // creea les files
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = R.layout.fila_monster_type;
        View filaView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(filaView);
    }
    // omple les dades a les files
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MonsterType actual = aMonsterType.get(position);
        holder.txvNom.setText(actual.getTitle());
        Context c = holder.imvSprite.getContext();
        int drawableResourceId = c.getResources().getIdentifier(actual.getImage(), "drawable", c.getPackageName());
        holder.imvSprite.setImageResource(drawableResourceId);
    }

    @Override
    public int getItemCount() { return aMonsterType.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imvSprite;
        public TextView txvNom;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvSprite = itemView.findViewById(R.id.imvMonsterTypeImg);
            txvNom = itemView.findViewById(R.id.txvMonsterTypeName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTypeSeleccionado = getAdapterPosition();
                    aListener.onSelectedItem(aMonsterType.get(mTypeSeleccionado));
                }
            });
        }
    }
}
