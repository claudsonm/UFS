package sintaxe_abstrata;

public class Simples extends Var {
    public String id;
    
    public Simples(String id) {
        this.id = id;
    }

    @Override
    public Object accept(Visitor vis) {
        vis.visitSimples(this);
        return null;
    }

}
