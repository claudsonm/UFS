/**
 * Labirinto com Union-Find
 *
 * @author  Allan Silva Santos              (201410060220)
 * @author  Claudson Bispo Martins Santos   (201410042132)
 * @author  Edgar Vieira Lima Neto          (201410042150)
 * @since   14/11/2015
 */
package labirinto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MeuLabirinto {

    private final int N;
    private boolean[][] visitado;
    private boolean completo = false;
    static int inicio1;
    static int inicio2;
    static int fim1;
    static int fim2;
    
    private int[] grid;
    private static final int UP = 1;
    private static final int RIGHT = 2;
    private static final int DOWN = 4;
    private static final int LEFT = 8;
    public static class Parede {
        private final int cel;
        private final int direcao;

        public Parede(int cel, int direcao) {
            this.cel = cel;
            this.direcao = direcao;
        }
    }
    
    public MeuLabirinto(int N) {
        this.N = N;
        this.grid = new int[N * N];
        StdDraw.setXscale(0, N + 2);
        StdDraw.setYscale(0, N + 2);
        inicializar();
        criar();
    }

    private void inicializar() {
        // Cria o vetor que indica se a celula foi ou nao visitada
        visitado = new boolean[N][N];
        
        // Inicia com todas as paredes do labirinto
        for (int i = 0; i < this.N * this.N; i++)
            this.grid[i] = UP | RIGHT | DOWN | LEFT;
    }

    // gera o labirinto
    private void criar() {
        
        List<Parede> paredes = new ArrayList<>();
        for (int row = 0; row < this.N; row++) {
            for (int col = 0; col < this.N; col++) {
                if (row > 0) paredes.add(new Parede(row * this.N + col, UP));
                if (col > 0) paredes.add(new Parede(row * this.N + col, LEFT));
            }
        }
        
        // Aplica o union find
        UnionFind uf = new UnionFind(this.N * this.N);
        Random rand = new Random();
        // Enquanto houver celulas nao unidas
        while (uf.size() > 1) {
            int pos = rand.nextInt(paredes.size());
            
            int cel1 = paredes.get(pos).cel;
            int cel2 = (paredes.get(pos).direcao == UP)
                    ? cel1 - this.N
                    : cel1 - 1;

            if (uf.find(cel1) != uf.find(cel2)) {
                // pode remover a parede
                if (paredes.get(pos).direcao == UP) {
                    this.grid[cel1] ^= UP;
                    this.grid[cel2] ^= DOWN;
                } else {
                    this.grid[cel1] ^= LEFT;
                    this.grid[cel2] ^= RIGHT;
                }
                uf.union(cel1, cel2);
            }
            paredes.remove(pos);
        }
    }

    // resolve o labirinto utilizando-se de recursao
    private void resolver(int x, int y) {
        if (x < 0 || y < 0 || x == N || y == N) return;
        if (completo || visitado[x][y]) return;
        
        visitado[x][y] = true;

        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
        StdDraw.show(30);

        // passando o parametro onde o labirinto ira terminar.
        if (x == fim2 && y == fim1) completo = true;
        
        // se aproveita da pilha de execucao do sistema, para realizar busca em
        // profundidade.
        int cell = grid[x + y * this.N];       
        
        if ((cell & UP) == 0) resolver(x, y - 1);
        if ((cell & RIGHT) == 0) resolver(x + 1, y);
        if ((cell & LEFT) == 0) resolver(x - 1, y);
        if ((cell & DOWN) == 0) resolver(x, y + 1);
        
        if (completo) return;

        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
        StdDraw.show(30);
    }

    /**
     * Resolve o labirinto, a partir do parametro da posicao inicial informado
     */
    public void resolver() {
        for (int x = 0; x < N; x++)
            for (int y = 0; y < N; y++)
                visitado[x][y] = false;
        
        completo = false;
        resolver(inicio2, inicio1);
    }

    // desenhando o labirinto
    public void desenhe() {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(fim2 + 0.5, fim1 + 0.5, 0.375);
        StdDraw.filledCircle(inicio2 + 0.5, inicio1 + 0.5, 0.375);

        StdDraw.setPenColor(StdDraw.BLACK);
        
        for (int row = 0; row < this.N; row++) {
            for (int col = 0; col < this.N; col++) {
                int cell = grid[col + row * this.N];
                
                if ((cell & LEFT) != 0)
                    StdDraw.line(col, row, col, row + 1);
                if ((cell & RIGHT) != 0)
                    StdDraw.line(col + 1, row, col + 1, row + 1);
                if ((cell & UP) != 0)
                    StdDraw.line(col, row, col + 1, row);
                if ((cell & DOWN) != 0)
                    StdDraw.line(col, row + 1, col + 1, row + 1);
            }
        }
        StdDraw.show(1000);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite a dimensao de sua matriz");
        int N = sc.nextInt();

        System.out.println("Digite a posicao onde sua matriz ira comecar");
        do {
            inicio1 = (sc.nextInt()) - 1;
            inicio2 = (sc.nextInt()) - 1;
            if (inicio1 >= N || inicio2 >= N || inicio1 < 0 || inicio2 < 0)
                System.out.println("Digite uma posicao contida na Matriz");
        } while (inicio1 >= N || inicio2 >= N || inicio1 < 0 || inicio2 < 0);

        System.out.println("Digite a posicao onde sua matriz ira terminar");
        do {
            fim1 = (sc.nextInt()) - 1;
            fim2 = (sc.nextInt()) - 1;
            if (fim1 >= N || fim2 >= N || fim1 < 0 || fim2 < 0)
                System.out.println("Digite uma posicao contida na Matriz");
        } while (fim1 >= N || fim2 >= N || fim1 < 0 || fim2 < 0);

        MeuLabirinto Labirinto = new MeuLabirinto(N);
        StdDraw.show(0);
        Labirinto.desenhe();
        Labirinto.resolver();
    }

}

/**
 * Classe que implementa o Union-Find (adaptada para o problema)
 */
class UnionFind {

    private int[] pai;
    private int[] rank;
    private int size;

    /**
     * Cria uma nova instancia, com cada item em seu proprio conjunto
     *
     * @param tam O numero inicial de elementos
     */
    public UnionFind(int tam) {
        this.size = tam;
        this.pai = new int[tam];
        this.rank = new int[tam];
        for (int i = 0; i < tam; i++) {
            this.pai[i] = i;
            this.rank[i] = 1;
        }
    }

    /**
     * Procura o conjunto em que o item e encontrado
     *
     * @param item O item a ser buscado
     * @return O item da raiz do conjunto encontrado
     */
    public int find(int item) {
        int raiz = item;
        // procura a raiz
        while (pai[raiz] != raiz)
            raiz = pai[raiz];
        
        // encurta os caminhos
        int atual = item;
        while (pai[atual] != raiz)
            pai[atual] = raiz;
        
        return raiz;
    }

    /**
     * Une dois conjuntos
     *
     * @return A raiz do conjunto unido
     */
    public int union(int item1, int item2) {
        int conj1 = find(item1);
        int conj2 = find(item2);
        --size;
        
        if (rank[conj1] > rank[conj2]) {
            pai[conj2] = conj1;
            rank[conj1] += rank[conj2];
            return conj1;
        } else {
            pai[conj1] = conj2;
            rank[conj2] += rank[conj1];
            return conj2;
        }
    }
    
    public int size() {
        return size;
    }
}