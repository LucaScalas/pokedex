package pokemon.istanze.tipoFuoco;

import pokemon.Pokemon;
import pokemon.abilita.Abilita;
import pokemon.abilita.Lanciafiamme;
import pokemon.debolezza.Debolezza;
import pokemon.debolezza.DebolezzaFuoco;
import pokemon.tipo.Tipo;
import pokemon.tipo.TipoFuoco;

public class Torkoal extends Pokemon {
    public Torkoal() {
        super("Torkoal", new TipoFuoco(), 1000, 50, new Lanciafiamme(), new DebolezzaFuoco());
        setPuntiVitaPieni(1000);
    }

    @Override
    public Pokemon evoluzione() {
        System.out.println(getNome() + " non ha evoluzioni...");
        return this;
    }
}
