package interpretador;

public class BoolValue extends Value {
    public boolean valor;
    
    public BoolValue(boolean v) {
        this.valor = v;
    }
    
    @Override
    public String toString() {
        return "" + this.valor;
    }

}
