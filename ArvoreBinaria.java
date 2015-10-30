import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    
    public static void main(String[] args) throws IOException, Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(r.readLine());
        String instrucoes = "";
        ArvoreBinaria minhaArvore = new ArvoreBinaria();
        
        for (int i = 0; i < N; i++) {
            instrucoes = r.readLine();
            String[] dados = instrucoes.split("\\s+");
            if (dados[0].equals("ins")) {
                for (int j = 1; j < dados.length; j++)
                    minhaArvore.inserir(Integer.parseInt(dados[j]));
            }
            else if (dados[0].equals("rem")) {
                for (int j = 1; j < dados.length; j++)
                    minhaArvore.remover(Integer.parseInt(dados[j]));
            }
            else System.out.println("Nao rola");
        }
        minhaArvore.buscaProfundidade();
    }
}

class ArvoreBinaria {
    
    private static class No {        
        No esq, dir;
        Integer valor;
        
        public No(Integer valor) {
            esq = dir = null;
            this.valor = valor;
        }
    }

    private No raiz;
    
    private No inserir(No node, int valor) {
        if (node == null) node = new No(valor);
        else {
            if (valor <= node.valor) node.esq = inserir(node.esq, valor);
            else node.dir = inserir(node.dir, valor);
        }
        return node;
    }
    
    private No remover(No node, int valor) throws Exception {
        if (this.raiz == null) throw new Exception("ERRO: Valor nao encontrado");
        else {
            if (valor < node.valor) node.esq = remover(node.esq, valor);
            else if (valor > node.valor) node.dir = remover(node.dir, valor);
            else if (node.esq != null && node.dir != null) {
                node.valor = valorMinimo(node.dir);
                node.dir = removeMinimo(node.dir);
            } else node = (node.esq != null) ? node.esq : node.dir;
            return node;
        }
    }
    
    private No removeMinimo(No node) {
        if (node == null) System.out.println("ERRO!");
        else if (node.esq != null) {
            node.esq = removeMinimo(node.esq);
            return node;
        } else return node.dir;
        return null;
    }
    
    private int valorMinimo(No no) {
        No atual = no;
        while (atual.esq != null) atual = atual.esq;
        return atual.valor;
    }
    
    public ArvoreBinaria() {
        this.raiz = null;
    }
        
    public void inserir(int valor) {
        this.raiz = inserir(this.raiz, valor);
    }

    public void remover(int valor) throws Exception {
        this.raiz = remover(this.raiz, valor);
    }    
    
    public int valorMinimo() {
        return valorMinimo(raiz);
    }
    
    public void buscaProfundidade() throws Exception {
        Fila q = new Fila();
        if (this.raiz == null) return;
        q.enfileira(this.raiz);
        String resultado = "";
        while (!q.vazia()) {
            No n = (No) q.desenfileira();
            resultado = resultado.concat(n.valor + ",");
            if (n.esq != null) q.enfileira(n.esq);
            if (n.dir != null) q.enfileira(n.dir);
        }
        int fimString = resultado.lastIndexOf(",");
        if (fimString != -1) resultado = resultado.substring(0, fimString);
        System.out.print(resultado);
    }
}

class Fila {

    private class Node {
        Object item;
        Node prox;
    }
    private Node frente;
    private Node tras;

    public Fila() {
        this.frente = new Node();
        this.tras = this.frente;
        this.frente.prox = null;
    }

    public void enfileira(Object x) {
        this.tras.prox = new Node();
        this.tras = this.tras.prox;
        this.tras.item = x;
        this.tras.prox = null;
    }

    public Object desenfileira() throws Exception {
        Object item = null;
        if (this.vazia()) return null;
        this.frente = this.frente.prox;
        item = this.frente.item;
        return item;
    }

    public boolean vazia() {
        return (this.frente == this.tras);
    }
}