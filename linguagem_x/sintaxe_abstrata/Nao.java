package sintaxe_abstrata;

public class Nao extends Exp {
    public Exp exp;
    
    public Nao(Exp exp) {
        this.exp = exp;
    }

    @Override
    public Object accept(Visitor vis) {
        return vis.visitNao(this);
    }

}
