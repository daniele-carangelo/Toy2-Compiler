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
import esercitazione5.SymbolTable.SymbolRecord;
import esercitazione5.SymbolTable.SymbolTable;
import esercitazione5.SymbolTable.SymbolType;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ScopeVisitor implements Visitor{

    private SymbolTable table;

    @Override
    public Object visit(EQOp eqOp) {
        eqOp.getExpr1().accept(this);
        eqOp.getExpr2().accept(this);

        return null;
    }

    @Override
    public Object visit(AndOp andOp) {
        andOp.getExpr1().accept(this);
        andOp.getExpr2().accept(this);
        return null;
    }

    @Override
    public Object visit(GEOp geOp) {
        geOp.getExpr1().accept(this);
        geOp.getExpr2().accept(this);
        return null;
    }

    @Override
    public Object visit(GTOp gtOp) {
        gtOp.getExpr1().accept(this);
        gtOp.getExpr2().accept(this);
        return null;
    }

    @Override
    public Object visit(LEOp leOp) {
        leOp.getExpr1().accept(this);
        leOp.getExpr2().accept(this);
        return null;
    }
    public Object visit(LTOp ltOp){
        ltOp.getExpr1().accept(this);
        ltOp.getExpr2().accept(this);
        return null;
    }

    public Object visit(NEOp neOp){
        neOp.getExpr1().accept(this);
        neOp.getExpr2().accept(this);
        return null;
    }

    public Object visit(NotOp notOp){
        notOp.getExpr().accept(this);
        return null;
    }

    @Override
    public Object visit(OrOp orOp) {
        orOp.getExpr1().accept(this);
        orOp.getExpr2().accept(this);
        return null;
    }

    @Override
    public Object visit(AddOp addOp) {
        addOp.getExpr1().accept(this);
        addOp.getExpr2().accept(this);
        return null;
    }

    @Override
    public Object visit(DiffOp diffOp) {
        diffOp.getExpr1().accept(this);
        diffOp.getExpr2().accept(this);
        return null;
    }

    @Override
    public Object visit(DivOp divOp) {
        divOp.getExpr1().accept(this);
        divOp.getExpr2().accept(this);
        return null;
    }

    @Override
    public Object visit(MulOp mulOp) {
        mulOp.getExpr1().accept(this);
        mulOp.getExpr2().accept(this);
        return null;
    }

    @Override
    public Object visit(UminusOp uminusOp) {
        uminusOp.getExpr().accept(this);
        return null;
    }

    @Override
    public Object visit(FalseOp falseOp) {

        return "boolean";
    }

    @Override
    public Object visit(TrueOp trueOp) {

        return "boolean";
    }

    public Object visit(IntegerOp integerOp){

        return "integer";
    }

    @Override
    public Object visit(RealOp realOp) {
        return "real";
    }

    @Override
    public Object visit(StringOp stringOp) {
        return "string";
    }

    @Override
    public Object visit(Expr expr) {
        return null;
    }

    @Override
    public Object visit(ParOp parOp) {
        parOp.getExpr().accept(this);
        return null;
    }


    @Override
    public Object visit(IdOp idOp) {

        if(table.lookup(idOp.getName()).isEmpty())
            throw new RuntimeException("La variabile: " + idOp.getName() + " non è stata dichiarata.");

        return null;
    }

    @Override
    public Object visit(FunCallOp funCallOp) {

        if(!funCallOp.getExprs().isEmpty())
            funCallOp.getExprs().forEach(expr -> expr.accept(this));

        return null;
    }

    @Override
    public Object visit(BodyOp bodyOp) {


        if(!bodyOp.getVarDecl().isEmpty()){
            for(VarDeclOp varDeclOp : bodyOp.getVarDecl()){
                varDeclOp.accept(this);
                /*
                for(SymbolRecord symbolRecord : varList){
                    table.addRecord(symbolRecord);
                }*/
            }
        }

        if(!bodyOp.getStmt().isEmpty()){
            bodyOp.getStmt().forEach(statement -> statement.accept(this));
        }

        return null;
    }

    @Override
    public Object visit(ProgramOp programOp) throws Exception {

        table = new SymbolTable();
        table.setScope("Global");
        table.setFather(null);
        programOp.setSymbolTable(table);

        if(!programOp.getFunctions().isEmpty()){
            for(FunOp funOp : programOp.getFunctions()) {
                LinkedList<TypeOp> param = funOp.getFuncParams().stream().map(FuncParamsOp::getType).collect(Collectors.toCollection(LinkedList::new));
                SymbolRecord row = new SymbolRecord(funOp.getId().getName(), "function",
                        new SymbolType(param, funOp.getTypes()));
                programOp.getSymbolTable().addRecord(row);
            }
        }

        if(!programOp.getProcedures().isEmpty()){
            for (ProcedureOp procedureOp : programOp.getProcedures()){
                LinkedList<TypeOp> procParam = procedureOp.getParams().stream().map(ProcParamsOp::getType).
                        collect(Collectors.toCollection(LinkedList::new));
                SymbolRecord record = new SymbolRecord(procedureOp.getId().getName(), "procedure", new SymbolType(procParam,procedureOp.getParams(),1));
                programOp.getSymbolTable().addRecord(record);
            }
        }

        programOp.getVarDecls().forEach(varDeclOp -> varDeclOp.accept(this));
        programOp.getProcedures().forEach(procOp -> procOp.accept(this));
        programOp.getFunctions().forEach(funOp -> {
            try {
                funOp.accept(this);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });



        return null;
    }

    @Override
    public Object visit(TypeOp typeOp) {
        return null;
    }


    @Override
    public Object visit(VarDeclOp varDeclOp) {

        Iterator<IdOp> idIterator = varDeclOp.getIds().iterator();
        Iterator<Expr> constIterator = varDeclOp.getConstant().iterator();


        if(!varDeclOp.getConstant().isEmpty()){ //sono nel caso dell'ASSIGN
            if(varDeclOp.getConstant().size() != varDeclOp.getIds().size())
                throw new RuntimeException("Nella dichiarazione il numero degli Id non corrisponde al numero delle costanti.");
            else{
                while (idIterator.hasNext() && constIterator.hasNext()) {
                     SymbolRecord record= new SymbolRecord(idIterator.next().getName(), "var",
                            new SymbolType(new LinkedList<TypeOp>(List.of(new TypeOp(constIterator.next().type)))),true);
                     table.addRecord(record);
                }
            }

        }
        else { //sono nel caso: Ids COLON Type
            idIterator = varDeclOp.getIds().iterator();
            while(idIterator.hasNext()) {
                IdOp id = idIterator.next();
                SymbolRecord record = new SymbolRecord(id.getName(), "var",
                        new SymbolType(new LinkedList<TypeOp>(List.of(varDeclOp.getType()))),true);
                table.addRecord(record);
            }
        }

        return null;
    }


    @Override
    public Object visit(FunOp funOp) {

        SymbolTable father = table;
        funOp.setSymbolTable(new SymbolTable());
        SymbolTable tableFunction = funOp.getSymbolTable();
        tableFunction.setFather(this.table);
        tableFunction.setScope(funOp.getId().getName());

        if(!funOp.getFuncParams().isEmpty()){ //se ci sono parametri li aggiungo nella nuova tab
            for(FuncParamsOp funcParamsOp : funOp.getFuncParams()){
                SymbolRecord record = new SymbolRecord(funcParamsOp.getId().getName(), "func",
                        new SymbolType(new LinkedList<TypeOp>(List.of(funcParamsOp.getType()))),false);
                tableFunction.addRecord(record);
            }
        }

        if(funOp.getBody() != null){
            table = funOp.getSymbolTable();
            funOp.getBody().accept(this);
            table = father;
        }
        table = funOp.getSymbolTable().getFather();

        return null;
    }

    public Object visit(FuncParamsOp funcParamsOp){
        return null;
    }


    @Override
    public Object visit(ProcedureOp procedureOp) {

        SymbolTable father = table;
        procedureOp.setSymbolTable(new SymbolTable());
        SymbolTable procTable = procedureOp.getSymbolTable();
        procTable.setScope(procedureOp.getId().getName());
        procTable.setFather(this.table);

        table = procedureOp.getSymbolTable();
        if(!procedureOp.getParams().isEmpty()){
            for(ProcParamsOp procParamsOp : procedureOp.getParams())
                procParamsOp.accept(this);
        }

        if(procedureOp.getBody() != null){
            procedureOp.getBody().accept(this);
        }

        table = father;

        return null;
    }

    @Override
    public Object visit(ProcParamsOp procParamsOp) {

        Boolean type = (procParamsOp.getParamId().getOut()) ? true : false;
        SymbolRecord record = new SymbolRecord(procParamsOp.getParamId().getId().getName(),
                "var",new SymbolType(new LinkedList<TypeOp>(List.of(procParamsOp.getType()))), type);
        table.addRecord(record);

        return null;
    }

    @Override
    public Object visit(ProcParamIdOp procParamIdOp) {
        return null;
    }


    @Override
    public Object visit(AssignStatOp assignStatOp) {

        assignStatOp.getExprs().forEach(expr -> expr.accept(this));
        assignStatOp.getIds().forEach(idOp -> idOp.accept(this));

        return null;
    }

    @Override
    public Object visit(Statement statement) {
        return null;
    }

    @Override
    public Object visit(IterOp iterOp) throws Exception {
        return null;
    }


    @Override
    public Object visit(IfStatOp ifStatOp) {
        ifStatOp.setSymbolTable(new SymbolTable());
        SymbolTable ifTable = ifStatOp.getSymbolTable();
        ifTable.setScope("if-scope");
        ifTable.setFather(table);
        table = ifStatOp.getSymbolTable();
        ifStatOp.getExpr().accept(this);
        ifStatOp.getBody().accept(this);
        table = ifStatOp.getSymbolTable().getFather();

        if(!ifStatOp.getElifS().isEmpty()){
            for(ElifOp elifOp : ifStatOp.getElifS())
                elifOp.accept(this);
        }

        if( ifStatOp.getElseBody() != null){   //sono nell'else
            ifStatOp.setElseTable(new SymbolTable());
            SymbolTable elseTable = ifStatOp.getElseTable();
            elseTable.setScope("else-scope");
            elseTable.setFather(table);
            table = ifStatOp.getElseTable() ;
            ifStatOp.getElseBody().accept(this);
            table = ifStatOp.getSymbolTable().getFather();
        }

        return null;
    }

    @Override
    public Object visit(ElifOp elifOp) {

        elifOp.setSymbolTable(new SymbolTable());
        SymbolTable tableElif = elifOp.getSymbolTable();
        tableElif.setFather(table);
        tableElif.setScope("elif-scope");

        table = elifOp.getSymbolTable();
        if(elifOp.getExpr() != null)
            elifOp.getExpr().accept(this);
        if(elifOp.getBody() != null)
            elifOp.getBody().accept(this);

        table = elifOp.getSymbolTable().getFather();

        return null;
    }

    @Override
    public Object visit(WhileStatOp whileStatOp) {

        whileStatOp.setSymbolTable(new SymbolTable());
        SymbolTable whileTable = whileStatOp.getSymbolTable();
        whileTable.setScope("while-scope");
        whileTable.setFather(table);

        table = whileStatOp.getSymbolTable();

        if(whileStatOp.getBody() != null)
            whileStatOp.getBody().accept(this);
        if(whileStatOp.getExpr() != null)
            whileStatOp.getExpr().accept(this);

        table = whileStatOp.getSymbolTable().getFather();



        return null;
    }

    @Override
    public Object visit(ProcCallOp procCallOp) {
        procCallOp.getId().accept(this);
        if(procCallOp.getProcExprs() != null)
            procCallOp.getProcExprs().accept(this);
        return null;
    }

    @Override
    public Object visit(ProcExprsOp procExprsOp) {
        //TODO qui solo exp.accept
        procExprsOp.getExprs().forEach(expr -> expr.accept(this));
        //procExprsOp.getRefId().forEach(idOp -> idOp.accept(this));
        return null;
    }

    @Override
    public Object visit(ReadOp readOp) {
        readOp.getExprs().forEach(expr -> expr.accept(this));

        return null;
    }


    @Override
    public Object visit(ReturnStatOp returnStatOp) {
        returnStatOp.getExprs().forEach(expr -> expr.accept(this));

        return null;
    }

    @Override
    public Object visit(WriteOp writeOp) {
        writeOp.getIOArgs().forEach(args -> args.accept(this));
        return null;
    }


}
