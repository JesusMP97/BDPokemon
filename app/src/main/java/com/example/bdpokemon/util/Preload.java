package com.example.bdpokemon.util;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;

import androidx.annotation.AnyRes;
import androidx.annotation.NonNull;

import com.example.bdpokemon.Entidades.Pokemon;
import com.example.bdpokemon.Entidades.Type;
import com.example.bdpokemon.R;
import com.example.bdpokemon.room.PokemonDatabase;

import java.util.Random;


public class Preload {

    private static Context context;

    public Preload(Context context) {
        this.context = context;
    }

    public static void cargaSincrona(@NonNull final PokemonDatabase db) {
        cargaDatos(db);
    }

    public static void restartDbSincrona(@NonNull final PokemonDatabase db) {
        reiniciarDatos(db);
    }

    private static void cargaDatos(PokemonDatabase db) {

        db.modeloPokemon().deleteAllPokemons();
        db.modeloType().deleteAllTypes();

        TypedArray ta = context.getResources().obtainTypedArray(R.array.pokemonIds);
        String[] pokemonNames = {"Bulbasaur","Ivysaur","Venusaur","Charmander","Charmeleon","Charizard","Squirtle","Wartortle","Blastoise","Caterpie","Metapod","Butterfree","Weedle","Kakuna","Beedrill","Pidgey","Pidgeotto","Pidgeot","Rattata","Raticate","Spearow","Fearow","Ekans","Arbok","Pikachu","Raichu","Sandshrew","Sandslash","Nidoran","Nidorina","Nidoqueen","Nidoran","Nidorino","Nidoking","Clefairy","Clefable","Vulpix","Ninetales","Jigglypuff","Wigglytuff","Zubat","Golbat","Oddish","Gloom","Vileplume","Paras","Parasect","Venonat","Venomoth","Diglett","Dugtrio","Meowth","Persian","Psyduck","Golduck","Mankey","Primeape","Growlithe","Arcanine","Poliwag","Poliwhirl","Poliwrath","Abra","Kadabra","Alakazam","Machop","Machoke","Machamp","Bellsprout","Weepinbell","Victreebel","Tentacool","Tentacruel","Geodude","Graveler","Golem","Ponyta","Rapidash","Slowpoke","Slowbro","Magnemite","Magneton","Farfetch'd","Doduo","Dodrio","Seel","Dewgong","Grimer","Muk","Shellder","Cloyster","Gastly","Haunter","Gengar","Onix","Drowzee","Hypno","Krabby","Kingler","Voltorb","Electrode","Exeggcute","Exeggutor","Cubone","Marowak","Hitmonlee","Hitmonchan","Lickitung","Koffing","Weezing","Rhyhorn","Rhydon","Chansey","Tangela","Kangaskhan","Horsea","Seadra","Goldeen","Seaking","Staryu","Starmie","Mr. Mime","Scyther","Jynx","Electabuzz","Magmar","Pinsir","Tauros","Magikarp","Gyarados","Lapras","Ditto","Eevee","Vaporeon","Jolteon","Flareon","Porygon","Omanyte","Omastar","Kabuto","Kabutops","Aerodactyl","Snorlax","Articuno","Zapdos","Moltres","Dratini","Dragonair","Dragonite","Mewtwo","Mew"};
        String[] pokemonTypes = {"Bug", "Dragon", "Electric", "Fighting", "Fire", "Flying", "Ghost", "Grass", "Ground", "Ice", "Normal", "Poison", "Psychic", "Rock", "Water"};

        for (int i = 0; i < pokemonTypes.length; i++){
            addType(db, i, pokemonTypes[i]);
        }

        for (int i = 0; i < pokemonNames.length; i++){
            String uriFoto = getUriToDrawable(context, ta.getResourceId(i, 0));
            addPokemon(db, i, pokemonNames[i], uriFoto, new Random().nextInt(pokemonTypes.length));
        }

    }

    public static final String getUriToDrawable(@NonNull Context context,
                                             @AnyRes int drawableId) {
        String imageUri = ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + context.getResources().getResourcePackageName(drawableId)
                + '/' + context.getResources().getResourceTypeName(drawableId)
                + '/' + context.getResources().getResourceEntryName(drawableId);
        return imageUri;
    }

    private static void reiniciarDatos(PokemonDatabase db) {
        db.modeloPokemon().deleteAllPokemons();
        db.modeloType().deleteAllTypes();

        TypedArray ta = context.getResources().obtainTypedArray(R.array.pokemonIds);
        String[] pokemonNames = {"Bulbasaur","Ivysaur","Venusaur","Charmander","Charmeleon","Charizard","Squirtle","Wartortle","Blastoise","Caterpie","Metapod","Butterfree","Weedle","Kakuna","Beedrill","Pidgey","Pidgeotto","Pidgeot","Rattata","Raticate","Spearow","Fearow","Ekans","Arbok","Pikachu","Raichu","Sandshrew","Sandslash","Nidoran","Nidorina","Nidoqueen","Nidoran","Nidorino","Nidoking","Clefairy","Clefable","Vulpix","Ninetales","Jigglypuff","Wigglytuff","Zubat","Golbat","Oddish","Gloom","Vileplume","Paras","Parasect","Venonat","Venomoth","Diglett","Dugtrio","Meowth","Persian","Psyduck","Golduck","Mankey","Primeape","Growlithe","Arcanine","Poliwag","Poliwhirl","Poliwrath","Abra","Kadabra","Alakazam","Machop","Machoke","Machamp","Bellsprout","Weepinbell","Victreebel","Tentacool","Tentacruel","Geodude","Graveler","Golem","Ponyta","Rapidash","Slowpoke","Slowbro","Magnemite","Magneton","Farfetch'd","Doduo","Dodrio","Seel","Dewgong","Grimer","Muk","Shellder","Cloyster","Gastly","Haunter","Gengar","Onix","Drowzee","Hypno","Krabby","Kingler","Voltorb","Electrode","Exeggcute","Exeggutor","Cubone","Marowak","Hitmonlee","Hitmonchan","Lickitung","Koffing","Weezing","Rhyhorn","Rhydon","Chansey","Tangela","Kangaskhan","Horsea","Seadra","Goldeen","Seaking","Staryu","Starmie","Mr. Mime","Scyther","Jynx","Electabuzz","Magmar","Pinsir","Tauros","Magikarp","Gyarados","Lapras","Ditto","Eevee","Vaporeon","Jolteon","Flareon","Porygon","Omanyte","Omastar","Kabuto","Kabutops","Aerodactyl","Snorlax","Articuno","Zapdos","Moltres","Dratini","Dragonair","Dragonite","Mewtwo","Mew"};
        String[] pokemonTypes = {"Bug", "Dragon", "Electric", "Fighting", "Fire", "Flying", "Ghost", "Grass", "Ground", "Ice", "Normal", "Poison", "Psychic", "Rock", "Water"};

        for (int i = 0; i < pokemonTypes.length; i++){
            addType(db, i, pokemonTypes[i]);
        }

        for (int i = 0; i < pokemonNames.length; i++){
            String uriFoto = getUriToDrawable(context, ta.getResourceId(i, 0));
            addPokemon(db, i, pokemonNames[i], uriFoto, new Random().nextInt(pokemonTypes.length));
        }

    }

    private static Pokemon addPokemon(final PokemonDatabase db, final int idPokemon,
                                      final String nombre, final String foto,
                                      final int idType) {
        Pokemon pokemon = new Pokemon();
        pokemon.idpokemon = idPokemon;
        pokemon.nombre = nombre;
        pokemon.foto = foto;
        pokemon.idType = idType;
        db.modeloPokemon().insertPokemon(pokemon);
        return pokemon;
    }

    private static Type addType(final PokemonDatabase db, final int idType, final String typeName) {
        Type type = new Type();
        type.idType = idType;
        type.typeName = typeName;
        db.modeloType().insertType(type);
        return type;
    }

    public static void reorderIds(PokemonDatabase db, int idFrom){
        for(int i = idFrom; i < db.modeloPokemon().getPokemons().size()+1; i++) {
            db.modeloPokemon().idMinusOne(i);
        }
    }

}
