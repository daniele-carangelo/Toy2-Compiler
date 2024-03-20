package esercitazione5.Expression;

import esercitazione5.visitor.Visitor;

import java.io.IOException;

public abstract class Expr {

    private Boolean dollar = false ;
    public String type;

    public Boolean getDollar() {
        return dollar;
    }

    public void setDollar(Boolean dollar) {
        this.dollar = dollar;
    }

    public String toString() {return super.toString();}
    public Object accept(Visitor v)  {
        return v.visit(this);
    }

}
