﻿package interpretador;

import analisador.*;
import checagem.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import sintaxe_abstrata.*;

/**
 * Classe principal do projeto, inicia a execução.
 *
 * @author Claudson Martins
 */
public class Main {

    public static void main(String[] args) {
        interpretacao();
    }

    public static void programa1() {
        /* =======================================================
         * Programa #1 em Linguagem X
         * Realiza alguns cálculos na circunferência
         * =======================================================
         *
         * cons real PI := 3.14;
         * var real raio := 6;
         *
         * function real areaCirculo(var real r)
         *     2 * PI * (r * r);
         *
         * function real diametroCirculo(var real r)
         *     2 * r;
         *
         * var real area, comprimento, diametro;
         *
         * procedimento main () {
         *     area := areaCirculo(raio);
         *     diametro := diametroCirculo(raio);
         *     comprimento := PI * diametro;
         *
         *     var real[] resultados := {area, diametro, comprimento};
         *     var int i := 0;
         *     var real soma;
         *     var bool par;
         *     while(i < 3){
         *         soma := soma + resultados[i];
         *         i := i + 1;
         *     }
         *     if (soma % 2 = 0) par := true;
         *     else par := false;
         * }
         *
         */

        // Lista contendo todas as declarações do programa
        List<Dec> d = new ArrayList<Dec>();

        // Lista de parâmetros das funções (neste caso, igual para as duas)
        List<Parametro> parametros = new ArrayList<Parametro>();
        parametros.add(new ParBaseCopia(TBase.Real, "r"));

        // Declara e inicializa a constante PI e a variável raio
        d.add(new DecCons(new Cons(new TipoBase(TBase.Real), "PI", new LiteralReal(3.14))));
        d.add(new DecVar(new VarInic(new TipoBase(TBase.Real), "raio", new LiteralInt(6))));

        // Declara as funções areaCirculo e diametroCirculo
        d.add(
                new Funcao(
                        new TipoBase(TBase.Real),
                        "areaCirculo",
                        parametros,
                        new BinExp(
                                BinOp.Mul,
                                new BinExp(
                                        BinOp.Mul,
                                        new VarExp(new Simples("r")),
                                        new VarExp(new Simples("r"))),
                                new BinExp(
                                        BinOp.Mul,
                                        new VarExp(new Simples("PI")),
                                        new LiteralInt(2)))));
        d.add(
                new Funcao(
                        new TipoBase(TBase.Real),
                        "diametroCirculo",
                        parametros,
                        new BinExp(BinOp.Mul, new VarExp(new Simples("r")), new LiteralInt(2))));

        // Declara as variáveis area, comprimento e diametro
        d.add(new DecVar(new VarNaoInic(new TipoBase(TBase.Real), "area")));
        d.add(new DecVar(new VarNaoInic(new TipoBase(TBase.Real), "comprimento")));
        d.add(new DecVar(new VarNaoInic(new TipoBase(TBase.Real), "diametro")));

        // Lista de parametros que serão passados nas chamadas das funções
        List<Exp> passagemParametros = new ArrayList<Exp>();
        passagemParametros.add(new VarExp(new Simples("raio")));

        // Comandos do main
        List<DVarConsCom> instrucoesMain = new ArrayList<DVarConsCom>();

        // Realiza as atribuições às variáveis area, diametro e comprimento
        instrucoesMain.add(
                new Com(
                        new ASSIGN(
                                new Simples("area"),
                                new ChamadaExp("areaCirculo", passagemParametros))));
        instrucoesMain.add(
                new Com(
                        new ASSIGN(
                                new Simples("diametro"),
                                new ChamadaExp("diametroCirculo", passagemParametros))));
        instrucoesMain.add(
                new Com(
                        new ASSIGN(
                                new Simples("comprimento"),
                                new BinExp(
                                        BinOp.Mul,
                                        new VarExp(new Simples("PI")),
                                        new VarExp(new Simples("diametro"))))));

        // Lista com a expressão que define o tamanho do array
        List<Exp> tamanhoArray = new ArrayList<Exp>();
        tamanhoArray.add(new LiteralInt(3));

        // Expressões que definem os valores de cada posição do array
        List<Exp> valoresArray = new ArrayList<Exp>();
        valoresArray.add(new VarExp(new Simples("area")));
        valoresArray.add(new VarExp(new Simples("diametro")));
        valoresArray.add(new VarExp(new Simples("comprimento")));

        // Declaração e inicialização do array
        instrucoesMain.add(
                new DV(
                        new VarInicExt(
                                new TipoArray(TBase.Real, tamanhoArray),
                                "resultados",
                                valoresArray)));

        // Declaração e inicialização da variável i
        instrucoesMain.add(new DV(new VarInic(new TipoBase(TBase.Int), "i", new LiteralInt(0))));

        // Declaração das variáveis soma e par
        instrucoesMain.add(new DV(new VarNaoInic(new TipoBase(TBase.Real), "soma")));
        instrucoesMain.add(new DV(new VarNaoInic(new TipoBase(TBase.Bool), "par")));

        // Lista de comandos a serem executados no bloco while
        List<DVarConsCom> comandosLopp = new ArrayList<DVarConsCom>();
        comandosLopp.add(
                new Com(
                        new ASSIGN(
                                new Simples("soma"),
                                new BinExp(
                                        BinOp.Som,
                                        new VarExp(new Simples("soma")),
                                        new VarExp(
                                                new Indexada(
                                                        new Simples("resultados"),
                                                        new VarExp(new Simples("i"))))))));
        comandosLopp.add(
                new Com(
                        new ASSIGN(
                                new Simples("i"),
                                new BinExp(
                                        BinOp.Som,
                                        new VarExp(new Simples("i")),
                                        new LiteralInt(1)))));

        // Declaração do bloco while
        instrucoesMain.add(
                new Com(
                        new WHILE(
                                new BinExp(
                                        BinOp.Menor,
                                        new VarExp(new Simples("i")),
                                        new LiteralInt(3)),
                                new BLOCO(comandosLopp))));

        // Declaração do bloco if
        instrucoesMain.add(
                new Com(
                        new IF(
                                new BinExp(
                                        BinOp.Igual,
                                        new LiteralInt(0),
                                        new BinExp(
                                                BinOp.Mod,
                                                new VarExp(new Simples("soma")),
                                                new LiteralInt(2))),
                                new ASSIGN(new Simples("par"), new LiteralBool(true)),
                                new ASSIGN(new Simples("par"), new LiteralBool(false)))));

        // Corpo do procedimento main
        BLOCO corpoMain = new BLOCO(instrucoesMain);
        // Declara o procedimento main
        d.add(new Procedimento("main", new ArrayList<>(), corpoMain));

        Programa p = new Programa(d);
        Checker c = new Checker();
        c.visitPrograma(p);
        c.erros.mostrar();
    }

    public static void programa2() {
        /* =======================================================
         * Programa #2 em Linguagem X
         * Realiza o cálculo da hipotenusa
         * =======================================================
         *
         * function real tamanhoHipotenusa(var real l1, var real l2)
         *      (l1 * l1) + (l2 * l2);
         *
         * procedure main() {
         *     var real lado1 := 6.6;
         *     var int lado2 := 5;
         *     var real hipotenusa;
         *
         *     hipotenusa := tamanhoHipotenusa(lado1, lado2); // erro, real + int
         *     var real[] resultados := {hipotenusa};
         * }
         *
         */

        // Lista contendo todas as declarações do programa
        List<Dec> d = new ArrayList<Dec>();

        // FUNÇÃO HIPOTENUSA
        // Lista de parâmetros da função hipotenusa
        List<Parametro> parametros = new ArrayList<Parametro>();
        parametros.add(new ParBaseCopia(TBase.Real, "l1"));
        parametros.add(new ParBaseCopia(TBase.Real, "l2"));

        // Declara a função hipotenusa
        d.add(
                new Funcao(
                        new TipoBase(TBase.Real),
                        "tamanhoHipotenusa",
                        parametros,
                        new BinExp(
                                BinOp.Mul,
                                new BinExp(
                                        BinOp.Mul,
                                        new VarExp(new Simples("l1")),
                                        new VarExp(new Simples("l1"))),
                                new BinExp(
                                        BinOp.Mul,
                                        new VarExp(new Simples("l2")),
                                        new VarExp(new Simples("l2"))))));

        // PROCEDIMENTO MAIN
        // declarações de variáveis do procedimento main
        List<DVarConsCom> instrucoesMain = new ArrayList<DVarConsCom>();
        instrucoesMain.add(
                new DV(new VarInic(new TipoBase(TBase.Real), "lado1", new LiteralReal(6.6))));
        instrucoesMain.add(
                new DV(new VarInic(new TipoBase(TBase.Int), "lado2", new LiteralInt(5))));
        instrucoesMain.add(new DV(new VarNaoInic(new TipoBase(TBase.Real), "hipotenusa")));

        // Lista de parametros que serão passados na chamada da função hipotenusa
        List<Exp> passagemParametros = new ArrayList<Exp>();
        passagemParametros.add(new VarExp(new Simples("lado1")));
        passagemParametros.add(new VarExp(new Simples("lado2")));

        // Realiza a atribuição a variável hipotenusa
        instrucoesMain.add(
                new Com(
                        new ASSIGN(
                                new Simples("hipotenusa"),
                                new ChamadaExp("tamanhoHipotenusa", passagemParametros))));

        // Lista com a expressão que define o tamanho do array
        List<Exp> tamanhoArray = new ArrayList<Exp>();
        tamanhoArray.add(new LiteralInt(1));

        // Expressões que definem os valores de cada posição do array
        List<Exp> valoresArray = new ArrayList<Exp>();
        valoresArray.add(new VarExp(new Simples("hipotenusa")));

        // Declaração e inicialização do array
        instrucoesMain.add(
                new DV(
                        new VarInicExt(
                                new TipoArray(TBase.Real, tamanhoArray),
                                "resultados",
                                valoresArray)));

        // Corpo do procedimento main
        BLOCO corpoMain = new BLOCO(instrucoesMain);
        // Declara o procedimento main
        d.add(new Procedimento("main", new ArrayList<>(), corpoMain));

        Programa p = new Programa(d);
        Checker c = new Checker();
        c.visitPrograma(p);
        c.erros.mostrar();
    }

    public static void interpretacao() {
        try {
            AnalisadorSintatico p =
                    new AnalisadorSintatico(
                            new AnalisadorLexico(
                                    new FileReader("linguagem_x//utilitarios//prog4x.txt")));
            Object result = p.parse().value;
            //System.out.println(result.toString());

            Checker c = new Checker();
            Object checkResult = c.visitPrograma((Programa) result);
            c.erros.mostrar();
            if (c.erros.quantidade() == 0) {
                Interpretador i = new Interpretador();
                System.out.println("========== APÓS CHECKER ========\n" + checkResult);
                i.visitPrograma((Programa) checkResult);
                System.out.println("================================");
                i.erros.mostrar();

                System.out.println("============= GLOBAIS ============= ");
                for (int j = 0, tGlobal = i.mem.globais.length; j < tGlobal; j++) {
                    if (i.mem.globais[j] == null) {
                        break;
                    }
                    System.out.println("\t[" + j + "] \t|\t" + i.mem.globais[j]);
                }
                System.out.println("=================================== ");

                System.out.println("============= PILHA ============== ");
                for (int j = 0, tPilha = i.mem.stackFrame.length; j < tPilha; j++) {
                    if (i.mem.stackFrame[j] == null) {
                        break;
                    }
                    System.out.println("\t[" + j + "] \t|\t" + i.mem.stackFrame[j]);
                }
                System.out.println("=================================== ");

                System.out.println(i.mem.funcoesProc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
