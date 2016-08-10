package sintaxe_abstrata;

public class VarInic extends DVar {
    Tipo nomeTipo;
    String id;
    Exp exp;
    
    public VarInic(Tipo nome, String id, Exp exp) {
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
