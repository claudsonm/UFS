package linguagem_x;

public class Indexada extends Var {
    Var var;
    Exp exp;
    
    public Indexada(Var var, Exp exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    Object accept(Visitor vis) {
        // TODO Auto-generated method stub
        return null;
    }

}
