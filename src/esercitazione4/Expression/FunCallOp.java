package esercitazione4.Expression;

import esercitazione4.visitor.Visitor;

import java.util.LinkedList;

public class FunCallOp extends Expr{

    private IdOp id;
    private LinkedList<Expr> exprs;

    public FunCallOp(IdOp id, LinkedList<Expr> exprs){
        this.id = id;
        this.exprs = exprs;
    }

    public IdOp getId() {
        return id;
    }

    public void setId(IdOp id) {
        this.id = id;
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
