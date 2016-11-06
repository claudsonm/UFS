package interpretador;

public class Endereco {

    public String local; // "pilha" ou "global"
    public int posicao;
    public String tipo; // Int, Real ou Bool

    public Endereco(String l, int p) {
        this.local = l;
        this.posicao = p;
    }

    public Endereco(String l, int p, String t) {
        this.local = l;
        this.posicao = p;
        this.tipo = t;
    }

    @Override
    public String toString() {
        return local + "[" + posicao + "]";
    }
}
