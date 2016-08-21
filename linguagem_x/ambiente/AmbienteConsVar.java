package ambiente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import checagem.*;

/**
 * Gerencia a tabela de símbolos de variáveis e constantes.
 * Atua como uma fábrica dos Flyweights para gerar objetos da classe concreta
 * VinculavelConsVar.
 */
public class AmbienteConsVar {
    /**
     * Hash Identificador -> VinculavelConsVar, que contém os dados sobre tipo. 
     */
    public static final HashMap<String, ArrayList<VinculavelConsVar>> tabela =
            new HashMap<String, ArrayList<VinculavelConsVar>>();
    
    /**
     * Nível atual de escopo da tabela de símbolos
     */
    public static int nivel = 0;
    
    /**
     * Pilha que contém a lista com todos os identificadores de cada escopo.
     */
    public static Stack<ArrayList<String>> simbolosEscopo =
            new Stack<ArrayList<String>>();
    
    /**
     * Adiciona um identificador na lista do hash da tabela de símbolos.
     * 
     * @param id
     * @param x
     * @return
     */
    private void adicionaTabela(String id, VinculavelConsVar x) {
        ArrayList<VinculavelConsVar> listaItens = tabela.get(id);
        
        // Se a lista da tabela ainda não existe, cria
        if (listaItens == null) {
            listaItens = new ArrayList<VinculavelConsVar>();
            listaItens.add(x);
            tabela.put(id, listaItens);
        }
        else listaItens.add(x);
        
        System.out.println(
            "Criando ConsVar no ambiente no [Escopo " + nivel + "]: " +
            x.isVar + " " + x.tipo + " " + id 
        );
    }
    
    /**
     * Remove um identificador da tabela de símbolos.
     * 
     * @param id
     * @return
     */
    private VinculavelConsVar deletaTabela(String id) {
        ArrayList<VinculavelConsVar> l = tabela.get(id);
        return l.remove(l.size() - 1);
    }
    
    /**
     * Adiciona um identificador na pilha e na tabela de símbolos.
     * 
     * @param id
     * @param b
     * @param t
     * @return
     */
    public boolean add(String id, boolean b, TipoSemantico t) {
        boolean r = false;
        ArrayList<String> listaPilha;
        
        if (simbolosEscopo.isEmpty()) {
            listaPilha = new ArrayList<String>();
            listaPilha.add(id);
            simbolosEscopo.push(listaPilha);
            VinculavelConsVar x = new VinculavelConsVar(b, t, nivel);
            this.adicionaTabela(id, x);
            r = true;
        }
        else {
            listaPilha = simbolosEscopo.peek();
            if (listaPilha.contains(id)) System.out.println("Ja tem [" + b + " " + t + " " + id + "] !");
            else {
                listaPilha.add(id);
                VinculavelConsVar x = new VinculavelConsVar(b, t, nivel);
                this.adicionaTabela(id, x);
                r = true;
            }
        }
        
        return r;
    }
    
    /**
     * Remove um identificador da pilha e da tabela de símbolos.
     * 
     * @param id
     */
    public void deleta(String id) {
        ArrayList<String> listaPilha = simbolosEscopo.peek();
        listaPilha.remove(id);
        this.deletaTabela(id);
    }
    
    /**
     * Retorna a instância mais recente de um elemento na tabela de símbolos.
     * 
     * @param id
     * @return
     */
    public VinculavelConsVar get(String id) {
        ArrayList<VinculavelConsVar> l = tabela.get(id);
        return l.get(l.size() - 1);
    }
    
    /**
     * Retorna true se o identificador existe no escopo atual.
     * 
     * @param id
     * @return
     */
    public boolean contem(String id) {
        ArrayList<String> listaPilha = simbolosEscopo.peek();
        return listaPilha.contains(id);
    }
    
    /**
     * Incrementa um nível de escopo
     */
    public void comecaEscopo() {
        ArrayList<String> listaPilha = new ArrayList<String>();
        nivel++;
        simbolosEscopo.push(listaPilha);
    }
    
    /**
     * Decrementa um nível de escopo
     */
    public void terminaEscopo() {
        ArrayList<String> listaPilha = simbolosEscopo.pop();
        for (String st : listaPilha)
            this.deletaTabela(st);
        nivel--;
    }
}
