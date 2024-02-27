package esercitazione5.Statement;

import esercitazione5.visitor.Visitor;

public abstract class Statement {

    public Object accept(Visitor v){
        return v.visit(this);
    }


}
