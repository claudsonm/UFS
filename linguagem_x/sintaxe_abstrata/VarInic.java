package sintaxe_abstrata;

public class VarInic extends DVar {
    public Tipo nomeTipo;
    public String id;
    public Exp exp;
    
    public VarInic(Tipo nome, String id, Exp exp) {
        this.nomeTipo = nome;
        this.id = id;
        this.exp = exp;
    }
    
    @Override
    public Object accept(Visitor vis) {
        vis.visitVarInic(this);
        return null;
    }
    
}
