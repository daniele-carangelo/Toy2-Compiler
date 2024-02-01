package esercitazione4.Statement;

import esercitazione4.Expression.Expr;

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
}
