package checagem;

public class VinculavelConsVar extends Vinculavel {
    public boolean isVar;
    public TipoSemantico tipo;
    
    public VinculavelConsVar(boolean b, TipoSemantico t) {
        this.isVar = b;
        this.tipo = t;
    }
}
