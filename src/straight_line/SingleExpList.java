package straight_line;

public class SingleExpList extends ExpList{
	
	public Exp exp;
	public SingleExpList(Exp e) { exp = e; }

	@Override
	public String print() {
		return exp.print();
	}
	
	@Override
	int evaluate(Memory mem) {
	    return exp.evaluate(mem);
	}

}
