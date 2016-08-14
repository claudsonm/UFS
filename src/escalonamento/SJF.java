package escalonamento;

import java.util.ArrayList;
import java.util.Comparator;

public class SJF {
    public static void processa(ArrayList<Processo> lista) {
        int numProcessos = lista.size();
        int tempoEspera = 0, tempoConclusao = 0, tempoRetorno = 0;
        double mediaRetorno, mediaEspera;
        ArrayList<Processo> l;
        ArrayList<Processo> finalizados = new ArrayList<>();
        
        Comparator<Processo> c = new Comparator<Processo>() {
            public int compare(Processo p2, Processo p1) {
                return (p2.tempoEstimado - p1.tempoEstimado);
            }
        };
        
        System.out.print("SJF ");
        for (int i = 0; i < numProcessos; i++) {
            l = processosProntos(lista, finalizados, tempoConclusao);
            l.sort(c);
            Processo p = l.get(0);
            finalizados.add(p);
            if (i == 0) {
                tempoEspera = 0;
                tempoConclusao = p.tempoChegada + p.tempoEstimado;
            }
            else {
                tempoEspera += tempoConclusao - p.tempoChegada;
                tempoConclusao += p.tempoEstimado;
            }
            tempoRetorno += tempoConclusao - p.tempoChegada;
        }
        // Turnaround
        mediaRetorno = (1.0*tempoRetorno)/numProcessos;
        mediaEspera = (1.0*tempoEspera)/numProcessos;
        
        System.out.println(mediaRetorno + " " + mediaEspera + " " + mediaEspera);
    }
    
    /**
     * Retorna um ArrayList com os processos prontos ainda não executados
     * para um determinado instante de tempo.
     * 
     * @param e Lista com todos os processos da entrada
     * @param f Lista com todos os processos finalizados
     * @param t Tempo da execução
     * @return
     */
    public static ArrayList<Processo> processosProntos(
        ArrayList<Processo> e, ArrayList<Processo> f, int t) {
        ArrayList<Processo> r = new ArrayList<Processo>();
        for (Processo p : e) {
            if (! f.contains(p) && p.tempoChegada <= t)
                r.add(p);
        }
        if (r.size() == 0) r = processosProntos(e, f, ++t);
        return r;
    }
}
