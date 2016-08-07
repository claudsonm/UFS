package linguagem_x;

public class VarNaoInic extends DVar {
    Tipo nomeTipo;
    String id;
    
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
