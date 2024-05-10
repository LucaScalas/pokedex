package pokemon.istanze.tipoErba;

import pokemon.Pokemon;
import pokemon.abilita.Abilita;
import pokemon.abilita.Verdebufera;
import pokemon.debolezza.Debolezza;
import pokemon.debolezza.DebolezzaErba;
import pokemon.istanze.tipoAcqua.Gyarados;
import pokemon.tipo.Tipo;
import pokemon.tipo.TipoErba;

public class Ivysaur extends Pokemon {
    public Ivysaur() {
        super("Ivysaur", new TipoErba(), 1000, 50, new Verdebufera(), new DebolezzaErba());
        setPuntiVitaPieni(1000);
    }

    @Override
    public Pokemon evoluzione() {
        if (getEsperienza()>=100){
            Venusaur venusaur = new Venusaur();
            venusaur.setEsperienza(getEsperienza());
            System.out.println(getNome() + " si è evoluto in " + venusaur.getNome() + "!");
            return venusaur;
        } else {
            System.out.println(getNome() + " non ha ancora abbastanza esperienza per evolversi...");
            return this;
        }
    }
}
