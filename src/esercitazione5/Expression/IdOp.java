package esercitazione5.Expression;

import esercitazione5.visitor.Visitor;

public class IdOp extends Expr{

    private String name;

    private boolean ref = false;

    public IdOp(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRef() {
        return ref;
    }

    public void setRef(boolean ref) {
        this.ref = ref;
    }

    public Object accept(Visitor v){
        return v.visit(this);
    }
}
