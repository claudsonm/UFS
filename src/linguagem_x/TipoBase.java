package linguagem_x;

public class TipoBase extends Tipo {
    TBase base;
    
    public TipoBase(TBase valor) {
        this.base = valor;
    }

    @Override
    Object accept(Visitor vis) {
        vis.visitTipoBase(this);
        return null;
    }

}
