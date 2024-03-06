package esercitazione5.Expression.ConstOP;

import esercitazione5.Expression.Expr;
import esercitazione5.visitor.Visitor;

public class StringOp extends Expr {

    private String attribute;

    public StringOp(String attribute){
        this.attribute = attribute;
        super.type = "string";
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public Object accept(Visitor v){
        return v.visit(this);
    }
}
