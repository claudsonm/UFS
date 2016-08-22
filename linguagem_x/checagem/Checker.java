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
         * O código abaixo é apenas para fins de exemplo
         * Não tem significância nenhuma 
         */
        // Insere a "variável" "x" do tipo "real" na tabela de símbolos
        /*VinculavelConsVar xVinc = (VinculavelConsVar) aConsVar.lookup("x", true, TipoBaseSemantico.Real);
        VinculavelConsVar yVinc = new VinculavelConsVar(true, TipoBaseSemantico.Int);
        System.out.println(aConsVar.tamanho());
        aConsVar.tabela.put("x", yVinc);
        System.out.println(aConsVar.get("x").tipo);
        System.out.println(aConsVar.tamanho());
        aConsVar.delete("x");
        System.out.println(aConsVar.tamanho());*/
        
        /*
        // Insere a "função" "calculaSoma" do tipo "real" na tabela de símbolos
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
     * Dada uma instância de TBase (sintático) retorna uma equivalente do tipo
     * TipoBaseSemantico (semântico).
     * 
     * @param b
     * @return
     */
    private TipoBaseSemantico converteParaSemantico(TBase b) {
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
     * Dado um TBase (sintático) e uma dimensão, retorna um equivalente
     * do tipo TipoArraySemantico (semântico).
     * 
     * @param b Tipo base sintático (sintaxe abstrata)
     * @param d Dimensão do array
     * @return  TipoArraySemantico
     */
    private TipoArraySemantico converteParaSemantico(TBase b, int d) {
        return new TipoArraySemantico(converteParaSemantico(b), d);
    }

    @Override
    public Object visitAssign(ASSIGN a) {
        // TODO Auto-generated method stub
        TipoSemantico tVar = (TipoSemantico) a.var.accept(this);
        TipoSemantico tExp = (TipoSemantico) a.exp.accept(this);
        
        /*if (tVar.equals(TipoBaseSemantico.Real))
            tExp = (TipoSemantico) (a.exp = new IntParaReal(a.exp)).accept(this);
        
        */
            
        return null;
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
                if (esq.tipo.equals(dir.tipo) && esq.tipo.nome != "bool")
                    tipoRetorno = esq.tipo;
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
                if (esq.tipo.equals(dir.tipo))
                    tipoRetorno = esq.tipo;
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
                if (esq.tipo.equals(dir.tipo) && esq.tipo.nome == "bool")
                    tipoRetorno = esq.tipo;
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
        for (DVarConsCom c : b.lista)
            c.accept(this);
        aConsVar.terminaEscopo();
        return null;
    }

    @Override
    public Object visitBlocoExp(BlocoExp b) {
        aConsVar.comecaEscopo();
        for (DCons d : b.listaCons)
            d.accept(this);
        TipoSemantico t = (TipoSemantico) b.exp.accept(this);
        aConsVar.terminaEscopo();
        return t;
    }

    @Override
    public Object visitChamada(CHAMADA c) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visitChamadaExp(ChamadaExp c) {
        // TODO Auto-generated method stub
        return null;
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
        // FIXME Esse método ta bugado, só um esboço
        // Obtém os tipos da lista de parâmetros
        List<VinculavelConsVar> listaParamVinculavel = new ArrayList<VinculavelConsVar>();
        for (Parametro p : f.listaParam)
            listaParamVinculavel.add((VinculavelConsVar) p.accept(this));
        
        // Obtém o tipo de retorno da função
        TipoSemantico retorno = (TipoSemantico) f.tipo.accept(this); 
        
        
        List<TipoSemantico> passagemParam = new ArrayList<TipoSemantico>();
        
        aFuncProc.lookupFuncProc(f.id, passagemParam, retorno);
        return retorno;
    }

    @Override
    public Object visitIf(IF i) {
        TipoSemantico t = (TipoSemantico) i.exp.accept(this);
        if (! t.equals(TipoBaseSemantico.Bool))
            erros.reportar(203, "IF com expressão do tipo " + t + " inválido.");
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
         if (t.equals(TipoBaseSemantico.Int) || t.equals(TipoBaseSemantico.Real))
             return t;
         else {
             erros.reportar(101, "Operação uniária de Menos inválida para " + t);
             return TipoBaseSemantico.Int;
         }
    }

    @Override
    public Object visitNao(Nao n) {
        TipoSemantico t = (TipoSemantico) n.exp.accept(this);
        if (t.equals(TipoBaseSemantico.Bool))
            return t;
        else {
            erros.reportar(102, "Operação unária de Negação inválida para " + t);
            return TipoBaseSemantico.Int;
        }
    }

    @Override
    public Object visitParArrayCopia(ParArrayCopia p) {
        aConsVar.add(p.id, true, converteParaSemantico(p.tipo, p.dimensao));
        return null;
    }

    @Override
    public Object visitParArrayRef(ParArrayRef p) {
        aConsVar.add(p.id, true, converteParaSemantico(p.tipo, p.dimensao));
        return null;
    }

    @Override
    public Object visitParBaseCopia(ParBaseCopia p) {
        aConsVar.add(p.id, true, converteParaSemantico(p.tipo));
        return null;
    }

    @Override
    public Object visitParBaseRef(ParBaseRef p) {
        aConsVar.add(p.id, true, converteParaSemantico(p.tipo));
        return null;
    }

    @Override
    public Object visitProcedimento(Procedimento p) {
        if (! aFuncProc.contem(p.id)) {
            List<PassagemTipoSemantico> l = new ArrayList<PassagemTipoSemantico>();
            /**
             * TODO
             * O for abaixo não esta 100% correto
             * Converter a lista de parametros sintática para semântica, ex:
             * l.add(new PassagemTipoSemantico(TipoBaseSemantico.Real));
             */
            for (Parametro param : p.listaParam)
                l.add( (PassagemTipoSemantico) param.accept(this) );
            
            aConsVar.comecaEscopo();
            aFuncProc.lookupFuncProc(p.id, l);
            p.comando.accept(this);
            aConsVar.terminaEscopo();
        }
        else erros.reportar(200, "O identificador " + p.id + " já está no ambiente.");
        
        return null;
    }
    
    @Override
    public Object visitPrograma(Programa p) {
        for (Dec d : p.declaracoes)
            d.accept(this);
        return null;
    }

    @Override
    public Object visitSimples(Simples s) {
        VinculavelConsVar v = aConsVar.get(s.id);
        // Se for nulo reporta o erro e assume uma variável do tipo inteiro
        if (v == null) {
            erros.reportar(404, "Identificador " + s.id + " não encontrado no ambiente!");
            v = new VinculavelConsVar(true, TipoBaseSemantico.Int);
        }
        return v;
    }

    @Override
    public Object visitTipoArray(TipoArray t) {
        TipoArraySemantico s = new TipoArraySemantico(
            this.converteParaSemantico(t.base),
            t.exp.size()
        );
        return s; 
    }

    @Override
    public Object visitTipoBase(TipoBase t) {
        return this.converteParaSemantico(t.tipo);
    }

    @Override
    public Object visitVarExp(VarExp v) {
        return v.var.accept(this);
    }

    @Override
    public Object visitVarInic(VarInic v) {
        TipoSemantico t = (TipoSemantico) v.nomeTipo.accept(this);
        if (! aConsVar.add(v.id, true, t))
            erros.reportar(200, "O identificador " + v.id + " não foi adicionado no ambiente.");
        return null;
    }

    @Override
    public Object visitVarInicComp(VarInicComp v) {
        TipoSemantico t = (TipoSemantico) v.nomeTipo.accept(this);
        if (! aConsVar.add(v.id, true, t))
            erros.reportar(200, "O identificador " + v.id + " não foi adicionado no ambiente.");
        return null;
    }

    @Override
    public Object visitVarInicExt(VarInicExt v) {
        TipoSemantico t = (TipoSemantico) v.nomeTipo.accept(this);
        if (! aConsVar.add(v.id, true, t))
            erros.reportar(200, "O identificador " + v.id + " não foi adicionado no ambiente.");
        return null;
    }

    @Override
    public Object visitVarNaoInic(VarNaoInic v) {
        TipoSemantico t = (TipoSemantico) v.nomeTipo.accept(this);
        if (! aConsVar.add(v.id, true, t))
            erros.reportar(200, "O identificador " + v.id + " não foi adicionado no ambiente.");
        return null;
    }

    @Override
    public Object visitWhile(WHILE w) {
        TipoSemantico t = (TipoSemantico) w.exp.accept(this);
        if (! t.equals(TipoBaseSemantico.Bool))
            erros.reportar(103, "A expressão deve ser do tipo bool");
        w.comando.accept(this);
        return null;
    }
}
