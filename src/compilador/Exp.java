package compilador;

public abstract class Exp {
	abstract String print();
	abstract int evaluate(Memory mem);
}
