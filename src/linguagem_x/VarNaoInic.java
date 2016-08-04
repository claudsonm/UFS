package linguagem_x;

public class VarNaoInic extends DVar {
    Tipo nomeTipo;
    ID id;
    
    public VarNaoInic(Tipo nomeTipo, ID id) {
        this.nomeTipo = nomeTipo;
        this.id = id;
    }

    @Override
    Object accept(Visitor vis) {
        // TODO Auto-generated method stub
        return null;
    }
}
