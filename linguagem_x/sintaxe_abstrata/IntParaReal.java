package sintaxe_abstrata;

public class IntParaReal extends Exp {
    public Exp exp;
    
    public IntParaReal(Exp exp) {
        this.exp = exp;
    }

    @Override
    public Object accept(Visitor vis) {
        return vis.visitIntParaReal(this);
    }
    
}
