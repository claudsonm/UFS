package linguagem_x;

import java.util.List;

public class VarInicExt extends DVar {
    Tipo nomeTipo;
    String id;
    List<Exp> exp;
    
    public VarInicExt(Tipo nome, String id, List<Exp> exp) {
        this.nomeTipo = nome;
        this.id = id;
        this.exp = exp;
    }
    
    @Override
    Object accept(Visitor vis) {
        vis.visitVarInicExt(this);
        return null;
    }
}
