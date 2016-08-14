package escalonamento;

import java.util.ArrayList;

public class FCFS {
    public static void processa(ArrayList<Processo> lista) {
        int numProcessos = lista.size();
        int tempoEspera = 0, tempoConclusao = 0, tempoRetorno = 0, i = 0;
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
        }
        // Turnaround    
        mediaRetorno = (1.0*tempoRetorno)/numProcessos;
        mediaEspera = (1.0*tempoEspera)/numProcessos;
        
        System.out.println(mediaRetorno + " " + mediaEspera + " " + mediaEspera);
    }
}
