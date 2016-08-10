package sintaxe_abstrata;

public abstract class Exp {
    public abstract Object accept(Visitor vis);
}
