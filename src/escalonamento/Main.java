package escalonamento;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner entrada = new Scanner(new File("entrada.txt"));
        int chegada, duracao, id = 1;
        ArrayList<Processo> lista = new ArrayList<Processo>();
        
        while( entrada.hasNextLine() ) {
            chegada = entrada.nextInt();
            duracao = entrada.nextInt();
            lista.add(new Processo(id, chegada, duracao));
            id++;
        }
        entrada.close();
        
        FCFS.processa(lista);
    }

}