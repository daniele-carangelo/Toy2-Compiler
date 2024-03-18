package esercitazione5.Statement;

import esercitazione5.Expression.Expr;
import esercitazione5.Expression.IdOp;
import esercitazione5.visitor.Visitor;

import java.util.LinkedList;

public class ProcExprsOp {

    private LinkedList<Expr> exprs;


    public ProcExprsOp(LinkedList<Expr> exprs){
        this.exprs = exprs;

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
