package linguagem_x;

import java.util.List;

public class ConsExt extends DCons {
    Tipo tipo;
    ID id;
    List<Exp> exp;
    
    public ConsExt(Tipo tipo, ID id, List<Exp> exp) {
        this.tipo = tipo;
        this.id = id;
        this.exp = exp;
    }

    @Override
    Object accept(Visitor vis) {
        // TODO Auto-generated method stub
        return null;
    }

}
