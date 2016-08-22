package sintaxe_abstrata;

public class LiteralBool extends Exp {
    public boolean valor;
    
    public LiteralBool(boolean valor) {
        this.valor = valor;
    }

    @Override
    public Object accept(Visitor vis) {
        return vis.visitLiteralBool(this);
    }

}
