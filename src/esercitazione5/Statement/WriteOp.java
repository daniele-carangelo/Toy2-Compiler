package esercitazione5.Statement;

import esercitazione5.Expression.Expr;
import esercitazione5.visitor.Visitor;

import java.util.LinkedList;

public class WriteOp extends Statement{

    private LinkedList<Expr> IOArgs;
    private Boolean returnType;

    public WriteOp(Boolean returnType, LinkedList<Expr> IOArgs){
        this.returnType = returnType;
        this.IOArgs = IOArgs;
    }

    public LinkedList<Expr> getIOArgs() {
        return IOArgs;
    }

    public void setIOArgs(LinkedList<Expr> IOArgs) {
        this.IOArgs = IOArgs;
    }

    public Boolean getType() {
        return returnType;
    }

    public void setType(Boolean returnType) {
        this.returnType = returnType;
    }

    public Object accept(Visitor v){
        return v.visit(this);
    }


}
