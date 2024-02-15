package esercitazione4.Statement;

import esercitazione4.Expression.Expr;
import esercitazione4.Node.BodyOp;

import java.util.LinkedList;

public class IfStatOp extends Statement{

    private Expr expr;
    private BodyOp body;
    private LinkedList<ElifOp> elifS;
    private BodyOp elseBody;

    public IfStatOp(Expr expr, BodyOp body){
        this.expr = expr;
        this.body = body;
        this.elseBody = null;
        this.elifS = null;
    }

    public IfStatOp(Expr expr, BodyOp body,LinkedList<ElifOp> elifS, BodyOp elseBody ){
        this.expr = expr;
        this.body = body;
        this.elifS = elifS;
        this.elseBody = elseBody;
    }

    public IfStatOp(Expr expr, BodyOp body, BodyOp elseBody ){
        this.expr = expr;
        this.body = body;
        this.elseBody = elseBody;
        this.elifS = null;
    }
    public IfStatOp(Expr expr, BodyOp body,LinkedList<ElifOp> elifS ){
        this.expr = expr;
        this.body = body;
        this.elifS = elifS;
        this.elseBody = null;
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

    public LinkedList<ElifOp> getElifS() {
        return elifS;
    }

    public void setElifS(LinkedList<ElifOp> elifS) {
        this.elifS = elifS;
    }

    public BodyOp getElseBody() {
        return elseBody;
    }

    public void setElseBody(BodyOp elseBody) {
        this.elseBody = elseBody;
    }
}
