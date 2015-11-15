/*
 * disjoint-sets
 * Modifique o código disponibilizado na página da disciplina para permitir que
 * o Labirinto seja gerado a partir de uma TAD Union-Find. Operações ânions
 * sucessivas devem possibilitar a geração, enquanto operações find podem ser
 * úteis para identificar se um dado Labirinto gerado, considerando pontos de
 * entrada e saída, possui solução.
 */
package labirinto;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author Claudson
 */
public class MeuLabirinto {

    private final int N; // Dimensao do labirinto
    private boolean[][] norte; // para verificação se há uma celula ao norte
    private boolean[][] leste;
    private boolean[][] sul;
    private boolean[][] oeste;
    private boolean[][] visitado;
    private boolean completo = false;
    static int inicio1;
    static int inicio2;
    static int fim1;
    static int fim2;
    // *************************************************************************
    // Minhas variaveis
    // *************************************************************************
    private int[] grid;
    private static final int UP = 1;
    private static final int RIGHT = 2;
    private static final int DOWN = 4;
    private static final int LEFT = 8;
    public static class Wall {
        private final int cell;
        private final int direction;

        public Wall(int cell, int direction) {
            this.cell = cell;
            this.direction = direction;
        }
    }
    private boolean[] visit;
    // *************************************************************************
    
    public MeuLabirinto(int N) {
        this.N = N;
        this.grid = new int[N * N];
        this.visit = new boolean[N * N];
        StdDraw.setXscale(0, N + 2);
        StdDraw.setYscale(0, N + 2);
        inicializar();
        //criar();
    }

    private void inicializar() {
        // inicializa as celulas das bordas como ja visitadas
        visitado = new boolean[N][N];
        /*visitado = new boolean[N + 2][N + 2];
        for (int x = 0; x < N + 2; x++) {
            visitado[x][0] = visitado[x][N + 1] = true;
        }
        for (int y = 0; y < N + 2; y++) {
            visitado[0][y] = visitado[N + 1][y] = true;
        }*/
        
        // *********************************************************************
        // Inicia com todas as paredes do labirinto
        // *********************************************************************
        for (int i = 0; i < this.N * this.N; i++) {
            this.grid[i] = UP | RIGHT | DOWN | LEFT;
            // define como nao visitados os pontos
            this.visit[i] = false;
        }
        
        // Cria todas as paredes
        List<Wall> walls = new ArrayList<>();
        for (int row = 0; row < this.N; row++) {
            for (int col = 0; col < this.N; col++) {
                if (row > 0) walls.add(new Wall(row * this.N + col, UP));
                if (col > 0) walls.add(new Wall(row * this.N + col, LEFT));
            }
        }
        // *********************************************************************
        
        // *********************************************************************
        // Remove todas as paredes possiveis com Union-Find
        // *********************************************************************
        UnionFind2 diset = new UnionFind2(this.N * this.N);
        
        Random rand = new Random();
        while (diset.size() > 1) {
            int wallIndex = rand.nextInt(walls.size());
            int cell1 = walls.get(wallIndex).cell;
            int cell2 = (walls.get(wallIndex).direction == UP)
                    ? cell1 - this.N
                    : cell1 - 1;

            if (diset.find(cell1) != diset.find(cell2)) {
                // we can remove the wall
                if (walls.get(wallIndex).direction == UP) {
                    this.grid[cell1] ^= UP;
                    this.grid[cell2] ^= DOWN;
                } else {
                    this.grid[cell1] ^= LEFT;
                    this.grid[cell2] ^= RIGHT;
                }
                diset.union(cell1, cell2);
            }
            walls.remove(wallIndex);
        }
        // *********************************************************************

        // inicializa todas as paredes como true
        /*norte = new boolean[N + 2][N + 2];
        leste = new boolean[N + 2][N + 2];
        sul = new boolean[N + 2][N + 2];
        oeste = new boolean[N + 2][N + 2];
        for (int x = 0; x < N + 2; x++) {
            for (int y = 0; y < N + 2; y++) {
                norte[x][y] = leste[x][y] = sul[x][y] = oeste[x][y] = true;
            }
        }*/
    }

    // gera o labirinto
    private void criar(int x, int y) {
        visitado[x][y] = true;
        double r;
        // enquanto houver um vizinho que nao foi visitado
        while (!visitado[x][y + 1] || !visitado[x + 1][y]
                || !visitado[x][y - 1] || !visitado[x - 1][y]) {

            // pega o vizinho aleatoriamente
            while (true) {
                r = Math.random();
                if (r < 0.25 && !visitado[x][y + 1]) {  // LESTE ->
                    norte[x][y] = sul[x][y + 1] = false;
                    criar(x, y + 1);
                    break;
                } else if (r >= 0.25 && r < 0.50 && !visitado[x + 1][y]) { // SUL
                    leste[x][y] = oeste[x + 1][y] = false;
                    criar(x + 1, y);
                    break;
                } else if (r >= 0.5 && r < 0.75 && !visitado[x][y - 1]) { // OESTE <-
                    sul[x][y] = norte[x][y - 1] = false;
                    criar(x, y - 1);
                    break;
                } else if (r >= 0.75 && r < 1.00 && !visitado[x - 1][y]) { // NORTE
                    oeste[x][y] = leste[x - 1][y] = false;
                    criar(x - 1, y);
                    break;
                }
            }
        }
    }

    // gera o labirinto , começando sua criação a partir da esquerda
    private void criar() {
        criar(1, 1);
    }

    // resolve o labirinto utilizando-se de recursao
    private void resolver(int x, int y) {
        /*if (x == 0 || y == 0 || x == N + 1 || y == N + 1) {
            return;
        }*/
        if (x < 0 || y < 0 || x == N || y == N) {
            System.out.println("Primeira condicao (X = "+ x +" | y = "+ y +")");
            return;
        }
        if (completo || visitado[x][y]) {
            return;
        }
        visitado[x][y] = true;
        /*if (completo || visit[x + y * this.N]) {
            return;
        }
        visit[x + y * this.N] = true;*/

        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
        StdDraw.show(30);

        // passando o parametro onde o labirinto ira terminar.
        if (x == fim2 && y == fim1) {
            completo = true;
        }
        // se aproveita da pilha de execucao do sistema, para realizar busca em
        // profundidade.
        int cell = grid[x + y * this.N];
        System.out.println("Valor da CELL: " + cell);
        
        System.out.println("& RIGHT: " + ((cell) & RIGHT));
        System.out.println("& LEFT: " + ((cell) & LEFT));
        System.out.println("& UP: " + ((cell) & UP));
        System.out.println("& DOWN: " + ((cell) & DOWN));
        
        
        if ((cell & RIGHT) == 0) {
            System.out.println("DIREITA");
            resolver(x + 1, y);
        }
        if ((cell & DOWN) == 0) {
            System.out.println("BAIXO");
            resolver(x, y + 1);
        }
        if ((cell & LEFT) == 0) {
            System.out.println("ESQ");
            resolver(x - 1, y);
        }
        if ((cell & UP) == 0) {
            System.out.println("CIMA");
            resolver(x, y - 1);
        }
        System.out.println("------------------------");
        if (completo) {
            return;
        }
        
        /*if (!norte[x][y]) {
            resolver(x, y + 1);
        }
        if (!leste[x][y]) {
            resolver(x + 1, y);
        }
        if (!sul[x][y]) {
            resolver(x, y - 1);
        }
        if (!oeste[x][y]) {
            resolver(x - 1, y);
        }
        if (completo) {
            return;
        }*/

        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
        StdDraw.show(30);
    }

    /*
     * Resolve o labirinto , comecando pelo parametro da posicao inicial passado
     * no main
     */
    public void resolver() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                visitado[x][y] = false;
            }
        }
        completo = false;
        resolver(inicio2, inicio1);
    }

    // desenhando o labirinto
    public void desenhe() {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(fim2 + 0.5, fim1 + 0.5, 0.375);
        StdDraw.filledCircle(inicio2 + 0.5, inicio1 + 0.5, 0.375);

        StdDraw.setPenColor(StdDraw.BLACK);
        /*for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                if (sul[x][y]) {
                    StdDraw.line(x, y, x + 1, y);
                }
                if (norte[x][y]) {
                    StdDraw.line(x, y + 1, x + 1, y + 1);
                }
                if (oeste[x][y]) {
                    StdDraw.line(x, y, x, y + 1);
                }
                if (leste[x][y]) {
                    StdDraw.line(x + 1, y, x + 1, y + 1);
                }
            }
        }*/
        
        for (int row = 0; row < this.N; row++) {
            for (int col = 0; col < this.N; col++) {
                int cell = grid[col + row * this.N];
                if ((cell & LEFT) != 0) {
                    //drawLeftWall(g, row, col);
                    StdDraw.line(col, row, col, row + 1);
                }
                if ((cell & RIGHT) != 0) {
                    //drawRightWall(g, row, col);
                    StdDraw.line(col + 1, row, col + 1, row + 1);
                }
                if ((cell & UP) != 0) {
                    //drawUpperWall(g, row, col);
                    StdDraw.line(col, row, col + 1, row);
                }
                if ((cell & DOWN) != 0) {
                    //drawLowerWall(g, row, col);
                    StdDraw.line(col, row + 1, col + 1, row + 1);
                }
            }
        }
        StdDraw.show(1000);
    }
    
    // *************************************************************************
    // Metodos para desnhar o labirinto na tela
    // *************************************************************************
    
    // Metodo de construcao do desenho do labirinto
    public void display(Graphics2D g) {
        for (int row = 0; row < this.N; row++) {
            for (int col = 0; col < this.N; col++) {
                int cell = grid[col + row * this.N];
                if ((cell & LEFT) != 0) drawLeftWall(g, row, col);
                if ((cell & RIGHT) != 0) drawRightWall(g, row, col);
                if ((cell & UP) != 0) drawUpperWall(g, row, col);
                if ((cell & DOWN) != 0) drawLowerWall(g, row, col);
            }
        }
    }
    
    // Parametros utilizados nos desenhos
    private final int startX = 20;
    private final int startY = 100;
    private final int size = 10;
    
    // Metodos para desenhar cada tipo de parede (cima, baixo, esq, dir)
    private void drawLeftWall(Graphics2D g, int row, int col) {
        g.drawLine(startX + size * col, startY + size * row,
                startX + size * col, startY + size * (row + 1));
    }

    private void drawRightWall(Graphics2D g, int row, int col) {
        g.drawLine(startX + size * (col + 1), startY + size * row,
                startX + size * (col + 1), startY + size * (row + 1));
    }

    private void drawUpperWall(Graphics2D g, int row, int col) {
        g.drawLine(startX + size * col, startY + size * row,
                startX + size * (col + 1), startY + size * row);
    }

    private void drawLowerWall(Graphics2D g, int row, int col) {
        g.drawLine(startX + size * col, startY + size * (row + 1),
                startX + size * (col + 1), startY + size * (row + 1));
    }
    // *************************************************************************

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite a dimensao de sua matriz");
        int N = sc.nextInt();

        System.out.println("Digite a posicao onde sua matriz ira comecar");
        do {
            inicio1 = (sc.nextInt()) - 1;
            inicio2 = (sc.nextInt()) - 1;
            if (inicio1 >= N || inicio2 >= N || inicio1 < 0 || inicio2 < 0) {
                System.out.println("Digite uma posicao contida na Matriz");
            }

        } while (inicio1 >= N || inicio2 >= N || inicio1 < 0 || inicio2 < 0);

        System.out.println("Digite a posicao onde sua matriz ira terminar");
        do {
            fim1 = (sc.nextInt()) - 1;
            fim2 = (sc.nextInt()) - 1;
            if (fim1 >= N || fim2 >= N || fim1 < 0 || fim2 < 0) {
                System.out.println("Digite uma posicao contida na Matriz");
            }

        } while (fim1 >= N || fim2 >= N || fim1 < 0 || fim2 < 0);

        MeuLabirinto Labirinto = new MeuLabirinto(N);
        // *********************************************************************
        // Constroe o frame, insere os desenhos do labirinto e exibe
        // *********************************************************************
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void paint(Graphics g) {
                        Labirinto.display((Graphics2D) g);
                    }
                };
                
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                GroupLayout layout = new GroupLayout(frame.getContentPane());
                frame.getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE));
                layout.setVerticalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE));
                frame.pack();

                frame.setVisible(true);
            }
        });
        // *********************************************************************
        StdDraw.show(0);
        Labirinto.desenhe();
        Labirinto.resolver();
    }

}

// *************************************************************************
// Classe que implementa o Union-Find (adaptada para o problema)
// *************************************************************************
class UnionFind2 {

    private int[] set;
    private int[] sizes;
    private int size;

    /**
     * Creates a new disjoint set, in which each item is in its own group.
     *
     * @param size The initial number of elements in the set.
     */
    public UnionFind2(int size) {
        this.set = new int[size];
        for (int i = 0; i < size; i++) {
            this.set[i] = i;
        }
        this.sizes = new int[size];
        for (int i = 0; i < size; i++) {
            this.sizes[i] = 1;
        }
        this.size = size;
    }

    /**
     * Finds the group in which the given item is found.
     *
     * Data structure optimization: while searching, makes all nodes in the
     * search path to point directly at the root of the group, which will be
     * returned.
     *
     * @param item the item for which group to search.
     * @return The item at the root of the found group.
     */
    public int find(int item) {
        int root = item;
        // find the root
        while (set[root] != root) {
            root = set[root];
        }
        // now shorten the paths
        int curr = item;
        while (set[curr] != root) {
            set[curr] = root;
        }
        return root;
    }

    /**
     * Joins the two groups together.
     *
     * Data structure optimization: joins the smaller group to the larger to
     * avoid long sequences.
     *
     * @return the root of the joint group.
     */
    public int union(int item1, int item2) {
        int group1 = find(item1);
        int group2 = find(item2);
        --size;
        if (sizes[group1] > sizes[group2]) {
            set[group2] = group1;
            sizes[group1] += sizes[group2];
            return group1;
        } else {
            set[group1] = group2;
            sizes[group2] += sizes[group1];
            return group2;
        }
    }
    
    public int size() {
        return size;
    }
}
// *****************************************************************************
