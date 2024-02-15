package esercitazione4.Expression;

import esercitazione4.visitor.Visitor;

public abstract class Expr {

    public Object accept(Visitor v){
        return v.visit(this);
    }

}
