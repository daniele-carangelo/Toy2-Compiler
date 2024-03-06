package esercitazione5.Node;

import esercitazione5.Expression.IdOp;
import esercitazione5.SymbolTable.SymbolTable;
import esercitazione5.visitor.Visitor;

import java.util.LinkedList;

public class ProcedureOp {

    private IdOp id;
    private LinkedList<ProcParamsOp> params;
    private BodyOp body;

    private SymbolTable symbolTable;

    public ProcedureOp(IdOp id, LinkedList<ProcParamsOp> params, BodyOp body){
        this.body = body;
        this.id = id;
        this.params = params;
    }

    public IdOp getId() {
        return id;
    }

    public void setId(IdOp id) {
        this.id = id;
    }

    public LinkedList<ProcParamsOp> getParams() {
        return params;
    }

    public void setParams(LinkedList<ProcParamsOp> params) {
        this.params = params;
    }

    public BodyOp getBody() {
        return body;
    }

    public void setBody(BodyOp body) {
        this.body = body;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public Object accept(Visitor v){
        return v.visit(this);
    }


}
