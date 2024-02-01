package esercitazione4.Node;

import esercitazione4.Statement.Statement;



public class BodyOp {

    private VarDeclOp varDecl;
    private Statement stmt;

    public BodyOp(VarDeclOp varDecl){
        this.varDecl = varDecl;
    }


    public BodyOp(Statement stmt){
        this.stmt = stmt;
    }

    public VarDeclOp getVarDecl() {
        return varDecl;
    }

    public void setVarDecl(VarDeclOp varDecl) {
        this.varDecl = varDecl;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }


}
