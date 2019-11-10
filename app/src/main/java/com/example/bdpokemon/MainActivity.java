package com.example.bdpokemon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bdpokemon.Entidades.Pokemon;
import com.example.bdpokemon.room.PokemonDatabase;
import com.example.bdpokemon.util.Preload;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private PokemonDatabase pokedex;

    EditText etId;
    RecyclerView rvPokemons;
    Adaptador adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_main);
        new Preload(this);

        pokedex = PokemonDatabase.getBasedatos(getApplicationContext());

        if(pokedex.modeloPokemon().getPokemons().size() == 0){
            populateDb();
        }

        try {
            initComponents();
            initEvents();
        }catch(Exception e){
            Toast.makeText(this, "Database error, restarting database", Toast.LENGTH_SHORT).show();
            Preload.restartDbSincrona(pokedex);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.create_pokemon:
                goToActivity(CreateActivity.class);
                return true;
            case R.id.search_pokemon:
                goToActivity(SearchActivity.class);
                return true;
            case R.id.restart_database:
                populateDb();
                goToActivity(MainActivity.class);
                Toast.makeText(this, "Database restarted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void goToActivity(Class sendTo) {
        Intent intent = new Intent(this, sendTo);
        startActivity(intent);
    }

    private void initEvents() {
        getSupportActionBar().setTitle("Pokedex");
        inicializaAdaptador();
        if(getIntent().getBooleanExtra("deleted", false)){
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
        }
    }


    private void inicializaAdaptador() {
        adapter = new Adaptador(pokedex);
        rvPokemons.setAdapter(adapter);
    }

    private void initComponents() {
        etId = findViewById(R.id.etId);

        rvPokemons = findViewById(R.id.recyclerMain);
        GridLayoutManager lim = new GridLayoutManager(this, 3);
        lim.setOrientation(GridLayoutManager.VERTICAL);
        rvPokemons.setLayoutManager(lim);
    }

    private void populateDb() {
        Preload.cargaSincrona(pokedex);
    }
}
