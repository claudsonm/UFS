package sintaxe_abstrata;

public class ParArrayRef extends Parametro {
    public TBase tipo;
    public Integer dimensao;
    public String id;
    
    public ParArrayRef(TBase tipo, Integer tam, String id) {
        this.tipo = tipo;
        this.dimensao = tam;
        this.id = id;
    }

    @Override
    public Object accept(Visitor vis) {
        vis.visitParArrayRef(this);
        return null;
    }

}
