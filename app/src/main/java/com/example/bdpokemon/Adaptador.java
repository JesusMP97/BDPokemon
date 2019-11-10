package com.example.bdpokemon;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bdpokemon.Entidades.Pokemon;
import com.example.bdpokemon.room.PokemonDatabase;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.PokemonViewHolder>{

    private PokemonDatabase db;
    View v;

    public Adaptador(PokemonDatabase db) {
        this.db = db;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new PokemonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        //Pokemon pokemon = pokemons.get(position);
        if(db.modeloPokemon().getPokemon(position) != null) {
            holder.name.setText(db.modeloPokemon().getPokemon(position).nombre);
            holder.id.setText(db.modeloPokemon().getPokemon(position).idpokemon+1+"");
            holder.type.setText(db.modeloType().getType(db.modeloPokemon().getPokemon(position).idType).typeName);
            holder.ivPokemon.setImageURI(Uri.parse(db.modeloPokemon().getPokemon(position).foto));
        }
    }

    @Override
    public int getItemCount() {
        return db.modeloPokemon().getPokemons().size();
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name, id, type;
        private ImageView ivPokemon;

        public PokemonViewHolder(View itemView) {

            super(itemView);
            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.tvNameP);
            id = itemView.findViewById(R.id.tvIdP);
            type = itemView.findViewById(R.id.tvTypeP);
            ivPokemon = itemView.findViewById(R.id.ivPokemon);

        }


        @Override
        public void onClick(View v) {
            //creating a popup menu
            PopupMenu popup = new PopupMenu(itemView.getContext(), itemView);

            //inflating menu from xml resource
            popup.inflate(R.menu.menuitem);

            //adding click listener
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.optionDelete:
                            Intent intent = new Intent(itemView.getContext(), DeleteActivity.class);
                            intent.putExtra("idDelete", getAdapterPosition());
                            itemView.getContext().startActivity(intent);
                            return true;
                        default:
                            return false;
                    }
                }
            });
            //displaying the popup
            popup.show();
        }
    }

}
