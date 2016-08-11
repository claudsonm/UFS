package sintaxe_abstrata;

public class ASSIGN extends Comando {
    Var var;
    Exp exp;
    
    public ASSIGN(Var var, Exp exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public Object accept(Visitor vis) {
        vis.visitAssign(this);
        return null;
    }

}
