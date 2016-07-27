package compilador;

public class PlusExp extends Exp{

	public Exp left, right;
	 public PlusExp(Exp l, Exp r) {
	 left = l; right = r; } 
	 
	@Override
	public String print() {
		return left.print() + " + " + right.print();
	}

	public int evaluate(Memory mem) {
		return this.left.evaluate(mem) + this.right.evaluate(mem);
	}	
}
