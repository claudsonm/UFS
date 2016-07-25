package compilador;

public class Main {

    public static void main(String[] args) {
        Stm prog = new CompoundStm(
            new AssignStm("a", new PlusExp(new NumExp(5), new NumExp(3))),

            new CompoundStm(
                new AssignStm("b", new EseqExp(
                    new PrintStm(new PairExpList(
                        new IdExp("a"),
                        new SingleExpList(new MinusExp(
                            new IdExp("a"),
                            new NumExp(1))
                        )
                    )),
                    new TimesExp(new NumExp(10), new IdExp("a")))),

                new PrintStm(new SingleExpList(new IdExp("b")))
            )
        );
        
        // a := 5+3 ; b := (print(a, a-1), 10*a) ; print(b)
        System.out.println(prog.print());
    }

}
