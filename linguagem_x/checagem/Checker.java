package checagem;

import java.util.ArrayList;
import java.util.List;
import sintaxe_abstrata.*;
import ambiente.*;
import utilitarios.*;


public class Checker extends Visitor {
    public AmbienteConsVar aConsVar = new AmbienteConsVar();
    public AmbienteFuncProc aFuncProc = new AmbienteFuncProc();
    public RegistroErros erros = new RegistroErros();
    
    public Checker() {
        /**
         * O c�digo abaixo � apenas para fins de exemplo
         * N�o tem signific�ncia nenhuma 
         */
        // Insere a "vari�vel" "x" do tipo "real" na tabela de s�mbolos
        /*VinculavelConsVar xVinc = (VinculavelConsVar) aConsVar.lookup("x", true, TipoBaseSemantico.Real);
        VinculavelConsVar yVinc = new VinculavelConsVar(true, TipoBaseSemantico.Int);
        System.out.println(aConsVar.tamanho());
        aConsVar.tabela.put("x", yVinc);
        System.out.println(aConsVar.get("x").tipo);
        System.out.println(aConsVar.tamanho());
        aConsVar.delete("x");
        System.out.println(aConsVar.tamanho());*/
        
        /*
        // Insere a "fun��o" "calculaSoma" do tipo "real" na tabela de s�mbolos
        List<PassagemTipoSemantico> p = new ArrayList<PassagemTipoSemantico>();
        p.add(new PassagemTipoSemantico(TipoBaseSemantico.Real));
        //VinculavelFuncProc x = (VinculavelFuncProc) aFuncProc.lookupFuncProc("calculaSoma", p);
        
        aConsVar.add("x", true, TipoBaseSemantico.Int);
        aConsVar.add("y", false, TipoBaseSemantico.Real);
        aConsVar.comecaEscopo();
        aConsVar.add("x", true, TipoBaseSemantico.Int);
        aConsVar.terminaEscopo();
        aConsVar.add("y", false, TipoBaseSemantico.Real);
        aConsVar.comecaEscopo();
        aConsVar.add("y", false, TipoBaseSemantico.Real);
        
        erros.reportar(01, "Que eu quiser");
        erros.mostrar();
        */
    }
    
    /**
     * Dada uma inst�ncia de TBase (sint�tico) retorna uma equivalente do tipo
     * TipoBaseSemantico (sem�ntico).
     * 
     * @param b
     * @return
     */
    private TipoBaseSemantico paraSemantico(TBase b) {
        switch (b.nome) {
            case "int":
                return TipoBaseSemantico.Int;
            
            case "bool":
                return TipoBaseSemantico.Bool;
            
            case "real":
                return TipoBaseSemantico.Real;
    
            default:
                return null;
        }
    }
    
    /**
     * Dado um TBase (sint�tico) e uma dimens�o, retorna um equivalente
     * do tipo TipoArraySemantico (sem�ntico).
     * 
     * @param b Tipo base sint�tico (sintaxe abstrata)
     * @param d Dimens�o do array
     * @return  TipoArraySemantico
     */
    private TipoArraySemantico paraSemantico(TBase b, int d) {
        return new TipoArraySemantico(paraSemantico(b), d);
    }

    @Override
    public Object visitAssign(ASSIGN a) {
        VinculavelConsVar tVar = (VinculavelConsVar) a.var.accept(this);
        TipoSemantico tExp = (TipoSemantico) a.exp.accept(this);
        
        if (! tVar.isVar) {
            erros.reportar(303, "Imposs�vel realizar atribui��o em constante");
        }
        if (tVar.tipo.equals(TipoBaseSemantico.Real)
                && tExp.equals(TipoBaseSemantico.Int)) {
            a.exp = new IntParaReal(a.exp);
            tExp = (TipoSemantico) a.exp.accept(this);
        }
        if (! tVar.tipo.equals(tExp)) {
            erros.reportar(400, "N�o � poss�vel atribuir " + tExp + " em "
                           + tVar.tipo);
        }
            
        return tVar.tipo;
    }

    @Override
    public Object visitBinExp(BinExp e) {
        TBase tipoRetorno = null;
        TipoBase esq, dir;
        
        switch (e.operacao.token) {
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
            case "<":
                esq = (TipoBase) e.expEsq.accept(this);
                dir = (TipoBase) e.expDir.accept(this);
                if (esq.tipo.equals(dir.tipo) && esq.tipo.nome != "bool") {
                    tipoRetorno = esq.tipo;
                }
                else {
                    try {
                        throw new Exception("BinExp: Tipos invalidos!");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                break;
            
            case "=":
                esq = (TipoBase) e.expEsq.accept(this);
                dir = (TipoBase) e.expDir.accept(this);
                if (esq.tipo.equals(dir.tipo)) {
                    tipoRetorno = esq.tipo;
                }
                else {
                    try {
                        throw new Exception("BinExp: Tipos invalidos!");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                break;
            
            case "and":
            case "or":
                esq = (TipoBase) e.expEsq.accept(this);
                dir = (TipoBase) e.expDir.accept(this);
                if (esq.tipo.equals(dir.tipo) && esq.tipo.nome == "bool") {
                    tipoRetorno = esq.tipo;
                }
                else {
                    try {
                        throw new Exception("BinExp: Tipos invalidos!");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                break;
        }
        return tipoRetorno;
    }

    @Override
    public Object visitBloco(BLOCO b) {
        aConsVar.comecaEscopo();
        for (DVarConsCom c : b.lista) {
            c.accept(this);
        }
        aConsVar.terminaEscopo();
        return null;
    }

    @Override
    public Object visitBlocoExp(BlocoExp b) {
        aConsVar.comecaEscopo();
        for (DCons d : b.listaCons) {
            d.accept(this);
        }
        TipoSemantico t = (TipoSemantico) b.exp.accept(this);
        aConsVar.terminaEscopo();
        return t;
    }

    @Override
    public Object visitChamada(CHAMADA c) {
        VinculavelFuncProc vinculo = aFuncProc.get(c.id);
        TipoSemantico t = ((vinculo != null) && (vinculo.isFunc)) ? vinculo.tipoRetorno : null;
        if (t == null){
          //ERRO - Func n�o declarada
        }
        if (c.listaExp.size() != vinculo.paramFunc.size()){
            //ERRO - Chamada: N�mero de param�tros difere do declarado
        } else{
            for (int i = 0; i < c.listaExp.size(); i++){
                TipoSemantico t1 = (TipoSemantico) c.listaExp.get(i).accept(this);
                TipoSemantico t2 = (TipoSemantico) vinculo.paramFunc.get(i);
                //verificar se t1 e t2 s�o compativeis
            }
        }
        return t;
    }

    @Override
    public Object visitChamadaExp(ChamadaExp c) {
        VinculavelFuncProc f = aFuncProc.get(c.id);
        /*
         * TODO
         * Verificar se � func�o ou procedimento e fazer a checagem dos tipos
         * dos parametros. Esbo�o
         * 
         * if (f.isFunc) 
         *      // checagem de parametros com c.listaExp e f.paramFunc
         * else
         *      // checagem de parametros com c.listaExp e f.paramProc
         */
        return f.tipoRetorno;
    }

    @Override
    public Object visitCom(Com c) {
        c.comando.accept(this);
        return null;
    }

    @Override
    public Object visitCons(Cons c) {
        TipoSemantico t = (TipoSemantico) c.tipo.accept(this);
        aConsVar.add(c.id, false, t);
        return null;
    }

    @Override
    public Object visitConsComp(ConsComp c) {
        TipoSemantico t = (TipoSemantico) c.tipo.accept(this);
        aConsVar.add(c.id, false, t);
        return null;
    }

    @Override
    public Object visitConsExt(ConsExt c) {
        TipoSemantico t = (TipoSemantico) c.tipo.accept(this);
        aConsVar.add(c.id, false, t);
        return null;
    }

    @Override
    public Object visitDC(DC d) {
        d.cons.accept(this);
        return null;
    }

    @Override
    public Object visitDecCons(DecCons d) {
        d.cons.accept(this);
        return null;
    }

    @Override
    public Object visitDecVar(DecVar d) {
        d.var.accept(this);
        return null;
    }

    @Override
    public Object visitDV(DV d) {
        d.var.accept(this);
        return null;
    }

    @Override
    public Object visitFuncao(Funcao f) {
        // FIXME Esse m�todo ta bugado, s� um esbo�o
        // Obt�m os tipos da lista de par�metros
        List<VinculavelConsVar> lParamVinc = new ArrayList<VinculavelConsVar>();
        for (Parametro p : f.listaParam) {
            lParamVinc.add((VinculavelConsVar) p.accept(this));
        }
        
        // Obt�m o tipo de retorno da fun��o
        TipoSemantico retorno = (TipoSemantico) f.tipo.accept(this); 
        
        
        List<TipoSemantico> passagemParam = new ArrayList<TipoSemantico>();
        
        aFuncProc.lookupFuncProc(f.id, passagemParam, retorno);
        return retorno;
    }

    @Override
    public Object visitIf(IF i) {
        TipoSemantico t = (TipoSemantico) i.exp.accept(this);
        if (! t.equals(TipoBaseSemantico.Bool)) {
            erros.reportar(203, "IF com express�o do tipo " + t + " inv�lido.");
        }
        i.comandoVerdade.accept(this);
        i.comandoFalso.accept(this);
        return null;
    }

    @Override
    public Object visitIndexada(Indexada i) {
        return i.var.accept(this);
    }
    
    @Override
    public Object visitIntParaReal(IntParaReal i) {
        return TipoBaseSemantico.Real;
    }

    @Override
    public Object visitLiteralBool(LiteralBool l) {
        return TipoBaseSemantico.Bool;
    }

    @Override
    public Object visitLiteralInt(LiteralInt l) {
        return TipoBaseSemantico.Int;
    }
    
    @Override
    public Object visitLiteralReal(LiteralReal l) {
        return TipoBaseSemantico.Real;
    }

    @Override
    public Object visitMenos(Menos m) {
         TipoSemantico t = (TipoSemantico) m.exp.accept(this);
         if (t.equals(TipoBaseSemantico.Int)
                 || t.equals(TipoBaseSemantico.Real)) {
             return t;
         }
         else {
             erros.reportar(101, "Opera��o uni�ria de Menos inv�lida para " + t);
             return TipoBaseSemantico.Int;
         }
    }

    @Override
    public Object visitNao(Nao n) {
        TipoSemantico t = (TipoSemantico) n.exp.accept(this);
        if (t.equals(TipoBaseSemantico.Bool)) {
            return t;
        }
        else {
            erros.reportar(102, "Opera��o un�ria de Nega��o inv�lida para " + t);
            return TipoBaseSemantico.Int;
        }
    }

    @Override
    public Object visitParArrayCopia(ParArrayCopia p) {
        aConsVar.add(p.id, true, paraSemantico(p.tipo, p.dimensao));
        return null;
    }

    @Override
    public Object visitParArrayRef(ParArrayRef p) {
        aConsVar.add(p.id, true, paraSemantico(p.tipo, p.dimensao));
        return null;
    }

    @Override
    public Object visitParBaseCopia(ParBaseCopia p) {
        aConsVar.add(p.id, true, paraSemantico(p.tipo));
        return null;
    }

    @Override
    public Object visitParBaseRef(ParBaseRef p) {
        aConsVar.add(p.id, true, paraSemantico(p.tipo));
        return null;
    }

    @Override
    public Object visitProcedimento(Procedimento p) {
        if (! aFuncProc.contem(p.id)) {
            List<PassagemTipoSemantico> l = new ArrayList<PassagemTipoSemantico>();
            /**
             * TODO
             * O for abaixo n�o esta 100% correto
             * Converter a lista de parametros sint�tica para sem�ntica, ex:
             * l.add(new PassagemTipoSemantico(TipoBaseSemantico.Real));
             */
            for (Parametro param : p.listaParam) {
                l.add( (PassagemTipoSemantico) param.accept(this) );
            }
            
            // aConsVar.comecaEscopo();
            aFuncProc.lookupFuncProc(p.id, l);
            p.comando.accept(this);
            // aConsVar.terminaEscopo();
        }
        else erros.reportar(200, "O identificador " + p.id
                            + " j� est� no ambiente.");
        
        return null;
    }
    
    @Override
    public Object visitPrograma(Programa p) {
        for (Dec d : p.declaracoes) {
            d.accept(this);
        }
        return null;
    }

    @Override
    public Object visitSimples(Simples s) {
        VinculavelConsVar v = aConsVar.get(s.id);
        // Se for nulo reporta o erro e assume uma vari�vel do tipo inteiro
        if (v == null) {
            erros.reportar(404, "Identificador " + s.id
                           + " n�o encontrado no ambiente!");
            v = new VinculavelConsVar(true, TipoBaseSemantico.Int);
        }
        return v;
    }

    @Override
    public Object visitTipoArray(TipoArray t) {
        TipoArraySemantico s = new TipoArraySemantico(
            this.paraSemantico(t.base),
            t.exp.size()
        );
        return s; 
    }

    @Override
    public Object visitTipoBase(TipoBase t) {
        return this.paraSemantico(t.tipo);
    }

    @Override
    public Object visitVarExp(VarExp v) {
        return v.var.accept(this);
    }

    @Override
    public Object visitVarInic(VarInic v) {
        TipoSemantico t = (TipoSemantico) v.nomeTipo.accept(this);
        if (! aConsVar.add(v.id, true, t)) {
            erros.reportar(200, "O identificador " + v.id
                           + " n�o foi adicionado no ambiente.");
        }
        return null;
    }

    @Override
    public Object visitVarInicComp(VarInicComp v) {
        TipoSemantico t = (TipoSemantico) v.nomeTipo.accept(this);
        if (! aConsVar.add(v.id, true, t)) {
            erros.reportar(200, "O identificador " + v.id
                           + " n�o foi adicionado no ambiente.");
        }
        return null;
    }

    @Override
    public Object visitVarInicExt(VarInicExt v) {
        TipoSemantico t = (TipoSemantico) v.nomeTipo.accept(this);
        if (! aConsVar.add(v.id, true, t)) {
            erros.reportar(200, "O identificador " + v.id
                           + " n�o foi adicionado no ambiente.");
        }
        return null;
    }

    @Override
    public Object visitVarNaoInic(VarNaoInic v) {
        TipoSemantico t = (TipoSemantico) v.nomeTipo.accept(this);
        if (! aConsVar.add(v.id, true, t)) {
            erros.reportar(200, "O identificador " + v.id
                           + " n�o foi adicionado no ambiente.");
        }
        return null;
    }

    @Override
    public Object visitWhile(WHILE w) {
        TipoSemantico t = (TipoSemantico) w.exp.accept(this);
        if (! t.equals(TipoBaseSemantico.Bool)) {
            erros.reportar(103, "A express�o deve ser do tipo bool");
        }
        w.comando.accept(this);
        return null;
    }
}
