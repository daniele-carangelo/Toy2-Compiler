package esercitazione5.Node;

import esercitazione5.Expression.IdOp;
import esercitazione5.visitor.Visitor;

import java.util.LinkedList;

public class FunOp {

    private IdOp id;

    private LinkedList<FuncParamsOp> funcParams;
    private LinkedList<TypeOp> types;
    private BodyOp body;

    public FunOp(IdOp id, LinkedList<FuncParamsOp> funcParams, LinkedList<TypeOp> types, BodyOp body){
        this.id = id;
        this.funcParams = funcParams;
        this.types = types;
        this.body = body;
    }

    public IdOp getId() {
        return id;
    }

    public void setId(IdOp id) {
        this.id = id;
    }

    public LinkedList<FuncParamsOp> getFuncParams() {
        return funcParams;
    }

    public void setFuncParams(LinkedList<FuncParamsOp> funcParams) {
        this.funcParams = funcParams;
    }

    public LinkedList<TypeOp> getTypes() {
        return types;
    }

    public void setTypes(LinkedList<TypeOp> types) {
        this.types = types;
    }

    public BodyOp getBody() {
        return body;
    }

    public void setBody(BodyOp body) {
        this.body = body;
    }

    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }


}
