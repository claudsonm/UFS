package escalonamento;

public class Processo {
    public int pid;
    public int tempoChegada;
    public int tempoEstimado;
    public int tempoEspera;
    public int tempoExecucao;

    public Processo(int id, int t0, int t1) {
        pid = id;
        tempoChegada = t0;
        tempoEstimado = t1;
        tempoEspera = tempoExecucao = 0;
    }

}