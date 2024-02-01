package esercitazione4.Node;

import esercitazione4.Expression.IdOp;

import java.util.LinkedList;

public class ProcedureOp {

    private IdOp id;
    private LinkedList<ProcParamsOp> params;
    private BodyOp body;

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

}
