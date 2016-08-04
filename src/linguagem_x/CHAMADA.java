package linguagem_x;

import java.util.List;

public class CHAMADA extends Comando {
    ID id;
    List<Exp> listaExp;
    
    public CHAMADA(ID id, List<Exp> lista) {
        this.id = id;
        this.listaExp = lista;
    }

    @Override
    Object accept(Visitor vis) {
        // TODO Auto-generated method stub
        return null;
    }

}
