package compilador;

public class SingleExpList extends ExpList{
	
	public Exp exp;
	public SingleExpList(Exp e) { exp = e; }

	@Override
	public String print() {
		return exp.print();
	}

}
