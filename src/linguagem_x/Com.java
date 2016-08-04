package linguagem_x;

public class Com extends Comando {
    Comando comando;
    
    public Com(Comando com) {
        this.comando = com;
    }

    @Override
    Object accept(Visitor vis) {
        // TODO Auto-generated method stub
        return null;
    }

}
