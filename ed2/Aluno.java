/* *****************************************************************************
 * Códigos desenvolvidos pelos seguintes alunos
 *
 * @author Claudson Bispo Martins Santos    201410042132
 * @author Edgar Vieira Lima Neto           201410042150
 * @author Guilherme Boroni Pereira         201410042197
 * ****************************************************************************/

package ed2;

import java.io.*;
import java.nio.*;

public class Aluno {
    
    // Define as contantes com o tamanho de cada campo do registro
    public static final int NOME = 50;
    public static final int ENDERECO = 60;
    public static final int IDADE = 2;
    public static final int SEXO = 1;
    public static final int EMAIL = 40;
    public static final int MATRICULA = 4;

    // Calcula e define o tamanho total de cada registro
    public static final int TOTAL = NOME + ENDERECO + IDADE + SEXO +
                                    MATRICULA + EMAIL;

    /**
     * A matricula do aluno
     * 4 bytes
     */
    private int matricula;
    
    /**
     * O nome do aluno
     * 50 bytes
     */
    private String nome;
    
    /**
     * O endereço do aluno
     * 60 bytes
     */
    private String endereco;
    
    /**
     * A idade do aluno
     * 2 bytes
     */
    private short idade;
    
    /**
     * O sexo do aluno
     * 1 byte
     */
    private String sexo;
    
    /**
     * O e-mail do aluno
     * 40 bytes
     */
    private String email;
    
    /**
     * Cria uma instância de Aluno a partir dos atributos fornecidos
     * 
     * @param matricula
     * @param nome
     * @param endereco
     * @param idade
     * @param sexo
     * @param email 
     */
    public Aluno(int matricula, String nome, String endereco, short idade,
                 String sexo, String email) {
        setMatricula(matricula);
        setNome(nome);
        setEndereco(endereco);
        setIdade(idade);
        setSexo(sexo);
        setEmail(email);
    }
    
    /**
     * Cria uma instância de Aluno a partir da leitura de um buffer
     * 
     * @param buf
     * @throws IOException 
     */
    public Aluno(ByteBuffer buf) throws IOException {
        this.matricula = buf.getInt();
        byte[] stringSize;
        stringSize = new byte[NOME];
        buf.get(stringSize);
        this.nome = new String(stringSize);

        stringSize = new byte[ENDERECO];
        buf.get(stringSize);
        this.endereco = new String(stringSize);

        this.idade = buf.getShort();

        stringSize = new byte[SEXO];
        buf.get(stringSize);
        this. sexo = new String(stringSize);

        stringSize = new byte[EMAIL];
        buf.get(stringSize);
        this. email = new String(stringSize);
    }
    
    /**
     * Formata a palavra para uma nova dimensão
     * 
     * @param palavra
     * @param dimensao
     * 
     * @return Palavra formatada na dimensão desejada
     */
    public String corrigir(String palavra, int dimensao) {
        // Versão recursiva [estoura a pilha ao lidar com tamanhos grandes]
        /*
        int tam = palavra.length();
        if (tam > dimensao)
            return corrigir(palavra.substring(0, palavra.length()-1), dimensao);
        else if (tam < dimensao)
            return corrigir(palavra + " ", dimensao);
        return palavra;
        */
        
        int c = dimensao - palavra.length();;
        for (int i = 0; i < c; i++) palavra = palavra + " ";
        palavra = palavra.substring(0, dimensao);
    
        return palavra;
    }
    
    /**
     * Obtém um ByteBuffer com as informações do aluno
     * 
     * @return o buffer
     */
    public ByteBuffer getByteBuffer(){
        ByteBuffer b = ByteBuffer.allocate(TOTAL);
        b.putInt(matricula);
        b.put(nome.getBytes());
        b.put(endereco.getBytes());
        b.putShort(idade);
        b.put(sexo.getBytes());
        b.put(email.getBytes());
        b.flip();
        return b;
    }

    /**
     * Obtém a matrícula do aluno
     * 
     * @return a matricula
     */
    public int getMatricula() {
        return matricula;
    }

    /**
     * Define a matrícula do aluno
     * 
     * @param matricula a matricula a ser definida
     */
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    /**
     * Obtém o nome do aluno
     * 
     * @return o nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do aluno
     * 
     * @param nome o nome a ser definido
     */
    public void setNome(String nome) {
        this.nome = corrigir(nome, NOME);
    }

    /**
     * Obtém o endereço do aluno
     * 
     * @return o endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Define o endereço do aluno
     * 
     * @param endereco o endereco a ser definido
     */
    public void setEndereco(String endereco) {
        this.endereco = corrigir(endereco, ENDERECO);
    }

    /**
     * Obtém a idade do aluno
     * 
     * @return a idade
     */
    public short getIdade() {
        return idade;
    }

    /**
     * Define a idade do aluno
     * 
     * @param idade a idade a ser definida
     */
    public void setIdade(short idade) {
        this.idade = idade;
    }

    /**
     * Obtém o sexo do aluno
     * 
     * @return o sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Define o sexo do aluno
     * 
     * @param sexo o sexo a ser definido
     */
    public void setSexo(String sexo) {
        this.sexo = corrigir(sexo, SEXO);
    }

    /**
     * Obtém o email do aluno
     * 
     * @return o email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email do aluno
     * 
     * @param email o email a ser definido
     */
    public void setEmail(String email) {
        this.email = corrigir(email, EMAIL);
    }
    
}
