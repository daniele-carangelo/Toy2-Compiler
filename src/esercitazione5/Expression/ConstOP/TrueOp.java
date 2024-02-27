package esercitazione5.Expression.ConstOP;

import esercitazione5.Expression.Expr;
import esercitazione5.visitor.Visitor;

public class TrueOp extends Expr {

    public TrueOp(){

    }
    public Object accept(Visitor v){
        return v.visit(this);
    }
}
