package sintaxe_abstrata;

public class BinExp extends Exp {
    public BinOp operacao;
    public Exp expEsq;
    public Exp expDir;
    
    public BinExp(BinOp op, Exp exp1, Exp exp2) {
        this.operacao = op;
        this.expEsq = exp1;
        this.expDir = exp2;
    }

    @Override
    public Object accept(Visitor vis) {
        vis.visitBinExp(this);
        return null;
    }

}
