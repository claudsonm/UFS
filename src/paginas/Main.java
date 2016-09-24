package paginas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner entrada = new Scanner(new File("entrada_p.txt"));
        int quadros = entrada.nextInt();
        
        ArrayList<Integer> lista = new ArrayList<>();
        
        while( entrada.hasNext() ) {
            lista.add(entrada.nextInt());
        }
        entrada.close();

        //processaFIFO(lista, quadros);
        processaOTM(lista, quadros);
    }
    
    public static void imprimaMemoria(List<Integer> m) {
        System.out.print("MEMORIA [");
        for (Integer i : m) {
            System.out.print(i + " ");
        }
        System.out.println("]");
    }
    
    public static void processaFIFO(List<Integer> lista, int q) {
        List<Integer> memoria = Arrays.asList(new Integer[q]);
        int faltas = 0;
        int j = 0;
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
        int faltas = 0;
        int j = 0;
        for (Integer i : lista) {
            if (! memoria.contains(i)) {
                faltas++;
                calculaProxUso(lista, memoria, j);
                memoria.set(j++ % q, i);
            }
            else {
                memoria.set(j % q, i);
            }
            //imprimaMemoria(memoria);
        }
        System.out.println("FIFO " + faltas);
    }
    
    public static int calculaProxUso(List<Integer> lista, List<Integer> memoria, int j) {
        int cont = 0;
        int[] diferencas = new int[memoria.size()];
        int inicio = j;
        
        // Para cada elemento da memoria
        for (int i = 0, t = memoria.size(); i < t; i++) {
            // Percorre a lista partindo de J
            for (int k = j; k < lista.size(); k++) {
                if (lista.get(j++) != memoria.get(i)) {
                    cont++;
                }
                else break;
            }
            diferencas[i] = cont;
            cont = 0;
            j = inicio;
        }
        
        for (int i = 0; i < diferencas.length; i++) {
            System.out.print(diferencas[i] + " ");
        }
        System.out.println("\n**************");
        return 0;
    }

}
