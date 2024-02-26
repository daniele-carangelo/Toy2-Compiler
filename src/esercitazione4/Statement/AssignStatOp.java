package esercitazione4.Statement;

import esercitazione4.Expression.Expr;
import esercitazione4.Expression.IdOp;
import esercitazione4.visitor.Visitor;

import java.util.LinkedList;

public class AssignStatOp extends Statement{

    private LinkedList<IdOp> ids;
    private LinkedList<Expr> exprs;

    public AssignStatOp(LinkedList<IdOp> ids, LinkedList<Expr> exprs){
        this.ids = ids;
        this.exprs = exprs;
    }

    public LinkedList<IdOp> getIds() {
        return ids;
    }

    public void setIds(LinkedList<IdOp> ids) {
        this.ids = ids;
    }

    public LinkedList<Expr> getExprs() {
        return exprs;
    }

    public void setExprs(LinkedList<Expr> exprs) {
        this.exprs = exprs;
    }

    public Object accept(Visitor v){
        return v.visit(this);
    }

}
