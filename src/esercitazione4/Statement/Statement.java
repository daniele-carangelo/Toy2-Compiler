package esercitazione4.Statement;

import esercitazione4.visitor.Visitor;

public abstract class Statement {

    public Object accept(Visitor v){
        return v.visit(this);
    }


}
