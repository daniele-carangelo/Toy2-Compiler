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
import esercitazione5.SymbolTable.TypeUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class TypeVisitor implements Visitor{

    private SymbolTable symbolTable;

    @Override
    public Object visit(ProgramOp programOp) throws Exception {
        symbolTable = programOp.getSymbolTable();
        TypeUtils.initializeMap(); //inizializza hashMap dei tipi
        Optional<SymbolRecord> main = programOp.getSymbolTable().lookup("main");

        if(main.isEmpty())
            throw new RuntimeException("Main procedure not found.");
        else if(!main.get().getKind().equals("procedure"))
                throw new RuntimeException("Il main deve essere una procedura");

        programOp.getFunctions().forEach(funOp -> {
            try {
                funOp.accept(this);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        programOp.getProcedures().forEach(procedureOp -> {
            try {
                procedureOp.accept(this);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        programOp.getVarDecls().forEach(varDeclOp -> varDeclOp.accept(this));

        return null;
    }

    @Override
    public Object visit(StringOp stringOp) {
        return stringOp.type;
    }

    @Override
    public Object visit(Expr expr) {
        return null;
    }

    private String checkReturnFunCall(Expr expr)  {
        LinkedList<TypeOp> returns;
        if(expr instanceof FunCallOp) {
            FunCallOp funcall = (FunCallOp) expr;
            returns = (LinkedList<TypeOp>) funcall.accept(this);
            if (returns.size() > 1) {
                throw new RuntimeException("la chiamata a funzione " + funcall.getId().getName() + " restituisce più valori per l'operazione");
            }
            else
                return returns.get(0).getType();
        }

        return (String) expr.accept(this);
    }

    @Override
    public Object visit(DiffOp diffOp)  {
        String type1;
        String type2;
/*
        if(checkReturnFunCall(diffOp.getExpr1()).isEmpty())
            type1 = (String) diffOp.getExpr1().accept(this);
        else
            type1 = checkReturnFunCall(diffOp.getExpr1()).get(0);

        if(checkReturnFunCall(diffOp.getExpr2()).isEmpty())
            type2 = (String) diffOp.getExpr2().accept(this);
        else
            type2 = checkReturnFunCall(diffOp.getExpr2()).get(0);
*/
        type1 = checkReturnFunCall(diffOp.getExpr1());
        type2 = checkReturnFunCall(diffOp.getExpr2());
        String op = diffOp.type;


        return TypeUtils.getType(op, type1, type2);
    }

    @Override
    public Object visit(DivOp divOp)  {
        String type1;
        String type2;

        type1 = checkReturnFunCall(divOp.getExpr1());
        type2 = checkReturnFunCall(divOp.getExpr2());

        String op = divOp.type;


        return TypeUtils.getType(op, type1, type2);
    }

    @Override
    public Object visit(MulOp mulOp)  {
        String type1;
        String type2;
        type1 = checkReturnFunCall(mulOp.getExpr1());
        type2 = checkReturnFunCall(mulOp.getExpr2());

        String op = mulOp.type;


        return TypeUtils.getType(op, type1, type2);
    }

    @Override
    public Object visit(UminusOp uminusOp)  {
        String type1;
        type1 = checkReturnFunCall(uminusOp.getExpr());

        String op = uminusOp.type;

        return TypeUtils.getType(op, type1);
    }

    @Override
    public Object visit(AndOp andOp)  {
        String type1;
        String type2;
        type1 = checkReturnFunCall(andOp.getExpr1());
        type2 = checkReturnFunCall(andOp.getExpr2());

        String op = andOp.type;


        return TypeUtils.getType(op, type1, type2);
    }

    @Override
    public Object visit(EQOp eqOp)  {
        String type1;
        String type2;
        type1 = checkReturnFunCall(eqOp.getExpr1());
        type2 = checkReturnFunCall(eqOp.getExpr2());

        String op = eqOp.type;


        return TypeUtils.getType(op, type1, type2);
    }

    @Override
    public Object visit(GEOp geOp)  {
        String type1;
        String type2;
        type1 = checkReturnFunCall(geOp.getExpr1());
        type2 = checkReturnFunCall(geOp.getExpr2());

        String op = geOp.type;


        return TypeUtils.getType(op, type1, type2);
    }

    @Override
    public Object visit(GTOp gtOp)  {
        String type1;
        String type2;
        type1 = checkReturnFunCall(gtOp.getExpr1());
        type2 = checkReturnFunCall(gtOp.getExpr2());

        String op = gtOp.type;


        return TypeUtils.getType(op, type1, type2);
    }

    @Override
    public Object visit(LEOp leOp)  {
        String type1;
        String type2;
        type1 = checkReturnFunCall(leOp.getExpr1());
        type2 = checkReturnFunCall(leOp.getExpr2());

        String op = leOp.type;


        return TypeUtils.getType(op, type1, type2);
    }

    @Override
    public Object visit(LTOp ltOp)  {
        String type1;
        String type2;
        type1 = checkReturnFunCall(ltOp.getExpr1());
        type2 = checkReturnFunCall(ltOp.getExpr2());

        String op = ltOp.type;


        return TypeUtils.getType(op, type1, type2);
    }

    @Override
    public Object visit(NEOp neOp)  {
        String type1;
        String type2;
        type1 = checkReturnFunCall(neOp.getExpr1());
        type2 = checkReturnFunCall(neOp.getExpr2());

        String op = neOp.type;


        return TypeUtils.getType(op, type1, type2);
    }

    @Override
    public Object visit(NotOp notOp)  {
        String type1;
        type1 = checkReturnFunCall(notOp.getExpr());

        String op = notOp.type;

        return TypeUtils.getType(op, type1);
    }

    @Override
    public Object visit(OrOp orOp)  {
        String type1;
        String type2;
        type1 = checkReturnFunCall(orOp.getExpr1());
        type2 = checkReturnFunCall(orOp.getExpr2());

        String op = orOp.type;


        return TypeUtils.getType(op, type1, type2);
    }

    @Override
    public Object visit(FunCallOp funCallOp)  {
        //prendo i tipi dei parametri nella firma della funzione
        //SymbolType typeFunc = (SymbolType) funCallOp.getId().accept(this);
       Optional<SymbolRecord> typeRecord= symbolTable.lookup((String )funCallOp.getId().getName());
       SymbolType typeFunc= typeRecord.get().getSymbolType();

        Iterator<TypeOp> typeOpIterator = typeFunc.getInputType().iterator();
        if(funCallOp.getExprs().size() > typeFunc.getInputType().size())
            throw new RuntimeException("Il numero di parametri della chiamata alla funzione:"+ funCallOp.getId().getName() +" è superiore ai parametri della firma");
        else if(funCallOp.getExprs().size() < typeFunc.getInputType().size())
            throw new RuntimeException("Il numero di parametri della chiamata alla funzione:"+ funCallOp.getId().getName() +" è inferiore ai parametri della firma");

        for(Expr exprCall : funCallOp.getExprs()){

            if(exprCall instanceof FunCallOp){
            FunCallOp funCheck= (FunCallOp) exprCall;
            SymbolType funCheckType = (SymbolType) funCheck.getId().accept(this);
                if((funCheckType.getOutputType().size() != 1))
                    throw new RuntimeException("chiamata a funzione invalid: la chiamata come parametro restituisce più di un valore");
                TypeOp funType = funCheckType.getOutputType().get(0);
                    if(!typeOpIterator.next().getType().equalsIgnoreCase(funType.getType()))
                        throw new RuntimeException("tipi della chiamata funzione diversi rispetto la firma");
                }
            else {
                String type = (String) exprCall.accept(this);
                if(typeOpIterator.hasNext())
                    if(!type.equals(typeOpIterator.next().getType()))
                        throw new RuntimeException("tipi della chiamata funzione diversi rispetto la firma");

            }

        }

        return typeFunc.getOutputType();
    }

    @Override
    public Object visit(IdOp idOp) {
        String type = symbolTable.typeOfId(idOp).getOutputType().get(0).getType();
        idOp.type = type;
        //return symbolTable.typeOfId(idOp);
        return type;
    }

    @Override
    public Object visit(ParOp parOp)  {

        return  (String) parOp.getExpr().accept(this);
    }

    @Override
    public Object visit(BodyOp bodyOp) {

        if(!bodyOp.getStmt().isEmpty())
            bodyOp.getStmt().forEach(statement -> statement.accept(this));

        if(!bodyOp.getVarDecl().isEmpty())
            bodyOp.getVarDecl().forEach(vars -> vars.accept(this));

        return null;
    }

    @Override
    public Object visit(FuncParamsOp funcParamsOp) {
        return null;
    }

    @Override
    public Object visit(RealOp realOp) {
        return realOp.type;
    }

    @Override
    public Object visit(TrueOp trueOp) {
        return trueOp.type;
    }

    @Override
    public Object visit(AddOp addOp)  {
        String type1;
        String type2;
        type1 = checkReturnFunCall(addOp.getExpr1());
        type2 = checkReturnFunCall(addOp.getExpr2());

        String op = addOp.type;

        if(type1.equalsIgnoreCase("string") || type2.equalsIgnoreCase("string"))
            addOp.setConcat(true);

        return TypeUtils.getType(op, type1, type2);
    }

    @Override
    public Object visit(FalseOp falseOp) {
        return falseOp.type;
    }

    @Override
    public Object visit(IntegerOp integerOp) {
        return integerOp.type;
    }

    @Override
    public Object visit(TypeOp typeOp) {
        return typeOp.getType();
    }

    private void checkReturn(BodyOp bodyOp,LinkedList<ReturnStatOp> returnStmt){ //check se nel body c'è almeno un return (recursive)
        if(!bodyOp.getStmt().isEmpty()){
        for(Statement stmt : bodyOp.getStmt()) {
            if (stmt instanceof ReturnStatOp) {
                returnStmt.add((ReturnStatOp) stmt);
            }
            else if (stmt instanceof IfStatOp) {
                if(((IfStatOp) stmt).getBody() != null)
                    checkReturn(((IfStatOp) stmt).getBody(), returnStmt);

                if (((IfStatOp) stmt).getElifS() != null) {
                    for (ElifOp elifOp : ((IfStatOp) stmt).getElifS()) {
                        checkReturn(elifOp.getBody(), returnStmt);
                    }
                }
                if(((IfStatOp) stmt).getElseBody() != null)
                    checkReturn(((IfStatOp) stmt).getElseBody(), returnStmt);
            }
            else if(stmt instanceof WhileStatOp){
                    if(((WhileStatOp) stmt).getBody() != null)
                        checkReturn(((WhileStatOp) stmt).getBody(), returnStmt);
            }
          }
        }
     }

    private void checkAssign(BodyOp bodyOp, LinkedList<AssignStatOp> assignStatOps){
        if(!bodyOp.getStmt().isEmpty()){
            for(Statement stmt : bodyOp.getStmt()) {
                if (stmt instanceof AssignStatOp) {
                    assignStatOps.add((AssignStatOp) stmt);
                }
                else if (stmt instanceof IfStatOp) {
                    if(((IfStatOp) stmt).getBody() != null)
                        checkAssign(((IfStatOp) stmt).getBody(), assignStatOps);

                    if (((IfStatOp) stmt).getElifS() != null) {
                        for (ElifOp elifOp : ((IfStatOp) stmt).getElifS()) {
                            checkAssign(elifOp.getBody(), assignStatOps);
                        }
                    }
                    if(((IfStatOp) stmt).getElseBody() != null)
                        checkAssign(((IfStatOp) stmt).getElseBody(), assignStatOps);
                }
                else if(stmt instanceof WhileStatOp){
                    if(((WhileStatOp) stmt).getBody() != null)
                        checkAssign(((WhileStatOp) stmt).getBody(), assignStatOps);
                }
            }
        }

    }

    @Override
    public Object visit(FunOp funOp)  {
        symbolTable = funOp.getSymbolTable();
/*
        //check se nella dichiarazione c'è almeno un return
        LinkedList<ReturnStatOp> returnStmt = new LinkedList<>();
        for(Statement stmt : funOp.getBody().getStmt()){
            if(stmt instanceof ReturnStatOp)
                returnStmt.add((ReturnStatOp) stmt);
            if(stmt instanceof IfStatOp){

            }
    }
        */
        LinkedList<ReturnStatOp> returnStmt = new LinkedList<>();
        checkReturn(funOp.getBody(),returnStmt);
        if(returnStmt.isEmpty())
            throw new RuntimeException("Nella funzione: " + funOp.getId().getName() + " deve esserci almeno un return");

        //check numero tipi di ritorno nel return
        for(ReturnStatOp returnStatOp : returnStmt) {
            if (funOp.getTypes().size() != returnStatOp.getExprs().size())
                throw new RuntimeException("Numero delle espressioni del return diverso dai tipi di ritorno della funzione: " + funOp.getId().getName());
        }

        //controllo se i tipi del return sono compatibili con i return della funzione

        for(ReturnStatOp returnStatOp : returnStmt){
            Iterator<TypeOp> typeOpIterator = funOp.getTypes().iterator();

            LinkedList<String> exprReturn = (LinkedList<String>) returnStatOp.accept(this);
            Iterator<String> iteratorReturn = exprReturn.iterator();


            while (typeOpIterator.hasNext() && iteratorReturn.hasNext()){
                if(!typeOpIterator.next().getType().equalsIgnoreCase(iteratorReturn.next()))
                    throw new RuntimeException("I tipi di ritorno della funzione: " + funOp.getId().getName() + " non coincidono con quelli dichiarati");
            }

        }

        //check se i parametri vengono modificati.
        LinkedList<AssignStatOp> assignStatOps = new LinkedList<>();
        checkAssign(funOp.getBody(),assignStatOps);

        List<IdOp> paramsOps = funOp.getFuncParams().stream().map(funcParamsOp -> funcParamsOp.getId()).toList();
        List<IdOp> idAssign = new LinkedList<>();

        boolean check = false ;
        for(AssignStatOp assign : assignStatOps){
            assign.getIds().stream().forEach(idOp -> idAssign.add(idOp));
        }

        for(IdOp ids : paramsOps)
            for(IdOp id2 : idAssign){
                if(ids.getName().equals(id2.getName()))
                    check = true;
            }

        if(check)
            throw new RuntimeException("Nelle funzioni non è possibbile modificare i parametri");

        symbolTable = funOp.getSymbolTable();
        funOp.getBody().accept(this);
        return null;
    }

    @Override
    public Object visit(IterOp iterOp)  {
        return null;
    }

    @Override
    public Object visit(ProcedureOp procedureOp) {
        symbolTable = procedureOp.getSymbolTable();
        //check se ci sono return
        LinkedList<ReturnStatOp> returnStmt = new LinkedList<>();
        checkReturn(procedureOp.getBody(),returnStmt);

        if(returnStmt.size() != 0)
            throw new RuntimeException("Le Procedure non posso avere RETURN statement");

        symbolTable = procedureOp.getSymbolTable();
        procedureOp.getBody().accept(this);

        return null;
    }

    @Override
    public Object visit(ProcParamsOp procParamsOp) {
        return null;
    }

    @Override
    public Object visit(ProcParamIdOp procParamIdOp) {
        return null;
    }


    @Override
    public Object visit(ReturnStatOp returnStatOp) {
        LinkedList<String> typeReturn = new LinkedList<>();

        returnStatOp.getExprs().forEach(expr -> {
            if(expr instanceof FunCallOp funCallOp) {
               LinkedList<TypeOp> typeOps = (LinkedList<TypeOp>) funCallOp.accept(this);
                typeReturn.addAll(typeOps.stream().map(TypeOp::getType).toList());
            }
            else
                typeReturn.add((String) expr.accept(this));
        });
        return typeReturn;
    }

    @Override
    public Object visit(WriteOp writeOp) {

        writeOp.getIOArgs().forEach(expr -> {
                if(expr instanceof FunCallOp funCall && ((LinkedList<TypeOp>)funCall.accept(this)).size() > 1)
                    throw new RuntimeException("La funzione chiamata nel return ritorna valori multipli");
                    }
                    );

        writeOp.getIOArgs().forEach(expr -> expr.accept(this));

        return null;
    }

    @Override
    public Object visit(VarDeclOp varDeclOp) {
        //ckeck del numero delle costanti fatto in ScopeVisitor
        return null;
    }
//-------------------------------------------------------------------
    @Override
    public Object visit(AssignStatOp assignStatOp)  {
        String type;
        Iterator<IdOp> ids = assignStatOp.getIds().iterator();
        LinkedList<Expr> exprs = assignStatOp.getExprs();
        boolean check = false;
        if(assignStatOp.getExprs().size() == 1 && assignStatOp.getExprs().get(0) instanceof FunCallOp ){
            check=true;
            FunCallOp funCall= (FunCallOp) assignStatOp.getExprs().get(0);
            LinkedList<TypeOp> typesFunCall = (LinkedList<TypeOp>) funCall.accept(this);
            if(assignStatOp.getIds().size() != typesFunCall.size())
                throw new RuntimeException("Numero di ID nell'assegnazione diverso dal numero dei valori ritornati dalla funzione");
            Iterator<TypeOp> typeOpIterator = typesFunCall.iterator();
            Iterator<IdOp> idOpIterator = assignStatOp.getIds().iterator();
            while (typeOpIterator.hasNext() && idOpIterator.hasNext()){
                String idType = (String) idOpIterator.next().accept(this);
                if(!idType.equalsIgnoreCase((typeOpIterator.next()).getType()))
                    throw new RuntimeException("i tipi di ritorno della funzione non corrispondono nell'assegnazione");

            }
        }
        else if(assignStatOp.getIds().size() != assignStatOp.getExprs().size())
                throw new RuntimeException("Numero di ID nell'assegnazione diverso dal numero delle EXPR");

            if(!check)
            for (Expr expr : exprs) {
                type = checkReturnFunCall(expr);
                IdOp name = ids.next();
                Optional<SymbolRecord> record1 = symbolTable.lookup(name.getName());
                if (record1.get().getKind().equals("function") || record1.get().getKind().equals("procedure"))
                    throw new RuntimeException("Impossibile assegnare ad una funziona o procedura");

                String typeId = (String) name.accept(this);
                Optional<SymbolRecord> record = symbolTable.lookup(name.getName());
                if (record.isPresent())
                    if (!record.get().getProperties())
                        throw new RuntimeException("L'ID: " + name.getName() + " non può essere assegnato");

                if (!type.equalsIgnoreCase(typeId))
                    if (!(typeId.equalsIgnoreCase("float") && type.equalsIgnoreCase("int")))
                        throw new RuntimeException("Il type di: " + name.getName() + " non corrisponde con il tipo dell'espressione associata");


            }


        return null;
    }

    @Override
    public Object visit(ElifOp elifOp)  {

        String type = (String )elifOp.getExpr().accept(this);
        if(!type.equalsIgnoreCase("boolean"))
            throw new RuntimeException("La condizione dell'ElseIF non è di tipo Booleano");

        if(elifOp.getBody() != null) {
            symbolTable = elifOp.getSymbolTable();
            elifOp.getBody().accept(this);
            symbolTable = elifOp.getSymbolTable().getFather();
        }
        return null;
    }

    @Override
    public Object visit(IfStatOp ifStatOp)  {
        String type = (String) ifStatOp.getExpr().accept(this);
        if(!type.equalsIgnoreCase("boolean"))
            throw new RuntimeException("La condizione dell'IF non è di tipo Booleano");

        symbolTable = ifStatOp.getSymbolTable();
        if(ifStatOp.getBody() != null)
            ifStatOp.getBody().accept(this);

        symbolTable = ifStatOp.getSymbolTable().getFather();

        ifStatOp.getElifS().forEach(elifOp -> elifOp.accept(this));


        if(ifStatOp.getElseBody() != null) {
            symbolTable = ifStatOp.getElseTable();
            ifStatOp.getElseBody().accept(this);
            symbolTable = ifStatOp.getElseTable().getFather();
        }

        return null;
    }

    @Override
    public Object visit(Statement statement) {
        return null;
    }

    @Override
    public Object visit(ProcCallOp procCallOp)  {

        //tipi della firma della procedura chiamata
        Optional<SymbolRecord> typeRecord= symbolTable.lookup(procCallOp.getId().getName());
        SymbolType typeFunc= typeRecord.get().getSymbolType();
        Iterator<ProcParamsOp> idFirma = null;


        LinkedList<ProcParamsOp> paramsOps = typeRecord.get().getSymbolType().getParamsOps();
        if(paramsOps != null) {
            idFirma = paramsOps.iterator();


            if (procCallOp.getProcExprs().getExprs().size() > typeFunc.getInputType().size())
                throw new RuntimeException("Il numero di parametri della chiamata alla procedura:" + procCallOp.getId().getName() + " è superiore ai parametri della firma");
            else if (procCallOp.getProcExprs().getExprs().size() < typeFunc.getInputType().size())
                throw new RuntimeException("Il numero di parametri della chiamata alla procedura:" + procCallOp.getId().getName() + " è inferiore ai parametri della firma");

            Iterator<TypeOp> iteratorFirma = typeFunc.getInputType().iterator();
            Iterator<Expr> iteratorCall = procCallOp.getProcExprs().getExprs().iterator();

            while (iteratorCall.hasNext() && iteratorFirma.hasNext()) {
                Expr exprCall = iteratorCall.next();
/*
            if(exprCall instanceof FunCallOp){
                String typeCheck = checkReturnFunCall(exprCall);
                FunCallOp funCallOp = (FunCallOp) exprCall;
                LinkedList<TypeOp> returnType = (LinkedList<TypeOp>) funCallOp.accept(this);
                if(returnType.size() > 1)
                    throw  new RuntimeException("La FunCall passata nella ProcCall restituisce più valori");


            }
*/
                TypeOp typeFirma = iteratorFirma.next();
                //String typeCheck = (String) exprCall.accept(this);
                String typeCheck = checkReturnFunCall(exprCall);

                //check tipi
                if (!typeCheck.equalsIgnoreCase(typeFirma.getType()))
                    throw new RuntimeException("I tipi della chiamata alla procedura non corrispondono ai tipi nella firma");


                boolean idCheck = idFirma.next().getParamId().getOut();
                if (exprCall instanceof IdOp) {
                    IdOp idCall = (IdOp) exprCall;
                    if (!idCall.isRef() && idCheck)
                        throw new RuntimeException("L'id: " + idCall.getName() + " nella chiamata deve essere passato per riferimento");
                    if (idCall.isRef() && !idCheck)
                        throw new RuntimeException("L'id: " + idCall.getName() + " nella chiamata non deve essere passato per riferimento");
                }

            }
        }

        return null;
    }

    @Override
    public Object visit(ProcExprsOp procExprsOp) {
        return null;
    }

    @Override
    public Object visit(ReadOp readOp) {
        for(Expr expr : readOp.getExprs()){
            if(expr.getDollar() && !(expr instanceof IdOp) )
                throw new RuntimeException("L'expr all'interno del $() deve essere necessariamente un ID");
            if(expr instanceof IdOp && expr.getDollar()){
                IdOp id = (IdOp) expr;
                Optional<SymbolRecord> record = symbolTable.lookup(id.getName());
                if(!record.get().getProperties())
                    throw new RuntimeException("Impossibile assegnare la lettura alla variabile: " + id.getName() + " perchè immutabile");
            }
        }

        return null;
    }

    @Override
    public Object visit(WhileStatOp whileStatOp)  {

        String type = (String) whileStatOp.getExpr().accept(this);
        if(!type.equalsIgnoreCase("boolean"))
            throw new RuntimeException("La condizione del while non è di tipo Booleano");

        whileStatOp.getBody().accept(this);

        return null;
    }


}
