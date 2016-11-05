package checagem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import sintaxe_abstrata.*;
import ambiente.*;
import interpretador.*;
import utilitarios.*;


public class Checker extends Visitor {
    
    public AmbienteConsVar aConsVar = new AmbienteConsVar();
    public AmbienteFuncProc aFuncProc = new AmbienteFuncProc();
    public RegistroErros erros = new RegistroErros();
    private HashMap<String, Endereco> endFrame = new HashMap<>();
    private HashMap<String, Endereco> endGlobal = new HashMap<>();
    
    /**
     * Dada uma instância de TBase (sintático) retorna uma equivalente do tipo
     * TipoBaseSemantico (semântico).
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
     * Dado um TBase (sintático) e uma dimensão, retorna um equivalente
     * do tipo TipoArraySemantico (semântico).
     * 
     * @param b Tipo base sintático (sintaxe abstrata)
     * @param d Dimensão do array
     * @return  TipoArraySemantico
     */
    private TipoArraySemantico paraSemantico(TBase b, int d) {
        return new TipoArraySemantico(paraSemantico(b), d);
    }
    
    /**
     * Gera o endereço que deve ser ornamentado sem se importar com o tipo
     * 
     * @param nivel O nível em que está o identificador
     * @return
     */
    private Endereco gerarEndereco(int nivel) {
        return gerarEndereco(nivel, null);
    }
    
    /**
     * Gera o endereço que deve ser ornamentado
     * 
     * @param nivel O nível em que está o identificador
     * @param tipo  Se é um endereço para Int, Real ou Bool
     * @return
     */
    private Endereco gerarEndereco(int nivel, String tipo) {
        String local = (nivel == 0) ? "global" : "pilha";
        Endereco e = new Endereco(local, Alocador.proxEndereco(local), tipo);
        Alocador.alocar(local);
        return e;
    }
    
    private void salvarReferencia(String id, Endereco e) {
        if (e.local.equals("pilha")) {
            endFrame.put(id, e);
        }
        else {
            endGlobal.put(id, e);
        }
    }
    
    private Endereco obterEndereco(String id) {
        if (endFrame.containsKey(id)) {
            return endFrame.get(id);
        }
        else if (endGlobal.containsKey(id)) {
            return endGlobal.get(id);
        }
        else {
            return null;
        }
    }

    @Override
    public Object visitAssign(ASSIGN a) {
        VinculavelConsVar tVar = (VinculavelConsVar) a.var.accept(this);
        TipoSemantico tExp;
        Object rExp = a.exp.accept(this);
        if (rExp instanceof VinculavelConsVar) {
            tExp = ((VinculavelConsVar) rExp).tipo;
        }
        else {
            tExp = (TipoSemantico) rExp;
        }
        
        if (! tVar.isVar) {
            erros.reportar(303, "Impossível realizar atribuição em constante");
        }
        if (tVar.tipo.equals(TipoBaseSemantico.Real)
                && tExp.equals(TipoBaseSemantico.Int)) {
            a.exp = new IntParaReal(a.exp);
            tExp = (TipoSemantico) a.exp.accept(this);
        }
        if (! tVar.tipo.equals(tExp)) {
            erros.reportar(400, "Não é possível atribuir " + tExp + " em "
                    + tVar.tipo);
        }
            
        return tVar.tipo;
    }

    @Override
    public Object visitBinExp(BinExp e) {
        TipoSemantico tipoRetorno = null, tEsq, tDir;
        Object esq = e.expEsq.accept(this);
        Object dir = e.expDir.accept(this);
        
        tEsq = (esq instanceof VinculavelConsVar)
                ? ((VinculavelConsVar) esq).tipo
                : (TipoSemantico) esq;
        
        tDir = (dir instanceof VinculavelConsVar)
                ? ((VinculavelConsVar) dir).tipo
                : (TipoSemantico) dir;
        
        switch (e.operacao.token) {
        case "+":
        case "-":
        case "*":
        case "/":
        case "%":
            if (tEsq instanceof TipoArraySemantico
                    || tDir instanceof TipoArraySemantico) {
                // Algum lado da operação é a instância completa de um array
                erros.reportar(405, "Operação [" + e.operacao.token + "] não "
                        + "permitida com a instância completa de um array.");
                // Retornando int apenas para prosseguir a checagem sem erros
                tipoRetorno = TipoBaseSemantico.Int;
            }
            else if (tEsq.equals(TipoBaseSemantico.Bool)
                    || tDir.equals(TipoBaseSemantico.Bool)) {
                // Algum lado da operação é boolean
                erros.reportar(101, "Operação [" + e.operacao.token + "] não"
                        + " permitida com booleanos.");
                // Retornando int apenas para prosseguir a checagem sem erros
                tipoRetorno = TipoBaseSemantico.Int;
            }
            else {
                // Ambos os lados são numéricos
                if (tEsq.equals(tDir)) {
                    tipoRetorno = tEsq;
                }
                else if (tEsq.equals(TipoBaseSemantico.Real)
                        && tDir.equals(TipoBaseSemantico.Int)) {
                    e.expDir = new IntParaReal(e.expDir);
                    tipoRetorno = TipoBaseSemantico.Real;
                }
                else {
                    e.expEsq = new IntParaReal(e.expEsq);
                    tipoRetorno = TipoBaseSemantico.Real;
                }
            }
            break;
        
        case "<":
        case "=":
            if (tEsq.equals(tDir)) {
                tipoRetorno = TipoBaseSemantico.Bool;
            }
            else if (tEsq.equals(TipoBaseSemantico.Real)
                    && tDir.equals(TipoBaseSemantico.Int)) {
                e.expDir = new IntParaReal(e.expDir);
                tipoRetorno = TipoBaseSemantico.Bool;
            }
            else if (tEsq.equals(TipoBaseSemantico.Int)
                    && tDir.equals(TipoBaseSemantico.Real)) {
                e.expEsq = new IntParaReal(e.expEsq);
                tipoRetorno = TipoBaseSemantico.Bool;
            }
            else {
                erros.reportar(101, "Impossível realizar a operação ["
                        + e.operacao.token + "] entre os tipos" + tEsq
                        + " e " + tDir); 
                // Retornando bool apenas para prosseguir a checagem sem erros
                tipoRetorno = TipoBaseSemantico.Bool;
            }
            break;
        
        case "and":
        case "or":
            if (tEsq.equals(tDir) && tEsq.equals(TipoBaseSemantico.Bool)) {
                tipoRetorno = tEsq;
            }
            else {
                erros.reportar(101, "Impossível realizar a operação ["
                        + e.operacao.token + "] entre os tipos" + tEsq
                        + " e " + tDir);
                // Retornando bool apenas para prosseguir a checagem sem erros
                tipoRetorno = TipoBaseSemantico.Bool;
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
        
        if (vinculo == null) {
            erros.reportar(404, "Procedimento " + c.id + " não encontrado no "
                    + "ambiente!");
        }
        else {
            if (vinculo.isFunc) {
                erros.reportar(001, "O identificador chamado é uma função,"
                        + " utilize CHAMADA apenas com procedimentos.");
            }
            if (c.listaExp.size() != vinculo.paramProc.size()){
                erros.reportar(002, "O número de parâmetros da chamada é "
                        + "diferente dos parâmetros da assinatura do "
                        + "procedimento.");
            }
            else {
                PassagemTipoSemantico p1; // parâmetro formal
                Object p2; // parâmetro real
                
                for (int i = 0, count = c.listaExp.size(); i < count; i++) {
                    p1 = vinculo.paramProc.get(i);
                    Exp e = c.listaExp.get(i);
                    p2 = e.accept(this);
                    
                    if (! p1.isCopia) {
                        // Se a passagem de parâmetros é por referência
                        if (p2 instanceof VinculavelConsVar) {
                            VinculavelConsVar v = (VinculavelConsVar) p2;
                            if (! v.isVar) {
                                erros.reportar(220, "O procedimento " + c.id
                                        + " exige passagem de parâmetros por"
                                        + "referência. Constantes são inválidas!");
                            }
                        }
                        else {
                            erros.reportar(333, "O procedimento " + c.id
                                    + "exige passagem de parâmetros por "
                                    + "referência. Para chamá-lo passe uma "
                                    + "variável.");
                        }
                    }
                    
                    if (p2 instanceof VinculavelConsVar) {
                        VinculavelConsVar v = (VinculavelConsVar) p2;
                        if (v.tipo.equals(TipoBaseSemantico.Int)
                                && p1.tipo.equals(TipoBaseSemantico.Real)) {
                            // Adiciona o TypeCast na hierarquia de classes
                            e = new IntParaReal(e);
                        }
                        else if (! v.tipo.equals(p1.tipo)) {
                            erros.reportar(202, "O parâmetro da chamada é "
                                    + "incompatível com o parâmetro da"
                                    + " assinatura do procedimento.");
                        }
                    }
                    else {
                        if ( ((TipoSemantico) p2).equals(TipoBaseSemantico.Int)
                                && p1.tipo.equals(TipoBaseSemantico.Real)) {
                            // Adiciona o TypeCast na hierarquia de classes
                            e = new IntParaReal(e);
                        }
                        else if (! p1.tipo.equals((TipoSemantico) p2)) {
                            erros.reportar(202, "O parâmetro da chamada é "
                                    + "incompatível com o parâmetro da assinatura");
                        }
                    }
                }
            }
        }
        
        return null;
    }

    @Override
    public Object visitChamadaExp(ChamadaExp c) {
        VinculavelFuncProc f = aFuncProc.get(c.id);
        
        if (f == null) {
            erros.reportar(404, "Função " + c.id + " não encontrada no "
                    + "ambiente!");
            return TipoBaseSemantico.Int;
        }
        else {
            if (! f.isFunc) {
                erros.reportar(001, "O identificador chamado é um procedimento,"
                        + " utilize ChamadaExp apenas com funções.");
            }
            if (c.listaExp.size() != f.paramFunc.size()){
                erros.reportar(002, "O número de parâmetros da chamada é "
                        + "diferente dos parâmetros da assinatura da "
                        + "função.");
            }
            else {
                PassagemTipoSemantico p1; // parâmetro formal
                Object p2; // parâmetro real
                
                for (int i = 0, count = c.listaExp.size(); i < count; i++) {
                    p1 = f.paramFunc.get(i);
                    Exp e = c.listaExp.get(i);
                    p2 = e.accept(this);
                    
                    if (p2 instanceof VinculavelConsVar) {
                        VinculavelConsVar v = (VinculavelConsVar) p2;
                        if (v.tipo.equals(TipoBaseSemantico.Int)
                                && p1.tipo.equals(TipoBaseSemantico.Real)) {
                            // Adiciona o TypeCast na hierarquia de classes
                            e = new IntParaReal(e);
                        }
                        else if (! v.tipo.equals(p1.tipo)) {
                            erros.reportar(202, "O parâmetro da chamada é "
                                    + "incompatível com o parâmetro da"
                                    + " assinatura da função.");
                        }
                    }
                    else {
                        if ( ((TipoSemantico) p2).equals(TipoBaseSemantico.Int)
                                && p1.tipo.equals(TipoBaseSemantico.Real)) {
                            // Adiciona o TypeCast na hierarquia de classes
                            e = new IntParaReal(e);
                        }
                        else if (! p1.tipo.equals((TipoSemantico) p2)) {
                            erros.reportar(202, "O parâmetro da chamada é "
                                    + "incompatível com o parâmetro da assinatura"
                                    + " da função");
                        }
                    }
                }
            }
        }
        
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
        c.endereco = gerarEndereco(AmbienteConsVar.nivel);
        salvarReferencia(c.id, c.endereco);
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
        if (! aFuncProc.contem(f.id)) {
            TipoSemantico retorno = (TipoSemantico) f.tipo.accept(this);
            List<PassagemTipoSemantico> l = new ArrayList<PassagemTipoSemantico>();
            aConsVar.comecaEscopo();
            Alocador.resetar();
            endFrame.clear();
            for (Parametro p : f.listaParam) {
                l.add((PassagemTipoSemantico) p.accept(this));
                Alocador.alocar("pilha");
            }
            TipoSemantico r2 = (TipoSemantico) f.exp.accept(this);
            if (! retorno.equals(r2)) {
                erros.reportar(201, "O tipo de retorno da função não é o mesmo"
                        + " retornado pela expressão.");
            }
            aConsVar.terminaEscopo();
            aFuncProc.lookupFuncProc(f.id, l, retorno);
            return retorno;
        }
        else {
            erros.reportar(200, "A função " + f.id + " já está no ambiente.");
            return null;
        }
    }

    @Override
    public Object visitIf(IF i) {
        TipoSemantico t = (TipoSemantico) i.exp.accept(this);
        if (! t.equals(TipoBaseSemantico.Bool)) {
            erros.reportar(203, "IF com expressão do tipo " + t + " inválido.");
        }
        i.comandoVerdade.accept(this);
        i.comandoFalso.accept(this);
        return null;
    }

    @Override
    public Object visitIndexada(Indexada i) {
        TipoSemantico t = ((VinculavelConsVar) i.var.accept(this)).tipo;
        
        if (t instanceof TipoBaseSemantico) {
            erros.reportar(303, "Não é possível realizar a indexação em"
                    + " identificadores do tipo base, apenas arrays!");
        }
        else {
            // Variável com as informações de tipo e dimensão do array
            TipoArraySemantico t1 = (TipoArraySemantico) t;
            
            // Verifica se o tipo da expressão do índice é válida
            Object rIndice = i.exp.accept(this);
            TipoSemantico tipo = (rIndice instanceof VinculavelConsVar) ?
                                 ((VinculavelConsVar) rIndice).tipo :
                                 (TipoBaseSemantico) rIndice;
            
            if (! tipo.equals(TipoBaseSemantico.Int)) {
                erros.reportar(101, "O tipo " + tipo + " não é um índice "
                        + "válido!");
            }
            
            // TODO Verificar se a dimensão é válida
            
            // Define o retorno como sendo o tipo dos elementos do array
            t = t1.tipo;
        }
        return t;
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
             erros.reportar(101, "Operação uniária de Menos inválida para " + t);
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
            erros.reportar(102, "Operação unária de Negação inválida para " + t);
            return TipoBaseSemantico.Bool;
        }
    }

    @Override
    public Object visitParArrayCopia(ParArrayCopia p) {
        aConsVar.add(p.id, true, paraSemantico(p.tipo, p.dimensao));
        return new PassagemTipoSemantico(paraSemantico(p.tipo, p.dimensao));
    }

    @Override
    public Object visitParArrayRef(ParArrayRef p) {
        aConsVar.add(p.id, true, paraSemantico(p.tipo, p.dimensao));
        return new PassagemTipoSemantico(paraSemantico(p.tipo, p.dimensao), false);
    }

    @Override
    public Object visitParBaseCopia(ParBaseCopia p) {
        aConsVar.add(p.id, true, paraSemantico(p.tipo));
        Endereco e = new Endereco("pilha", Alocador.proxEndereco("pilha"));
        salvarReferencia(p.id, e);
        return new PassagemTipoSemantico(paraSemantico(p.tipo));
    }

    @Override
    public Object visitParBaseRef(ParBaseRef p) {
        aConsVar.add(p.id, true, paraSemantico(p.tipo));
        return new PassagemTipoSemantico(paraSemantico(p.tipo), false);
    }

    @Override
    public Object visitProcedimento(Procedimento p) {
        if (! aFuncProc.contem(p.id)) {
            List<PassagemTipoSemantico> l = new ArrayList<PassagemTipoSemantico>();
            aConsVar.comecaEscopo();
            Alocador.resetar();
            endFrame.clear();
            for (Parametro param : p.listaParam) {
                l.add((PassagemTipoSemantico) param.accept(this));
            }
            p.comando.accept(this);
            aConsVar.terminaEscopo();
            aFuncProc.lookupFuncProc(p.id, l);
        }
        else {
            erros.reportar(200, "O procedimento " + p.id + " já está no ambiente.");
        }
        
        return null;
    }
    
    @Override
    public Object visitPrograma(Programa p) {
        for (Dec d : p.declaracoes) {
            d.accept(this);
        }
        return p;
    }

    @Override
    public Object visitSimples(Simples s) {
        VinculavelConsVar v = aConsVar.get(s.id);
        // Se for nulo reporta o erro e assume uma variável do tipo inteiro
        if (v == null) {
            erros.reportar(404, "Identificador " + s.id
                    + " não encontrado no ambiente!");
            v = new VinculavelConsVar(true, TipoBaseSemantico.Int);
        }
        s.endereco = obterEndereco(s.id);
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
                    + " não foi adicionado no ambiente.");
        }
        v.endereco = gerarEndereco(AmbienteConsVar.nivel);
        salvarReferencia(v.id, v.endereco);
        v.exp.accept(this);
        return null;
    }

    @Override
    public Object visitVarInicComp(VarInicComp v) {
        TipoSemantico t = (TipoSemantico) v.nomeTipo.accept(this);
        if (! aConsVar.add(v.id, true, t)) {
            erros.reportar(200, "O identificador " + v.id
                    + " não foi adicionado no ambiente.");
        }
        return null;
    }

    @Override
    public Object visitVarInicExt(VarInicExt v) {
        TipoSemantico t = (TipoSemantico) v.nomeTipo.accept(this);
        if (! aConsVar.add(v.id, true, t)) {
            erros.reportar(200, "O identificador " + v.id
                    + " não foi adicionado no ambiente.");
        }
        return null;
    }

    @Override
    public Object visitVarNaoInic(VarNaoInic v) {
        TipoSemantico t = (TipoSemantico) v.nomeTipo.accept(this);
        if (! aConsVar.add(v.id, true, t)) {
            erros.reportar(200, "O identificador " + v.id
                    + " não foi adicionado no ambiente.");
        }
        salvarReferencia(v.id, gerarEndereco(AmbienteConsVar.nivel, t.toString()));
        return null;
    }

    @Override
    public Object visitWhile(WHILE w) {
        TipoSemantico t = (TipoSemantico) w.exp.accept(this);
        if (! t.equals(TipoBaseSemantico.Bool)) {
            erros.reportar(103, "A condição do WHILE deve ser do tipo bool."
                    + " Um " + t + " foi passado.");
        }
        w.comando.accept(this);
        return null;
    }
}
