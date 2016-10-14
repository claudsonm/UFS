package sintaxe_abstrata;

import java.util.List;

public class VarInicComp extends DVar {
    public Tipo nomeTipo;
    public String id;
    public List<String> listaID;
    public Exp exp;
    
    public VarInicComp(Tipo nome, String id, List<String> lista, Exp exp) {
        this.nomeTipo = nome;
        this.id = id;
        this.listaID = lista;
        this.exp = exp;
    }
    
    @Override
    public Object accept(Visitor vis) {
        return vis.visitVarInicComp(this);
    }
    
    @Override
    public String toString() {
        return "VarInicComp(" + nomeTipo + ", " + id + ", " + listaID + ", " + exp + ")";
    }
}
