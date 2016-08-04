package linguagem_x;

public class Cons extends DCons {
    Tipo tipo;
    ID id;
    Exp exp;
    
    public Cons(Tipo tipo, ID id, Exp exp) {
        this.tipo = tipo;
        this.id = id;
        this.exp = exp;
    }
    
    @Override
    Object accept(Visitor vis) {
        // TODO Auto-generated method stub
        return null;
    }

}
