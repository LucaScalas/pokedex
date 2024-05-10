package pokemon.istanze.tipoAcqua;

import pokemon.Pokemon;
import pokemon.abilita.Abilita;
import pokemon.abilita.Idropompa;
import pokemon.debolezza.Debolezza;
import pokemon.debolezza.DebolezzaAcqua;
import pokemon.tipo.Tipo;
import pokemon.tipo.TipoAcqua;

public class Lapras extends Pokemon {
    public Lapras() {
        super("Lapras", new TipoAcqua(), 1000, 50, new Idropompa(), new DebolezzaAcqua());
        setPuntiVitaPieni(1000);
    }

    @Override
    public Pokemon evoluzione() {
        System.out.println(getNome() + " non ha evoluzioni...");
        return this;
    }
}
