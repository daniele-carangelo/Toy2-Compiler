package esercitazione4.Expression.ConstOP;

import esercitazione4.Expression.Expr;
import esercitazione4.visitor.Visitor;

public class TrueOp extends Expr {

    public TrueOp(){

    }
    public Object accept(Visitor v){
        return v.visit(this);
    }
}
