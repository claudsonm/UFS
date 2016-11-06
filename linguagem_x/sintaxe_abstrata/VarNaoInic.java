package sintaxe_abstrata;

public class VarNaoInic extends DVar {
    public Tipo nomeTipo;
    public String id;

    public VarNaoInic(Tipo nomeTipo, String id) {
        this.nomeTipo = nomeTipo;
        this.id = id;
    }

    @Override
    public Object accept(Visitor vis) {
        return vis.visitVarNaoInic(this);
    }

    @Override
    public String toString() {
        return "VarNaoInic(" + nomeTipo + ", " + id + ")";
    }
}
