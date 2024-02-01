package esercitazione4.Statement;

import esercitazione4.Node.BodyOp;

public class ElseOp extends Statement{

    private BodyOp body;

    public ElseOp(BodyOp body){
        this.body = body;
    }

    public BodyOp getBody() {
        return body;
    }

    public void setBody(BodyOp body) {
        this.body = body;
    }
}
