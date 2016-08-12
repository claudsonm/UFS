package sintaxe_abstrata;

import java.util.List;

public class ConsExt extends DCons {
    public Tipo tipo;
    public String id;
    public List<Exp> exp;
    
    public ConsExt(Tipo tipo, String id, List<Exp> exp) {
        this.tipo = tipo;
        this.id = id;
        this.exp = exp;
    }

    @Override
    public Object accept(Visitor vis) {
        vis.visitConsExt(this);
        return null;
    }

}
