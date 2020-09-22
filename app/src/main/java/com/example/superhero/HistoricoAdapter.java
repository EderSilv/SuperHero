package com.example.superhero;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoricoAdapter extends RecyclerView.Adapter<HistoricoAdapter.MyViewHolder> {
    private ArrayList<String> Lista;

    public HistoricoAdapter(ArrayList<String> lista) {
        Lista = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.historicoadapter, parent,false);
        return new HistoricoAdapter.MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String PerosnagemString = Lista.get(position);
        holder.Personagem.setText(PerosnagemString);
    }

    @Override
    public int getItemCount() {
        return Lista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Personagem;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Personagem=itemView.findViewById(R.id.texthist);
        }
    }

}
