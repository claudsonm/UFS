package sintaxe_abstrata;

public class LiteralInt extends Exp {
    public int valor;
    
    public LiteralInt(int valor) {
        this.valor = valor;
    }

    @Override
    public Object accept(Visitor vis) {
        vis.visitLiteralInt(this);
        return null;
    }

}
