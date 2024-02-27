package esercitazione5.Node;

import esercitazione5.visitor.Visitor;

public class ProcParamsOp {

    private ProcParamIdOp paramId;
    private TypeOp type;

    public ProcParamsOp(ProcParamIdOp paramId, TypeOp type){
        this.paramId = paramId;
        this.type = type;
    }

    public ProcParamIdOp getParamId() {
        return paramId;
    }

    public void setParamId(ProcParamIdOp paramId) {
        this.paramId = paramId;
    }

    public TypeOp getType() {
        return type;
    }

    public void setType(TypeOp type) {
        this.type = type;
    }
    public Object accept(Visitor v){
        return v.visit(this);
    }

}
