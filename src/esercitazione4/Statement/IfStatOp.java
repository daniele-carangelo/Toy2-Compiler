package esercitazione4.Statement;

import esercitazione4.Expression.Expr;
import esercitazione4.Node.BodyOp;

import java.util.LinkedList;

public class IfStatOp extends Statement{

    private Expr expr;
    private BodyOp body;
    private ElseOp elseOp;
    private LinkedList<ElifOp> elifS;

    public IfStatOp(Expr expr, BodyOp body){
        this.expr = expr;
        this.body = body;
    }

    public IfStatOp(Expr expr, BodyOp body,LinkedList<ElifOp> elifS, ElseOp elseOp ){
        this.expr = expr;
        this.body = body;
        this.elifS = elifS;
        this.elseOp = elseOp;
    }

    public IfStatOp(Expr expr, BodyOp body, ElseOp elseOp ){
        this.expr = expr;
        this.body = body;
        this.elseOp = elseOp;
    }
    public IfStatOp(Expr expr, BodyOp body,LinkedList<ElifOp> elifS ){
        this.expr = expr;
        this.body = body;
        this.elifS = elifS;
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

    public ElseOp getElseOp() {
        return elseOp;
    }

    public void setElseOp(ElseOp elseOp) {
        this.elseOp = elseOp;
    }

    public LinkedList<ElifOp> getElifS() {
        return elifS;
    }

    public void setElifS(LinkedList<ElifOp> elifS) {
        this.elifS = elifS;
    }
}
