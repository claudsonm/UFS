package sintaxe_abstrata;

public class LiteralReal extends Exp {
    public double valor;
    
    public LiteralReal(double valor) {
        this.valor = valor;
    }

    @Override
    public Object accept(Visitor vis) {
        return vis.visitLiteralReal(this);
    }
    
    @Override
    public String toString() {
        return "LiteralReal(" + valor + ")";
    }
}
