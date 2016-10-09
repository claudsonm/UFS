package sintaxe_abstrata;

import java.util.List;

public class ChamadaExp extends Exp {
    public String id;
    public List<Exp> listaExp;
    
    public ChamadaExp(String id, List<Exp> lista) {
        this.id = id;
        this.listaExp = lista;
    }

    @Override
    public Object accept(Visitor vis) {
        return vis.visitChamadaExp(this);
    }
    
    @Override
    public String toString() {
        return "ChamadaExp(" + id + ", " + listaExp + ")";
    }

}
