package linguagem_x;

public class Menos extends Exp {
    Exp exp;
    
    public Menos(Exp exp) {
        this.exp = exp;
    }

    @Override
    Object accept(Visitor vis) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
