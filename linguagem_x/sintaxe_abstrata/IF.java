package sintaxe_abstrata;

public class IF extends Comando {
    public Exp exp;
    public Comando comandoVerdade;
    public Comando comandoFalso;
    
    public IF(Exp exp, Comando com1, Comando com2) {
        this.exp = exp;
        this.comandoVerdade = com1;
        this.comandoFalso = com2;
    }

    @Override
    public Object accept(Visitor vis) {
        return vis.visitIf(this);
    }
    
    @Override
    public String toString() {
        return "IF(" + exp + ", " + comandoVerdade + ", " + comandoFalso + ")";
    }

}
