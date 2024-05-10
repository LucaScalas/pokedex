package pokemon.debolezza;

public abstract class Debolezza {
    private String nome;
    private float moltiplicatoreDanno;

    // Costruttore
    public Debolezza(String nome, float moltiplicatoreDanno) {
        this.nome = nome;
        this.moltiplicatoreDanno = moltiplicatoreDanno;
    }

    // Getter e Setter
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public float getMoltiplicatoreDanno() {
        return moltiplicatoreDanno;
    }
    public void setMoltiplicatoreDanno(float moltiplicatoreDanno) {
        this.moltiplicatoreDanno = moltiplicatoreDanno;
    }
}
