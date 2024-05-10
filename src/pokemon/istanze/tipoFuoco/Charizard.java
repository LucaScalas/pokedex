package pokemon.istanze.tipoFuoco;

import pokemon.Pokemon;
import pokemon.abilita.Abilita;
import pokemon.abilita.Lanciafiamme;
import pokemon.debolezza.Debolezza;
import pokemon.debolezza.DebolezzaFuoco;
import pokemon.tipo.Tipo;
import pokemon.tipo.TipoFuoco;

public class Charizard extends Pokemon {
    public Charizard() {
        super("Charizard", new TipoFuoco(), 1000, 50, new Lanciafiamme(), new DebolezzaFuoco());
        setPuntiVitaPieni(1000);
    }

    @Override
    public Pokemon evoluzione() {
        System.out.println(getNome() + " è alla sua evoluzione finale...");
        return this;
    }
}
