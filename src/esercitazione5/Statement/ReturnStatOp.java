package esercitazione5.Statement;

import esercitazione5.Expression.Expr;
import esercitazione5.visitor.Visitor;

import java.util.LinkedList;

public class ReturnStatOp extends Statement{

    private LinkedList<Expr> exprs;

    public ReturnStatOp(LinkedList<Expr> exprs){
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

    @Override
    public String toString() {
        return "ReturnStatOp{" +
                "exprs=" + exprs +
                '}';
    }
}
