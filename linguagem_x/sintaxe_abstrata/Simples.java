package sintaxe_abstrata;

import interpretador.Endereco;

public class Simples extends Var {
    public String id;
    public Endereco endereco;

    public Simples(String id) {
        this.id = id;
    }

    @Override
    public Object accept(Visitor vis) {
        return vis.visitSimples(this);
    }

    @Override
    public String toString() {
        return "Simples(" + id + " -> " + endereco + ")";
    }
}
