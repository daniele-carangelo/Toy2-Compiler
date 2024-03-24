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

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class CodeGenVisitor implements Visitor {
    private SymbolTable table;
    private FileWriter fileWriter;
    private String nameFile;
    private TypeVisitor typeVisitor;


    public CodeGenVisitor(String nameFile, TypeVisitor typeVisitor){
        this.nameFile = nameFile;
        this.typeVisitor = typeVisitor;
    }

    @Override
    public Object visit(ProgramOp programOp) throws Exception {
        String path = "test_files" + File.separator + "c_out" + File.separator;

        table = programOp.getSymbolTable();


            if (!Files.exists(Path.of(path)))
                new File(path).mkdirs();

            File file = new File(path + File.separator + this.nameFile + ".c");

            file.createNewFile();

            fileWriter = new FileWriter(file);
            writeLibrary();
            createStruct(programOp.getFunctions());

            addUtilityFunction();



            for(FunOp funop: programOp.getFunctions()){
                table = funop.getSymbolTable();
                if(funop.getTypes().size() > 1) {
                    fileWriter.write("returnOf");
                    funop.getId().accept(this);
                }
                else
                    funop.getTypes().get(0).accept(this);

                fileWriter.write(" ");
                funop.getId().accept(this);
                fileWriter.write("(");
                Collections.reverse(funop.getFuncParams());
                if(!funop.getFuncParams().isEmpty())
                    for(int i = 0; i<funop.getFuncParams().size(); i++){  //TODO check -1?
                        funop.getFuncParams().get(i).accept(this);
                        if((funop.getFuncParams().size() - i) != 1)
                            fileWriter.write(",");
                    }
                fileWriter.write(");\n");
                table = funop.getSymbolTable().getFather();
            }

            for(ProcedureOp procedureOp: programOp.getProcedures()){
                table = procedureOp.getSymbolTable();
                fileWriter.write("void ");
                procedureOp.getId().accept(this);
                fileWriter.write(" (");
                Collections.reverse(procedureOp.getParams());
                if(!procedureOp.getParams().isEmpty())
                    for(int i=0; i < procedureOp.getParams().size(); i++){
                        procedureOp.getParams().get(i).getType().accept(this);
                        fileWriter.write(" ");
                        /*
                        if(procedureOp.getParams().get(i).getParamId().getOut() && !procedureOp.getParams().get(i).getType().getType().equalsIgnoreCase("string"))
                            fileWriter.write("*"); */
                        procedureOp.getParams().get(i).getParamId().getId().accept(this);

                        if((procedureOp.getParams().size() - i) != 1)
                            fileWriter.write(",");
                    }
                    fileWriter.write(");\n");
                    table = procedureOp.getSymbolTable().getFather();

            }

            programOp.getVarDecls().forEach(varDeclOp -> varDeclOp.accept(this));
            fileWriter.write("\n");
            programOp.getFunctions().forEach(funOp -> {
                try {
                    funOp.accept(this);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            fileWriter.write("\n");
            programOp.getProcedures().forEach(procedureOp -> {
                try {
                    procedureOp.accept(this);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            fileWriter.close();


        return null;
    }


    private void createStruct(LinkedList<FunOp> funList ){

        if(!funList.isEmpty())
            for(FunOp funOp : funList){
                try {
                    if(funOp.getTypes().size() > 1) {
                        fileWriter.write("typedef struct{\n");
                        for (int i = 0; i < funOp.getTypes().size(); i++) {
                            funOp.getTypes().get(i).accept(this);
                            fileWriter.write(" returnValue" + (i+1)  + ";\n");
                        }
                        fileWriter.write("} ");
                        fileWriter.write("returnOf");
                        funOp.getId().accept(this);
                        fileWriter.write( ";\n\n");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
    }

    private void addUtilityFunction() throws IOException {

        fileWriter.write("char* intToString(int numero);\n");
        fileWriter.write("char* floatToString(float numero);\n");
        fileWriter.write("char* boolToString(bool numero);\n");
        fileWriter.write("char* StringConcat(char* str1, char* str2);\n");


        fileWriter.write("char* intToString(int numero) {\n");
        fileWriter.write("char *stringa = malloc(sizeof(char)*512);\n");
        fileWriter.write("sprintf(stringa, \"%d\", numero);\n");
        fileWriter.write("return stringa;}\n");

        fileWriter.write("char* floatToString(float numero) {\n");
        fileWriter.write("char *stringa = malloc(sizeof(char)*512);\n");
        fileWriter.write("sprintf(stringa, \"%f\", numero);\n");
        fileWriter.write("return stringa;}\n");

        fileWriter.write("char* boolToString(bool numero) {\n");
        fileWriter.write("char *stringa = malloc(sizeof(char)*512);\n");
        fileWriter.write("sprintf(stringa, \"%d\", numero);\n");
        fileWriter.write("return stringa;}\n");

        fileWriter.write("char* StringConcat(char* str1, char* str2){\n");
        fileWriter.write("char *NewString = malloc(sizeof(char)*512);\n");
        fileWriter.write("strcpy(NewString,str1);\n");
        fileWriter.write("strcat(NewString,str2);\n");
        fileWriter.write("return NewString;}\n");

    }

    @Override
    public Object visit(IfStatOp ifStatOp) {
try {
    fileWriter.write("if ( ");
    ifStatOp.getExpr().accept(this);
    fileWriter.write(" )");
    fileWriter.write("\n");
    table = ifStatOp.getSymbolTable();
    ifStatOp.getBody().accept(this);
    table = ifStatOp.getSymbolTable().getFather();
    ifStatOp.getElifS().forEach(elifs -> elifs.accept(this));

    if (ifStatOp.getElseBody() != null) {
        fileWriter.write("else");
        fileWriter.write("\n");
        table = ifStatOp.getElseTable();
        ifStatOp.getElseBody().accept(this);
        table = ifStatOp.getElseTable().getFather();
    }

} catch (IOException e) {
    throw new RuntimeException(e);
}
        return null;
    }

    @Override
    public Object visit(ElifOp elifOp)  {

        try {
            fileWriter.write("else if (");

        elifOp.getExpr().accept(this);
        fileWriter.write(" )");
        table = elifOp.getSymbolTable();
        elifOp.getBody().accept(this);
        table = elifOp.getSymbolTable().getFather();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(FalseOp falseOp)  {
        try {
            fileWriter.write("false");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(TrueOp trueOp) {
        try {
            fileWriter.write("true");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(IntegerOp integerOp) {
        try {
            fileWriter.write(Integer.toString(integerOp.getAttribute()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(RealOp realOp)  {
        try {
            fileWriter.write(String.valueOf(realOp.getAttribute()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(ParOp parOp)  {
        try {
            fileWriter.write("(");

        parOp.getExpr().accept(this);
        fileWriter.write(")");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(Expr expr) {
        return null;
    }

    @Override
    public Object visit(StringOp stringOp)  {
        try {
            fileWriter.write("\"");
            fileWriter.write(stringOp.getAttribute());
            fileWriter.write("\"");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(IdOp idOp)  {
        try {

            Optional<SymbolRecord> recordType= table.getSymbolRecords().stream().filter(record -> record.getSymbol().equals(idOp.getName())).findFirst();
            if(recordType.isPresent())
                if(recordType.get().getProperties() && !isString(idOp) && recordType.get().getKind().equalsIgnoreCase("varProc")) {
                    fileWriter.write("*");
                }



            fileWriter.write(idOp.getName());
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private void castType(Expr expr) throws IOException {
        String type;



        if(expr instanceof FunCallOp funCallOp)
            type= table.typeOfId(funCallOp.getId()).getOutputType().get(0).getType();
        else
            type = expr.type;

        switch (type) {
            case "int":
                fileWriter.write("intToString(");
                break;
            case "float":
                fileWriter.write("floatToString(");
                break;
            case "Boolean":
                fileWriter.write("boolToString(");
                break;
        }
        expr.accept(this);
        fileWriter.write(")");


    }

    private void stringConcat(AddOp addOp) throws IOException {
       String type1;
       String type2;
        fileWriter.write("StringConcat(");

        if(addOp.getExpr1() instanceof StringOp)
           addOp.getExpr1().accept(this);
        else if(addOp.getExpr1() instanceof AddOp)
            addOp.getExpr1().accept(this);
        else if(addOp.getExpr1() instanceof IdOp idOp && table.typeOfId(idOp).getOutputType().get(0).getType().equalsIgnoreCase("string"))
            addOp.getExpr1().accept(this);

            else
            castType(addOp.getExpr1());
        /*
        {
            if(addOp.getExpr1() instanceof FunCallOp funCallOp)
                type1 = table.typeOfId(funCallOp.getId()).getOutputType().get(0).getType();
            else
                type1 = addOp.getExpr1().type;

                switch (type1) {
                    case "int":
                        fileWriter.write("intToString(");
                        break;
                    case "float":
                        fileWriter.write("floatToString(");
                        break;
                    case "Boolean":
                        fileWriter.write("boolToString(");
                        break;
                }
                addOp.getExpr1().accept(this);
                fileWriter.write(")");

        }
*/

        fileWriter.write(",");


        if(addOp.getExpr2() instanceof StringOp)
            addOp.getExpr2().accept(this);
        else if(addOp.getExpr2() instanceof AddOp)
            addOp.getExpr2().accept(this);
        else if(addOp.getExpr2() instanceof IdOp idOp && table.typeOfId(idOp).getOutputType().get(0).getType().equalsIgnoreCase("string"))
            addOp.getExpr2().accept(this);

        else
            castType(addOp.getExpr2());
        /*
        {
            if(addOp.getExpr2() instanceof FunCallOp funCallOp)
                type2 = table.typeOfId(funCallOp.getId()).getOutputType().get(0).getType();
            else
                type2 = addOp.getExpr2().type;

                switch (type2) {
                    case "int":
                        fileWriter.write("intToString(");
                        break;
                    case "float":
                        fileWriter.write("floatToString(");
                        break;
                    case "Boolean":
                        fileWriter.write("boolToString(");
                        break;
                }
                addOp.getExpr2().accept(this);
                fileWriter.write(")");

        }

         */


        fileWriter.write(")");
    }


    @Override
    public Object visit(AddOp addOp)  {
        try {

/*
        if((isString(addOp.getExpr1())  || isString(addOp.getExpr2()) ) || checkType ){
            System.out.println(addOp.getExpr1() + " " + addOp.getExpr2());
            stringConcat(addOp);
            if(addOp.getExpr1() instanceof AddOp && checkType && !isString(addOp.getExpr2())){
                checkType = false;
            }
        }
        else {
            addOp.getExpr1().accept(this);

            fileWriter.write("+");

            addOp.getExpr2().accept(this);
        }

*/
            if(addOp.isConcat()) {

                stringConcat(addOp);


            }
            else{
                addOp.getExpr1().accept(this);

                fileWriter.write("+");

                addOp.getExpr2().accept(this);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(MulOp mulOp) {
        try {
        mulOp.getExpr1().accept(this);
        fileWriter.write("*");
        mulOp.getExpr2().accept(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(DivOp divOp) {
        try {
        divOp.getExpr1().accept(this);
        fileWriter.write("/");
            divOp.getExpr2().accept(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(DiffOp diffOp)  {
        try {
        diffOp.getExpr1().accept(this);
        fileWriter.write("-");
        diffOp.getExpr2().accept(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(GTOp gtOp)  {
        try {
        gtOp.getExpr1().accept(this);
        fileWriter.write(">");

            gtOp.getExpr2().accept(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(GEOp geOp) {
        try {
        geOp.getExpr1().accept(this);
        fileWriter.write(">=");
        geOp.getExpr2().accept(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(LEOp leOp)  {
        try {
        leOp.getExpr1().accept(this);
        fileWriter.write("<=");
        leOp.getExpr2().accept(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(LTOp ltOp)  {
        try {
        ltOp.getExpr1().accept(this);
        fileWriter.write("<");
        ltOp.getExpr2().accept(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(OrOp orOp)  {
        try {
        orOp.getExpr1().accept(this);
        fileWriter.write("||");
        orOp.getExpr2().accept(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(AndOp andOp)  {
        try {
        andOp.getExpr1().accept(this);
        fileWriter.write("&&");
        andOp.getExpr2().accept(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(NotOp notOp)  {
        try {
            fileWriter.write("!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(UminusOp uminusOp) {
        try {
            fileWriter.write("-");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(EQOp eqOp) {
        try {
        if(isString(eqOp.getExpr1()) && isString(eqOp.getExpr2())){
            fileWriter.write("strcmp(");
            eqOp.getExpr1().accept(this);
            fileWriter.write(", ");
            eqOp.getExpr2().accept(this);
            fileWriter.write(") == 0");
        }
        else {
            eqOp.getExpr1().accept(this);
            fileWriter.write("==");
            eqOp.getExpr2().accept(this);
        }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(NEOp neOp) {
        try {
            if(isString(neOp.getExpr1()) && isString(neOp.getExpr2())){
                fileWriter.write("strcmp(");
                neOp.getExpr1().accept(this);
                fileWriter.write(", ");
                neOp.getExpr2().accept(this);
                fileWriter.write(") != 0");
            }
            else
                fileWriter.write("!=");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public Object visit(FunCallOp funCallOp)  {
        try {
        funCallOp.getId().accept(this);
        fileWriter.write("(");
Collections.reverse(funCallOp.getExprs());
        if(funCallOp.getExprs().size() > 0) {
            for (int i=0;i < funCallOp.getExprs().size(); i++) {
                funCallOp.getExprs().get(i).accept(this);
                if((funCallOp.getExprs().size() - i) != 1)
                    fileWriter.write(",");
            }
        }

            fileWriter.write(")");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


    @Override
    public Object visit(BodyOp bodyOp) {

        try {

            Collections.reverse(bodyOp.getStmt());
            Collections.reverse(bodyOp.getVarDecl());
            fileWriter.write("{");

        fileWriter.write("\n");

         if(!bodyOp.getVarDecl().isEmpty())
             bodyOp.getVarDecl().forEach(vars -> vars.accept(this));

        if(!bodyOp.getStmt().isEmpty())
            bodyOp.getStmt().forEach(statement -> statement.accept(this));

        fileWriter.write("}\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(FuncParamsOp funcParamsOp) {
        try {
        funcParamsOp.getType().accept(this);
        fileWriter.write(" ");
        funcParamsOp.getId().accept(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(FunOp funOp) {
        try {

        //firma funzione
        if(funOp.getTypes().size() > 1){
            fileWriter.write("returnOf");
            funOp.getId().accept(this);
            fileWriter.write(" ");
            funOp.getId().accept(this);
        }
        else{
            funOp.getTypes().get(0).accept(this);
            fileWriter.write(" ");
            funOp.getId().accept(this);
        }

        fileWriter.write("(");

            if(!funOp.getFuncParams().isEmpty()) {
                for (int i=0;i < funOp.getFuncParams().size(); i++) {
                    funOp.getFuncParams().get(i).accept(this);
                    if((funOp.getFuncParams().size() - i) != 1)
                        fileWriter.write(",");
                }
            }

            fileWriter.write(")");
            table = funOp.getSymbolTable();
            funOp.getBody().accept(this);
            table = funOp.getSymbolTable().getFather();

            fileWriter.write("\n");
            fileWriter.write("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(IterOp iterOp)  {
        return null;
    }

    @Override
    public Object visit(ProcedureOp procedureOp)  {
        try {
        fileWriter.write("void ");
        procedureOp.getId().accept(this);
        fileWriter.write("(");

        table = procedureOp.getSymbolTable();

        if(!procedureOp.getParams().isEmpty()) {
            for (int i=0;i < procedureOp.getParams().size(); i++) {
                procedureOp.getParams().get(i).accept(this);
                if((procedureOp.getParams().size() - i) != 1)
                    fileWriter.write(",");
            }
        }

            fileWriter.write(")");


        procedureOp.getBody().accept(this);
            fileWriter.write("\n");
            fileWriter.write("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        table = procedureOp.getSymbolTable().getFather();


        return null;
    }

    @Override
    public Object visit(TypeOp typeOp)  {
        try{
        switch (typeOp.getType()) {
            case "Boolean":
                fileWriter.write("bool");
                break;
            case "int":
                fileWriter.write("int");
                break;
            case "float":
                fileWriter.write("float");
                break;
            case "string":
                fileWriter.write("char*");
                break;
        }
        }
         catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(ProcParamsOp procParamsOp) {

        procParamsOp.getType().accept(this);
        procParamsOp.getParamId().accept(this);

        return null;
    }

    @Override
    public Object visit(ProcParamIdOp procParamIdOp)  {
        try {
            fileWriter.write(" ");
/*
        if(procParamIdOp.getOut())
            fileWriter.write("*");
*/

        procParamIdOp.getId().accept(this);



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(VarDeclOp varDeclOp) {
        Iterator<IdOp> ids = varDeclOp.getIds().iterator();
        try {
            if(varDeclOp.getConstant().isEmpty()){ //decl con tipo
                while (ids.hasNext()) {
                    varDeclOp.getType().accept(this);
                    fileWriter.write(" ");
                    ids.next().accept(this);
                    if(varDeclOp.getType().getType().equalsIgnoreCase("string"))
                        fileWriter.write(" = malloc(sizeof(char)*512)");

                    fileWriter.write(";\n");
                }
            }
            else{ //decl con costanti
                Iterator<Expr> constant = varDeclOp.getConstant().iterator();
                while (ids.hasNext() && constant.hasNext()){
                    IdOp idop= ids.next();
                    SymbolType symbolType = table.typeOfId(idop);
                    symbolType.getOutputType().get(0).accept(this);
                    fileWriter.write(" ");
                    idop.accept(this);
                    fileWriter.write(" = ");
                    if(symbolType.getOutputType().get(0).getType().equals("string")){
                        fileWriter.write(" malloc(sizeof(char)*512);\n");
                        fileWriter.write("strcpy(");
                        idop.accept(this);
                        fileWriter.write(",");
                        constant.next().accept(this);
                        fileWriter.write(");\n");
                    }
                    else {
                        constant.next().accept(this);
                        fileWriter.write(";\n");
                    }
                }

            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(AssignStatOp assignStatOp) { //TODO
        try {
            Collections.reverse(assignStatOp.getIds());
        Iterator<IdOp> ids = assignStatOp.getIds().iterator();
        while (ids.hasNext())
        for(Expr expr: assignStatOp.getExprs()){
            int i=1;
            if(expr instanceof FunCallOp funCallOp && table.typeOfId(funCallOp.getId()).getOutputType().size() > 1) {
                fileWriter.write("returnOf");
                funCallOp.getId().accept(this);
                fileWriter.write(" struct" + i + " =");

                funCallOp.accept(this);
                fileWriter.write(";\n");

                //TODO

                for(int j =0; j< assignStatOp.getIds().size(); j++){
                    ids.next().accept(this);
                    fileWriter.write(" = ");
                    fileWriter.write("struct" + i +".returnValue" + (j+1) + ";\n");

                }
                i++;
            }
            else{
                ids.next().accept(this);
                fileWriter.write(" = ");
                expr.accept(this);
                fileWriter.write(";\n");

            }

        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

        return null;
    }


    @Override
    public Object visit(ProcCallOp procCallOp) {

        try {
            procCallOp.getId().accept(this);
            fileWriter.write("(");
            LinkedList<Expr> exprs = procCallOp.getProcExprs().getExprs();
            Collections.reverse(exprs);
            if(exprs.size() > 0)
               for(int i = 0; i < exprs.size();i++){
                   if(exprs.get(i) instanceof IdOp id && id.isRef() && !isString(id)) {
                       fileWriter.write("&");
                        fileWriter.write(id.getName());
                   }
                   else
                        exprs.get(i).accept(this);
                   if((exprs.size() - i) != 1)
                       fileWriter.write(",");
                }

            fileWriter.write(");");
            fileWriter.write("\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Object visit(ProcExprsOp procExprsOp) {
        return null;
    }

    @Override
    public Object visit(ReadOp readOp) {
        try {//TODO prima printf e poi scanf
            Collections.reverse(readOp.getExprs());
            if(!readOp.getExprs().isEmpty())
                for(int i=0; i< readOp.getExprs().size() ; i++){
                    Expr expr = readOp.getExprs().get(i);
                    if(expr.getDollar()){
                        fileWriter.write("scanf(\"");
                        String type = table.typeOfId((IdOp) expr).getOutputType().get(0).getType();
                        if(type.equalsIgnoreCase("string"))
                            fileWriter.write("%s\",");
                        if(type.equalsIgnoreCase("int"))
                            fileWriter.write("%d\",&");
                        if(type.equalsIgnoreCase("float"))
                            fileWriter.write("%f\",&");


                    expr.accept(this);
                    fileWriter.write(");\n");
                    }
                    else {
                        fileWriter.write("printf(");
                        expr.accept(this);
                        fileWriter.write(");\n");
                    }
                }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


    @Override
    public Object visit(Statement statement) {
        return null;
    }

    @Override
    public Object visit(WhileStatOp whileStatOp) {

        try {
            fileWriter.write("while(");
            whileStatOp.getExpr().accept(this);
            fileWriter.write(")");
            table = whileStatOp.getSymbolTable();
            whileStatOp.getBody().accept(this);
            table = whileStatOp.getSymbolTable().getFather();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Object visit(ReturnStatOp returnStatOp) {
        //se più valori da ritornare definisco e inizializzo la struct
        try {
        if(returnStatOp.getExprs().size() > 1){
            fileWriter.write("returnOf" + table.getScope() + " structReturn;\n");
            for(int i=0; i < returnStatOp.getExprs().size(); i++){
                fileWriter.write("structReturn.returnValue" + (i+1) +" = ");
                if(returnStatOp.getExprs().get(i) instanceof StringOp) {
                    fileWriter.write("\"");
                    returnStatOp.getExprs().get(i).accept(this);
                    fileWriter.write("\"");
                }
                else
                    returnStatOp.getExprs().get(i).accept(this);
                fileWriter.write(";\n");
            }

            fileWriter.write("return structReturn;\n");

        }
        else{
            fileWriter.write("return ");
            returnStatOp.getExprs().get(0).accept(this);
            fileWriter.write(";\n");
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    return null;
    }

    @Override
    public Object visit(WriteOp writeOp) {

        try {
            fileWriter.write("printf(");
            String type;
            Collections.reverse(writeOp.getIOArgs());

            if(!writeOp.getIOArgs().isEmpty()) {
                fileWriter.write("\"");
                for (Expr expr : writeOp.getIOArgs()) {
                    if (expr.getDollar()) {
                        if (expr instanceof FunCallOp)
                            type = ((LinkedList<TypeOp>) expr.accept(typeVisitor)).get(0).getType();  //TODO passare il visitor nel costruttore
                        else if (expr instanceof IdOp)
                            type = table.typeOfId((IdOp) expr).getOutputType().get(0).getType();
                        else
                            type = (String) expr.accept(typeVisitor);

                        if (type.equalsIgnoreCase("string"))
                            fileWriter.write("%s");
                        if (type.equalsIgnoreCase("int") || type.equalsIgnoreCase("Boolean"))
                            fileWriter.write("%d");
                        if (type.equalsIgnoreCase("float"))
                            fileWriter.write("%f");
                    } else if (!expr.getDollar() && expr instanceof StringOp string) {
                        fileWriter.write(" " + string.getAttribute());
                    }
                }
            }

            if(writeOp.getIOArgs().isEmpty()) //printf vuota
                fileWriter.write("\"");
            //se writereturn
            if(writeOp.getType())
                fileWriter.write("\\n\""); //TODO \n??
            else
                fileWriter.write("\"");

            if(writeOp.getIOArgs().size() > 0) {
                List<Expr> temp =  writeOp.getIOArgs().stream().filter(expr -> expr.getDollar()).toList();
                if(!temp.isEmpty())
                    fileWriter.write(",");
                for (int i = 0; i < temp.size(); i++) {
                    if (temp.get(i).getDollar()) {
                        temp.get(i).accept(this);
                        if ((temp.size() - i) != 1)
                            fileWriter.write(",");
                    }
                }
            }

                fileWriter.write(");\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }




    private boolean isString(Expr expr){

        if(expr instanceof StringOp)
            return true;
        if(expr instanceof FunCallOp) {
            FunCallOp func = (FunCallOp) expr;
            String type = table.typeOfId(func.getId()).getOutputType().get(0).getType();
            if (type.equalsIgnoreCase("string"))
                return true;
        }
        if(expr instanceof IdOp){
            IdOp id = (IdOp) expr;
            String typeId = table.typeOfId(id).getOutputType().get(0).getType();
            if(typeId.equalsIgnoreCase("string"))
                return true;
        }

        return false;
    }


    private void writeLibrary(){
        try {
            fileWriter.write("#include <stdio.h>\n");
            fileWriter.write("#include <string.h>\n");
            fileWriter.write("#include <stdlib.h>\n");
            fileWriter.write("#include <stdbool.h>\n");

            fileWriter.write("\n");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
//TODO nelle procedure se passso una stringa non deve mettere *