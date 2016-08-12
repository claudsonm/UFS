package sintaxe_abstrata;

public class TipoBase extends Tipo {
    public TBase tipo;
    
    public TipoBase(TBase valor) {
        this.tipo = valor;
    }

    @Override
    public Object accept(Visitor vis) {
        vis.visitTipoBase(this);
        return null;
    }

}
