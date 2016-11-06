package sintaxe_abstrata;

public class VarExp extends Exp {
    public Var var;

    public VarExp(Var var) {
        this.var = var;
    }

    @Override
    public Object accept(Visitor vis) {
        return vis.visitVarExp(this);
    }

    @Override
    public String toString() {
        return "VarExp(" + var + ")";
    }
}
