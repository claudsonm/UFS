package linguagem_x;

import java.util.List;

public class CHAMADA extends Comando {
    String id;
    List<Exp> listaExp;
    
    public CHAMADA(String id, List<Exp> lista) {
        this.id = id;
        this.listaExp = lista;
    }

    @Override
    Object accept(Visitor vis) {
        vis.visitChamada(this);
        return null;
    }

}
