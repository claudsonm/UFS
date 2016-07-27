package compilador;

public class IdExp extends Exp {

	public String id;
	public IdExp(String i) { id = i; }
	 
	@Override
	public String print() {
		return id;
	}

	@Override
	public int evaluate(Memory mem) {
		return mem.lookup(this.id);
	}
}
