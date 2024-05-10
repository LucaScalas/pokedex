package allenatori;

import pokedex.PokeDex;
import pokemon.Pokemon;

import java.util.HashMap;
import java.util.List;

public class Allenatore {
    private String nome;
    private int eta;
    private int esperienza;
    private int numeroDiPokemonPosseduti;
    private PokeDex pokeDex = new PokeDex();
    private HashMap <String,Pokemon> listaLimitataPersonale = new HashMap<>();
    private HashMap <String,Pokemon> portfolio = new HashMap<>();

    // Costruttore
    public Allenatore(String nome, int eta, int esperienza, int numeroDiPokemonPosseduti) {
        this.nome = nome;
        this.eta = eta;
        this.esperienza = esperienza;
        this.numeroDiPokemonPosseduti = numeroDiPokemonPosseduti;
    }

    // Metodi
    public void aggiungiPokemonAListaPersonale(String nomePokemon) {
        // Metodo che prende un pokemon dal pokedex e lo aggiunge alla lista personale
        if (getListaLimitataPersonale().size()<6) {
            if (pokeDex.getCatalogo().containsKey(nomePokemon)){
                listaLimitataPersonale.put(nomePokemon,pokeDex.getPokemonByName(nomePokemon));
                System.out.println("Il pokemon " + nomePokemon + " è stato aggiunto alla lista personale correttamente!");
            } else System.out.println("Il pokemon che hai provato a inserire non esiste...");
        } else System.out.println("La tua lista personale è piena, libera prima un pokemon!");
    }
    public void liberaPokemon(String nomePokemon) {
        // Metodo che elimina un pokemon dalla lista personale in base al nome
        if (getListaLimitataPersonale().containsKey(nomePokemon)){
            System.out.println(nomePokemon + " è stato liberato...");
            getListaLimitataPersonale().remove(nomePokemon);
        } else System.out.println("Il nome inserito non corrisponde a nessun pokemon della tua lista personale...");
    }
    public Pokemon getPokemonListaPersonale(String nome) {
        // Metodo che restituisce Pokemon dalla lista personale sulla base del nome
        return listaLimitataPersonale.get(nome);
    }
    public void aggiungiPokemonAPortfolio(String nomePokemon){
        // Metodo che aggiunge un pokemon dalla lista personale al portfolio di un allenatore
        if (getPortfolio().size()<3) {
            if (getListaLimitataPersonale().containsKey(nomePokemon)){
                getPortfolio().put(nomePokemon,getPokemonListaPersonale(nomePokemon));
                System.out.println(nomePokemon + " è stato aggiunto al portfolio con successo!");
            } else System.out.println("Il pokemon che hai provato a inserire non esiste...");
        } else System.out.println("Il tuo portfolio è pieno!");
    }
    public void curaTeam(){
        // Metodo che cura tutti i pokemon del portfolio dell'allenatore
        String[] chiavi = getPortfolio().keySet().toArray(new String[3]);
        for (String s : chiavi) {
            getPortfolio().get(s).setPuntiVita(getPortfolio().get(s).getPuntiVitaPieni());
        }

    }

    // Getter e Setter
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getEta() {
        return eta;
    }
    public void setEta(int eta) {
        this.eta = eta;
    }
    public int getEsperienza() {
        return esperienza;
    }
    public void setEsperienza(int esperienza) {
        this.esperienza = esperienza;
    }
    public int getNumeroDiPokemonPosseduti() {
        return numeroDiPokemonPosseduti;
    }
    public void setNumeroDiPokemonPosseduti(int numeroDiPokemonPosseduti) {
        this.numeroDiPokemonPosseduti = numeroDiPokemonPosseduti;
    }
    public HashMap<String, Pokemon> getListaLimitataPersonale() {
        return listaLimitataPersonale;
    }
    public void setListaLimitataPersonale(HashMap<String, Pokemon> listaLimitataPersonale) {
        this.listaLimitataPersonale = listaLimitataPersonale;
    }
    public PokeDex getPokeDex() {
        return pokeDex;
    }
    public void setPokeDex(PokeDex pokeDex) {
        this.pokeDex = pokeDex;
    }
    public HashMap<String, Pokemon> getPortfolio() {
        return portfolio;
    }
    public void setPortfolio(HashMap<String, Pokemon> portfolio) {
        this.portfolio = portfolio;
    }
}
