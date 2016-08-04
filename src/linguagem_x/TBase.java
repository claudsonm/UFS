package linguagem_x;

public enum TBase {
    Int("int"), Bool("bool"), Real("real");
    
    public final String nome;
    
    private TBase(String nome) {
        this.nome = nome;
    }
}
