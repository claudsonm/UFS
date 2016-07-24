import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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
    
    public void economiza() throws Exception {
        Object item = null;
        String[] digitosI, digitosJ;
        int numeroDigitos, economizados = 0;
        
        item = desenfileira();
        digitosI = (item.toString()).split("");
        item = desenfileira();
        
        while( item != null){
            digitosJ = (item.toString()).split("");
            item = desenfileira();
            
            numeroDigitos = digitosI.length;
            for (int i = 0; i < numeroDigitos; i++) {
                if (digitosI[i].equals(digitosJ[i])) economizados++;
                else break;
            }   
            digitosI = digitosJ;
        }
        System.out.println(economizados);
    }
}

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        try {
            Scanner dados = new Scanner(System.in);
            int N;
            String X;
            ArrayList<String> entrada = new ArrayList<> ();
            Fila numeros = new Fila();
            
            // Percorra o arquivo até o final das entradas
            mainLoop:
            while (dados.hasNext()) {    
                N = dados.nextInt();
                if (N >= 1 && N <= 100000) {
                    // Leia todos os N números daquele bloco e enfileire
                    for (int i = 0; i < N; i++) {
                        if (dados.hasNext()) {
                            X = dados.next();
                            if (X.length() < 1 || X.length() > 200)
                                break mainLoop;
                           
                            entrada.add(X);
                        }
                    }
                    
                    Collections.sort(entrada);
                    for (String linha : entrada) numeros.enfileira(linha);
                    
                    numeros.economiza();
                    for (int i = 0; i < N; i++) numeros.desenfileira();
                    entrada.clear();
                }
                else break;
            } // end-while
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }   
    }
}
