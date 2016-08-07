package linguagem_x;

public class Com extends DVarConsCom {
    Comando comando;
    
    public Com(Comando com) {
        this.comando = com;
    }

    @Override
    Object accept(Visitor vis) {
        vis.visitCom(this);
        return null;
    }

}
