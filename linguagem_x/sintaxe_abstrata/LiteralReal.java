package sintaxe_abstrata;

public class LiteralReal extends Exp {
    public double valor;
    
    public LiteralReal(double valor) {
        this.valor = valor;
    }

    @Override
    public Object accept(Visitor vis) {
        vis.visitLiteralReal(this);
        return null;
    }
}
