package sintaxe_abstrata;

public class Cons extends DCons {
    public Tipo tipo;
    public String id;
    public Exp exp;
    
    public Cons(Tipo tipo, String id, Exp exp) {
        this.tipo = tipo;
        this.id = id;
        this.exp = exp;
    }
    
    @Override
    public Object accept(Visitor vis) {
        return vis.visitCons(this);
    }

}
