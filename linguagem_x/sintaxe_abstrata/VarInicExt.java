package sintaxe_abstrata;

import java.util.List;

public class VarInicExt extends DVar {
    public Tipo nomeTipo;
    public String id;
    public List<Exp> exp;
    
    public VarInicExt(Tipo nome, String id, List<Exp> exp) {
        this.nomeTipo = nome;
        this.id = id;
        this.exp = exp;
    }
    
    @Override
    public Object accept(Visitor vis) {
        return vis.visitVarInicExt(this);
    }
    
    @Override
    public String toString() {
        return "VarInicExt: (Tipo " + nomeTipo + ", " + id + ", Exp " + exp + ")";
    }
}
