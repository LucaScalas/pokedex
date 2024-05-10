package pokemon.istanze.tipoAcqua;

import pokemon.Pokemon;
import pokemon.abilita.Abilita;
import pokemon.abilita.Idropompa;
import pokemon.debolezza.Debolezza;
import pokemon.debolezza.DebolezzaAcqua;
import pokemon.tipo.Tipo;
import pokemon.tipo.TipoAcqua;

public class Magikarp extends Pokemon {
    public Magikarp() {
        super("Magikarp", new TipoAcqua(), 1000, 50, new Idropompa(), new DebolezzaAcqua());
        setPuntiVitaPieni(1000);
    }

    @Override
    public Pokemon evoluzione() {
        if (getEsperienza()>=100){
            Gyarados gyarados = new Gyarados();
            gyarados.setEsperienza(getEsperienza());
            System.out.println(getNome() + " si è evoluto in " + gyarados.getNome() + "!");
            return gyarados;
        } else {
            System.out.println(getNome() + " non ha ancora abbastanza esperienza per evolversi...");
            return this;
        }
    }
}
