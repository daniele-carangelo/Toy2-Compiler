package esercitazione4.Node;

import esercitazione4.Expression.IdOp;

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
}
