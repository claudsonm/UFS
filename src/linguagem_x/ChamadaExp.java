package linguagem_x;

import java.util.List;

public class ChamadaExp extends Exp {
    String id;
    List<Exp> listaExp;
    
    public ChamadaExp(String id, List<Exp> lista) {
        this.id = id;
        this.listaExp = lista;
    }

    @Override
    Object accept(Visitor vis) {
        vis.visitChamadaExp(this);
        return null;
    }

}
