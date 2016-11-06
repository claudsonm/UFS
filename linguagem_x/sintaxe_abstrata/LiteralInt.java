package sintaxe_abstrata;

public class LiteralInt extends Exp {
    public int valor;

    public LiteralInt(int valor) {
        this.valor = valor;
    }

    @Override
    public Object accept(Visitor vis) {
        return vis.visitLiteralInt(this);
    }

    @Override
    public String toString() {
        return "LiteralInt(" + valor + ")";
    }
}
