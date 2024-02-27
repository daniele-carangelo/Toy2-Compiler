package esercitazione5.Statement;

import esercitazione5.Expression.Expr;
import esercitazione5.Node.BodyOp;
import esercitazione5.visitor.Visitor;

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
    public Object accept(Visitor v){
        return v.visit(this);
    }

}
