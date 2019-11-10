package com.example.bdpokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bdpokemon.room.PokemonDatabase;

public class SearchActivity extends AppCompatActivity {

    TextView tvName, tvType;
    EditText etId;
    ImageView ivFoto;
    Button btSearch;
    private PokemonDatabase pokedex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initComponents();
        initEvents();
    }

    private void initEvents() {
        pokedex = PokemonDatabase.getBasedatos(getApplicationContext());
        tvName.setText(pokedex.modeloPokemon().getPokemon(0).nombre);
        tvType.setText(pokedex.modeloType().getType(pokedex.modeloPokemon().getPokemon(0).idType).typeName);
        ivFoto.setImageURI(Uri.parse(pokedex.modeloPokemon().getPokemon(0).foto));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Search pokemon");

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPokemonId();
            }
        });
    }

    private void initComponents() {
        tvName = findViewById(R.id.tvNameSearch);
        tvType = findViewById(R.id.tvTypeSearch);
        etId = findViewById(R.id.etIdSearch);
        ivFoto = findViewById(R.id.ivFotoSearch);
        btSearch = findViewById(R.id.btSearch);

    }

    private void searchPokemonId() {
        try {
            tvName.setText(pokedex.modeloPokemon().getPokemon(Integer.parseInt(etId.getText().toString())-1).nombre);
            tvType.setText(pokedex.modeloType().getType(pokedex.modeloPokemon().getPokemon(Integer.parseInt(etId.getText().toString())-1).idType).typeName);
            ivFoto.setImageURI(Uri.parse(pokedex.modeloPokemon().getPokemon(Integer.parseInt(etId.getText().toString())-1).foto));

        }catch(Exception e){
            tvName.setText("MissingNo");
            tvType.setText("???");
            ivFoto.setImageResource(R.drawable.missing);
        }
    }
}
