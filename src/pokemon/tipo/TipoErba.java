package pokemon.tipo;

import pokemon.debolezza.Debolezza;
import pokemon.debolezza.DebolezzaErba;

public class TipoErba extends Tipo{
    public TipoErba() {
        super("erba", new DebolezzaErba());
    }
}
