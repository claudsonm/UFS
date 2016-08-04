package linguagem_x;

public class LiteralBool extends Exp {
    boolean valor;
    
    public LiteralBool(boolean valor) {
        this.valor = valor;
    }

    @Override
    Object accept(Visitor vis) {
        vis.visitLiteralBool(this);
        return null;
    }

}
