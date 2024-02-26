package esercitazione4.Node;

import esercitazione4.Expression.IdOp;
import esercitazione4.visitor.Visitor;

import java.util.LinkedList;

public class FuncParamsOp {

    private IdOp id;
    private TypeOp type;


    public FuncParamsOp(IdOp id, TypeOp type){
        this.id = id;
        this.type = type;
    }



    public IdOp getId() {
        return id;
    }

    public void setId(IdOp id) {
        this.id = id;
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
