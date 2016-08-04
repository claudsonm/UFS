package linguagem_x;

public abstract class Visitor {
    abstract Object visitAssign(ASSIGN a);
    
    abstract Object visitBinExp(BinExp e);
    
    abstract Object visitBloco(BLOCO b);
    
    abstract Object visitBlocoExp(BlocoExp b);
    
    abstract Object visitChamada(CHAMADA c);
    
    abstract Object visitChamadaExp(ChamadaExp c);
    
    abstract Object visitCom(Com c);
    
    abstract Object visitCons(Cons c);
    
    abstract Object visitConsComp(ConsComp c);
    
    abstract Object visitConsExt(ConsExt c);
    
    abstract Object visitDC(DC d);
    
    abstract Object visitDecCons(DecCons d);
    
    abstract Object visitDecVar(DecVar d);
    
    abstract Object visitDV(DV d);
    
    abstract Object visitFuncao(Funcao f);
    
    //abstract Object visitID(ID i);
    
    abstract Object visitIf(IF i);
    
    abstract Object visitIndexada(Indexada i);
    
    abstract Object visitLiteralBool(LiteralBool l);
    
    abstract Object visitLiteralInt(LiteralInt l);
    
    abstract Object visitMenos(Menos m);
    
    abstract Object visitNao(Nao n);
    
    abstract Object visitParArrayCopia(ParArrayCopia p);
    
    abstract Object visitParArrayRef(ParArrayRef p);
    
    abstract Object visitParBaseCopia(ParBaseCopia p);
    
    abstract Object visitParBaseRef(ParBaseRef p);
    
    abstract Object visitProcedimento(Procedimento p);
    
    abstract Object visitSimples(Simples s);
    
    abstract Object visitTipoArray(TipoArray t);
    
    abstract Object visitTipoBase(TipoBase t);
    
    abstract Object visitVarExp(VarExp v);
    
    abstract Object visitVarInic(VarInic v);
    
    abstract Object visitVarInicComp(VarInicComp v);
    
    abstract Object visitVarInicExt(VarInicExt v);
    
    abstract Object visitVarNaoInic(VarNaoInic v);
    
    abstract Object visitWhile(WHILE w);
    
}
