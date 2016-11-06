package sintaxe_abstrata;

public enum BinOp {
    Som("+"),
    Sub("-"),
    Mul("*"),
    Div("/"),
    Mod("%"),
    Igual("="),
    Menor("<"),
    Maior(">"),
    E("and"),
    Ou("or");

    public final String token;

    private BinOp(String valor) {
        this.token = valor;
    }
}
