package sintaxe_abstrata;

public class Menos extends Exp {
    public Exp exp;
    
    public Menos(Exp exp) {
        this.exp = exp;
    }

    @Override
    public Object accept(Visitor vis) {
        vis.visitMenos(this);
        return null;
    }
    
}
