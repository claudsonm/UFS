package linguagem_x;

import java.util.List;

public class ConsExt extends DCons {
    Tipo tipo;
    String id;
    List<Exp> exp;
    
    public ConsExt(Tipo tipo, String id, List<Exp> exp) {
        this.tipo = tipo;
        this.id = id;
        this.exp = exp;
    }

    @Override
    Object accept(Visitor vis) {
        vis.visitConsExt(this);
        return null;
    }

}
