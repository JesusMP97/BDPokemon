package com.example.bdpokemon.room;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.bdpokemon.Entidades.Pokemon;
import com.example.bdpokemon.Entidades.Type;
import com.example.bdpokemon.dao.DaoPokemon;
import com.example.bdpokemon.dao.DaoType;

@Database(entities = {Pokemon.class, Type.class}, version = 2)
public abstract class PokemonDatabase extends RoomDatabase {
    private static PokemonDatabase INSTANCIA;

     public abstract DaoPokemon modeloPokemon();
     public abstract DaoType modeloType();

    public static PokemonDatabase getBasedatos(Context contexto) {

        if (INSTANCIA == null) {
            Log.v("TAG", "instancia");
            INSTANCIA =
                    Room.databaseBuilder(contexto.getApplicationContext(), PokemonDatabase.class,
                            "biblioteca.sqlite").fallbackToDestructiveMigration().allowMainThreadQueries().build();
                    /*Room.inMemoryDatabaseBuilder(contexto.getApplicationContext(), BasedatosApp.class)
                            // To simplify the codelab, allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();*/
        }
        return INSTANCIA;
    }

    public static void destruirInstancia() {
        INSTANCIA = null;
    }
}
