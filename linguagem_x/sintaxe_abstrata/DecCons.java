package sintaxe_abstrata;

public class DecCons extends Dec {
    public DCons cons;
    
    public DecCons(DCons cons) {
        this.cons = cons;
    }
    
    @Override
    public Object accept(Visitor vis) {
        return vis.visitDecCons(this);
    }
    
    @Override
    public String toString() {
        return "DecCons " + cons;
    }

}
