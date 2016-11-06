package sintaxe_abstrata;

public class Indexada extends Var {
    public Var var;
    public Exp exp;

    public Indexada(Var var, Exp exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public Object accept(Visitor vis) {
        return vis.visitIndexada(this);
    }

    @Override
    public String toString() {
        return "Indexada(" + var + ", " + exp + ")";
    }
}
