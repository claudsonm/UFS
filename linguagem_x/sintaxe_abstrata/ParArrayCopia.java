package sintaxe_abstrata;

public class ParArrayCopia extends Parametro {
    TBase tipo;
    Integer tam;
    String id;
    
    public ParArrayCopia(TBase tipo, Integer tam, String id) {
        this.tipo = tipo;
        this.tam = tam;
        this.id = id;
    }

    @Override
    Object accept(Visitor vis) {
        vis.visitParArrayCopia(this);
        return null;
    }

}
