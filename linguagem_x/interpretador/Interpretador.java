package interpretador;

import java.util.ArrayList;

import sintaxe_abstrata.*;
import utilitarios.*;

public class Interpretador extends Visitor {
    
    public Memoria mem = new Memoria();
    public RegistroErros erros = new RegistroErros();
    
    /**
     * Carrega o valor no endereço de memória
     * 
     * @param e Endereço de memória
     * @param v Valor
     */
    private void putMemoria(Endereco e, Value v) {
        if (e.local.equals("pilha")) {
            mem.putPilha(e.posicao, v);
            Alocador.alocar("pilha");
        }
        else if (e.local.equals("global")) {
            mem.putGlobal(e.posicao, v);
            Alocador.alocar("global");
        }
        else {
            erros.reportar(333, "Endereço de memória inválido!");
        }
    }
    
    /**
     * Carrega um valor padrão em um endereço de memória
     * 
     * @param end   Endereço de memória
     * @return      O valor padrão carregado
     */
    private Value inicializeEndereco(Endereco end) {
        Value valor;
        switch (end.tipo) {
        case "Bool":
            valor = new BoolValue(false);
            break;
        
        case "Real":
            valor = new RealValue(0.0);
            break;
        
        default:
            valor = new IntValue(0);
            break;
        }
        putMemoria(end, valor);
        return valor;
    }

    @Override
    public Object visitAssign(ASSIGN a) {
        Value v = (Value) a.exp.accept(this);
        Endereco e = (Endereco) a.var.accept(this);
        putMemoria(e, v);
        System.out.println("RESULTADO: " + v);
        return null;
    }

    @Override
    public Object visitBinExp(BinExp e) {
        Value vEsq = (Value) e.expEsq.accept(this);
        Value vDir = (Value) e.expDir.accept(this);
        Value rValor = null;
        
        if (vEsq instanceof IntValue) {
            IntValue ve = (IntValue) vEsq;
            IntValue vd = (IntValue) vDir;
            switch (e.operacao.token) {
            case "+":
                rValor = new IntValue(ve.valor + vd.valor);
                break;
            
            case "-":
                rValor = new IntValue(ve.valor - vd.valor);
                break;
            
            case "*":
                rValor = new IntValue(ve.valor * vd.valor);
                break;
            
            case "/":
                rValor = new RealValue((double) ve.valor / (double) vd.valor);
                break;
            
            case "%":
                rValor = new IntValue(ve.valor % vd.valor);
                break;
            
            case "=":
                rValor = new BoolValue(ve.valor == vd.valor);
                break;
            
            case "<":
                rValor = new BoolValue(ve.valor < vd.valor);
                break;
            
            case ">":
                rValor = new BoolValue(ve.valor > vd.valor);
                break;
            }
        }
        else if (vEsq instanceof RealValue) {
            RealValue ve = (RealValue) vEsq;
            RealValue vd = vDir instanceof IntValue ?
                           new RealValue(((IntValue) vDir).valor) :
                           (RealValue) vDir;
            switch (e.operacao.token) {
            case "+":
                rValor = new RealValue(ve.valor + vd.valor);
                break;
            
            case "-":
                rValor = new RealValue(ve.valor - vd.valor);
                break;
            
            case "*":
                rValor = new RealValue(ve.valor * vd.valor);
                break;
            
            case "/":
                rValor = new RealValue(ve.valor / vd.valor);
                break;
            
            case "%":
                rValor = new RealValue(ve.valor % vd.valor);
                break;
            
            case "=":
                rValor = new BoolValue(ve.valor == vd.valor);
                break;
            
            case "<":
                rValor = new BoolValue(ve.valor < vd.valor);
                break;
            
            case ">":
                rValor = new BoolValue(ve.valor > vd.valor);
                break;
            }
        }
        else {
            BoolValue ve = (BoolValue) vEsq;
            BoolValue vd = (BoolValue) vDir;
            switch (e.operacao.token) {
            case "AND":
                rValor = new BoolValue(ve.valor && vd.valor);
                break;
            
            case "OR":
                rValor = new BoolValue(ve.valor || vd.valor);
                break;
            }
        }
        return rValor;
    }

    @Override
    public Object visitBloco(BLOCO b) {
        for (DVarConsCom c : b.lista) {
            c.accept(this);
        }
        return null;
    }

    @Override
    public Object visitBlocoExp(BlocoExp b) {
        for (DCons d : b.listaCons) {
            d.accept(this);
        }
        return null;
    }

    @Override
    public Object visitChamada(CHAMADA c) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visitChamadaExp(ChamadaExp c) {
        Object[] funcProc = mem.getDeclaracao(c.id);
        ArrayList<Value> v = new ArrayList<>();
        int i = 0;
        for (Exp e : c.listaExp) {
            v.add((Value) e.accept(this));
            i++;
        }
        mem.novoFrame();
        for (int j = 0; j < i; j++) {
            putMemoria(new Endereco("pilha", j), v.get(j));
        }
        Value valor = (Value) ((Exp)funcProc[0]).accept(this);
        mem.removerFrame();
        return valor;
    }

    @Override
    public Object visitCom(Com c) {
        c.comando.accept(this);
        return null;
    }

    @Override
    public Object visitCons(Cons c) {
        Value v = (Value) c.exp.accept(this);
        putMemoria(c.endereco, v);
        return null;
    }

    @Override
    public Object visitConsComp(ConsComp c) {
        return null;
    }

    @Override
    public Object visitConsExt(ConsExt c) {
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
        Object[] conteudo = new Object[2];
        conteudo[0] = f.exp;
        conteudo[1] = f.listaParam;
        mem.putDeclaracao(f.id, conteudo);
        return null;
    }

    @Override
    public Object visitIf(IF i) {
        BoolValue b = (BoolValue) i.exp.accept(this);
        if (b.valor) {
            i.comandoVerdade.accept(this);
        }
        else {
            i.comandoFalso.accept(this);
        }
        return null;
    }

    @Override
    public Object visitIndexada(Indexada i) {
        return null;
    }

    @Override
    public Object visitIntParaReal(IntParaReal i) {
        IntValue v = (IntValue) i.exp.accept(this);
        return new RealValue((double) v.valor);
    }

    @Override
    public Object visitLiteralBool(LiteralBool l) {
        return new BoolValue(l.valor);
    }

    @Override
    public Object visitLiteralInt(LiteralInt l) {
        return new IntValue(l.valor);
    }

    @Override
    public Object visitLiteralReal(LiteralReal l) {
        return new RealValue(l.valor);
    }

    @Override
    public Object visitMenos(Menos m) {
        Value v = (Value) m.exp.accept(this);
        if (v instanceof IntValue) {
            IntValue vInt = (IntValue) v;
            vInt.valor = - vInt.valor;
            return vInt;
        }
        else {
            RealValue vReal = (RealValue) v;
            vReal.valor = - vReal.valor;
            return vReal;
        }
    }

    @Override
    public Object visitNao(Nao n) {
        BoolValue b = (BoolValue) n.exp.accept(this);
        b.valor = ! b.valor;
        return b;
    }

    @Override
    public Object visitParArrayCopia(ParArrayCopia p) {
        return null;
    }

    @Override
    public Object visitParArrayRef(ParArrayRef p) {
        return null;
    }

    @Override
    public Object visitParBaseCopia(ParBaseCopia p) {
        return null;
    }

    @Override
    public Object visitParBaseRef(ParBaseRef p) {
        return null;
    }

    @Override
    public Object visitProcedimento(Procedimento p) {
        if (! p.id.equals("main")) {
            Object[] conteudo = new Object[2];
            conteudo[0] = p.comando;
            conteudo[1] = p.listaParam;
            mem.putDeclaracao(p.id, conteudo);
        }
        else {
            p.comando.accept(this);
        }
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
        return s.endereco;
    }

    @Override
    public Object visitTipoArray(TipoArray t) {
        return null;
    }

    @Override
    public Object visitTipoBase(TipoBase t) {
        return null;
    }

    @Override
    public Object visitVarExp(VarExp v) {
        Endereco end = (Endereco) v.var.accept(this);
        Value r;
        if (end.local.equals("global")) {
            r = mem.getGlobal(end.posicao);
            if (r == null) {
                r = inicializeEndereco(end);
            }
            return r;
        }
        else {
            r = mem.getPilha(end.posicao);
            if (r == null) {
                r = inicializeEndereco(end);
            }
            return r;
        }
    }

    @Override
    public Object visitVarInic(VarInic v) {
        Value valor = (Value) v.exp.accept(this);
        putMemoria(v.endereco, valor);
        return null;
    }

    @Override
    public Object visitVarInicComp(VarInicComp v) {
        return null;
    }

    @Override
    public Object visitVarInicExt(VarInicExt v) {
        return null;
    }

    @Override
    public Object visitVarNaoInic(VarNaoInic v) {
        return null;
    }

    @Override
    public Object visitWhile(WHILE w) {
        while ( ((BoolValue) w.exp.accept(this)).valor ) {
            w.comando.accept(this);
        }
        return null;
    }

}
