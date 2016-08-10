package sintaxe_abstrata;

public class WHILE extends Comando {
    Exp exp;
    Comando comando;
    
    public WHILE(Exp exp, Comando com) {
        this.exp = exp;
        this.comando = com;
    }

    @Override
    Object accept(Visitor vis) {
        vis.visitWhile(this);
        return null;
    }

}
