package com.example.bdpokemon.Entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Type {

    @PrimaryKey
    public int idType;

    public String typeName;

}
