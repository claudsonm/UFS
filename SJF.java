package escalonamento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SJF {
    public static void processa(ArrayList<Processo> lista) {
        int numProcessos = lista.size();
        int tempoEspera = 0;
        int tempoConclusao = 0;
        int tempoRetorno = 0;
        int i = 0;
        double mediaRetorno, mediaEspera;
        ArrayList<Processo> l;
        
        Collections.sort(l, new Comparator<Processo>() {
            @Override
            public int compare(Processo p2, Processo p1) {
                return (p2.tempoEstimado - p1.tempoEstimado);
            }
        });
        
        for(Processo p : l) {
            System.out.println(p.pid);
        }
        
        for (int j = 0; j < numProcessos; i++) {
            l = processosNoInstante(lista, i);
            
            Processo p = ;
            tempoConclusao += p.tempoEstimado;
            tempoRetorno += tempoConclusao - p.tempoChegada;
            System.out.print(" - P" + p.pid + "\n" + tempoConclusao);
            if (i < processoA.length -1) tempoEspera += tempoConclusao;
        }
        
        
        /*
        Processo[] processoA = lista.toArray(new Processo[numProcessos]);
        Comparator<Processo> cTempo = new Comparator<Processo>() {
            public int compare(Processo t1, Processo t2) {
                return(t1.tempoEstimado - t2.tempoEstimado);
            }
        };
        Arrays.sort(processoA, cTempo);
        
        for (int j = 0; j < processoA.length; j++) {
            System.out.println(processoA[j].pid);
        }
        
        System.out.println(numProcessos + " processos criados");
        System.out.print("0");
        for (i=0; i<processoA.length; i++) {
            Processo p = processoA[i];
            tempoConclusao += p.tempoEstimado;
            tempoRetorno += tempoConclusao - p.tempoChegada;
            System.out.print(" - P" + p.pid + "\n" + tempoConclusao);
            if (i < processoA.length -1) tempoEspera += tempoConclusao;
        }
        */
        
        // Turnaround
        mediaRetorno = (1.0*tempoRetorno)/numProcessos;
        mediaEspera = (1.0*tempoEspera)/numProcessos;
        
        System.out.println("\n****\nMedia espera: " + mediaRetorno + " " + mediaEspera);
    }
    
    public static ArrayList<Processo> processosNoInstante(ArrayList<Processo> l, int t) {
        ArrayList<Processo> r = new ArrayList<Processo>();
        for(Processo p : l) {
            if(p.tempoChegada == t) r.add(p);
        }
        return r;
    }
}
