package esercitazione4.Statement;

import esercitazione4.Expression.Expr;
import esercitazione4.visitor.Visitor;

import java.util.LinkedList;

public class ReadOp extends Statement{

    private LinkedList<Expr> exprs;


    public ReadOp(LinkedList<Expr> exprs){
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
