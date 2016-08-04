package linguagem_x;

public abstract class Comando {
    abstract Object accept(Visitor vis);
}
