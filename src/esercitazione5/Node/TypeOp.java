package esercitazione5.Node;

import esercitazione5.visitor.Visitor;

public class TypeOp {

    private String type;

    public TypeOp(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Object accept(Visitor v){
        return v.visit(this);
    }

}
