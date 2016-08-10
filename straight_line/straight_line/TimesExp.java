package straight_line;

public class TimesExp extends Exp {
    public Exp left, right;
    public TimesExp(Exp l, Exp r) {
    left = l; right = r; }
    
    @Override
    public String print() {
        return left.print() + " * " + right.print();
    }

    @Override
    public int evaluate(Memory mem) {
    	return this.left.evaluate(mem) * this.right.evaluate(mem);
    }
}
