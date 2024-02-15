package esercitazione4.visitor;

import esercitazione4.Expression.ConstOP.*;
import esercitazione4.Expression.Expr;
import esercitazione4.Expression.Operation.AddOp;

public interface Visitor {

    Object visit(FalseOp falseOp);
    Object visit(IntegerOp integerOp);
    Object visit(RealOp realOp);
    Object visit(StringOp stringOp);
    Object visit(TrueOp trueOp);
    Object visit(AddOp addOp);
    Object visit(Expr expr);
}
