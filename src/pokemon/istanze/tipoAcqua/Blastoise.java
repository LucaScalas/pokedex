package pokemon.istanze.tipoAcqua;

import pokemon.Pokemon;
import pokemon.abilita.Abilita;
import pokemon.abilita.Idropompa;
import pokemon.debolezza.Debolezza;
import pokemon.debolezza.DebolezzaAcqua;
import pokemon.tipo.Tipo;
import pokemon.tipo.TipoAcqua;

public class Blastoise extends Pokemon {
    public Blastoise() {
        super("Blastoise", new TipoAcqua(), 1000, 50, new Idropompa(), new DebolezzaAcqua());
        setPuntiVitaPieni(1000);
    }

    @Override
    public Pokemon evoluzione() {
        System.out.println(getNome() + " è alla sua evoluzione finale...");
        return this;
    }
}
