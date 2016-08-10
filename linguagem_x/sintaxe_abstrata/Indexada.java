package sintaxe_abstrata;

public class Indexada extends Var {
    public Var var;
    public Exp exp;
    
    public Indexada(Var var, Exp exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    Object accept(Visitor vis) {
        vis.visitIndexada(this);
        return null;
    }

}
