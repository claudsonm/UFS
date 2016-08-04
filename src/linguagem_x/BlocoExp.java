package linguagem_x;

import java.util.List;

public class BlocoExp extends Exp {
    List<DCons> listaCons;
    Exp exp;
    
    public BlocoExp(List<DCons> lista, Exp exp) {
        this.listaCons = lista;
        this.exp = exp;
    }

    @Override
    Object accept(Visitor vis) {
        // TODO Auto-generated method stub
        return null;
    }

}
