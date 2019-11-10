package com.example.bdpokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bdpokemon.room.PokemonDatabase;
import com.example.bdpokemon.util.Preload;

public class DeleteActivity extends AppCompatActivity {

    TextView tvDeleteName;
    ImageView ivDeleteImage;
    Button btCancelDelete, btDelete;
    private PokemonDatabase db;
    int idDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        idDelete = getIntent().getIntExtra("idDelete", 0);
        db = PokemonDatabase.getBasedatos(getApplicationContext());

        initComponents();
        initEvents();
    }

    private void initComponents() {
        tvDeleteName = findViewById(R.id.tvDeleteName);
        ivDeleteImage = findViewById(R.id.ivDelete);
        btCancelDelete = findViewById(R.id.btCancel);
        btDelete = findViewById(R.id.btDelete);
    }

    private void initEvents() {
        tvDeleteName.setText(db.modeloPokemon().getPokemon(idDelete).nombre);
        ivDeleteImage.setImageURI(Uri.parse(db.modeloPokemon().getPokemon(idDelete).foto));

        btCancelDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("deleted", false);
                startActivity(intent);
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.modeloPokemon().deletePokemon( db.modeloPokemon().getPokemon(idDelete) );
                Preload.reorderIds(db, idDelete);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("deleted", true);
                startActivity(intent);

            }
        });
    }

}
