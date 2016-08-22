package sintaxe_abstrata;

public class DC extends DVarConsCom {
    public DCons cons;
    
    public DC(DCons cons) {
        this.cons = cons;
    }

    @Override
    public Object accept(Visitor vis) {
        return vis.visitDC(this);
    }

}
