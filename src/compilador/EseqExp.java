package compilador;

public class EseqExp extends Exp{

	public Stm stm;
	 public Exp exp;
	 public EseqExp(Stm s, Exp e) {
		 stm = s; exp = e;
	 }
	
	@Override
	String print() {
		// TODO Auto-generated method stub
		return stm.print() + " " + exp.print();
	}
}
