package linguagem_x;

public class DecCons extends Dec {
    DCons cons;
    
    public DecCons(DCons cons) {
        this.cons = cons;
    }
    
    @Override
    Object accept(Visitor vis) {
        vis.visitDecCons(this);
        return null;
    }

}
