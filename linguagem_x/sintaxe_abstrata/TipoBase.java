package sintaxe_abstrata;

public class TipoBase extends Tipo {
    public TBase base;
    
    public TipoBase(TBase valor) {
        this.base = valor;
    }

    @Override
    Object accept(Visitor vis) {
        vis.visitTipoBase(this);
        return null;
    }

}
