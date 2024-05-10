package pokemon.istanze.tipoFuoco;

import pokemon.Pokemon;
import pokemon.abilita.Abilita;
import pokemon.abilita.Lanciafiamme;
import pokemon.debolezza.Debolezza;
import pokemon.debolezza.DebolezzaFuoco;
import pokemon.tipo.Tipo;
import pokemon.tipo.TipoFuoco;

public class Charmeleon extends Pokemon {
    public Charmeleon() {
        super("Charmeleon", new TipoFuoco(), 1000, 50, new Lanciafiamme(), new DebolezzaFuoco());
        setPuntiVitaPieni(1000);
    }

    @Override
    public Pokemon evoluzione() {
        if (getEsperienza()>=100){
            Charizard charizard = new Charizard();
            charizard.setEsperienza(getEsperienza());
            System.out.println(getNome() + " si è evoluto in " + charizard.getNome() + "!");
            return charizard;
        } else {
            System.out.println(getNome() + " non ha ancora abbastanza esperienza per evolversi...");
            return this;
        }
    }
}
