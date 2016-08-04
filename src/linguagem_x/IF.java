package linguagem_x;

public class IF extends Comando {
    Exp exp;
    Comando comandoVerdade;
    Comando comandoFalso;
    
    public IF(Exp exp, Comando com1, Comando com2) {
        this.exp = exp;
        this.comandoVerdade = com1;
        this.comandoFalso = com2;
    }

    @Override
    Object accept(Visitor vis) {
        // TODO Auto-generated method stub
        return null;
    }

}
