package checagem;

import java.util.ArrayList;
import java.util.List;
import sintaxe_abstrata.*;
import ambiente.*;


public class Checker extends Visitor {
    public AmbienteConsVar aConsVar = new AmbienteConsVar();
    public AmbienteFuncProc aFuncProc = new AmbienteFuncProc();
    
    public Checker() {
        /**
         * FIXME
         * O código abaixo é apenas para fins de exemplo, não tem significância nenhuma 
         */
        // Insere a "variável" "x" do tipo "real" na tabela de símbolos
        VinculavelConsVar xVinc = (VinculavelConsVar) aConsVar.lookup("x", true, TipoBaseSemantico.Real);
        
        // Insere a "função" "calculaSoma" do tipo "real" na tabela de símbolos
        List<PassagemTipoSemantico> p = new ArrayList<PassagemTipoSemantico>();
        p.add(new PassagemTipoSemantico(TipoBaseSemantico.Real));
        VinculavelFuncProc x = (VinculavelFuncProc) aFuncProc.lookupFuncProc("calculaSoma", p);
        /**
         * END-FIXME
         */
    }
    
    /**
     * Dada uma instância de TBase (abstrato) retorna uma equivalente do tipo
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visitLiteralBool(LiteralBool l) {
        return l.valor;
    }

    @Override
    public Object visitLiteralInt(LiteralInt l) {
        return l.valor;
    }

    @Override
    public Object visitMenos(Menos m) {
        return m.exp.accept(this);
    }

    @Override
    public Object visitNao(Nao n) {
        TipoBase b = (TipoBase) n.exp.accept(this);
        if (b.tipo == TBase.Bool)
            return ! (Boolean) n.exp.accept(this);
        else {
            try {
                throw new Exception("Não: negação de " + b.tipo.nome + " não suportada!");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
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
        return aConsVar.get(s.id);
    }

    @Override
    public Object visitTipoArray(TipoArray t) {
        TipoArraySemantico s = new TipoArraySemantico(
            this.converteParaSemantico(t.base),
            3 // FIXME Determinar a dimensão
        );
        return s; 
    }

    @Override
    public Object visitTipoBase(TipoBase t) {
        return this.converteParaSemantico(t.tipo);
    }

    @Override
    public Object visitVarExp(VarExp v) {
        return v.accept(this);
    }

    @Override
    public Object visitVarInic(VarInic v) {
        TipoSemantico t = (TipoSemantico) v.nomeTipo.accept(this);
        return aConsVar.lookup(v.id, true, t);
    }

    @Override
    public Object visitVarInicComp(VarInicComp v) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visitVarInicExt(VarInicExt v) {
        // FIXME v.exp deve ser usada em algum momento...
        TipoSemantico t = (TipoSemantico) v.nomeTipo.accept(this);
        return aConsVar.lookup(v.id, true, t);
    }

    @Override
    public Object visitVarNaoInic(VarNaoInic v) {
        TipoSemantico t = (TipoSemantico) v.nomeTipo.accept(this);
        return aConsVar.lookup(v.id, true, t);
    }

    @Override
    public Object visitWhile(WHILE w) {
        while ((Boolean) w.exp.accept(this))
            w.comando.accept(this);
        return null;
    }
}
