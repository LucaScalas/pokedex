package pokemon.abilita;

import pokemon.tipo.Tipo;

public abstract class Abilita {
    private String nome;
    private Tipo tipo;
    private int danno;

    // Costruttore
    public Abilita(String nome, Tipo tipo, int danno) {
        this.nome = nome;
        this.tipo = tipo;
        this.danno = danno;
    }

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
    public int getDanno() {
        return danno;
    }
    public void setDanno(int danno) {
        this.danno = danno;
    }
}
