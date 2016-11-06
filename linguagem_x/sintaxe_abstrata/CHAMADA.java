package sintaxe_abstrata;

import java.util.List;

public class CHAMADA extends Comando {
    public String id;
    public List<Exp> listaExp;

    public CHAMADA(String id, List<Exp> lista) {
        this.id = id;
        this.listaExp = lista;
    }

    @Override
    public Object accept(Visitor vis) {
        return vis.visitChamada(this);
    }
}
