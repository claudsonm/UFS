package linguagem_x;

public class Simples extends Var {
    ID id;
    
    public Simples(ID id) {
        this.id = id;
    }

    @Override
    Object accept(Visitor vis) {
        vis.visitSimples(this);
        return null;
    }

}
