package compilador;

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
}
