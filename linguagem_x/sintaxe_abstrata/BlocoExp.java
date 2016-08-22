package sintaxe_abstrata;

import java.util.List;

public class BlocoExp extends Exp {
    public List<DCons> listaCons;
    public Exp exp;
    
    public BlocoExp(List<DCons> lista, Exp exp) {
        this.listaCons = lista;
        this.exp = exp;
    }

    @Override
    public Object accept(Visitor vis) {
        return vis.visitBlocoExp(this);
    }

}
