package threads;

/**
 * Um simples gerenciador de lembretes para introdução a threads.
 * 
 * @see LembreteIntroducao Lembretes gerenciados
 */
public class GerenciadorLembretesIntroducao {

	public static void main(String[] args) {
		LembreteIntroducao l1 = new LembreteIntroducao("dorflex", 8, 10);
		LembreteIntroducao l2 = new LembreteIntroducao("cataflan", 6, 12);
		LembreteIntroducao l3 = new LembreteIntroducao("tilenol", 12, 6);
		l1.start();
		l2.start();
		l3.start();
	}

}
