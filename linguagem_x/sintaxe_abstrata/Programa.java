package sintaxe_abstrata;

import java.util.List;

public class Programa {
    public List<Dec> declaracoes;
    
    public Programa(List<Dec> dec) {
        declaracoes = dec;
    }
    
    public Object accept(Visitor vis) {
        return vis.visitPrograma(this);
    }
}
