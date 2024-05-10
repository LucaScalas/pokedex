package pokemon.istanze.tipoFuoco;

import pokemon.Pokemon;
import pokemon.abilita.Abilita;
import pokemon.abilita.Lanciafiamme;
import pokemon.debolezza.Debolezza;
import pokemon.debolezza.DebolezzaFuoco;
import pokemon.istanze.tipoAcqua.Gyarados;
import pokemon.tipo.Tipo;
import pokemon.tipo.TipoFuoco;

public class Magmar extends Pokemon {
    public Magmar() {
        super("Magmar", new TipoFuoco(), 1000, 50, new Lanciafiamme(), new DebolezzaFuoco());
        setPuntiVitaPieni(1000);
    }

    @Override
    public Pokemon evoluzione() {
        if (getEsperienza()>=100){
            Magmortar magmortar = new Magmortar();
            magmortar.setEsperienza(getEsperienza());
            System.out.println(getNome() + " si è evoluto in " + magmortar.getNome() + "!");
            return magmortar;
        } else {
            System.out.println(getNome() + " non ha ancora abbastanza esperienza per evolversi...");
            return this;
        }
    }
}
