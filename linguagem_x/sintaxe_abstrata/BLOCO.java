package sintaxe_abstrata;

import java.util.List;

public class BLOCO extends Comando {
    public List<DVarConsCom> lista;

    public BLOCO(List<DVarConsCom> lista) {
        this.lista = lista;
    }

    @Override
    public Object accept(Visitor vis) {
        return vis.visitBloco(this);
    }

    @Override
    public String toString() {
        return "BLOCO(" + lista + ")";
    }
}
