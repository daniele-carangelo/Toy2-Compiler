package esercitazione5.Expression.Operation;

import esercitazione5.Expression.Expr;
import esercitazione5.visitor.Visitor;

public class UminusOp extends Expr {

    private Expr expr;

    public UminusOp(Expr expr){
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
