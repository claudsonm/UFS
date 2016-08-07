package linguagem_x;

public class Simples extends Var {
    String id;
    
    public Simples(String id) {
        this.id = id;
    }

    @Override
    Object accept(Visitor vis) {
        vis.visitSimples(this);
        return null;
    }

}
