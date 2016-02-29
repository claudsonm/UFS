/* *****************************************************************************
 * Códigos desenvolvidos pelos seguintes alunos
 *
 * @author Claudson Bispo Martins Santos    201410042132
 * @author Edgar Vieira Lima Neto           201410042150
 * @author Guilherme Boroni Pereira         201410042197
 * ****************************************************************************/

package ed2;
/**
 * Interface genérica que define as operações de organizadores de arquivos
 * de alunos em disco.
 * 
 * @author Tarcisio Rocha
 *
 */
public interface FileOrganizer {
    
    /**
     * Dada uma instância da classe Aluno, este método adiciona os dados
     * da instância em um arquivo seguindo o método de organização
     * de arquivos especificado.
     * 
     * @param a Instância da classe Aluno
     */
    public void addReg(Aluno a);
    
    /**
     * Dado um número de matrícula, localiza e exclui o registro do arquivo
     * 
     * @param matric Matrícula do aluno a ser excluído
     * @return Instância da classe Aluno correspondente ao aluno excluído
     */
    public Aluno delReg(int matric);
    
    /**
     * Dado um número de matrícula, este método consulta o arquivo de alunos e
     * devolve uma instância que encapsula os dados do aluno com a referida
     * matrícula.
     * 
     * @param matric Número de matrícula para a consulta
     * @return Instância da classe Aluno, Null se for inexistente
     */
    public Aluno getReg(int matric);
}
