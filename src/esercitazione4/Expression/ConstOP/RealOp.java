package esercitazione4.Expression.ConstOP;

import esercitazione4.Expression.Expr;

public class RealOp extends Expr {

    private float attribute;

    public RealOp(float attribute){
        this.attribute = attribute;
    }

    public float getAttribute() {
        return attribute;
    }

    public void setAttribute(float attribute) {
        this.attribute = attribute;
    }
}
