package esercitazione4.Expression.RelOp;

import esercitazione4.Expression.Expr;
import esercitazione4.visitor.Visitor;

public class NotOp extends Expr {

    private Expr expr;

    public NotOp(Expr expr){
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
