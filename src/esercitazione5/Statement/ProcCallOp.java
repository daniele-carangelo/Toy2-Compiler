package esercitazione5.Statement;

import esercitazione5.Expression.IdOp;
import esercitazione5.visitor.Visitor;

public class ProcCallOp extends Statement{

    private IdOp id;

    private ProcExprsOp procExprs;

    public ProcCallOp(IdOp id, ProcExprsOp procExprs){
        this.id = id;
        this.procExprs = procExprs;

    }

    public IdOp getId() {
        return id;
    }

    public void setId(IdOp id) {
        this.id = id;
    }

    public ProcExprsOp getProcExprs() {
        return procExprs;
    }

    public void setProcExprs(ProcExprsOp procExprs) {
        this.procExprs = procExprs;
    }

    public Object accept(Visitor v){
        return v.visit(this);
    }

}
