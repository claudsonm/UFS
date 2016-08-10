package sintaxe_abstrata;

public class LiteralInt extends Exp {
    int valor;
    
    public LiteralInt(int valor) {
        this.valor = valor;
    }

    @Override
    public Object accept(Visitor vis) {
        vis.visitLiteralInt(this);
        return null;
    }

}
