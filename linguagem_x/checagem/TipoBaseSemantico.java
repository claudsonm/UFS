package checagem;

public enum TipoBaseSemantico implements TipoSemantico {
    Int("int"),
    Bool("bool"),
    Real("real");

    public final String tipo;

    private TipoBaseSemantico(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(TipoSemantico t) {
        return (t == this);
    }
}
