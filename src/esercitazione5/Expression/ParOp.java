package esercitazione5.Expression;

import esercitazione5.visitor.Visitor;

public class ParOp extends Expr{

    private Expr expr;

    public ParOp(Expr expr){
        this.expr = expr;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }

    public Object accept(Visitor v){
        return v.visit(this);
    }
}
