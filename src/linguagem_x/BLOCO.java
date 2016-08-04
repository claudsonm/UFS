package linguagem_x;

import java.util.List;

public class BLOCO extends Comando {
    List<DVarConsCom> lista;
    
    public BLOCO(List<DVarConsCom> lista) {
        this.lista = lista;
    }

    @Override
    Object accept(Visitor vis) {
        // TODO Auto-generated method stub
        return null;
    }

}
