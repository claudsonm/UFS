package sintaxe_abstrata;

public class DV extends DVarConsCom {
    DVar var;
    
    public DV(DVar var) {
        this.var = var;
    }

    @Override
    Object accept(Visitor vis) {
        vis.visitDV(this);
        return null;
    }

}
