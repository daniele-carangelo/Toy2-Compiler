package esercitazione4.Expression.Operation;

import esercitazione4.Expression.Expr;

public class AddOp extends Expr {
    private Expr expr1, expr2;

    public AddOp(Expr expr1, Expr expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public Expr getExpr1() {
        return expr1;
    }

    public Expr getExpr2() {
        return expr2;
    }

    public void setExpr1(Expr expr1) {
        this.expr1 = expr1;
    }

    public void setExpr2(Expr expr2) {
        this.expr2 = expr2;
    }


}
