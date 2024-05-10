package pokemon.tipo;

import pokemon.debolezza.Debolezza;
import pokemon.debolezza.DebolezzaAcqua;

public class TipoAcqua extends Tipo{
    public TipoAcqua() {
        super("acqua", new DebolezzaAcqua());
    }
}
