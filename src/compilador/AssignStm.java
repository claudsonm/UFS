package compilador;

public class AssignStm extends Stm {

	public String id;
	public Exp exp;
	public AssignStm(String i, Exp e) { id = i; exp = e; }
	
	@Override
	public String print() {
		return id + " := " + exp.print();
	}
	
}
