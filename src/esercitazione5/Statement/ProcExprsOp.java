package esercitazione5.Statement;

import esercitazione5.Expression.Expr;
import esercitazione5.Expression.IdOp;
import esercitazione5.visitor.Visitor;

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

    public Object accept(Visitor v){
        return v.visit(this);
    }

}
