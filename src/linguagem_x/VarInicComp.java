package linguagem_x;

import java.util.List;

public class VarInicComp extends DVar {
    Tipo nomeTipo;
    ID id;
    List<ID> listaID;
    Exp exp;
    
    public VarInicComp(Tipo nome, ID id, List<ID> lista, Exp exp) {
        this.nomeTipo = nome;
        this.id = id;
        this.listaID = lista;
        this.exp = exp;
    }
    
    @Override
    Object accept(Visitor vis) {
        // TODO Auto-generated method stub
        return null;
    }
}
