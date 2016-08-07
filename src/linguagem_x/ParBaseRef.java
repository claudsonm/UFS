package linguagem_x;

public class ParBaseRef extends Parametro {
    TBase tipo;
    String id;
    
    public ParBaseRef(TBase tipo, String id) {
        this.tipo = tipo;
        this.id = id;
    }

    @Override
    Object accept(Visitor vis) {
        vis.visitParBaseRef(this);
        return null;
    }

}
