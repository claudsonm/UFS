package escalonamento;

import java.util.ArrayList;

public class FCFS {
    public static void processa(ArrayList<Processo> lista) {
        int numProcessos = lista.size();
        int tempoEspera = 0;
        int tempoCPU = 0;
        
        System.out.print("FCFS ");
        while ( ! lista.isEmpty() ) {
            Processo p = lista.remove(0);
            tempoEspera += tempoCPU - p.tempoChegada;
            tempoCPU += p.tempoEstimado;
        }
        
        double mediaRetorno = (1.0*(tempoCPU + tempoEspera))/numProcessos;
        //double mediaResposta = (1.0*tempoCPU)/numProcessos;
        double mediaEspera = (1.0*tempoEspera)/numProcessos;
        
        
        // Turnaround Time
        System.out.println("\nTempo de retorno medio: " + mediaRetorno);
        // Response Time
        System.out.println("Tempo de resposta medio: " + mediaEspera);
        // Wait Time
        System.out.println("Tempo de espera medio: " + mediaEspera);
    }
}
