package com.example.bdpokemon;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bdpokemon.Entidades.Pokemon;
import com.example.bdpokemon.room.PokemonDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;

public class CreateActivity extends AppCompatActivity {

    private static final int PHOTO_SELECTED = 0;
    private boolean pickImage = false;
    private PokemonDatabase pokedex;
    ImageView ivImageCreate;
    String uriImage;
    TextView tvIdCreate2;
    EditText etNameCreate;
    Spinner spinnerTypeCreate;
    String[] types;
    Button btCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        initComponents();
        initEvents();
    }

    private void initComponents() {
        ivImageCreate = findViewById(R.id.ivImageCreate);
        tvIdCreate2 = findViewById(R.id.tvIdCreate2);
        etNameCreate = findViewById(R.id.etNameCreate);
        spinnerTypeCreate = findViewById(R.id.spinnerTypeCreate);
        btCreate = findViewById(R.id.btCreate);
    }

    private void initEvents() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Create pokemon");

        pokedex = PokemonDatabase.getBasedatos(getApplicationContext());

        tvIdCreate2.setText(pokedex.modeloPokemon().getPokemons().size()+1 + "");

        types = new String[pokedex.modeloType().getTypes().size()];
        for(int i = 0; i < pokedex.modeloType().getTypes().size(); i++){
            types[i] = pokedex.modeloType().getTypes().get(i).typeName;
        }

        ivImageCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinnerTypeCreate.setAdapter(spinnerArrayAdapter);

        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePokemon();
            }
        });
    }


    private void goToActivity(Class sendTo) {
        Intent intent = new Intent(this, sendTo);
        startActivity(intent);
    }

    private void savePokemon() {
        boolean continuar = true;
        for (int i = 0; i < pokedex.modeloPokemon().getPokemons().size(); i++){
            if(pokedex.modeloPokemon().getPokemons().get(i).nombre.equalsIgnoreCase(etNameCreate.getText().toString())){ // Si el nombre del pokemon ya existe en la BD
                Toast.makeText(this, "El pokemon '" + etNameCreate.getText().toString() + "' ya existe", Toast.LENGTH_SHORT).show();
                continuar = false;
                break;
            }
        }
        if(continuar){
            Pokemon pokemon = new Pokemon(pokedex.modeloPokemon().getPokemons().size(), etNameCreate.getText().toString(), uriImage, spinnerTypeCreate.getSelectedItemPosition());

            pokedex.modeloPokemon().insertPokemon(pokemon);

            goToActivity(MainActivity.class);
        }
    }

    private void chooseImage(){
        Intent f = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(f, PHOTO_SELECTED);
        pickImage = true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_SELECTED && resultCode == RESULT_OK && data != null && data.getData() != null){
            android.net.Uri pokemonUri = data.getData();

            Glide.with(this)
                    .load(pokemonUri)
                    .override(500,500)
                    .into(ivImageCreate);

            uriImage = pokemonUri.toString();
        }
    }

}
