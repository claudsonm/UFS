package sintaxe_abstrata;

import java.util.List;

public class CHAMADA extends Comando {
    String id;
    List<Exp> listaExp;
    
    public CHAMADA(String id, List<Exp> lista) {
        this.id = id;
        this.listaExp = lista;
    }

    @Override
    public Object accept(Visitor vis) {
        vis.visitChamada(this);
        return null;
    }

}
