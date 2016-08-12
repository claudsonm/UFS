package sintaxe_abstrata;

public class DecVar extends Dec {
    public DVar var;
    
    public DecVar(DVar var) {
        this.var = var;
    }
    
    @Override
    public Object accept(Visitor vis) {
        vis.visitDecVar(this);
        return null;
    }

}
