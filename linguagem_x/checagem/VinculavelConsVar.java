package checagem;

public class VinculavelConsVar extends Vinculavel {
    public boolean isVar;
    public TipoSemantico tipo;
    public int nivelEscopo = 0;

    public VinculavelConsVar(boolean b, TipoSemantico t) {
        this.isVar = b;
        this.tipo = t;
    }

    public VinculavelConsVar(boolean b, TipoSemantico t, int n) {
        this.isVar = b;
        this.tipo = t;
        this.nivelEscopo = n;
    }

    @Override
    public boolean equals(Object o) {
        boolean r = false;
        if (o instanceof VinculavelConsVar) {
            VinculavelConsVar x = (VinculavelConsVar) o;
            r = this.nivelEscopo == x.nivelEscopo;
        }
        return r;
    }
}
