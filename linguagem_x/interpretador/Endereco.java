package interpretador;

public class Endereco {
    
    public String tipo; // "pilha" ou "global"
    public int posicao;
    
    public Endereco(String t, int p) {
        this.tipo = t;
        this.posicao = p;
    }

}
