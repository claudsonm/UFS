package linguagem_x;

public abstract class Exp {
    abstract Object accept(Visitor vis);
}
