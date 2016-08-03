package straight_line;

public class EseqExp extends Exp{

	public Stm stm;
	 public Exp exp;
	 public EseqExp(Stm s, Exp e) {
		 stm = s; exp = e;
	 }
	
	@Override
	public String print() {
		return "(" + stm.print() + ", " + exp.print() + ")";
	}

	public int evaluate(Memory mem) {
	    //throw new UnsupportedOperationException();
	    stm.interpretStatement(mem);
	    return exp.evaluate(mem);
	}
}
