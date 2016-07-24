/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructure;

import java.util.Scanner;

/**
 *
 * @author Claudson
 */
public class LEXSIM {
    
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        Pilha operadores = new Pilha();
        char[] ch;
        char topo;
        
        ch = s.next().toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (Character.isLetterOrDigit(ch[i])) {
                System.out.print(ch[i]);
            }
            else {
                // Se a pilha está vazia, simplesmente empilha
                if (operadores.vazia()) {
                    operadores.empilha(ch[i]);
                }
                else {
                    topo = (char) operadores.desempilha();
                    /* Se o que está tentando empilhar for de MAIOR precedencia,
                     * empilha normalmente.
                     * Retorna o topo e depois adiciona o novo
                     */
                    if (checkPrecedencia(ch[i]) > checkPrecedencia(topo)) {
                        operadores.empilha(topo);
                        operadores.empilha(ch[i]);
                    }
                    /* Se o que está tentando empilhar for de MENOR precedencia
                     * ou IGUAL, desempilha até achar um que seja de maior.
                     * E depois empilha o que estava querendo
                     */
                    else {
                        //System.out.print(topo);
                        do {
                            if ((int) topo != 40 && (int) topo != 41)
                                System.out.print(topo);
                            
                            if (!operadores.vazia())
                                topo = (char) operadores.desempilha();
                        }
                        while (topoMenorIgual(ch[i], topo) && !operadores.vazia());
                        
                        if ((int) ch[i] != 40 && (int) ch[i] != 41) {
                            operadores.empilha(ch[i]);
                        }
                    }
                    
                }
            }
        }
        
        while (!operadores.vazia()) {
            topo = (char) operadores.desempilha();
            if ((int) topo != 40 && (int) topo != 41) System.out.print(topo);
        }
    }
    
    private static void runDemo() throws Exception {
        Pilha pilha = new Pilha();
        Scanner s = new Scanner(System.in);
        Integer n = null;
        int i, DADOS_NATUREZA;

        /*Empilhamento de dados fornecidos */
        System.out.print("Quantos dados deseja empilhar? ");
        DADOS_NATUREZA = s.nextInt();
        System.out.print("\nEntre com os " + DADOS_NATUREZA + " dados:");
        for (i = 0; i < DADOS_NATUREZA; i++) {
            pilha.empilha(Integer.valueOf(s.nextInt()));
        }
        System.out.println("\nTamanho da pilha: " + pilha.tamanho());

        /*Desempilhamento */
        while (!pilha.vazia()) {
            n = (Integer) pilha.desempilha();
            System.out.println("Desempilhou: " + n.toString());
        }
        System.out.println("\nTamanho da pilha: " + pilha.tamanho());
    }

    private static int checkPrecedencia(char ch) {
        int ascii = (int) ch;
        int prioridade;
        
        switch (ascii) {
            default:
            case 40:    // (
            case 41:    // )
                prioridade = 0;
                break;
                
            case 124:   // | (OR)
                prioridade = 1;
                break;
                
            case 46:    // . (AND)
                prioridade = 2;
                break;
                
            case 62:    // >
            case 60:    // <
            case 61:    // =
            case 35:    // #
                prioridade = 3;
                break;
                
            case 43:    // +
            case 45:    // -
                prioridade = 4;
                break;
                
            case 42:    // *
            case 47:    // /
                prioridade = 5;
                break;
                
            case 94:    // ^
                prioridade = 6;
                break;
        }
        //System.out.println("Recebi " + ch + " (ASCII: " + ascii + " | PRIO: "+ prioridade + ")");
        return prioridade;
    }

    private static boolean topoMenorIgual(char ch, char topo) {
        return (checkPrecedencia(topo) <= checkPrecedencia(ch));
    }
}

class Pilha {

    private static class Celula {
        Object item;
        Celula prox;
    }
    private Celula topo;
    private int tam;

    public Pilha() {
        this.topo = null;
        this.tam = 0;
    }

    public void empilha(Object x) {
        Celula aux = this.topo;
        this.topo = new Celula();
        this.topo.item = x;
        this.topo.prox = aux;
        this.tam++;
    }

    public Object desempilha() throws Exception {
        if (this.vazia()) {
            throw new Exception("Erro: A pilha esta vazia");
        }
        Object item = this.topo.item;
        this.topo = this.topo.prox;
        this.tam--;
        return item;
    }

    public boolean vazia() {
        return (this.topo == null);
    }

    public int tamanho() {
        return this.tam;
    }
}
