package sintaxe_abstrata;

public class DV extends DVarConsCom {
    public DVar var;
    
    public DV(DVar var) {
        this.var = var;
    }

    @Override
    public Object accept(Visitor vis) {
        vis.visitDV(this);
        return null;
    }

}
