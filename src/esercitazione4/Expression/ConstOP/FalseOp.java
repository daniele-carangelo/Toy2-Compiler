package esercitazione4.Expression.ConstOP;

import esercitazione4.Expression.Expr;
import esercitazione4.visitor.Visitor;

public class FalseOp extends Expr {

    public FalseOp(){

    }

    public Object accept(Visitor v){
        return v.visit(this);
    }

}
