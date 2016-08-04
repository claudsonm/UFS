package linguagem_x;

public enum BinOp {
    Som("+"), Sub("-"), Mul("*"), Div("/"), Mod("%"),
    Igual("="), Menor("<"), E("and"), Ou("or");
    
    public final String token;
    
    private BinOp(String valor) {
        this.token = valor;
    }

}
