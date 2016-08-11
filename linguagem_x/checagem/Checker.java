package checagem;

import java.util.Map;
import sintaxe_abstrata.*;
import ambiente.*;


public class Checker extends Visitor {
    Map<String, Tipo> tabela;
    public AmbienteConsVar aConsVar = new AmbienteConsVar();
    
    public Checker() {
        VinculavelConsVar xVinc = (VinculavelConsVar) aConsVar.getConsVar("x", true, TipoBaseSemantico.Real);
        VinculavelConsVar yVinc = (VinculavelConsVar) aConsVar.getConsVar("y", true, TipoBaseSemantico.Int);
        VinculavelConsVar vet1Vinc = (VinculavelConsVar) aConsVar.getConsVar(
            "vet1", true, new TipoArraySemantico(TipoBaseSemantico.Real, 3)
        );
        VinculavelConsVar vet2Vinc = (VinculavelConsVar) aConsVar.getConsVar(
            "vet2", true, new TipoArraySemantico(TipoBaseSemantico.Real, 4)
        );
        VinculavelConsVar vet3Vinc = (VinculavelConsVar) aConsVar.getConsVar(
            "vet2", true, new TipoArraySemantico(TipoBaseSemantico.Real, 4)
        );
        if (vet2Vinc.equals(vet3Vinc)) {
            System.out.println("é igual");
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
        
        switch (e.operacao.token) {
            case "+":
            case "-":
            case "*":
            case "/":
                TipoBase esq = (TipoBase) e.expEsq.accept(this);
                TipoBase dir = (TipoBase) e.expDir.accept(this);
                if (esq.base.nome == "int" && dir.base.nome == "int")
                    tipoRetorno = TBase.Int;
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
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visitDecCons(DecCons d) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visitDecVar(DecVar d) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visitDV(DV d) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visitFuncao(Funcao f) {
        // TODO Auto-generated method stub
        return null;
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visitLiteralInt(LiteralInt l) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visitMenos(Menos m) {
        return m.exp.accept(this);
    }

    @Override
    public Object visitNao(Nao n) {
        TipoBase b = (TipoBase) n.exp.accept(this);
        if (b.base == TBase.Bool){
            return ! (Boolean) n.exp.accept(this);
        } else {
            try {
                throw new Exception("BinExp: Tipos invalidos!");
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
    public Object visitSimples(Simples s) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visitTipoArray(TipoArray t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visitTipoBase(TipoBase t) {
        return t.base.nome;
    }

    @Override
    public Object visitVarExp(VarExp v) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visitVarInic(VarInic v) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visitVarInicComp(VarInicComp v) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visitVarInicExt(VarInicExt v) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visitVarNaoInic(VarNaoInic v) {
        tabela.put(v.id, v.nomeTipo);
        return null;
    }

    @Override
    public Object visitWhile(WHILE w) {
        while ((Boolean) w.exp.accept(this)){
            w.comando.accept(this);
        }
        return null;
    }
}
