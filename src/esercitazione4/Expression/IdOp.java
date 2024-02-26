package esercitazione4.Expression;

import esercitazione4.visitor.Visitor;

public class IdOp extends Expr{

    private String name;

    public IdOp(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object accept(Visitor v){
        return v.visit(this);
    }
}
