package sintaxe_abstrata;

public class Menos extends Exp {
    public Exp exp;

    public Menos(Exp exp) {
        this.exp = exp;
    }

    @Override
    public Object accept(Visitor vis) {
        return vis.visitMenos(this);
    }

    @Override
    public String toString() {
        return "Menos(" + exp + ")";
    }
}
