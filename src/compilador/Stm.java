package compilador;

public abstract class Stm {

	abstract String print();

	abstract void interpretStatement(Memory mem);
}
