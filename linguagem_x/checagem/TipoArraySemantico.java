package checagem;

public class TipoArraySemantico implements TipoSemantico {
    public TipoBaseSemantico tipo;
    public int dimensao;
    
    public TipoArraySemantico(TipoBaseSemantico t, int d) {
        this.tipo = t;
        this.dimensao = d;
    }

    @Override
    public boolean equals(TipoSemantico t) {
        TipoArraySemantico t2 = (TipoArraySemantico) t;
        return (t2.tipo == this.tipo && t2.dimensao == this.dimensao);
    }
    
    @Override
    public String toString() {
        return tipo.tipo + "[" + dimensao + "]";
    }

}
