package linguagem_x;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
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
         * area := areaCirculo(raio);
         * diametro := diametroCirculo(raio);
         * comprimento := PI * diametro;
         * 
         * var real resultados[3] := {area, diametro, comprimento};
         * var int i := 0;
         * var real soma;
         * var bool par;
         * 
         * while(i < 3){
         *     soma := soma + resultados[i];
         *     i := i + 1;
         * }
         * 
         * if (soma % 2 = 0) par := true;
         * else par := false;
         * 
         */
        
        // Lista contendo todas as declarações do programa
        List<Dec> d = new ArrayList<Dec>();
        
        // Lista de parâmetros das funções (neste caso, igual para as duas)
        List<Parametro> parametros = new ArrayList<Parametro>();
        parametros.add( new ParBaseCopia(TBase.Real, "r") );
        
        // Declara e inicializa a constante PI e a variável raio
        d.add( new DecCons(new Cons(new TipoBase(TBase.Real), "PI", new LiteralInt(3))) );
        d.add( new DecVar(new VarInic(new TipoBase(TBase.Real), "raio", new LiteralInt(6))) );
        
        // Declara as funções areaCirculo e diametroCirculo
        d.add(
            new Funcao(new TipoBase(TBase.Real), "areaCirculo", parametros,
                new BinExp(BinOp.Mul,
                    new BinExp(BinOp.Mul, new VarExp(new Simples("r")), new VarExp(new Simples("r"))),
                    new BinExp(BinOp.Mul, new VarExp(new Simples("PI")), new LiteralInt(2))
                )
            )
        );
        d.add(
            new Funcao(new TipoBase(TBase.Real), "diametroCirculo", parametros,
                new BinExp(BinOp.Mul, new VarExp(new Simples("r")), new LiteralInt(2))
            )
        );
        
        // Declara as variáveis area, comprimento e diametro
        d.add( new DecVar(new VarNaoInic(new TipoBase(TBase.Real), "area")) );
        d.add( new DecVar(new VarNaoInic(new TipoBase(TBase.Real), "comprimento")) );
        d.add( new DecVar(new VarNaoInic(new TipoBase(TBase.Real), "diametro")) );
        
        // Lista de parametros que serão passados nas chamadas das funções
        List<Exp> passagemParametros = new ArrayList<Exp>();
        passagemParametros.add( new VarExp(new Simples("raio")) );
        
        /**
         * FIXME Na BNF comandos e expressões não foram herdados de Dec
         * Por isso as instruções abaixo não podem ser adicionados no programa
         */
        // Realiza as atribuições às variáveis area, diametro e comprimento
        new ASSIGN( new Simples("area"), new ChamadaExp("areaCirculo", passagemParametros) );
        new ASSIGN( new Simples("diametro"), new ChamadaExp("diametroCirculo", passagemParametros) );
        new ASSIGN(
            new Simples("comprimento"),
            new BinExp(
                BinOp.Mul, new VarExp(new Simples("PI")), new VarExp(new Simples("diametro"))
            )
        );
        /* END-FIXME */
        
        // Lista com a expressão que define o tamanho do array
        List<Exp> tamanhoArray = new ArrayList<Exp>();
        tamanhoArray.add(new LiteralInt(3));
        
        // Expressões que definem os valores de cada posição do array
        List<Exp> valoresArray = new ArrayList<Exp>();
        valoresArray.add( new VarExp(new Simples("area")) );
        valoresArray.add( new VarExp(new Simples("diametro")) );
        valoresArray.add( new VarExp(new Simples("comprimento")) );
        
        // Declaração e inicialização do array
        d.add(
            new DecVar(
                new VarInicExt(new TipoArray(TBase.Real, tamanhoArray), "resultados", valoresArray)
            )
        );
        
        // Declaração e inicialização da variável i
        d.add( new DecVar(new VarInic(new TipoBase(TBase.Int), "i", new LiteralInt(0))) );
        
        // Declaração das variáveis soma e par
        d.add( new DecVar(new VarNaoInic(new TipoBase(TBase.Real), "soma")) );
        d.add( new DecVar(new VarNaoInic(new TipoBase(TBase.Bool), "par")) );
        
        /**
         * FIXME Na BNF comandos e expressões não foram herdados de Dec
         * Por isso as instruções abaixo não podem ser adicionados no programa
         */
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
                                new Simples("resultados"), new VarExp(new Simples("i"))
                            )
                        )
                    )
                )
            )
        );
        comandosLopp.add(
            new Com(
                new ASSIGN(
                    new Simples("i"),
                    new BinExp(
                        BinOp.Som, new VarExp(new Simples("i")), new LiteralInt(1)
                    )
                )
            )
        );
        
        // Declaração do bloco while
        new WHILE(
            new BinExp(
                BinOp.Menor,
                new VarExp(new Simples("i")),
                new LiteralInt(3)
            ),
            new BLOCO(comandosLopp)
        );
        
        // Declaração do bloco if
        new IF(
            new BinExp(
                BinOp.Igual,
                new LiteralInt(0),
                new BinExp(
                    BinOp.Mod,
                    new VarExp(new Simples("soma")),
                    new LiteralInt(2)
                )
            ),
            new ASSIGN(new Simples("par"), new LiteralBool(true)),
            new ASSIGN(new Simples("par"), new LiteralBool(false))
        );
        /* END-FIXME */
        
        Programa p = new Programa(d);
        System.out.println(p.declaracoes);
    }

}
