package linguagem_x;

public class Nao extends Exp {
    Exp exp;
    
    public Nao(Exp exp) {
        this.exp = exp;
    }

    @Override
    Object accept(Visitor vis) {
        // TODO Auto-generated method stub
        return null;
    }

}
