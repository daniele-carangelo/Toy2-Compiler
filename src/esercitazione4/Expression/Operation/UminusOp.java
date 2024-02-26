package esercitazione4.Expression.Operation;

import esercitazione4.Expression.Expr;
import esercitazione4.visitor.Visitor;

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
