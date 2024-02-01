package esercitazione4.Node;

import esercitazione4.Expression.Expr;
import esercitazione4.Expression.IdOp;

import java.util.LinkedList;

public class VarDeclOp {

    private LinkedList<IdOp> ids;
    private LinkedList<Expr> constant;
    private TypeOp type;


    public VarDeclOp(LinkedList<IdOp> ids, TypeOp type){
        this.ids = ids;
        this.type = type;
    }

    public VarDeclOp(LinkedList<IdOp> ids, LinkedList<Expr> constant){
        this.ids = ids;
        this.constant = constant;
    }

    public LinkedList<IdOp> getIds() {
        return ids;
    }

    public void setIds(LinkedList<IdOp> ids) {
        this.ids = ids;
    }

    public LinkedList<Expr> getConstant() {
        return constant;
    }

    public void setConstant(LinkedList<Expr> constant) {
        this.constant = constant;
    }

    public TypeOp getType() {
        return type;
    }

    public void setType(TypeOp type) {
        this.type = type;
    }


}
