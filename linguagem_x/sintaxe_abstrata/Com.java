package sintaxe_abstrata;

public class Com extends DVarConsCom {
    public Comando comando;
    
    public Com(Comando com) {
        this.comando = com;
    }

    @Override
    public Object accept(Visitor vis) {
        return vis.visitCom(this);
    }

}
