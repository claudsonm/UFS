package linguagem_x;

public class ParBaseCopia extends Parametro {
    TBase tipo;
    ID id;
    
    public ParBaseCopia(TBase tipo, ID id) {
        this.tipo = tipo;
        this.id = id;
    }

    @Override
    Object accept(Visitor vis) {
        vis.visitParBaseCopia(this);
        return null;
    }

}
