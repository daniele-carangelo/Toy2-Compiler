package esercitazione5.Expression.RelOp;

import esercitazione5.Expression.Expr;
import esercitazione5.visitor.Visitor;

public class NotOp extends Expr {

    private Expr expr;

    public NotOp(Expr expr){
        this.expr = expr;
        super.type = "not";
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
