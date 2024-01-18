package esercitazione4.Expression;

public class BoolOp extends Expr{

    private Boolean value;

    public BoolOp(Boolean value){
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
