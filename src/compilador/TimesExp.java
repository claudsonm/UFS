package compilador;

public class TimesExp extends Exp {
    public Exp left, right;
    public TimesExp(Exp l, Exp r) {
    left = l; right = r; }
    
   @Override
   public String print() {
       return left.print() + " * " + right.print();
   }
}
