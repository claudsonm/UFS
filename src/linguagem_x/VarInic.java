package linguagem_x;

public class VarInic extends DVar {
    Tipo nomeTipo;
    ID id;
    Exp exp;
    
    public VarInic(Tipo nome, ID id, Exp exp) {
        this.nomeTipo = nome;
        this.id = id;
        this.exp = exp;
    }
    
    @Override
    Object accept(Visitor vis) {
        vis.visitVarInic(this);
        return null;
    }
    
}
