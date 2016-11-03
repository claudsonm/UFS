package interpretador;

public class Alocador {
    private static int endereco = 0;
    private static int enderecoGlobal = 0;
    
    public static int proxEndereco(String tipo) {
        if (tipo.equals("pilha")) {
            return endereco;
        }
        else {
            return enderecoGlobal;
        }
    }
    
    public static void alocar(String tipo) {
        if (tipo.equals("pilha")) {
            endereco++;
        }
        else {
            enderecoGlobal++;
        }
    }
    
    public static void resetar() {
        endereco = 0;
    }
    
}
