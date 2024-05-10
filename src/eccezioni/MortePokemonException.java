package eccezioni;

public class MortePokemonException extends RuntimeException{
    // Eccezione creata per gestire la morte dei pokemon dei duelli
public MortePokemonException(String msg){
    super(msg);
}
}
