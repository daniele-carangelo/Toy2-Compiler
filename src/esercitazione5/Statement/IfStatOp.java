package esercitazione5.Statement;

import esercitazione5.Expression.Expr;
import esercitazione5.Node.BodyOp;
import esercitazione5.SymbolTable.SymbolTable;
import esercitazione5.visitor.Visitor;

import java.util.LinkedList;

public class IfStatOp extends Statement{

    private Expr expr;
    private BodyOp body;
    private LinkedList<ElifOp> elifS;
    private BodyOp elseBody;

    private SymbolTable symbolTable;

    private SymbolTable elseTable;

    public IfStatOp(Expr expr, BodyOp body){
        this.expr = expr;
        this.body = body;
        this.elseBody = null;
        this.elifS = new LinkedList<ElifOp>();
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
        this.elifS = new LinkedList<ElifOp>();
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

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public SymbolTable getElseTable() {
        return elseTable;
    }

    public void setElseTable(SymbolTable elseTable) {
        this.elseTable = elseTable;
    }

    public Object accept(Visitor v){
        return v.visit(this);
    }

}
