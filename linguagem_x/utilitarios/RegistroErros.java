package utilitarios;

import java.util.ArrayList;

/**
 * Classe que gerencia todos os erros identificados no programa.
 */
public class RegistroErros {
    
    /**
     * Tipo Abstrato de Dado que armazena o código e a mensagem de erro.
     */
    private static class Erro {
        int cod;
        String msg;
        
        public Erro(int codigo, String mensagem) {
            this.cod = codigo;
            this.msg = mensagem;
        }
    }
    
    /**
     * Lista com todos os erros identificados no programa.
     */
    private ArrayList<Erro> e = new ArrayList<Erro>();
    
    /**
     * Adiciona um erro na lista de erros.
     * 
     * @param codigo
     * @param mensagem
     */
    public void reportar(int codigo, String mensagem) {
        e.add(new Erro(codigo, mensagem));
    }
    
    /**
     * Retorna a quantidade de erros identificados no programa.
     * 
     * @return
     */
    public int quantidade() {
        return e.size();
    }
    
    /**
     * Exibe todas as mensagens de erro do programa.
     */
    public void mostrar() {
        if (! e.isEmpty()) {
            for (Erro err : e) {
                System.out.println("[" + err.cod + "] " + err.msg);
            }
        }
        else {
            System.out.println("Nenhum erro para mostrar.");
        }
    }
}
