package sintaxe_abstrata;

public abstract class Tipo {
    public abstract Object accept(Visitor vis);
}
