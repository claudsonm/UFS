package sintaxe_abstrata;

public abstract class Tipo {
    abstract Object accept(Visitor vis);
}
