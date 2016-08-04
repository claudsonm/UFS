package linguagem_x;

public class DecVar extends Dec {
    DVar var;
    
    public DecVar(DVar var) {
        this.var = var;
    }
    
    @Override
    Object accept(Visitor vis) {
        // TODO Auto-generated method stub
        return null;
    }

}
