package escalonamento;

import java.util.ArrayList;

public class FCFS {
    public static void processa(ArrayList<Processo> lista) {
        int numProcessos = lista.size();
        int tempoEspera = 0;
        int tempoConclusao = 0;
        int tempoRetorno = 0;
        int i = 0;
        double mediaRetorno, mediaEspera;
        
        System.out.print("FCFS ");
        for (Processo p : lista) {
            if (i == 0) {
                tempoEspera = 0;
                tempoConclusao = p.tempoChegada + p.tempoEstimado;
            }
            else {
                tempoEspera += tempoConclusao - p.tempoChegada;
                tempoConclusao += p.tempoEstimado;
            }
            tempoRetorno += tempoConclusao - p.tempoChegada;
            i++;
            
            //System.out.println(tempoEspera + " | " + tempoConclusao + " | " + tempoRetorno);
        }
            
        
        mediaRetorno = (1.0*tempoRetorno)/numProcessos;
        //mediaResposta = (1.0*tempoCPU)/numProcessos;
        mediaEspera = (1.0*tempoEspera)/numProcessos;
        
        
        // Turnaround Time
        System.out.println("\nTempo de retorno medio: " + mediaRetorno);
        // Response Time
        System.out.println("Tempo de resposta medio: " + mediaEspera);
        // Wait Time
        System.out.println("Tempo de espera medio: " + mediaEspera);
    }
}
