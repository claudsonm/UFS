package escalonamento;

import java.util.ArrayList;

public class FCFS {
    public static void processa(ArrayList<Processo> lista) {
        int numProcessos = lista.size();
        int tempoEspera = 0; //na fila
        int tempoCPU = 0;
        
        System.out.print("FCFS ");
        while ( ! lista.isEmpty() ) {
            Processo p = lista.remove(0);
            if ( ! lista.isEmpty() )
                tempoEspera += tempoEspera + p.tempoEstimado;
            tempoCPU += p.tempoEstimado;
            
            System.out.print(tempoCPU + "," + tempoEspera + " | ");
        }
        
        double mediaEspera = (1.0*tempoEspera)/numProcessos;
        
        System.out.println("\n\nTempo de espera: " + tempoEspera);
        System.out.println("Tempo de espera medio: " + mediaEspera);
    }
}
