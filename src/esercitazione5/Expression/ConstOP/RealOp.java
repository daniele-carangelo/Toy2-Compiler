package esercitazione5.Expression.ConstOP;

import esercitazione5.Expression.Expr;
import esercitazione5.visitor.Visitor;

public class RealOp extends Expr {

    private float attribute;

    public RealOp(float attribute){
        this.attribute = attribute;
        super.type = "float";
    }

    public float getAttribute() {
        return attribute;
    }

    public void setAttribute(float attribute) {
        this.attribute = attribute;
    }

    public Object accept(Visitor v){
        return v.visit(this);
    }

}
