package esercitazione4.Expression.RelOp;

import esercitazione4.Expression.Expr;
import esercitazione4.visitor.Visitor;

public class GTOp extends Expr {

    private Expr expr1, expr2;

    public GTOp(Expr expr1, Expr expr2){
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public Expr getExpr1() {
        return expr1;
    }

    public void setExpr1(Expr expr1) {
        this.expr1 = expr1;
    }

    public Expr getExpr2() {
        return expr2;
    }

    public void setExpr2(Expr expr2) {
        this.expr2 = expr2;
    }

    public Object accept(Visitor v){
        return v.visit(this);
    }

}
