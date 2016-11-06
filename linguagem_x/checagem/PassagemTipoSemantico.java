package checagem;

public class PassagemTipoSemantico {
    public TipoSemantico tipo;
    public boolean isCopia;

    /**
     * Construtor básico, apenas com o tipo semântico Passagem de parametro padrão é por cópia
     *
     * @param t
     */
    public PassagemTipoSemantico(TipoSemantico t) {
        this.tipo = t;
        this.isCopia = true;
    }

    /**
     * Construtor para explicitar tipo da passagem de parametro
     *
     * @param t
     * @param b
     */
    public PassagemTipoSemantico(TipoSemantico t, boolean b) {
        this.tipo = t;
        this.isCopia = b;
    }

    @Override
    public String toString() {
        return (isCopia) ? "(valor, " + tipo + ")" : "(ref, " + tipo + ")";
    }
}
