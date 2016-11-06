/*
 * MIT License
 *
 * Copyright (c) 2016 Claudson Martins
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ambiente;

import java.util.HashMap;
import java.util.List;
import checagem.*;

/**
 * Gerencia a tabela de s�mbolos de fun��es e procedimentos do Checker. Atua como uma f�brica dos
 * flyweights para gerar objetos da classe concreta VinculavelFuncProc.
 *
 * @author Claudson Martins
 * @author Edgar Lima
 * @author Guilherme Boroni
 */
public class AmbienteFuncProc {

    /**
     * Tabela hash que mapeia o nome de um identificador para uma Vinculavel, que cont�m as
     * informa��es sobre os tipos das fun��es ou procedimentos.
     */
    public static final HashMap<String, Vinculavel> tabela = new HashMap<>();
    
    /**
     * Recupera ou cria na tabela de s�mbolos as informa��es sobre uma <b>fun��o</b>.
     * 
     * @param id nome do identificador
     * @param p lista de tipos dos par�metros formais
     * @param r informa��es sobre o tipo de retorno
     * @return informa��es sobre a fun��o
     */
    public VinculavelFuncProc lookupFuncProc(String id, List<PassagemTipoSemantico> p,
            TipoSemantico r) {
        VinculavelFuncProc x = (VinculavelFuncProc) tabela.get(id);
        if (x == null) {
            x = new VinculavelFuncProc(p, r);
            tabela.put(id, x);
            System.out.println("Criando FuncProc no ambiente: func " + id + "(" + x.paramFunc + ")");
        }
        return x;
    }
    
    /**
     * Recupera ou cria na tabela de s�mbolos as informa��es sobre um <b>procedimento</b>.
     * 
     * @param id nome do identificador
     * @param p lista de tipos dos par�metros formais
     * @return informa��es sobre a procedimento
     */
    public VinculavelFuncProc lookupFuncProc(String id, List<PassagemTipoSemantico> p) {
        VinculavelFuncProc x = (VinculavelFuncProc) tabela.get(id);
        if (x == null) {
            x = new VinculavelFuncProc(p);
            tabela.put(id, x);
            System.out.println("Criando FuncProc no ambiente: proc " + id + "(" + x.paramProc + ")");
        }
        return x;
    }
    
    /**
     * Recupera da tabela de s�mbolos a fun��o ou procedimento.
     * 
     * @param id nome da fun��o ou procedimento
     * @return informa��es sobre a fun��o ou procedimento
     */
    public VinculavelFuncProc get(String id) {
        return (VinculavelFuncProc) tabela.get(id);
    }
    
    /**
     * Verifica se o identificador j� existe na tabela de s�mbolos.
     * 
     * @param id nome do identificador
     * @return true caso j� exista, false caso n�o exista
     */
    public boolean contem(String id) {
        return tabela.containsKey(id);
    }
}
