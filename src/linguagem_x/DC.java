package linguagem_x;

public class DC extends DVarConsCom {
    DCons cons;
    
    public DC(DCons cons) {
        this.cons = cons;
    }

    @Override
    Object accept(Visitor vis) {
        vis.visitDC(this);
        return null;
    }

}
