package pokemon.istanze.tipoAcqua;

import pokemon.Pokemon;
import pokemon.abilita.Idropompa;
import pokemon.debolezza.DebolezzaAcqua;
import pokemon.tipo.TipoAcqua;

public class Wartortle extends Pokemon {
    public Wartortle() {
        super("Wartortle", new TipoAcqua(), 1000, 50, new Idropompa(), new DebolezzaAcqua());
        setPuntiVitaPieni(1000);
    }

    @Override
    public Pokemon evoluzione(){
        if (getEsperienza()>=100){
            Blastoise blastoise = new Blastoise();
            blastoise.setEsperienza(getEsperienza());
            System.out.println(getNome() + " si è evoluto in " + blastoise.getNome() + "!");
            return blastoise;
        } else {
            System.out.println(getNome() + " non ha ancora abbastanza esperienza per evolversi...");
            return this;
        }
    }
}
