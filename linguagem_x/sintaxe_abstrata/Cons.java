package sintaxe_abstrata;

public class Cons extends DCons {
    Tipo tipo;
    String id;
    Exp exp;
    
    public Cons(Tipo tipo, String id, Exp exp) {
        this.tipo = tipo;
        this.id = id;
        this.exp = exp;
    }
    
    @Override
    Object accept(Visitor vis) {
        vis.visitCons(this);
        return null;
    }

}
