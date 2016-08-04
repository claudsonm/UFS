package linguagem_x;

public abstract class Tipo {
    abstract Object accept(Visitor vis);
}
