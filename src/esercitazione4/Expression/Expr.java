package esercitazione4.Expression;

import esercitazione4.visitor.Visitor;

public abstract class Expr {

    private Boolean dollar;

    public Boolean getDollar() {
        return dollar;
    }

    public void setDollar(Boolean dollar) {
        this.dollar = dollar;
    }

    public String toString() {return super.toString();}
    public Object accept(Visitor v){
        return v.visit(this);
    }

}
