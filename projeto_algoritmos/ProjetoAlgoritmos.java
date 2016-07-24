/**
 * Os códigos foram desenvolvidos durante as aulas pelos seguintes alunos
 * 
 * @author Claudson Bispo Martins Santos    201410042132
 * @author Edgar Vieira Lima Neto           201410042150
 * @author Guilherme Boroni Pereira         201410042197
 */
package projeto_algoritmos;

public class ProjetoAlgoritmos {
    public static void main(String[] args) {
//        long startTime, endTime, totalTime;
        //Fatorial.fatIter(3);
//        Fibonacci.fibIter(3);
//        long x;
//        int x;
        // ------------------------------------
        // ------------------------------------
//        startTime = System.nanoTime();
        
        //x = Fatorial.fatIter(1000);
        //x = Fibonacci.fibIter(50);
        
       No no10 = new No(10);
       No no7 = new No(7);
       No no15 = new No(15);
       No no5 = new No(5);
       No no8 = new No(8);
       No no11 = new No(11);
       No no23 = new No(23);
       No no6 = new No(6);
       
       no10.esq = no7;
       no10.dir = no15;
       no7.esq  = no5;
       no7.dir  = no8;
       no5.dir  = no6;
       no15.esq = no11;
       no15.dir = no23;
       
       Arvore bina = new Arvore(no10);
       bina.preOrdem();
       
        
//        endTime   = System.nanoTime();
//        totalTime = endTime - startTime;
//        System.out.println("----------------------");
//        System.out.println("Valor f(n): " + x);
//        System.out.println(totalTime + " ns");
//        // ------------------------------------
//        // ------------------------------------
//        startTime = System.nanoTime();
        
        //x = Fatorial.fatRec(1000);
//        x = Fibonacci.fibRec(50);
        
//        endTime   = System.nanoTime();
//        totalTime = endTime - startTime;
//        System.out.println("----------------------");
//        System.out.println("Valor f(n): " + x);
//        System.out.println(totalTime + " ns");
    }
}
