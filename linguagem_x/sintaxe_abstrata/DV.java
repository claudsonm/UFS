package sintaxe_abstrata;

public class DV extends DVarConsCom {
    public DVar var;
    
    public DV(DVar var) {
        this.var = var;
    }

    @Override
    public Object accept(Visitor vis) {
        return vis.visitDV(this);
    }
    
    @Override
    public String toString() {
        return "DV(" + var + ")";
    }

}
