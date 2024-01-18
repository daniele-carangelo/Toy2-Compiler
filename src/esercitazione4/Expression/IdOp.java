package esercitazione4.Expression;

public class IdOp extends Expr{

    private String name;

    public IdOp(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
