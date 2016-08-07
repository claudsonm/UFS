package linguagem_x;

import java.util.Map;

public class Checker extends Visitor {
    Map<String, Tipo> tabela;

    @Override
    Object visitAssign(ASSIGN a) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitBinExp(BinExp e) {
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
    Object visitBloco(BLOCO b) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitBlocoExp(BlocoExp b) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitChamada(CHAMADA c) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitChamadaExp(ChamadaExp c) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitCom(Com c) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitCons(Cons c) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitConsComp(ConsComp c) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitConsExt(ConsExt c) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitDC(DC d) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitDecCons(DecCons d) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitDecVar(DecVar d) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitDV(DV d) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitFuncao(Funcao f) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitIf(IF i) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitIndexada(Indexada i) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitLiteralBool(LiteralBool l) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitLiteralInt(LiteralInt l) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitMenos(Menos m) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitNao(Nao n) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitParArrayCopia(ParArrayCopia p) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitParArrayRef(ParArrayRef p) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitParBaseCopia(ParBaseCopia p) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitParBaseRef(ParBaseRef p) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitProcedimento(Procedimento p) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitSimples(Simples s) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitTipoArray(TipoArray t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitTipoBase(TipoBase t) {
        return t.base.nome;
    }

    @Override
    Object visitVarExp(VarExp v) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitVarInic(VarInic v) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitVarInicComp(VarInicComp v) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitVarInicExt(VarInicExt v) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Object visitVarNaoInic(VarNaoInic v) {
        tabela.put(v.id, v.nomeTipo);
        return null;
    }

    @Override
    Object visitWhile(WHILE w) {
        // TODO Auto-generated method stub
        return null;
    }

}
