package linguagem_x;

import java.util.List;

public class ChamadaExp extends Exp {
    ID id;
    List<Exp> listaExp;
    
    public ChamadaExp(ID id, List<Exp> lista) {
        this.id = id;
        this.listaExp = lista;
    }

    @Override
    Object accept(Visitor vis) {
        // TODO Auto-generated method stub
        return null;
    }

}
