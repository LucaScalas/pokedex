package pokemon.tipo;

import pokemon.debolezza.Debolezza;
import pokemon.debolezza.DebolezzaFuoco;

public class TipoFuoco extends Tipo{
    public TipoFuoco() {
        super("fuoco", new DebolezzaFuoco());
    }
}
