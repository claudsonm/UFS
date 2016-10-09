package sintaxe_abstrata;

public class ParBaseRef extends Parametro {
    public TBase tipo;
    public String id;
    
    public ParBaseRef(TBase tipo, String id) {
        this.tipo = tipo;
        this.id = id;
    }

    @Override
    public Object accept(Visitor vis) {
        return vis.visitParBaseRef(this);
    }
    
    @Override
    public String toString() {
        return "ParBaseRef(" + tipo + ", " + id + ")";
    }

}
