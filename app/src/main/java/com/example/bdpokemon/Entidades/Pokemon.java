package com.example.bdpokemon.Entidades;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Type.class,
                parentColumns = "idType",
                childColumns = "idTypeFK")})

public class Pokemon {

    @PrimaryKey
    public int idpokemon;

    public String nombre;
    public String foto;

    @ColumnInfo(name = "idTypeFK")
    public int idType;

    public Pokemon(){

    }

    public Pokemon(int idpokemon, String nombre, String foto, int idType) {
        this.idpokemon = idpokemon;
        this.nombre = nombre;
        this.foto = foto;
        this.idType = idType;
    }
}
