package linguagem_x;

public class WHILE extends Comando {
    Exp exp;
    Comando comando;
    
    public WHILE(Exp exp, Comando com) {
        this.exp = exp;
        this.comando = com;
    }

    @Override
    Object accept(Visitor vis) {
        // TODO Auto-generated method stub
        return null;
    }

}
