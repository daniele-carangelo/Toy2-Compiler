package esercitazione4.Expression.ConstOP;

import esercitazione4.Expression.Expr;
import esercitazione4.visitor.Visitor;

public class IntegerOp extends Expr {

    private int attribute;

    public IntegerOp(int attribute){
        this.attribute = attribute;
    }

    public int getAttribute() {
        return attribute;
    }

    public void setAttribute(int attribute) {
        this.attribute = attribute;
    }

    public Object accept(Visitor v){
        return v.visit(this);
    }
}
