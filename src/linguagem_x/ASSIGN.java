package linguagem_x;

public class ASSIGN extends Comando {
    Var var;
    Exp exp;
    
    public ASSIGN(Var var, Exp exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    Object accept(Visitor vis) {
        // TODO Auto-generated method stub
        return null;
    }

}
