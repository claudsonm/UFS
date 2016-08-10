package sintaxe_abstrata;

public abstract class Comando {
    abstract Object accept(Visitor vis);
}
