package pokemon.istanze.tipoErba;

import pokemon.Pokemon;
import pokemon.abilita.Abilita;
import pokemon.abilita.Verdebufera;
import pokemon.debolezza.Debolezza;
import pokemon.debolezza.DebolezzaErba;
import pokemon.tipo.Tipo;
import pokemon.tipo.TipoErba;

public class Roserade extends Pokemon {
    public Roserade() {
        super("Roserade", new TipoErba(), 1000, 50, new Verdebufera(), new DebolezzaErba());
        setPuntiVitaPieni(1000);
    }

    @Override
    public Pokemon evoluzione() {
        System.out.println(getNome() + " è alla sua evoluzione finale...");
        return this;
    }
}
