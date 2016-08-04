package linguagem_x;

public class VarExp extends Exp {
    Var var;
    
    public VarExp(Var var) {
        this.var = var;
    }

    @Override
    Object accept(Visitor vis) {
        vis.visitVarExp(this);
        return null;
    }

}
