package linguagem_x;

import java.util.List;

public class VarInicComp extends DVar {
    Tipo nomeTipo;
    String id;
    List<String> listaID;
    Exp exp;
    
    public VarInicComp(Tipo nome, String id, List<String> lista, Exp exp) {
        this.nomeTipo = nome;
        this.id = id;
        this.listaID = lista;
        this.exp = exp;
    }
    
    @Override
    Object accept(Visitor vis) {
        vis.visitVarInicComp(this);
        return null;
    }
}
