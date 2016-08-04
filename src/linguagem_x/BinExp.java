package linguagem_x;

public class BinExp extends Exp {
    BinOp operacao;
    Exp expEsq;
    Exp expDir;
    
    public BinExp(BinOp op, Exp exp1, Exp exp2) {
        this.operacao = op;
        this.expEsq = exp1;
        this.expDir = exp2;
    }

    @Override
    Object accept(Visitor vis) {
        // TODO Auto-generated method stub
        return null;
    }

}
