package paginas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner entrada = new Scanner(new File("entrada_p.txt"));
        ArrayList<Integer> lista = new ArrayList<>();
        int quadros = entrada.nextInt();
        
        while( entrada.hasNext() ) {
            lista.add(entrada.nextInt());
        }
        entrada.close();

        processaFIFO(lista, quadros);
        processaOTM(lista, quadros);
        processaLRU(lista, quadros);
    }
    
    public static void processaFIFO(List<Integer> lista, int q) {
        List<Integer> memoria = Arrays.asList(new Integer[q]);
        int faltas = 0, j = 0;
        
        for (Integer i : lista) {
            if (! memoria.contains(i)) {
                faltas++;
                memoria.set(j++ % q, i);
            }
            else {
                memoria.set(j % q, i);
            }
        }
        System.out.println("FIFO " + faltas);
    }
    
    public static void processaOTM(List<Integer> lista, int q) {
        List<Integer> memoria = Arrays.asList(new Integer[q]);
        int faltas = 0, j = 0;
        
        for (Integer i : lista) {
            if (! memoria.contains(i)) {
                faltas++;
                memoria.set(melhorTroca(lista, memoria, j), i);
            }
            j++;
        }
        System.out.println("OTM " + faltas);
    }
    
    public static void processaLRU(List<Integer> lista, int q) {
        List<Integer> memoria = Arrays.asList(new Integer[q]);
        int faltas = 0, j = 0;
        
        for (Integer i : lista) {
            if (! memoria.contains(i)) {
                faltas++;
                memoria.set(trocaRecente(lista, memoria, j), i);
            }
            j++;
        }
        System.out.println("LRU " + faltas);
    }
    
    public static int melhorTroca(List<Integer> lista, List<Integer> memoria, int j) {
        int cont = 0;
        int[] diferencas = new int[memoria.size()];
        
        // Para cada elemento da memoria
        for (int i = 0, t = memoria.size(); i < t; i++) {
            if (memoria.get(i) == null) return i;
            // Percorre a lista partindo de J
            for (int k = j, t2 = lista.size(); k < t2; k++) {
                cont++;
                if (lista.get(k) == memoria.get(i)) {
                    break;
                }
            }
            diferencas[i] = cont;
            cont = 0;
        }
        
        return posicaoMaiorElemento(diferencas);
    }
    
    public static int trocaRecente(List<Integer> lista, List<Integer> memoria, int j) {
        int cont = 0;
        int[] diferencas = new int[memoria.size()];
        
        // Para cada elemento da memoria
        for (int i = 0, t = memoria.size(); i < t; i++) {
            if (memoria.get(i) == null) return i;
            // Percorre a lista partindo de J
            for (int k = j; k > 0; k--) {
                cont++;
                if (lista.get(k) == memoria.get(i)) {
                    break;
                }
            }
            diferencas[i] = cont;
            cont = 0;
        }
        
        return posicaoMaiorElemento(diferencas);
    }
    
    public static int posicaoMaiorElemento(int[] v){
        int index = -1, maior = -1;
        for (int i = 0; i < v.length; i++) {
            if (v[i] > maior) {
                maior = v[i];
                index = i;
            }
        }
        return index;
    }
    
    public static void imprimaMemoria(List<Integer> m) {
        System.out.print("MEMORIA [");
        for (Integer i : m) {
            System.out.print(i + " ");
        }
        System.out.println("]");
    }

}
