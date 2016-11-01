package interpretador;

public class IntValue extends Value {
    public int valor;
    
    public IntValue(int v) {
        this.valor = v;
    }
    
    @Override
    public String toString() {
        return "" + this.valor;
    }
}
