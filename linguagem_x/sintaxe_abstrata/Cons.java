package sintaxe_abstrata;

import interpretador.Endereco;

public class Cons extends DCons {
    public Tipo tipo;
    public String id;
    public Exp exp;
    public Endereco endereco;

    public Cons(Tipo tipo, String id, Exp exp) {
        this.tipo = tipo;
        this.id = id;
        this.exp = exp;
    }

    @Override
    public Object accept(Visitor vis) {
        return vis.visitCons(this);
    }

    @Override
    public String toString() {
        return "Cons(" + tipo + ", " + id + ", " + exp + ")";
    }
}
