package esercitazione4.visitor;

import esercitazione4.Expression.ConstOP.*;

import esercitazione4.Expression.Expr;
import esercitazione4.Expression.Operation.AddOp;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XmlGenerator implements Visitor{

    private Document document;
    public XmlGenerator() throws ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.newDocument();
    }


    public Object visit(FalseOp falseOp){
        Element falseOpElement = document.createElement("FalseOp");
        return falseOpElement;
    }

    @Override
    public Object visit(IntegerOp integerOp) {
        Element integerOpElement = document.createElement("IntegerOp");
        integerOpElement.appendChild(document.createTextNode(String.valueOf(integerOp.getAttribute())));
        return integerOpElement;
    }

    @Override
    public Object visit(RealOp realOp) {
        Element realOpElement = document.createElement("RealOp");
        realOpElement.appendChild(document.createTextNode(String.valueOf(realOp.getAttribute())));
        return realOpElement;
    }

    @Override
    public Object visit(StringOp stringOp) {
        Element stringOpElement = document.createElement("StringOp");
        stringOpElement.appendChild(document.createTextNode(stringOp.getAttribute()));
        return stringOpElement;
    }

    @Override
    public Object visit(TrueOp trueOp) {
        Element trueOpElement = document.createElement("TrueOp");
        return trueOpElement;
    }

    @Override
    public Object visit(AddOp addOp) {
        Element addOpElement = document.createElement("AddOp");
        Object e1 = addOp.getExpr1().accept(this);
        Object e2 = addOp.getExpr2().accept(this);

        addOpElement.appendChild(document.createTextNode((String) e1));
        addOpElement.appendChild(document.createTextNode((String) e2));

        return addOpElement;
    }

    @Override
    public Object visit(Expr expr) {
        return null;
    }


}
