package linguagem_x;

public class ParBaseRef extends Parametro {
    TBase tipo;
    ID id;
    
    public ParBaseRef(TBase tipo, ID id) {
        this.tipo = tipo;
        this.id = id;
    }

    @Override
    Object accept(Visitor vis) {
        vis.visitParBaseRef(this);
        return null;
    }

}
