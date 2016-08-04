package linguagem_x;

public class DecVar extends Dec {
    DVar var;
    
    public DecVar(DVar var) {
        this.var = var;
    }
    
    @Override
    Object accept(Visitor vis) {
        vis.visitDecVar(this);
        return null;
    }

}
