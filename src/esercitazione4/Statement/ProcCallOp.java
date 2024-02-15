package esercitazione4.Statement;

import esercitazione4.Expression.IdOp;

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
}
