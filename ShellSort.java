package datastructure;

import java.util.Scanner;

/**
 * ORD_E
 * Algoritmo que implementa o Shell Sort
 * @author Claudson Martins
 */
public class ShellSort {
    public static void main(String[] args) {
        int valor, j, h;
        // Lendo a entrada e colocando no array de inteiros
        Scanner s       = new Scanner(System.in);
        String[] input  = s.nextLine().split(" ");
        int[] A = new int[input.length];
        for (int i = 0; i < input.length; i++)
            A[i] = Integer.parseInt(input[i]);
        
        // Realiza o Shell Sort
        h = A.length;
        while ( h > 1 ) {
            h = h/2;
            for (int i = h; i <= A.length - 1; i++) {
                valor = A[i];
                j = i;
                while (j >= h && A[j - h] > valor) {
                    A[j] = A[j - h];
                    j = j - h;
                }
                A[j] = valor;
            }
        }
        
        //Imprime o arranjo ordenado
        for (int i = 0; i < A.length; i++)
            System.out.print(A[i] + (i == (A.length - 1) ? "" : ", "));
    }
}
