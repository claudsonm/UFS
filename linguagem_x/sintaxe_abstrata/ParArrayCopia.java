package sintaxe_abstrata;

public class ParArrayCopia extends Parametro {
    public TBase tipo;
    public Integer tam;
    public String id;
    
    public ParArrayCopia(TBase tipo, Integer tam, String id) {
        this.tipo = tipo;
        this.tam = tam;
        this.id = id;
    }

    @Override
    public Object accept(Visitor vis) {
        vis.visitParArrayCopia(this);
        return null;
    }

}
