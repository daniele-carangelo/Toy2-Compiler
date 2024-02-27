package esercitazione5.Node;

import esercitazione5.Expression.IdOp;
import esercitazione5.visitor.Visitor;

public class ProcParamIdOp {

    private IdOp id;
    private Boolean out;

    public ProcParamIdOp(IdOp id, Boolean out){
        this.id = id;
        this.out = out;
    }

    public IdOp getId() {
        return id;
    }

    public void setId(IdOp id) {
        this.id = id;
    }

    public Boolean getOut() {
        return out;
    }

    public void setOut(Boolean out) {
        this.out = out;
    }
    public Object accept(Visitor v){
        return v.visit(this);
    }

}
