package sintaxe_abstrata;

public class WHILE extends Comando {
    public Exp exp;
    public Comando comando;
    
    public WHILE(Exp exp, Comando com) {
        this.exp = exp;
        this.comando = com;
    }

    @Override
    public Object accept(Visitor vis) {
        vis.visitWhile(this);
        return null;
    }

}
