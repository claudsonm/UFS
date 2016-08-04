package linguagem_x;

public class TipoBase extends Tipo {
    TBase base;
    
    public TipoBase(TBase valor) {
        this.base = valor;
    }

    @Override
    Object accept(Visitor vis) {
        // TODO Auto-generated method stub
        return null;
    }

}
