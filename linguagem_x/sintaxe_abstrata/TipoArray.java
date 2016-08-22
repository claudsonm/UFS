package sintaxe_abstrata;

import java.util.List;

public class TipoArray extends Tipo {
    public TBase base;
    public List<Exp> exp;
    
    public TipoArray(TBase tipo, List<Exp> lista) {
        this.base = tipo;
        this.exp = lista;
    }

    @Override
    public Object accept(Visitor vis) {
        return vis.visitTipoArray(this);
    }
    
}
