package linguagem_x;

public class LiteralInt extends Exp {
    int valor;
    
    public LiteralInt(int valor) {
        this.valor = valor;
    }

    @Override
    Object accept(Visitor vis) {
        vis.visitLiteralInt(this);
        return null;
    }

}
