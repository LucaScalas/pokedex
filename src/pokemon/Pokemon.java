package pokemon;

import eccezioni.MortePokemonException;
import pokemon.abilita.Abilita;
import pokemon.debolezza.Debolezza;
import pokemon.tipo.Tipo;

public abstract class Pokemon {
    private String nome;
    private Tipo tipo;
    private int puntiVita;
    private int esperienza;
    private Abilita abilita;
    private Debolezza debolezza;
    int puntiVitaPieni;

    // Costruttore
    public Pokemon(String nome, Tipo tipo, int puntiVita, int esperienza, Abilita abilita, Debolezza debolezza) {
        this.nome = nome;
        this.tipo = tipo;
        this.puntiVita = puntiVita;
        this.esperienza = esperienza;
        this.abilita = abilita;
        this.debolezza = debolezza;
    }

    // Metodi
    public void attacco(Pokemon difensore) {
        // Attacco di un pokemon verso un altro

        // Caso in cui la debolezza del pokemon avversario coincida col tipo dell'abilità
        if (difensore.getDebolezza().getNome().equals(getAbilita().getTipo().getNome())){
            difensore.setPuntiVita(getPuntiVita()-getAbilita().getDanno()*2*(int)(getEsperienza()/5.5F));
        }

        // Caso in cui la debolezza del pokemon avversario non coincida col tipo dell'abilità e il tipo del pokemon avversario coincida con quello dell'abilità
        if (!difensore.getDebolezza().getNome().equals(getAbilita().getTipo().getNome()) && difensore.getTipo().getNome().equals(getAbilita().getTipo().getNome())){
            difensore.setPuntiVita(getPuntiVita()-getAbilita().getDanno()/2*(int)(getEsperienza()/5.5F));
        }

        // Caso in cui la debolezza del pokemon avversario non coincida col tipo dell'abilità e il tipo del pokemon avversario non coincida con quello dell'abilità
        if (!difensore.getDebolezza().getNome().equals(getAbilita().getTipo().getNome()) && !difensore.getTipo().getNome().equals(getAbilita().getTipo().getNome())){
            difensore.setPuntiVita(getPuntiVita()-getAbilita().getDanno()*(int)(getEsperienza()/5.5F));
        }

        // Eccezione che gestisce la morte nei duelli
        if (difensore.getPuntiVita()<=0){
            throw new MortePokemonException("Il pokemon è morto");
        }
    }

    public abstract Pokemon evoluzione(); // Metodo che permette ad un pokemon di essere sostituito con la sua evoluzione

    // Getter e Setter
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Tipo getTipo() {
        return tipo;
    }
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    public int getPuntiVita() {
        return puntiVita;
    }
    public void setPuntiVita(int puntiVita) {
        this.puntiVita = puntiVita;
    }
    public int getEsperienza() {
        return esperienza;
    }
    public void setEsperienza(int esperienza) {
        this.esperienza = esperienza;
    }
    public Abilita getAbilita() {
        return abilita;
    }
    public void setAbilita(Abilita abilita) {
        this.abilita = abilita;
    }
    public Debolezza getDebolezza() {
        return debolezza;
    }
    public void setDebolezza(Debolezza debolezza) {
        this.debolezza = debolezza;
    }
    public int getPuntiVitaPieni() {
        return puntiVitaPieni;
    }
    public void setPuntiVitaPieni(int puntiVitaPieni) {
        this.puntiVitaPieni = puntiVitaPieni;
    }
}