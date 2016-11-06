package interpretador;

import java.util.HashMap;

public class Memoria {

    private int TAMANHO_MAXIMO = 300;
    private int indice = 0;

    public Value[] stackFrame = new Value[TAMANHO_MAXIMO];
    public Value[] globais = new Value[TAMANHO_MAXIMO / 10];
    public int framePointer = 0;
    public int[] tamanhoFrames = new int[TAMANHO_MAXIMO / 10];
    public HashMap<String, Object[]> funcoesProc = new HashMap<>();

    public void putPilha(int posicao, Value valor) {
        stackFrame[framePointer + posicao] = valor;
        tamanhoFrames[indice] += 1;
    }

    public void putGlobal(int posicao, Value valor) {
        globais[posicao] = valor;
    }

    public void novoFrame() {
        framePointer += tamanhoFrames[indice];
        indice++;
    }

    public void removerFrame() {
        for (int i = framePointer, f = framePointer + tamanhoFrames[indice]; i < f; i++) {
            stackFrame[i] = null;
        }
        framePointer -= tamanhoFrames[indice - 1];
        tamanhoFrames[indice] = 0;
        indice--;
    }

    public void putDeclaracao(String id, Object[] conteudo) {
        funcoesProc.put(id, conteudo);
    }

    public Object[] getDeclaracao(String id) {
        return funcoesProc.get(id);
    }

    public Value getGlobal(int posicao) {
        return globais[posicao];
    }

    public Value getPilha(int posicao) {
        return stackFrame[framePointer + posicao];
    }
}
