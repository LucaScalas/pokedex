package pokedex;

import pokemon.Pokemon;
import pokemon.istanze.tipoAcqua.*;
import pokemon.istanze.tipoErba.*;
import pokemon.istanze.tipoFuoco.*;

import java.util.HashMap;

public class PokeDex {
    private HashMap<String,Pokemon> catalogo = new HashMap<>();

    // Metodi
    public Pokemon getPokemonByName(String nome) {
        // Restituisce Pokemon sulla base del nome
        return getCatalogo().get(nome);
    }
    public void aggiungiPokemonACatalogo(Pokemon pokemon){
        // Metodo che aggiunge un pokemon al catalogo del pokedex
        catalogo.put(pokemon.getNome(), pokemon);
    }
    public void completaCatalogo(){
        // Riempie il catalogo con tutti i pokemon esistenti
        aggiungiPokemonACatalogo(new Wartortle());
        aggiungiPokemonACatalogo(new Magikarp());
        aggiungiPokemonACatalogo(new Lapras());
        aggiungiPokemonACatalogo(new Ivysaur());
        aggiungiPokemonACatalogo(new Roselia());
        aggiungiPokemonACatalogo(new Shaymin());
        aggiungiPokemonACatalogo(new Charmeleon());
        aggiungiPokemonACatalogo(new Magmar());
        aggiungiPokemonACatalogo(new Torkoal());
    }

    //Getter e Setter
    public HashMap<String, Pokemon> getCatalogo() {
        return catalogo;
    }
    public void setCatalogo(HashMap<String, Pokemon> catalogo) {
        this.catalogo = catalogo;
    }
}
