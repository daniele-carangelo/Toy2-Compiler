package esercitazione5.Node;

import esercitazione5.visitor.Visitor;

import java.util.LinkedList;

public class IterOp {

    private LinkedList<VarDeclOp> varDecls;
    private LinkedList<FunOp> functions;
    private LinkedList<ProcedureOp> procedures;

    public IterOp(LinkedList<VarDeclOp> varDecls, LinkedList<FunOp> functions, LinkedList<ProcedureOp> procedures){
        this.functions = functions;
        this.procedures = procedures;
        this.varDecls =  varDecls;
    }

    public IterOp(LinkedList<VarDeclOp> varDecls, LinkedList<FunOp> functions){
        this.functions = functions;
        this.varDecls =  varDecls;
        this.procedures = null;
    }

    public LinkedList<VarDeclOp> getVarDecls() {
        return varDecls;
    }

    public void setVarDecls(LinkedList<VarDeclOp> varDecls) {
        this.varDecls = varDecls;
    }

    public LinkedList<FunOp> getFunctions() {
        return functions;
    }

    public void setFunctions(LinkedList<FunOp> functions) {
        this.functions = functions;
    }

    public LinkedList<ProcedureOp> getProcedures() {
        return procedures;
    }

    public void setProcedures(LinkedList<ProcedureOp> procedures) {
        this.procedures = procedures;
    }

    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }

}
