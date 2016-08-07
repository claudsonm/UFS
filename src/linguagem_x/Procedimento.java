package linguagem_x;

import java.util.List;

public class Procedimento extends Dec {
    String id;
    List<Parametro> listaParam;
    Comando comando;
    
    public Procedimento(String id, List<Parametro> lista, Comando com) {
        this.id = id;
        this.listaParam = lista;
        this.comando = com;
    }

    @Override
    Object accept(Visitor vis) {
        vis.visitProcedimento(this);
        return null;
    }

}
