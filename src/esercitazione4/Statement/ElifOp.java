package esercitazione4.Statement;

import esercitazione4.Expression.Expr;
import esercitazione4.Node.BodyOp;
import esercitazione4.visitor.Visitor;

public class ElifOp extends Statement{

    private Expr expr;
    private BodyOp body;

    public ElifOp(Expr expr, BodyOp body){
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
