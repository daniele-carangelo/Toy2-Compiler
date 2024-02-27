package esercitazione5.Node;

import esercitazione5.Expression.Expr;
import esercitazione5.Expression.IdOp;
import esercitazione5.visitor.Visitor;

import java.util.LinkedList;

public class VarDeclOp {

    private LinkedList<IdOp> ids;
    private LinkedList<Expr> constant;
    private TypeOp type;


    public VarDeclOp(LinkedList<IdOp> ids, TypeOp type){
        this.ids = ids;
        this.type = type;
        this.constant = new LinkedList<>();
    }

    public VarDeclOp(LinkedList<IdOp> ids, LinkedList<Expr> constant){
        this.ids = ids;
        this.constant = constant;
        this.type = null;
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
    public Object accept(Visitor v){
        return v.visit(this);
    }


}
