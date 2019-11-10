package com.example.bdpokemon.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bdpokemon.Entidades.Type;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface DaoType {
    @Query("select * from Type")
    List<Type> getTypes();

    @Query("select * from Type where idType = :id")
    Type getType(int id);

    @Query("select * from Type where typeName = :nombre")
    List<Type> getTypeByName(String nombre);

    @Insert(onConflict = IGNORE)
    void insertType(Type type);

    @Delete
    void deleteType(Type type);

    @Query("delete from Type where typeName like :nombre")
    int deleteTypeByName(String nombre);

    @Insert(onConflict = IGNORE)
    void insertOrUpdateType(Type... type);


    @Query("delete from Type")
    void deleteAllTypes();
}