package esercitazione4.Statement;

import esercitazione4.Expression.Expr;
import esercitazione4.Node.BodyOp;

public class WhileStatOp extends Statement{

    private Expr expr;
    private BodyOp body;

    public WhileStatOp(Expr expr, BodyOp body){
        this.body = body;
        this.expr = expr;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }

    public BodyOp getBody() {
        return body;
    }

    public void setBody(BodyOp body) {
        this.body = body;
    }
}
