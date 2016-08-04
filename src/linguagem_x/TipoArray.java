package linguagem_x;

import java.util.List;

public class TipoArray extends Tipo {
    TBase base;
    List<Exp> exp;
    
    public TipoArray(TBase tipo, List<Exp> lista) {
        this.base = tipo;
        this.exp = lista;
    }

    @Override
    Object accept(Visitor vis) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
