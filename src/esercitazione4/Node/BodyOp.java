package esercitazione4.Node;

import esercitazione4.Statement.Statement;
import esercitazione4.visitor.Visitor;

import java.util.LinkedList;


public class BodyOp {

    private LinkedList<VarDeclOp> varDecl;
    private LinkedList<Statement> stmt;

    public BodyOp(LinkedList<VarDeclOp> varDecl, LinkedList<Statement> stmt ){
        this.varDecl = varDecl;
        this.stmt = stmt;
    }


    public LinkedList<VarDeclOp> getVarDecl() {
        return varDecl;
    }

    public void setVarDecl(LinkedList<VarDeclOp> varDecl) {
        this.varDecl = varDecl;
    }

    public LinkedList<Statement> getStmt() {
        return stmt;
    }

    public void setStmt(LinkedList<Statement> stmt) {
        this.stmt = stmt;
    }

    public Object accept(Visitor v){
        return v.visit(this);
    }

}
