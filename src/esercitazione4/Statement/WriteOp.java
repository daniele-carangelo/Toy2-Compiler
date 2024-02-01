package esercitazione4.Statement;

import esercitazione4.Expression.Expr;

import java.util.LinkedList;

public class WriteOp extends Statement{

    private LinkedList<Expr> IOArgs;
    private String type;

    public WriteOp(String type, LinkedList<Expr> IOArgs){
        this.type = type;
        this.IOArgs = IOArgs;
    }

    public LinkedList<Expr> getIOArgs() {
        return IOArgs;
    }

    public void setIOArgs(LinkedList<Expr> IOArgs) {
        this.IOArgs = IOArgs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
