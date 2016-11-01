package interpretador;

public class Alocador {
    private static int endereco = 0;
    
    public static int proxEndereco() {
        return endereco;
    }
    
    public static void alocar() {
        endereco++;
    }
    
    public static void resetar() {
        endereco = 0;
    }

}
