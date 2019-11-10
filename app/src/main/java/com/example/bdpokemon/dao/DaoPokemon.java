package com.example.bdpokemon.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bdpokemon.Entidades.Pokemon;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface DaoPokemon {
    @Query("select * from Pokemon")
    List<Pokemon> getPokemons();

    @Query("select * from Pokemon where idpokemon = :id")
    Pokemon getPokemon(int id);

    @Query("select * from Pokemon where nombre = :nombre")
    List<Pokemon> getPokemonByName(String nombre);

    @Insert(onConflict = IGNORE)
    void insertPokemon(Pokemon pokemon);

    @Delete
    void deletePokemon(Pokemon pokemon);

    @Query("delete from Pokemon where nombre like :nombre")
    int deletePokemonByName(String nombre);

    @Insert(onConflict = IGNORE)
    void insertOrUpdatePokemon(Pokemon... pokemon);

    @Query("delete from Pokemon")
    void deleteAllPokemons();

    @Query("update Pokemon SET idpokemon=:id-1 where idpokemon=:id")
    void idMinusOne(int id);
}
