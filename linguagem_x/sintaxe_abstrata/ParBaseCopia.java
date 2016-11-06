package sintaxe_abstrata;

public class ParBaseCopia extends Parametro {
    public TBase tipo;
    public String id;

    public ParBaseCopia(TBase tipo, String id) {
        this.tipo = tipo;
        this.id = id;
    }

    @Override
    public Object accept(Visitor vis) {
        return vis.visitParBaseCopia(this);
    }

    @Override
    public String toString() {
        return "ParBaseCopia(" + tipo + ", " + id + ")";
    }
}
