package sintaxe_abstrata;

import java.util.List;

public class BlocoExp extends Exp {
    List<DCons> listaCons;
    Exp exp;
    
    public BlocoExp(List<DCons> lista, Exp exp) {
        this.listaCons = lista;
        this.exp = exp;
    }

    @Override
    public Object accept(Visitor vis) {
        vis.visitBlocoExp(this);
        return null;
    }

}
