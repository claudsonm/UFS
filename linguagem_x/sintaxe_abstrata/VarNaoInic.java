package sintaxe_abstrata;

public class VarNaoInic extends DVar {
    public Tipo nomeTipo;
    public String id;
    
    public VarNaoInic(Tipo nomeTipo, String id) {
        this.nomeTipo = nomeTipo;
        this.id = id;
    }

    @Override
    Object accept(Visitor vis) {
        vis.visitVarNaoInic(this);
        return null;
    }
}
