package linguagem_x;

public class LiteralBool extends Exp {
    boolean valor;
    
    public LiteralBool(boolean valor) {
        this.valor = valor;
    }

    @Override
    Object accept(Visitor vis) {
        // TODO Auto-generated method stub
        return null;
    }

}
