package ambiente;

import checagem.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Gerencia a tabela de símbolos de variáveis e constantes do Checker. Atua como uma fábrica dos
 * flyweights para gerar objetos da classe concreta VinculavelConsVar.
 *
 * @author Claudson Martins
 * @author Edgar Lima
 * @author Guilherme Boroni
 */
public class AmbienteConsVar {

    /**
     * Tabela hash que mapeia o nome de um identificador para uma lista de VinculavelConsVar, que
     * contém as informações dos tipos dos identificadores de mesmo nome.
     */
    public static final HashMap<String, ArrayList<VinculavelConsVar>> tabela =
            new HashMap<String, ArrayList<VinculavelConsVar>>();

    /** Nível atual de escopo da tabela de símbolos. */
    public static int nivel = 0;

    /** Pilha que contém a lista com o nome de todos os identificadores de cada escopo. */
    public static Stack<ArrayList<String>> simbolosEscopo = new Stack<ArrayList<String>>();

    /**
     * Adiciona o nome do identificador na pilha e as informações sobre ele na tabela de símbolos.
     *
     * @param id nome do identificador
     * @param b true representa uma variável, false representa uma constante
     * @param t informações sobre o tipo
     * @return true se adicionou com sucesso, false caso tenha sido possível
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
        } else {
            listaPilha = simbolosEscopo.peek();
            if (listaPilha.contains(id)) {
                System.out.println("Ja tem [" + b + " " + t + " " + id + "] !");
            } else {
                listaPilha.add(id);
                VinculavelConsVar x = new VinculavelConsVar(b, t, nivel);
                this.adicionaTabela(id, x);
                r = true;
            }
        }
        return r;
    }

    /**
     * Adiciona um identificador na lista do hash da tabela de símbolos.
     *
     * @param id nome do identificador
     * @param x informações sobre o identificador
     */
    private void adicionaTabela(String id, VinculavelConsVar x) {
        ArrayList<VinculavelConsVar> listaItens = tabela.get(id);
        // Se a lista da tabela ainda não existe, cria
        if (listaItens == null) {
            listaItens = new ArrayList<VinculavelConsVar>();
            listaItens.add(x);
            tabela.put(id, listaItens);
        } else {
            listaItens.add(x);
        }
        System.out.println(
                "Criando ConsVar no ambiente no [Escopo "
                        + nivel
                        + "]: "
                        + (x.isVar ? "var" : "cons")
                        + " "
                        + x.tipo
                        + " "
                        + id);
    }

    /** Incrementa um nível de escopo */
    public void comecaEscopo() {
        ArrayList<String> listaPilha = new ArrayList<String>();
        nivel++;
        simbolosEscopo.push(listaPilha);
    }

    /**
     * Verifica se o identificador existe no escopo atual.
     *
     * @param id nome do identificador
     * @return true caso já exista, false caso não exista
     */
    public boolean contem(String id) {
        ArrayList<String> listaPilha = simbolosEscopo.peek();
        return listaPilha.contains(id);
    }

    /**
     * Remove o nome do identificador da pilha e as informações de tipo da tabela de símbolos.
     *
     * @param id nome do identificador
     */
    public void deleta(String id) {
        ArrayList<String> listaPilha = simbolosEscopo.peek();
        listaPilha.remove(id);
        this.deletaTabela(id);
    }

    /**
     * Remove um identificador da tabela de símbolos.
     *
     * @param id nome do identificador
     * @return informações sobre o identificador removido
     */
    private VinculavelConsVar deletaTabela(String id) {
        ArrayList<VinculavelConsVar> l = tabela.get(id);
        return l.remove(l.size() - 1);
    }

    /** Decrementa um nível de escopo */
    public void terminaEscopo() {
        ArrayList<String> listaPilha = simbolosEscopo.pop();
        for (String st : listaPilha) {
            this.deletaTabela(st);
        }
        nivel--;
    }

    /**
     * Retorna a instância mais recente de um identificador na lista da tabela de símbolos.
     *
     * @param id nome do identificador
     * @return informações sobre o identificador
     */
    public VinculavelConsVar get(String id) {
        ArrayList<VinculavelConsVar> l = tabela.get(id);
        return (l == null) ? null : l.get(l.size() - 1);
    }
}
