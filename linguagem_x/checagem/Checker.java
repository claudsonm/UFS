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
         * FIXME
         * O código abaixo é apenas para fins de exemplo, não tem significância nenhuma 
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
        
        /**
         * END-FIXME
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

    @Override
    public Object visitAssign(ASSIGN a) {
        // TODO Auto-generated method stub
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
        for (DVarConsCom c : b.lista)
            c.accept(this);
        return null;
    }

    @Override
    public Object visitBlocoExp(BlocoExp b) {
        // TODO Auto-generated method stub
        return null;
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
        return c.comando.accept(this);
    }

    @Override
    public Object visitCons(Cons c) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visitConsComp(ConsComp c) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visitConsExt(ConsExt c) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visitDC(DC d) {
        return d.cons.accept(this);
    }

    @Override
    public Object visitDecCons(DecCons d) {
        return d.cons.accept(this);
    }

    @Override
    public Object visitDecVar(DecVar d) {
        return d.var.accept(this);
    }

    @Override
    public Object visitDV(DV d) {
        return d.var.accept(this);
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
        Boolean r = (Boolean) i.exp.accept(this);
        if (r) return i.comandoVerdade.accept(this);
        return i.comandoFalso.accept(this);
    }

    @Override
    public Object visitIndexada(Indexada i) {
        return i.var.accept(this);
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visitParArrayRef(ParArrayRef p) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visitParBaseCopia(ParBaseCopia p) {
        // TODO Auto-generated method stub        
        return null;
    }

    @Override
    public Object visitParBaseRef(ParBaseRef p) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visitProcedimento(Procedimento p) {
        // TODO Auto-generated method stub
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
