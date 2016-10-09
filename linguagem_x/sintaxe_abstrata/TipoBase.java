package sintaxe_abstrata;

public class TipoBase extends Tipo {
    public TBase tipo;
    
    public TipoBase(TBase valor) {
        this.tipo = valor;
    }

    @Override
    public Object accept(Visitor vis) {
        return vis.visitTipoBase(this);
    }
    
    @Override
    public String toString() {
        return tipo.nome;
    }

}
