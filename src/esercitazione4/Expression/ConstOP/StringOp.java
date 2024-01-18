package esercitazione4.Expression.ConstOP;

import esercitazione4.Expression.Expr;

public class StringOp extends Expr {

    private String attribute;

    public StringOp(String attribute){
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
