package linguagem_x;

public class ParArrayCopia extends Parametro {
    TBase tipo;
    Integer tam;
    ID id;
    
    public ParArrayCopia(TBase tipo, Integer tam, ID id) {
        this.tipo = tipo;
        this.tam = tam;
        this.id = id;
    }

    @Override
    Object accept(Visitor vis) {
        // TODO Auto-generated method stub
        return null;
    }

}
