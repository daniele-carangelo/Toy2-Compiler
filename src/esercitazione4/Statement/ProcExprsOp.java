package esercitazione4.Statement;

import esercitazione4.Expression.Expr;
import esercitazione4.Expression.IdOp;

import java.util.LinkedList;

public class ProcExprsOp {

    private LinkedList<Expr> exprs;
    private LinkedList<IdOp> refId;

    public ProcExprsOp(LinkedList<Expr> exprs, LinkedList<IdOp> refId){
        this.exprs = exprs;
        this.refId = refId;
    }

    public LinkedList<Expr> getExprs() {
        return exprs;
    }

    public void setExprs(LinkedList<Expr> exprs) {
        this.exprs = exprs;
    }

    public LinkedList<IdOp> getRefId() {
        return refId;
    }

    public void setRefId(LinkedList<IdOp> refId) {
        this.refId = refId;
    }
}
