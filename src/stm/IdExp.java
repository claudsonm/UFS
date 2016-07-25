package stm;

public class IdExp extends Exp {

	public String id;
	public IdExp(String i) { id = i; }
	 
	@Override
	String print() {
		// TODO Auto-generated method stub
		return id ;
	}
}
