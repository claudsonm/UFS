package sintaxe_abstrata;

public class ParArrayCopia extends Parametro {
    public TBase tipo;
    public Integer dimensao;
    public String id;
    
    public ParArrayCopia(TBase tipo, Integer tam, String id) {
        this.tipo = tipo;
        this.dimensao = tam;
        this.id = id;
    }

    @Override
    public Object accept(Visitor vis) {
        return vis.visitParArrayCopia(this);
    }

}
