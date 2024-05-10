package pokemon.tipo;

import pokemon.debolezza.Debolezza;

public abstract class Tipo {
    private String nome;
    private Debolezza debolezza;

    // Costruttore
    public Tipo(String nome, Debolezza debolezza) {
        this.nome = nome;
        this.debolezza = debolezza;
    }

    // Getter e Setter
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Debolezza getDebolezza() {
        return debolezza;
    }
    public void setDebolezza(Debolezza debolezza) {
        this.debolezza = debolezza;
    }

}
