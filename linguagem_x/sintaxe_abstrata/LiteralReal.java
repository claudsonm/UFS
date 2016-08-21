package sintaxe_abstrata;

public class LiteralReal extends Exp {
    public float valor;
    
    public LiteralReal(float valor) {
        this.valor = valor;
    }

    @Override
    public Object accept(Visitor vis) {
        vis.visitLiteralReal(this);
        return null;
    }
}
