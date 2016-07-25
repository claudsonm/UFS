package compilador;

public class DivExp extends Exp{
    public Exp left, right;
    public DivExp(Exp l, Exp r) {
    left = l; right = r; }
    
   @Override
   public String print() {
       return left.print() + " / " + right.print();
   }
}
