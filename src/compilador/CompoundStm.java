package compilador; 

public class CompoundStm extends Stm{
	public Stm stm1,stm2;
	
	public CompoundStm(Stm s1, Stm s2) { stm1=s1; stm2=s2; }
	
	@Override
	public String print() {
		return stm1.print() + " " + stm2.print();
	}

	public void interpretStatement(Memory mem) {
		this.stm1.interpretStatement(mem);
		this.stm2.interpretStatement(mem);
	}

}
