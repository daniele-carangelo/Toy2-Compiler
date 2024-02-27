package esercitazione5.Expression.ConstOP;

import esercitazione5.Expression.Expr;
import esercitazione5.visitor.Visitor;

public class FalseOp extends Expr {

    public FalseOp(){

    }

    public Object accept(Visitor v){
        return v.visit(this);
    }

}
