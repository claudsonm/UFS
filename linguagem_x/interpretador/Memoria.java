package interpretador;

import java.util.ArrayList;

public class Memoria {
    
    private int TAMANHO_MAXIMO = 300;
    
    public Value[] stackFrame = new Value[TAMANHO_MAXIMO];
    public Value[] globais = new Value[TAMANHO_MAXIMO/10];
    public int framePointer = -1;
    public ArrayList<Integer> frames = new ArrayList<>();
    
    public void putPilha(int posicao, Value valor) {
        stackFrame[framePointer+posicao] = valor;
    }
    
    public void putGlobal(int posicao, Value valor) {
        globais[posicao] = valor;
    }
    
    public void incrementarFramePointer() {
        framePointer++;
    }

}
