package sintaxe_abstrata;

public abstract class Comando {
    public abstract Object accept(Visitor vis);
}
