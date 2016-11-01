package interpretador;

public class RealValue extends Value {
    
    public double valor;
    
    public RealValue(double v) {
        this.valor = v;
    }
    
    @Override
    public String toString() {
        return "" + this.valor;
    }

}
