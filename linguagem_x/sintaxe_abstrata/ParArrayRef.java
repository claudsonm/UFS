package sintaxe_abstrata;

public class ParArrayRef extends Parametro {
    TBase tipo;
    Integer tam;
    String id;
    
    public ParArrayRef(TBase tipo, Integer tam, String id) {
        this.tipo = tipo;
        this.tam = tam;
        this.id = id;
    }

    @Override
    Object accept(Visitor vis) {
        vis.visitParArrayRef(this);
        return null;
    }

}
