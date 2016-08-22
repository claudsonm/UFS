package sintaxe_abstrata;

import java.util.List;

public class Procedimento extends Dec {
    public String id;
    public List<Parametro> listaParam;
    public Comando comando;
    
    public Procedimento(String id, List<Parametro> lista, Comando com) {
        this.id = id;
        this.listaParam = lista;
        this.comando = com;
    }

    @Override
    public Object accept(Visitor vis) {
        return vis.visitProcedimento(this);
    }

}
