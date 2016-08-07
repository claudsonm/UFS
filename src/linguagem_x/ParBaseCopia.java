package linguagem_x;

public class ParBaseCopia extends Parametro {
    TBase tipo;
    String id;
    
    public ParBaseCopia(TBase tipo, String id) {
        this.tipo = tipo;
        this.id = id;
    }

    @Override
    Object accept(Visitor vis) {
        vis.visitParBaseCopia(this);
        return null;
    }

}
