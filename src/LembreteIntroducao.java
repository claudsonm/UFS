package threads;

/**
 * Uma simples classe de lembrete, apenas para introdução a threads.
 * 
 * @see GerenciadorLembretesIntroducao Classe que gerencia estes lembretes
 */
public class LembreteIntroducao extends Thread {

	private String nome;

	private int intervalo;

	private int total;

	/**
	 * Construtor da classe lembrete
	 * 
	 * @param string Nome do lembrete
	 * @param t Intervalo de tempo (horas)
	 * @param n Numero total de repetições
	 */
	public LembreteIntroducao(String nome, int intervalo, int total) {
		this.nome = nome;
		this.intervalo = intervalo;
		this.total = total;
	}

	@Override
	public void run() {
		while (total > 0) {
			try {
				System.out.println("Hey, lembre-se de tomar: " + nome);
				Thread.sleep(this.intervalo * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			total--;
		}
	}
}