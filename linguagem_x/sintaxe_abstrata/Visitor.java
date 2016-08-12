package sintaxe_abstrata;

public abstract class Visitor {
    public abstract Object visitAssign(ASSIGN a);
    
    public abstract Object visitBinExp(BinExp e);
    
    public abstract Object visitBloco(BLOCO b);
    
    public abstract Object visitBlocoExp(BlocoExp b);
    
    public abstract Object visitChamada(CHAMADA c);
    
    public abstract Object visitChamadaExp(ChamadaExp c);
    
    public abstract Object visitCom(Com c);
    
    public abstract Object visitCons(Cons c);
    
    public abstract Object visitConsComp(ConsComp c);
    
    public abstract Object visitConsExt(ConsExt c);
    
    public abstract Object visitDC(DC d);
    
    public abstract Object visitDecCons(DecCons d);
    
    public abstract Object visitDecVar(DecVar d);
    
    public abstract Object visitDV(DV d);
    
    public abstract Object visitFuncao(Funcao f);
    
    //public abstract Object visitID(ID i);
    
    public abstract Object visitIf(IF i);
    
    public abstract Object visitIndexada(Indexada i);
    
    public abstract Object visitLiteralBool(LiteralBool l);
    
    public abstract Object visitLiteralInt(LiteralInt l);
    
    public abstract Object visitMenos(Menos m);
    
    public abstract Object visitNao(Nao n);
    
    public abstract Object visitParArrayCopia(ParArrayCopia p);
    
    public abstract Object visitParArrayRef(ParArrayRef p);
    
    public abstract Object visitParBaseCopia(ParBaseCopia p);
    
    public abstract Object visitParBaseRef(ParBaseRef p);
    
    public abstract Object visitProcedimento(Procedimento p);
    
    public abstract Object visitPrograma(Programa p);
    
    public abstract Object visitSimples(Simples s);
    
    public abstract Object visitTipoArray(TipoArray t);
    
    public abstract Object visitTipoBase(TipoBase t);
    
    public abstract Object visitVarExp(VarExp v);
    
    public abstract Object visitVarInic(VarInic v);
    
    public abstract Object visitVarInicComp(VarInicComp v);
    
    public abstract Object visitVarInicExt(VarInicExt v);
    
    public abstract Object visitVarNaoInic(VarNaoInic v);
    
    public abstract Object visitWhile(WHILE w);
    
}
