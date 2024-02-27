package esercitazione5.visitor;

import esercitazione5.Expression.ConstOP.*;

import esercitazione5.Expression.Expr;
import esercitazione5.Expression.FunCallOp;
import esercitazione5.Expression.IdOp;
import esercitazione5.Expression.Operation.*;
import esercitazione5.Expression.ParOp;
import esercitazione5.Expression.RelOp.*;
import esercitazione5.Node.*;
import esercitazione5.Statement.*;
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

        addOpElement.appendChild((Element) e1);
        addOpElement.appendChild((Element) e2);

        return addOpElement;
    }

    @Override
    public Object visit(Expr expr) {
        return null;
    }

    @Override
    public Object visit(DiffOp diffOp) {
        Element diffOpElement = document.createElement("DiffOp");
        Object e1 = diffOp.getExpr1().accept(this);
        Object e2 = diffOp.getExpr2().accept(this);

        diffOpElement.appendChild((Element) e1);
        diffOpElement.appendChild((Element) e2);

        return diffOpElement;
    }

    @Override
    public Object visit(DivOp divOp) {
        Element diffOpElement = document.createElement("DivOp");
        Object e1 = divOp.getExpr1().accept(this);
        Object e2 = divOp.getExpr2().accept(this);

        diffOpElement.appendChild((Element) e1);
        diffOpElement.appendChild((Element) e2);

        return diffOpElement;
    }

    @Override
    public Object visit(MulOp mulOp) {
        Element mulOpElement = document.createElement("MulOp");
        Object e1 = mulOp.getExpr1().accept(this);
        Object e2 = mulOp.getExpr2().accept(this);

        mulOpElement.appendChild((Element) e1);
        mulOpElement.appendChild((Element) e2);

        return mulOpElement;
    }

    @Override
    public Object visit(UminusOp uminusOp) {
        Element uminusOpElement = document.createElement("UminusOp");
        Object e1 = uminusOp.getExpr().accept(this);

        uminusOpElement.appendChild((Element) e1);

        return uminusOpElement;
    }

    @Override
    public Object visit(AndOp andOp) {
        Element andOpElement = document.createElement("AndOp");
        Object e1 = andOp.getExpr1().accept(this);
        Object e2 = andOp.getExpr2().accept(this);

        andOpElement.appendChild((Element) e1);
        andOpElement.appendChild((Element) e2);

        return andOpElement;
    }

    @Override
    public Object visit(EQOp eqOp) {
        Element eqOpElement = document.createElement("EqOp");
        Object e1 = eqOp.getExpr1().accept(this);
        Object e2 = eqOp.getExpr1().accept(this);

        eqOpElement.appendChild((Element) e1);
        eqOpElement.appendChild((Element) e2);

        return eqOpElement;
    }

    @Override
    public Object visit(GEOp geOp) {
        Element geOpElement = document.createElement("GEOp");
        Object e1 = geOp.getExpr1().accept(this);
        Object e2 = geOp.getExpr2().accept(this);

        geOpElement.appendChild((Element) e1);
        geOpElement.appendChild((Element) e2);

        return geOpElement;
    }

    @Override
    public Object visit(GTOp gtOp) {
        Element gtOpElement = document.createElement("GTOp");
        Object e1 = gtOp.getExpr1().accept(this);
        Object e2 = gtOp.getExpr2().accept(this);

        gtOpElement.appendChild((Element) e1);
        gtOpElement.appendChild((Element) e2);

        return gtOpElement;
    }

    @Override
    public Object visit(LEOp leOp) {
        Element leOpElement = document.createElement("LEOp");
        Object e1 = leOp.getExpr1().accept(this);
        Object e2 = leOp.getExpr2().accept(this);

        leOpElement.appendChild((Element) e1);
        leOpElement.appendChild((Element) e2);

        return leOpElement;
    }

    @Override
    public Object visit(LTOp ltOp) {
        Element ltOpElement = document.createElement("LTOp");
        Object e1 = ltOp.getExpr1().accept(this);
        Object e2 = ltOp.getExpr2().accept(this);

        ltOpElement.appendChild((Element) e1);
        ltOpElement.appendChild((Element) e2);

        return ltOpElement;
    }

    @Override
    public Object visit(NEOp neOp) {
        Element neOpElement = document.createElement("NEOp");
        Object e1 = neOp.getExpr1().accept(this);
        Object e2 = neOp.getExpr2().accept(this);

        neOpElement.appendChild((Element) e1);
        neOpElement.appendChild((Element) e2);

        return neOpElement;
    }

    @Override
    public Object visit(NotOp notOp) {
        Element notOpElement = document.createElement("NotOp");
        Object e1 = notOp.getExpr().accept(this);

        notOpElement.appendChild((Element) e1);

        return notOpElement;
    }

    @Override
    public Object visit(OrOp orOp) {
        Element orOpElement = document.createElement("OrOp");
        Object e1 = orOp.getExpr1().accept(this);
        Object e2 = orOp.getExpr2().accept(this);

        orOpElement.appendChild((Element) e1);
        orOpElement.appendChild((Element) e2);

        return orOpElement;    }

    @Override
    public Object visit(FunCallOp funCallOp) {
        Element funCallOpElement = document.createElement("FunCallOp");

        funCallOpElement.appendChild((Element) funCallOp.getId().accept(this));

        if(funCallOp.getExprs() != null) {
            for (Expr p : funCallOp.getExprs()) {
                funCallOpElement.appendChild((Element) p.accept(this));
            }
        }

        return funCallOpElement;

    }

    @Override
    public Object visit(IdOp idOp) {
        Element idOpElement = document.createElement("IdOp");
        idOpElement.appendChild(document.createTextNode(idOp.getName()));

        return idOpElement;
    }

    @Override
    public Object visit(ParOp parOp) {
        Element parOpElement = document.createElement("ParOp");

        parOpElement.appendChild(document.createTextNode((String) parOp.getExpr().accept(this)));
        return parOpElement;
    }

    @Override
    public Object visit(BodyOp bodyOp) {
        Element bodyOpElement = document.createElement("BodyOp");

        Element varList = document.createElement("VarList");
        for (VarDeclOp varDecl : bodyOp.getVarDecl()) {
            varList.appendChild((Element) varDecl.accept(this));
        }
        bodyOpElement.appendChild(varList);

        Element stmtList = document.createElement("StatementList");
        for(Statement statement : bodyOp.getStmt()){
            stmtList.appendChild((Element) statement.accept(this));
        }
        bodyOpElement.appendChild(stmtList);

        return bodyOpElement;
    }

    @Override
    public Object visit(FuncParamsOp funcParamsOp) {
        Element funcParamElement = document.createElement("FuncParamsOp");

        funcParamElement.appendChild((Element) funcParamsOp.getId().accept(this));
        funcParamElement.appendChild((Element) funcParamsOp.getType().accept(this));
        return funcParamElement;
    }

    @Override
    public Object visit(FunOp funOp) throws Exception {
        Element funOpElement = document.createElement("FunOp");
        funOpElement.appendChild((Element)funOp.getId().accept(this));

        if(!funOp.getFuncParams().isEmpty()){
            Element paramElement = document.createElement("FuncParams");
            for(FuncParamsOp paramsOp : funOp.getFuncParams()){
                paramElement.appendChild((Element) paramsOp.accept(this) );
            }
            funOpElement.appendChild(paramElement);
        }

        if(!funOp.getTypes().isEmpty()){
            Element typeElement = document.createElement("TypesReturn");
            for(TypeOp typeOp : funOp.getTypes()){
                typeElement.appendChild((Element) typeOp.accept(this));
            }
            funOpElement.appendChild(typeElement);
        }
        else
            throw new Exception("Syntax Error: Le funzioni devono avere almeno un tipo di ritorno");

        funOpElement.appendChild((Element) funOp.getBody().accept(this));


        return funOpElement;
    }

    @Override
    public Object visit(IterOp iterOp) throws Exception {
        Element iterOpElement = document.createElement("IterOp");

        if(!iterOp.getProcedures().isEmpty()){
            Element proceduresElement = document.createElement("ProcedureOpList");
            for(ProcedureOp procedure : iterOp.getProcedures()){
                proceduresElement.appendChild((Element) procedure.accept(this));
            }
            iterOpElement.appendChild(proceduresElement);
        }

        if(!iterOp.getFunctions().isEmpty()){
            Element funcElement = document.createElement("FunOpList");
            for(FunOp funOp : iterOp.getFunctions()){
                funcElement.appendChild((Element) funOp.accept(this));
            }
            iterOpElement.appendChild(funcElement);
        }

        if(!iterOp.getVarDecls().isEmpty()){
            Element varDeclElement = document.createElement("VarDeclOpList");
            for(VarDeclOp varDeclOp : iterOp.getVarDecls()){
                varDeclElement.appendChild((Element) varDeclOp.accept(this));
            }
            iterOpElement.appendChild(varDeclElement);
        }

        return iterOpElement;
    }

    @Override
    public Object visit(ProcedureOp procedureOp) {
        Element procedureOpElement = document.createElement("ProcedureOp");
        procedureOpElement.appendChild((Element) procedureOp.getId().accept(this));

        if(!procedureOp.getParams().isEmpty()){
            Element paramsElement = document.createElement("ProcParamsList");
            for(ProcParamsOp procParamsOp : procedureOp.getParams()){
                paramsElement.appendChild((Element) procParamsOp.accept(this));
            }
            procedureOpElement.appendChild(paramsElement);
        }

        procedureOpElement.appendChild((Element)procedureOp.getBody().accept(this));

        return procedureOpElement;
    }

    @Override
    public Object visit(ProcParamsOp procParamsOp) {
        Element procParamsOpElement = document.createElement("ProcParamsOp");

        procParamsOpElement.appendChild((Element) procParamsOp.getParamId().accept(this));
        procParamsOpElement.appendChild((Element) procParamsOp.getType().accept(this));

        return procParamsOpElement;
    }

    @Override
    public Object visit(ProcParamIdOp procParamIdOp) {
        Element procParamIdOpElement = document.createElement("ProcParamIdOp");

        procParamIdOpElement.appendChild((Element) procParamIdOp.getId().accept(this));
        procParamIdOpElement.appendChild(document.createTextNode(String.valueOf(procParamIdOp.getOut())));

        return procParamIdOpElement;
    }

    @Override
    public Object visit(ProgramOp programOp) throws Exception {

        Element programOpElement = document.createElement("ProgramOp");

        if(programOp.getProcedures().isEmpty())
            throw new Exception("Il programma deve contenere almeno una procedura");
        else{
            Element proceduresElement = document.createElement("ProcedureOpList");
            for(ProcedureOp procedure : programOp.getProcedures()){
                proceduresElement.appendChild((Element) procedure.accept(this));
            }
            programOpElement.appendChild(proceduresElement);
        }

        if(!programOp.getFunctions().isEmpty()){
            Element funcElement = document.createElement("FunOpList");
            for(FunOp funOp : programOp.getFunctions()){
                funcElement.appendChild((Element) funOp.accept(this));
            }
            programOpElement.appendChild(funcElement);
        }

        if(!programOp.getVarDecls().isEmpty()){
            Element varDeclElement = document.createElement("VarDeclOpList");
            for(VarDeclOp varDeclOp : programOp.getVarDecls()){
                varDeclElement.appendChild((Element) varDeclOp.accept(this));
            }
            programOpElement.appendChild(varDeclElement);
        }
        document.appendChild(programOpElement);

        return document;
    }

    @Override
    public Object visit(TypeOp typeOp) {
        Element typeOpElement = document.createElement("TypeOp");

        typeOpElement.appendChild(document.createTextNode(typeOp.getType()));

        return typeOpElement;
    }

    @Override
    public Object visit(VarDeclOp varDeclOp) {
        Element varDeclOpElement = document.createElement("VarDeclOp");

        Element idListElement = document.createElement("IdList");
        for(IdOp idOp : varDeclOp.getIds()){
            idListElement.appendChild((Element) idOp.accept(this));
        }
        varDeclOpElement.appendChild(idListElement);

        if(varDeclOp.getConstant().isEmpty())
            varDeclOpElement.appendChild((Element)varDeclOp.getType().accept(this));
        else {
            for(Expr expr : varDeclOp.getConstant()){
                varDeclOpElement.appendChild((Element) expr.accept(this));
            }
        }

        return varDeclOpElement;
    }

    @Override
    public Object visit(AssignStatOp assignStatOp) {
        Element assignStatOpElement = document.createElement("AssignStatOp");

        Element idListElement = document.createElement("IdList");
        for(IdOp idOp : assignStatOp.getIds()){
            idListElement.appendChild((Element) idOp.accept(this));
        }
        assignStatOpElement.appendChild(idListElement);


        Element exprListElement = document.createElement("ExprList");
        for(Expr expr : assignStatOp.getExprs()){
               exprListElement.appendChild((Element) expr.accept(this));
        }
        assignStatOpElement.appendChild(exprListElement);

        return assignStatOpElement;
    }

    @Override
    public Object visit(ElifOp elifOp) {
        Element elifOpElement = document.createElement("ElifOp");

        elifOpElement.appendChild((Element) elifOp.getExpr().accept(this));
        elifOpElement.appendChild((Element) elifOp.getBody().accept(this));

        return elifOpElement;
    }

    @Override
    public Object visit(IfStatOp ifStatOp) {
        Element ifStatOpElement = document.createElement("IfStatOp");

        ifStatOpElement.appendChild((Element)ifStatOp.getExpr().accept(this));
        ifStatOpElement.appendChild((Element) ifStatOp.getBody().accept(this));

        if(ifStatOp.getElifS() != null){
            Element elifsList = document.createElement("ElifsList");
            for(ElifOp elifOp : ifStatOp.getElifS()){
                elifsList.appendChild((Element) elifOp.accept(this));
            }
            ifStatOpElement.appendChild(elifsList);
        }

        if(ifStatOp.getElseBody() != null)
            ifStatOpElement.appendChild((Element) ifStatOp.getElseBody().accept(this));


        return ifStatOpElement;
    }

    @Override
    public Object visit(Statement statement) {
        return null;
    }

    @Override
    public Object visit(ProcCallOp procCallOp) {
        Element procCallOpElement = document.createElement("ProcCallOp");

        procCallOpElement.appendChild((Element) procCallOp.getId().accept(this));
        if(procCallOp.getProcExprs() != null)
            procCallOpElement.appendChild((Element) procCallOp.getProcExprs().accept(this));

        return procCallOpElement;
    }

    @Override
    public Object visit(ProcExprsOp procExprsOp) {
        Element procExprsOpElement = document.createElement("ProcExprsOp");

        if(!procExprsOp.getExprs().isEmpty()){
            Element exprListElement = document.createElement("ExprList");
            for(Expr expr : procExprsOp.getExprs()){
                exprListElement.appendChild((Element) expr.accept(this));
            }
            procExprsOpElement.appendChild(exprListElement);
        }

        if(!procExprsOp.getRefId().isEmpty()){
            Element refIdElement = document.createElement("RefIdList");
            for(IdOp idOp : procExprsOp.getRefId()){
                refIdElement.appendChild((Element) idOp.accept(this));
            }
            procExprsOpElement.appendChild(refIdElement);
        }


        return procExprsOpElement;
    }

    @Override
    public Object visit(ReadOp readOp) {
        Element readOpElement = document.createElement("ReadOp");

        for(Expr expr : readOp.getExprs()){
            readOpElement.appendChild((Element) expr.accept(this));
        }

        return readOpElement;
    }

    @Override
    public Object visit(WhileStatOp whileStatOp) {
        Element whileStatOpElement = document.createElement("WhileStatOp");

        whileStatOpElement.appendChild((Element) whileStatOp.getExpr().accept(this));
        whileStatOpElement.appendChild((Element) whileStatOp.getBody().accept(this));

        return whileStatOpElement;
    }

    @Override
    public Object visit(ReturnStatOp returnStatOp) {
        Element returnStatOpElement = document.createElement("ReturnStatOp");

        for(Expr expr : returnStatOp.getExprs()){
            returnStatOpElement.appendChild((Element) expr.accept(this));
        }

        return returnStatOpElement;
    }

    @Override
    public Object visit(WriteOp writeOp) {
        Element writeOpElement;
        if(writeOp.getType()) {
            writeOpElement = document.createElement("WriteOpReturn");
        }
        else {
            writeOpElement = document.createElement("WriteOp");
        }

        for(Expr expr : writeOp.getIOArgs()){
            writeOpElement.appendChild((Element) expr.accept(this));
        }




        return writeOpElement;
    }


}
